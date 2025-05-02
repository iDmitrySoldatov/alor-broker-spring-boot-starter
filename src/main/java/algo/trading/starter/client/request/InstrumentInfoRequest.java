package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Exchange;
import algo.trading.starter.client.common.InstrumentGroup;
import algo.trading.starter.client.common.ResponseFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Request to fetch instrument info. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentInfoRequest {
  /** Stock exchange. */
  private Exchange exchange;

  /** Symbol of trading instrument. */
  private String symbol;

  /** Trading mode code. */
  private InstrumentGroup instrumentGroup;

  /** Format type for response. */
  private ResponseFormat format;

  /** Json response format flag. */
  private Boolean jsonResponse;
}
