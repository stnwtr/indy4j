package at.stnwtr.indy4j.entry;

/**
 * An enum for all possible houses.
 *
 * @author stnwtr
 * @since 16.11.2019
 */
public enum House {

  /**
   * The HAK building.
   */
  HAK("HAK"),

  /**
   * The HTL building.
   */
  HTL("HTL"),

  /**
   * Defines an empty house.
   */
  EMPTY("");

  /**
   * The indy intern used title for the house.
   */
  private final String title;

  /**
   * Constructor which only expects the indy intern used title.
   *
   * @param title The title.
   */
  House(String title) {
    this.title = title;
  }

  /**
   * Get the house by the indy intern used title.
   *
   * @param title The title.
   * @return The with the title associated house.
   */
  public static House getByTitle(String title) {
    for (int i = 0; i < values().length; i++) {
      if (values()[i].title.equals(title)) {
        return values()[i];
      }
    }

    return null;
  }

  /**
   * Get the indy intern used house title.
   *
   * @return The title used for the request.
   */
  public String getTitle() {
    return title;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "House{" +
        "title='" + title + '\'' +
        '}';
  }
}
