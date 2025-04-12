package algo.trading.client;

import static algo.trading.client.common.AlorApiPath.GET_MARKET_HISTORY;
import static algo.trading.client.common.AlorQueryParam.COUNT_BACK;
import static algo.trading.client.common.AlorQueryParam.EXCHANGE;
import static algo.trading.client.common.AlorQueryParam.FORMAT;
import static algo.trading.client.common.AlorQueryParam.FROM;
import static algo.trading.client.common.AlorQueryParam.SPLIT_ADJUST;
import static algo.trading.client.common.AlorQueryParam.SYMBOL;
import static algo.trading.client.common.AlorQueryParam.TF;
import static algo.trading.client.common.AlorQueryParam.TO;
import static algo.trading.client.common.AlorQueryParam.UNTRADED;

import algo.trading.client.common.AlorRequestBuilder;
import algo.trading.client.request.MarketHistoryRequest;
import algo.trading.client.response.MarketHistoryResponse;
import algo.trading.config.AlorIntegrationProperty;
import java.net.URI;
import org.springframework.web.client.RestClient;

/**
 * Client for interacting with Alor API to fetch various data for financial instruments, including
 * market history, trades, quotes, and other instrument-related information.
 */
public class AlorSecurityInfoClient {
  private final RestClient alorAuthRestClient;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Constructor to initialize the client with necessary dependencies.
   *
   * @param alorAuthRestClient the RestClient used to send HTTP requests
   * @param alorIntegrationProperty configuration properties for Alor API
   */
  public AlorSecurityInfoClient(
      RestClient alorAuthRestClient, AlorIntegrationProperty alorIntegrationProperty) {
    this.alorAuthRestClient = alorAuthRestClient;
    this.alorIntegrationProperty = alorIntegrationProperty;
  }

  /**
   * Retrieves market history data based on the provided request.
   *
   * @param request the request containing the parameters for market history query
   * @return the response containing market history data
   */
  public MarketHistoryResponse getMarketHistory(MarketHistoryRequest request) {
    URI uri =
        AlorRequestBuilder.from(alorIntegrationProperty.getApiUrl(), GET_MARKET_HISTORY.path())
            .with(SYMBOL, request.instrument().symbol())
            .with(EXCHANGE, request.instrument().exchange())
            .with(TF, request.tf())
            .with(FROM, request.from())
            .with(TO, request.to())
            .with(COUNT_BACK, request.countBack())
            .with(UNTRADED, request.untraded())
            .with(SPLIT_ADJUST, request.splitAdjust())
            .with(FORMAT, request.format())
            .build();

    return alorAuthRestClient.get().uri(uri).retrieve().body(MarketHistoryResponse.class);
  }
}
