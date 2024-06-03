package Education;

import java.io.Serializable;

import Enums.Marks;

// TODO: Auto-generated Javadoc
/**
 * The Class Mark.
 */
public class Mark implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/** The first att. */
public Double firstAtt;

/** The second att. */
public Double secondAtt;

/** The finall. */
public Double finall;

/** The student id. */
public String studentId;

/**
 * Instantiates a new mark.
 *
 * @param att1 the att 1
 * @param att2 the att 2
 * @param finall the finall
 * @param id the id
 */
public Mark(Double att1, Double att2, Double finall, String id) {
	this.firstAtt = att1;
	this.secondAtt = att2;
	this.finall = finall;
	this.studentId = id;
}

/**
 * Instantiates a new mark.
 *
 * @param studentId the student id
 */
public Mark(String studentId) {
	this.studentId = studentId;
}

/**
 * Put first att.
 *
 * @param mark the mark
 */
public void putFirstAtt(double mark) {
	this.firstAtt = mark; 
}

/**
 * Put second att.
 *
 * @param mark the mark
 */
public void putSecondAtt(double mark) {
	this.secondAtt = mark;
}

/**
 * Put final.
 *
 * @param mark the mark
 */
public void putFinal(double mark) {
	this.finall = mark;
}

/**
 * Overall mark.
 *
 * @return the marks
 */
public Marks overallMark() {
	double sum = this.firstAtt + this.secondAtt + this.finall;
	if (this.firstAtt + this.secondAtt < 30)return Marks.F;
	if (this.finall < 20 && this.finall > 10) return Marks.P;
	if (100 > sum && 94 < sum)return Marks.Aplus;
	if (95 > sum && 89 < sum)return Marks.A;
	if (90 > sum && 84 < sum)return Marks.Aminus;
	if (85 > sum && 79 < sum)return Marks.Bplus;
	if (80 > sum && 74 < sum)return Marks.B;
	if (75 > sum && 69 < sum)return Marks.Bminus;
	if (70 > sum && 64 < sum)return Marks.Cplus;
	if (65 > sum && 59 < sum)return Marks.C;
	if (60 > sum && 54 < sum)return Marks.Cminus;
	if (55 > sum && 49 < sum)return Marks.D;
	return Marks.F;
}
}

