package at.stnwtr.indy4j.entry;

import java.util.Objects;
import org.json.JSONObject;

/**
 * The class which stores attributes for response entry checks.
 *
 * @author stnwtr
 * @since 22.11.2019
 */
public class ResponseEntry {

  /**
   * The raw json object.
   */
  private final JSONObject jsonObject;

  /**
   * The {@link EntryType}.
   */
  private final EntryType entryType;

  /**
   * PROBABLY indicates if came too late.
   */
  private final boolean tooLate;

  /**
   * If teacher signed you.
   */
  private final boolean signed;

  /**
   * The id of the teacher.
   */
  private final String teacherId;

  /**
   * The subject for this lesson.
   */
  private final String subject;

  /**
   * The activity a student is planning to do.
   */
  private final String activity;

  /**
   * The description for an {@link EntryType#SCHOOL_EVENT}.
   */
  private final String description;

  /**
   * The house if {@link EntryType#FREE_ROOM} was chosen.
   */
  private final House house;

  /**
   * The floor if {@link EntryType#FREE_ROOM} was chosen.
   */
  private final Floor floor;

  /**
   * Constructor which only expects the raw json object to parse.
   *
   * @param jsonObject The json object.
   */
  public ResponseEntry(JSONObject jsonObject) {
    this.jsonObject = jsonObject;

    this.entryType = EntryType.getByTitle(jsonObject.getString("type"));
    this.tooLate = jsonObject.getInt("tooLate") != 0;
    this.signed = jsonObject.optInt("signed", 0) != 0 || entryType == EntryType.FREE_ROOM;

    this.teacherId = jsonObject.optString("tid", "-");
    this.subject = jsonObject.optString("subject", "");
    this.activity = jsonObject.optString("activity", "");
    this.description = jsonObject.optString("description", "");

    this.house = House.getByTitle(jsonObject.optString("house", ""));
    this.floor = Floor.getByTitle(jsonObject.optString("floor", ""));
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
   * Get the entry type.
   *
   * @return The {@link EntryType}.
   */
  public EntryType getEntryType() {
    return entryType;
  }

  /**
   * Check if one was too late.
   *
   * @return True if too late else false.
   */
  public boolean isTooLate() {
    return tooLate;
  }

  /**
   * Check if the lesson was signed.
   *
   * @return True if signed else false.
   */
  public boolean isSigned() {
    return signed;
  }

  /**
   * Get the teacher id.
   *
   * @return The teacher id.
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
   * Get the activity, if empty the description.
   *
   * @return The activity or the description.
   */
  public String getActivity() {
    if (activity.isEmpty()) {
      return description;
    }

    return activity;
  }

  /**
   * Get the description, if empty the action.
   *
   * @return The description or the action.
   */
  public String getDescription() {
    if (description.isEmpty()) {
      return activity;
    }

    return description;
  }

  /**
   * Get the house if {@link EntryType#FREE_ROOM} was chosen.
   *
   * @return The house.
   */
  public House getHouse() {
    return house;
  }

  /**
   * Get the floor if {@link EntryType#FREE_ROOM} was chosen.
   *
   * @return The floor.
   */
  public Floor getFloor() {
    return floor;
  }

  /**
   * Parse the response entry to a new request entry.
   *
   * @param hour The hour the entry is for.
   * @return A new {@link RequestEntry}.
   */
  public RequestEntry toRequestEntry(int hour) {
    switch (entryType) {
      case NORMAL_SCHOOL_DAY:
        return RequestEntry.normalSchoolDay(hour, teacherId, subject, activity);
      case SCHOOL_EVENT:
        return RequestEntry.schoolEvent(hour, teacherId, description);
      case SCHOOL_ABSENCE:
        return RequestEntry.schoolAbsence(hour);
      case FREE_ROOM:
        return RequestEntry.freeRoom(hour, subject, activity, house, floor);
      default:
        return RequestEntry.cancel(hour);
    }
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
    ResponseEntry that = (ResponseEntry) o;
    return tooLate == that.tooLate &&
        signed == that.signed &&
        Objects.equals(jsonObject, that.jsonObject) &&
        entryType == that.entryType &&
        Objects.equals(teacherId, that.teacherId) &&
        Objects.equals(subject, that.subject) &&
        Objects.equals(activity, that.activity) &&
        Objects.equals(description, that.description) &&
        house == that.house &&
        floor == that.floor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(jsonObject, entryType, tooLate, signed, teacherId, subject, activity, description,
            house, floor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "ResponseEntry{" +
        "jsonObject=" + jsonObject +
        ", entryType=" + entryType +
        ", tooLate=" + tooLate +
        ", signed=" + signed +
        ", teacherId='" + teacherId + '\'' +
        ", subject='" + subject + '\'' +
        ", activity='" + activity + '\'' +
        ", description='" + description + '\'' +
        ", house=" + house +
        ", floor=" + floor +
        '}';
  }
}
