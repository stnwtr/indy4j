package at.stnwtr.indy4j.net.cookie;

import at.stnwtr.indy4j.net.Parameter;
import java.util.Objects;

/**
 * The cookie bean.
 *
 * @author stnwtr
 * @since 27.09.2019
 */
public class Cookie extends Parameter<String> {

  /**
   * The cookie domain.
   */
  private final String domain;

  /**
   * The cookie path.
   */
  private final String path;

  /**
   * The cookie expiration timestamp, zero means no expiration.
   */
  private final long expiration;

  /**
   * If secure flag is set.
   */
  private final boolean secure;

  /**
   * If cookie is for host only.
   */
  private final boolean hostOnly;

  /**
   * Constructor which expects all attributes.
   *
   * @param key The key.
   * @param value The value.
   * @param domain The domain.
   * @param path The path.
   * @param expiration The expiration timestamp.
   * @param secure The secure flag.
   * @param hostOnly The host only flag.
   */
  public Cookie(String key, String value, String domain, String path, long expiration,
      boolean secure, boolean hostOnly) {
    super(key, value);
    this.domain = domain;
    this.path = path;
    this.expiration = expiration;
    this.secure = secure;
    this.hostOnly = hostOnly;
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
   * Get the expiration timestamp.
   *
   * @return The expiration timestamp.
   */
  public long getExpiration() {
    return expiration;
  }

  /**
   * Check if the secure flag is set.
   *
   * @return If the secure flag is set.
   */
  public boolean isSecure() {
    return secure;
  }

  /**
   * Check if the host only flag is set.
   *
   * @return If the host only flag is set.
   */
  public boolean isHostOnly() {
    return hostOnly;
  }

  /**
   * Check if the cookie has expired.
   *
   * @param now The timestamp to check.
   * @return If the cookie has expired.
   */
  public boolean hasExpired(long now) {
    return expiration != 0 && expiration < now;
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
    if (!super.equals(o)) {
      return false;
    }
    Cookie cookie = (Cookie) o;
    return expiration == cookie.expiration &&
        secure == cookie.secure &&
        hostOnly == cookie.hostOnly &&
        Objects.equals(domain, cookie.domain) &&
        Objects.equals(path, cookie.path);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), domain, path, expiration, secure, hostOnly);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Cookie{" +
        "domain='" + domain + '\'' +
        ", path='" + path + '\'' +
        ", expiration=" + expiration +
        ", secure=" + secure +
        ", hostOnly=" + hostOnly +
        ", key='" + key + '\'' +
        ", value=" + value +
        '}';
  }
}
