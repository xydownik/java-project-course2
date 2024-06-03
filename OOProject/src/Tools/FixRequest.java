package Tools;

import java.io.Serializable;

import Actors.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FixRequest.
 */
public class FixRequest extends Request implements  Serializable {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The from. */
	private Employee from;
	
	/** The to. */
	private TechSpecialist to;
    /** The room. */
    private Integer room;

    /** The text. */
    private String text;

    /**
     * Instantiates a new fix request.
     */
    public FixRequest() {
    	
    }
    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return null;
    }

	/**
	 * Gets the room.
	 *
	 * @return the room
	 */
	public Integer getRoom() {
		return room;
	}

	/**
	 * Sets the room.
	 *
	 * @param room the new room
	 */
	public void setRoom(Integer room) {
		this.room = room;
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
	 * Gets the from.
	 *
	 * @return the from
	 */
	public Employee getFrom() {
		return from;
	}
	
	/**
	 * Sets the from.
	 *
	 * @param from the new from
	 */
	public void setFrom(Employee from) {
		this.from = from;
	}
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public TechSpecialist getTo() {
		return to;
	}
	
	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(TechSpecialist to) {
		this.to = to;
	}
}

