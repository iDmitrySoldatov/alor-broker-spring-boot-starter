package algo.trading.starter.client.response;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.common.AlorMarketStatus;
import algo.trading.starter.client.common.Side;
import algo.trading.starter.client.common.TimeInForce;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response payload for get market order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMarketOrderResponse {
  private String id;
  private String symbol;
  private String board;
  private String brokerSymbol;
  private String portfolio;
  private AlorExchange exchange;
  private String comment;
  private String type;
  private Side side;
  private AlorMarketStatus status;
  private Instant transTime;
  private Instant updateTime;
  private Instant endTime;
  private Integer qtyUnits;
  private Integer qtyBatch;
  private Integer qty;
  private Double filledQtyUnits;
  private Double filledQtyBatch;
  private Double filled;
  private BigDecimal price;
  private TimeInForce timeInForce;
  private Iceberg iceberg;
  private BigDecimal volume;

  /** Dto of iceberg. */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Iceberg {
    private Integer creationFixedQuantity;
    private Integer creationVarianceQuantity;
    private Integer visibleQuantity;
    private Integer visibleQuantityBatch;
    private Integer visibleFilledQuantity;
    private Integer visibleFilledQuantityBatch;
  }
}
