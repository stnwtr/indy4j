package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.entry.EntryCombination;
import at.stnwtr.indy4j.entry.Teacher;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An abstract super event holder.
 *
 * @author stnwtr
 * @since 13.11.2019
 */
public class Event {

  /**
   * The associated {@link EventContext}.
   */
  protected final EventContext eventContext;

  /**
   * The raw json object.
   */
  protected final JSONObject jsonObject;

  /**
   * A set of all subjects.
   */
  protected final Set<String> subjects;

  /**
   * True if can log into free room else false.
   */
  protected final boolean freeRoomAllowed;

  /**
   * A set of all teachers.
   */
  protected final Set<Teacher> allTeachers;

  /**
   * A set of all possible entry combinations for both hours.
   */
  protected final Map<Integer, Set<EntryCombination>> entryCombinations;

  /**
   * The constructor which only expects the raw json response. Parses the json object.
   *
   * @param jsonObject The raw json response.
   */
  public Event(EventContext eventContext, JSONObject jsonObject) {
    this.eventContext = eventContext;
    this.jsonObject = jsonObject;

    subjects = jsonObject.getJSONArray("subjects").toList().stream()
        .map(String::valueOf)
        .collect(Collectors.toSet());

    freeRoomAllowed = jsonObject.getInt("freeRoom") == 1;

    JSONObject allTeachersJsonObject = jsonObject.getJSONObject("allTeachers");
    JSONObject allTeachersExpertise = jsonObject.getJSONObject("expertise");
    this.allTeachers = allTeachersJsonObject.keySet().stream()
        .map(id -> {
          Teacher teacher = new Teacher(id,
              allTeachersJsonObject.getJSONObject(id).getString("firstname"),
              allTeachersJsonObject.getJSONObject(id).getString("lastname"));

          if (allTeachersExpertise.has(id)) {
            JSONArray array = allTeachersExpertise.getJSONArray(id);
            for (int i = 0; i < array.length(); i++) {
              JSONObject expertise = array.getJSONObject(i);
              teacher.addExpertise(
                  expertise.getString("label"),
                  expertise.getString("content")
              );
            }
          }

          return teacher;
        })
        .collect(Collectors.toSet());

    entryCombinations = new HashMap<>();
    entryCombinations.put(3, getCombinationForHour(3));
    entryCombinations.put(4, getCombinationForHour(4));
  }

  private Set<EntryCombination> getCombinationForHour(int hour) {
    Set<EntryCombination> set = new HashSet<>();
    JSONArray array = jsonObject.getJSONObject("teachers").getJSONObject(eventContext.getDay())
        .getJSONArray(String.valueOf(hour));

    for (int i = 0; i < array.length(); i++) {
      JSONObject combination = array.getJSONObject(i);
      if (combination.getString("tid").equals("-")) {
        continue;
      }

      Teacher teacher = allTeachers.stream()
          .filter(t -> t.getId().equals(combination
              .getString("tid"))) // TODO: 14.11.2019 remote allTeachers and create teacher here.
          .findFirst().orElseThrow(Error::new); // TODO: 14.11.2019 teacher not found.
      String room = combination.getString("room");
      boolean consultation = combination.optBoolean("consultation", false);
      boolean absence = combination.optBoolean("absence", false);
      int amount = combination.getInt("studentAmount");
      int limit = combination.getInt("limit");

      set.add(new EntryCombination(teacher, room, consultation, absence, amount, limit, hour));
    }

    return set;
  }

  public Map<Integer, Set<EntryCombination>> getEntryCombinations() {
    return entryCombinations;
  }

  public Set<EntryCombination> getEntryCombinationForHour(int hour) {
    return entryCombinations.get(hour);
  }
}
