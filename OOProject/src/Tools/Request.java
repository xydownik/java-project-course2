package Tools;

import Enums.*;

import java.io.Serializable;
import java.lang.*;

import Actors.Admin;
import Actors.Dean;
import Actors.Teacher;

// TODO: Auto-generated Javadoc
/**
 * The Class Request.
 */
public abstract class Request implements Requestable, Comparable<Request>, Serializable{
    
	
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The status. */
    private Status status;

    /** The urgency. */
    private Urgency urgency;

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "Urgency: " + urgency + " Status: " + status;
    }

    /**
     * Confirm.
     */
    public void confirm() {
    	this.setStatus(Status.ACCEPTED);
    }

    /**
     * Reject.
     */
    public void reject() {
    	this.setStatus(Status.REJECTED);
    }

	/**
	 * Gets the urgency.
	 *
	 * @return the urgency
	 */
	public Urgency getUrgency() {
		return urgency;
	}

	/**
	 * Sets the urgency.
	 *
	 * @param urgency the new urgency
	 */
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Compare to.
	 *
	 * @param r the r
	 * @return the int
	 */
	public int compareTo(Request r) {
		if(this.urgency == Urgency.CRITICAL && r.urgency!=this.urgency)return 1;
		if(this.urgency == Urgency.HIGH && r.urgency!=this.urgency 
				&& r.urgency!=Urgency.CRITICAL)return 1;
		if(this.urgency!=Urgency.MEDIUM && r.urgency == Urgency.LOW)return 1;
		if(this.urgency == r.urgency)return 0;
		else return -1;
	}

	
}

