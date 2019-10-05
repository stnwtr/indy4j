package at.stnwtr.indy4j.object;

import java.util.Objects;
import java.util.function.Function;
import net.dongliu.requests.RawResponse;

/**
 * Custom configurable object.
 *
 * @param <T> The type to supply.
 */
public class CustomIndyObject<T> extends IndyObject {

  /**
   * The function to execute.
   */
  private Function<String, T> function;

  /**
   * {@inheritDoc}
   */
  public CustomIndyObject(RawResponse response) {
    super(response);
  }

  /**
   * Set the function which should be executed.
   *
   * @param function The function with input type string and result type T.
   * @return Itself, for builder pattern purposes.
   */
  public CustomIndyObject<T> setFunction(Function<String, T> function) {
    this.function = function;
    return this;
  }

  /**
   * Get the function to apply the response.
   *
   * @return The function.
   */
  public Function<String, T> getFunction() {
    return function;
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
    CustomIndyObject<?> that = (CustomIndyObject<?>) o;
    return Objects.equals(function, that.function);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), function);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "IndyCustomType{" +
        "function=" + function +
        ", response=" + response +
        '}';
  }
}
