package algo.trading.starter.client.response;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the response of a successful market order creation. Contains a confirmation message
 * and the unique order ID.
 */
@Data
@Builder
public class CreateOrderResponse {
  /*
   * Text message of the response.
   * Example: "success"
   */
  private String message;

  /*
   * Unique order ID assigned by the system.
   * Example: "18995978560"
   */
  private String orderNumber;
}
