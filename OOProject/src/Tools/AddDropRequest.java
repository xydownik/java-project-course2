package Tools;

import Enums.*;

import java.io.Serializable;
import java.lang.*;

import Actors.Student;
import Education.Course;

// TODO: Auto-generated Javadoc
/**
 * The Class AddDropRequest.
 */
public class AddDropRequest extends Request implements  Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
    public AddDropType type;

    /** The course. */
    public Course course;

    /** The student. */
    public Student student;
    
    /**
     * Instantiates a new adds the drop request.
     *
     * @param type the type
     * @param course the course
     * @param student the student
     */
    public AddDropRequest(AddDropType type, Course course, Student student) {
    	this.type = type;
    	this.course = course;
    	this.student = student;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "Add/Drop: " + type + " Course: " + " Student: " + student;
    }

	

	
}

