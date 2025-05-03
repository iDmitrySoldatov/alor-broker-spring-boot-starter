package algo.trading.starter.service;

import algo.trading.starter.client.AlorAuthClient;
import algo.trading.starter.config.AlorIntegrationProperty;
import java.util.concurrent.atomic.AtomicReference;

/** Service for managing the access token required for interacting with the Alor Broker API. */
public class AlorTokenStorageService {
  private final AtomicReference<String> accessToken = new AtomicReference<>();
  private final AlorAuthClient alorAuthClient;

  /**
   * Constructs a new instance of AlorTokenStorageService.
   *
   * @param alorAuthClient the client used to interact with the Alor Broker authentication system
   */
  public AlorTokenStorageService(AlorAuthClient alorAuthClient) {
    this.alorAuthClient = alorAuthClient;
  }

  /**
   * Retrieves the current access token.
   *
   * @return The current access token as a string.
   */
  public String getAccessToken() {
    return accessToken.get();
  }

  /**
   * Refreshes the current access token by using the refresh token. This method makes a call to the
   * Alor client to fetch a new access token using the stored refresh token.
   */
  public void refreshAccessToken(String refreshToken) {
    String token = alorAuthClient.getAccessToken(refreshToken).getAccessToken();
    accessToken.set(token);
  }
}
