package algo.trading.starter.client.common;

/**
 * Enum representing base and specific API endpoint paths used in Alor client. Helps centralize and
 * manage endpoint definitions in a type-safe way.
 */
public enum AlorApiPath {
  EXCHANGE_ORDER_BASE("/commandapi/warptrans/TRADE/v2/client/orders/actions"),
  SECURITY_INFO_BASE("/md/v2"),
  GET_MARKET_HISTORY(SECURITY_INFO_BASE.path() + "/history"),
  REFRESH_TOKEN("/refresh"),
  CREATE_MARKET_ORDER(EXCHANGE_ORDER_BASE.path() + "/market"),
  CREATE_LIMIT_ORDER(EXCHANGE_ORDER_BASE.path() + "/limit"),
  CREATE_STOP_ORDER(EXCHANGE_ORDER_BASE.path() + "/stop");

  private final String path;

  AlorApiPath(String path) {
    this.path = path;
  }

  /** Returns the full path string of the API endpoint. */
  public String path() {
    return path;
  }
}
