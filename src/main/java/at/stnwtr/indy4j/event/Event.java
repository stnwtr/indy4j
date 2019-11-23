package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import at.stnwtr.indy4j.entry.ResponseEntry;
import java.util.Objects;
import java.util.Optional;
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
  final Indy indy;

  /**
   * The associated {@link EventContext}.
   */
  final EventContext eventContext;

  /**
   * The raw json object.
   */
  final JSONObject jsonObject;

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
   * Get the entry for a specific hour.
   *
   * @param hour The hour to get the entry for.
   * @return The {@link ResponseEntry} wrapped in an optional instance.
   */
  Optional<ResponseEntry> getEntryForHour(int hour) {
    return Optional.ofNullable(jsonObject)
        .map(jo -> jo.optJSONObject("entries"))
        .map(jo -> jo.optJSONObject(String.valueOf(hour)))
        .map(ResponseEntry::new);
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
    return Objects.equals(indy, event.indy) &&
        Objects.equals(eventContext, event.eventContext) &&
        Objects.equals(jsonObject, event.jsonObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(indy, eventContext, jsonObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Event{" +
        "indy=" + indy +
        ", eventContext=" + eventContext +
        ", jsonObject=" + jsonObject +
        '}';
  }
}
