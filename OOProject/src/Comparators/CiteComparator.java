package Comparators;
import java.util.Comparator;

import Researcher.ResearchPaper;

// TODO: Auto-generated Javadoc
/**
 * The Class CiteComparator.
 */
public class CiteComparator implements Comparator<ResearchPaper>{

	
	/**
	 * Compare.
	 *
	 * @param rp1 the rp 1
	 * @param rp2 the rp 2
	 * @return the int
	 */
	@Override
	  public int compare(ResearchPaper rp1, ResearchPaper rp2) {
	    return rp1.getNumOfCites().compareTo(rp2.getNumOfCites());
	  }

	
}
