package algo.trading.client.request;

import algo.trading.client.common.Side;
import algo.trading.client.common.StopCondition;
import java.math.BigDecimal;

/** Request payload for creating a stop order. */
public record CreateStopOrderRequest(
    /* Order side: buy or sell. */
    Side side,

    /* Triggering condition for stop and stop limit orders. */
    StopCondition condition,

    /* Conditional price (trigger price). */
    BigDecimal triggerPrice,

    /* Stop time for stop order. Validity time (UTC) in Unix Time Seconds format. */
    Long stopEndUnixTime,

    /* Quantity in lots. Example: 1 */
    int quantity,

    /* Instrument information including symbol, exchange, and group. */
    Instrument instrument,

    /* User info including portfolio ID. */
    User user,

    /* Allow margin trading. Must be true for uncovered positions. */
    boolean allowMargin,

    /* Protective time. Continuous period of time in seconds, during which
     * the market price of the instrument must satisfy the price and triggering
     * condition specified in the order (triggerPrice and condition).
     * It is intended to protect conditional orders from triggering
     * on short-term price fluctuations.
     * Possible values: >= 1 and <= 300
     */
    Integer protectingSeconds,

    /* The flag specifies whether to create an active or inactive order.
     * An inactive order is displayed in the system, but is not counted in trading until
     * it becomes active. This flag is required when creating a group of orders
     * with the 'TriggerBracketOrders' type
     */
    Boolean activate) {}
