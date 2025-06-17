package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.INSTRUMENT_INFO;
import static algo.trading.starter.client.common.AlorApiPath.INSTRUMENT_QUOTES;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorQueryParam;
import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.client.common.ResponseFormat;
import algo.trading.starter.client.request.AlorInstrument;
import algo.trading.starter.client.request.InstrumentInfoRequest;
import algo.trading.starter.client.response.InstrumentInfoResponse;
import algo.trading.starter.client.response.InstrumentQuotesResponse;
import algo.trading.starter.service.RestClientProvider;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

/** Client for receive information about instruments. */
@RequiredArgsConstructor
public class AlorInstrumentInfoClient {
  private final RestClientProvider restClientProvider;

  /**
   * Get info about instrument.
   *
   * @param request parameters for request
   * @return response with information of trading instrument
   */
  public InstrumentInfoResponse getInstrumentInfo(InstrumentInfoRequest request) {
    String path = format(INSTRUMENT_INFO.path(), request.getExchange(), request.getSymbol());

    URI uri =
        AlorRequestBuilder.from(path)
            .with(AlorQueryParam.INSTRUMENT_GROUP, request.getInstrumentGroup())
            .with(AlorQueryParam.FORMAT, ResponseFormat.SIMPLE)
            .with(AlorQueryParam.JSON_RESPONSE, true)
            .build();
    return restClientProvider
        .getRestClient()
        .get()
        .uri(uri)
        .retrieve()
        .body(InstrumentInfoResponse.class);
  }

  /**
   * Get quotes of instrument.
   *
   * @param instruments list og instruments
   * @return list of quotes
   */
  public List<InstrumentQuotesResponse> getInstrumentQuotes(List<AlorInstrument> instruments) {
    String symbols =
        instruments.stream()
            .map(i -> String.format("%s:%s", i.getExchange().name(), i.getSymbol()))
            .collect(Collectors.joining(","));
    return restClientProvider
        .getRestClient()
        .get()
        .uri(format(INSTRUMENT_QUOTES.path(), symbols))
        .retrieve()
        .body(new ParameterizedTypeReference<List<InstrumentQuotesResponse>>() {});
  }
}
