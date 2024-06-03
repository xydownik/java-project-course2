package Researcher;

import java.io.*;
import java.util.*;
import Comparators.*;
import Actors.*;
import Enums.*;
import SubSystems.*;
import Tools.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Researcher.
 */
public class Researcher extends Employee implements CanBeResearcher, Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user. */
    private CanBeResearcher user = null;

    /** The projects. */
    public Vector<ResearchProject> projects = new Vector<ResearchProject>();

    /** The papers. */
    public Vector<ResearchPaper> papers = new Vector<ResearchPaper>();

    /**
     * Prints the papers.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     */
    
    public Researcher(Key key, String name, String surname, String id) {
    	super(key,name,surname,id);
    }
    
    /**
     * Instantiates a new researcher.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     * @param r the r
     */
    public Researcher(Key key, String name, String surname, String id, CanBeResearcher r) {
    	super(key,name,surname,id);
    	user = r;
    }
    
    /**
     * Prints the papers.
     *
     * @param c the c
     */
    public void printPapers(Comparator<ResearchPaper> c) {
    	@SuppressWarnings("unchecked")
		Vector<ResearchPaper> ans = (Vector<ResearchPaper>) papers.clone();
    	Collections.sort(ans,c);
    	System.out.println(ans);
    }

    /**
     * Gets the citations.
     *
     * @param f the f
     * @return the citations
     */
    public void getCitations(Format f) {
       if(f == Format.BIBTEX) {
    	   for(ResearchPaper rp : papers) {
				System.out.println(rp.bibtexConverter());
				
			}
       }
       else {
    	   for(ResearchPaper rp : papers) {
				System.out.println(rp.plaintextConverter());
			}
       }
       
    }
    /**
     * Sort papers.
     *
     * @param c the c
     */
    public void sortPapers(Comparator<ResearchPaper> c) {
    	Collections.sort(papers,c);
    }

    /**
     * Calc H index.
     *
     * @return the int
     */
    public int calcHIndex() {
    	Vector<ResearchPaper> rps = (Vector<ResearchPaper>) papers.clone();
    	Collections.sort(rps, new CiteComparator());
        Vector<Integer> v = new Vector<Integer>();
        for(ResearchPaper rp : rps) {
        	v.add(rp.getCites().size());
        }
        for(int i=0;i<rps.size();i++) {
        	for(int j =0; j< v.size();j++) {
        		if(i == j)return i;
        		else if(i>j && i< j+1)return i;
        	}
        }
		return 0;
    }
    
    /**
     * Log in.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void logIn() throws IOException {
    	try {
			System.out.println(Translator.translate(" Welcome! "));
			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password \n"
						+ " 3) Change language \n 4) My journals \n 5) Messenger \n 6) Get citations \n 7) Print papers \n"
						+ " 8) Calculate H-index \n 9) Log out ");
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
				else if (choice == 6) {
					if(!papers.isEmpty()) {
						System.out.println(Translator.translate(" Choose a format "));
						Translator.printing(" 1) BIBTEX \n 2) Plaintext ");
						choice = in.nextInt();
						if(choice == 1) {
							getCitations(Format.BIBTEX);
							continue main;
						}
						else {
							getCitations(Format.PLAIN_TEXT);
							continue main;
						}
					}
					else System.out.println(Translator.translate(" No data yet "));
					continue main;
				}
				else if (choice == 7) {
					System.out.println(Translator.translate(" Sorted by citations "));
					printPapers(new CiteComparator());
					continue main;
				}
				else if(choice == 8) {
					System.out.println(Translator.translate(" Your H-index "));
					System.out.println(calcHIndex());
					continue main;
				}
				else if (choice==9){
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
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(CanBeResearcher user) {
    	this.user = user;
    }
    
    
    /**
     * Gets the user.
     *
     * @return the user
     */
    public CanBeResearcher getUser() {
    	if(user != null) {
    		return user;
    	}
    	System.out.println(Translator.translate(" Externe "));
    	return this;
    }
}

