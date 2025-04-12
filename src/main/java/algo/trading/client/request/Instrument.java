package algo.trading.client.request;

import algo.trading.client.common.Exchange;

/** Represents the trading instrument (e.g., symbol and exchange). */
public record Instrument(
    /* Ticker symbol of the instrument. Example: "SBER" */
    String symbol,

    /* Exchange code: MOEX or SPBX. */
    Exchange exchange,

    /* Trading board code. Example: "TQBR" */
    String instrumentGroup) {}
