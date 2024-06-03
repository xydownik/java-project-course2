package Tools;

import java.io.Serializable;

import Enums.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message implements Cloneable,  Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The status. */
    public Status status;

    /** The message. */
    public String message;

    /** The type. */
    public MessageType type;
    
    /**
     * Instantiates a new message.
     *
     * @param message the message
     */
    public Message( String message) {
    	this.status = Status.UNSEEN;
    	this.message = message;
    	this.type = MessageType.SENT;
    }
    
    /**
     * Clone.
     *
     * @return the object
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public Object clone() throws CloneNotSupportedException{
    	Message m = (Message) super.clone();
    	m.message = message;
    	m.status = status;
    	m.type = type;
    	return m;
    }
    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return status + " -- " + message + " -- " + type;
    }
}

