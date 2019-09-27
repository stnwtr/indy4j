package at.stnwtr.indy4j.net;

import java.util.Objects;

/**
 * Parameter which holds key and value.
 * Both attributes can not be null!
 *
 * @author stnwtr
 * @since 27.09.2019
 *
 * @param <T>
 */
public class Parameter<T> {

  /**
   * The immutable key.
   */
  protected final String key;

  /**
   * The immutable value.
   */
  protected final T value;

  /**
   * Constructor which takes both, key and value.
   *
   * @param key The key.
   * @param value The value.
   */
  public Parameter(String key, T value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Static factory pattern.
   *
   * @param key The key.
   * @param value The value.
   * @param <T> The type of the value.
   * @return A new parameter instance.
   */
  public static <T> Parameter<T> of(String key, T value) {
    return new Parameter<>(key, value);
  }

  /**
   * Get the key.
   *
   * @return The key.
   */
  public String getKey() {
    return key;
  }

  /**
   * Get the value.
   *
   * @return The value.
   */
  public T getValue() {
    return value;
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
    Parameter<?> parameter = (Parameter<?>) o;
    return Objects.equals(key, parameter.key) &&
        Objects.equals(value, parameter.value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Parameter{" +
        "key='" + key + '\'' +
        ", value=" + value +
        '}';
  }
}
