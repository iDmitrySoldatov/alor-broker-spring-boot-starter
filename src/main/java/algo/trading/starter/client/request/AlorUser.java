package algo.trading.starter.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Represents the user placing the order. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlorUser {
  /* Unique portfolio ID. Example: "D39004" */
  String portfolio;
}
