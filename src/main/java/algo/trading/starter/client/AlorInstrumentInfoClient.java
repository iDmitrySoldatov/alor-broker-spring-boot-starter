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

/** Client for receive information about instruments. */
public class AlorInstrumentInfoClient {
  private final RestClientProvider restClientProvider;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Initializes the instrument info client with required dependencies.
   *
   * @param restClientProvider provider of authenticated HTTP client for Alor API
   * @param alorIntegrationProperty configuration properties (e.g. API base URL, fallback values)
   */
  public AlorInstrumentInfoClient(
      RestClientProvider restClientProvider, AlorIntegrationProperty alorIntegrationProperty) {
    this.restClientProvider = restClientProvider;
    this.alorIntegrationProperty = alorIntegrationProperty;
  }

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
