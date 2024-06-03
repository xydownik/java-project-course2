package Tools;

import java.io.Serializable;
import java.util.Vector;

import Actors.Student;
import Enums.Clubs;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentOrgs.
 */
public class StudentOrgs implements  Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
    public  Clubs name;

    /** The president. */
    public Student president;

    /** The members. */
    public Vector<Student> members;

    /**
     * Creates the event.
     */
    public void createEvent() {
    	
    }
}

