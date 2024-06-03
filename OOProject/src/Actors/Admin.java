package Actors;
import SubSystems.*;
import Tools.Key;
import Tools.Message;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

import Enums.*;
import Researcher.CanBeResearcher;
import Researcher.Researcher;

// TODO: Auto-generated Javadoc
/**
 * The Class Admin.
 */
public class Admin extends Employee implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an empty admin.
	 */
	public Admin() {}
	
	/**
	 * Constructs admin with super class constructor.
	 *
	 * @param key the key
	 * @param name the name
	 * @param surname the surname
	 * @param id the id
	 */
	public Admin(Key key, String name, String surname, String id) {
		super(key, name, surname, id);

	}
	
	/**
	 * //	 * Manage users.
	 * //	 *
	 * //	 * @param u the u
	 * //	 * @param command the command
	 * //
	 *
	 * @param ids the ids
	 * @return true, if is unique
	 */
//	public void removeUser(String id, Command command) {
//		
//
//	}
	public boolean isUnique(String ids) {
		if(db.users != null) {
			for(User u : db.users) {
				if(u.getId().equals(ids))return false;

				if(u.getUsername().equals(ids))return false;
			}
		}
		return true;
	}
	/**
	 * See log files from database.
	 *
	 * @return the string of log files.
	 */
	public void seeLogFiles() {
		Logger.getLogger().showLogFile();
	}
	
	/**
	 * Removes the user.
	 *
	 * @param ids the ids
	 */
	public void removeUser(String ids) {
		for(User u : db.users) {
			if(u.getId().equals(ids) && !this.getId().equals(ids)) {
				System.out.println(u.toString());
				db.users.remove(u);
				System.out.println(Translator.translate(" User successfully deleted! "));
				return;
			}
		}
		System.out.println(Translator.translate(" No such user was found... "));
	}
	
	

	/**
	 * Manage users.
	 *
	 * @param u the u
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void manageUsers(User u, Command c) throws IOException {
		if(c == Command.ADD_STUD)manageStudent((Student)u, c);
		if(c == Command.REMOVE && (u instanceof Student))manageStudent((Student)u, Command.REMOVE_STUD);
		
		if(c == Command.ADD_TEACH)manageTeacher((Teacher)u, c);
		if(c == Command.REMOVE && (u instanceof Teacher))manageTeacher((Teacher)u, Command.REMOVE_TEACH);
		
		if(c == Command.ADD_RES)manageResearcher((Researcher)u, c);
		if(c == Command.REMOVE && (u instanceof Researcher))manageResearcher((Researcher)u, Command.REMOVE_RES);
		
		if(c == Command.ADD_TS)manageTS((TechSpecialist)u, c);
		if(c == Command.REMOVE && (u instanceof TechSpecialist))manageTS((TechSpecialist)u, Command.REMOVE_TS);

		if(c == Command.ADD_MANAGER)manageManager((Manager)u, c);
		if(c == Command.REMOVE && (u instanceof Manager))manageManager((Manager)u, Command.REMOVE_MANAGER);
		
		if(c == Command.ADD_ADMIN)manageAdmin((Admin)u, c);
		if(c == Command.REMOVE && (u instanceof Admin))manageAdmin((Admin)u, Command.REMOVE_ADMIN);
		
		if(c == Command.ADD_DEAN)manageDean((Dean)u, c);
		if(c == Command.REMOVE && (u instanceof Dean))manageDean((Dean)u, Command.REMOVE_DEAN);
	}
	
	/**
	 * Method to act while logged in to user.
	 * Includes menu options, which helps to organize all methods in the class.
	 * </p>Have general parts: news, change password, change language, subscribe journal, log out.</p>
	 * Have specific parts: messenger.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void logIn() throws IOException{
		try {
			System.out.println(Translator.translate(" Welcome! "));
			main : while(true){
				Translator.printing(" :) MAIN PAGE \n 1) News \n 2) Change password  "
						+ "\n 3) Change language  \n 4) Subscribe journal \n 5) Messenger "
						+ "\n 6) Manage users \n 7) See log files \n 8) See all users \n 9) Log out ");
				int choice = in.nextInt();
				if(choice==1){
					seeNews();
					Translator.printing("\n 1) Go back");
					choice = in.nextInt();
					if(choice==1)continue main; 
					
				}
				else if (choice==2){
					changePassword: while(true){
						System.out.println(Translator.translate(" Enter new password: "));
						setPassword();
						Translator.printing(" 1) Change password again \n 2) Go back ");
						choice = in.nextInt();
						if(choice==1) continue changePassword;
						if(choice==2) continue main;
						break;
					}
				}
				else if (choice==3){
					System.out.println(" 1) KZ \n 2) RU \n 3) EN \n 4) Go back ");
					choice = in.nextInt();
					if(choice==1) changeLang(Language.KZ);
					if(choice==2) changeLang(Language.RU);
					if(choice==3) changeLang(Language.EN);
					if(choice==4) continue main;
					
				}
				else if (choice==4){
					journals:while(true) {
						db.printData(db.journals);
						Translator.printing("\n 1) Subscribe journal \n 2) Unsubscribe journal \n 3) Go back ");
						choice = in.nextInt();
						if(choice == 1) {subscriptionJournal(1); continue journals;}
						else if(choice == 2) {subscriptionJournal(2);continue journals;}
						else if(choice == 3)continue main;break journals;
					}
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
					manageUsers: while(true) {
						Translator.printing(" 1) Add user \n 2) Remove user \n 3) Update user \n 4) Go back ");
						choice = in.nextInt();
						if(choice == 1) {
							addUser: while(true) {
								String ids,name,surname, username,password; Key k;
								
								System.out.println(Translator.translate(" Enter an ID: "));
								ids = in.next();
								while(!isUnique(ids)) {
									System.out.println(Translator.translate(" Enter an ID: "));
									ids = in.next();
								}

								System.out.println(Translator.translate(" Enter a name: "));
								name = in.next();

								System.out.println(Translator.translate(" Enter a surname: "));
								surname = in.next();

								System.out.println(Translator.translate(" Enter a username: "));
								username = in.next();
								while(!isUnique(username)) {
									System.out.println(Translator.translate(" Enter a username: "));
									username = in.next();
								}

								System.out.println(Translator.translate(" Enter a password: "));
								password = in.next();



								k = new Key(username,password);
								
								
								
								System.out.println(Translator.translate(" Choose user type to manage: "));
								Translator.printing(" 1) Student \n 2) Researcher \n 3) Teacher \n"
										+ " 4) Manager \n 5) Admin \n 6) Tech Specialist \n 7) Dean \n 8)Go back ");
								choice = in.nextInt();
								
								if(choice == 1) {
									Degree degree; Faculty faculty;
									System.out.println(Translator.translate(" Choose a degree: "));
									Translator.printing(" 1) Bachelor \n 2) Master \n 3) PhD ");
									choice = in.nextInt();
									if(choice == 1)degree = Degree.BACHELOR;
									else if( choice == 2)degree = Degree.MASTER;
									else degree = Degree.PHD;
									
									System.out.println(Translator.translate(" Choose a faculty: "));
									Translator.printing(" 1) SITE \n 2) KMA \n 3) BS \n 4) SAM \n"
											+ " 5) SCE \n 6) ISE \n 7) SMSGT \n 8) SNSS \n 9) SG \n 10) SEOGI ");
									choice = in.nextInt();
									if(choice == 1) faculty = Faculty.SITE;
									else if(choice ==2)faculty = Faculty.KMA;
									else if(choice ==3)faculty = Faculty.BS;
									else if(choice ==4)faculty = Faculty.SAM;
									else if(choice ==5)faculty = Faculty.SCE;
									else if(choice ==6)faculty = Faculty.ISE;
									else if(choice ==7)faculty = Faculty.SMSGT;
									else if(choice ==8)faculty = Faculty.SNSS;
									else if(choice ==9)faculty = Faculty.SG;
									else faculty = Faculty.SEOGI;
									Student s;
									if(degree == Degree.BACHELOR) {
										s = new Student(k,name,surname,ids,degree,faculty);
									}
									else {
										if(!db.researchers.isEmpty()) {
											int index;Researcher supervisor;
											System.out.println(Translator.translate(" Choose supervisor "));
											db.printData(db.researchers);
											index= in.nextInt();
											supervisor= db.researchers.get(index-1);
											s = new GraduateStudent(k,name,surname,ids,degree,faculty,supervisor);
										}
										else {
											 System.out.println(Translator.translate(" No supervisor assigned "));
											 s = new GraduateStudent(k,name,surname,ids,degree,faculty);
										}	
									}
									manageUsers(s, Command.ADD_STUD);
									Translator.printing(" 1) Add one more user \n 2) Go back ");
									choice = in.nextInt();
									if(choice == 1)continue addUser;
									if(choice == 2)continue manageUsers; save();
								}
								else if (choice == 2) {//researcher
									Researcher r = new Researcher(k,name,surname,ids);
								
									manageUsers(r, Command.ADD_RES);
									Translator.printing(" 1) Add one more user \n 2) Go back ");
									choice = in.nextInt();
									if(choice == 1)continue addUser;
									if(choice == 2)continue manageUsers; save();
								}
								else if(choice == 3) {//Teacher
									Rank r;
									System.out.println(Translator.translate(" Choose the rank "));
									Translator.printing(" 1) Professor \n 2) Senior lecturer \n 3) Lecturer "
											+ "\n 4) Practice teacher \n 5) Tutor ");
									choice = in.nextInt();
									if(choice == 1) r = Rank.PROFESSOR;
									else if(choice == 2) r = Rank.SENIOR_LECTURER;
									else if(choice == 3) r = Rank.LECTURER;
									else if(choice == 4) r = Rank.PRACTICE_TEACHER;
									else r = Rank.TUTOR;
									
									Teacher t = new Teacher(k,name,surname,ids,r);
									manageUsers(t, Command.ADD_TEACH);
									Translator.printing(" 1) Add one more user \n 2) Go back ");
									choice = in.nextInt();
									if(choice == 1)continue addUser;
									if(choice == 2)continue manageUsers;save();
								}
								else if(choice == 4) {//Manager
									ManagerType type;
									Translator.printing(" 1) Registration Office \n 2) Dean's Office \n 3) Department ");
									choice = in.nextInt();
									if(choice == 1)type = ManagerType.OR;
									else if(choice == 1)type = ManagerType.DEANS_OFFICE;
									else type = ManagerType.DEPARTMENT;
									Manager m = new Manager(k,name,surname,ids,type);
									manageUsers(m, Command.ADD_MANAGER);
									Translator.printing(" 1) Add one more user \n 2) Go back ");
									choice = in.nextInt();
									if(choice == 1)continue addUser;
									if(choice == 2)continue manageUsers;save();
								}
								else if(choice == 5) {//admin
									Admin a = new Admin(k,name,surname,ids);
									manageUsers(a, Command.ADD_ADMIN);
									Translator.printing(" 1) Add one more user \n 2) Go back ");
									choice = in.nextInt();
									if(choice == 1)continue addUser;
									if(choice == 2)continue manageUsers;save();
								}
								else if(choice == 6) {//tech specialist
									TechSpecialist ts = new TechSpecialist(k,name,surname,ids);
									manageUsers(ts,Command.ADD_TS);
								}
								else if(choice ==7) {//dean
									Dean d = new Dean(k,name,surname,ids);
									manageUsers(d,Command.ADD_DEAN);
								}
								else if(choice ==8) continue main; break manageUsers;
							}
							
						}
						else if(choice == 2) {
							removeUser: while(true) {
								System.out.println(Translator.translate(" Enter username of the user to delete "));
								String username = in.next();
								User u = null;
								for(User i : db.users) {
									if(i.getUsername().equals(username))u = i;
								}
								manageUsers(u,Command.REMOVE);
								Translator.printing(" 1) Remove user again \n 2) Go back ");
								choice = in.nextInt();
								if(choice == 1)continue removeUser;
								if(choice == 2)continue manageUsers;save();
							}
						}
						else if(choice == 3) {
							updateUser:while(true) {
								Translator.printing(" 1) Assign researcher \n 2) Assign student as teacher \n 3) Go back ");
								choice = in.nextInt();
								if(choice == 1) {
									System.out.println(Translator.translate(" Choose user type to assign "));
									Translator.printing(" 1) Student \n 2) Teacher \n ");
									choice = in.nextInt();
									if(choice ==1) {//assign stud
										if(!db.students.isEmpty()) {
											db.printData(db.students);
											System.out.println(Translator.translate(" Choose an index "));
											int index = in.nextInt();
											Student s = db.students.get(index-1);
											System.out.println(Translator.translate(" Write another username "));
											String username  = in.next();
											
											Researcher r = new Researcher(new Key(username, s.getKey().getPassword()),
													s.getName(), s.getSurname(), s.getId(), s);
											manageUsers(r,Command.ADD_RES);
										}
										else System.out.println(" No students ");
										continue updateUser;
									}
									else if(choice == 2) {//assign teach
										if(!db.teachers.isEmpty()) {
											db.printData(db.teachers);
											System.out.println(Translator.translate(" Choose an index "));
											int index = in.nextInt();
											Teacher t = db.teachers.get(index-1);
											Researcher r = new Researcher(t.getKey(), t.getName(), t.getSurname(), t.getId(), t);
											manageUsers(r,Command.ADD_RES);
										}
										else System.out.println(" No students ");
										continue updateUser;
									}
								}
								else if(choice == 3)continue manageUsers; save(); break updateUser;
								
							}
							
						}
						else if(choice == 4)continue main;break manageUsers;
					}
						
					}
				else if (choice ==7) {
					seeLogFiles();
					continue main;
				}
				else if (choice == 8) {
					db.printData(db.users);
					continue main;
				}
				else if (choice==9){
					logOut();
					break main;
				}
			}
		} catch (Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
			save();
		}
	}
	
	/**
	 * Manage student.
	 *
	 * @param s the s
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageStudent(Student s, Command c) throws IOException{
		try {
			if(c == Command.ADD_STUD) {
				db.users.add(s);
				db.students.add(s);
				
				System.out.println(Translator.translate(" Student was successfully added! "));
			}
			else if (c == Command.REMOVE_STUD) {
				db.users.remove(s);
				db.students.remove(s);
				
				System.out.println(Translator.translate(" Student was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.students);
	}
	
	/**
	 * Manage teacher.
	 *
	 * @param t the t
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageTeacher(Teacher t, Command c) throws IOException{
		try {
			if(c == Command.ADD_TEACH) {
				db.users.add(t);
				db.teachers.add(t);
				db.employees.add(t);
				
				System.out.println(Translator.translate(" Teacher was successfully added! "));
			}
			else if (c == Command.REMOVE_TEACH) {
				db.users.remove(t);
				db.teachers.remove(t);
				db.employees.remove(t);
				
				System.out.println(Translator.translate(" Teacher was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.teachers);
	}
	
	/**
	 * Manage researcher.
	 *
	 * @param r the r
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageResearcher(Researcher r, Command c) throws IOException{
		try {
			if(c == Command.ADD_RES) {
				db.users.add(r);
				db.researchers.add(r);
				db.employees.add(r);
				
				System.out.println(Translator.translate(" Researcher was successfully added! "));
			}
			else if (c == Command.REMOVE_RES) {
				db.users.remove(r);
				db.researchers.remove(r);
				db.employees.remove(r);
				
				System.out.println(Translator.translate(" Researcher was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.researchers);
	}
	
	/**
	 * Manage TS.
	 *
	 * @param ts the ts
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageTS(TechSpecialist ts, Command c) throws IOException{
		try {
			if(c == Command.ADD_TS) {
				db.users.add(ts);
				db.techSpecialists.add(ts);
				db.techSpecialists.add(ts);
				
				System.out.println(Translator.translate(" Tech specialist was successfully added! "));
			}
			else if (c == Command.REMOVE_TS) {
				db.users.remove(ts);
				db.techSpecialists.remove(ts);
				db.techSpecialists.remove(ts);
				
				System.out.println(Translator.translate(" Tech specialist was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.techSpecialists);
	}
	
	/**
	 * Manage manager.
	 *
	 * @param m the m
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageManager(Manager m, Command c) throws IOException{
		try {
			if(c == Command.ADD_MANAGER) {
				db.users.add(m);
				db.managers.add(m);
				db.employees.add(m);
				
				System.out.println(Translator.translate(" Manager was successfully added! "));
			}
			else if (c == Command.REMOVE_MANAGER) {
				db.users.remove(m);
				db.managers.remove(m);
				db.employees.remove(m);
				
				System.out.println(Translator.translate(" Manager was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.managers);
	}
	
	/**
	 * Manage admin.
	 *
	 * @param a the a
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageAdmin(Admin a, Command c) throws IOException{
		try {
			if(c == Command.ADD_ADMIN) {
				db.users.add(a);
				db.admins.add(a);
				db.admins.add(a);
				
				System.out.println(Translator.translate(" Admin was successfully added! "));
			}
			else if (c == Command.REMOVE_ADMIN) {
				db.users.remove(a);
				db.admins.remove(a);
				db.admins.remove(a);
				
				System.out.println(Translator.translate(" Admin was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.admins);
	}
	
	/**
	 * Manage dean.
	 *
	 * @param d the d
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void manageDean(Dean d, Command c) throws IOException{
		try {
			if(c == Command.ADD_DEAN) {
				db.users.add(d);
				db.deans.add(d);
				db.deans.add(d);
				
				System.out.println(Translator.translate(" Dean was successfully added! "));
			}
			else if (c == Command.REMOVE_DEAN) {
				db.users.remove(d);
				db.deans.remove(d);
				db.deans.remove(d);
				System.out.println(Translator.translate(" Dean was successfully deleted! "));
			}
		}
		catch(NullPointerException n){
			n.printStackTrace();
		}
		db.printData(db.deans);
	}
}


