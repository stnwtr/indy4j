package at.stnwtr.indy4j.object;

import java.util.Arrays;
import java.util.Objects;
import org.json.JSONArray;
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
   * The count of indy hours.
   */
  private int count;

  /**
   * The hours as array.
   */
  private int[] hours;

  /**
   * Constructor which only expects the raw json object.
   *
   * @param jsonObject The {@link JSONObject}.
   */
  public Event(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
    eventType = EventType.fromClasses(jsonObject.getString("class"));

    if (eventType == EventType.HOLIDAY) {
      hours = new int[0];
      return;
    }

    date = jsonObject.getString("number");
    day = jsonObject.getString("day");
    count = jsonObject.getInt("count");

    if (eventType != EventType.EDITABLE_OPEN_HOURS
        && eventType != EventType.EDITABLE_NO_OPEN_HOURS) {
      hours = new int[0]; // count = 2; array length = 0; ???
      return;
    }

    hours = new int[count];
    JSONArray dayEvents = jsonObject.getJSONArray("dayEvents");
    for (int i = 0; i < dayEvents.length(); i++) {
      hours[i] = dayEvents.getJSONObject(i).getInt("title");
    }
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

  /**
   * Get the count of indy hours.
   *
   * @return The count of indy hours.
   */
  public int getCount() {
    return count;
  }

  /**
   * Get the hours in which indy takes place.
   *
   * @return The hours in which indy takes place.
   */
  public int[] getHours() {
    return hours;
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
    Event event = (Event) o;
    return count == event.count &&
        Objects.equals(jsonObject, event.jsonObject) &&
        eventType == event.eventType &&
        Objects.equals(date, event.date) &&
        Objects.equals(day, event.day) &&
        Arrays.equals(hours, event.hours);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(jsonObject, eventType, date, day, count);
    result = 31 * result + Arrays.hashCode(hours);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Event{" +
        "jsonObject=" + jsonObject +
        ", eventType=" + eventType +
        ", date='" + date + '\'' +
        ", day='" + day + '\'' +
        ", count=" + count +
        ", hours=" + Arrays.toString(hours) +
        '}';
  }
}
