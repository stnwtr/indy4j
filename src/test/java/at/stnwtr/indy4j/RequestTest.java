package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.event.Event;
import at.stnwtr.indy4j.response.IndyResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the indy http requests.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
class RequestTest {

  /**
   * The indy instance.
   */
  private Indy indy;

  /**
   * Set the credentials.
   */
  @BeforeEach
  void setUp() {
    try {
      URI uri = Objects
          .requireNonNull(Credentials.class.getClassLoader().getResource("credentials.json"))
          .toURI();
      Credentials credentials = Credentials.fromJsonFile(Paths.get(uri).toString());

      indy = new Indy(credentials);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check if the response code is ok.
   */
  @Test
  void logActionsStatusCode() {
    IndyResponse login = indy.login();
    Assertions.assertEquals(200, login.getStatusCode());
    IndyResponse logout = indy.logout();
    Assertions.assertEquals(200, logout.getStatusCode());
  }

  /**
   * Check if the {@link Indy#loggedIn()} function works as expected.
   */
  @Test
  void checkUserLoggedIn() {
    indy.login();
    Assertions.assertTrue(indy.loggedIn());
    indy.logout();
    Assertions.assertFalse(indy.loggedIn());
  }

//  /**
//   * Check if loading all events is working as expected.
//   */
//  @Test
//  void getEventsTest() {
//    indy.login();
//
//    indy.getNextEvents(3).stream().map(Event::getDate).forEach(System.out::println);
//
//    indy.logout();
//  }
}
