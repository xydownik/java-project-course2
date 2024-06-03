package Education;

import Enums.*;
import SubSystems.Translator;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import Actors.Student;
import Actors.Teacher;

// TODO: Auto-generated Javadoc
/**
 * The Class Course.
 */
public class Course implements Serializable {
    
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The students. */
    public Vector<Student> students;

    /** The teachers. */
    public Vector<Teacher> teachers;

    /** The type. */
    public CourseType type;

    /** The schedule. */
    public Vector<Lesson> schedule;

    /** The marks. */
    public Vector<Mark> marks;

    /** The credits. */
    public Integer credits;

    /** The place limit. */
    public int placeLimit;

    /** The prerequisites. */
    public Vector<Course> prerequisites;

    /** The name. */
    public String name;

    
    /**
     * Instantiates a new course.
     */
    public Course() {
    	students = new Vector<Student>();
    	teachers  = new Vector<Teacher>();
    	schedule = new Vector<Lesson>();
    	marks = new Vector<Mark>();
    	this.prerequisites = new Vector<Course>();
    }
    
    /**
     * Instantiates a new course.
     *
     * @param type the type
     * @param credits the credits
     * @param placeLimit the place limit
     * @param name the name
     * @param pre the pre
     */
    public Course(CourseType type, Integer credits, Integer placeLimit, String name, Vector<Course> pre) {
    	this();
    	this.type = type;
    	this.credits = credits;
    	this.placeLimit = placeLimit;
    	this.name = name;
    	this.prerequisites = pre;
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
		Course other = (Course) o;
		return Objects.equals(credits, other.credits) && Objects.equals(marks, other.marks)
				&& Objects.equals(name, other.name) && placeLimit == other.placeLimit
				&& Objects.equals(prerequisites, other.prerequisites) && Objects.equals(schedule, other.schedule)
				&& Objects.equals(students, other.students) && Objects.equals(teachers, other.teachers)
				&& Objects.equals(type, other.type);
	}

    /**
     * Hash code.
     *
     * @return the int
     */
    public int hashCode() {
    	return Objects.hash(credits, marks, name, placeLimit, prerequisites, schedule, students, teachers, type);
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
    	return Translator.translate(" Name: ") + name + Translator.translate(" Type: ") + type + 
    			Translator.translate(" Credits: ") + credits + Translator.translate(" Limit of place: ") + placeLimit;
    }
	

	/**
	 * Gets the mark.
	 *
	 * @param s the s
	 * @return the mark
	 */
    public Mark getMark(Student s) {
    	Mark m = (Mark) this.marks.stream().filter(n->n.studentId.equals(s.getId()));
		return m;
    }

    /**
     * Adds the student.
     *
     * @param s the s
     */
    public void addStudent(Student s) {
    	students.add(s);
    }

    /**
     * Removes the student.
     *
     * @param s the s
     */
    public void removeStudent(Student s) {
    	students.remove(s);
    }
}

