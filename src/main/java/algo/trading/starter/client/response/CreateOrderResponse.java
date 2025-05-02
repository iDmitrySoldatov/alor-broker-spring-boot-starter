package algo.trading.starter.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of a successful market order creation. Contains a confirmation message
 * and the unique order ID.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
