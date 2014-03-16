import java.io.Console;

/**

Example of how to read a password without echoing it

*/

public class PasswordExample {


    public static void main (String [] args) {


	Console console = System.console();
	String username = console.readLine("Username: ");
	char[] password = console.readPassword("Password: ");

	String passwordString = new String(password);
	java.util.Arrays.fill(password, ' ');

	System.out.println("Username: " + username);
	System.out.println("Password: " + passwordString);
    }



}