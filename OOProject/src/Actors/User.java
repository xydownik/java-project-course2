package Actors;

import Tools.*;
import Tools.Observer;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import Enums.*;
import SubSystems.*;

// TODO: Auto-generated Javadoc
/**
 * The Abstract Class User.
 */
public abstract class User implements Comparable<User>, Observer, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The db. */
	public /** The db. */
	Database db = Database.DATABASE;
	
	/** The in. */
	protected static Scanner in = new Scanner(System.in);

	/** The key. */
	private Key key;

	/** The name. */
	private String name;

	/** The id. */
	private String id;

	/** The surname. */
	private String surname;
	

	/** Constructs empty random user. */
	public User() {
		key = new Key();
		name = "No name";
		surname = "No surname";
		id = "00Z000000";
	}

	/**
	 *  Constructs user by all fields.
	 *
	 * @param key the key
	 * @param name the name
	 * @param surname the surname
	 * @param id the id
	 */
	public User(Key key, String name, String surname, String id) {
		this.key = key;
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	/**
	 *  Saves all changes made by current user.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save() throws IOException {
		Database.writeData();
	}

	/**
	 * Log out.
	 * 
	 * @return the boolean
	 */
	protected void logOut() throws ClassNotFoundException {
		
		try {
			save();
			LoginApp.main(null);
			System.out.println(Translator.translate(" See you next time! "));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changes language.
	 * 
	 * @param l the language from enumeration Language.
	 *          </p>
	 *          The language the system have to be set to.
	 */
	public void changeLang(Language l) {
		Translator.setToLanguage(l);
	}

	/**
	 * See news.
	 * 
	 * @return the string
	 */
	public String seeNews() {
		return db.news.toString();
	}

	/**
	 * Manages journal subscription details.
	 * </p>
	 * is allowed for all child classes of Actors.User.
	 * </p>
	 *
	 * @param choice the choice
	 * @return true if operation completed, false otherwise.
	 */
	public void subscriptionJournal(int choice) {
		System.out.println("Enter the number of Journal: ");
		int index = in.nextInt();
		Vector<Journal> v = db.journals;

		if (v.isEmpty())
			System.out.println(" No Journals yet... ");
		;

		for (int i = 0; i < v.size(); i++) {
			if (i + 1 == index) {
				if (choice == 1) {
					v.get(i).addObserver(this);
					System.out.println(" Subscribed ");
					return;
				} else
					v.get(i).removeObserver(this);
				System.out.println(" Unsubscribed ");
				return;
			}
		}
		System.out.println(" Couldn't find the journal ");
		return;
	}

	/**
	 * Sets the password by the key. Gets new password string from user input.
	 * Prints text if successfully finished operation.
	 */
	public void setPassword() {
		String p = in.next();
		key.setPassword(p);
		System.out.println("Successfully changed!");
	}

	/**
	 * Abstract method to show the menu interface.
	 * </p>
	 * Every child have to implement it by adding specific options.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void logIn() throws IOException;

	/**
	 * Equals. Checks by parameters: id, name, surname.
	 * 
	 * @param o the object to check the equality.
	 * @return true, if successful
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (this.getClass() != o.getClass())
			return false;
		User u = (User) o;
		return this.id.equals(u.id) && this.name.equals(u.name) && this.surname.equals(u.surname);
	}

	/**
	 * Hash code.
	 *
	 * @return the hash code by id, name, surname.
	 */
	public int hashCode() {
		return Objects.hash(id, name, surname);
	}

	/**
	 * To string.
	 *
	 * @return string of name, surname, id.
	 */
	public String toString() {
		return Translator.translate(" Name: ") + name + 
				Translator.translate(" Surname: ")+ surname + Translator.translate(" ID: ") + id;
	}

	/**
	 * Compare to.
	 *
	 * @param u the User to compare with.
	 * @return integer -1(smaller)/0(equal)/1(greater), to sort. 
	 */
	public int compareTo(User u) {
		int ans = this.name.compareTo(u.name);
		if (ans == 0) {
			int ans2 = this.surname.compareTo(u.surname);
			if (ans2 == 0)
				return this.id.compareTo(u.id);
			return ans2;
		}
		return ans;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return key.getUsername();
	}
}