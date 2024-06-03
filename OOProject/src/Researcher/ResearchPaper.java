package Researcher;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import Actors.User;
import Enums.Format;

// TODO: Auto-generated Javadoc
/**
 * The Class ResearchPaper.
 */
public class ResearchPaper implements Comparable<ResearchPaper>, Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cites. */
    private Vector<String> cites;

    /** The authors. */
    private Vector<User> authors;

    /** The date. */
    private Date date;

    /** The title. */
    private String title;

    /** The pages. */
    private Integer pages;

    /** The format. */
    private Format format;

    /**
     * Plaintext converter.
     *
     * @return the string
     */
    public String plaintextConverter() {
        String authorsString = authors.stream()
            .map(User::getName)
            .collect(Collectors.joining(", "));
        return "Title: " + title + "\nAuthors: " + authorsString + "\nDate: " + date;
    }
    
    /**
     * Bibtex converter.
     *
     * @return the string
     */
    public String bibtexConverter() {
        String authorsString = authors.stream()
            .map(User::getName)
            .collect(Collectors.joining(" and "));
        return "@article{" + title.replaceAll("\\s","") + ",\n" +
               "  author = {" + authorsString + "},\n" +
               "  title = {" + title + "},\n" +
               "  year = {" + date + "}\n" +
               "}";
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "ResearchPaper{" +
               "title='" + title + '\'' +
               ", authors=" + authors +
               ", date=" + date +
               ", pages=" + pages +
               '}';
    }

	/**
	 * Gets the cites.
	 *
	 * @return the cites
	 */
	public Vector<String> getCites() {
		return cites;
	}

	/**
	 * Sets the cites.
	 *
	 * @param cites the new cites
	 */
	public void setCites(Vector<String> cites) {
		this.cites = cites;
	}
	
	/**
	 * Gets the num of cites.
	 *
	 * @return the num of cites
	 */
	public Integer getNumOfCites() {
		return cites.size();
	}

	

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(ResearchPaper o) {
		if(this.pages>o.pages)return 1;
		else if (this.pages == o.pages)return 0;
		else return -1;
	}
	
	
}

