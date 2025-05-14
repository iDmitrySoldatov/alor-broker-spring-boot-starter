package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.client.AlorConditionalOrdersClient;
import algo.trading.starter.client.AlorExchangeOrdersClient;
import algo.trading.starter.client.AlorInstrumentInfoClient;
import algo.trading.starter.client.AlorSecurityInfoClient;
import algo.trading.starter.service.RestClientProvider;
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
  public AlorAuthClient authAlorClient(
      AlorIntegrationProperty property, RestClient alorRestClient) {
    return new AlorAuthClient(property, alorRestClient);
  }

  /**
   * Configures AlorSecurityInfoClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorSecurityInfoClient alorSecurityInfoClient(
      AlorIntegrationProperty property, RestClient restClientProvider) {
    return new AlorSecurityInfoClient(restClientProvider, property);
  }

  /**
   * Configures AlorExchangeOrdersClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorExchangeOrdersClient alorExchangeOrdersClient(
      AlorIntegrationProperty property, RestClientProvider restClientProvider) {
    return new AlorExchangeOrdersClient(restClientProvider, property);
  }

  /**
   * Configures AlorConditionalOrdersClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorConditionalOrdersClient alorConditionalOrdersClient(
      AlorIntegrationProperty property, RestClientProvider restClientProvider) {
    return new AlorConditionalOrdersClient(restClientProvider, property);
  }

  /**
   * Configures AlorInstrumentInfoClient for interacting with Alor Broker API.
   *
   * @param property Integration properties including API URL.
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorInstrumentInfoClient alorInstrumentInfoClient(
      AlorIntegrationProperty property, RestClientProvider restClientProvider) {
    return new AlorInstrumentInfoClient(restClientProvider, property);
  }
}
