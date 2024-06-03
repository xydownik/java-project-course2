package Comparators;

import java.util.Comparator;

import Tools.Request;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestComparator.
 */
public class RequestComparator implements Comparator<Request>{

	
	/**
	 * Compare.
	 *
	 * @param r1 the r 1
	 * @param r2 the r 2
	 * @return the int
	 */
	@Override
	  public int compare(Request r1, Request r2) {
	    return r1.compareTo(r2);
	  }
}
