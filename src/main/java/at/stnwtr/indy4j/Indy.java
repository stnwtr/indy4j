package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.event.Event;
import at.stnwtr.indy4j.event.EventContext;
import at.stnwtr.indy4j.event.FutureEvent;
import at.stnwtr.indy4j.event.PastEvent;
import at.stnwtr.indy4j.net.IndyResponse;
import at.stnwtr.indy4j.route.Routes;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
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
   * Get a set of all events.
   *
   * @return A set of all events.
   */
  public Set<EventContext> getAllEventContexts() {
    JSONObject events = Routes.GET_EVENTS.newRequest().send(session).asJson();
    return events.keySet()
        .stream()
        .map(events::getJSONObject)
        .map(EventContext::new)
        .collect(Collectors.toSet());
  }

  /**
   * Get the next n upcoming events. Can be less than the limit.
   *
   * @param limit The amount of events the stream should contain.
   * @return A set of n upcoming events.
   */
  public Set<EventContext> getNextEventContexts(int limit) {
    return getAllEventContexts()
        .stream()
        .filter(EventContext::isFuture)
        .sorted(Comparator.comparing(EventContext::getDate))
        .limit(limit)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Load more event details into an {@link Event} object.
   *
   * @param eventContext The {@link EventContext}.
   * @return A new {@link Event}.
   */
  public Event eventFromContext(EventContext eventContext) {
    JSONObject jsonObject = Routes.LOAD_ALL.newRequest()
        .body(eventContext.getEventRequestParameter()).send(session).asJson();

    if (eventContext.isFuture()) {
      return new FutureEvent(jsonObject);
    } else {
      return new PastEvent(jsonObject);
    }
  }

//  public void loadEventContext(EventContext event) {
//    if (event.isHoliday())
//      return;
//
//    // is upcoming or not // load past // load all // <-- difference
//
//    JSONObject data = new JSONObject()
//        .put("day", event.getDay())
//        .put("date", event.getDate())
//        .put("totalHours", event.getCount())
//        .put("specificHours", event.getHours());
//
//    JSONObject details = Routes.LOAD_ALL.newRequest().body(data).send(session).asJson();
//
//    System.out.println(details.toString(4));
//  }

//
//  private Optional getFutureEventDetails(Event event) {
//    JSONArray specifiedHours = new JSONArray();
//    for (int hour : event.getHours()) {
//      specifiedHours.put(hour);
//    }
//    JSONObject data = new JSONObject()
//        .put("day", event.getDay())
//        .put("totalHours", event.getCount())
//        .put("date", event.getDate())
//        .put("specifiedHours", specifiedHours);
//
//    JSONObject details = Routes.LOAD_ALL.newRequest().body(data).send(session).asJson();
//
//    System.out.println(details);
//
//    return null;
//  }
//
//  private Optional getPastEventDetails(Event event) {
//    return null;
//  }
//
//  public Optional getEventDetails(Event event) {
////    JSONObject data = new JSONObject()
////        .put("date", event.getDate());
////
////     JSONObject absence = Routes.CHECK_TEACHER_ABSENCE.newRequest().body(data).send(session)
////        .asJson();
//
//    if (event.getEventType().isEditable()) {
//      return getFutureEventDetails(event);
//    } else {
//      return getPastEventDetails(event);
//    }
//  }
}
