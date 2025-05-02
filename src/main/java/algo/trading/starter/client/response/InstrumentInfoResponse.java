package algo.trading.starter.client.response;

import algo.trading.starter.client.common.Currency;
import algo.trading.starter.client.common.Exchange;
import algo.trading.starter.client.common.InstrumentType;
import algo.trading.starter.client.common.MarketCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Response with parameters of instrument. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentInfoResponse {
  /** Symbol of trading instrument. */
  private String symbol;

  /** Shortname of trading instrument. */
  private String shortname;

  /** Description of trading instrument. */
  private String description;

  /** Stock exchange. */
  private Exchange exchange;

  /** Market code. */
  private MarketCode market;

  /** Instrument type. */
  private InstrumentType type;

  /** Size of one lot. */
  @JsonProperty("lotsize")
  private BigDecimal lotSize;

  /**
   * For the stock market - the nominal value of a single financial instrument. For the futures
   * market - the size of one lot. For the forex market - the lot size of currency for which the
   * price is quoted
   */
  @JsonProperty("facevalue")
  private BigDecimal faceValue;

  /** Minimal step of price. */
  @JsonProperty("minstep")
  private BigDecimal minStep;

  /** Currency of instrument. */
  private Currency currency;
}
