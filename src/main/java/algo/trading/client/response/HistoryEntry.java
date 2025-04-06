package algo.trading.client.response;

import java.math.BigDecimal;

/**
 * Represents a single entry of market history data for a specific time period. This record contains
 * open, close, high, low prices and the trade volume for the given period.
 */
public record HistoryEntry(
    /*
     * The timestamp (in Unix time format) representing the start of the period (UTC).
     *
     * Example: 1620220020
     */
    long time,

    /*
     * The closing price for the period.
     *
     * Example: 210.83
     */
    BigDecimal close,

    /*
     * The opening price for the period.
     *
     * Example: 210.82
     */
    BigDecimal open,

    /*
     * The highest price reached during the period.
     *
     * Example: 176.02
     */
    BigDecimal high,

    /*
     * The lowest price reached during the period.
     *
     * Example: 170.33
     */
    BigDecimal low,

    /*
     * The trading volume for the period.
     *
     * Example: 13544356235
     */
    long volume) {}
