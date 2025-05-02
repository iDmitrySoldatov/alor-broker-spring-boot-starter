package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Side;
import algo.trading.starter.client.common.TimeInForce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request payload for creating a market order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMarketOrderRequest {
  /* Order side: buy or sell. */
  private Side side;

  /* Quantity in lots. Example: 1 */
  private int quantity;

  /* Instrument information including symbol; exchange; and group. */
  private Instrument instrument;

  /* Custom comment for the order. */
  private String comment;

  /* User info including portfolio ID. */
  private User user;

  /* Order validity type. Default: oneday. */
  private TimeInForce timeInForce;

  /* Allow margin trading. Must be true for uncovered positions. */
  private boolean allowMargin;
}
