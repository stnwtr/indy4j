package at.stnwtr.indy4j.route;

import java.util.Objects;
import net.dongliu.requests.Methods;

/**
 * A mapping between the the indy paths and the response type.
 *
 * @author stnwtr
 * @since 01.10.2019
 *
 * @param <T> The response type.
 */
public class Route<T> {

  /**
   * The request method.
   */
  private final String method;

  /**
   * The indy url.
   */
  private final String url;

  /**
   * The response type class.
   */
  private final Class<T> responseType;

  /**
   * Private constructor to prevent instantiation from outside.
   * Expects the method, the url and the response type.
   *
   * @param method The request method.
   * @param url The indy url.
   * @param responseType The response type class.
   */
  private Route(String method, String url, Class<T> responseType) {
    this.method = method;
    this.url = url;
    this.responseType = responseType;
  }

  /**
   * Create a new post route.
   *
   * @param url The indy url.
   * @param responseType The response type class.
   * @param <T> The response type.
   * @return A new route with post request type.
   */
  public static <T> Route<T> post(String url, Class<T> responseType) {
    return new Route<>(Methods.POST, url, responseType);
  }

  /**
   * Create a new get route.
   *
   * @param url The indy url.
   * @param responseType The response type class.
   * @param <T> The response type.
   * @return A new route with get request type.
   */
  public static <T> Route<T> get(String url, Class<T> responseType) {
    return new Route<>(Methods.GET, url, responseType);
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
   * Get the response type class.
   *
   * @return The response type class.
   */
  public Class<T> getResponseType() {
    return responseType;
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
    Route<?> route = (Route<?>) o;
    return Objects.equals(method, route.method) &&
        Objects.equals(url, route.url) &&
        Objects.equals(responseType, route.responseType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(method, url, responseType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Route{" +
        "method='" + method + '\'' +
        ", url='" + url + '\'' +
        ", responseType=" + responseType +
        '}';
  }
}
