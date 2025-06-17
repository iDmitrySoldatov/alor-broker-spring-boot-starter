package algo.trading.starter.client.response;

import algo.trading.starter.client.common.AlorOrderGroupStatus;
import algo.trading.starter.client.common.AlorOrderGroupType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response payload for get order group. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderGroupResponse {
  private String id;
  private String login;
  private List<Order> orders;
  private AlorOrderGroupType executionPolicy;
  private AlorOrderGroupStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime closedAt;

  /** Dto representing order for get order group request. */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Order {
    private Long orderId;
  }
}
