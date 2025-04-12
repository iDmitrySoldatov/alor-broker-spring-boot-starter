package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorQueryParam.TOKEN;

import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.config.AlorIntegrationProperty;
import java.net.URI;
import org.springframework.web.client.RestClient;

/** Client responsible for obtaining an access token from Alor using a refresh token. */
public class AlorAuthClient {
  private final AlorIntegrationProperty property;
  private final RestClient restClient;

  /**
   * Constructs a new instance of AlorAuthClient.
   *
   * @param property the integration properties containing configuration, including the auth URL
   * @param restClient the RestClient used to send HTTP requests to Alor
   */
  public AlorAuthClient(AlorIntegrationProperty property, RestClient restClient) {
    this.property = property;
    this.restClient = restClient;
  }

  /**
   * Sends a request to the Alor authorization endpoint to retrieve an access token.
   *
   * @param refreshToken the refresh token to exchange
   * @return access token as a string
   */
  public String getAccessToken(String refreshToken) {
    URI uri = AlorRequestBuilder.from(property.getAuthUrl(), "").with(TOKEN, refreshToken).build();
    return restClient.post().uri(uri).retrieve().body(String.class);
  }
}
