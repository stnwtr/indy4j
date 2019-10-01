package at.stnwtr.indy4j;

import java.util.HashMap;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

/**
 * Main class for this project.
 *
 * @author stnwtr
 * @since 25.09.2019
 */
public class Indy {

  public static void main(String[] args) {
    Session session = Requests.session();

    RawResponse response = session
        .post("https://indy.sz-ybbs.ac.at/pages/loginLogout/ldap_auth.php")
        .body(new HashMap<String, String>() {{
          put("LoginName", "username");
          put("LoginPassword", "password");
          put("camefrom", "index");
        }})
        .send();

    System.out.println(response.statusLine());
    System.out.println(response.readToText());
  }
}
