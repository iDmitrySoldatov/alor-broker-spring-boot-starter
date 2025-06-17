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
  private Integer quantity;

  /* Instrument information including symbol; exchange; and group. */
  private AlorInstrument instrument;

  /* Custom comment for the order. */
  private String comment;

  /* User info including portfolio ID. */
  private AlorUser user;

  /* Order validity type. Default: oneday. */
  private TimeInForce timeInForce;

  /* Allow margin trading. Must be true for uncovered positions. */
  private Boolean allowMargin;
}
