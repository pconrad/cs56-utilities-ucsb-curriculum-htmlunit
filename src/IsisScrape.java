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
    
    
    public void connect() {
	try{ 
	    page = webClient.getPage(url);
	} catch (FailingHttpStatusCodeException e) {
	    System.out.println("FailingHttpStatusCodeException during getDeptOptions:" + e.getMessage());
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException during getDeptOptions:" + e.getMessage());
	} catch (IOException e) {
	    System.out.println("IOException during getDeptOptions:" + e.getMessage());
	}
	
    }

    /**A main function used for testing purposes when writing the methods
     * 
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) {
	Scanner ans = new Scanner(System.in);
	IsisScrape test = new IsisScrape();

	test.connect();
	
    }	
}
