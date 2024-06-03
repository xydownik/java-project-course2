package Comparators;

import java.util.Comparator;

import Actors.Student;
import SubSystems.News;

// TODO: Auto-generated Javadoc
/**
 * The Class NewsComparator.
 */
public class NewsComparator implements Comparator<News> {
	
	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	
	@Override
	public int compare(News o1, News o2) {
		return o1.compareTo(o2);
	}

}
