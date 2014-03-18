import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Console;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.DomNode;


/** 
    Scraper for the Isis website

   	@author Dwayne Conard
   	@author Phill Conrad
   	@version 1.2
*/

public class IsisScrape {
	
    private String url = "https://isis.sa.ucsb.edu/‎";
    
    private WebClient webClient = null;
    private HtmlPage page = null;
    private HtmlSelect selectField = null;
    private List<HtmlOption> options = null;
    private List<DomElement> courses = null;
    
    
    private String username;
    private String password;

    public boolean debug=false;


    /** setter */

    void setUsername(String username) { this.username = username; }
    void setPassword(char [] password) { this.password = new String(password); }

    /**The No argument constructor.
     * 
     * <code>IsisScrape c = new IsisScrape();</code>
     * 
     * <br>It modifies some setting so that the program will run without it printing out almost 200 lines of errors
     * warning the programmer to be careful if the web page uses javascript
     */
    public IsisScrape () {
	webClient = new WebClient();
	webClient.getOptions().setThrowExceptionOnScriptError(false);
	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	webClient.getOptions().setCssEnabled(false);
	webClient.getOptions().setJavaScriptEnabled(false);
    }
    
    /**
       Initial connect to ISIS web page
    */
    
    public void connect() {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);

	try{ 
	    this.page = webClient.getPage(url);


	    HtmlInput username = null;
	    HtmlInput password = null;
	    HtmlElement theElement = null;

	    username = (HtmlInput) this.page.getElementById("cphMain_tbUsername");
	    username.setValueAttribute(this.username);

	    password = (HtmlInput) this.page.getElementById("cphMain_tbPassword");
	    password.setValueAttribute(this.password);

	    theElement = (HtmlElement) this.page.getElementById("cphMain_btnLogin");               
	    this.page = theElement.click();

	    if (debug) {
		System.out.println("page = " + this.page);
		System.out.println("page = " + this.page.asXml());
	    }
	    clickHiddenSubmit();

	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
	
    }

    /**
       Click hidden submit button
    */
    
    public void clickHiddenSubmit() {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);

	try{ 

	    DomNodeList<DomElement> domNodeList = this.page.getElementsByTagName("input");

	    HtmlInput hiddenSubmit = null;
	    for (DomElement de: domNodeList) {
		if (de.getAttribute("type").equals("submit")) {
		    hiddenSubmit = (HtmlInput) de;
		    break;
		}
	    }

	    if (hiddenSubmit == null) {
		throw new Exception("Could not find hidden input element for submit");
	    }

	    this.page = hiddenSubmit.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }
	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
	
    }

    /** click link on main menu to access STAR system */

    public void clickSTAR() {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	
	try{ 

	    DomNodeList<DomElement> domNodeList = this.page.getElementsByTagName("a");

	    HtmlElement starLink = null;
	    for (DomElement de: domNodeList) {
		if (de.getAttribute("href").equals("/STAR/SACCP001.aspx")) {
		    starLink = (HtmlElement) de;
		    break;
		}
	    }

	    if (starLink == null) {
		throw new Exception("Could not find hidden input element for submit");
	    }

	    this.page = starLink.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }
	    clickHiddenSubmit();

	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
    }

    /** click link on CONTINUE button to acknowledge FERPA notice  */

    public void clickCONTINUE() {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	
	try{ 
	    HtmlElement theElement = null;

	    theElement = (HtmlElement) this.page.getElementById("ContentPlaceHolder1_ButtonContinue");               
	    this.page = theElement.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }

	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
    }


    /** click input button of type submit, with given value 

	@param value value of the value="..." attribute
    */

    public void clickSubmit(String value) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	
	HtmlElement sb = null;
	try{ 

	    DomNodeList<DomElement> nodeList = this.page.getElementsByTagName("input");

	    for ( DomElement de: nodeList) {
		if (value.equals(de.getAttribute("value"))) {
		    sb = (HtmlElement) de;
		    break;			    
		}
	    }
		     
	    String type = sb.getAttribute("type");

	    if ( type==null || !type.equals("submit")) { 
		System.out.println("Element with value " + value + " was not of type submit");
		System.exit(20);
	    } 

	    System.out.println("Clicking submit button with value=" + value);
	    this.page = sb.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }

	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
    }


    /**
       Main menu entry
    */
    
    public void mainMenu(String select, String qtr) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);

	try{ 

	    HtmlInput selectElement = null;
	    HtmlInput qtrElement = null;
	    HtmlElement theElement = null;

	    selectElement = (HtmlInput) this.page.getElementById("ContentPlaceHolder1_Select");
	    selectElement.setValueAttribute(select);


	    qtrElement = (HtmlInput) this.page.getElementById("ContentPlaceHolder1_SelectQtr");
	    qtrElement.setValueAttribute(qtr);

	    theElement = (HtmlElement) this.page.getElementById("ContentPlaceHolder1_ButtonSelect");               
	    this.page = theElement.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }
	    
	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
	
    }

    /**
       Enter data

       @param idOfTextInput  id of the input element with type text
       @param valueOfTextInput value of the value attribute for text input 
    */
    
    public void enterData(String idOfTextInput, String valueOfTextInput) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	
	System.out.println("  idOfTextInput=" + idOfTextInput + " valueOfTextInput=" + valueOfTextInput);

	HtmlInput selectElement = null;
	
	selectElement = (HtmlInput) this.page.getElementById(idOfTextInput);
	selectElement.setValueAttribute(valueOfTextInput);

	System.out.println("Leaving " + mname);
    }


    /**
       <p>Enter data in the input element that is in the same enclosing &lt;tr&gt; element with 
       an element containing exactly this text content (i.e. a label).</p>
       
       <p>There should be only one such input element; otherwise an error
       will occur.</p>
       

       @param content The text label to look for 
       @param value value of the text to enter 
    */
    
    public void enterDataAfterLabel(String content,  String value) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	

	java.util.Iterator<HtmlElement> iterable = this.page.getHtmlElementDescendants().iterator();

	boolean found=false;
	HtmlElement elem=null;
	while (iterable.hasNext()) {
	    elem = iterable.next();

	    if (elem.getTextContent().equals(content)) {
		found  = true;
		break;
	    }
	    
	}

	if (!found) {
	    System.out.println("Could not find element with text content: " + content);
	    System.exit(10);
	}

	System.out.println("Found element with text content: " + content);

	HtmlElement enclosingTr = elem.getEnclosingElement("tr");

	if (enclosingTr ==null) {
	    System.out.println("Could not find enclosing td for element with text content: " + content);
	    System.exit(11);	    
	}

	List<HtmlElement> listOfInputElements = enclosingTr.getHtmlElementsByTagName("input");

	if (listOfInputElements.size() != 1) {
	    System.out.println("Found more than one possible match for input element to go with: " + content);
	    System.exit(12);	    
	}

	HtmlInput inputElem = (HtmlInput) listOfInputElements.get(0);

	inputElem.setValueAttribute(value);
	System.out.println("Set input field next to: " + content + " to value: " + value);

	System.out.println("Leaving " + mname);
    }


    /**
       fill series of fields with ids that have numeric suffixes _1, _2, etc.

       @param start first index (e.g. 0 or 1), typically 1
       @param id prefix, including _ if appropriate.
       @param values array of string values to fill in
    */
    
    public void fillSeriesOfFields(int start, String id, String [] values) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);
	
	System.out.println("  start=" + start + " id=" + id + " values=" + toString(values));

	int i=start;
	for (String s: values) {
	    HtmlInput selectElement = null;

	    selectElement = (HtmlInput) this.page.getElementById(id+i);
	    selectElement.setValueAttribute(s);

	    i++;
	}
	System.out.println("Leaving " + mname);
    }


    /**
       clickSubmit

       @param idOfSubmitButton  id of the input element with type submit
       @param valueOfSubmitButtont  value of the value attribute of submit button
    */
    
    public void clickSubmit(String idOfSubmitButton, String valueOfSubmitButton) {

	String mname = TraceHelper.getMethodName(0);
	System.out.println("Entering " + mname);

	try{ 

	    HtmlElement submitButton = getElemWithIdTypeAndValue(idOfSubmitButton,"submit",valueOfSubmitButton);

	    System.out.println("Clicking id=" + idOfSubmitButton + " with value=" + valueOfSubmitButton);

	    this.page = submitButton.click();

	    if (debug) {
		System.out.println("In " + mname + " page after click = " + this.page);
		System.out.println("In " + mname + " page after click = " + this.page.asXml());
	    }
	    
	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during " + mname + " " + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during " + mname + " " + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during "  + mname + " "+ e.getMessage());
	} catch (Exception e) {
	    System.out.println("Exception during "  + mname + " "+ e.getMessage());
	}
	
	System.out.println("Leaving " + mname);
	
    }

    /** 
	check current page for element with this id, and return its text contents 

	@param id of the element to look for
	@return text contents, or null if element not found
    */

    public String getTextContentById(String id) {
	
	HtmlElement elem = (HtmlElement) this.page.getElementById(id);
	
	if (elem==null)
	    return null;

	return elem.getTextContent();
    }


    /** 
	check current page for element with this id, and return one of its attribute values

	@param id of the element to look for
	@param attr attribute to return
	@return text contents, or null if element not found
    */

    public String getAttributeById(String id, String attr) {
	
	HtmlElement elem = (HtmlElement) this.page.getElementById(id);
	
	if (elem==null)
	    return null;

	return elem.getAttribute(attr);
    }

    /** 
	check current page for element with this id, and make sure its
	contents are as shown.  If not, exit program

	@param id of the element to look for
	@param content what it should contain
    */

    public void assertElemContains(String id, String content) {
	
	String actualContent = getTextContentById(id);
	
	if (actualContent==null) {
	    System.out.println("Element with id=" + id + " not found");
	    System.exit(1);
	}
	    
	if (!actualContent.equals(content)) {	    
	    System.out.println("Element with id=" + id + " does not equal " + content );
	    System.out.println("Element with id=" + id + " instead equals " + actualContent );
	    System.exit(2);
	}

	System.out.println("Element with id=" + id + " equals " + content );
	
    }

    /** 
	Get element with this id, type and value.
	If it doesn't, halts the program, so doubles an assertion.

    */

    public HtmlElement getElemWithIdTypeAndValue(String id, String type, String value) {

	HtmlElement elem = (HtmlElement) this.page.getElementById(id);

	if (elem == null) {
	    System.out.println("Element with id=" + id + " not found.   Was looking for type=" + type + " value=" + value);
	    System.exit(5);
	}
	
	String actualType = elem.getAttribute("type");
	String actualValue = elem.getAttribute("value");

	if ( actualType==null || !actualType.equals(type)) { 
	    System.out.println("Element with id=" + id + " did not have type " + type + ".  actualType=" + actualType + " Expected value=" + value);
	    System.exit(6);
	} 


	if ( actualValue==null || !actualValue.equals(value)) { 
	    System.out.println("Found submit button with id=" + id + "and type=" + type + " but value is not " + value);
	    System.out.println("Value was: " + actualValue);
	    System.exit(7);
	} 

	System.out.println("Element with id=" + id + " has type: " + actualType + " and value=" + actualValue);
	return elem;
    }

    /** 
	Just assert that this exists
    */

    public void  assertElemHasTypeAndValue(String id, String type, String value) {

	HtmlElement elem = getElemWithIdTypeAndValue(id,type,value);
    }

    public static String toString(String [] strings) {
	
	String r="";
	boolean first=true;
	for (String s:strings) {
	    r+= (first ? "{" : ",");
	    first = false;
	    r+= s;
	}
	r += "}";
	return r;
    }
    

    /**A main function used for testing purposes when writing the methods
     * 
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) {
	Scanner ans = new Scanner(System.in);
	IsisScrape test = new IsisScrape();

	Console console = System.console();
	String username = console.readLine("Username: ");
	test.setUsername(username);
	char[] password = console.readPassword("Password: ");
	test.setPassword(password);
	java.util.Arrays.fill(password, ' ');

	test.connect();

	test.clickSTAR();

	test.clickCONTINUE();

	test.mainMenu("R","");

	test.assertElemContains("ContentPlaceHolder1_Fldlit1","*** UCSB STUDENT ACCESS ***");

	test.assertElemContains("ContentPlaceHolder1_Fldlit2","REPORTS MENU");

	test.clickSubmit("ContentPlaceHolder1_ButtonEnter","Select Report/Next Page(ENTER)");



	test.assertElemHasTypeAndValue("ContentPlaceHolder1_PG_1_DDesc_2_PR_12","text","Student Extract");

	test.enterData("ContentPlaceHolder1_RptMapSelect","25");

	test.clickSubmit("ContentPlaceHolder1_ButtonEnter","Select Report/Next Page(ENTER)");

	test.assertElemContains("ContentPlaceHolder1_Fldlit2","STUDENT EXTRACT - JOB SUBMISSION");

	test.fillSeriesOfFields(1,"ContentPlaceHolder1_MapMajor_",new String [] {"CMPSC","PRCMP","CPSCI","PRCPS","CMPCS","CMPEN","PRCME","CRTST"} );

	test.enterDataAfterLabel("Expanded Extract:","Y");

	test.enterDataAfterLabel("Level:","U");

	test.enterDataAfterLabel("Data Source:","EOT");

	String [] quarters = {"F94","F95","F96","F97","F98","F99","F00","F01","F02","F03","F04","F05","F06","F07","F08","F09"};

	for (String qtr: quarters) {

	    test.enterDataAfterLabel("Quarter:",qtr);
	    
	    test.enterDataAfterLabel("Send file to:","pconrad+eot-y-" + qtr + "@cs.ucsb.edu");

	    test.clickSubmit("Submit(ENTER)");

	    System.out.println("Message: " + test.getAttributeById("ContentPlaceHolder1_RjeSubmitted","value"));	    

	    try {
		Thread.sleep(120000);
	    } catch (Exception e) {

	    }
	}



    }	
}
