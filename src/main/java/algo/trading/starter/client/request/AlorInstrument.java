package algo.trading.starter.client.request;

import algo.trading.starter.client.common.AlorExchange;
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
public class AlorInstrument {
  /* Ticker symbol of the instrument. Example: "SBER" */
  private String symbol;

  /* AlorExchange code: MOEX or SPBX. */
  private AlorExchange exchange;

  /* Trading board code. Example: "TQBR" */
  private InstrumentGroup instrumentGroup;
}
