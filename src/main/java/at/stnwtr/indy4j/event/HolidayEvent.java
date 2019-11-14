package at.stnwtr.indy4j.event;

import org.json.JSONObject;

public class HolidayEvent extends Event {

  public HolidayEvent(EventContext eventContext, JSONObject jsonObject) {
    super(eventContext, jsonObject);
  }
}
