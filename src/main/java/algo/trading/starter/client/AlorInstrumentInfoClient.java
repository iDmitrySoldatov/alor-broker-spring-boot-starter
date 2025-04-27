package algo.trading.starter.client;

import static algo.trading.starter.client.common.AlorApiPath.INSTRUMENT_INFO;
import static java.text.MessageFormat.format;

import algo.trading.starter.client.common.AlorQueryParam;
import algo.trading.starter.client.common.AlorRequestBuilder;
import algo.trading.starter.client.common.ResponseFormat;
import algo.trading.starter.client.request.InstrumentInfoRequest;
import algo.trading.starter.client.response.InstrumentInfoResponse;
import algo.trading.starter.config.AlorIntegrationProperty;
import java.net.URI;
import org.springframework.web.client.RestClient;

/** Client for receive information about instruments. */
public class AlorInstrumentInfoClient {
  private final RestClient alorAuthRestClient;
  private final AlorIntegrationProperty alorIntegrationProperty;

  /**
   * Initializes the exchange orders client with required dependencies.
   *
   * @param alorAuthRestClient authenticated HTTP client for Alor API
   * @param alorIntegrationProperty configuration properties (e.g. API base URL, fallback values)
   */
  public AlorInstrumentInfoClient(
      RestClient alorAuthRestClient, AlorIntegrationProperty alorIntegrationProperty) {
    this.alorAuthRestClient = alorAuthRestClient;
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
    return alorAuthRestClient.get().uri(uri).retrieve().body(InstrumentInfoResponse.class);
  }
}
