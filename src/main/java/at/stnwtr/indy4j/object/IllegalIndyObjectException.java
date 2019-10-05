package at.stnwtr.indy4j.object;

/**
 * Thrown if there is an error with an {@link IndyObject}.
 *
 * @author stnwtr
 * @since 04.10.2019
 */
public class IllegalIndyObjectException extends RuntimeException {

  /**
   * {@inheritDoc}
   */
  public IllegalIndyObjectException() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public IllegalIndyObjectException(String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  public IllegalIndyObjectException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "IllegalIndyObjectException{}";
  }
}
