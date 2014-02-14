/**<p>A Section object is a class that holds a number of string variables</p>
 * These variables can accessed via the setters, getters, and the toString() method.
 * @author Dwayne Conard
 * @version 1.2.1
 */

public class Section {
	private Lecture mainLecture;
	private String status, enrollCode, instructor, days, time, location;
	private int enrolledCount, enrolledMax;
		
	/**The No argument constructor.
	 * 
	 * <code>Section sect = new Section();</code>
	 * 
	 * <br>It does absolutely nothing, as in java. This is because in java, instance variables are 
	 * automatically initialized to default values.
	 */
	public Section() {	}
	
	/**This sets the <code>mainLecture</code> variable, which the Lecture this section is associated with
	 * 
	 * @param theLecture a Lecture object that has this section as one of its sections
	 */
	public void setMainLecture (Lecture theLecture) {
		this.mainLecture = theLecture;
	}
	
	/**This sets the <code>status</code> variable.
	 * 
	 * @param theStatus a string that represents the status of the section.
	 */
	public void setStatus(String theStatus) {
		this.status = theStatus;
	}
	
	/**This sets the <code>enrollCode</code> variable.
	 * 
	 * @param theEnrollCode a string that represents the Enroll Code of the section.
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
	 * @param theDays a string that represents the days the section meets.
	 */
	public void setDays(String theDays) {
		this.days = theDays;
	}
	
	/**This sets the <code>time</code> variable.
	 * 
	 * @param theTime a string that represents the beginning and ending time of the Section.
	 */
	public void setTime(String theTime) {
		this.time = theTime;
	}
	
	/**This sets the <code>location</code> variable.
	 * 
	 * @param theLocation a string that represents where the section is held.
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
		
	/**Returns the <code>mainLecture</code> instance variable.
	 * 
	 * @return Lecture object associated with this section object.
	 */
	public Lecture getMainLecture() {return this.mainLecture; }
	
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
	public String getEnrolled() { return "" + this.enrolledCount + " / " + this.enrolledMax; }
	
	/**The overridden <code>toString()</code> method for a Lecture object
	 * 
	 * @return String representation of the Lecture object
	 */
	@Override
	public String toString() {
        String result = "Main Lecture:			" + this.getMainLecture().getCourseName() + "\n"
        			+ 	"Section Status:			" + this.status + "\n"
        			+ 	"Enroll Code: 			" + this.enrollCode + "\n"	
        			+ 	"Teaching Assistant: 		" + this.instructor + "\n"
                	+ 	"Section Days: 			" + this.days + "\n"
                	+ 	"Section Time: 			" + this.time + "\n"
                	+ 	"Section Location: 		" + this.location + "\n"
                	+ 	"Enrolled Count: 		" + this.enrolledCount + "\n"
        			+ 	"Enrolled Max:			" + this.enrolledMax + "\n\n";
        return result;
    }
}
