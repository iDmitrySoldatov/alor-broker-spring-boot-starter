package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Side;
import algo.trading.starter.client.common.StopCondition;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request payload for creating a stop order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStopOrderRequest {
  /* Order side: buy or sell. */
  private Side side;

  /* Triggering condition for stop and stop limit orders. */
  private StopCondition condition;

  /* Conditional price (trigger price). */
  private BigDecimal triggerPrice;

  /* Stop time for stop order. Validity time (UTC) in Unix Time Seconds format. */
  private Long stopEndUnixTime;

  /* Quantity in lots. Example: 1 */
  private int quantity;

  /* Instrument information including symbol; exchange; and group. */
  private Instrument instrument;

  /* User info including portfolio ID. */
  private User user;

  /* Allow margin trading. Must be true for uncovered positions. */
  private boolean allowMargin;

  /* Protective time. Continuous period of time in seconds; during which
   * the market price of the instrument must satisfy the price and triggering
   * condition specified in the order (triggerPrice and condition).
   * It is intended to protect conditional orders from triggering
   * on short-term price fluctuations.
   * Possible values: >= 1 and <= 300
   */
  private Integer protectingSeconds;

  /* The flag specifies whether to create an active or inactive order.
   * An inactive order is displayed in the system; but is not counted in trading until
   * it becomes active. This flag is required when creating a group of orders
   * with the 'TriggerBracketOrders' type
   */
  private Boolean activate;
}
