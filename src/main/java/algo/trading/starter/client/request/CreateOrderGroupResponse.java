package algo.trading.starter.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** Response payload for create order group. */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderGroupResponse {
  private String message;
  private String groupId;
}
