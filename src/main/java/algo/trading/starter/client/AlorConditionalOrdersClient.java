package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.CREATE_STOP_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.DELETE_CONDITIONAL_ORDER;
import static algo.trading.starter.client.common.AlorApiPath.GET_CONDITIONAL_ORDER;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.common.AlorQueryParam;
import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.client.request.CreateStopOrderRequest;
import algo.trading.starter.client.response.CreateOrderResponse;
import algo.trading.starter.client.response.GetConditionalOrderResponse;
import algo.trading.starter.service.RestClientProvider;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

/** Client for placing conditional (e.g., stop) orders via Alor trading API. */
@RequiredArgsConstructor
public class AlorConditionalOrdersClient {
  private static final String X_REQID = "X-REQID";
  private final RestClientProvider restClientProvider;

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
    String requestId = UUID.randomUUID().toString();
    return restClientProvider
        .getRestClient()
        .post()
        .uri(CREATE_STOP_ORDER.path())
        .header(X_REQID, requestId)
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderResponse.class);
  }

  /**
   * Get conditional order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @param orderId the orderId
   * @return the response containing the conditional order
   */
  public GetConditionalOrderResponse getStopOrder(
      AlorExchange exchange, String portfolio, String orderId) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(GET_CONDITIONAL_ORDER.path(), exchange, portfolio, orderId))
        .retrieve()
        .body(GetConditionalOrderResponse.class);
  }

  /**
   * Delete conditional order by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @param orderId the orderId
   */
  public void deleteStopOrder(AlorExchange exchange, String portfolio, String orderId) {
    URI uri =
        AlorRequestBuilder.from(format(DELETE_CONDITIONAL_ORDER.path(), orderId))
            .with(AlorQueryParam.EXCHANGE, exchange.name())
            .with(AlorQueryParam.PORTFOLIO, portfolio)
            .with(AlorQueryParam.STOP, Boolean.TRUE.toString())
            .build();
    restClientProvider.getRestClient().delete().uri(uri).retrieve().toEntity(Void.class);
  }
}
