package algo.trading.starter.client.request;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/** Request for access token. */
@Data
@Builder
public class TokenRequest {
  private String token;
  private List<String> allowedPortfolios;
}
