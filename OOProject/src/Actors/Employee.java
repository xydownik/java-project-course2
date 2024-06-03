package Actors;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import Tools.*;
import Enums.*;
import Researcher.CanBeResearcher;
import SubSystems.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee. Child class of User. Specific fields - Vector of messages and scanner. 
 */
public class Employee extends User implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** Messages of the employee. Both sent and received. */
    protected Vector<Message> messages = new Vector<Message>();

    /**
     * Constructs an empty employee.
     */
    public Employee() {};
    
    /**
     * Constructs employee by super class' parameters.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     */
    public Employee(Key key, String name, String surname, String id) {
    	super(key, name, surname, id);
    }
    
    /**
     * Messaging.
     * </p>Scans the index of the employee from database.
     * Scans the message string to make a new message.
     * Clones the message. Adds clone to messages of the receiver employee and sets as "received".
     * Original message is added to current employee's messages and settled as "sent".</p>
     *
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public void messaging(Message m, Employee e) throws CloneNotSupportedException {
    	
        	
        	Message clon = (Message)m.clone();
        	clon.type = MessageType.RECEIVED;
        	e.messages.add(clon);
        	this.messages.add(m);
        	System.out.println(Translator.translate(" Added "));
        	
    		
    }
    
    
    /**
     * Method to act while logged in to user.
     * Includes menu options, which helps to organize all methods in the class.
     * </p>Have general parts: news, change password, change language, subscribe journal, log out.</p>
     * Have specific parts: messenger.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void logIn() throws IOException {
    	try {
			System.out.println(Translator.translate(" Welcome! "));
			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password \n"
						+ " 3) Change language \n 4) My journals \n 5) Messenger \n 6) Log out ");
				int choice = in.nextInt();
				if(choice==1){
						seeNews();
						Translator.printing("\n 1) Go back ");
						choice = in.nextInt();
						if(choice==1)continue main;
						break;
				}
				else if (choice==2){
					changePassword: while(true){
						System.out.println(Translator.translate("\n Enter new password: "));
						setPassword();
						Translator.printing(" 1) Change password \n 2) Go back ");
						choice = in.nextInt();
						if(choice==1) continue changePassword;
						if(choice==2) continue main;
						break;
					}
				}
				else if (choice==3){
				
					System.out.println(" 1) KZ \n 2) RU \n 3) EN ");
					choice = in.nextInt();
					if(choice==1) changeLang(Language.KZ);
					if(choice==2) changeLang(Language.RU);
					if(choice==3) changeLang(Language.EN);
					continue main; 

				}
				else if (choice==4){
						db.printData(db.journals);
						Translator.printing(" 1) Subscribe journal \n 2) Unsubscribe journal \n 3) Go back ");
						choice = in.nextInt();
						if(choice == 1)subscriptionJournal(1);
						if(choice == 2)subscriptionJournal(2);
						if(choice == 3)continue main; 
						break;
				}
				else if(choice == 5) {
					message:while(true) {
						System.out.println("\n 1) Write a message \n 2) Received messages \n 3) Sent Message \n 4) Go back ");
						choice = in.nextInt();
						if(choice == 1) {
							if(!db.employees.isEmpty()) {
								db.printData(db.employees);
								System.out.println(Translator.translate(" Choose an index "));
								int index = in.nextInt();

								Employee e = db.employees.get(index - 1);

								System.out.println(Translator.translate(" Enter line 'add' to stop messaging "));
								String newMes = "",mes ;
								mes = in.nextLine();
								while(!mes.equals("add")) {
									mes  = in.nextLine();
									newMes += mes;
								}
								Message m = new Message(newMes);
								messaging(m,e);
								continue message;
							}
							else {System.out.println(Translator.translate(" No data yet "));continue message;}
						}	
						if(choice == 2) {
							for(Message m : this.getMessages()) {
								if(m.type == MessageType.RECEIVED) {
									m.status = Status.SEEN;
									System.out.println(m);
								}
							}
							continue message;
						}
						if(choice == 3) {
							for(Message m : this.getMessages()) {
								if(m.type == MessageType.SENT) System.out.println(m);
							}
							continue message;
						}
						if(choice == 4) continue main;
						break;

					}
				}
				else if (choice==6){
					logOut();
					break main;
				}
			}
		 }catch (Exception e) {
			System.err.println(" Something bad happened... \n Saving resources...");
			e.printStackTrace();
			save();
		}
    }


    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return super.toString();
    }


    /**
     * Sets the messages.
     *
     * @param messages the new messages
     */
    public void setMessages(Vector<Message> messages) {
    	this.messages = messages;
    }
    
    /**
     * Gets the messages.
     *
     * @return the messages
     */
    public Vector<Message> getMessages() {
        return messages;
    }
}

