package at.stnwtr.indy4j.net.cookie;

import java.util.Objects;

/**
 * The attributes which identifies a cookie.
 *
 * @author stnwtr
 * @since 27.09.2019
 */
public class CookieKey {

  /**
   * The cookie domain.
   */
  private final String domain;

  /**
   * The cookie path.
   */
  private final String path;

  /**
   * The cookie name.
   */
  private final String name;

  /**
   * Constructor which takes all three attributes as parameter.
   *
   * @param domain The domain.
   * @param path The path.
   * @param name The name.
   */
  public CookieKey(String domain, String path, String name) {
    this.domain = domain;
    this.path = path;
    this.name = name;
  }

  /**
   * Static factory pattern.
   *
   * @param domain The cookie domain.
   * @param path The cookie path.
   * @param name The cookie name.
   * @return A new cookie key.
   */
  public static CookieKey of(String domain, String path, String name) {
    return new CookieKey(domain, path, name);
  }

  /**
   * Get the domain.
   *
   * @return The domain.
   */
  public String getDomain() {
    return domain;
  }

  /**
   * Get the path.
   *
   * @return The path.
   */
  public String getPath() {
    return path;
  }

  /**
   * Get the name.
   *
   * @return The name.
   */
  public String getName() {
    return name;
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
    CookieKey cookieKey = (CookieKey) o;
    return Objects.equals(domain, cookieKey.domain) &&
        Objects.equals(path, cookieKey.path) &&
        Objects.equals(name, cookieKey.name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(domain, path, name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "CookieKey{" +
        "domain='" + domain + '\'' +
        ", path='" + path + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
