package Actors;

import Enums.*;
import Exceptions.RegistrationIsNotAllowed;
import Exceptions.WrongManagerType;
import Researcher.CanBeResearcher;
import SubSystems.*;
import Tools.*;
import Education.*;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 *<
 */
public class Student extends User implements CanBeResearcher, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The courses. */
	protected Vector<Course> courses = new Vector<Course>();

	/** The schedule. */
	protected Vector<Lesson> schedule = new Vector<Lesson>();

	/** The transcript. */
	protected Vector<Transcript> transcript = new Vector<Transcript>();

	/** The clubs. */
	protected Vector<StudentOrgs> clubs = new Vector<StudentOrgs>();

	/** The gpa. */
	protected Double gpa = 0.0;

	/** The degree. */
	protected Degree degree = Degree.BACHELOR;

	/** The my requests. */
	protected Vector<Request> myRequests = new Vector<Request>();

	/** The faculty. */
	protected Faculty faculty;

	/**
	 * Instantiates a new student.
	 */
	public Student() {}

	/**
	 * Instantiates a new student.
	 *
	 * @param key the key
	 * @param name the name
	 * @param surname the surname
	 * @param id the id
	 * @param degree the degree
	 * @param faculty the faculty
	 */
	public Student(Key key, String name, String surname, String id, Degree degree, Faculty faculty) {
		super(key, name, surname, id);
		this.degree = degree;
		this.faculty = faculty;
	}

	/**
	 * View info teachers.
	 *
	 * @return the string
	 */
	public void viewInfoTeachers() {
		try {
			for(Course c : courses) {
				for(Teacher t : db.teachers) {
					if(t.getCourses().contains(c)) System.out.println( t.toString());
				}
			}
		}catch(NullPointerException n) {
			n.printStackTrace();
		}
	}

	/**
	 * Register for course.
	 *
	 * @param c the c
	 * @param index the index
	 * @param m the m
	 * @return the boolean
	 * @throws WrongManagerType the wrong manager type
	 * @throws RegistrationIsNotAllowed the registration is not allowed
	 */
	public Boolean RegisterForCourse(Vector<Course> c, Vector<Integer> index, Manager m) throws  WrongManagerType, RegistrationIsNotAllowed {
		
		if( m.getType() == ManagerType.OR) {
			for(int i=0 ;i<c.size();i++) {
				for(int j =0 ;j<index.size();j++) {
					if(index.get(j).equals(i)) {
						m.confirmRegistration(this, c.get(i));
					}
				}
			}
			return true;
		}
		else throw new WrongManagerType(" OR managers are resposible for this operation! ");
		

	}
	
	/**
	 * Register for course.
	 *
	 * @param c the c
	 * @param m the m
	 * @return the boolean
	 * @throws WrongManagerType the wrong manager type
	 * @throws RegistrationIsNotAllowed the registration is not allowed
	 */
	public Boolean RegisterForCourse(Vector<Course> c,  Manager m) throws WrongManagerType, RegistrationIsNotAllowed {
		if(m.getType() == ManagerType.OR) {
			for(Course i : courses) {
				 m.confirmRegistration(this, i);
			}
			return true;
		}
		else throw new WrongManagerType("  OR managers are resposible for this operation! ");
		

	}

	/**
	 * View courses for reg.
	 *
	 * @param ct the ct
	 * @return the string
	 */
	public Vector<Course> viewCoursesForReg(CourseType ct) {
		Vector<Course> courses = new Vector<Course>();

		if(!db.coursesForRegistration.get(faculty).isEmpty()) {
			for(Course c : db.coursesForRegistration.get(faculty)){
				if(c.type == CourseType.MAJOR && ct == CourseType.MAJOR)courses.add(c);
				else if(c.type == CourseType.MINOR && ct == CourseType.MINOR)courses.add(c);
				else if(c.type == CourseType.FREE_ELECTIVE && ct == CourseType.FREE_ELECTIVE)courses.add(c);
			}
		}
		return courses;

	}

	/**
	 * View marks.
	 *
	 * @param index the index
	 * @return the string
	 */
	public void viewMarks(Integer index) {
		try {
			Course c = courses.get(index);
			Mark m = c.getMark(this);
			System.out.println(m.toString());
		}
		catch(ArrayIndexOutOfBoundsException a){
			a.printStackTrace();
		}
	}


	/**
	 * Sign attendance.
	 *
	 * @param l the l
	 * @return the boolean
	 */
	public Boolean signAttendance(Lesson l) {
		return l.makeAttendance(this);

	}

	/**
	 * Adds the drop request.
	 *
	 * @param c the c
	 * @param type the type
	 * @param m the m
	 * @throws WrongManagerType the wrong manager type
	 */
	public void addDropRequest(Course c, AddDropType type, Manager m) throws WrongManagerType {
		if(m.getType() == ManagerType.OR) {
			m.getRequests().add(new AddDropRequest(type, c, this));
			System.out.println(Translator.translate(" Sent! "));
			return;
		}
		else throw new WrongManagerType(" OR managers are responsible for this operation! ");
	}

	/**
	 * Join club.
	 *
	 * @param s the s
	 */
	public void joinClub(StudentOrgs s) {
		s.members.add(this);
	}

	/**
	 * View courses.
	 *
	 * @return the string
	 */
	public String viewCourses() {
		return courses.toString();
	}

	/**
	 * Rate teachers.
	 *
	 * @param c the c
	 */
	public void RateTeachers(Course c) {
		Teacher t = c.teachers.get(0);
		int rate = in.nextInt();
		t.getRates().add(rate);
	}


	/**
	 * Log in.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void logIn() throws IOException {
		try {
			Translator.printing(" Welcome! ");

			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password  \n"
						+ " 3) Change language  \n 4) My journals \n 5) View marks \n 6) Sign attendance \n 7) Transcript \n"
						+ " 8) Rate teachers \n 9) Info about teachers \n 10) Register for courses \n 11) My Courses \n"
						+ " 12) Add/Drop subject \n 13) My clubs \n 14) Log out ");
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
					if(!db.coursesForRegistration.isEmpty()) {
						db.printData(db.coursesForRegistration.get(faculty));
						int index = in.nextInt();
						Course c = db.coursesForRegistration.get(faculty).get(index-1);
						c.students.add(this);
						System.out.println(" Added");
					
					}
					else System.out.println(Translator.translate(" No data yet "));continue main;
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
	 * Gets the courses.
	 *
	 * @return the courses
	 */
	public Vector<Course> getCourses() {
		return courses;
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
	 * Gets the transcript.
	 *
	 * @return the transcript
	 */
	public Vector<Transcript> getTranscript() {
		return transcript;
	}

	/**
	 * Gets the clubs.
	 *
	 * @return the clubs
	 */
	public Vector<StudentOrgs> getClubs() {
		return clubs;
	}

	/**
	 * Gets the gpa.
	 *
	 * @return the gpa
	 */
	public Double getGPA() {
		return gpa;
	}

	/**
	 * Sets the courses.
	 *
	 * @param v the new courses
	 */
	public void setCourses(Vector<Course> v) {
		this.courses = v;
	}

	/**
	 * Sets the schedule.
	 *
	 * @param v the new schedule
	 */
	public void setSchedule(Vector<Lesson> v) {
		this.schedule = v;
	}

	/**
	 * Sets the transcript.
	 *
	 * @param v the new transcript
	 */
	public void setTranscript(Vector<Transcript> v) {
		this.transcript = v;
	}

	/**
	 * Sets the clubs.
	 *
	 * @param v the new clubs
	 */
	public void setClubs(Vector<StudentOrgs> v) {
		this.clubs = v;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return super.toString() + " GPA: " + gpa + " Degree: " + degree + " Faculty: " + faculty;
	}

	/**
	 * Gets the faculty.
	 *
	 * @return the faculty
	 */
	public Faculty getFaculty() {
		return faculty;
	}

	/**
	 * Sets the faculty.
	 *
	 * @param faculty the new faculty
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	/**
	 * Compare to.
	 *
	 * @param s the s
	 * @return the integer
	 */
	public int compareTo(Student s) {
		if(this.gpa>s.gpa) return 1;
		if(this.gpa<s.gpa)return -1;
		return 0;
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
	 * Gets the degree.
	 *
	 * @return the degree
	 */
	public Degree getDegree() {
		return degree;
	}


}