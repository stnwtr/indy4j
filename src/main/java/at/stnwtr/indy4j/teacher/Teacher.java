package at.stnwtr.indy4j.teacher;

import at.stnwtr.indy4j.util.Pair;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class stores all attributes a teacher needs to identify.
 *
 * @author stnwtr
 * @since 13.11.2019
 */
public final class Teacher {

  /**
   * The unique teacher id.
   */
  private final String id;

  /**
   * The teacher's first name.
   */
  private final String firstName;

  /**
   * The teacher's last name.
   */
  private final String lastName;

  /**
   * Stores all expertise entries.
   */
  private final Set<Pair<String, String>> expertise;

  /**
   * Creates a new teacher with id, first name and last name.
   *
   * @param id The teacher id.
   * @param firstName The teacher's first name.
   * @param lastName Tht teacher's last name.
   */
  public Teacher(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;

    expertise = new HashSet<>();
  }

  /**
   * Get the teacher's unique id.
   *
   * @return The teacher's unique id.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the teacher's first name.
   *
   * @return The teacher's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Get the teacher's last name.
   *
   * @return The teacher's last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Adds a new expertise entry.
   *
   * @param title The title.
   * @param description The expertise description.
   */
  public void addExpertise(String title, String description) {
    expertise.add(new Pair<>(title, description));
  }

  /**
   * Get the set with all expert qualifications.
   *
   * @return A set with all expert qualifications.
   */
  public Set<Pair<String, String>> getExpertise() {
    return expertise;
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
    Teacher teacher = (Teacher) o;
    return Objects.equals(id, teacher.id) &&
        Objects.equals(firstName, teacher.firstName) &&
        Objects.equals(lastName, teacher.lastName) &&
        Objects.equals(expertise, teacher.expertise);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, expertise);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Teacher{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", expertise=" + expertise +
        '}';
  }
}
