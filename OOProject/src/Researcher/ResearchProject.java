package Researcher;

import java.io.Serializable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class ResearchProject.
 */
public class ResearchProject implements Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The members. */
    private Vector<Researcher> members;

    /** The title. */
    private String title;

    /** The papers. */
    private Vector<ResearchPaper> papers;

    /**
     * Instantiates a new research project.
     */
    public ResearchProject() {papers = new Vector<ResearchPaper>(); }
    
    /**
     * Instantiates a new research project.
     *
     * @param title the title
     * @param papers the papers
     */
    public ResearchProject(String title, Vector<ResearchPaper> papers) {
    	this.title = title;
    	this.papers = papers;
    }
    
    /**
     * Check member.
     *
     * @param r the r
     * @return the boolean
     */
    public Boolean checkMember(Researcher r) {
    	if(members.contains(r))return true;
    	return false;
    }

    /**
     * Adds the member.
     *
     * @param r the r
     * @return the boolean
     */
    public Boolean addMember(Researcher r ) {
    	members.add(r);
    	return true;
    }

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the papers.
	 *
	 * @return the papers
	 */
	public Vector<ResearchPaper> getPapers() {
		return papers;
	}

	/**
	 * Sets the papers.
	 *
	 * @param papers the new papers
	 */
	public void setPapers(Vector<ResearchPaper> papers) {
		this.papers = papers;
	}
}

