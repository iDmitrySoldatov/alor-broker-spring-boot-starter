package algo.trading.starter.client;

import algo.trading.starter.client.common.AlorApiPath;
import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.response.AllPositionResponse;
import algo.trading.starter.service.RestClientProvider;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

/** Client for receive information about client. */
@RequiredArgsConstructor
public class AlorClientInfoClient {
  private final RestClientProvider restClientProvider;

  /**
   * Get all clients position.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @return response with all open position of client
   */
  public AllPositionResponse getAllPosition(AlorExchange exchange, String portfolio) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(MessageFormat.format(AlorApiPath.GET_ALL_POSITION.path(), exchange.name(), portfolio))
        .retrieve()
        .body(AllPositionResponse.class);
  }
}
