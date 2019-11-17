package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.entry.Entry;
import at.stnwtr.indy4j.entry.Floor;
import at.stnwtr.indy4j.entry.House;
import at.stnwtr.indy4j.event.EventContext;
import at.stnwtr.indy4j.event.FutureEvent;
import at.stnwtr.indy4j.net.IndyResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
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

  /**
   * Check if loading all events is working as expected.
   */
  @Test
  void getEventsTest() {
    indy.login();

    indy.getNextEventContexts(10).stream()
        .map(EventContext::loadEvent)
        .filter(event -> event instanceof FutureEvent)
        .map(event -> (FutureEvent) event)
        .forEach(futureEvent -> {
          for (int i = 0; i < futureEvent.getEventContext().getHours().length; i++) {
            int hour = futureEvent.getEventContext().getHours()[i];

            Entry entry = Entry.cancel(hour);

            futureEvent.enrol(entry);
          }
        });

    indy.logout();
  }
}
