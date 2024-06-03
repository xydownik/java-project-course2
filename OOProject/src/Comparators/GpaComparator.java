package Comparators;

import java.util.Comparator;

import Actors.Student;

// TODO: Auto-generated Javadoc
/**
 * The Class GpaComparator.
 */
public class GpaComparator implements Comparator<Student> {
	
	/**
	 * Compare.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @return the int
	 */
	@Override
	  public int compare(Student s1, Student s2) {
	    return s1.getGPA().compareTo(s2.getGPA());
	  }

	
}
