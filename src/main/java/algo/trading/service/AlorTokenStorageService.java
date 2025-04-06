package algo.trading.service;

import algo.trading.client.AlorAuthClient;
import algo.trading.config.AlorIntegrationProperty;
import jakarta.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicReference;

/** Service for managing the access token required for interacting with the Alor Broker API. */
public class AlorTokenStorageService {
  private AtomicReference<String> accessToken = new AtomicReference<>();
  private AlorAuthClient alorAuthClient;
  private AlorIntegrationProperty property;

  /**
   * Constructs a new instance of AlorTokenStorageService.
   *
   * @param alorAuthClient the client used to interact with the Alor Broker authentication system
   * @param property the integration properties that contain configuration like the refresh token
   */
  public AlorTokenStorageService(AlorAuthClient alorAuthClient, AlorIntegrationProperty property) {
    this.alorAuthClient = alorAuthClient;
    this.property = property;
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
  @PostConstruct
  public void refreshAccessToken() {
    accessToken.set(alorAuthClient.getAccessToken(property.getRefreshToken()));
  }
}
