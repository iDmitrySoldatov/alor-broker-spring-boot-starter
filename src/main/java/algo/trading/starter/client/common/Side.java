package algo.trading.starter.client.common;

/** Order side: BUY or SELL. */
public enum Side {
  buy,
  sell;

  /**
   * Inverse side direction.
   *
   * @return inversed Side
   */
  public Side inverse() {
    return this == buy ? sell : buy;
  }
}
