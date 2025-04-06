package algo.trading.config;

import algo.trading.client.AlorAuthClient;
import algo.trading.client.AlorSecurityInfoClient;
import algo.trading.service.AlorTokenStorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/** Configuration for Alor Broker integration starter. */
@Configuration
@EnableConfigurationProperties(AlorIntegrationProperty.class)
public class AlorBrokerConfiguration {
  /**
   * Configures AlorAuthClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorAuthClient authAlorClient(AlorIntegrationProperty property, RestClient restClient) {
    return new AlorAuthClient(property, restClient);
  }

  /**
   * Configures AlorSecurityInfoClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorSecurityInfoClient alorInstrumentClient(
      AlorIntegrationProperty property, RestClient restClient) {
    return new AlorSecurityInfoClient(restClient, property);
  }

  /**
   * Configures AlorTokenStorageService for managing access tokens and authentication.
   *
   * @param alorAuthClient AuthorizationAlorClient for interacting with the Alor Broker API.
   * @param property Integration properties including refreshToken.
   * @return A configured AlorTokenService.
   */
  @Bean
  public AlorTokenStorageService alorTokenStorageService(
      AlorAuthClient alorAuthClient, AlorIntegrationProperty property) {
    return new AlorTokenStorageService(alorAuthClient, property);
  }
}
