package algo.trading.client.response;

import java.util.List;

/**
 * Represents a response containing market history data for a given financial instrument. This class
 * includes historical data for each period (open, close, high, low prices) and volume, as well as
 * pagination information to fetch more historical data.
 */
public record MarketHistoryResponse(
    /*
     * List of market history entries, each representing a time period with open, close, high,
     * low prices, and volume.
     */
    List<HistoryEntry> history,

    /*
     * Timestamp of the next available period in Unix time format. This value is used for pagination
     * to retrieve the next set of historical data.
     * If there is no next period, this field might be null.
     *
     * Example: 1532944860
     */
    long next,

    /*
     * Timestamp of the previous available period in Unix time format. This value is used
     * for pagination to retrieve the previous set of historical data.
     * If there is no previous period, this field might be null.
     *
     * Example: 1532944860
     */
    long prev) {}
