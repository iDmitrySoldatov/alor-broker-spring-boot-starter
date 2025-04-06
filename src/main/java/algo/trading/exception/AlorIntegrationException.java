package algo.trading.exception;

/**
 * Base exception class for all errors related to Alor integration. This is the parent class for all
 * specific exceptions thrown during integration.
 */
public class AlorIntegrationException extends RuntimeException {
  /**
   * Constructs a new AlorIntegrationException with the specified message.
   *
   * @param message the detail message
   */
  public AlorIntegrationException(String message) {
    super(message);
  }
}
