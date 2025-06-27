package algo.trading.starter.client.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response with all position. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllPositionResponse {
  private BigDecimal currentVolume;
  private BigDecimal volume;
  private String symbol;
  private Boolean isCurrency;
  private BigDecimal qty;
  private BigDecimal qtyUnits;
  private BigDecimal openUnits;
  private BigDecimal lotSize;
}
