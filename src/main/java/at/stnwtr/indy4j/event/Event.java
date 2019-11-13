package at.stnwtr.indy4j.event;

import org.json.JSONObject;

/**
 * An abstract super event holder.
 *
 * @author stnwtr
 * @since 13.11.2019
 */
public abstract class Event {

  /**
   * The raw json object.
   */
  protected final JSONObject jsonObject;

  /**
   * The constructor which only expects the raw json response.
   *
   * @param jsonObject The raw json response.
   */
  public Event(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }
}
