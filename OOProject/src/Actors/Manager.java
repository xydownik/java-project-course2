package Actors;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import Enums.*;
import Exceptions.RegistrationIsNotAllowed;
import Tools.*;
import SubSystems.*;
import Education.Course;
import Comparators.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Manager.
 */
public class Manager extends Employee implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
    private ManagerType type;

    /** The requests. */
    private Vector<Request> requests;
    
    

    /**
     * Instantiates a new manager.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     * @param type the type
     */
    public Manager(Key key, String name, String surname, String id, ManagerType type) {
    	super(key, name, surname, id);
    	this.type = type;
    }
    /**
     * Assign course.
     *
     * @param c the c
     * @param t the t
     */
    public void assignCourse(Course c, Teacher t) {
    	c.teachers.add(t);
    	System.out.println(t.getName() + Translator.translate(" added to ") + c.name);
    }

    /**
     * Manage requests.
     */
    public void manageRequests() {
    	for(Request r : requests){
    		if(r instanceof Complaint) {
    			Complaint c = (Complaint)r;
    			c.setStatus(Status.REFERRED);
    			c.getTo().getComplaints().add(c);
    			requests.remove(r);
    		}
    		if(r instanceof FixRequest) {
    			FixRequest fr = (FixRequest)r;
    			fr.setStatus(Status.REFERRED);
    			fr.getTo().getRequests().add(fr);
    			requests.remove(r);
    		}
    		else {
    			AddDropRequest adr = (AddDropRequest)r;
    			if(adr.type == AddDropType.ADD && !adr.course.students.contains(adr.student)) {
    				adr.setStatus(Status.ACCEPTED);
    				adr.course.students.add(adr.student);
    				requests.remove(r);
    			}
    			else if(adr.type == AddDropType.DROP && adr.course.students.contains(adr.student)) {
    				adr.setStatus(Status.ACCEPTED);
    				adr.course.students.remove(adr.student);
    				requests.remove(r);
    			}
    			else {
    				adr.setStatus(Status.REJECTED);
    				requests.remove(r);
    			}
    		}
    	}
    }

    /**
     * Confirm registration.
     *
     * @param s the s
     * @param c the c
     * @return the boolean
     * @throws RegistrationIsNotAllowed the registration is not allowed
     */
    public Boolean confirmRegistration(Student s, Course c) throws RegistrationIsNotAllowed {
    	if(db.coursesForRegistration.get(s.getFaculty()).contains(c) &&
    			!s.getCourses().contains(c)) {return s.getCourses().add(c);}
        else throw new RegistrationIsNotAllowed(" Registration is not allowed ");
    	
    }

    /**
     * View info teachers.
     *
     * @param comp the comp
     * @return the string
     */
   
    public void viewInfoTeachers(Comparator<Teacher> comp) {
    	@SuppressWarnings("unchecked")
		Vector<Teacher> t = (Vector<Teacher>) db.teachers.clone();
    	Collections.sort(t,comp);
    	db.printData(t);
    }

    /**
     * View info students.
     *
     * @param comp the comp
     * @return the string
     */
    public void viewInfoStudents(Comparator<Student> comp) {
    	@SuppressWarnings("unchecked")
		Vector<Student> s = (Vector<Student>) db.students.clone();
    	
    	Collections.sort(s,comp);
        db.printData(s);
    }

    /**
     * Manage news.
     *
     * @param command the command
     * @param n the n
     */
    public void manageNews(Command command, News n) {
    	db.news.add(n);
    }

    /**
     * Log in.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void logIn() throws IOException{
    	try {
			System.out.println(Translator.translate(" Welcome! "));
			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password \n"
						+ " 3) Change language \n 4) My journals \n 5) Messenger \n 6) Assign course \n"
						+ " 7) Manage requests \n 8) View info about teachers \n 9) View info about students \n"
						+ " 10) Manage news \n 11) Add courses for registration \n 12) Close registration \n 13) Add course \n 14) Remove Course \n 15) Log out ");
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
					assign:while(true) {
						if(!db.teachers.isEmpty() && !db.courses.isEmpty()) {
							System.out.println(Translator.translate(" Choose a course "));
							db.printData(db.courses);
							int index = in.nextInt();
							Course c = db.courses.get(index-1);
							System.out.println(Translator.translate(" Choose a teacher "));
							db.printData(db.teachers);
							int index2 = in.nextInt();
							Teacher t = db.teachers.get(index2-1);
							assignCourse(c,t);
						}
						else System.out.println(Translator.translate(" No info about teachers/courses "));
						
						Translator.printing(" 1) Assign more \n 2) Go back ");
						choice = in.nextInt();
						if(choice == 1) continue assign;
						else if(choice == 2)continue main; break assign;
					}
				}
				else if(choice == 7) {
					request:while(true) {
						if(!requests.isEmpty()) {
							db.printData(requests);
							Translator.printing(" 1) Handle requests \n 2) Go back ");
							choice = in.nextInt();
							if(choice == 1) {
								manageRequests();
							}
							else continue main;break request;
						}
						else System.out.println(Translator.translate(" No requests yet "));
						continue main;
					}	
				}
				else if(choice == 8) {
					infoTeach:while(true) {
						if(!db.teachers.isEmpty()) {
							db.printData(db.teachers);
							Translator.printing(" 1) Sort by name \n 2) Sort by rank \n 3) Go back ");
							choice = in.nextInt();
							if(choice == 1) {
								viewInfoTeachers(new NameComparator());
								continue main;
							}
							else if(choice == 2) {
								viewInfoTeachers(new RankComparator());
								continue main;
							}
							else if (choice ==3)continue main; break infoTeach;
						}
					}
				}
				else if (choice ==9) {
					infoStud:while(true) {
						if(!db.teachers.isEmpty()) {
							db.printData(db.teachers);
							Translator.printing(" 1) Sort by name \n 2) Sort by GPA \n 3) Go back ");
							choice = in.nextInt();
							if(choice == 1) {
								viewInfoStudents(new NameComparatorStud());
								continue main;
							}
							else if(choice == 2) {
								viewInfoStudents(new GpaComparator());
								continue main;
							}
							else if (choice ==3)continue main; break infoStud;
						}
					}
				}
				else if(choice ==10) {
					if(type == ManagerType.DEPARTMENT) {
						String title,text; Priority p;
						System.out.println(Translator.translate(" Enter a title "));
						title = in.next();
						System.out.println(Translator.translate(" Enter the main text(line 'add' to stop)"));
						text = in.nextLine();
						while(text!="add") {
							text += in.nextLine();
						}
						System.out.println(Translator.translate(" Choose priority "));
						Translator.printing(" 1) HIGH \n 2) LOW ");
						choice = in.nextInt();
						if(choice ==1) {
							p = Priority.HIGH;
						}
						else p = Priority.LOW;
						News n = new News(title, new Date(),text,p);
						db.news.add(n);
						Collections.sort(db.news,new NewsComparator());
						System.out.println(Translator.translate(" Successfully added "));
						continue main;
					}
					else System.out.println(Translator.translate(" Only for department managers "));
					continue main;
				}
				else if(choice == 11) {
					reg:while(true) {
						if(!db.courses.isEmpty()) {
							System.out.println(Translator.translate(" Choose a course "));
							db.printData(db.courses);
							int index = in.nextInt();
							Course c = db.courses.get(index-1);
							Faculty f;
							System.out.println(Translator.translate(" Choose a faculty "));
							System.out.println(" 1) SITE \n 2) KMA \n 3) BS \n 4) SAM \n 5) SCE \n 6) ISE \n"
									+ " 7) SMSGT \n 8) SNSS \n 9) SG \n 10) SEOGI ");
							choice = in.nextInt();
							if(choice == 1) f = Faculty.SITE;
							if(choice == 2) f = Faculty.KMA;
							if(choice == 3) f = Faculty.BS;
							if(choice == 4) f = Faculty.SAM;
							if(choice == 5) f = Faculty.SCE;
							if(choice == 6) f = Faculty.ISE;
							if(choice == 7) f = Faculty.SMSGT;
							if(choice == 8) f = Faculty.SNSS;
							if(choice == 9) f = Faculty.SG;
							else f = Faculty.SEOGI;
							if(db.coursesForRegistration.containsKey(f)) {
								db.coursesForRegistration.get(f).add(c);
							}
							else {
								Vector<Course> cr = new Vector<Course>();
								cr.add(c);
								db.coursesForRegistration.put(f, cr);
							}
							System.out.println(db.coursesForRegistration);
							System.out.println(Translator.translate(" Added "));
							Translator.printing(" 1) Add more \n 2) Go back ");
							choice = in.nextInt();
							if(choice == 1)continue reg;
							else if(choice == 2)continue main; break reg;
						}
						System.out.println(Translator.translate(" No data "));
						continue main;
					}
				}
				else if(choice == 12) {
					System.out.println(Translator.translate(" Are you sure you want to close registration? "));
					Translator.printing(" 1) Yes \n 2) No ");
					choice = in.nextInt();
					if(choice == 1) {
						db.coursesForRegistration.clear();
						System.out.println(Translator.translate(" Closed "));
						continue main;
					}
					else if(choice == 2)continue main;
				}
				else if(choice == 13) {
					System.out.println(Translator.translate(" Enter a name "));
					String name = in.next();
					System.out.println(Translator.translate(" Choose a Course Type "));
					Translator.printing(" 1) Major \n 2) Minor \n 3) Free elective ");
					choice = in.nextInt();
					CourseType ct;
					if(choice==1) ct = CourseType.MAJOR;
					if(choice == 2)ct = CourseType.MINOR;
					else if(choice == 3) ct = CourseType.FREE_ELECTIVE;
					else ct = CourseType.FREE_ELECTIVE;
					System.out.println(Translator.translate(" Enter number of credits "));
					Integer credit = in.nextInt();
					System.out.println(Translator.translate(" Enter limit of places "));
					int lp = in.nextInt();
					Vector<Course> pre = new Vector<Course>();
					System.out.println(Translator.translate(" Add prerequisites? "));
					Translator.printing(" 1) Yes \n 2) No ");
					choice = in.nextInt();
					if(choice == 1 && !db.courses.isEmpty()) {
						db.printData(db.courses);
						System.out.println(Translator.translate(" Enter index(0 to stop) "));
						int inx= in.nextInt();
						while(inx != 0) {
							pre.add(db.courses.get(inx-1));
							inx = in.nextInt();
							
						}
						Course c = new Course(ct,credit, lp ,name, pre);
						db.courses.add(c);
						System.out.println(Translator.translate(" Created "));
						continue main; 
					}
					else if(choice == 2 || (db.courses.isEmpty() && choice == 1)) {
						if(db.courses.isEmpty())System.out.println(Translator.translate(" No prerequisite disciplines "));
						Course c = new Course(ct,credit, lp ,name, pre);
						db.courses.add(c);
						System.out.println(Translator.translate(" Created "));
						continue main;
					}
					
				}
				else if(choice ==14) {
					if(!db.courses.isEmpty()) {
						db.printData(db.courses);
						System.out.println(Translator.translate(" Choose an index "));
						int index = in.nextInt();
						Course c = db.courses.get(index-1);
						db.courses.remove(c);
					}
					else {
						System.out.println(Translator.translate(" No data yet "));
					}
					continue main;
				}
				else if (choice==15){
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
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return super.toString() + Translator.translate(" Manager Type: " )+ type;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public ManagerType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param t the new type
     */
    public void setType(ManagerType t) {
    	type = t;
    }

   

    /**
     * Adds the course for reg.
     *
     * @param f the f
     * @param c the c
     */
    public void addCourseForReg(Faculty f, Course c) {
    }
    
    /**
     * Gets the requests.
     *
     * @return the requests
     */
    public Vector<Request> getRequests(){
    	return requests;
    }
}

