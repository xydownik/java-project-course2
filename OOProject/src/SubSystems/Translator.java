package SubSystems;

import java.io.*;
import java.util.*;

import Enums.Language;

// TODO: Auto-generated Javadoc
/**
 * The Class Translator.
 */
public class Translator  {


	/** The en kz. */
	static Map<String, String> enKz = new HashMap<String, String>();

	/** The en rus. */
	static Map<String, String> enRus = new HashMap<String, String>();

	/** The pw. */
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);

	/** The to language. */
	static Language toLanguage = Language.EN;
	

	/**
	 * Read rus to map.
	 */
	static void readRusToMap() {

		try (BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\Аружан\\eclipse-workspace\\OOProject\\src\\rus"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Split each line into key-value pairs based on '='
				String[] parts = line.split("=");

				String key = parts[0];
				String value = parts[1];
				// Store key-value pairs into the map
				enRus.put(key, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read kaz to map.
	 */
	static void readKazToMap() {

		try (BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\Аружан\\eclipse-workspace\\OOProject\\src\\kaz"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Split each line into key-value pairs based on '='
				String[] parts = line.split("=");

				String key = parts[0];
				String value = parts[1];
				// Store key-value pairs into the map
				enKz.put(key, value);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Printing.
	 *
	 * @param s the s
	 */
	public static void printing(String s) {
		if (toLanguage == Language.EN) {
			pw.println(s);
			return;
		}
		StringTokenizer tokenizer = new StringTokenizer(s, "\n");
		while (tokenizer.hasMoreElements()) {
			try{
				String str = tokenizer.nextToken();
				StringTokenizer tokenizer2 = new StringTokenizer(str, ")");
				pw.println(tokenizer2.nextToken() + ")" + translate(tokenizer2.nextToken()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}

			

		}
		
	}

	/**
	 * Translate.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String translate(String s) {
		if (toLanguage == Language.KZ) {
			return enKz.get(s);
		} else if (toLanguage == Language.RU) {
			return enRus.get(s);
		}
		return s;
	}

	static {
		if(enKz.isEmpty() && enRus.isEmpty()) {
			readKazToMap();
			readRusToMap();
		}
	}

	/**
	 * Sets the to language.
	 *
	 * @param toLanguage the new to language
	 */
	public static void setToLanguage(Language toLanguage) {
		Translator.toLanguage = toLanguage;
	}


}
