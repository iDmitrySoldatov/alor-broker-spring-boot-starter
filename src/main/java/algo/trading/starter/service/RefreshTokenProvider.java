package algo.trading.starter.service;

/** Provides functionality to obtain a refresh token. */
public interface RefreshTokenProvider {
  /**
   * Retrieves the current refresh token.
   *
   * @return refresh token
   */
  String getRefreshToken();
}
