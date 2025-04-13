package algo.trading.starter.client.common;

/**
 * Order validity type: - oneday: Valid until end of trading day. - immediateorcancel: Partial fill,
 * cancel remaining. - fillorkill: Fill completely or cancel. - goodtillcancelled: Keep until
 * explicitly cancelled.
 */
public enum TimeInForce {
  oneday,
  immediateorcancel,
  fillorkill,
  goodtillcancelled
}
