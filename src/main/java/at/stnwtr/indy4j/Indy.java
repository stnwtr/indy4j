package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.route.Routes;
import java.util.HashMap;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import org.json.JSONArray;
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
   */
  public void login() {
    JSONObject data = new JSONObject()
        .put("LoginName", credentials.getUsername())
        .put("LoginPassword", credentials.getPassword())
        .put("camefrom", "index");

    Routes.LOGIN.newRequest().body(data).send(session);
  }

  /**
   * Log out of the indy http session.
   */
  public void logout() {
    Routes.LOGOUT.newRequest().send(session);
  }
}
