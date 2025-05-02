package algo.trading.starter.client.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single entry of market history data for a specific time period. This record contains
 * open, close, high, low prices and the trade volume for the given period.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntry {
  /*
   * The timestamp (in Unix time format) representing the start of the period (UTC).
   *
   * Example: 1620220020
   */
  private long time;

  /*
   * The closing price for the period.
   *
   * Example: 210.83
   */
  private BigDecimal close;

  /*
   * The opening price for the period.
   *
   * Example: 210.82
   */
  private BigDecimal open;

  /*
   * The highest price reached during the period.
   *
   * Example: 176.02
   */
  private BigDecimal high;

  /*
   * The lowest price reached during the period.
   *
   * Example: 170.33
   */
  private BigDecimal low;

  /*
   * The trading volume for the period.
   *
   * Example: 13544356235
   */
  private long volume;
}
