package algo.trading.starter.client.request;

import lombok.Builder;
import lombok.Data;

/** Represents the user placing the order. */
@Data
@Builder
public class User {
  /* Unique portfolio ID. Example: "D39004" */
  String portfolio;
}
