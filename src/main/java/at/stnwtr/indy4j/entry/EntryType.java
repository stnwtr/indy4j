package at.stnwtr.indy4j.entry;

/**
 * Specifies the type of an indy entry.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public enum EntryType {

  /**
   * Just a normal entry.
   */
  NORMAL_SCHOOL_DAY("schoolday", "schultag"),

  /**
   * A school event. There is no student limit. All teachers available.
   */
  SCHOOL_EVENT("schoolevent", "schulveranstaltung"),

  /**
   * School absence indicates that you are not in school.
   */
  SCHOOL_ABSENCE("schoolabsence", "abwesenheit"),

  /**
   * Free room, can be anywhere in HAK or HTL without needing a teacher to check your presence.
   */
  FREE_ROOM("schoolfreeroom", "schultag");

  /**
   * The indy intern used title of the entry type.
   */
  private final String title;

  /**
   * The exact name of the request string.
   */
  private final String requestEventName;

  /**
   * Constructor which only expects the indy intern used title.
   *
   * @param title The title of the entry type.
   * @param requestEventName The name which is used for the http request to enrol.
   */
  EntryType(String title, String requestEventName) {
    this.title = title;
    this.requestEventName = requestEventName;
  }

  /**
   * Get the entry type by the indy intern used title.
   *
   * @param title The title of the entry type.
   * @return The with the title associated entry type.
   */
  public static EntryType getByTitle(String title) {
    for (int i = 0; i < values().length; i++) {
      if (values()[i].title.equals(title)) {
        return values()[i];
      }
    }

    return null;
  }

  /**
   * Get the indy intern used title.
   *
   * @return The entry type title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Get the name which is used for the request.
   *
   * @return The request string name.
   */
  public String getRequestEventName() {
    return requestEventName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "EntryType{" +
        "title='" + title + '\'' +
        ", requestEventName='" + requestEventName + '\'' +
        '}';
  }
}
