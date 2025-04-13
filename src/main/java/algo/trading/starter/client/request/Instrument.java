package algo.trading.starter.client.request;

import algo.trading.starter.client.common.Exchange;
import lombok.Builder;
import lombok.Data;

/** Represents the trading instrument (e.g.; symbol and exchange). */
@Data
@Builder
public class Instrument {
  /* Ticker symbol of the instrument. Example: "SBER" */
  private String symbol;

  /* Exchange code: MOEX or SPBX. */
  private Exchange exchange;

  /* Trading board code. Example: "TQBR" */
  private String instrumentGroup;
}
