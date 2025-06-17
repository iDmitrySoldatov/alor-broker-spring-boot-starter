package algo.trading.starter.config;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.client.AlorConditionalOrdersClient;
import algo.trading.starter.client.AlorExchangeOrdersClient;
import algo.trading.starter.client.AlorGroupOrdersClient;
import algo.trading.starter.client.AlorHistoryInfoClient;
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
   * @param alorRestClient restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorSecurityInfoClient alorSecurityInfoClient(RestClient alorRestClient) {
    return new AlorSecurityInfoClient(alorRestClient);
  }

  /**
   * Configures AlorHistoryInfoClient for interacting with Alor Broker API.
   *
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorHistoryInfoClient alorHistoryInfoClient(RestClientProvider restClientProvider) {
    return new AlorHistoryInfoClient(restClientProvider);
  }

  /**
   * Configures AlorExchangeOrdersClient for interacting with Alor Broker API.
   *
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorExchangeOrdersClient alorExchangeOrdersClient(RestClientProvider restClientProvider) {
    return new AlorExchangeOrdersClient(restClientProvider);
  }

  /**
   * Configures AlorConditionalOrdersClient for interacting with Alor Broker API.
   *
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorConditionalOrdersClient alorConditionalOrdersClient(
      RestClientProvider restClientProvider) {
    return new AlorConditionalOrdersClient(restClientProvider);
  }

  /**
   * Configures AlorInstrumentInfoClient for interacting with Alor Broker API.
   *
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorInstrumentInfoClient alorInstrumentInfoClient(RestClientProvider restClientProvider) {
    return new AlorInstrumentInfoClient(restClientProvider);
  }

  /**
   * Configures AlorGroupOrdersClient for interacting with Alor Broker API.
   *
   * @param restClientProvider provider of restClient for send HTTP request.
   * @return A configured AlorClient.
   */
  @Bean
  public AlorGroupOrdersClient alorGroupOrdersClient(RestClientProvider restClientProvider) {
    return new AlorGroupOrdersClient(restClientProvider);
  }
}
