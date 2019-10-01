package at.stnwtr.indy4j.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple implementation of the {@link Repository} with a {@link List} as holder.
 *
 * @author stnwtr
 * @since 01.10.2019
 *
 * @param <E>
 */
public class ListRepository<E> implements Repository<E> {

  /**
   * The list which holds all elements.
   */
  private final List<E> list;

  /**
   * Package private constructor to direct instantiation.
   */
  ListRepository() {
    this.list = new ArrayList<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(E element) {
    list.add(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(E element) {
    list.remove(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(Specification<E> specification) {
    list.removeIf(specification::isSatisfied);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(E element) {
    return list.contains(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(Specification<E> specification) {
    return list.stream()
        .anyMatch(specification::isSatisfied);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<E> getAll() {
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<E> query(Specification<E> specification) {
    return list.stream()
        .filter(specification::isSatisfied)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int size() {
    return list.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    list.clear();
  }
}
