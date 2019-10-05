package at.stnwtr.indy4j.route;

import at.stnwtr.indy4j.request.IndyRequest;
import java.util.Objects;
import net.dongliu.requests.Methods;

/**
 * A mapping between the the indy paths and the response type.
 *
 * @author stnwtr
 * @since 01.10.2019
 */
public class Route {

  /**
   * The request method.
   */
  private final String method;

  /**
   * The indy url.
   */
  private final String url;

  /**
   * Private constructor to prevent instantiation from outside.
   * Expects the method, the url and the response type.
   *
   * @param method The request method.
   * @param url The indy url.
   */
  private Route(String method, String url) {
    this.method = method;
    this.url = Routes.BASE_URL + url;
  }

  /**
   * Create a new post route.
   *
   * @param url The indy url.
   * @return A new route with post request type.
   */
  public static Route post(String url) {
    return new Route(Methods.POST, url);
  }

  /**
   * Create a new get route.
   *
   * @param url The indy url.
   * @return A new route with get request type.
   */
  public static Route get(String url) {
    return new Route(Methods.GET, url);
  }

  /**
   * Get the request method.
   *
   * @return The request method.
   */
  public String getMethod() {
    return method;
  }

  /**
   * Get the indy url.
   *
   * @return The indy url.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Generate a new {@link IndyRequest}.
   *
   * @return The new request.
   */
  public IndyRequest newRequest() {
    return new IndyRequest(this);
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
    Route route = (Route) o;
    return Objects.equals(method, route.method) &&
        Objects.equals(url, route.url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(method, url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Route{" +
        "method='" + method + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
