package Actors;

import java.io.*;
import java.util.Vector;

import Enums.*;
import Researcher.*;
import Tools.*;
import Education.*;
import Actors.*;
import Exceptions.*;
import SubSystems.*;
import Comparators.*;


// TODO: Auto-generated Javadoc
/**
 * The Class GraduateStudent.
 */
public class GraduateStudent extends Student implements CanBeTeacher, Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The diploma project. */
    public ResearchProject diplomaProject;

    /** The supervisor. */
    public Researcher supervisor;

    /**
     * Make diploma project.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     * @param degree the degree
     * @param faculty the faculty
     */
    
    public GraduateStudent(Key key, String name, String surname, String id, Degree degree, Faculty faculty) {
    	super(key, name, surname, id,degree,faculty);
    }
    
    /**
     * Instantiates a new graduate student.
     *
     * @param key the key
     * @param name the name
     * @param surname the surname
     * @param id the id
     * @param degree the degree
     * @param faculty the faculty
     * @param s the s
     */
    public GraduateStudent(Key key, String name, String surname, String id, Degree degree, Faculty faculty, Researcher s) {
    	super(key, name, surname, id,degree,faculty);
    	supervisor = s;
    }
    
    /**
     * Make diploma project.
     *
     * @return the research project
     */
    public ResearchProject addDiplomaProject() {
    	return new ResearchProject();
    }

    /**
     * Gets the diploma project.
     *
     * @return the diploma project
     */
    public ResearchPaper getDiplomaProject() {
        return null;
    }

    /**
     * Sets the diploma project.
     *
     * @param rp the new diploma project
     */
    public void setDiplomaProject(ResearchPaper rp) {
    }

    /**
     * Gets the supervisor.
     *
     * @return the supervisor
     */
    public Researcher getSupervisor() {
        return null;
    }

    /**
     * Sets the supervisor.
     *
     * @param r the new supervisor
     */
    public void setSupervisor(Researcher r) {
    }

    
    /**
     * Log in.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void logIn()throws IOException{
    	try {
			Translator.printing(" Welcome! ");

			main : while(true){
				Translator.printing(" 0) MAIN PAGE \n 1) News \n 2) Change password  \n"
						+ " 3) Change language  \n 4) My journals \n 5) View marks \n 6) Sign attendance \n 7) Transcript \n"
						+ " 8) Rate teachers \n 9) Info about teachers \n 10) Register for courses \n 11) My Courses \n"
						+ " 12) Add/Drop subject \n 13) My clubs \n 14) \n  Log out ");
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
						System.out.println("\n" + Translator.translate(" Enter new password: "));
						setPassword();
						Translator.printing("\n 1) Change password \n 2) Go back ");
						choice = in.nextInt();
						if(choice==1) continue changePassword;
						if(choice==2) continue main;
						break;
					}
				}
				else if (choice==3){
					System.out.println("\n 1) KZ \n 2) RU \n 3) EN \n 4) Go back");
					choice = in.nextInt();
					if(choice==1) changeLang(Language.KZ);
					if(choice==2) changeLang(Language.RU);
					if(choice==3) changeLang(Language.EN);
					if(choice==4) continue main;
					break;
				}
				else if (choice==4){
					journals:while(true) {
						db.printData(db.journals);
						Translator.printing("\n 1) Subscribe journal \n 2) Unsubscribe journal 3) Go back ");
						choice = in.nextInt();
						if(choice == 1) {subscriptionJournal(1); continue journals;}
						else if(choice == 2) {subscriptionJournal(2);continue journals;}
						else if(choice == 3)continue main;break journals;
					}
				}
				else if(choice == 5) {
					viewMark:while(true) {
						db.printData(courses);
						System.out.println(Translator.translate(" Enter course's index "));
						int index = in.nextInt();
						viewMarks(index-1);
						choice = in.nextInt();
						Translator.printing(" 1) View mark \n 2) Go back ");
						if(choice == 1)continue viewMark;
						else if(choice == 2) continue main; 
						break;
					}
				}
				else if(choice == 6) {
					db.printData(schedule);
					System.out.println(Translator.translate(" Enter lesson's index "));
					int index = in.nextInt();
					Lesson l = schedule.get(index-1);
					if(signAttendance(l))System.out.println(Translator.translate(" Signed! "));
					continue main;

				}
				else if(choice == 7) {
					getTranscript().toString();
					continue main;
				}
				else if(choice == 8) {
					rateTeacher: while(true) {
						db.printData(courses);
						System.out.println(Translator.translate(" Enter the number of course to rate your teacher "));
						int index = in.nextInt();
						Course c = courses.get(index-1);
						RateTeachers(c);
						Translator.printing(" 1) Rate teachers \n 2) Go back ");
						choice = in.nextInt();
						if(choice == 1) continue rateTeacher;
						else if(choice == 2) continue main; 
						break;
					}
				}
				else if(choice == 9) {
					viewInfoTeachers();
					continue main;
				}
				else if(choice == 10) {
					Vector<Course> maj = viewCoursesForReg(CourseType.MAJOR);
					Vector<Course> min = viewCoursesForReg(CourseType.MINOR);
					Vector<Course> free = viewCoursesForReg(CourseType.FREE_ELECTIVE);
					System.out.println(Translator.translate(" MAJOR: "));
					db.printData(maj);
					System.out.println(Translator.translate(" MINOR: "));
					db.printData(min);
					System.out.println(Translator.translate(" FREE ELECTIVE: "));
					db.printData(free);
					Translator.printing(" 1) Register \n 2) Go back ");
					choice = in.nextInt();
					if(choice == 1) {
						System.out.println(Translator.translate(" Choose indexes of minors(0 to quit) "));
						Vector<Integer> indexes = new Vector<>();
						int num = in.nextInt();
						indexes.add(num);
						while(num!=0 || indexes.size() < 4) {
							num = in.nextInt();
							indexes.add(num);
						}
						System.out.println(Translator.translate(" Choose indexes of free elective(0 to quit) "));
						Vector<Integer> indexes2 = new Vector<>();
						int num2 = in.nextInt();
						indexes2.add(num2);
						while(num2!=0 || indexes2.size() < 4) {
							num2 = in.nextInt();
							indexes2.add(num2);
						}
						db.printData(db.managers);
						System.out.println(Translator.translate(" Choose a manager "));
						int ind = in.nextInt();
						Manager m = db.managers.get(ind-1);
						if(RegisterForCourse(maj,m))System.out.println(Translator.translate(" Registered for all majors. "));
						if(RegisterForCourse(min,indexes,m))System.out.println(Translator.translate(" Registered for chosen minors. "));
						if(RegisterForCourse(free,indexes2,m))System.out.println(Translator.translate(" Registered for chosen free electives. "));
						continue main;
					}
				}
				else if(choice == 11) {
					db.printData(courses);
					continue main;
				}
				else if(choice == 12) {
					addDrop:while(true) {
						Translator.printing(" 1) Add subject \n 2) Drop subject \n 3) Go back ");
						choice = in.nextInt();
						if(choice == 1) {
							db.printData(db.courses);
							System.out.println(Translator.translate(" Choose a course "));
							int indexC = in.nextInt();
							Course c = db.courses.get(indexC-1);
							db.printData(db.managers);
							System.out.println(Translator.translate(" Choose a manager "));
							int indexM = in.nextInt();
							Manager m = db.managers.get(indexM-1);
							addDropRequest(c,AddDropType.ADD, m);
							continue addDrop;
						}
						else if(choice ==2) {
							db.printData(courses);
							System.out.println(Translator.translate(" Choose a course "));
							int indexC = in.nextInt();
							Course c = db.courses.get(indexC-1);
							db.printData(db.managers);
							System.out.println(Translator.translate(" Choose a manager "));
							int indexM = in.nextInt();
							Manager m = db.managers.get(indexM-1);
							addDropRequest(c,AddDropType.DROP, m);
							continue addDrop;
						}
					}

				}
				else if(choice == 13) {
					db.printData(clubs);
				}

				else if (choice==14){
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
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    public boolean equals(Object o) {
        return false;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Clone.
     *
     * @return the object
     */
    public Object clone() {
        return null;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return null;
    }

    /**
     * Compare to.
     *
     * @param gs the gs
     * @return the int
     */
    public int compareTo(GraduateStudent gs) {
        return 0;
    }

    /**
     * Check supervisor.
     */
    public void checkSupervisor() {
    }
}

