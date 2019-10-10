package at.stnwtr.indy4j;

import at.stnwtr.indy4j.credentials.Credentials;
import at.stnwtr.indy4j.credentials.IllegalCredentialsException;

import java.io.BufferedReader;
import java.io.StringReader;
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
    // the string doesn't matter in this case
    Credentials credentials = Credentials.of("pra14", "abc123".toCharArray());

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertArrayEquals("abc123".toCharArray(), credentials.getPassword());
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
    Assertions.assertArrayEquals("abc123".toCharArray(), credentials.getPassword());
  }

  /**
   * Check if the BufferedReader contains a valid credentials json object.
   */
  @Test
  void fromJsonFileTest() {
    Credentials credentials = Credentials.fromJsonFile(new BufferedReader(new StringReader(RequestTest.JSON_CREDENTIALS)));
    Assertions.assertEquals("firstname.lastname", credentials.getUsername());
    Assertions.assertArrayEquals("walrus123".toCharArray(), credentials.getPassword());
  }

  /**
   * Check if the credentials instance created from the json string are valid.
   */
  @Test
  void fromJsonStringTest() {
    Credentials credentials = Credentials.fromJsonString(RequestTest.JSON_CREDENTIALS);

    Assertions.assertEquals("firstname.lastname", credentials.getUsername());
    Assertions.assertArrayEquals("walrus123".toCharArray(), credentials.getPassword());
  }

  /**
   * Check if can created a valid credentials instance from the dotted string.
   */
  @Test
  void fromDottedStringTest() {
    String dottedString = "pra14:abc123";
    Credentials credentials = Credentials.fromDottedString(dottedString);

    Assertions.assertEquals("pra14", credentials.getUsername());
    Assertions.assertArrayEquals("abc123".toCharArray(), credentials.getPassword());
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
