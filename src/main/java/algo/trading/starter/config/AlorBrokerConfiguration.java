package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.client.AlorConditionalOrdersClient;
import algo.trading.starter.client.AlorExchangeOrdersClient;
import algo.trading.starter.client.AlorInstrumentInfoClient;
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
   * @param alorRestClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  @ConditionalOnProperty("alor.integration.refreshToken")
  public AlorAuthClient authAlorClient(
      AlorIntegrationProperty property, RestClient alorRestClient) {
    return new AlorAuthClient(property, alorRestClient);
  }

  /**
   * Configures AlorSecurityInfoClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param alorRestClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorSecurityInfoClient alorSecurityInfoClient(
      AlorIntegrationProperty property, RestClient alorRestClient) {
    return new AlorSecurityInfoClient(alorRestClient, property);
  }

  /**
   * Configures AlorExchangeOrdersClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  @ConditionalOnProperty("alor.integration.refreshToken")
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
  @ConditionalOnProperty("alor.integration.refreshToken")
  public AlorConditionalOrdersClient alorConditionalOrdersClient(
      AlorIntegrationProperty property, @Qualifier("alorAuthRestClient") RestClient restClient) {
    return new AlorConditionalOrdersClient(restClient, property);
  }

  /**
   * Configures AlorTokenStorageService for managing access tokens and authentication.
   *
   * @param alorAuthClient AuthorizationAlorClient for interacting with the Alor Broker API.
   * @return A configured AlorTokenService.
   */
  @Bean
  @ConditionalOnProperty("alor.integration.refreshToken")
  public AlorTokenStorageService alorTokenStorageService(
      AlorAuthClient alorAuthClient) {
    return new AlorTokenStorageService(alorAuthClient);
  }

  /**
   * Configures AlorInstrumentInfoClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClient RestClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  @ConditionalOnProperty("alor.integration.refreshToken")
  public AlorInstrumentInfoClient alorInstrumentInfoClient(
      AlorIntegrationProperty property, @Qualifier("alorAuthRestClient") RestClient restClient) {
    return new AlorInstrumentInfoClient(restClient, property);
  }
}
