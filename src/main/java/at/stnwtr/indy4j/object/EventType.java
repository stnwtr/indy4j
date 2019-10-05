package at.stnwtr.indy4j.object;

/**
 * An enum to determine which event the current one is.
 *
 * @author stnwtr
 * @since 05.10.2019
 */
public enum EventType {

  /**
   * Future event with open hours.
   */
  EDITABLE_OPEN_HOURS(true, true, false),

  /**
   * Future event without open hours.
   */
  EDITABLE_NO_OPEN_HOURS(true, false, false),

  /**
   * Past event all signed.
   */
  NOT_EDITABLE_SIGNED(false, false, true),

  /**
   * Past event without all signed.
   */
  NOT_EDITABLE_NOT_SIGNED(false, false, false),

  /**
   * Past event with open hours.
   */
  NOT_EDITABLE_OPEN_HOURS(false, true, false),

  /**
   * Event on holiday.
   */
  HOLIDAY(false, false, false);

  /**
   * If the event is past or future. Editable or not.
   */
  private final boolean editable;

  /**
   * If there are open hours.
   */
  private final boolean openHours;

  /**
   * On past events if the hours are signed.
   */
  private final boolean signed;

  /**
   * Constructor which takes all there attributes.
   *
   * @param editable Past or future event.
   * @param openHours If there are open hours.
   * @param signed If the hours were are signed.
   */
  EventType(boolean editable, boolean openHours, boolean signed) {
    this.editable = editable;
    this.openHours = openHours;
    this.signed = signed;
  }

  /**
   * Check if the event is editable or not.
   *
   * @return True if it is editable, else false.
   */
  public boolean isEditable() {
    return editable;
  }

  /**
   * Check if there are open hours or not.
   *
   * @return True if not each hour is enrolled.
   */
  public boolean areHoursOpen() {
    return openHours;
  }

  /**
   * Check if the event was signed or not.
   *
   * @return True if it is signed, else false.
   */
  public boolean isSigned() {
    return signed;
  }

  public static EventType fromClasses(String classes) {
    switch (classes) {
      case "active CalendarToDoNotEnoughEntriesRegistered":
        return EDITABLE_OPEN_HOURS;
      case "active CalendarToDoAllEntriesRegistered":
        return EDITABLE_NO_OPEN_HOURS;
      case "CalendarPastSigned CalendarPastDays":
        return NOT_EDITABLE_SIGNED;
      case "CalendarPastNotSigned CalendarPastDays":
        return NOT_EDITABLE_NOT_SIGNED;
      case "CalendarPastNotEnoughEntriesRegistered CalendarPastDays":
        return NOT_EDITABLE_OPEN_HOURS;
      default:
        return HOLIDAY;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "EventType{" +
        "editable=" + editable +
        ", openHours=" + openHours +
        ", signed=" + signed +
        '}';
  }
}
