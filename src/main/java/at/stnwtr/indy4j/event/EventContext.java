package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
   * The dependency to an {@link Indy} object.
   */
  private final Indy indy;

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
   * The count of the indy hours.
   */
  private final int hourCount;

  /**
   * The indy hours.
   */
  private final int[] hours;

  /**
   * Constructor which expects the raw json object only.
   *
   * @param jsonObject The json object to build the event from.
   */
  public EventContext(Indy indy, JSONObject jsonObject) {
    this.indy = indy;
    this.jsonObject = jsonObject;
    this.classes = jsonObject.getString("class");

    this.future = jsonObject.optString("pastOrFuturePopUp", "past").equals("future");
    this.date = jsonObject.optString("number", "");
    this.day = jsonObject.optString("day", "");

    this.hourCount = 2;
    this.hours = new int[]{3, 4};

    // TODO: 15.11.2019 check for past event and parse count & hours.
    /*
    this.hourCount = future ? jsonObject.optInt("count", 0) : 0;
    hours = new int[hourCount];

    for (int i = 0; i < hourCount; i++) {
      hours[i] = jsonObject.getJSONArray("dayEvents").getJSONObject(i).getInt("title");
    }
     */
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
   * Get the amount of indy lessons.
   *
   * @return The amount of indy lessons.
   */
  public int getHourCount() {
    return hourCount;
  }

  /**
   * Get an array of the hours which are indy lessons.
   *
   * @return The indy lessons.
   */
  public int[] getHours() {
    return hours;
  }

  /**
   * Get the {@link JSONObject} which is used to get further event details.
   *
   * @return The {@link Event} request data.
   */
  public List<? extends Map.Entry<String, ?>> getEventRequestData() {
    List<SimpleEntry<String, ?>> entries = new ArrayList<>();
    entries.add(new SimpleEntry<>("day", day));
    entries.add(new SimpleEntry<>("date", date));
    entries.add(new SimpleEntry<>("totalHours", hourCount));
    for (int hour : hours) {
      entries.add(new SimpleEntry<>("specificHours[]", hour));
    }

    return entries;
  }

  /**
   * Load more event details.
   *
   * @return A new {@link Event} filled with details.
   */
  public Event loadEvent() {
    return indy.eventFromContext(this);
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
        hourCount == that.hourCount &&
        Objects.equals(indy, that.indy) &&
        Objects.equals(jsonObject, that.jsonObject) &&
        Objects.equals(classes, that.classes) &&
        Objects.equals(date, that.date) &&
        Objects.equals(day, that.day) &&
        Arrays.equals(hours, that.hours);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(indy, jsonObject, classes, future, date, day, hourCount);
    result = 31 * result + Arrays.hashCode(hours);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "EventContext{" +
        "indy=" + indy +
        ", jsonObject=" + jsonObject +
        ", classes='" + classes + '\'' +
        ", future=" + future +
        ", date='" + date + '\'' +
        ", day='" + day + '\'' +
        ", hourCount=" + hourCount +
        ", hours=" + Arrays.toString(hours) +
        '}';
  }
}
