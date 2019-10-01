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
   * Instantiate the repository and add tes users.
   */
  @BeforeEach
  void setUp() {
    users = Repository.create();

    users.add(new User("A", "A"));
    users.add(new User("B", "B"));
    users.add(new User("C", "C"));
  }

  /**
   * Check if the method to add a single element works.
   */
  @Test
  void addSinglesTest() {
    users.clear();
    users.add(new User("A", "A"));
    users.add(new User("B", "B"));
    users.add(new User("C", "C"));

    Assertions.assertEquals(3, users.size());
  }

  /**
   * Check if the method to add a whole collection works.
   */
  @Test
  void addCollectionTest() {
    List<User> temporal = new ArrayList<>();

    temporal.add(new User("A", "A"));
    temporal.add(new User("B", "B"));
    temporal.add(new User("C", "C"));

    users.clear();
    users.add(temporal);

    Assertions.assertEquals(3, users.size());
  }
}
