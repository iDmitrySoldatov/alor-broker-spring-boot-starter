package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.GET_ALL_POSITION;
import static algo.trading.starter.client.common.AlorApiPath.GET_TRADES;
import static algo.trading.starter.client.common.AlorApiPath.GET_TRADES_HISTORY;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorExchange;
import algo.trading.starter.client.response.AllPositionResponse;
import algo.trading.starter.client.response.TradeResponse;
import algo.trading.starter.service.RestClientProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

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
  public List<AllPositionResponse> getAllPosition(AlorExchange exchange, String portfolio) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(GET_ALL_POSITION.path(), exchange.name(), portfolio))
        .retrieve()
        .body(new ParameterizedTypeReference<List<AllPositionResponse>>() {});
  }

  /**
   * Get all trades by instrument for current session.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @param symbol instrument symbol
   * @return list of trades
   */
  public List<TradeResponse> getTrades(AlorExchange exchange, String portfolio, String symbol) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(GET_TRADES.path(), exchange.name(), portfolio, symbol))
        .retrieve()
        .body(new ParameterizedTypeReference<List<TradeResponse>>() {});
  }

  /**
   * Get all trades by instrument for previous session.
   *
   * @param exchange the exchange
   * @param portfolio the portfolio
   * @param symbol instrument symbol
   * @return list of trades
   */
  public List<TradeResponse> getTradesHistory(
      AlorExchange exchange, String portfolio, String symbol) {
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(GET_TRADES_HISTORY.path(), exchange.name(), portfolio, symbol))
        .retrieve()
        .body(new ParameterizedTypeReference<List<TradeResponse>>() {});
  }
}
