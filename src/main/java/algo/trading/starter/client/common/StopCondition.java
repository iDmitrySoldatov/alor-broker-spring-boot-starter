package algo.trading.starter.client.common;

/** Triggering condition for stop and stop limit orders. */
public enum StopCondition {
  /* Current price is higher that trigger price. */
  More,
  /* Current price is lower than trigger price. */
  Less,
  /* Current price is higher or equal to the trigger price. */
  MoreOrEqual,
  /* Current price is lower or equal to the trigger price. */
  LessOrEqual
}
