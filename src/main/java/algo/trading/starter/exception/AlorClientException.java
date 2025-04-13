package algo.trading.starter.exception;

/**
 * Exception representing a client-side error in Alor integration. This is thrown when there is a
 * problem with the client's request, e.g., invalid data or bad request.
 */
public class AlorClientException extends AlorIntegrationException {
  /**
   * Constructs a new AlorClientException with the specified message.
   *
   * @param message the detail message
   */
  public AlorClientException(String message) {
    super(message);
  }
}
