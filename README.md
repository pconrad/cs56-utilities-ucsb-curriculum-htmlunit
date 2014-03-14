cs56-utilities-ucsb-curriculum-htmlunit
=======================================

TBD | pconrad | moniker85 | UCSB Curriculum Scraper based on HtmlUnit

Javadoc @ http://www.cs.ucsb.edu/~dconard/cs56/cs56-utilities-ucsb-curriculum-htmlunit/javadoc/


##High-level description (User’s View)

      This project connects to the following website: 
      https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx
      
      It then allows the user to perform all of the actions they would perform on the website 
      on their desktop. Users can search for courses based on department, quarter, and graduate level, 
      and the results are displayed after the user presses the submit button.
      
      Searched courses can also be saved and loaded at any time.
    
##Some internal documentation (Developer’s View)

      Originally, two users would be able to communicate over a client-server setup, but I couldn't 
      test this functionality at csil (couldn't find a free server port). It might be better to test
      this at home.
      
      When building the resultsPanel GUI, you have to pass in a lecture object.  This is done so that,
      in the future, if the user wants to load up and see several lectures at once, a simple next/previous 
      button can navigate through an arraylist of lecture objects.
      
      The main Jframe has at most 2 panels in its box layout (although those two panels can have multiple
      other components).  Whenever a button is pressed, and a new screen is shown, remove the second panel,
      and then add the new panel.
      
      

##How to run your project 

    Run the Project using ant run
