package Education;
import Enums.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import Tools.*;
import Actors.*;
// TODO: Auto-generated Javadoc

/**
 * The Class Lesson.
 */
public class Lesson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The time. */
	public Time time;
	
	/** The type. */
	public LessonType type;
	
	/** The course. */
	public Course course;
	
	/** The teacher. */
	public Teacher teacher;
	
	/** The attendance. */
	public Vector<Map<Student, Boolean>> attendance;
	
	/** The room. */
	public Integer room;
	
	/** The limit for attendance. */
	public Integer limitForAttendance;

	/**
	 * Instantiates a new lesson.
	 */
	public Lesson() {
	}

	/**
	 * Instantiates a new lesson.
	 *
	 * @param time the time
	 * @param type the type
	 * @param course the course
	 * @param teacher the teacher
	 * @param room the room
	 * @param limitForAttendance the limit for attendance
	 */
	public Lesson(Time time, LessonType type, Course course, Teacher teacher, Integer room,
			Integer limitForAttendance) {
		this.time = time;
		this.type = type;
		this.course = course;
		this.teacher = teacher;
		this.room = room;
		this.limitForAttendance = limitForAttendance;
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Lesson other = (Lesson) o;
		return Objects.equals(attendance, other.attendance) && Objects.equals(course, other.course)
				&& Objects.equals(limitForAttendance, other.limitForAttendance) && Objects.equals(room, other.room)
				&& Objects.equals(teacher, other.teacher) && Objects.equals(time, other.time)
				&& Objects.equals(type, other.type);
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		return Objects.hash(attendance, course, limitForAttendance, room, teacher, time, type);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Lesson [time=" + time + ", type=" + type + ", course=" + course + ", teacher=" + teacher
				+ ", attendance=" + attendance + ", room=" + room + ", limitForAttendance=" + limitForAttendance + "]";
	}

	/**
	 * Make attendance.
	 *
	 * @param s the s
	 * @return the boolean
	 */
	public Boolean makeAttendance(Student s) {
		Map<Student, Boolean> mapp = new HashMap<>();
		mapp.put(s, true);
		attendance.add(mapp);
		return true;
	}
}

