package algo.trading.starter.client.common;

/** Enum representing the possible exchanges. */
public enum AlorExchange {
  MOEX("MOEX"),
  SPBX("SPBX");

  private final String value;

  AlorExchange(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
