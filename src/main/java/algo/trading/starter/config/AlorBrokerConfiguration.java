package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.client.AlorConditionalOrdersClient;
import algo.trading.starter.client.AlorExchangeOrdersClient;
import algo.trading.starter.client.AlorSecurityInfoClient;
import algo.trading.starter.service.AlorTokenStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
  @ConditionalOnProperty("refreshToken")
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
  public AlorSecurityInfoClient alorSecurityInfoClient(
      AlorIntegrationProperty property, RestClient restClient) {
    return new AlorSecurityInfoClient(restClient, property);
  }

  /**
   * Configures AlorExchangeOrdersClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  @ConditionalOnProperty("refreshToken")
  public AlorExchangeOrdersClient alorExchangeOrdersClient(
      AlorIntegrationProperty property, @Qualifier("alorAuthRestClient") RestClient restClient) {
    return new AlorExchangeOrdersClient(restClient, property);
  }

  /**
   * Configures AlorConditionalOrdersClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  @ConditionalOnProperty("refreshToken")
  public AlorConditionalOrdersClient alorConditionalOrdersClient(
      AlorIntegrationProperty property, @Qualifier("alorAuthRestClient") RestClient restClient) {
    return new AlorConditionalOrdersClient(restClient, property);
  }

  /**
   * Configures AlorTokenStorageService for managing access tokens and authentication.
   *
   * @param alorAuthClient AuthorizationAlorClient for interacting with the Alor Broker API.
   * @param property Integration properties including refreshToken.
   * @return A configured AlorTokenService.
   */
  @Bean
  @ConditionalOnProperty("refreshToken")
  public AlorTokenStorageService alorTokenStorageService(
      AlorAuthClient alorAuthClient, AlorIntegrationProperty property) {
    return new AlorTokenStorageService(alorAuthClient, property);
  }
}
