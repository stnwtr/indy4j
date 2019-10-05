package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.object.CustomIndyObject;
import at.stnwtr.indy4j.object.IndyObject;
import at.stnwtr.indy4j.route.Routes;
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
   * @return A new {@link IndyObject}.
   */
  public IndyObject login() {
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
  @SuppressWarnings("unchecked")
  public boolean loggedIn() {
    // TODO: 05.10.2019 Use regex or a html parser.
    CustomIndyObject<Boolean> response = Routes.LOGGED_IN.newRequest().send(session);
    response.setFunction(s -> s.contains("Abmelden"));
    return response.getFunction().apply(response.asString());
  }

  /**
   * Log out of the indy http session.
   *
   * @return A new {@link IndyObject}.
   */
  public IndyObject logout() {
    return Routes.LOGOUT.newRequest().send(session);
  }
}
