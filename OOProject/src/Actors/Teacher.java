package Actors;

import Enums.*;
import Researcher.CanBeResearcher;
import SubSystems.Database;
import SubSystems.Translator;
import Tools.Complaint;
import Tools.Key;
import Tools.Message;
import Tools.Request;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import Education.Course;
import Education.Lesson;
import Education.Mark;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher.
 *
 * @see new value
 * @see new value
 */
public class Teacher extends Employee implements CanBeResearcher, Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


    /** The schedule. */
    public Vector<Lesson> schedule;

    /** The qualification. */
    public Rank rank;

    /** The my requests. */
    private Vector<Request> myRequests;

    /** The rates. */
    private Vector<Integer> rates;
    
    /**
     * View students.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     * @param rank the rank
     * @return the string
     */
    public Teacher(Key key, String name, String surname, String id, Rank rank) {
    	super(key, name, surname, id);
    	this.rank = rank;
    }
    
    /**
     * View students.
     *
     * @return the string
     */
    public void viewStudents(){
    	db.printData(getStudents());
    }

    
    /**
     * View info students.
     *
     * @param id the id
     * @return the string
     */
    public String viewInfoStudent(String id) {
    	Student s = (Student)Database.DATABASE.students.stream().filter(n->n.getId().equals(id));
        return s.toString();
    }

    /**
     * Put marks.
     *
     * @param att1 the att 1
     * @param att2 the att 2
     * @param finall the finall
     * @param ids the ids
     */
    public void putMarks(Double att1, Double att2, Double finall, String ids){
    	Mark m = new Mark(att1,att2,finall,ids);
    	for(Course c : getCourses()) {
    		for(Student s : c.students) {
    			if(s.getId().equals(ids)) {
    				c.marks.add(m);
    				return;
    			}
    		}
    	}
    }

    /**
     * Write complaint.
     *
     * @param m the m
     * @param c the c
     */
    public void writeComplaint(Manager m, Complaint c) {
    	m.getRequests().add(c);
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
						+ " 3) Change language \n 4) My journals \n 5) Messenger \n "
						+ "6) View students \n 7) Put Marks \n 8) Write complaint \n 9) Log out ");
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
					
					viewStudents();
					continue main;
				}
				else if(choice == 7) {
					putMarks:while(true) {
						if(!getCourses().isEmpty() && !getStudents().isEmpty()) {
							Double att1=0.0,att2=0.0,finall=0.0;
							for(Course c : getCourses()) {
								for(Student s : getStudents()) {
									if(c.students.contains(s)) {
										System.out.println(c.name);
										System.out.println(s);
									}
								}
							}
							System.out.println(Translator.translate(" Enter student's ID "));
							String ids = in.next();
							System.out.println(Translator.translate(" Enter 1st Attestation "));
							att1 = in.nextDouble();
							System.out.println(Translator.translate(" Enter 2nd Attestation "));
							att2 = in.nextDouble();
							System.out.println(Translator.translate(" Enter Final "));
							finall = in.nextDouble();
							putMarks(att1,att2,finall,ids);
							Translator.printing(" 1) Put more marks \n 2) Go back ");
							choice = in .nextInt();
							if(choice == 1)continue putMarks;
							else continue main;
						}
						else System.out.println(Translator.translate(" No data yet "));continue main;
					}
						
				}
				else if(choice  == 8) {
					writeComp:while(true) {
						db.printData(getStudents());
						System.out.println(Translator.translate(" Enter student's index "));
						int index = in.nextInt();
						Student s = getStudents().get(index-1);
						
						db.printData(db.deans);
						System.out.println(Translator.translate(" Enter Dean's index "));
						index = in.nextInt();
						Dean d  = db.deans.get(index-1);
						
						db.printData(db.managers);
						System.out.println(Translator.translate(" Enter Manager's index "));
						index = in.nextInt();
						Manager m = db.managers.get(index-1);
						
						System.out.println(Translator.translate(" Enter the main text(line 'add' to stop)"));
						String text = in.nextLine();
						while(text!="add") {
							text += in.nextLine();
						}
						
						Complaint c = new Complaint(s,text,d,this);
						m.getRequests().add(c);
						System.out.println(Translator.translate(" Added "));
						Translator.printing(" 1) Write complaint \n 2) Go back ");
						choice = in.nextInt();
						if(choice == 1) continue writeComp;
						else continue main;
						
					}
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
     * Gets the schedule.
     *
     * @return the schedule
     */
    public Vector<Lesson> getSchedule() {
        return schedule;
    }


    /**
     * Gets the qualification.
     *
     * @return the qualification
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets the schedule.
     *
     * @param s the new schedule
     */
    public void setSchedule(Vector<Lesson> s) {
    	this.schedule = s;
    	
    }


    /**
     * Gets the students.
     *
     * @return the students
     */
    public Vector<Student> getStudents() {
    	Vector<Student> v = new Vector<>();
    	for(Course c : getCourses()) {
    		for(Student s : c.students) {
    			v.add(s);
    		}
    	}
    	return v;
    }
    
    /**
     * Gets the courses.
     *
     * @return the courses
     */
    public Vector<Course> getCourses() {
    	Vector<Course> v = new Vector<>();
    	for(Course c : db.courses) {
    		if(c.teachers.contains(this)) {
    			v.add(c);
    		}
    	}
    	return v;
    }

    /**
     * Compare to.
     *Compares by qualification
     * @param t the t
     * @return the int
     */
    public int compareTo(Teacher t) {
        if(this.rank ==Rank.PROFESSOR && t.rank != Rank.PROFESSOR)return 1;
        if(this.rank == Rank.SENIOR_LECTURER && 
        		t.rank != Rank.PROFESSOR &&
        		t.rank != Rank.SENIOR_LECTURER)return 1;
        if(this.rank == Rank.LECTURER &&
        		t.rank != Rank.PROFESSOR && 
        		t.rank != Rank.SENIOR_LECTURER)return 1;
        if(this.rank == Rank.PRACTICE_TEACHER && 
        		t.rank == Rank.TUTOR)return 1;
        if(this.rank == t.rank)return 0;
        return -1;
    }

    /**
     * Gets the my requests.
     *
     * @return the my requests
     */
    public Vector<Request> getMyRequests() {
        return myRequests;
    }

	/**
	 * Sets the my requests.
	 *
	 * @param myRequests the new my requests
	 */
	public void setMyRequests(Vector<Request> myRequests) {
		this.myRequests = myRequests;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return Translator.translate(" Name: ") + this.getName() + Translator.translate(" Rank: ") + this.rank
				+ Translator.translate(" USername: ") + this.getUsername();
	}

	/**
	 * Gets the rates.
	 *
	 * @return the rates
	 */
	public Vector<Integer> getRates(){
		return rates;
	}
	public int getRating() {
		int sum = 0;
		for(int i : rates) {
			sum+=i;
		}
		return sum/rates.size();
	}

	/**
	 * Sets the rates.
	 *
	 * @param rates the new rates
	 */
	public void setRates(Vector<Integer> rates) {
		this.rates = rates;
	}
}

