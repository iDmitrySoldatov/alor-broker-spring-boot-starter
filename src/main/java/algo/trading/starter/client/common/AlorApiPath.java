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
  CREATE_STOP_ORDER(EXCHANGE_ORDER_BASE.path() + "/stop"),
  INSTRUMENT_INFO(SECURITY_INFO_BASE.path() + "/Securities/{0}/{1}"),
  INSTRUMENT_QUOTES(SECURITY_INFO_BASE.path() + "/Securities/{0}/quotes"),
  ESTIMATE_MARKET_ORDER("/commandapi/warptrans/TRADE/v2/client/orders/estimate"),
  ORDER_GROUP_BASE("/commandapi/api/orderGroups"),
  ORDER_GROUP_BY_ID(ORDER_GROUP_BASE.path() + "/{0}"),
  GET_MARKET_ORDER("/md/v2/Clients/{0}/{1}/orders/{2}"),
  GET_CONDITIONAL_ORDER("/md/v2/Clients/{0}/{1}/stoporders/{2}");

  private final String path;

  AlorApiPath(String path) {
    this.path = path;
  }

  /** Returns the full path string of the API endpoint. */
  public String path() {
    return path;
  }
}
