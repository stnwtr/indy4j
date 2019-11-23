package at.stnwtr.indy4j.net;

import at.stnwtr.indy4j.route.Route;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Session;
import org.json.JSONObject;

/**
 * The more accurate indy http request which stores basic data.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
public class IndyRequest {

  /**
   * The route to request.
   */
  private final Route route;

  /**
   * The indy http request json body.
   */
  private JSONObject jsonBody;

  /**
   * The indy http request map entry body.
   */
  private List<? extends Map.Entry<String, ?>> mapEntryBody;

  /**
   * Constructor which takes only the {@link Route}.
   *
   * @param route The route.
   */
  public IndyRequest(Route route) {
    this.route = route;
    this.jsonBody = new JSONObject();
    mapEntryBody = new ArrayList<>();
  }

  /**
   * Set the request body.
   *
   * @param jsonObject The {@link JSONObject} which holds the form data.
   * @return Itself, for builder pattern purposes.
   */
  public IndyRequest body(JSONObject jsonObject) {
    this.jsonBody = jsonObject;
    return this;
  }

  /**
   * Set the request body.
   *
   * @param mapEntries An array of {@link Map.Entry} objects.
   * @return Itself, for builder pattern purposes.
   */
  public IndyRequest body(List<? extends Map.Entry<String, ?>> mapEntries) {
    this.mapEntryBody = mapEntries;
    return this;
  }

  /**
   * Send the indy http request and proceed the response.
   *
   * @param session The http session.
   * @return The newly generated {@link IndyResponse}.
   */
  public IndyResponse send(Session session) {
    RawResponse response = session.newRequest(route.getMethod(), route.getUrl())
        .body(jsonBody.toMap() == null ? new HashMap<>() : jsonBody.toMap())
        .send();

    return new IndyResponse(response);
  }

  public IndyResponse sendLoadAllRequest(Session session) {
    RawResponse response = session.newRequest(route.getMethod(), route.getUrl())
        .body(mapEntryBody)
        .send();

    return new IndyResponse(response);
  }
}
