import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**<p>A Lecture object is a class that holds a number of string variables</p>
 * These variables can accessed via the setters, getters, and the toString() method.
 * @author Dwayne Conard
 * @version 1.2.1
 */

public class Lecture extends UCSBClass implements Serializable {
	private static final long serialVersionUID = -1176285613677203485L;
	private String courseAbbr, courseName, fullTitle, description, preReq, college, units, grading;
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
        			+ 	"Lecture Status:			" + this.getStatus() + "\n"
        			+ 	"Enroll Code:			" + this.getEnrollCode() + "\n"	
        			+ 	"Instructor:			" + this.getInstructor() + "\n"
                	+ 	"Lecture Days:			" + this.getDays() + "\n"
                	+	"Lecture Time:			" + this.getTime() + "\n"
                	+ 	"Lecture Location:		" + this.getLocation() + "\n"
                	+ 	"Enrolled Count:			" + this.getEnrolledCount() + "\n"
        			+ 	"Enrolled Max:			" + this.getEnrolledMax() + "\n\n";
        if (!(sections.size() == (0))) {
        	result += "Sections\n--------\n";
        	for (int i = 0; i < this.sections.size(); i++) {
        		result += "Section " + i + "\n" + sections.get(i).toString();
        	}
        }
        return result;
    }
}
