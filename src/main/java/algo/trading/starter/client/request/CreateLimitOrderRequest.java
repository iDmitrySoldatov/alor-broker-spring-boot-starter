package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Side;
import algo.trading.starter.client.common.TimeInForce;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request payload for creating a market order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLimitOrderRequest {
  /* Order side: buy or sell. */
  private Side side;

  /* Quantity in lots. Example: 1 */
  private Integer quantity;

  /* Price for create order. */
  private BigDecimal price;

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

  /* The visible constant part of the iceberg order in lots set out at the order creation. */
  private Integer icebergFixed;

  /* The deviation amplitude (% of icebergFixed) of the random increment to the visible
  part of the iceberg order set out at the order creation. Derivatives only. */
  private Integer icebergVariance;
}
