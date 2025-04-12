package algo.trading.client.common;

/** Enum representing the possible formats for the response data. */
public enum ResponseFormat {
  SIMPLE("Simple");

  private final String value;

  ResponseFormat(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
