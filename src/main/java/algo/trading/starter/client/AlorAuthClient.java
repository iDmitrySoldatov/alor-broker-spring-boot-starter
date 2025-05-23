package algo.trading.starter.client;

import algo.trading.starter.client.request.TokenRequest;
import algo.trading.starter.client.response.AccessTokenResponse;
import algo.trading.starter.config.AlorIntegrationProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

/** Client responsible for obtaining an access token from Alor using a refresh token. */
@RequiredArgsConstructor
public class AlorAuthClient {
  private final AlorIntegrationProperty property;
  private final RestClient restClient;

  /**
   * Sends a request to the Alor authorization endpoint to retrieve an access token.
   *
   * @param refreshToken the refresh token to exchange
   * @return access token as a string
   */
  public AccessTokenResponse getAccessToken(String refreshToken) {
    return restClient
        .post()
        .uri(property.getAuthUrl())
        .body(TokenRequest.builder().token(refreshToken).build())
        .retrieve()
        .body(AccessTokenResponse.class);
  }
}
