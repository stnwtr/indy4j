package at.stnwtr.indy4j.route;

/**
 * A collection of {@link Route} objects.
 *
 * @author stnwtr
 * @since 01.10.2019
 */
public class Routes {

  /**
   * The base URL for all http requests.
   */
  public static final String BASE_URL = "https://indy.sz-ybbs.ac.at/";

  /**
   * The route which logs the user in. Is a redirection.
   */
  public static final Route LOGIN = Route.post("pages/loginLogout/ldap_auth.php");

  /**
   * The route which checks if the user is logged in.
   */
  public static final Route LOGGED_IN = Route.get("pages/index.php");

  /**
   * The route which logs the user out.
   */
  public static final Route LOGOUT = Route.get("pages/loginLogout/logout.php");

  /**
   * Get all event entries.
   */
  public static final Route GET_EVENTS = Route.post("pages/calendarStudent/scripts/getEvents.php");

  /**
   * Check the teacher absence on a specific date.
   */
  public static final Route CHECK_TEACHER_ABSENCE = Route
      .post("php/queries/get/checkTeacherAbsence.php");

  /**
   * Load all indy data used for this event.
   */
  public static final Route LOAD_ALL = Route.post("php/queries/get/loadAll.php");

  /**
   * A link to the index page. View a list of special indy events.
   */
  public static final Route INDEX = Route.get("pages/index.php");

  /**
   * Load the calendar. No response from this request.
   */
  public static final Route CALENDAR = Route.get("pages/calendarStudent/calendar.php");

  /**
   * This route saves an entry.
   */
  public static final Route SAVE_ENTRY = Route.post("php/queries/set/saveEntry.php");

  /**
   * Change a past event entry to absent state.
   */
  public static final Route CHANGE_ABSENT = Route.post("php/queries/set/saveAbsenceChange.php");
}
