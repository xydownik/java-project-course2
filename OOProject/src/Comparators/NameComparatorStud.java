package Comparators;
import java.util.Comparator;

import Actors.Student;
import Actors.Teacher;
import Actors.User; 

// TODO: Auto-generated Javadoc
/**
 * The Class NameComparator.
 */
public class NameComparatorStud implements Comparator<Student> {
	
	  /**
  	 * Compare.
  	 *
  	 * @param o1 the o 1
  	 * @param o2 the o 2
  	 * @return the int
  	 */


	@Override
	public int compare(Student o1, Student o2) {
		return o1.getName().compareTo(o2.getName());
	}


}
