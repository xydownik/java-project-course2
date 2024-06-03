package Tools;


import java.io.Serializable;

import Actors.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Complaint.
 */
public class Complaint extends Request implements  Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The text. */
    private String text;

    /** The student. */
    private Student student;
    
    /** The to. */
    private Dean to;
    
    /** The from. */
    private Teacher from;

    /**
     * Instantiates a new complaint.
     *
     * @param student the student
     * @param text the text
     * @param to the to
     * @param from the from
     */
    public Complaint(Student student, String text, Dean to, Teacher from) {
    	this.student = student;
    	this.text = text;
    	this.to = to;
    	this.from = from;
    }
    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return null;
    }

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the student.
	 *
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Sets the student.
	 *
	 * @param student the new student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public Dean getTo() {
		return to;
	}

	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(Dean to) {
		this.to = to;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public Teacher getFrom() {
		return from;
	}

	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(Teacher from) {
		this.from = from;
	}
}

