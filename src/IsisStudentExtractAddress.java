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

   	@author Phill Conrad

*/

public class IsisStudentExtractAddress {
	
    

    /**A main function used for testing purposes when writing the methods
     * 
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) {
	Scanner ans = new Scanner(System.in);
	IsisScrape is = new IsisScrape();

	Console console = System.console();
	String username = console.readLine("Username: ");
	is.setUsername(username);
	char[] password = console.readPassword("Password: ");
	is.setPassword(password);
	java.util.Arrays.fill(password, ' ');

	is.connect();

	is.clickSTAR();

	is.clickCONTINUE();

	is.mainMenu("R","");

	is.assertElemContains("ContentPlaceHolder1_Fldlit1","*** UCSB STUDENT ACCESS ***");

	is.assertElemContains("ContentPlaceHolder1_Fldlit2","REPORTS MENU");

	is.clickSubmit("ContentPlaceHolder1_ButtonEnter","Select Report/Next Page(ENTER)");



	is.assertElemHasTypeAndValue("ContentPlaceHolder1_PG_1_DDesc_2_PR_12","text","Student Extract");

	is.enterData("ContentPlaceHolder1_RptMapSelect","26");

	is.clickSubmit("ContentPlaceHolder1_ButtonEnter","Select Report/Next Page(ENTER)");

	is.assertElemContains("ContentPlaceHolder1_Fldlit2","STUDENT EXTRACT - JOB SUBMISSION");

	is.fillSeriesOfFields(1,"ContentPlaceHolder1_MapMajor_",new String [] {"CMPSC","PRCMP","CPSCI","PRCPS","CMPCS","CMPEN","PRCME","CRTST"} );

	is.enterDataAfterLabel("Addr Type:","M");

	is.enterDataAfterLabel("Level:","U");


	// String [] quarters = {"F94","F95","F96","F97","F98","F99","F00","F01","F02","F03","F04","F05","F06","F07","F08","F09","F10","F11","F12","F13"};

	String [] quarters = {"F97"};

	for (String qtr: quarters) {

	    is.enterDataAfterLabel("Quarter:",qtr);
	    
	    is.enterDataAfterLabel("Send file to:","pconrad+" + qtr + "am@cs.ucsb.edu");

	    is.clickSubmit("Submit(ENTER)");

	    System.out.println("Message: " + is.getAttributeById("ContentPlaceHolder1_RjeSubmitted","value"));	    

	    try {
		Thread.sleep(180000);
	    } catch (Exception e) {

	    }
	}



    }	
}
