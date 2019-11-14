package at.stnwtr.indy4j.event;

import org.json.JSONObject;

public class PastEvent extends Event {

  public PastEvent(EventContext eventContext, JSONObject jsonObject) {
    super(eventContext, jsonObject);
  }
}
