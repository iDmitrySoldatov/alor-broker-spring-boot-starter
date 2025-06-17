package algo.trading.starter.client.request;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.common.AlorOrderGroupType;
import algo.trading.starter.client.common.AlorOrderType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request payload for create order group. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderGroupRequest {

  private List<Order> orders;
  private AlorOrderGroupType executionPolicy;

  /** Dto representing order for create order group request. */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Order {
    private String portfolio;
    private AlorExchange exchange;
    private String orderId;
    private AlorOrderType type;
  }
}
