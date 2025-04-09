package algo.trading.config;

import algo.trading.exception.handler.AlorIntegrationErrorHandler;
import algo.trading.service.AlorTokenStorageService;
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
   * Simple RestClient.
   *
   * @return plain RestClient instance
   */
  @Bean
  public RestClient restClient() {
    return RestClient.builder().defaultStatusHandler(new AlorIntegrationErrorHandler()).build();
  }

  /**
   * RestClient with authentication headers for Alor broker API requests. Automatically refreshes
   * the token and retries on 401 Unauthorized responses.
   *
   * @param tokenService service that provides and refreshes access tokens
   * @return configured authenticated RestClient for Alor
   */
  @Bean(name = "alorAuthRestClient")
  public RestClient alorAuthRestClient(AlorTokenStorageService tokenService) {
    return RestClient.builder()
        .requestInterceptor(createAuthInterceptor(tokenService))
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
