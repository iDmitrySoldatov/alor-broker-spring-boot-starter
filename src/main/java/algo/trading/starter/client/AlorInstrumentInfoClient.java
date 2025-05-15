package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.INSTRUMENT_INFO;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorQueryParam;
import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.client.common.ResponseFormat;
import algo.trading.starter.client.request.InstrumentInfoRequest;
import algo.trading.starter.client.response.InstrumentInfoResponse;
import algo.trading.starter.config.AlorIntegrationProperty;
import algo.trading.starter.service.RestClientProvider;
import java.net.URI;
import lombok.RequiredArgsConstructor;

/** Client for receive information about instruments. */
@RequiredArgsConstructor
public class AlorInstrumentInfoClient {
  private final RestClientProvider restClientProvider;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Get info about instrument.
   *
   * @param request parameters for request
   * @return response with information of trading instrument
   */
  public InstrumentInfoResponse getInstrumentInfo(InstrumentInfoRequest request) {
    String baseUrl = alorIntegrationProperty.getApiUrl();
    String path = format(INSTRUMENT_INFO.path(), request.getExchange(), request.getSymbol());

    URI uri =
        AlorRequestBuilder.from(baseUrl, path)
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
}
