package at.stnwtr.indy4j.repository;

/**
 * The specification interface to filter elements.
 *
 * @author stnwtr
 * @since 01.10.2019
 *
 * @param <T> The type of the elements to check.
 */
public interface Specification<T> {

  /**
   * Check if the passed element matches some condition.
   *
   * @param t The element to check.
   * @return If the element matches some criteria.
   */
  boolean isSatisfied(T t);
}
