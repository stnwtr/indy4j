package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.object.Event;
import at.stnwtr.indy4j.response.IndyResponse;
import at.stnwtr.indy4j.route.Routes;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import org.json.JSONObject;

/**
 * Main class for this project.
 *
 * @author stnwtr
 * @since 25.09.2019
 */
public class Indy {

  /**
   * The {@link Credentials} the user needs to log in.
   */
  private final Credentials credentials;

  /**
   * The http session which sends the requests.
   */
  private final Session session;

  /**
   * Constructor which expects only the credentials.
   *
   * @param credentials The user credentials.
   */
  public Indy(Credentials credentials) {
    this.credentials = credentials;
    session = Requests.session();
  }

  /**
   * Log in into the indy http session.
   *
   * @return A new {@link IndyResponse}.
   */
  public IndyResponse login() {
    JSONObject data = new JSONObject()
        .put("LoginName", credentials.getUsername())
        .put("LoginPassword", credentials.getPassword())
        .put("camefrom", "index");

    return Routes.LOGIN.newRequest().body(data).send(session);
  }

  /**
   * Check if the user is logged in.
   *
   * @return True if logged in, else false.
   */
  public boolean loggedIn() {
    IndyResponse response = Routes.LOGGED_IN.newRequest().send(session);
    return response.asString().contains("Abmelden"); // Use regex or a html parser
  }

  /**
   * Log out of the indy http session.
   *
   * @return A new {@link IndyResponse}.
   */
  public IndyResponse logout() {
    return Routes.LOGOUT.newRequest().send(session);
  }

  /**
   * Get a stream of all events.
   *
   * @return A stream of all events.
   */
  public Stream<Event> getEvents() {
    JSONObject events = Routes.GET_EVENTS.newRequest().send(session).asJson();
    return events.keySet()
        .stream()
        .map(events::getJSONObject)
        .map(Event::new);
  }

  /**
   * Get the next n upcoming events. Can be less than the limit.
   *
   * @param limit The amount of events the stream should contain.
   * @return A stream of n upcoming events.
   */
  public Stream<Event> getNextEvents(int limit) {
    return getEvents()
        .filter(event -> event.getEventType().isEditable())
        .sorted(Comparator.comparing(Event::getDate))
        .limit(limit);
  }
}
