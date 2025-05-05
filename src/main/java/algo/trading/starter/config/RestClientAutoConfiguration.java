package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.exception.handler.AlorIntegrationErrorHandler;
import algo.trading.starter.service.AlorTokenStorageService;
import algo.trading.starter.service.RefreshTokenProvider;
import algo.trading.starter.service.RestClientProvider;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

/** Autoconfiguration for RestClient beans used for communication with Alor Broker services. */
@Configuration
public class RestClientAutoConfiguration {
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * RestClient without authentication.
   *
   * @return plain RestClient instance
   */
  @Bean
  public RestClient alorRestClient() {
    return RestClient.builder().defaultStatusHandler(new AlorIntegrationErrorHandler()).build();
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
        .defaultStatusHandler(new AlorIntegrationErrorHandler())
        .build();
  }

  private ClientHttpRequestInterceptor createAuthInterceptor(
      AlorTokenStorageService alorTokenStorageService) {
    return ((request, body, execution) -> {
      String token = alorTokenStorageService.getAccessToken();
      request.getHeaders().set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);

      ClientHttpResponse response = execution.execute(request, body);

      if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        alorTokenStorageService.refreshAccessToken();
        String refreshedToken = alorTokenStorageService.getAccessToken();
        request.getHeaders().set(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + refreshedToken);
        response = execution.execute(request, body);
      }

      return response;
    });
  }
}
