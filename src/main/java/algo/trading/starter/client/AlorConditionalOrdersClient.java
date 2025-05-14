package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.CREATE_STOP_ORDER;

import algo.trading.starter.client.request.CreateStopOrderRequest;
import algo.trading.starter.client.response.CreateOrderResponse;
import algo.trading.starter.config.AlorIntegrationProperty;
import algo.trading.starter.service.RestClientProvider;
import java.util.UUID;
import org.springframework.http.MediaType;

/** Client for placing conditional (e.g., stop) orders via Alor trading API. */
public class AlorConditionalOrdersClient {
  private final RestClientProvider restClientProvider;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Initializes the conditional orders client with required dependencies.
   *
   * @param restClientProvider provider of authenticated HTTP client for Alor API
   * @param alorIntegrationProperty configuration properties (e.g. API base URL)
   */
  public AlorConditionalOrdersClient(
      RestClientProvider restClientProvider, AlorIntegrationProperty alorIntegrationProperty) {
    this.restClientProvider = restClientProvider;
    this.alorIntegrationProperty = alorIntegrationProperty;
  }

  /**
   * Creates a stop order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all required fields in the request. If any mandatory fields are
   * missing, the request will be rejected.
   *
   * @param request the stop order request data
   * @return the response containing the order number and status information
   */
  public CreateOrderResponse createStopOrder(CreateStopOrderRequest request) {
    String baseUrl = alorIntegrationProperty.getApiUrl();
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(baseUrl + CREATE_STOP_ORDER.path())
        .header("X-REQID", requestId)
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderResponse.class);
  }
}
