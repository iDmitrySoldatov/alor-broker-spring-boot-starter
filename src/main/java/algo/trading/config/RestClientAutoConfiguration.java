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

  /**
   * RestClient with authentication headers for Alor broker API requests. Automatically refreshes
   * the token and retries on 401 Unauthorized responses.
   *
   * @param tokenService service that provides and refreshes access tokens
   * @return configured authenticated RestClient for Alor
   */
  @Bean
  public RestClient alorAuthRestClient(AlorTokenStorageService tokenService) {
    return RestClient.builder()
        .requestInterceptor(createAuthInterceptor(tokenService))
        .defaultStatusHandler(new AlorIntegrationErrorHandler())
        .build();
  }

  /**
   * Simple RestClient.
   *
   * @return plain RestClient instance
   */
  @Bean
  public RestClient restClient() {
    return RestClient.builder().defaultStatusHandler(new AlorIntegrationErrorHandler()).build();
  }

  private ClientHttpRequestInterceptor createAuthInterceptor(
      AlorTokenStorageService alorTokenStorageService) {
    return ((request, body, execution) -> {
      request
          .getHeaders()
          .add(HttpHeaders.AUTHORIZATION, "Bearer " + alorTokenStorageService.getAccessToken());
      ClientHttpResponse response = execution.execute(request, body);
      if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
        alorTokenStorageService.refreshAccessToken();
        request
            .getHeaders()
            .add(HttpHeaders.AUTHORIZATION, "Bearer " + alorTokenStorageService.getAccessToken());
        response = execution.execute(request, body);
      }

      return response;
    });
  }
}
