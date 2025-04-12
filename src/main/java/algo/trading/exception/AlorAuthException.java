package algo.trading.exception;

/**
 * Exception thrown when authentication fails in Alor integration. This occurs, for example, if the
 * provided access token is invalid or expired.
 */
public class AlorAuthException extends AlorIntegrationException {
  /**
   * Constructs a new AlorAuthException with the specified message.
   *
   * @param message the detail message
   */
  public AlorAuthException(String message) {
    super(message);
  }
}
