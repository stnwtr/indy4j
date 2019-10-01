package at.stnwtr.indy4j;

import java.util.Objects;

/**
 * Simple user class for some tests.
 */
public class User {

  /**
   * The username.
   */
  private final String username;

  /**
   * The password.
   */
  private final String password;

  /**
   * Constructor which expects both attributes.
   *
   * @param username The username.
   * @param password The password.
   */
  User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Empty constructor which invalidates username and password.
   */
  public User() {
    this("invalid", "invalid");
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
  public String getPassword() {
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
    User user = (User) o;
    return Objects.equals(username, user.username) &&
        Objects.equals(password, user.password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
