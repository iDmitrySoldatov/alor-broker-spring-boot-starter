package algo.trading.starter.client.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response payload for instrument quotes. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InstrumentQuotesResponse {
  private String symbol;
  private String exchange;
  private String description;
  private BigDecimal prevClosePrice;
  private BigDecimal lastPrice;
  private Long lastPriceTimestamp; // Unix timestamp (seconds)
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private Integer accruedInt;
  private Long volume;
  private Long openInterest;
  private BigDecimal ask;
  private BigDecimal bid;
  private Integer askVolume;
  private Integer bidVolume;
  private Long orderBookTimestamp; // Unix timestamp (milliseconds)
  private BigDecimal openPrice;
  private BigDecimal yield;
  private Integer lotSize;
  private Integer lotValue;
  private Integer faceValue;
  private String type;
  private Integer totalBidVolume;
  private Integer totalAskVolume;
  private BigDecimal change;
  private BigDecimal changePercent;
}
