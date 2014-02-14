import java.util.List;
import java.util.ArrayList;

/**<p>A Lecture object is a class that holds a number of string variables</p>
 * These variables can accessed via the setters, getters, and the toString() method.
 * @author Dwayne Conard
 * @version 1.2.1
 */

public class Lecture {
	private String courseAbbr, courseName, status, enrollCode, instructor, days, time;
	private String location, fullTitle, description, preReq, college, units, grading;
	private int enrolledCount, enrolledMax;
	public List<Section> sections = new ArrayList<Section>();
	
	/**The No argument constructor.
	 * 
	 * <code>Lecture lect = new Lecture();</code>
	 * 
	 * <br>It does absolutely nothing, as in java. This is because in java, instance variables are 
	 * automatically initialized to default values.
	 */
	public Lecture() {	}
	
	/**This sets the <code>coureAbbr</code> variable.
	 * 
	 * @param theCourseAbbr a string that represents the course abbreviation.
	 */
	public void setCourseAbbr(String theCourseAbbr) {
		this.courseAbbr = theCourseAbbr;
	}
	
	/**This sets the <code>courseName</code> variable.
	 * 
	 * @param theCourseName a string that represents the name of the course.
	 */
	public void setCourseName(String theCourseName) {
		this.courseName = theCourseName;
	}
	
	/**This sets the <code>status</code> variable.
	 * 
	 * @param theStatus a string that represents the status of the course.
	 */
	public void setStatus(String theStatus) {
		this.status = theStatus;
	}
	
	/**This sets the <code>enrollCode</code> variable.
	 * 
	 * @param theEnrollCode a string that represents the Enroll Code of the course.
	 */
	public void setEnrollCode(String theEnrollCode) {
		this.enrollCode = theEnrollCode;
	}
	
	/**This sets the <code>instructor</code> variable.
	 * 
	 * @param theInstructor a string that represents the name of the Instructor.
	 */
	public void setInstructor(String theInstructor) {
		this.instructor = theInstructor;
	}
	
	/**This sets the <code>days</code> variable.
	 * 
	 * @param theDays a string that represents the days the course meets.
	 */
	public void setDays(String theDays) {
		this.days = theDays;
	}
	
	/**This sets the <code>time</code> variable.
	 * 
	 * @param theTime a string that represents the beginning and ending time of the Lecture.
	 */
	public void setTime(String theTime) {
		this.time = theTime;
	}
	
	/**This sets the <code>location</code> variable.
	 * 
	 * @param theLocation a string that represents where the Lecture is held.
	 */
	public void setLocation(String theLocation) {
		this.location = theLocation;
	}
	
	/**This sets the <code>enrolledCount</code> variable.
	 * 
	 * @param theEnRolledCount a string that represents the number of people currently enrolled.
	 */
	public void setEnrolledCount (int theEnRolledCount) {
		this.enrolledCount = theEnRolledCount;
	}
	
	/**This sets the <code>enrolledMax</code> variable.
	 * 
	 * @param theEnRolledMax a string that represents the maximum number of possible people enrolled.
	 */
	public void setEnrolledMax (int theEnRolledMax) {
		this.enrolledMax = theEnRolledMax;
	}
	
	/**This sets the both the <code>enrolledCount</code> and the <code>enrolledMax</code> variables.
	 * 
	 * @param theEnRolled a string of the form <code>46/75</code> that is split to set the count and max.
	 */
	public void setEnrolled(String theEnRolled) {
		String [] temp = theEnRolled.replaceAll("\\s", "").split("/");
		this.enrolledCount = Integer.parseInt(temp[0]);
		this.enrolledMax = Integer.parseInt(temp[1]);
	}
	
	/**This sets the <code>fullTitle</code> variable.
	 * 
	 * @param theFullTitle a string that represents the full title of the ourse
	 */
	public void setFullTitle(String theFullTitle) {
		this.fullTitle = theFullTitle;
	}
	
	/**This sets the <code>description</code> variable.
	 * 
	 * @param theDescription a string that represents the description of the course.
	 */
	public void setDescription(String theDescription) {
		this.description = theDescription;
	}
	
	/**This sets the <code>preReq</code> variable.
	 * 
	 * @param thePreReq a string that represents the Prerequisite courses.
	 */
	public void setPreReq(String thePreReq) {
		this.preReq = thePreReq;
	}
	
	/**This sets the <code>college</code> variable.
	 * 
	 * @param theCollege a string that represents the college the course is associated with
	 */
	public void setCollege(String theCollege) {
		this.college = theCollege;
	}
	
	/**This sets the <code>units</code> variable.
	 * 
	 * @param theUnits a string that represents the units the course is worth
	 */
	public void setUnits(String theUnits) {
		this.units = theUnits;
	}
	
	/**This sets the <code>grading</code> variable.
	 * 
	 * @param theGrading a string that represents the grading options (Pass/No Pass, Letter, etc.).
	 */
	public void setGrading(String theGrading) {
		this.grading = theGrading;
	}
	
	
	/**Returns the <code>courseAbbr</code> instance variable.
	 * 
	 * @return String representation of the <code>courseAbbr</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getCourseAbbr() { return this.courseAbbr; }
	
	/**Returns the <code>courseName</code> instance variable.
	 * 
	 * @return String representation of the <code>courseName</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getCourseName() { return this.courseName; }
	
	/**Returns the <code>status</code> instance variable.
	 * 
	 * @return String representation of the <code>status</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getStatus() { return this.status; }
	
	/**Returns the <code>enrollCode</code> instance variable.
	 * 
	 * @return String representation of the <code>enrollCode</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getEnrollCode() { return this.enrollCode; }
	
	/**Returns the <code>instructor</code> instance variable.
	 * 
	 * @return String representation of the <code>instructor</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getInstructor() { return this.instructor; }
	
	/**Returns the <code>days</code> instance variable.
	 * 
	 * @return String representation of the <code>days</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getDays() { return this.days; }
	
	/**Returns the <code>time</code> instance variable.
	 * 
	 * @return String representation of the <code>time</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getTime() { return this.time; }
	
	/**Returns the <code>location</code> instance variable.
	 * 
	 * @return String representation of the <code>location</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getLocation() { return this.location; }
	
	/**Returns the <code>enrolledCount</code> instance variable.
	 * 
	 * @return int representation of the <code>enrolledCount</code> variable.
	 */
	public int getEnrolledCount() { return this.enrolledCount; }
	
	/**Returns the <code>enrolledMax</code> instance variable.
	 * 
	 * @return int representation of the <code>enrolledMax</code> variable.
	 */
	public int getEnrolledMax() { return this.enrolledMax; }
	
	/**Returns the <code>enrolled</code> instance variable.
	 * 
	 * @return String representation of the <code>enrolled</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getEnrolled() {return "" + this.enrolledCount + " / " + this.enrolledMax;}
	
	/**Returns the <code>fullTitle</code> instance variable.
	 * 
	 * @return String representation of the <code>fullTitle</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getFullTitle() { return this.fullTitle; }
	
	/**Returns the <code>description</code> instance variable.
	 * 
	 * @return String representation of the <code>description</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getDescription() { return this.description; }
	
	/**Returns the <code>preReq</code> instance variable.
	 * 
	 * @return String representation of the <code>PreReq</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getPreReq() { return this.preReq; }
	
	/**Returns the <code>college</code> instance variable.
	 * 
	 * @return String representation of the <code>college</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getCollege() { return this.college; }
	
	/**Returns the <code>units</code> instance variable.
	 * 
	 * @return String representation of the <code>units</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getUnits() { return this.units; }
	
	/**Returns the <code>grading</code> instance variable.
	 * 
	 * @return String representation of the <code>grading</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getGrading() { return this.grading; }
	
	/**The overridden <code>toString()</code> method for a Lecture object
	 * 
	 * @return String representation of the Lecture object
	 */
	@Override
	public String toString() {
        String result = "Course Name:			" + this.courseName + "\n"
                	+ 	"Course Abbreviation:		" + this.courseAbbr + "\n"
        			+	"Full Title:			" + this.fullTitle + "\n"
        			+ 	"Description:			" + this.description + "\n"
        			+ 	"PreReq(s):			" + this.preReq + "\n"
        			+ 	"College:			" + this.college + "\n"
        			+	"Units:				" + this.units + "\n"
        			+	"Grading:			" + this.grading + "\n"
        			+ 	"Lecture Status:			" + this.status + "\n"
        			+ 	"Enroll Code:			" + this.enrollCode + "\n"	
        			+ 	"Instructor:			" + this.instructor + "\n"
                	+ 	"Lecture Days:			" + this.days + "\n"
                	+	"Lecture Time:			" + this.time + "\n"
                	+ 	"Lecture Location:		" + this.location + "\n"
                	+ 	"Enrolled Count:			" + this.enrolledCount + "\n"
        			+ 	"Enrolled Max:			" + this.enrolledMax + "\n\n";
        if (!(sections.size() == (0))) {
        	result += "Sections\n--------\n";
        	for (int i = 0; i < this.sections.size(); i++) {
        		result += "Section " + i + "\n" + sections.get(i).toString();
        	}
        }
        return result;
    }
}
