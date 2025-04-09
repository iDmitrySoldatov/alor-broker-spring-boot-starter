package algo.trading.client.request;

import algo.trading.client.common.Side;
import algo.trading.client.common.TimeInForce;

/** Request payload for creating a market order. */
public record CreateMarketOrderRequest(
    /* Order side: buy or sell. */
    Side side,

    /* Quantity in lots. Example: 1 */
    int quantity,

    /* Instrument information including symbol, exchange, and group. */
    Instrument instrument,

    /* Custom comment for the order. */
    String comment,

    /* User info including portfolio ID. */
    User user,

    /* Order validity type. Default: oneday. */
    TimeInForce timeInForce,

    /* Allow margin trading. Must be true for uncovered positions. */
    boolean allowMargin) {}
