import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;


/** <p>CourseSearch opens up the following link: https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx,
   and searches for a Course based on Department, Quarter, and GradLevel</p>
  
  	<p>The suggested work flow is to call the following methods on the CourseSearch object first:
		<ul>
           	<li> <code>getDept</code>
           	<li> <code>getQuarterOptions</code>
           	<li> <code>getGradLevel</code>
   		</ul>
   		
   These will return ArrayLists of all the available options for the user to choose.</p>
   
   <p> Every time the user selects a choice, call the following methods to set those choices:
   		<ul>
           	<li> <code>setDept</code>
           	<li> <code>setQuarter</code>
           	<li> <code>setGradLevel</code>
   		</ul>
   	</p>
   	
   	<p>Only after these three variables are set can you call the next method:
   		<ul>
   			<li> <code>getCourseOptions</code>
   		</ul>
   	This will return an ArrayList of all available courses within the given Department, during the 
   	given Quarter, and for the given GradLevel. </p>
   	
   	<p>When the user selects a choice, call the following method:
   		<ul>
   			<li> <code>setCourse</code>
   		</ul>
   	Which will set the course variable to the given value.</p>
   	
   	<p>When all of that is done, call the following methods:
   		<ul>
   			<li> <code>setLecture</code>
   			<li> <code>getLecture</code>
   		</ul>
   	The <code>setLecture</code> method attempts to create the Lecture object. If it is unsuccessful,
   	it returns false.
   	The <code>getLecture</code> method is what finally returns the lecture object</p>
   	
   	@author Dwayne Conard
   	@version 1.2
*/

public class CourseSearch {
	private ArrayList<String> deptOptions = new ArrayList<String>();
	private ArrayList<String> quarterOptions = new ArrayList<String>();
	private ArrayList<String> gradLevelOptions = new ArrayList<String>();
	private ArrayList<String> courseOptions = new ArrayList<String>();
	private ArrayList<Integer> courseOptionsIndex = new ArrayList<Integer>();

	private String course = "", dept = "", gradLevel = "", quarter = "";
	private int courseIndex = 0, deptIndex = 0, quarterIndex = 0, gradLevelIndex = 0;
	
	private Lecture theLecture = null;
	
	private String url = "https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx";
    private WebClient webClient = null;
	private HtmlPage page = null;
	private HtmlSelect selectField = null;
	private List<HtmlOption> options = null;
	private List<DomElement> courses = null;

	/**The No argument constructor.
	 * 
	 * <code>CourseSearch c = new CourseSearch();</code>
	 * 
	 * <br>It modifies some setting so that the program will run without it printing out almost 200 lines of errors
	 * warning the programmer to be careful if the web page uses javascript
	 */
	public CourseSearch () {
		webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	    webClient.getOptions().setCssEnabled(false);
	    webClient.getOptions().setJavaScriptEnabled(false);
	}

	/**This sets the <code>course</code> variable used in later methods.
	 * 
	 * @param theCourse a string that MUST BE ENTERED EXACTLY. Any typo will cause an error.
	 * The string to be entered should match a string from the <code>getCourseOptions</code> method.
	 */
	public void setCourse(String theCourse) {
		if (courseOptions.contains(theCourse) && !(theCourse.equals("No Classes Found"))) {
			this.courseIndex = courseOptionsIndex.get(courseOptions.indexOf(theCourse));
			this.course = theCourse;
		}
		else {
			this.courseIndex = -50;
			this.course = "";
		}
	}

	/**This sets the <code>dept</code> variable used in later methods.
	 * 
	 * @param theDept a string that MUST BE ENTERED EXACTLY. Any typo will cause an error.
	 * The string to be entered should match a string from the <code>getDeptOptions</code> method.
	 */
	public void setDept(String theDept) {
		if (deptOptions.contains(theDept)) {
			this.deptIndex = this.deptOptions.indexOf(theDept);
			this.dept = theDept;
		}
		else {
			this.dept = "";
		}
	}
	
	
	/**This sets the <code>gradLevel</code> variable used in later methods.
	 * 
	 * @param theGradLevel a string that MUST BE ENTERED EXACTLY. Any typo will cause an error.
	 * The string to be entered should match a string from the <code>getGradLevelOptions</code> method.
	 */
	public void setGradLevel(String theGradLevel) {
		if (gradLevelOptions.contains(theGradLevel)) {
			this.gradLevelIndex = this.gradLevelOptions.indexOf(theGradLevel);
			this.gradLevel = theGradLevel;
		}
		else {
			this.gradLevel = "";
		}
	}
	
	/**This sets the <code>quarter</code> variable used in later methods.
	 * 
	 * @param theQuarter a string that MUST BE ENTERED EXACTLY. Any typo will cause an error.
	 * The string to be entered should match a string from the <code>getQuarterOptions</code> method.
	 */
	public void setQuarter(String theQuarter) {
		if (quarterOptions.contains(theQuarter)) {
			this.quarterIndex = this.quarterOptions.indexOf(theQuarter);
			this.quarter = theQuarter;
		}
		else {
			this.quarter = "";
		}
	}
	
	/**Returns the value of the <code>course</code> instance variable
	 * 
	 * @return String representation of the <code>course</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getCourse() {
		return this.course;
	}
	
	/**Returns the value of the <code>dept</code> instance variable
	 * 
	 * @return String representation of the <code>dept</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getDept() {
		return this.dept;
	}
	
	/**Returns the value of the <code>gradLevel</code> instance variable
	 * 
	 * @return String representation of the <code>gradLevel</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getGradLevel() {
		return this.gradLevel;
	}
	
	/**Returns the value of the <code>quarter</code> instance variable
	 * 
	 * @return String representation of the <code>quarter</code> variable.
	 * Returns <code>""</code> if it was never set
	 */
	public String getQuarter() {
		return this.quarter;
	}
	
	/**Returns an ArrayList of all available Course options.
	 * 
	 * Call this method only after having set the Department, Quarter, and GradLevel using
	 * the appropriate setters
	 * @return an ArrayList of all available Course options.
	 * @throws IOException
	 */
	public List<String> getCourseOptions() throws IOException {
		if (this.loadClassList()) {
			try {
				courses = page.getElementsByIdAndOrName("CourseTitle");
				DomElement courseName;
				int increment = 2;
				String temp = "";
            
				for (int i = 0; i < courses.size(); i++)
				{
					int k = 1;
           	 
					String[] courseAbbr = courses.get(i).getTextContent().trim().split(" ",10);
					while (courseAbbr[k].equals("")) {	  k++;	}
           	 
					String courseAbbr2 = courseAbbr[0] + " " + courseAbbr[k].split(" ")[0].trim();
           	 
					courseName = courses.get(i).getNextElementSibling();
					if (!(courseName.getTextContent().trim().length() == 0)) {	
						if (temp.equals(courseName.getTextContent().trim())) {
							temp = courseName.getTextContent().trim();
							courseOptions.add(courseAbbr2 + " (" + temp + ") - Class " + increment);
							courseOptionsIndex.add(i);
							increment++;
						}
						else {
							temp = courseName.getTextContent().trim();
							courseOptions.add(courseAbbr2 + " (" + temp + ")");
							courseOptionsIndex.add(i);
							increment = 2;
						}
					}
				}
			} catch (FailingHttpStatusCodeException e) {
				System.out.println("FailingHttpStatusCodeException during getCourseOptions:" + e.getMessage());
			}
		}
		if (courseOptions.size() == 0) {
			courseOptions.add("No Classes Found");
		}
		return courseOptions;
	}
	
	/**Returns an ArrayList of all available Department options
	 * 
	 * @return an ArrayList of all available Department options
	 * @throws IOException
	 */
	public List<String> getDeptOptions() throws IOException {
        try {
        	page = webClient.getPage(url);
        	
        	selectField = (HtmlSelect) page.getElementById("pageContent_courseList");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	deptOptions.add(option.getText());
            }
        } catch (FailingHttpStatusCodeException e) {
        	System.out.println("FailingHttpStatusCodeException during getDeptOptions:" + e.getMessage());
        } catch (MalformedURLException e) {
        	System.out.println("MalformedURLException during getDeptOptions:" + e.getMessage());
        } catch (IOException e) {
        	System.out.println("IOException during getDeptOptions:" + e.getMessage());
        }
        return deptOptions;
	}
	
	/**Returns an ArrayList of all available GradLevel options.
	 * 
	 * @return an ArrayList of all available GradLevel options
	 * @throws IOException
	 */
	public List<String> getGradLevelOptions() throws IOException { 
        try {
        	page = webClient.getPage(url);
        	
            selectField = (HtmlSelect) page.getElementById("pageContent_dropDownCourseLevels");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	gradLevelOptions.add(option.getText());
            }
        } catch (FailingHttpStatusCodeException e) {
        	System.out.println("FailingHttpStatusCodeException during getGradlevelOptions:" + e.getMessage());
        } catch (MalformedURLException e) {
        	System.out.println("MalformedURLException during getGradLevelOptions:" + e.getMessage());
        } catch (IOException e) {
        	System.out.println("IOException during getGradlevelOptions:" + e.getMessage());
        }
        return gradLevelOptions;
	}
	
	/**Returns an ArrayList of all available Quarter options.
	 * 
	 * @return an ArrayList of all available Quarter options.
	 * @throws IOException
	 */
	public List<String> getQuarterOptions() throws IOException {
        try {
        	page = webClient.getPage(url);
        	
            selectField = (HtmlSelect) page.getElementById("pageContent_quarterList");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	quarterOptions.add(option.getText());
            }
        } catch (FailingHttpStatusCodeException e) {
        	System.out.println("FailingHttpStatusCodeException during getQuarterOptions:" + e.getMessage());
        } catch (MalformedURLException e) {
        	System.out.println("MalformedURLException during getQuarterOptions:" + e.getMessage());
        } catch (IOException e) {
        	System.out.println("IOException during getQuarterOptions:" + e.getMessage());
        }
        return quarterOptions;
	}
	
	/**A helper function called within getCourseOptions.
	 * 
	 * This function takes all the instance variables (Dept, Quarter, and GradLeve) and loads 
	 * the corresponding web page
	 * @return true if it was able to load the page
	 * @return false is something went wrong
	 * @throws IOException
	 */
	private boolean loadClassList() throws IOException {
		if (this.dept.equals("") || this.quarter.equals("") || this.gradLevel.equals("")) {
			return false;
		}
		else {
			try {
	        	this.page = webClient.getPage(url);
				HtmlElement theElement = null;
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_courseList");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(deptIndex), true );
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_quarterList");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(quarterIndex), true );
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_dropDownCourseLevels");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(gradLevelIndex), true );
			
				theElement = (HtmlElement) this.page.getElementById("pageContent_searchButton");               
				this.page = theElement.click();
				theElement = (HtmlElement) this.page.getElementById("pageContent_repeaterSearchResults_HyperLinkPrimaryCourse_0");
				if (theElement == null) {
					return false;
				}
				this.page = theElement.click();
				return true;
	        } catch (FailingHttpStatusCodeException e) {
	        	System.out.println("FailingHttpStatusCodeException during loadClassList:" + e.getMessage());
	        } catch (MalformedURLException e) {
	        	System.out.println("MalformedURLException during loadClassList:" + e.getMessage());
	        } catch (IOException e) {
	        	System.out.println("IOException during loadClassList:" + e.getMessage());
	        }
			return false;
		}
	}
	
	/**Returns the Lecture object based on the options the user selected
	 * 
	 * @return the Lecture object based on the selected Dept, Quarter, GradLevel, Course
	 */
	public Lecture getLecture() {
		return this.theLecture;
	}
	
	/**Checks if all instance variables have been set to appropriate values.
	 * 
	 * Call this after having set the course variable using <code>setCourse</code>.
	 * @return false if any instance variable was not set to a valid variable.
	 * <br>true if all instance variables were set correctly
	 */
	public boolean setLecture() {
		if (courseIndex == -50) {
			return false;
		}
		else {
			this.theLecture = this.performResult(courseIndex);
			return true;
		}
	}
	
	/**A helper function called within <code>setLecture<code>.
	 * 
	 * @param index the index of the <code>course</code> variable within the <code>courseChoice</code> ArrayList
	 * @return a Lecture object
	 */
	private Lecture performResult(int index) {
		Lecture lect = new Lecture();
		DomElement theCourseName = courses.get(index).getNextElementSibling();
		for (int i = index; i < courses.size(); i++) {               		 
			DomElement courseName = courses.get(i).getNextElementSibling();
			if ( theCourseName.getTextContent().trim().equals(courseName.getTextContent().trim()) || courseName.getTextContent().trim().equals("")) {	 
				int k = 1;
	               			 
				String[] courseAbbr = courses.get(i).getTextContent().trim().split(" ",10);
				while (courseAbbr[k].equals("")) {	  k++;	}
	               			 
				String courseAbbr2 = courseAbbr[0] + " " + courseAbbr[k].split(" ")[0].trim();
	               			 
				DomElement status = courseName.getNextElementSibling();
				DomElement enrollCode = status.getNextElementSibling();
				DomElement instructor = enrollCode.getNextElementSibling();
				DomElement days = instructor.getNextElementSibling();
				DomElement time = days.getNextElementSibling();
				DomElement location = time.getNextElementSibling();
				DomElement enrolled = location.getNextElementSibling();
	                  		 
				if (i == index) {
					DomElement fullTitle = page.getElementById("pageContent_repeaterSearchResults_labelTitle_" + index);
					DomElement description = page.getElementById("pageContent_repeaterSearchResults_labelDescription_" + index);
					DomElement preRequisite = page.getElementById("pageContent_repeaterSearchResults_labelPreReqComment_" + index);
					DomElement college = page.getElementById("pageContent_repeaterSearchResults_labelCollege_" + index);
					DomElement units = page.getElementById("pageContent_repeaterSearchResults_labelUnits_" + index);
					DomElement grading = page.getElementById("pageContent_repeaterSearchResults_labelQuarter_" + index);
					
					lect.setCourseName(courseName.getTextContent().trim() + " (" + courseAbbr2 + ")");
					if (lect.getCourseName().equals("")) { lect.setCourseName("N/A"); }
					
					lect.setCourseAbbr(courseAbbr2);
					if (lect.getCourseAbbr().equals("")) { lect.setCourseAbbr("N/A"); }
					
					lect.setStatus(status.getTextContent().trim());
					if (lect.getStatus().equals("")) { lect.setStatus("N/A"); }
					
					lect.setEnrollCode(enrollCode.getTextContent().trim().split(" ")[0]);
					if (lect.getEnrollCode().equals("")) { lect.setEnrollCode("N/A"); }
					
					lect.setInstructor(instructor.getTextContent().trim());
					if (lect.getInstructor().equals("")) { lect.setInstructor("N/A"); }
					
					lect.setDays(days.getTextContent().trim());
					if (lect.getDays().equals("")) { lect.setDays("N/A"); }
					
					lect.setTime(time.getTextContent().trim());
					if (lect.getTime() == null) { lect.setTime("N/A"); }
					
					lect.setLocation(location.getTextContent().trim());
					if (lect.getLocation().equals("")) { lect.setLocation("N/A"); }
					
					lect.setEnrolled(enrolled.getTextContent().trim());
					if (lect.getEnrolled().equals("")) { lect.setEnrolled("N/A"); }
					
					lect.setFullTitle(fullTitle.getTextContent().trim());
					if (lect.getFullTitle().equals("")) { lect.setFullTitle("N/A"); }
					
					lect.setDescription(description.getTextContent().trim());
					if (lect.getDescription().equals("")) { lect.setDescription("N/A"); }
					
					lect.setPreReq(preRequisite.getTextContent().trim());
					if (lect.getPreReq().equals("")) { lect.setPreReq("N/A"); }
					
					lect.setCollege(college.getTextContent().trim());
					if (lect.getCollege().equals("")) { lect.setCollege("N/A"); }
					
					lect.setUnits(units.getTextContent().trim());
					if (lect.getUnits().equals("")) { lect.setUnits("N/A"); }
					
					lect.setGrading(grading.getTextContent().trim());
					if (lect.getGrading().equals("")) { lect.setGrading("N/A"); }
				}
				else {
					Section sect = new Section();
					
					sect.setMainLecture(lect);
					sect.setDays(days.getTextContent().trim());
					if (sect.getDays().equals("")) { sect.setDays("N/A"); }
					sect.setEnrollCode(enrollCode.getTextContent().trim().split(" ")[0]);
					if (sect.getEnrollCode().equals("")) { sect.setEnrollCode("N/A"); }
					sect.setEnrolled(enrolled.getTextContent().trim());
					if (sect.getEnrolled().equals("")) { sect.setEnrolled("N/A"); }
					sect.setInstructor(instructor.getTextContent().trim());
					if (sect.getInstructor().equals("")) { sect.setInstructor("N/A"); }
					sect.setLocation(location.getTextContent().trim());
					if (sect.getLocation().equals("")) { sect.setLocation("N/A"); }
					sect.setStatus(status.getTextContent().trim());
					if (sect.getStatus().equals("")) { sect.setStatus("N/A"); }
					sect.setTime(time.getTextContent().trim());
					if (sect.getTime() == null) { sect.setTime("N/A"); }
					
					lect.sections.add(sect);
				}
			}
			else {
				courseIndex = i;
				break;	
			}
		}
		return lect;
	}

	/**A main function used for testing purposes when writing the methods
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner ans = new Scanner(System.in);
		CourseSearch test = new CourseSearch();
		
		for (String theOption : test.getDeptOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Department");
		test.setDept(ans.nextLine());
		
		while(test.getDept().equals("")) {
			System.out.println("That wasn't a valid choice. Please try again");
			test.setDept(ans.nextLine());
		}
		System.out.println("You seleceted " + test.getDept());
		
		for (String theOption: test.getQuarterOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Quarter");
		test.setQuarter(ans.nextLine());
		
		while(test.getQuarter().equals("")) {
			System.out.println("That wasn't a valid choice. Please try again");
			test.setQuarter(ans.nextLine());
		}
		System.out.println("You selected " + test.getQuarter());
		
		for (String theOption : test.getGradLevelOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Graduate Level");
		test.setGradLevel(ans.nextLine());
		
		while (test.getGradLevel().equals("")) {
			System.out.println("That wasn't a valid choice. Please try agian");
			test.setGradLevel(ans.nextLine());
		}
		System.out.println("You selected " + test.getGradLevel());
		
		if (test.getCourseOptions().get(0) == "No Classes Found") {
			System.out.println("No Classes Found");
			ans.close();
			return;
		}
		for (String theOption : test.getCourseOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Course");
		test.setCourse(ans.nextLine());
		
		while (test.getCourse().equals("")) {
			System.out.println("That wasn't a valid choice. Please try again");
			test.setCourse(ans.nextLine());
		}
		System.out.println("You selected " + test.getCourse());
		
		ans.close();
		
		boolean finish = test.setLecture();
		
		if (finish) {
			System.out.println(test.getLecture().toString());
		}
		else {
			System.out.println("We could not find any classes");
			return;
		}
	}
}
