package algo.trading.client.request;

/** Represents the user placing the order. */
public record User(
    /* Unique portfolio ID. Example: "D39004" */
    String portfolio) {}
