package algo.trading.starter.client.common;

/**
 * Enum representing the query parameters used in requests to the Alor API. Each enum constant
 * corresponds to a specific parameter in the API query string.
 */
public enum AlorQueryParam {
  TOKEN("token"),
  SYMBOL("symbol"),
  EXCHANGE("exchange"),
  TF("tf"),
  FROM("from"),
  TO("to"),
  COUNT_BACK("countBack"),
  UNTRADED("untraded"),
  SPLIT_ADJUST("splitAdjust"),
  FORMAT("format");

  private final String param;

  AlorQueryParam(String param) {
    this.param = param;
  }

  public String getParam() {
    return param;
  }
}
