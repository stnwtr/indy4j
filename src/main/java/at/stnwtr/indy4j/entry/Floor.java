package at.stnwtr.indy4j.entry;

/**
 * An enum for possible floors.
 *
 * @author stnwtr
 * @since 16.11.2019
 */
public enum Floor {

  /**
   * The basement.
   */
  BASEMENT("Keller"),

  /**
   * The ground floor.
   */
  GROUND_FLOOR("Erdgeschoss"),

  /**
   * The first floor.
   */
  FIRST_FLOOR("1. Stock"),

  /**
   * The second floor.
   */
  SECOND_FLOOR("2. Stock"),

  /**
   * The third floor.
   */
  THIRD_FLOOR("3. Stock");

  /**
   * The indy intern used title for the floor.
   */
  private final String title;

  /**
   * Constructor which expects the indy intern used title only.
   *
   * @param title The title.
   */
  Floor(String title) {
    this.title = title;
  }

  /**
   * Get the floor by the indy intern used title.
   *
   * @param title The title.
   * @return The with the title associated floor.
   */
  public static Floor getByTitle(String title) {
    for (int i = 0; i < values().length; i++) {
      if (values()[i].title.equals(title)) {
        return values()[i];
      }
    }

    return null;
  }
}
