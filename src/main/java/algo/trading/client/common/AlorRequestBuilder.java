package algo.trading.client.common;

import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * A builder for constructing URIs with specified base URL, path, and query parameters. This class
 * provides a fluent API for adding query parameters and building the final URI.
 */
public class AlorRequestBuilder {
  private final UriComponentsBuilder builder;

  private AlorRequestBuilder(String baseUrl, String path) {
    this.builder = UriComponentsBuilder.newInstance().uri(URI.create(baseUrl + path));
  }

  /**
   * Creates a new instance of {@link AlorRequestBuilder} with the specified base URL and path.
   *
   * @param baseUrl the base URL
   * @param path the path to append to the base URL
   * @return a new instance of {@link AlorRequestBuilder}
   */
  public static AlorRequestBuilder from(String baseUrl, String path) {
    return new AlorRequestBuilder(baseUrl, path);
  }

  /**
   * Adds a query parameter to the URI being built.
   *
   * @param param the query parameter to add
   * @param value the value of the query parameter
   * @return the current instance of {@link AlorRequestBuilder} for method chaining
   */
  public AlorRequestBuilder with(AlorQueryParam param, Object value) {
    if (value != null) {
      builder.queryParam(param.getParam(), value);
    }
    return this;
  }

  /**
   * Builds and returns the final URI based on the provided base URL, path, and query parameters.
   *
   * @return the constructed URI
   */
  public URI build() {
    return builder.build().toUri();
  }
}
