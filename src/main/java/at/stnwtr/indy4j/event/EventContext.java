package at.stnwtr.indy4j.event;

import java.util.Objects;
import org.json.JSONObject;

/**
 * A class which stores an event and its details.
 *
 * @author stnwtr
 * @since 20.10.2019
 */
public class EventContext {

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
   * Constructor which expects the raw json object only.
   *
   * @param jsonObject The json object to build the event from.
   */
  public EventContext(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
    this.classes = jsonObject.getString("class");

    this.future = jsonObject.optString("pastOrFuturePopUp", "past").equals("future");
    this.date = jsonObject.optString("number", null);
    this.day = jsonObject.optString("day", null);
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
   * Get the {@link JSONObject} which is used to get further event details.
   *
   * @return The {@link Event} request json object.
   */
  public JSONObject getEventRequestParameter() {
    return new JSONObject()
        .put("day", day)
        .put("date", date)
        .put("totalHours", 2)
        .put("specificHours", new JSONObject()
            .put("0", 3)
            .put("1", 4)
        );
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
    EventContext that = (EventContext) o;
    return future == that.future &&
        Objects.equals(jsonObject, that.jsonObject) &&
        Objects.equals(classes, that.classes) &&
        Objects.equals(date, that.date) &&
        Objects.equals(day, that.day);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(jsonObject, classes, future, date, day);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "EventContext{" +
        "jsonObject=" + jsonObject +
        ", classes='" + classes + '\'' +
        ", future=" + future +
        ", date='" + date + '\'' +
        ", day='" + day + '\'' +
        '}';
  }
}
