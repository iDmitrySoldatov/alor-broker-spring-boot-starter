package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.exception.handler.AlorIntegrationErrorHandler;
import algo.trading.starter.service.AlorTokenStorageService;
import algo.trading.starter.service.RefreshTokenProvider;
import algo.trading.starter.service.RestClientProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

/** Autoconfiguration for RestClient beans used for communication with Alor Broker services. */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestClientAutoConfiguration {
  public static final String TOKEN_PREFIX = "Bearer ";
  private final AlorIntegrationProperty alorIntegrationProperty;
  private final ObjectMapper mapper;

  /**
   * RestClient without authentication.
   *
   * @return plain RestClient instance
   */
  @Bean
  public RestClient alorRestClient() {
    return RestClient.builder()
        .baseUrl(alorIntegrationProperty.getApiUrl())
        .defaultStatusHandler(new AlorIntegrationErrorHandler())
        .build();
  }

  /**
   * RestClientProvider instance.
   *
   * @param restClientFactory bean
   * @param refreshTokenProvider bean
   * @return configured RestClientProvider
   */
  @Bean
  public RestClientProvider restClientProvider(
      Function<String, RestClient> restClientFactory, RefreshTokenProvider refreshTokenProvider) {
    return new RestClientProvider(restClientFactory, refreshTokenProvider);
  }

  /**
   * RestClientFactory instance.
   *
   * @param alorAuthClient rest client bean
   * @return configured restClientFactory
   */
  @Bean
  public Function<String, RestClient> restClientFactory(AlorAuthClient alorAuthClient) {
    return refreshToken -> getRestClient(alorAuthClient, refreshToken);
  }

  private RestClient getRestClient(AlorAuthClient alorAuthClient, String refreshToken) {
    return RestClient.builder()
        .requestInterceptor(
            createAuthInterceptor(new AlorTokenStorageService(alorAuthClient, refreshToken)))
        .requestInterceptor(createLoggingInterceptor())
        .defaultStatusHandler(new AlorIntegrationErrorHandler())
        .baseUrl(alorIntegrationProperty.getApiUrl())
        .build();
  }

  private ClientHttpRequestInterceptor createLoggingInterceptor() {
    return (request, body, execution) -> {
      if (log.isDebugEnabled()) {
        log.debug(
            "Execute request: url={}, headers={}, body={}",
            request.getURI(),
            request.getHeaders().entrySet().stream()
                .filter(entry -> !HttpHeaders.AUTHORIZATION.equalsIgnoreCase(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
            new String(body));
      }
      ClientHttpResponse response = execution.execute(request, body);
      byte[] responseBody = StreamUtils.copyToByteArray(response.getBody());
      if (log.isDebugEnabled()) {
        log.debug(
            "Response received: status={}, headers={}, body={}",
            response.getStatusCode(),
            response.getHeaders(),
            new String(responseBody));
      }
      return new BufferedClientHttpResponse(response, responseBody);
    };
  }

  private ClientHttpRequestInterceptor createAuthInterceptor(
      AlorTokenStorageService alorTokenStorageService) {
    return (request, body, execution) -> {
      String token = alorTokenStorageService.getAccessToken();
      if (!StringUtils.hasText(token)) {
        alorTokenStorageService.refreshAccessToken();
        token = alorTokenStorageService.getAccessToken();
      }
      if (needRefresh(token)) {
        alorTokenStorageService.refreshAccessToken();
        token = alorTokenStorageService.getAccessToken();
      }
      request.getHeaders().set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);

      ClientHttpResponse response = execution.execute(request, body);

      if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        alorTokenStorageService.refreshAccessToken();
        String refreshedToken = alorTokenStorageService.getAccessToken();
        request.getHeaders().set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + refreshedToken);
        response = execution.execute(request, body);
      }

      return response;
    };
  }

  private boolean needRefresh(String token) {
    Map<String, String> bodyMap = getBodyMap(token);
    String exp = bodyMap.get("exp");
    if (Objects.isNull(exp)) {
      return false;
    }
    return Instant.now().isBefore(Instant.ofEpochSecond(Long.parseLong(exp) + 10));
  }

  private Map<String, String> getBodyMap(String token) {
    try {
      String[] split = token.split("\\.");
      if (split.length < 2 || !StringUtils.hasText(split[1])) {
        return Map.of();
      }
      String body = new String(Base64.getDecoder().decode(split[1]));
      return mapper.readValue(body, new TypeReference<>() {});
    } catch (Exception e) {
      log.warn("Can't get SUB from token", e);
      return Map.of();
    }
  }

  private static class BufferedClientHttpResponse implements ClientHttpResponse {
    private final ClientHttpResponse response;
    private final byte[] body;

    public BufferedClientHttpResponse(ClientHttpResponse response, byte[] body) {
      this.response = response;
      this.body = body;
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
      return response.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
      return response.getStatusText();
    }

    @Override
    public void close() {
      response.close();
    }

    @Override
    public InputStream getBody() throws IOException {
      return new ByteArrayInputStream(body);
    }

    @Override
    public HttpHeaders getHeaders() {
      return response.getHeaders();
    }
  }
}
