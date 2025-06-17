package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.GET_MARKET_HISTORY;
import static algo.trading.starter.client.common.AlorQueryParam.COUNT_BACK;
import static algo.trading.starter.client.common.AlorQueryParam.EXCHANGE;
import static algo.trading.starter.client.common.AlorQueryParam.FORMAT;
import static algo.trading.starter.client.common.AlorQueryParam.FROM;
import static algo.trading.starter.client.common.AlorQueryParam.SPLIT_ADJUST;
import static algo.trading.starter.client.common.AlorQueryParam.SYMBOL;
import static algo.trading.starter.client.common.AlorQueryParam.TF;
import static algo.trading.starter.client.common.AlorQueryParam.TO;
import static algo.trading.starter.client.common.AlorQueryParam.UNTRADED;

import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.client.request.MarketHistoryRequest;
import algo.trading.starter.client.response.MarketHistoryResponse;
import algo.trading.starter.service.RestClientProvider;
import java.net.URI;
import lombok.RequiredArgsConstructor;

/**
 * Authenticated API client for interacting with Alor API to fetch various data for financial
 * instruments, including market history, trades, quotes, and other instrument-related information.
 */
@RequiredArgsConstructor
public class AlorHistoryInfoClient {
  private final RestClientProvider restClientProvider;

  /**
   * Retrieves market history data based on the provided request.
   *
   * @param request the request containing the parameters for market history query
   * @return the response containing market history data
   */
  public MarketHistoryResponse getMarketHistory(MarketHistoryRequest request) {
    URI uri =
        AlorRequestBuilder.from(GET_MARKET_HISTORY.path())
            .with(SYMBOL, request.getInstrument().getSymbol())
            .with(EXCHANGE, request.getInstrument().getExchange())
            .with(TF, request.getTf())
            .with(FROM, request.getFrom())
            .with(TO, request.getTo())
            .with(COUNT_BACK, request.getCountBack())
            .with(UNTRADED, request.getUntraded())
            .with(SPLIT_ADJUST, request.getSplitAdjust())
            .with(FORMAT, request.getFormat())
            .build();

    return restClientProvider
        .getRestClient()
        .get()
        .uri(uri)
        .retrieve()
        .body(MarketHistoryResponse.class);
  }
}
