package SubSystems;

import Actors.*;
import Enums.*;
import Tools.*;
import Education.*;
import Researcher.*;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * Database class for storing various system-related data.
 */
public class Database implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The courses for registration. */
    public Map<Faculty, Vector<Course>> coursesForRegistration = new HashMap<Faculty,Vector<Course>>();
    
    /** The courses. */
    public Vector<Course> courses = new Vector<>();
    
    /** The users. */
    public Vector<User> users = new Vector<>();
    
    /** The employees. */
    public Vector<Employee> employees = new Vector<>();
    
    /** The managers. */
    public Vector<Manager> managers = new Vector<>();
    
    /** The tech specialists. */
    public Vector<TechSpecialist> techSpecialists = new Vector<>();
    
    /** The students. */
    public Vector<Student> students = new Vector<>();
    
    /** The teachers. */
    public Vector<Teacher> teachers = new Vector<>();
    
    /** The researchers. */
    public Vector<Researcher> researchers = new Vector<>();
    
    /** The admins. */
    public Vector<Admin> admins = new Vector<>();
    
    /** The log files. */
    public Logger logFiles = Logger.getLogger();
    
    /** The news. */
    public Vector<News> news = new Vector<>();
    
    /** The journals. */
    public Vector<Journal> journals = new Vector<>();
    
    /** The all requests. */
    public Vector<Request> allRequests = new Vector<>();
    
    /** The deans. */
    public Vector<Dean> deans = new Vector<>(4);
    
    /** The database. */
    public static Database DATABASE;

    static {
        if (new File("data2.ser").exists()) {
            try {
                DATABASE = readData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            DATABASE = new Database();
        }
    }

    /**
     * Instantiates a new database.
     */
    private Database() {
        // Private constructor to enforce Singleton pattern
    }

    /**
     * Read data.
     *
     * @return the database
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException the class not found exception
     */
    public static Database readData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("data2.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Database db = (Database) ois.readObject();
        ois.close();
        fis.close();
        return db;
    }

    /**
     * Prints the data.
     *
     * @param l the l
     */
    public void printData(List<?> l) {
        for (int i = 0; i < l.size(); i++) {
            System.out.println(i + 1 + ") " + l.get(i));
        }
    }

    /**
     * Write data.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void writeData() throws IOException {
        FileOutputStream fos = new FileOutputStream("data2.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(DATABASE);
        oos.close();
        fos.close();
    }
}
