package algo.trading.starter.exception;

/** Exception thrown when there is no object found in the request sent to the Alor API. */
public class AlorNotFoundException extends AlorIntegrationException {

  /**
   * Constructs a new AlorNotFoundException with the specified message.
   *
   * @param message the detail message
   */
  public AlorNotFoundException(String message) {
    super(message);
  }
}
