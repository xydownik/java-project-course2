package Actors;
import java.io.IOException;
import java.util.Scanner;

import Enums.Degree;
import Enums.Faculty;
import SubSystems.Database;
import Tools.Key;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginApp.
 */
public class LoginApp {
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException the class not found exception
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	 
    	User myUser = null;
        Scanner scanner = new Scanner(System.in);
        Database.DATABASE.users.add(new Student(new Key("aruzhan", "123456"), "Aruzhan", "Sazanova", "22B030429", Degree.BACHELOR, Faculty.SITE));
       
       while(myUser == null) {
    	   System.out.println("Welcome to the Login Page!");

           System.out.print("Enter username: ");
           String username = scanner.nextLine();

           System.out.print("Enter password: ");
           String password = scanner.nextLine();
           
           Key k = new Key(username,password);
           // Check if entered credentials match the predefined ones
           for(User u : Database.DATABASE.users) {
        	   if(u.getKey().equals(k)) myUser = u;
           }
       }
       myUser.logIn();
       
    }
}
