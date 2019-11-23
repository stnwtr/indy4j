package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import at.stnwtr.indy4j.entry.ResponseEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

/**
 * An {@link Event} which is already over.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public class PastEvent extends Event {

  /**
   * A map which stores the hour and the associated entry.
   */
  private final Map<Integer, Optional<ResponseEntry>> entries;

  /**
   * {@inheritDoc}
   */
  public PastEvent(Indy indy, EventContext eventContext, JSONObject jsonObject) {
    super(indy, eventContext, jsonObject);

    entries = new HashMap<>();
    for (int hour : eventContext.getHours()) {
      entries.put(hour, getEntryForHour(hour));
    }
  }

  /**
   * Mark this indy hour as absent.
   *
   * @param hour The indy hour.
   */
  public void changeAbsent(int hour) {
    indy.changeAbsent(this, hour);
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
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "PastEvent{}";
  }
}
