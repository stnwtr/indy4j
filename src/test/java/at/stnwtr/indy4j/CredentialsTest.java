package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.credentials.IllegalCredentialsException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Credentials} class.
 *
 * @author stnwtr
 * @since 28.09.2019
 */
class CredentialsTest {

  // TODO: 04.10.2019 Second resource file with credentials username.
  //                  Custom user tests for everyone. (username equals).

  /**
   * Check if the credentials created from the static factory are valid.
   */
  @Test
  void staticFactoryTest() {
    Credentials credentials = Credentials.from("pra14", "abc123");

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertEquals("abc123", credentials.getPassword());
  }

  /**
   * Check if the {@link Credentials#fromJsonObject(JSONObject)} method works as expected.
   */
  @Test
  void fromJsonObjectTest() {
    Credentials credentials = Credentials.fromJsonObject(
        new JSONObject().put("username", "pra14").put("password", "abc123")
    );

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertEquals("abc123", credentials.getPassword());
  }

  /**
   * Check if the file contains a valid credentials json object.
   */
  @Test
  void fromJsonFileTest() {
    try {
      URI uri = Objects
          .requireNonNull(CredentialsTest.class.getClassLoader().getResource("credentials.json"))
          .toURI();

      Credentials credentials = Credentials.fromJsonFile(Paths.get(uri).toString());
      Assertions.assertNotNull(credentials);
      Assertions.assertNotNull(credentials.getUsername());
      Assertions.assertNotNull(credentials.getPassword());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check if the credentials instance created from the json string are valid.
   */
  @Test
  void fromJsonStringTest() {
    String jsonString = "{\"username\": \"pra14\", \"password\": \"abc123\"}";
    Credentials credentials = Credentials.fromJsonString(jsonString);

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertEquals("abc123", credentials.getPassword());
  }

  /**
   * Check if can created a valid credentials instance from the dotted string.
   */
  @Test
  void fromDottedStringTest() {
    String dottedString = "pra14:abc123";
    Credentials credentials = Credentials.fromDottedString(dottedString);

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertEquals("abc123", credentials.getPassword());
  }

  /**
   * Check if an exception is thrown if {@link JSONObject} does not contain username and password.
   */
  @Test
  void illegalJsonObjectTest() {
    Assertions.assertThrows(IllegalCredentialsException.class,
        () -> Credentials.fromJsonObject(new JSONObject().put("username", "pra14")
    ));
  }

  /**
   * Check if an exception is thrown on illegal dotted string.
   */
  @Test
  void illegalDottedString() {
    Assertions.assertThrows(IllegalCredentialsException.class,
        () -> Credentials.fromDottedString("pra14::"));
  }
}
