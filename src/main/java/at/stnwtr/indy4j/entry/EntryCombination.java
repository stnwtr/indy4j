package at.stnwtr.indy4j.entry;

import java.util.Objects;

/**
 * An combination of teacher and associated room with a few further attributes.
 *
 * @author stnwtr
 * @since 14.11.2019
 */
public class EntryCombination {

  private final Teacher teacher;

  private final String room;

  private final boolean consultation;

  private final boolean absence;

  private final int studentAmount;

  private final int studentLimit;

  private final int hour;

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

  public Teacher getTeacher() {
    return teacher;
  }

  public String getRoom() {
    return room;
  }

  public boolean isConsultation() {
    return consultation;
  }

  public boolean isAbsence() {
    return absence;
  }

  public int getStudentAmount() {
    return studentAmount;
  }

  public int getStudentLimit() {
    return studentLimit;
  }

  public int getHour() {
    return hour;
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(teacher, room, consultation, absence, studentAmount, studentLimit, hour);
  }

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
