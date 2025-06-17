package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.CREATE_LIMIT_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.CREATE_MARKET_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.ESTIMATE_MARKET_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.GET_MARKET_ORDER;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.request.CreateLimitOrderRequest;
import algo.trading.starter.client.request.CreateMarketOrderRequest;
import algo.trading.starter.client.request.EstimateMarketOrderRequest;
import algo.trading.starter.client.request.EstimateMarketOrderResponse;
import algo.trading.starter.client.response.CreateOrderResponse;
import algo.trading.starter.client.response.GetMarketOrderResponse;
import algo.trading.starter.service.RestClientProvider;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

/** Client for placing exchange orders via Alor trading API. */
@RequiredArgsConstructor
public class AlorExchangeOrdersClient {
  private static final String X_REQID = "X-REQID";

  private final RestClientProvider restClientProvider;

  /**
   * Creates a market order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param request the order request data, including all necessary fields
   * @return the response containing the order number and status information
   */
  public CreateOrderResponse createMarketOrder(CreateMarketOrderRequest request) {
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(CREATE_MARKET_ORDER.path())
        .header(X_REQID, requestId)
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderResponse.class);
  }

  /**
   * Creates a limit order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param request the order request data, including all necessary fields
   * @return the response containing the order number and status information
   */
  public CreateOrderResponse createLimitOrder(CreateLimitOrderRequest request) {
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(CREATE_LIMIT_ORDER.path())
        .header(X_REQID, requestId)
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderResponse.class);
  }

  /**
   * Estimates a market order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param request the order request data, including all necessary fields
   * @return the response containing the estimation result
   */
  public EstimateMarketOrderResponse estimateMarketOrder(EstimateMarketOrderRequest request) {
    return restClientProvider
        .getRestClient()
        .post()
        .uri(ESTIMATE_MARKET_ORDER.path())
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(EstimateMarketOrderResponse.class);
  }

  /**
   * Get market order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @param orderId the orderId
   * @return the response containing the market order
   */
  public GetMarketOrderResponse getMarketOrder(
      AlorExchange exchange, String portfolio, String orderId) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(GET_MARKET_ORDER.path(), exchange, portfolio, orderId))
        .retrieve()
        .body(GetMarketOrderResponse.class);
  }
}
