package at.stnwtr.indy4j.entry;

public class Entry {

}

//package at.stnwtr.indy4j.entry;
//
//import at.stnwtr.indy4j.event.Event;
//import org.json.JSONObject;
//
///**
// * An entry for an {@link Event}.
// *
// * @author stnwtr
// * @since 15.11.2019
// */
//public class Entry {
//
//  /**
//   * The raw json object.
//   */
//  private final JSONObject jsonObject;
//
//  /**
//   * The type of the entry.
//   */
//  private final EntryType entryType;
//
//  /**
//   * -> I don't know what this value is for.
//   */
//  private final int signed;
//
//  /**
//   * -> I don't know what this value is for.
//   */
//  private final boolean tooLate;
//
//  /**
//   * Constructor which only expects the raw json object.
//   *
//   * @param jsonObject The raw json response.
//   */
//  private Entry(JSONObject jsonObject) {
//    this.jsonObject = jsonObject;
//
//    entryType = EntryType.getByTitle(jsonObject.getString("type"));
//    signed = jsonObject.optInt("signed", 0);
//    tooLate = jsonObject.optBoolean("tooLate", false);
//  }
//
//  /**
//   * Constructs a new {@link Entry} from the http json response.
//   *
//   * @param jsonObject The http json response.
//   * @return A new Entry.
//   */
//  public static Entry parseJsonResponse(JSONObject jsonObject) {
//    return new Entry(jsonObject);
//  }
//
//  public static Entry create(EntryType entryType)
//}
