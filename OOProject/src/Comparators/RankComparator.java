package Comparators;

import java.util.Comparator;

import Actors.Teacher;
import Researcher.ResearchPaper;

// TODO: Auto-generated Javadoc
/**
 * The Class RankComparator.
 */
public class RankComparator implements Comparator<Teacher>{

	
	/**
	 * Compare.
	 *
	 * @param t1 the t 1
	 * @param t2 the t 2
	 * @return the int
	 */
	@Override
	  public int compare(Teacher t1, Teacher t2) {
	    return t1.compareTo(t2);
	  }

}
