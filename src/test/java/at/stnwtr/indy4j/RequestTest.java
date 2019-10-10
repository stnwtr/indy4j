package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.response.IndyResponse;

import java.io.BufferedReader;
import java.io.StringReader;
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

  static String JSON_CREDENTIALS = "{\"username\": \"firstname.lastname\", \"password\": \"walrus123\"}";

  /**
   * Set the credentials.
   */
  @BeforeEach
  void setUp() {
    Credentials credentials = Credentials.fromJsonFile(new BufferedReader(new StringReader(JSON_CREDENTIALS)));
    indy = new Indy(credentials);
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
//    System.out.println("---------------");
//    indy.getEvents()
//        .stream()
//        .map(Event::getEventType)
//        .forEach(System.out::println);
//    System.out.println("---------------");
//
//    indy.logout();
//  }
}
