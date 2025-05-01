package algo.trading.starter.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/** Response with access token. */
@Data
@Builder
public class AccessTokenResponse {
  @JsonProperty("AccessToken")
  private String accessToken;
}
