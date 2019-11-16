package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import at.stnwtr.indy4j.entry.EntryCombination;
import at.stnwtr.indy4j.teacher.InvalidTeacherException;
import at.stnwtr.indy4j.teacher.Teacher;
import at.stnwtr.indy4j.util.JsonUtility;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 * An abstract super event holder.
 *
 * @author stnwtr
 * @since 13.11.2019
 */
public abstract class Event {

  /**
   * The dependency to an {@link Indy} object.
   */
  protected final Indy indy;

  /**
   * The associated {@link EventContext}.
   */
  protected final EventContext eventContext;

  /**
   * The raw json object.
   */
  protected final JSONObject jsonObject;

  /**
   * The constructor which only expects the raw json response. Parses the json object.
   *
   * @param jsonObject The raw json response.
   */
  Event(Indy indy, EventContext eventContext, JSONObject jsonObject) {
    this.indy = indy;
    this.eventContext = eventContext;
    this.jsonObject = jsonObject;
  }

  /**
   * Parses the json object and builds all available {@link EntryCombination} objects.
   *
   * @param hour The hour to build this {@link Set} from;
   * @param allTeachers A {@link Set} with all available teachers.
   * @return A new {@link Set} which consists of all possible {@link EntryCombination} objects.
   */
  Set<EntryCombination> getCombinationForHour(int hour, Set<Teacher> allTeachers) {
    return JsonUtility.jsonArrayToJsonObjectSet(
        jsonObject.getJSONObject("teachers").getJSONObject(eventContext.getDay())
            .getJSONArray(String.valueOf(hour))).stream()
        .filter(combination -> !combination.getString("tid").equals("-"))
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
   * Get the {@link EventContext}.
   *
   * @return The {@link EventContext}.
   */
  public EventContext getEventContext() {
    return eventContext;
  }

  /**
   * Get the raw json object.
   *
   * @return The raw json object.
   */
  public JSONObject getJsonObject() {
    return jsonObject;
  }
}
