package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Side;
import algo.trading.starter.client.common.TimeInForce;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

/** Request payload for creating a market order. */
@Data
@Builder
public class CreateLimitOrderRequest {
  /* Order side: buy or sell. */
  private Side side;

  /* Quantity in lots. Example: 1 */
  private int quantity;

  /* Price for create order. */
  private BigDecimal price;

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

  /* The visible constant part of the iceberg order in lots set out at the order creation. */
  private Integer icebergFixed;

  /* The deviation amplitude (% of icebergFixed) of the random increment to the visible
  part of the iceberg order set out at the order creation. Derivatives only. */
  private Integer icebergVariance;
}
