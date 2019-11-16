package at.stnwtr.indy4j.teacher;

/**
 * An exception which is thrown if a teacher or the teacher's id is not unique.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public class InvalidTeacherException extends RuntimeException {

  /**
   * {@inheritDoc}
   */
  public InvalidTeacherException() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public InvalidTeacherException(String message) {
    super(message);
  }

  /**
   * {@inheritDoc}
   */
  public InvalidTeacherException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * {@inheritDoc}
   */
  public InvalidTeacherException(Throwable cause) {
    super(cause);
  }
}
