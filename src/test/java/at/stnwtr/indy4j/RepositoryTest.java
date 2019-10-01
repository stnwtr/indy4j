package at.stnwtr.indy4j;

import at.stnwtr.indy4j.repository.Repository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the whole {@link at.stnwtr.indy4j.repository}
 */
class RepositoryTest {

  /**
   * The user repository.
   */
  private Repository<User> users;

  /**
   * Some test users.
   */
  private User alex, max, mike;

  /**
   * Instantiate the repository and add tes users.
   */
  @BeforeEach
  void setUp() {
    users = Repository.create();

    alex = new User("Alex", "S");
    max = new User("Max", "S");
    mike = new User("Mike", "R");
  }

  /**
   * Check if the method to add a single element works.
   */
  @Test
  void addSinglesTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    Assertions.assertEquals(3, users.size());
  }

  /**
   * Check if the method to add a whole collection works.
   */
  @Test
  void addCollectionTest() {
    List<User> temporal = new ArrayList<>();

    temporal.add(alex);
    temporal.add(max);
    temporal.add(mike);

    users.add(temporal);

    Assertions.assertEquals(3, users.size());
  }

  /**
   * Check if the method to remove a single element works.
   */
  @Test
  void removeSingleTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    users.remove(alex);

    Assertions.assertEquals(2, users.size());
  }

  /**
   * Check if the method to remove a whole collection works.
   */
  @Test
  void removeCollectionTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    List<User> temporal = new ArrayList<>();

    temporal.add(alex);
    temporal.add(max);

    users.remove(temporal);

    Assertions.assertEquals(1, users.size());
  }

  /**
   * Test if the remove with specification works as expected.
   */
  @Test
  void removeSpecificationTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    users.remove(user -> "S".equals(user.getPassword()));

    Assertions.assertEquals(1, users.size());
  }

  /**
   * Test if the contains method works fine.
   */
  @Test
  void containsSingleTest() {
    users.add(alex);
    users.add(max);

    Assertions.assertTrue(users.contains(alex));
    Assertions.assertFalse(users.contains(mike));
  }

  /**
   * Test if contains with a {@link at.stnwtr.indy4j.repository.Specification} works well.
   */
  @Test
  void containsSpecificationTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    Assertions.assertTrue(users.contains(user -> "R".equals(user.getPassword())));
    Assertions.assertFalse(users.contains(user -> "A".equals(user.getPassword())));
  }

  /**
   * Check if get all returns all.
   */
  @Test
  void getAllTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    Assertions.assertEquals(3, users.getAll().size());
  }

  /**
   * Check if the query method works as expected.
   */
  @Test
  void queryTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    Assertions.assertEquals(2, users.query(user -> "S".equals(user.getPassword())).size());
    Assertions.assertEquals(0, users.query(user -> false).size());
    Assertions.assertEquals(3, users.query(user -> true).size());
  }

  /**
   * Check if the repository is cleared.
   */
  @Test
  void clearTest() {
    users.add(alex);
    users.add(max);
    users.add(mike);

    users.clear();

    Assertions.assertEquals(0, users.size());
  }
}
