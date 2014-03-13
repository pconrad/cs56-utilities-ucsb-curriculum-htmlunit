import java.io.Serializable;

/**<p>A Section object is a class that holds a number of string variables</p>
 * These variables can accessed via the setters, getters, and the toString() method.
 * @author Dwayne Conard
 * @version 1.2.1
 */

public class Section extends UCSBClass implements Serializable {
	private static final long serialVersionUID = -5374505671577116733L;
	private Lecture mainLecture;
		
	/**The No argument constructor.
	 * 
	 * <code>Section sect = new Section();</code>
	 * 
	 * <br>It does absolutely nothing, as in java. This is because in java, instance variables are 
	 * automatically initialized to default values.
	 */
	public Section() { }
	
	/**This sets the <code>mainLecture</code> variable, which the Lecture this section is associated with
	 * 
	 * @param theLecture a Lecture object that has this section as one of its sections
	 */
	public void setMainLecture (Lecture theLecture) {
		this.mainLecture = theLecture;
	}
		
	/**Returns the <code>mainLecture</code> instance variable.
	 * 
	 * @return Lecture object associated with this section object.
	 */
	public Lecture getMainLecture() {return this.mainLecture; }
	
	@Override
	public String toString() {
        String result = "Main Lecture:			" + this.getMainLecture().getCourseName() + "\n"
        			+ 	"Section Status:		" + this.getStatus() + "\n"
        			+ 	"Enroll Code: 			" + this.getEnrollCode() + "\n"	
        			+ 	"Teaching Assistant: 	" + this.getInstructor() + "\n"
                	+ 	"Section Days: 			" + this.getDays() + "\n"
                	+ 	"Section Time: 			" + this.getTime() + "\n"
                	+ 	"Section Location: 		" + this.getLocation() + "\n"
                	+ 	"Enrolled Count: 		" + this.getEnrolledCount() + "\n"
        			+ 	"Enrolled Max:			" + this.getEnrolledMax() + "\n\n";
        return result;
    }
}
