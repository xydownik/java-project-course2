package Tools;

import java.io.Serializable;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key implements  Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The login. */
    private String username;

    /** The password. */
    private String password;

    /**
     * Instantiates a new key.
     */
    public Key() {};
    
    /**
     * Instantiates a new key.
     *
     * @param username the username
     * @param password the password
     */
    public Key(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    public boolean equals(Object o) {
        if(this == o)return true;
        if(this.getClass()!=o.getClass())return false;
        Key k = (Key)o;
        return this.username.equals(k.username) && this.password.equals(k.password);
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    public int hashCode() {
    	return Objects.hash(username, password);
    }

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param login the new username
	 */
	public void setUsername(String login) {
		this.username = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}

