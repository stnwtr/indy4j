package at.stnwtr.indy4j.object;

import java.util.Objects;
import net.dongliu.requests.RawResponse;
import org.json.JSONObject;

/**
 * The top level object of all indy object.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
public class IndyObject {

  /**
   * The indy http response.
   */
  protected RawResponse response;

  /**
   * Constructor which takes the response only.
   *
   * @param response The response.
   */
  public IndyObject(RawResponse response) {
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
    return response.readToJson(JSONObject.class);
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
    IndyObject that = (IndyObject) o;
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
