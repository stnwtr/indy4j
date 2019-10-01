package at.stnwtr.indy4j.repository;

import java.util.Collection;

/**
 * Repository to store objects of type e.
 *
 * @author stnwtr
 * @since 01.10.2019
 *
 * @param <E> Type of the elements.
 */
public interface Repository<E> {

  /**
   * Add a new element.
   *
   * @param element The element to add.
   */
  void add(E element);

  /**
   * Add a collection of elements.
   *
   * @param elements The collection to add.
   */
  default void add(Collection<E> elements) {
    elements.forEach(this::add);
  }

  /**
   * Remove an element.
   *
   * @param element The element to remove.
   */
  void remove(E element);

  /**
   * Remove all elements in the given collection.
   *
   * @param elements All elements to remove.
   */
  default void remove(Collection<E> elements) {
    elements.forEach(this::remove);
  }

  /**
   * Remove elements which are satisfied by this {@link Specification}.
   *
   * @param specification The {@link Specification} which checks the elements.
   */
  void remove(Specification<E> specification);

  /**
   * Check if the repository contains a given element.
   *
   * @param element The element to check.
   */
  boolean contains(E element);

  /**
   * Check if the repository contains elements which matches the {@link Specification}.
   *
   * @param specification The {@link Specification} to check if the elements match.
   */
  boolean contains(Specification<E> specification);

  /**
   * Get all elements.
   *
   * @return All elements.
   */
  Collection<E> getAll();

  /**
   * Select all elements which matches the given {@link Specification}.
   *
   * @param specification The {@link Specification} to check if the elements match.
   * @return A collection of elements which match.
   */
  Collection<E> query(Specification<E> specification);

  /**
   * Get the size of the repository.
   *
   * @return The size of the repository.
   */
  int size();

  /**
   * Clear the repository.
   */
  void clear();

  /**
   * Create a new {@link ListRepository}.
   *
   * @param <E> The type of the repository.
   * @return A new instance of the {@link ListRepository}.
   */
  static <E> Repository<E> create() {
    return new ListRepository<>();
  }
}
