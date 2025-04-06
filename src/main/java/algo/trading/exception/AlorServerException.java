package algo.trading.exception;

/**
 * Exception thrown for server-side errors in the Alor integration. This indicates a problem with
 * the Alor API or server, such as an internal server error (500).
 */
public class AlorServerException extends AlorIntegrationException {
  /**
   * Constructs a new AlorServerException with the specified message.
   *
   * @param message the detail message
   */
  public AlorServerException(String message) {
    super(message);
  }
}
