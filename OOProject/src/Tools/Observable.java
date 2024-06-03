package Tools;

import java.io.Serializable;
import java.util.Vector;

import Tools.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Observable.
 */
public abstract class Observable implements Observer, Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The observers. */
    private Vector<Observer> observers;

    /**
     * Adds the observer.
     *
     * @param o the o
     */
    public void addObserver(Observer o) {
    }

    /**
     * Removes the observer.
     *
     * @param o the o
     */
    public void removeObserver(Observer o) {
    }

    /**
     * Notify observers.
     *
     * @return true, if successful
     */
    public boolean notifyObservers() {
        return false;
    }
}

