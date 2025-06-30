package algo.trading.starter.client.response;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.common.Side;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response payload for get trade. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {
  private String id;

  @JsonProperty("orderno")
  private String orderId;

  private String comment;
  private String symbol;
  private String board;
  private String brokerSymbol;
  private AlorExchange exchange;
  private Instant date;
  private Integer qtyUnits;
  private Integer qtyBatch;
  private Integer qty;
  private BigDecimal price;
  private BigDecimal accruedInt;
  private Side side;
  private Boolean existing;
  private BigDecimal commission;
  private BigDecimal volume;
}
