package SubSystems;

import java.io.Serializable;
import java.util.Date;

import Enums.Priority;

// TODO: Auto-generated Javadoc
/**
 * The Class News.
 */
public class News implements Comparable<News>,Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The title. */
    private String title;

    /** The date. */
    private Date date;

    /** The text. */
    private String text;

    /** The priority. */
    private Priority priority;

    /**
     * Instantiates a new news.
     *
     * @param title the title
     * @param date the date
     * @param text the text
     * @param p the p
     */
    public News(String title, Date date, String text, Priority p) {
    	this.title = title;
    	this.text = text;
    	this.date = date;
    	priority = p;
    }
   

    /**
     * Compare to.
     *
     * @param n the n
     * @return the integer
     */
    public int compareTo(News n) {
    	
        if(this.priority == Priority.HIGH &&n.priority == Priority.LOW)return 1;
        else if(this.priority==n.priority && this.date.compareTo(n.date)==1)return 1;
        else if(this.priority==n.priority && this.date.compareTo(n.date)==0)return 0;
        else return -1;
        
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
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}

