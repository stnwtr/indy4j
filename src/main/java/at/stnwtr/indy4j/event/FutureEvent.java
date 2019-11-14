package at.stnwtr.indy4j.event;

import org.json.JSONObject;

public class FutureEvent extends Event {

  public FutureEvent(EventContext eventContext, JSONObject jsonObject) {
    super(eventContext, jsonObject);
  }
}
