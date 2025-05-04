package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.CREATE_LIMIT_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.CREATE_MARKET_ORDER;

import algo.trading.starter.client.request.CreateLimitOrderRequest;
import algo.trading.starter.client.request.CreateMarketOrderRequest;
import algo.trading.starter.client.response.CreateOrderResponse;
import algo.trading.starter.config.AlorIntegrationProperty;
import algo.trading.starter.service.RestClientProvider;
import java.util.UUID;
import org.springframework.http.MediaType;

/** Client for placing exchange orders via Alor trading API. */
public class AlorExchangeOrdersClient {
  private static final String X_REQID = "X-REQID";

  private final RestClientProvider restClientProvider;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Initializes the exchange orders client with required dependencies.
   *
   * @param restClientProvider authenticated HTTP client for Alor API
   * @param alorIntegrationProperty configuration properties (e.g. API base URL, fallback values)
   */
  public AlorExchangeOrdersClient(
      RestClientProvider restClientProvider, AlorIntegrationProperty alorIntegrationProperty) {
    this.restClientProvider = restClientProvider;
    this.alorIntegrationProperty = alorIntegrationProperty;
  }

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
    String baseUrl = alorIntegrationProperty.getApiUrl();
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(baseUrl + CREATE_MARKET_ORDER.path())
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
    String baseUrl = alorIntegrationProperty.getApiUrl();
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(baseUrl + CREATE_LIMIT_ORDER.path())
        .header(X_REQID, requestId)
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderResponse.class);
  }
}
