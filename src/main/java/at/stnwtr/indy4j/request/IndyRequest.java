package at.stnwtr.indy4j.request;

import at.stnwtr.indy4j.object.IndyObject;
import at.stnwtr.indy4j.route.Route;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Session;
import org.json.JSONObject;

/**
 * The more accurate indy http request which stores basic data.
 *
 * @author stnwtr
 * @since 04.10.2019
 *
 * @param <T> The type of {@link IndyObject} to return.
 */
public class IndyRequest<T extends IndyObject> {

  /**
   * The route to request.
   */
  private final Route<T> route;

  /**
   * The indy http request body.
   */
  private final RequestBody body;

  /**
   * Constructor which takes only the {@link Route}.
   *
   * @param route The route.
   */
  public IndyRequest(Route<T> route) {
    this.route = route;
    this.body = new RequestBody();
  }

  /**
   * Set the request body.
   *
   * @param jsonObject The {@link JSONObject} which holds the form data.
   * @return Itself, for builder pattern purposes.
   */
  public IndyRequest<T> body(JSONObject jsonObject) {
    body.setJsonObject(jsonObject);
    return this;
  }

  /**
   * Send the indy http request and proceed the response.
   *
   * @param session The http session.
   * @return The newly generated {@link IndyObject}.
   */
  public T send(Session session) {
    RawResponse response = session.newRequest(route.getMethod(), route.getUrl())
        .body(body.toUrlEncodedMap())
        .send();

    // process and return object of type T (route.type)

    return null;
  }
}
