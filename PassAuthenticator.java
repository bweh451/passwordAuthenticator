package passAuthenticator;

//Imported the following java library functions, in order to use them.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.UnknownFormatConversionException;
import javax.swing.JOptionPane;


public class PassAuthenticator {
	
	//Created static variables for the PassAuthenticator class for later use.
	static File password;
	static Formatter f;
	
	public static void main(String[] args) {
		
		//Code is entered into a try-catch block to manage exceptions.
		try {
			
			//Finds the "password.txt" file.
			password = new File("password.txt");
			
			//Writes to the "password.txt" file.
			f = new Formatter(password);
		}
		
		//Catch block.
		//If the "password.txt" does not exist of can't be found then if will print out the following.
		catch(FileNotFoundException e) {
			System.out.println("Error - file not found!");
		}
			
		//String that prompts the user to enter the password they wish to create (first user input).
		String pass = JOptionPane.showInputDialog("Please enter the password you would like to create: \n\n(Note: Your password must contain atleast one number"
				+ " and your password cannot include the special character '%')");
		
		//String that prompts the user to re-enter the password they have entered in the first prompt in order to confirm it's creation (second user input).
		String validatePass = JOptionPane.showInputDialog("Please re-enter the password in order to confirm the creation:");		
		
		//Created to boolean variables related to both strings above.
		boolean passHasNum = false;
		boolean validatePassHasNum = false;
		
		passHasNum = checkNum(pass, passHasNum);
		
		validatePassHasNum = checkNum(validatePass, validatePassHasNum);
		
		//Created a variable that stores the following string.
		String entries = "Your first entry: " + pass + "\nYour second entry: " + validatePass;
		
		//Code is entered into a try-catch block to manage exceptions.
		try {
		
			//Main if statement.
			//If the both of the user's inputs contains a number then it will write both of the user's inputs to the "password.txt" file.
			if(passHasNum && validatePassHasNum) {
				f.format(entries);
				
				//Nested if statement
				//If the user's first input matches the user's second input, it will write the following to the "password.txt" file.
				if(pass.equals(validatePass)) {
					
					f.format("\n\nPassword creation successfull! \nYour password is: " + validatePass);
					
					//Prints the following message to the user.
					System.out.println("Password creation successfull!");
				}
				
				//Nested else statement.
				//If the above if statement is false, it will write out the following to the "password.txt" file.
				else {
					f.format("\n\nYour password creation was unsuccessful your second entry does not match the first! \nPlease try again");
					
					//Prints the following message to the user.
					System.out.println("Your password creation was unsuccessful your second entry does not match the first! \nPlease try again");
				}
			}
			
			//Main else statement if the main if statement is false, it will write the following to the "password.txt" file.
			else {
				f.format(entries + "\n\nYour password creation was unsuccessful. \nOne or both of your entries do not contain any numbers."
					+ "\nPassword must contain at least one number! \nPlease try again");
				
				//Prints the following message to the user.
				System.out.println("Your password creation was unsuccessful. \nOne or both of your entries do not contain any numbers."
						+ "\nPassword must contain at least one number! \nPlease try again");
			}
				
		}
		
		//First catch block.
		//If the user uses the special character "%" then it will print out the following.
		catch(UnknownFormatConversionException e) {
			System.out.println("Your password creation was unsuccessful. \nPlease refrain from using '%' in you password! \nPlease try again.");
		}
		
		//Second catch block.
		//If the user presses "cancel" on one of their entries it will print out the following message.
		catch(NullPointerException e) {
			System.out.println("Your password creation was unsuccessful. \nYou have cancelled your entry. \nPlease try again.");
		}
		
		//Insures that the formatter will close no matter what.
		finally {
			
			//Closes scanner and formatter.
			f.close();
		}
		
	}

	private static boolean checkNum(String pass, boolean passHasNum) {
		//For loop that iterates through the "pass" string.  
		for(int i = 0; i < pass.length(); i++) {
			
			//If the "pass" string contains a character that is a digit then "passHasNum" is set to true.
			if(Character.isDigit(pass.charAt(i))) {
				passHasNum = true;
			}	
		}
		return passHasNum;
	}

}
