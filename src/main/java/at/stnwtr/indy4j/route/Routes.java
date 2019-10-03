package at.stnwtr.indy4j.route;

import at.stnwtr.indy4j.object.IndyObject;

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
  public static final Route<IndyObject> LOGIN = Route.post("pages/loginLogout/ldap_auth.php", null);

  /**
   * The route which logs the user out.
   */
  public static final Route<IndyObject> LOGOUT = Route.get("pages/loginLogout/logout.php", null);

  /**
   * A link to the index page. View a list of special indy events.
   */
  public static final Route<IndyObject> INDEX = Route.get("pages/index.php", null);

  /**
   * Load the calendar. No response from this request.
   */
  public static final Route<IndyObject> CALENDAR = Route.get("pages/calendarStudent/calendar.php", null);
}
