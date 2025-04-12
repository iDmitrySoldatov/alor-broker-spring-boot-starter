package algo.trading.client.request;

import algo.trading.client.common.ResponseFormat;

/** Represents a request to fetch market history data for a given financial instrument. */
public record MarketHistoryRequest(
    /* Represents the trading instrument (e.g., symbol and exchange). */
    Instrument instrument,

    /*
     * The time frame for the market data.
     * This parameter is required.
     * Possible values: [15, 60, 3600, D, W, M, Y] representing seconds, days, weeks, etc.
     * Example: "60" for a 1-minute time frame.
     */
    String tf,

    /*
     * The start of the time range for which market history data is requested.
     * This parameter is required.
     * Represented as Unix Time in seconds.
     *
     * Example: 1549000661
     */
    long from,

    /*
     * The end of the time range for which market history data is requested.
     * This parameter is required.
     * Represented as Unix Time in seconds.
     *
     * Example: 1550060661
     */
    long to,

    /*
     * Minimum number of candles to be returned by the server for the requested time range.
     * Optional. If not specified, the server will return all available data for the given range.
     *
     * Example: 10
     */
    Integer countBack,

    /*
     * Flag to include expired or untraded instruments in the search results.
     * Optional.
     *
     * Example: false
     */
    Boolean untraded,

    /*
     * Flag to adjust the historical market data with respect to stock splits, consolidations,
     * and other factors.
     * Optional, default value is true.
     *
     * Example: true
     */
    Boolean splitAdjust,

    /*
     * The format of the response data.
     * Optional. Possible values are:
     * - Simple: Original format (with deprecated parameters for backward compatibility).(DEFAULT)
     *
     * Example: "Simple"
     */
    ResponseFormat format) {}
