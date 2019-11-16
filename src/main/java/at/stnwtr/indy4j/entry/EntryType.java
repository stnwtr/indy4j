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
  NORMAL_SCHOOL_DAY("schoolday"),

  /**
   * A school event. There is no student limit. All teachers available.
   */
  SCHOOL_EVENT("schoolevent"),

  /**
   * School absence indicates that you are not in school.
   */
  SCHOOL_ABSENCE("schoolabsence"),

  /**
   * Free room, can be anywhere in HAK or HTL without needing a teacher to check your presence.
   */
  FREE_ROOM("schoolfreeroom");

  /**
   * The indy intern used title of the entry type.
   */
  private final String title;

  /**
   * Constructor which only expects the indy intern used title.
   *
   * @param title The title of this entry type.
   */
  EntryType(String title) {
    this.title = title;
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
}
