package algo.trading.starter.exception;

/**
 * Exception thrown when there is invalid data in the request sent to the Alor API. This can occur
 * when the input parameters are incorrect or missing.
 */
public class AlorDataValidationException extends AlorIntegrationException {
  /**
   * Constructs a new AlorDataValidationException with the specified message.
   *
   * @param message the detail message
   */
  public AlorDataValidationException(String message) {
    super(message);
  }
}
