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

	    System.out.println("page = " + this.page);
	    System.out.println("page = " + this.page.asXml());

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


	    System.out.println("In " + mname + " page after click = " + this.page);
	    System.out.println("In " + mname + " page after click = " + this.page.asXml());

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

	    System.out.println("In " + mname + " page after click = " + this.page);
	    System.out.println("In " + mname + " page after click = " + this.page.asXml());

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

	    System.out.println("In " + mname + " page after click = " + this.page);
	    System.out.println("In " + mname + " page after click = " + this.page.asXml());


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

	    System.out.println("In " + mname + " page after click = " + this.page);
	    System.out.println("In " + mname + " page after click = " + this.page.asXml());


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
    }	
}
