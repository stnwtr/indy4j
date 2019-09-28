package at.stnwtr.indy4j.credentials;

/**
 * Thrown if credentials could not be created or are illegal.
 *
 * @author stnwtr
 * @since 28.09.2019
 */
public class IllegalCredentialsException extends RuntimeException {

  /**
   * {@inheritDoc}
   */
  public IllegalCredentialsException() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public IllegalCredentialsException(String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  public IllegalCredentialsException(String message, Throwable cause) {
    super(message, cause);
  }
}
