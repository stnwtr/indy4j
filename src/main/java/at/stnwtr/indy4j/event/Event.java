package at.stnwtr.indy4j.event;

import java.util.Arrays;
import java.util.Objects;
import org.json.JSONObject;

/**
 * A class which stores an event and its details.
 *
 * @author stnwtr
 * @since 20.10.2019
 */
public class Event {

  /**
   * The raw json object.
   */
  private final JSONObject jsonObject;

  /**
   * All html classes.
   */
  private final String classes;

  /**
   * True if the event is upcoming.
   */
  private final boolean future;

  /**
   * The date of the event.
   */
  private final String date;

  /**
   * The day of the week.
   */
  private final String day;

  /**
   * The count of indy hours.
   */
  private final int count;

  /**
   * The indy hours.
   */
  private final int[] openHours;

  /**
   * Constructor which expects the raw json object only.
   *
   * @param jsonObject The json object to build the event from.
   */
  public Event(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
    this.classes = jsonObject.getString("class");

    this.future = jsonObject.optString("pastOrFuturePopUp", "past").equals("future");
    this.date = jsonObject.optString("number", null);
    this.day = jsonObject.optString("day", null);
    this.count = jsonObject.optInt("count", 0);
    this.openHours = new int[count];

    if (future) {
      for (int i = 0; i < count; i++) {
        openHours[i] = jsonObject.getJSONArray("dayEvents").getJSONObject(i).getInt("title");
      }
    }
  }

  /**
   * Get the raw json object.
   *
   * @return The json object.
   */
  public JSONObject getJsonObject() {
    return jsonObject;
  }

  /**
   * Get the html classes.
   *
   * @return All the html classes.
   */
  public String getClasses() {
    return classes;
  }

  /**
   * Check if the event is holiday.
   *
   * @return True if the event is holiday.
   */
  public boolean isHoliday() {
    return classes.equals("CalendarHoliday");
  }

  /**
   * Check if the event is upcoming.
   *
   * @return True if the event is upcoming.
   */
  public boolean isFuture() {
    return future;
  }

  /**
   * Get the date of the event.
   *
   * @return The date of the event.
   */
  public String getDate() {
    return date;
  }

  /**
   * Get the day of the week.
   *
   * @return The day of the week.
   */
  public String getDay() {
    return day;
  }

  /**
   * Get the indy hour count.
   *
   * @return The indy hour count.
   */
  public int getCount() {
    return count;
  }

  /**
   * Get the indy hours.
   *
   * @return The indy hours.
   */
  public int[] getOpenHours() {
    return openHours;
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
    return future == event.future &&
        count == event.count &&
        Objects.equals(jsonObject, event.jsonObject) &&
        Objects.equals(classes, event.classes) &&
        Objects.equals(date, event.date) &&
        Objects.equals(day, event.day) &&
        Arrays.equals(openHours, event.openHours);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(jsonObject, classes, future, date, day, count);
    result = 31 * result + Arrays.hashCode(openHours);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Event{" +
        "jsonObject=" + jsonObject +
        ", classes='" + classes + '\'' +
        ", future=" + future +
        ", date='" + date + '\'' +
        ", day='" + day + '\'' +
        ", count=" + count +
        ", openHours=" + Arrays.toString(openHours) +
        '}';
  }
}
