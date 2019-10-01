package at.stnwtr.indy4j.request;

import java.util.Objects;

/**
 * Simple immutable key value pair.
 *
 * @author stnwtr
 * @since 01.10.2019
 *
 * @param <T> The value type.
 */
public class Parameter<T> {

  /**
   * The parameter key.
   */
  private final String name;

  /**
   * The parameter value.
   */
  private final T value;

  /**
   * Private constructor to prevent instantiation.
   *
   * @param name The parameter name.
   * @param value The parameter value.
   */
  private Parameter(String name, T value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Static factory method.
   *
   * @param name The parameter name.
   * @param value The parameter value.
   * @param <T> The type of the value.
   * @return A new parameter instance.
   */
  public static <T> Parameter<T> of(String name, T value) {
    return new Parameter<>(name, value);
  }

  /**
   * Get the parameter name.
   *
   * @return The parameter name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the parameter value.
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
    return Objects.equals(name, parameter.name) &&
        Objects.equals(value, parameter.value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Parameter{" +
        "name='" + name + '\'' +
        ", value=" + value +
        '}';
  }
}
