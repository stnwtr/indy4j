package at.stnwtr.indy4j.object;

import org.json.JSONObject;

/**
 * The class which stores an indy event.
 *
 * @author stnwtr
 * @since 05.10.2019
 */
public class Event {

  /**
   * The raw json object.
   */
  private final JSONObject jsonObject;

  /**
   * The {@link EventType} which stores a few attributes.
   */
  private final EventType eventType;

  /**
   * The date of this event.
   */
  private String date;

  /**
   * The day of this event.
   */
  private String day;

  /**
   * Constructor which only expects the raw json object.
   *
   * @param jsonObject The {@link JSONObject}.
   */
  public Event(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
    eventType = EventType.fromClasses(jsonObject.getString("class"));

    if (eventType == EventType.HOLIDAY) {
      return;
    }

    this.date = jsonObject.getString("number");
    this.day = jsonObject.getString("day");
  }

  /**
   * Get the json object.
   *
   * @return The json object.
   */
  public JSONObject getJsonObject() {
    return jsonObject;
  }

  /**
   * Get the event type.
   *
   * @return The event type.
   */
  public EventType getEventType() {
    return eventType;
  }

  /**
   * Get the date of this event.
   *
   * @return The date of this event.
   */
  public String getDate() {
    return date;
  }

  /**
   * Get the day of this event.
   *
   * @return The day of this event.
   */
  public String getDay() {
    return day;
  }
}
