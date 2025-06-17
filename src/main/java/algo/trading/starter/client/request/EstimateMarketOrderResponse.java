package algo.trading.starter.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class EstimateMarketOrderResponse {
  private String portfolio;
  private String ticker;
  private String exchange;

  @JsonProperty("")
  private Double quantityToSell;

  private Double quantityToBuy;
  private Double notMarginQuantityToSell;
  private Double notMarginQuantityToBuy;
  private BigDecimal orderEvaluation;
  private BigDecimal commission;
  private BigDecimal buyPrice;
  private Boolean isUnitedPortfolio; // Default: false
}
