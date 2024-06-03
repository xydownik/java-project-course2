package Comparators;
import java.util.Comparator;

import Actors.Teacher;
import Actors.User; 

// TODO: Auto-generated Javadoc
/**
 * The Class NameComparator.
 */
public class NameComparator implements Comparator<Teacher> {
	
	  /**
  	 * Compare.
  	 *
  	 * @param o1 the o 1
  	 * @param o2 the o 2
  	 * @return the int
  	 */

	@Override
	public int compare(Teacher o1, Teacher o2) {
		return o1.getName().compareTo(o2.getName());
	}


}
