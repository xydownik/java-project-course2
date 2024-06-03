package Education;
import java.io.Serializable;
import java.util.Map;
import java.util.Vector;

import Enums.Marks;
// TODO: Auto-generated Javadoc

/**
 * The Class Transcript.
 */
public class Transcript implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/** The transcript. */
public Map<Integer,Vector<Course>> transcript;

/** The student id. */
private String studentId;

/**
 * Instantiates a new transcript.
 */
public Transcript() {}

/**
 * Instantiates a new transcript.
 *
 * @param studentId the student id
 */
public Transcript(String studentId) {
	this.studentId = studentId;
}

/**
 * To string.
 *
 * @return the string
 */
public String toString() {
	return "Transcript [transcript=" + transcript + ", studentId=" + studentId + "]";
}

/**
 * Count GPA.
 *
 * @return the double
 */
public Double countGPA() {
	double gpa = 0;
	int ects = 0;
	
	for (Integer sem : transcript.keySet()) {
		
         Vector<Course> courses = transcript.get(sem);

         for(Course course: courses) {
        	 Mark m = (Mark) course.marks.stream().filter(n -> n.studentId.equals(this.studentId));
        	 if (m.overallMark()== Marks.Aplus) {
         		gpa += 4.00 * course.credits;
         	 }
        	 if (m.overallMark()==Marks.A) {
         		gpa += 3.67 * course.credits;
         	 }
        	 if (m.overallMark()==Marks.Aminus) {
         		gpa += 3.33 * course.credits;
         	 }
        	 if (m.overallMark()==Marks.Bplus) {
         		gpa += 3.00 * course.credits;
         	 }
        	 if (m.overallMark()==Marks.B) {
         		gpa += 2.67 * course.credits;
         	 }
        	 if (m.overallMark()==Marks.Bminus){
         		gpa += 2.33 * course.credits;
         	 }
        	 if (m.overallMark()== Marks.Cplus) {
         		gpa += 2.00 * course.credits;
         	 }
        	 if (m.overallMark()== Marks.C) {
         		gpa += 1.67 * course.credits;
         	 }
        	 if (m.overallMark()== Marks.Cminus) {
         		gpa += 1.33 * course.credits;
         	 }
        	 if (m.overallMark()== Marks.D){
         		gpa += 1.00 * course.credits;
         	 }
        	 if (m.overallMark()== Marks.F){
         		gpa += 0 * course.credits;
         	 }
         	 ects += course.credits;
         }
         
     }
	return gpa / ects;
}

/**
 * Gets the transcript.
 *
 * @return the transcript
 */
public Map<Integer,Vector<Course>> getTranscript() {
    return transcript;
}
}


