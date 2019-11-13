package at.stnwtr.indy4j.net;

import java.util.Objects;
import net.dongliu.requests.RawResponse;
import org.json.JSONObject;

/**
 * The top level object of all indy object.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
public class IndyResponse {

  /**
   * The indy http response.
   */
  protected RawResponse response;

  /**
   * Constructor which takes the response only.
   *
   * @param response The response.
   */
  public IndyResponse(RawResponse response) {
    this.response = response;
  }

  /**
   * Get the status code of the request.
   *
   * @return The status code.
   */
  public int getStatusCode() {
    return response.statusCode();
  }

  /**
   * Get the whole response as string.
   *
   * @return The response as string.
   */
  public String asString() {
    return response.readToText();
  }

  /**
   * Get the response as json.
   *
   * @return The response as json.
   */
  public JSONObject asJson() {
    return new JSONObject(asString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndyResponse that = (IndyResponse) o;
    return Objects.equals(response, that.response);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(response);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "IndyObject{" +
        "response=" + response +
        '}';
  }
}
