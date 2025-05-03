package algo.trading.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Configuration properties for Alor Broker integration. */
@ConfigurationProperties(prefix = "alor.integration")
public class AlorIntegrationProperty {

  private String authUrl; // URL for authentication (token retrieval)
  private String apiUrl; // URL for API communication with Alor Broker

  public String getAuthUrl() {
    return authUrl;
  }

  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

}
