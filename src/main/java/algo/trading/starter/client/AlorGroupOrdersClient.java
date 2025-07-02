package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.ORDER_GROUP_BASE;
import static algo.trading.starter.client.common.AlorApiPath.ORDER_GROUP_BY_ID;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.request.CreateOrderGroupRequest;
import algo.trading.starter.client.request.CreateOrderGroupResponse;
import algo.trading.starter.client.response.GetOrderGroupResponse;
import algo.trading.starter.service.RestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

/** Client for manage groups of orders via Alor trading API. */
@RequiredArgsConstructor
public class AlorGroupOrdersClient {
  private static final String X_REQID = "X-REQID";

  private final RestClientProvider restClientProvider;

  /**
   * Creates a group of orders by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param request the order request data, including all necessary fields
   * @return the response containing the id of created group
   */
  public CreateOrderGroupResponse createOrderGroup(CreateOrderGroupRequest request) {
    return restClientProvider
        .getRestClient()
        .post()
        .uri(ORDER_GROUP_BASE.path())
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .body(CreateOrderGroupResponse.class);
  }

  /**
   * Get group of orders by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param groupId id of orders groups
   * @return the response containing status of group
   */
  public GetOrderGroupResponse getOrderGroup(String groupId) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(ORDER_GROUP_BY_ID.path(), groupId))
        .retrieve()
        .body(GetOrderGroupResponse.class);
  }

  /**
   * Delete group of orders by sending a request to the Alor trading API.
   *
   * <p>The client must provide all necessary fields in the request. If any required fields are
   * missing, the request will be invalid.
   *
   * @param groupId id of orders groups
   */
  public void deleteOrderGroup(String groupId) {
    restClientProvider
        .getRestClient()
        .delete()
        .uri(format(ORDER_GROUP_BY_ID.path(), groupId))
        .retrieve()
        .toEntity(Void.class);
  }
}
