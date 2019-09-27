package at.stnwtr.indy4j.net.cookie;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A collection of all cookies from all visited {@link URL}s.
 */
public class CookieJar {

  /**
   * The map which stores key and cookie.
   */
  private Map<CookieKey, Cookie> cookieMap;

  /**
   * Basic empty constructor.
   */
  public CookieJar() {
    cookieMap = new HashMap<>();
  }

  /**
   * Store a new {@link Collection} of cookies.
   *
   * @param cookies The cookies.
   */
  public void storeCookies(Collection<Cookie> cookies) {
    cookies.forEach(cookie ->
        cookieMap.put(CookieKey.of(cookie.getDomain(), cookie.getPath(), cookie.getKey()), cookie));

    removeExpiredCookies();
  }

  private void removeExpiredCookies() {
    long now = System.currentTimeMillis();
    cookieMap.values().removeIf(cookie -> cookie.hasExpired(now));
  }

  /**
   * Get all cookies from a specific {@link URL}.
   *
   * @param url The {@link URL}.
   * @return All cookies from there.
   */
  List<Cookie> getCookies(URL url) {
    throw new NotImplementedException();
  }

  /**
   * Get all cookies.
   *
   * @return All cookies.
   */
  List<Cookie> getCookies() {
    return new ArrayList<>(cookieMap.values());
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
    CookieJar cookieJar = (CookieJar) o;
    return Objects.equals(cookieMap, cookieJar.cookieMap);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(cookieMap);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "CookieJar{" +
        "cookieMap=" + cookieMap +
        '}';
  }
}
