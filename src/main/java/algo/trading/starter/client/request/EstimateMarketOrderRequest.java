package algo.trading.starter.client.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request payload for estimate a market order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimateMarketOrderRequest {
  private String portfolio; // Example: "D39004"

  private String ticker; // Example: "SBER"

  private String exchange; // Example: "MOEX"

  private BigDecimal price; // Example: 142.52

  private Integer lotQuantity; // Example: 1

  private BigDecimal budget; // Example: 100000

  private String board; // Example: "TQBR"

  private boolean includeLimitOrders = false; // Default: false
}
