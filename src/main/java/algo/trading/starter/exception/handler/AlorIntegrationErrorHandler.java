package algo.trading.starter.exception.handler;

import algo.trading.starter.exception.AlorAuthException;
import algo.trading.starter.exception.AlorClientException;
import algo.trading.starter.exception.AlorDataValidationException;
import algo.trading.starter.exception.AlorServerException;
import algo.trading.starter.exception.AlorUnknownException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  private static final ObjectMapper MAPPER = new ObjectMapper();

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
    String responseMessage;
    try {
      JsonNode jsonNode = MAPPER.readTree(responseBody);
      responseMessage = jsonNode.has("message") ? jsonNode.get("message").asText() : responseBody;
    } catch (Exception e) {
      responseMessage = responseBody;
    }
    String message = String.format("HTTP %d Error. Message: %s", status, responseMessage);
    switch (status) {
      case 400 -> throw new AlorDataValidationException(message);
      case 401, 403 -> throw new AlorAuthException(message);
      default -> {
        if (statusCode.is4xxClientError()) {
          throw new AlorClientException(message);
        } else if (statusCode.is5xxServerError()) {
          throw new AlorServerException(message);
        } else {
          throw new AlorUnknownException(message);
        }
      }
    }
  }
}
