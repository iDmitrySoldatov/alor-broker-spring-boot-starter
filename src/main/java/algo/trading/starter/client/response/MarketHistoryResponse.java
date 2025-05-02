package algo.trading.starter.client.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response containing market history data for a given financial instrument. This class
 * includes historical data for each period (open, close, high, low prices) and volume, as well as
 * pagination information to fetch more historical data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketHistoryResponse {
  /*
   * List of market history entries, each representing a time period with open, close, high,
   * low prices, and volume.
   */
  private List<HistoryEntry> history;

  /*
   * Timestamp of the next available period in Unix time format. This value is used for pagination
   * to retrieve the next set of historical data.
   * If there is no next period, this field might be null.
   *
   * Example: 1532944860
   */
  private long next;

  /*
   * Timestamp of the previous available period in Unix time format. This value is used
   * for pagination to retrieve the previous set of historical data.
   * If there is no previous period, this field might be null.
   *
   * Example: 1532944860
   */
  private long prev;
}
