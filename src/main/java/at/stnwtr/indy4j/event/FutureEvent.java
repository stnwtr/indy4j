package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import at.stnwtr.indy4j.entry.EntryCombination;
import at.stnwtr.indy4j.entry.RequestEntry;
import at.stnwtr.indy4j.entry.ResponseEntry;
import at.stnwtr.indy4j.teacher.InvalidTeacherException;
import at.stnwtr.indy4j.teacher.Teacher;
import at.stnwtr.indy4j.util.JsonUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 * An {@link Event} which is in the future on an event day.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public class FutureEvent extends Event {

  /**
   * A set of all possible subjects.
   */
  private final Set<String> subjects;

  /**
   * True if can use free room.
   */
  private final boolean freeRoomAllowed;

  /**
   * A set of all teachers.
   */
  private final Set<Teacher> allTeachers;

  /**
   * A map which stores hour and a set of available entry combinations.
   */
  private final Map<Integer, Set<EntryCombination>> entryCombinations;

  /**
   * A map which stores the hour and the associated entry.
   */
  private final Map<Integer, Optional<ResponseEntry>> entries;

  /**
   * {@inheritDoc}
   */
  public FutureEvent(Indy indy, EventContext eventContext, JSONObject jsonObject) {
    super(indy, eventContext, jsonObject);

    subjects = jsonObject.getJSONArray("subjects")
        .toList().stream()
        .map(String::valueOf)
        .collect(Collectors.toSet());

    freeRoomAllowed = jsonObject.getInt("freeRoom") == 1;

    allTeachers = jsonObject.getJSONObject("allTeachers").keySet().stream()
        .map(id -> {
          Teacher teacher = new Teacher(id,
              jsonObject.getJSONObject("allTeachers").getJSONObject(id).getString("lastname"),
              jsonObject.getJSONObject("allTeachers").getJSONObject(id).getString("firstname"));

          if (jsonObject.getJSONObject("expertise").has(id)) {
            JsonUtility
                .jsonArrayToJsonObjectSet(jsonObject.getJSONObject("expertise").getJSONArray(id))
                .forEach(expertise -> teacher.addExpertise(
                    expertise.getString("label"),
                    expertise.getString("content")
                ));
          }

          return teacher;
        })
        .collect(Collectors.toSet());

    entryCombinations = new HashMap<>();
    for (int hour : eventContext.getHours()) {
      entryCombinations.put(hour, getCombinationForHour(hour));
    }

    entries = new HashMap<>();
    for (int hour : eventContext.getHours()) {
      entries.put(hour, getEntryForHour(hour));
    }
  }

  /**
   * Parses the json object and builds all available {@link EntryCombination} objects.
   *
   * @param hour The hour to build this {@link Set} from;
   * @return A new {@link Set} which consists of all possible {@link EntryCombination} objects.
   */
  private Set<EntryCombination> getCombinationForHour(int hour) {
    return JsonUtility.jsonArrayToJsonObjectSet(
        jsonObject.getJSONObject("teachers").getJSONObject(eventContext.getDay())
            .getJSONArray(String.valueOf(hour))).stream()
        .filter(combination -> !combination.getString("tid").equals(Teacher.INVALID.getId()))
        .map(combination -> {
          String teacherId = combination.getString("tid");
          Teacher teacher = allTeachers.stream()
              .filter(t -> t.getId().equals(teacherId))
              .findFirst()
              .orElseThrow(() -> new InvalidTeacherException("Teacher for combination not found!"));

          return new EntryCombination(
              teacher,
              combination.getString("room"),
              combination.optBoolean("consultation", false),
              combination.optBoolean("absence", false),
              combination.getInt("studentAmount"),
              combination.getInt("limit"),
              hour
          );
        })
        .collect(Collectors.toSet());
  }

  /**
   * Get all possible subjects.
   *
   * @return All possible subjects.
   */
  public Set<String> getSubjects() {
    return subjects;
  }

  /**
   * Get a flag if free room is allowed.
   *
   * @return True if free room entry is allowed else false.
   */
  public boolean isFreeRoomAllowed() {
    return freeRoomAllowed;
  }

  /**
   * Get a set of all teachers.
   *
   * @return All teachers.
   */
  public Set<Teacher> getAllTeachers() {
    return allTeachers;
  }

  /**
   * Get the map of indy lesson and the set of {@link EntryCombination} objects.
   *
   * @return The map of indy lesson and entry combinations.
   */
  public Map<Integer, Set<EntryCombination>> getEntryCombinations() {
    return entryCombinations;
  }

  /**
   * Get the set of {@link EntryCombination} objects for a specific hour.
   *
   * @param hour The hour to get the combinations for.
   * @return The set of entry combinations, null if the hour is not an indy lesson.
   */
  public Set<EntryCombination> getEntryCombinationForHour(int hour) {
    return entryCombinations.get(hour);
  }

  /**
   * Get all rooms for a specific hour.
   *
   * @param hour The indy hour to get the room names from.
   * @return A set of room names.
   */
  public Set<String> getRooms(int hour) {
    return getEntryCombinationForHour(hour).stream()
        .map(EntryCombination::getRoom)
        .collect(Collectors.toSet());
  }

  /**
   * Get all teachers for a specific hour.
   *
   * @param hour The indy hour to get the teachers from.
   * @return A set of teachers.
   */
  public Set<Teacher> getTeachers(int hour) {
    return getEntryCombinationForHour(hour).stream()
        .map(EntryCombination::getTeacher)
        .collect(Collectors.toSet());
  }

  /**
   * Get the json object needed to save an entry.
   *
   * @param requestEntry The entry to save.
   * @return The newly built {@link JSONObject}.
   */
  public JSONObject enrolmentJsonRequest(RequestEntry requestEntry) {
    return requestEntry.asJsonRequest()
        .put("day", eventContext.getDay())
        .put("date", eventContext.getDate());
  }

  /**
   * Get all entries.
   *
   * @return All entries.
   */
  public Map<Integer, Optional<ResponseEntry>> getEntries() {
    return entries;
  }

  /**
   * Enrol in the indy service.
   *
   * @param requestEntry The entry to save.
   */
  public void enrol(RequestEntry requestEntry) {
    indy.enrol(this, requestEntry);
  }

  /**
   * Cancel an indy enrolment.
   *
   * @param hour The indy hour.
   */
  public void cancel(int hour) {
    indy.enrol(this, RequestEntry.cancel(hour));
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
    if (!super.equals(o)) {
      return false;
    }
    FutureEvent that = (FutureEvent) o;
    return freeRoomAllowed == that.freeRoomAllowed &&
        Objects.equals(subjects, that.subjects) &&
        Objects.equals(allTeachers, that.allTeachers) &&
        Objects.equals(entryCombinations, that.entryCombinations);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), subjects, freeRoomAllowed, allTeachers, entryCombinations);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "FutureEvent{" +
        "subjects=" + subjects +
        ", freeRoomAllowed=" + freeRoomAllowed +
        ", allTeachers=" + allTeachers +
        ", entryCombinations=" + entryCombinations +
        '}';
  }
}
