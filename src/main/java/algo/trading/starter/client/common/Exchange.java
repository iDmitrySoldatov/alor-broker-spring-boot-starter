package algo.trading.starter.client.common;

/** Enum representing the possible exchanges. */
public enum Exchange {
  MOEX("MOEX"),
  SPBX("SPBX");

  private final String value;

  Exchange(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
