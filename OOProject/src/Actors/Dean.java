package Actors;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import Enums.Language;
import Enums.MessageType;
import Enums.Status;
import SubSystems.Translator;
import Tools.*;
import Comparators.*;
// TODO: Auto-generated Javadoc
/**
 * The Class Dean.
 */
public class Dean extends Employee implements DealerWithRequest, Serializable {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The year. */
	private Integer year;
    /** The complaints. */
    private List<Complaint> complaints = new ArrayList<Complaint>();

    /**
     * Instantiates a new dean.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     */
    public Dean(Key key, String name ,String surname, String id) {
    	super(key, name, surname, id);
    	
    }
    {
    	for(Request r : db.allRequests) {
    		if(r instanceof Complaint)complaints.add((Complaint)r);
    	}
    }
    /**
     * See.
     *
     * @return the string
     */
    public void seeAll() {
    	for(Complaint c : complaints) {
    		c.setStatus(Status.SEEN);
    	}
    	Collections.sort(complaints, new RequestComparator());
		db.printData(complaints);
    }

    /**
     * Accept.
     *
     * @param r the r
     */
    public void accept(Request r) {
    	Complaint c = (Complaint)r;
    	c.setStatus(Status.ACCEPTED);
    }

    /**
     * Reject.
     *
     * @param r the r
     */
    public void reject(Request r ) {
    	Complaint c = (Complaint)r;
    	c.setStatus(Status.ACCEPTED);
    	
    }
    
    /**
     * Log in.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void logIn()throws IOException {
    	try {
			System.out.println(Translator.translate(" Welcome! "));
			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password \n"
						+ " 3) Change language \n 4) My journals \n 5) Messenger \n 6) Manage complaints \n 7) Log out ");
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
				else if(choice == 6) {
					seeAll();
					System.out.println(Translator.translate(" Choose a complaint to handle "));
					int index = in.nextInt();
					Complaint c = complaints.get(index-1);
					Translator.printing(" 1) Accept \n 2) Reject 3) Go back ");
					choice = in.nextInt();
					if(choice == 1)accept(c);
					if(choice == 1)reject(c);
					else continue main; break; 
				}
				else if (choice==7){
					logOut();
					break main;
				}
			}
		} catch (Exception e) {
			System.err.println(" Something bad happened... \n Saving resources...");
			e.printStackTrace();
			save();
		}
    }

	/**
	 * Gets the complaints.
	 *
	 * @return the complaints
	 */
	public List<Complaint> getComplaints() {
		return complaints;
	}

}