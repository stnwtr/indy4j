package at.stnwtr.indy4j.entry;

import org.json.JSONObject;

/**
 * This class creates an entry json object from attributes to enrol in indy lessons.
 *
 * @author stnwtr
 * @since 16.11.2019
 */
public class Entry {

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
  private Entry(EntryType entryType, int hour, String teacherId, String subject,
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

  public static Entry normalSchoolDay(int hour, String teacherId, String subject, String activity) {
    return new Entry(
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

  public static Entry schoolEvent(int hour, String teacherId, String event) {
    return new Entry(
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

  public static Entry schoolAbsence(int hour) {
    return new Entry(
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

  public static Entry freeRoom(int hour, String subject, String activity, House house,
      Floor floor) {
    return new Entry(
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

  public static Entry cancel(int hour) {
    return new Entry(
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
}
