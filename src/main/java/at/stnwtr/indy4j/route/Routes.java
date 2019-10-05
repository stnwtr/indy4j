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
   * A link to the index page. View a list of special indy events.
   */
  public static final Route INDEX = Route.get("pages/index.php");

  /**
   * Load the calendar. No response from this request.
   */
  public static final Route CALENDAR = Route.get("pages/calendarStudent/calendar.php");

  /**
   * Get all event entries.
   */
  public static final Route GET_EVENTS = Route.post("pages/calendarStudent/scripts/getEvents.php");
}
