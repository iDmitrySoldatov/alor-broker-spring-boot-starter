package algo.trading.client.response;

/**
 * Represents the response of a successful market order creation. Contains a confirmation message
 * and the unique order ID.
 */
public record CreateOrderResponse(
    /*
     * Text message of the response.
     * Example: "success"
     */
    String message,

    /*
     * Unique order ID assigned by the system.
     * Example: "18995978560"
     */
    String orderNumber) {}
