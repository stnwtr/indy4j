package at.stnwtr.indy4j.entry;

import java.util.Objects;
import org.json.JSONObject;

/**
 * This class creates an entry json object from attributes to enrol in indy lessons.
 *
 * @author stnwtr
 * @since 16.11.2019
 */
public class RequestEntry {

  /**
   * The {@link EntryType}.
   */
  private final EntryType entryType;

  /**
   * The hour in which indy is.
   */
  private final int hour;

  /**
   * The ID of the teacher.
   */
  private final String teacherId;

  /**
   * The subject.
   */
  private final String subject;

  /**
   * The activity.
   */
  private final String activity;

  /**
   * The message on events.
   */
  private final String event;

  /**
   * The house if {@link EntryType#FREE_ROOM} was chosen.
   */
  private final House house;

  /**
   * The floor if {@link EntryType#FREE_ROOM} was chosen.
   */
  private final Floor floor;

  /**
   * The constructor which expects every single attribute.
   *
   * @param entryType The {@link EntryType}.
   * @param hour The indy hour.
   * @param teacherId The teacher's id.
   * @param subject The subject.
   * @param activity The activity.
   * @param event The event message.
   * @param house The house.
   * @param floor The floor.
   */
  private RequestEntry(EntryType entryType, int hour, String teacherId, String subject,
      String activity, String event, House house, Floor floor) {
    this.entryType = entryType;
    this.hour = hour;
    this.teacherId = teacherId;
    this.subject = subject;
    this.activity = activity;
    this.event = event;
    this.house = house;
    this.floor = floor;
  }

  /**
   * Create a new entry for a normal school day.
   *
   * @param hour The indy hour.
   * @param teacherId The teacher ID.
   * @param subject The subject.
   * @param activity The activity.
   * @return A new entry for normal school days.
   */
  public static RequestEntry normalSchoolDay(int hour, String teacherId, String subject,
      String activity) {
    return new RequestEntry(
        EntryType.NORMAL_SCHOOL_DAY,
        hour,
        teacherId,
        subject,
        activity,
        "undefined",
        House.EMPTY,
        Floor.EMPTY
    );
  }

  /**
   * Create a new entry for school events.
   *
   * @param hour The indy hour.
   * @param teacherId The teacher ID.
   * @param event The event description.
   * @return A new entry for school events.
   */
  public static RequestEntry schoolEvent(int hour, String teacherId, String event) {
    return new RequestEntry(
        EntryType.SCHOOL_EVENT,
        hour,
        teacherId,
        "",
        "undefined",
        event,
        House.EMPTY,
        Floor.EMPTY
    );
  }

  /**
   * Create a new entry for future school absence.
   *
   * @param hour The indy hour.
   * @return A new entry for school absence.
   */
  public static RequestEntry schoolAbsence(int hour) {
    return new RequestEntry(
        EntryType.SCHOOL_ABSENCE,
        hour,
        "undefined",
        "",
        "undefined",
        "undefined",
        House.EMPTY,
        Floor.EMPTY
    );
  }

  /**
   * Create a new entry for any free room.
   *
   * @param hour The indy hour.
   * @param subject The subject.
   * @param activity The activity.
   * @param house The house.
   * @param floor The floor.
   * @return A new entry for free room.
   */
  public static RequestEntry freeRoom(int hour, String subject, String activity, House house,
      Floor floor) {
    return new RequestEntry(
        EntryType.FREE_ROOM,
        hour,
        "",
        subject,
        activity,
        "undefined",
        house,
        floor
    );
  }

  /**
   * Cancel an entry subscription.
   *
   * @param hour The indy hour.
   * @return A new empty entry which overrides already existing entries.
   */
  public static RequestEntry cancel(int hour) {
    return new RequestEntry(
        EntryType.NORMAL_SCHOOL_DAY,
        hour,
        "",
        "",
        "",
        "undefined",
        House.EMPTY,
        Floor.EMPTY
    );
  }

  /**
   * Get the entry type.
   *
   * @return The entry type.
   */
  public EntryType getEntryType() {
    return entryType;
  }

  /**
   * Get the indy hour.
   *
   * @return The indy hour.
   */
  public int getHour() {
    return hour;
  }

  /**
   * Get the teacher ID.
   *
   * @return The teacher ID.
   */
  public String getTeacherId() {
    return teacherId;
  }

  /**
   * Get the subject.
   *
   * @return The subject.
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Get the activity.
   *
   * @return The activity.
   */
  public String getActivity() {
    return activity;
  }

  /**
   * Get the event description.
   *
   * @return The event description.
   */
  public String getEvent() {
    return event;
  }

  /**
   * Get the house if free room was chosen.
   *
   * @return The house.
   */
  public House getHouse() {
    return house;
  }

  /**
   * Get the floor if free room was chosen.
   *
   * @return The floor.
   */
  public Floor getFloor() {
    return floor;
  }

  /**
   * Summarizes the entry into a json object to send via http request.
   *
   * @return The json request object.
   */
  public JSONObject asJsonRequest() {
    return new JSONObject()
        .put("hour", hour)
        .put("teacher", teacherId)
        .put("subject", subject)
        .put("activity", activity)
        .put("entryType", entryType.getRequestEventName())
        .put("freeSpace", entryType == EntryType.FREE_ROOM ? "ja" : "nein")
        .put("event", event)
        .put("fid", floor.getTitle())
        .put("house", house.getTitle());
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
    RequestEntry requestEntry = (RequestEntry) o;
    return hour == requestEntry.hour &&
        entryType == requestEntry.entryType &&
        Objects.equals(teacherId, requestEntry.teacherId) &&
        Objects.equals(subject, requestEntry.subject) &&
        Objects.equals(activity, requestEntry.activity) &&
        Objects.equals(event, requestEntry.event) &&
        house == requestEntry.house &&
        floor == requestEntry.floor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(entryType, hour, teacherId, subject, activity, event, house, floor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Entry{" +
        "entryType=" + entryType +
        ", hour=" + hour +
        ", teacherId='" + teacherId + '\'' +
        ", subject='" + subject + '\'' +
        ", activity='" + activity + '\'' +
        ", event='" + event + '\'' +
        ", house=" + house +
        ", floor=" + floor +
        '}';
  }
}
