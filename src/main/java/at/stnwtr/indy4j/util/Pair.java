package at.stnwtr.indy4j.util;

import java.util.Objects;

/**
 * Stores a key and a value in a single object.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class Pair<K, V> {

  /**
   * The generic key object.
   */
  private final K key;

  /**
   * The generic value object.
   */
  private final V value;

  /**
   * Constructor which expects both, key and value.
   *
   * @param key The key.
   * @param value The value.
   */
  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Get the key of the pair.
   *
   * @return The key.
   */
  public K getKey() {
    return key;
  }

  /**
   * Get the value of the pair.
   *
   * @return The value.
   */
  public V getValue() {
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
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(key, pair.key) &&
        Objects.equals(value, pair.value);
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
    return "Pair{" +
        "key=" + key +
        ", value=" + value +
        '}';
  }
}
