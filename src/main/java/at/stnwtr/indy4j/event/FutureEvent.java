package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import at.stnwtr.indy4j.entry.Entry;
import at.stnwtr.indy4j.entry.EntryCombination;
import at.stnwtr.indy4j.route.Routes;
import at.stnwtr.indy4j.teacher.Teacher;
import at.stnwtr.indy4j.util.JsonUtility;
import java.util.HashMap;
import java.util.Map;
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
   * A map which stores hour and entry.
   */
  private final Map<Integer, Set<Entry>> entries = null;

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
    for (int i = 0; i < eventContext.getHours().length; i++) {
      entryCombinations
          .put(eventContext.getHours()[i],
              getCombinationForHour(eventContext.getHours()[i], allTeachers));
    }

    // TODO: 15.11.2019 load entries here
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
   * @param entry The entry to save.
   * @return The newly built {@link JSONObject}.
   */
  public JSONObject enrolmentJsonRequest(Entry entry) {
    return entry.asJsonRequest()
        .put("day", eventContext.getDay())
        .put("date", eventContext.getDate());
  }

  /**
   * Enrol in the indy service.
   *
   * @param entry The entry to save.
   */
  public void enrol(Entry entry) {
    indy.enrol(this, entry);
  }
}
