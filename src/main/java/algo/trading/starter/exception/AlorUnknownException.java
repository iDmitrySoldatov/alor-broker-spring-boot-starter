package algo.trading.starter.exception;

/**
 * Exception thrown for unknown errors in the Alor integration. This can be used for unexpected
 * issues or errors that do not fit into other categories.
 */
public class AlorUnknownException extends AlorIntegrationException {
  /**
   * Constructs a new AlorUnknownException with the specified message.
   *
   * @param message the detail message
   */
  public AlorUnknownException(String message) {
    super(message);
  }
}
