package at.stnwtr.indy4j.event;

import at.stnwtr.indy4j.Indy;
import org.json.JSONObject;

/**
 * An {@link Event} which is already over.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public class PastEvent extends Event {

  /**
   * {@inheritDoc}
   */
  public PastEvent(Indy indy, EventContext eventContext, JSONObject jsonObject) {
    super(indy, eventContext, jsonObject);

    // TODO: 16.11.2019 load entries here
  }
}
