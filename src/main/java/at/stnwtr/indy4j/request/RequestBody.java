package at.stnwtr.indy4j.request;

import java.util.Map;
import org.json.JSONObject;

/**
 * The body used for http requests.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
public class RequestBody {

  /**
   * The private {@link JSONObject} to use.
   */
  private JSONObject jsonObject;

  /**
   * Constructor which takes the json object as parameter.
   *
   * @param jsonObject The whole json object.
   */
  public RequestBody(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  /**
   * Empty constructor. Initializes with null.
   */
  public RequestBody() {
    this(null);
  }

  /**
   * Set the new json object.
   *
   * @param jsonObject The json object.
   */
  void setJsonObject(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  /**
   * Get the json object as x-www-urlencoded map.
   *
   * @return The json object as map.
   */
  Map<String, ?> toUrlEncodedMap() {
    // TODO: 04.10.2019 check if arrays work as well
    //       if not -> recursive transformation
    //       if     -> useless wrapper object :c
    return jsonObject.toMap();
  }
}
