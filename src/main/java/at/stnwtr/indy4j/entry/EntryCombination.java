package at.stnwtr.indy4j.entry;

import at.stnwtr.indy4j.teacher.Teacher;
import java.util.Objects;

/**
 * An combination of teacher and associated room with a few further attributes.
 *
 * @author stnwtr
 * @since 14.11.2019
 */
public class EntryCombination {

  /**
   * The specific teacher.
   */
  private final Teacher teacher;

  /**
   * The room the teacher is in.
   */
  private final String room;

  /**
   * True if teacher is open for consulting.
   */
  private final boolean consultation;

  /**
   * If teacher is absent.
   */
  private final boolean absence;

  /**
   * The current student amount.
   */
  private final int studentAmount;

  /**
   * The maximal student amount.
   */
  private final int studentLimit;

  /**
   * The hour of the day.
   */
  private final int hour;

  /**
   * Constructor which expects all possible attributes.
   *
   * @param teacher The taecher.
   * @param room The room.
   * @param consultation If consultation.
   * @param absence If absent.
   * @param studentAmount The student amount.
   * @param studentLimit The student limit.
   * @param hour The hour.
   */
  public EntryCombination(Teacher teacher, String room, boolean consultation, boolean absence,
      int studentAmount, int studentLimit, int hour) {
    this.teacher = teacher;
    this.room = room;
    this.consultation = consultation;
    this.absence = absence;
    this.studentAmount = studentAmount;
    this.studentLimit = studentLimit;
    this.hour = hour;
  }

  /**
   * Get the teacher.
   *
   * @return The teacher.
   */
  public Teacher getTeacher() {
    return teacher;
  }

  /**
   * Get the room.
   *
   * @return The room.
   */
  public String getRoom() {
    return room;
  }

  /**
   * Check if teacher is open for consulting.
   *
   * @return True if teacher is open for consulting else false.
   */
  public boolean isConsultation() {
    return consultation;
  }

  /**
   * Check if the teacher is absent.
   *
   * @return True if the teacher is absent else false.
   */
  public boolean isAbsence() {
    return absence;
  }

  /**
   * Get the current amount of students.
   *
   * @return The amount of students.
   */
  public int getStudentAmount() {
    return studentAmount;
  }

  /**
   * Get the student limit.
   *
   * @return The student limit.
   */
  public int getStudentLimit() {
    return studentLimit;
  }

  /**
   * Get the hour of the day.
   *
   * @return The hour.
   */
  public int getHour() {
    return hour;
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
    EntryCombination that = (EntryCombination) o;
    return consultation == that.consultation &&
        absence == that.absence &&
        studentAmount == that.studentAmount &&
        studentLimit == that.studentLimit &&
        hour == that.hour &&
        Objects.equals(teacher, that.teacher) &&
        Objects.equals(room, that.room);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(teacher, room, consultation, absence, studentAmount, studentLimit, hour);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "EntryCombination{" +
        "teacher=" + teacher +
        ", room='" + room + '\'' +
        ", consultation=" + consultation +
        ", absence=" + absence +
        ", studentAmount=" + studentAmount +
        ", studentLimit=" + studentLimit +
        ", hour=" + hour +
        '}';
  }
}
