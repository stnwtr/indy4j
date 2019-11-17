package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import org.json.JSONObject;

/**
 * An {@link Event} which is on a holiday.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public class HolidayEvent extends Event {

  /**
   * {@inheritDoc}
   */
  public HolidayEvent(Indy indy, EventContext eventContext, JSONObject jsonObject) {
    super(indy, eventContext, jsonObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "HolidayEvent{}";
  }
}
