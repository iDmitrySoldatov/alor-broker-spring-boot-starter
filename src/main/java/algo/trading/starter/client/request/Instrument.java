package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Exchange;
import algo.trading.starter.client.common.InstrumentGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Represents the trading instrument (e.g.; symbol and exchange). */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {
  /* Ticker symbol of the instrument. Example: "SBER" */
  private String symbol;

  /* Exchange code: MOEX or SPBX. */
  private Exchange exchange;

  /* Trading board code. Example: "TQBR" */
  private InstrumentGroup instrumentGroup;
}
