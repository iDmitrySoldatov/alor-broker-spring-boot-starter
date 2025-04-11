package algo.trading.exception.handler;

import algo.trading.exception.AlorAuthException;
import algo.trading.exception.AlorClientException;
import algo.trading.exception.AlorDataValidationException;
import algo.trading.exception.AlorServerException;
import algo.trading.exception.AlorUnknownException;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * This class handles errors from the Alor API responses, categorizing errors into client-side (4xx)
 * and server-side (5xx) errors, and throwing custom exceptions based on the status code.
 */
public class AlorIntegrationErrorHandler implements ResponseErrorHandler {
  /**
   * Checks if the HTTP response contains an error.
   *
   * @param response the HTTP response to check for errors
   * @return true if the response contains a client-side or server-side error
   * @throws IOException in case of I/O issues when reading the response
   */
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return (response.getStatusCode().is4xxClientError()
        || response.getStatusCode().is5xxServerError());
  }

  /**
   * Handles the error response and throws appropriate exceptions based on the status code.
   *
   * <p>The method categorizes errors into 4xx client errors and 5xx server errors, and throws
   * specific custom exceptions for each case. In case of unknown errors, an unknown exception is
   * thrown.
   *
   * @param url the URI of the request that resulted in the error
   * @param method the HTTP method of the request
   * @param response the HTTP response containing the error
   * @throws IOException in case of I/O issues when reading the response
   * @throws AlorAuthException if the status code is UNAUTHORIZED (401, 403)
   * @throws AlorDataValidationException if the status code is BAD_REQUEST (400)
   * @throws AlorClientException if a general client error occurred (4xx)
   * @throws AlorServerException if a server error occurred (5xx)
   * @throws AlorUnknownException if an unknown error occurred
   */
  @Override
  public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
      throws IOException {
    HttpStatusCode statusCode = response.getStatusCode();
    String responseBody = new String(response.getBody().readAllBytes());
    int status = statusCode.value();
    String message = String.format("HTTP %d Error. Response: %s", status, responseBody);

    switch (status) {
      case 400 -> throw new AlorDataValidationException("Bad request (400). " + message);
      case 401 -> throw new AlorAuthException("Unauthorized (401). " + message);
      case 403 -> throw new AlorAuthException("Forbidden (403). " + message);
      default -> {
        if (statusCode.is4xxClientError()) {
          throw new AlorClientException("Client error. " + message);
        } else if (statusCode.is5xxServerError()) {
          throw new AlorServerException("Server error. " + message);
        } else {
          throw new AlorUnknownException("Unknown error. " + message);
        }
      }
    }
  }
}
