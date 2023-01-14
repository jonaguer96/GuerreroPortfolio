
/* DO NOT CHANGE OR REMOVE ANY IMPORTS */
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
/* ----------------------------------- */
/* Jonathan Guerrero>
[CS1101] CL2
Through my submission, I certify that all written code belongs
to me. I acknowledge that I will be held responsible for my
dishonesty should the Instructional Team find any evidence of
academic dishonesty.
*/

public class CL2_Guerrero{
	public static void main(String [] args){

		//Welcome Header
		welcomeHeader("blabber-header.txt");
        
        //Instantiate a scanner that reads keyboard.
        Scanner keyboard = new Scanner(System.in);


        //Declare variable for userInput for main menu. 
        String userRequest = "";

        //Break down the request into Command and Arguments.

        //As long as the user does not quit, we can continue inside the loop.
        while(!(userRequest.equalsIgnoreCase("quit"))){
        	System.out.println("Give a command, or type [quit] to exit, or type [help] to see all commands and their descriptions");
        	userRequest = keyboard.nextLine();
        	String[] arguments = userRequest.split(" ");

        	//***************************************************************************************CREATE AN ACCOUNT
	        if(arguments[0].equalsIgnoreCase("CreateAccount")){
				//Calling the createAccount method.
				createAccount(arguments[1]);

			//*************************************************************************************FOLLOWING AN ACCOUNT
	        }else if(arguments[0].equalsIgnoreCase("FollowAccount")){
	        	if(arguments.length>=3){

	      			followAccount(arguments[1], arguments[2]);
	      		}else{
	      			System.out.println("Argument length error or invalid command.\n");
	      		}
	      	//*******************************************************************************************POSTING A BLAB
	        }else if(arguments[0].equalsIgnoreCase("PostBlab")){
	        	if(arguments.length>=3){
		        	String [] requestList = userRequest.split(" ", 3);
		        	postBlab(requestList[1],requestList[2]);
	        	}else if(arguments.length==1){
	        		System.out.println("PostBlab is missing arguments. Consider typing [help] for more information.\n");
	        	}else{
	        		System.out.println("Argument length error or invalid commands.\n");
	        	}
	        //*********************************************************************************************VIEW TIMELINE
	        }else if(arguments[0].equalsIgnoreCase("ViewTimeline")){
	        	if(arguments.length==1){
	        		System.out.println("ViewTimeline is missing arguments. Consider typing [help] for more information.\n");
	        	}else{
	        		viewTimeline(arguments[1]);
	        	}


	        //*****************************************************************************************************HELP
	        }else if(arguments[0].equalsIgnoreCase("help")){
	        	commandList();
	        //*****************************************************************************************************QUIT
	        }else if(userRequest.equalsIgnoreCase("quit")){
	        	goodBye("goodBye.txt");
	        	System.out.println("Good-bye!\n");
	    	}else{
	        	System.out.println("Argument length error or invalid command.");
	        }
    	}

		
	}//END main

	/* -------------------------------------------------------------------------------- FILE EXISTS  
	   This method will take a fileName as a parameter, and
	   return true if it exists, false otherwise.
	*/
	public static boolean fileExists( String fileName ){
		File file = new File( fileName );
		if(file.exists()){
			return true;
		}
		return false;
	}//END fileExists


	/* -------------------------------------------------------------------------------- NUM LINES 
	   	This method will take a fileName as a parameter and return
	   	the number of lines this file has.
	   	** This method will make use of the following method:
	   		- fileExists()
	 */
	public static int numLines( String fileName ){

		//Initial counter of lines:
		int lines = 0;

		if(fileExists(fileName)){
			try {
				//We have to first declare a file object to begin reading. 
				File file = new File(fileName);
				//We then scan the file. 
				Scanner fileReader = new Scanner(file);

				//As long as the file reader has a new line, loop will keep going.
				while(fileReader.hasNextLine()){
				fileReader.nextLine();
				lines++;
				}
				//close scanner
				fileReader.close();
			}catch ( IOException errorMsg ) {
					System.out.println( "IO Error: " + errorMsg + "\n");
				}
		}else{
			return 0;
		}

		return lines;
	}//END numLines

	/* -------------------------------------------------------------------------------- READ FILE
	   	This method will take a fileName as a parameter and return an 
	   	array of Strings that stores each line in the file onto the array. 
	   	** This method will make use of the following methods:
		  	- fileExists()
		  	- numLines()
		Note: If the string array has a length of 0, simply return
		an empty array, as shown in the method.
	*/
	public static String [] readFile( String fileName ){
		String [] contents = {};
		return contents;
	}//END readFile

	/* -------------------------------------------------------------------------------- ACCOUNT EXISTS
		This method will take a username as a parameter and return true if
		the username text file exists, false otherwise.
		** This method will make use of the following method:
		   	- fileExists()
	*/
	public static boolean accountExists(String username){
		String checkUsername = username + ".txt";
		if( fileExists(checkUsername) ){
			return true;
		}
	    return false;
  	}//END accountExists

  	/*  -------------------------------------------------------------------------------- IS ALPHANUMERIC
  		This method will take a username as a parameter and check if it is
  		alphanumeric. A phrase is alphanumeric if it only contains uppercase 
  		letters, lowercase letters, and numbers. 
	 	Return true if the phrase is alphanumeric, false otherwise.

	 	Hint: You will use the following to help you:
	 		- Character.isUpperCase(char)
	 		- Character.isLowerCase(char)
	 		- Character.isDigit(char) 
	*/ 
	public static boolean isAlphanumeric( String username ) {
		boolean isAlphanumeric = false;

		for(int i = 0; i < username.length(); i++){
			if((username.charAt(i) >= 'a' && username.charAt(i) <= 'z') || (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') || (username.charAt(i) >= '0' && username.charAt(i) <= '9')){
				isAlphanumeric = true;
			}else{
				isAlphanumeric = false;
				}
			}

				return isAlphanumeric;
			}//END isAlphanumeric

  	/* -------------------------------------------------------------------------------- CREATE ACCOUNT  
		This method will take a username as a parameter and either:
			- return 0 if the account is successfully created.
			- return 1 if an account already exists with the given username
			- return 2 if the username entered is invalid (not alphanumeric)
		This method will make use of the following methods:
			- accountExists()
			- isAlphanumeric()

		Hint: You will use the following to help you:
	 		- .createNewFile()

	 	************* DO NOT FORGET TO .close() PRINTWRITER!
	*/
	public static int createAccount(String username){

		if( username.equalsIgnoreCase("blabs")){
			System.out.println("Error: " + "\""+ username + "\"" + " is not available.");
			System.out.println("Please Try Again!\n");
		}else if( accountExists(username) ){
			System.out.println("Error: " + "\""+ username + "\"" + " is already taken.\n");
			return 1;
		}else if( isAlphanumeric(username) ){
			File file = new File(username + ".txt");
			try {
				file.createNewFile();
				System.out.println("Success: " + "\"" + username + "\"" + " now has an account.\n");
			}catch ( IOException errorMsg ) {
				System.out.println( "IO Error: " + errorMsg + "\n");
			}
		}else{
			System.out.println("Error: " + "\""+ username + "\"" + " is not Alphanumeric\n");
			System.out.println("Please Try Agin.\n");
			return 2;
			}

		return 0;
	}//END createAccount

	/* -------------------------------------------------------------------------------- FOLLOW ACCOUNT  
		This method will take username1 and username2. It will make 
		username1 follow username2.
			- Return 0 upon success.
	   		- Return 1 if either username doesnt exist or is invalid
	   		- Return 2 if a user is	trying to follow themselves
	   		- Return 3 if the user already follows the other user 

	   	This method will make use of the following methods:
	   		- accountExists()
	   		- readFile()

	   	NOTE: You must use the following class to append onto a file:
	   		- PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter (nameOfFile, true) ) )

	   	************* DO NOT FORGET TO .close() PRINTWRITER!
	*/
	public static int followAccount(String username, String usernameToFollow){
		
		PrintWriter writeToFile = null;
		String fileName = username + ".txt";

		if(!username.equalsIgnoreCase(usernameToFollow)){
			//The first thing we do is make sure user is not trying to follow themselves. 
			if( accountExists(username) ){
				//We have to make sure both accounts do exist; if not throw an error. 
				if( accountExists(usernameToFollow) ){
					try {
					writeToFile = new PrintWriter(new BufferedWriter(new FileWriter(username + ".txt", true)));
					writeToFile.println( usernameToFollow );
					writeToFile.close(); //Make sure to close the file or the top line will not work
					} catch ( IOException errMsg ) {
					System.out.println( "ERROR: " + errMsg  + "\n"); ;
					}//END try catch
					System.out.println( "\"" + username + "\"" + " now follows " + "\"" + usernameToFollow + "\"\n" );
				} else{
				System.out.println( "\"" + usernameToFollow + "\"" + " does not exist.\n" );
				return 1;
				}
			} else{
				System.out.println(  "\"" + username + "\"" + " does not exist.\n");
				return 1;
			}
		} else{
			System.out.println("You cannot follow yourself.\n");
			return 1;
		}

		return 0;
	}//END followAccount

	/* -------------------------------------------------------------------------------- POST BLAB  
		This method will take a username and a blabMessage as a parameter
		and append the blabMessage into the blabs.txt file.
			- Return 0 upon success
	 		- Return 1 if the username doesn't exist or is invalid

	 	This method will make use of the following methods:
	 		- numLines() 

	 	NOTE: You must use the following class to append onto a file:
	   		- PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter (nameOfFile, true) ) )

	   	************* DO NOT FORGET TO .close() PRINTWRITER!
	*/
	public static int postBlab(String username, String blab){
		PrintWriter writeToFile = null;
		if(accountExists(username)){
			if(blab.charAt(0) == '"'){
				blab = blab.replace("\"", "");
				try {
					writeToFile = new PrintWriter(new BufferedWriter(new FileWriter("blabs.txt", true)));
					writeToFile.println( username + "-" + blab );
					writeToFile.close(); //Make sure to close the file or the top line will not work
					System.out.println("Success: " + "\"" + blab + "\" blab was posted by " + username + "\n");
					} catch ( IOException errMsg ) {
					System.out.println( "ERROR: " + errMsg  + "\n"); ;
					}//END try catch			
			}else{
					System.out.println("Invalid request. Enter [help] to see available commands.\n");
				}
		}else{
			System.out.println("\"" + username + "\"" + " does not exist.\n");
		}

		return 0;
	}//END postBlab

	/* -------------------------------------------------------------------------------- DISPLAY ARRAY 
		This method will take an array of strings containing the blabs
		from the blabs.txt file and an array of strings containing the
		names of the accounts a specific user follows.
		It will print out each element in the given array on it's own 
		line as shown on the assignment for viewTimeline.
	*/
  	public static void displayArray(String[] blabs, String[] followedAccounts) throws IOException{
  		welcomeHeader("timeline.txt");

  		for(int i=0; i<blabs.length; i++){
  			//We go through each line of the blabs file as an array. 
  			String [] individualBlabs = blabs[i].split("-", 2);
  			//The instace of the line is then separated into two; to later compare if user followes the user that posted the blab.
  			//It is separated between user and what user posted.
  			for(int j=0; j<followedAccounts.length; j++){
  				//We then compare if the blab was posted by an account the user follows.
  				//If so, we print it.
  				if(individualBlabs[0].equalsIgnoreCase(followedAccounts[j])){
  					System.out.println(individualBlabs[0]);
  					System.out.println(individualBlabs[1]);
  					System.out.println("----------------------------------");
  				}
  			}
  		}



  	}//END displayArray

	/* -------------------------------------------------------------------------------- VIEW TIMELINE
		This method will take a username as a parameter and will display
		all the blabs this user follows in a timeline.
			- Return 0 upon success OR if the user is not following anyone (as it is still successfull)
				- if the user is not following anyone, display a message as such:
				>> You may want to consider following someone to view a timeline.
			- Return 1 if the username doesn't exist or is invalid

		This method will make use of the following methods:
			- accountExists()
			- readFile()
			- displayArray()
	*/
	public static int viewTimeline( String username ) {

	    String blabs = "blabs.txt";
	    String user = username + ".txt";
	    File checkifUserisFollowing = new File(user);

		String[] blabsInFile = new String[numLines(blabs)];
		String[] accountsUserFollows = new String[numLines(user)];


		if(accountExists(username)){
			if(checkifUserisFollowing.length() == 0){
				System.out.println("You might want to consider following more accounts\n");
				return 0;
			}

			try {
				//This section converts all lines on blabs file into an array called blabsInFile
				File file = new File(blabs);
				Scanner fileReader = new Scanner(file);

				//As long as the file reader has a new line, loop will keep going.
				while(fileReader.hasNextLine()){
					for(int i=0; i<numLines(blabs); i++){
						blabsInFile[i] = fileReader.nextLine();
					}
				}
				//close scanner
				fileReader.close();

				//This section converts all lines on username file into an array called accountsUserFollows
				File file2 = new File(user);
				Scanner fileReader2 = new Scanner(file2);

				while(fileReader2.hasNextLine()){
					for(int i=0; i<numLines(user); i++){
						accountsUserFollows[i] = fileReader2.nextLine();
					}
				}
			}catch ( IOException errorMsg ) {
				System.out.println( "IO Error: " + errorMsg + "\n");
				}
		}else{
			System.out.println("Error: " + "\"" + username + "\"" + " is invalid or does not exist.\n");
			return 1;
		}
		try{
			displayArray(blabsInFile,accountsUserFollows);
		}catch ( IOException errorMsg ) {
			System.out.println( "IO Error: " + errorMsg + "\n");
		}



		//displayArray(blabs, username)

		return 0;
	}//END viewTimeline

	public static void goodBye( String fileName ){
		try{
			Scanner blabberBye = new Scanner( new File( fileName ) );
			while( blabberBye.hasNext() ){
				System.out.println( blabberBye.nextLine() );
			}
		}
		catch( FileNotFoundException msg ){
			System.out.println(msg);
		}
	}//END welcomeHeader	

	/* ----------------------------------------------- DO **NOT** DELETE OR MODIFY THE CODE BELOW THIS LINE ----------------------------------------------- */

	/* -------------------- APPEND BLAB  */
	public static String [] appendBlab( String userInput ){
		userInput = userInput.replace("\"", ""); //gets rid of quotations
		String [] blab = userInput.split(" ");

		String [] request = new String[3];
		request[0] = blab[0]; //store command in first position of request
		request[1] = blab[1]; //stores username in second position of request
		request[2] = "";

		//Ignore first position as that contains the command
		for( int i = 2; i < blab.length; i++ ){
			request[2] += blab[i] + " "; //appends message in third position
		}

		return request;
	}//END appendBlab

	/* -------------------- WELCOME HEADER  */
	public static void welcomeHeader( String fileName ){
		try{
			Scanner blabberHeader = new Scanner( new File( fileName ) );
			while( blabberHeader.hasNext() ){
				System.out.println( blabberHeader.nextLine() );
			}
		}
		catch( FileNotFoundException msg ){
			System.out.println(msg);
		}
	}//END welcomeHeader

	/* -------------------- COMMAND LIST  */
	public static void commandList(){
		System.out.println(
			"\t[CreateAccount username]\n\n\tThis command expects you to enter" 
			+"\n\ta unique username. Your username must contain the following alpha-"
			+"\n\tnumeric values: a-z, A-Z, and 0-9 and must be greater than 4 in length."
			+"\n\n\n"
			+"\t[FollowAccount username username_to_follow]\n\n\tThis command expects you to"
			+"\n\tto enter the name of the user you would like to follow. This will allow"
			+"\n\tyou to see their blabs on your feed. You may NOT follow yourself."
			+"\n\n\n"
			+"\t[PostBlab username \"your message in quotations\"]\n\n\tThis command will allow you to"
			+"\n\tpost a blab."
			+"\n\n\n"
			+"\t[ViewTimeline userName]\n\n\tThis command will allow the current logged in user to view"
			+"\n\tall of the blabs of the user's they follow. By default they will view the"
			+"\n\tblabs in chronological order, unless specified."
			+"\n\n\n"
			+"\t[quit]\n\n\tThis command will end the program\n"
		);
	}//END commandList
}