package algo.trading.client.request;

import algo.trading.client.common.Side;
import algo.trading.client.common.TimeInForce;
import java.math.BigDecimal;

/** Request payload for creating a market order. */
public record CreateLimitOrderRequest(
    /* Order side: buy or sell. */
    Side side,

    /* Quantity in lots. Example: 1 */
    int quantity,

    /* Price for create order. */
    BigDecimal price,

    /* Instrument information including symbol, exchange, and group. */
    Instrument instrument,

    /* Custom comment for the order. */
    String comment,

    /* User info including portfolio ID. */
    User user,

    /* Order validity type. Default: oneday. */
    TimeInForce timeInForce,

    /* Allow margin trading. Must be true for uncovered positions. */
    boolean allowMargin,

    /* The visible constant part of the iceberg order in lots set out at the order creation. */
    Integer icebergFixed,

    /* The deviation amplitude (% of icebergFixed) of the random increment to the visible
    part of the iceberg order set out at the order creation. Derivatives only. */
    Integer icebergVariance) {}
