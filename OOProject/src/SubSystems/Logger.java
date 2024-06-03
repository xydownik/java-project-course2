package SubSystems;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: Auto-generated Javadoc
/**
 * The Class Logger.
 */
public class Logger implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The logger. */
    private static Logger logger;
    
    
    /**
     * Instantiates a new logger.
     */
    private Logger(){}
    static {
    	if(logger == null) {
     	   logger = new Logger();
        }
        else if(new File("log.txt").exists()){
     	   try {
                logger = readData();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    /**
     * Gets the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    /**
     * Adds the log info.
     *
     * @param logInfo the log info
     */
    public static void writeData() throws IOException {
        FileOutputStream fos = new FileOutputStream("log.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(logger);
    }
    public static Logger readData() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("log.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Logger l = (Logger) ois.readObject();
        
        return l;
    }

    /**
     * Show log file.
     */
    public void showLogFile() {
        try {
            Files.lines(Paths.get("log.txt")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

