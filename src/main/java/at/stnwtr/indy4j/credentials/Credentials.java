package at.stnwtr.indy4j.credentials;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import org.json.JSONObject;

/**
 * Stores the credentials for the login.
 *
 * @author stnwtr
 * @since 28.09.2019
 */
public class Credentials {

  /**
   * The username.
   */
  private final String username;

  /**
   * The password.
   */
  private final char[] password;

  /**
   * Constructor which takes both attributes.
   *
   * @param username The username.
   * @param password The password.
   */
  private Credentials(String username, char[] password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Static factory method to create new credentials.
   *
   * @param username The username.
   * @param password The password.
   * @return A new credentials instance.
   */
  public static Credentials from(String username, String password) {
    return new Credentials(username, password.toCharArray());
  }

  /**
   * Get a new credentials instance from a {@link JSONObject}.
   *
   * @param jsonObject The json object.
   * @return The new credentials instance.
   */
  public static Credentials fromJsonObject(JSONObject jsonObject) {
    if (!jsonObject.has("username") || !jsonObject.has("password")) {
      throw new IllegalCredentialsException("JSONObject must contain username and password!");
    }

    return new Credentials(jsonObject.getString("username"),
        jsonObject.getString("password").toCharArray());
  }

  /**
   * Get a new credentials instance from a file.
   *
   * @param path The file path.
   * @return The new credentials instance.
   */
  public static Credentials fromJsonFile(String path) {
    try {
      byte[] content = Files.readAllBytes(Paths.get(path));

      JSONObject jsonObject = new JSONObject(new String(content));

      return fromJsonObject(jsonObject);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalCredentialsException("Could not read json file!", e);
    }
  }

  /**
   * Get credential instance from json string.
   *
   * @param json The json string.
   * @return The new credentials instance.
   */
  public static Credentials fromJsonString(String json) {
    return fromJsonObject(new JSONObject(json));
  }

  /**
   * Get credential instance from a <quote>username:password</quote> string.
   *
   * @param dottedString The dotted string.
   * @return The new credentials instance.
   */
  public static Credentials fromDottedString(String dottedString) {
    String[] parts = dottedString.split(":");
    if (parts.length != 2) {
      throw new IllegalCredentialsException("Wrong number of \":\" in string.");
    }

    return new Credentials(parts[0], parts[1].toCharArray());
  }

  /**
   * Get the username.
   *
   * @return The username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Get the password.
   *
   * @return The password.
   */
  public char[] getPassword() {
    return password;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Credentials that = (Credentials) o;
    return Objects.equals(username, that.username) &&
        Arrays.equals(password, that.password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(username);
    result = 31 * result + Arrays.hashCode(password);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Credentials{" +
        "username='" + username + '\'' +
        ", password='" + "********" + '\'' +
        '}';
  }
}
