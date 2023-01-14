/* DO NOT CHANGE OR REMOVE ANY IMPORTS */
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
/* ----------------------------------- */
/* Jonathan A. Guerrero.
[CS1101 - FA22] Comprehensive Lab 3
This work is to be done individually. It is not permitted to share,
reproduce, or alter any part of this assignment for any purpose.
Students are not permitted from sharing code, uploading this assignment
online in any form, or viewing/receiving/modifying code written from
anyone else. This assignment is part of an academic course at The
University of Texas at El Paso and a grade will be assigned for the work
produced individually by the student.
*/
public class CL3_Guerrero{
	public static void main(String [] args){

		//Welcome Header
		welcomeHeader("spotify-header.txt");

		//Create a FileReader class
		FileReader songReader = new FileReader("song-directory.csv");

        //Instantiate a scanner that reads keyboard.
        Scanner keyboard = new Scanner(System.in);

        //Declare variable for userInput for main menu. 
        int userRequest = 0;

        while(userRequest!=6){

        	System.out.println("What would you like to do today?");
        	System.out.println("1. Create a new playlist");
        	System.out.println("2. Add a song to playlist");
        	System.out.println("3. View playlist");
        	System.out.println("4. Delete playlist");
        	System.out.println("5. Show all songs available");
        	System.out.println("6. Close application");

        	userRequest = keyboard.nextInt();
        	//buffer 
        	keyboard.nextLine();
        	//********************************Create new playlist
        	if(userRequest==1){
        		System.out.println("What is the name of your new playlist?\n");
        		String userStringRequest = keyboard.nextLine();
        		createPlaylist(userStringRequest);

        	//********************************Add Song to Playlist
        	}else if(userRequest==2){
        		System.out.println("Enter the name of the playlist to add the song to:\n");
        		String userStringPlaylist = keyboard.nextLine();
        		if(playlistExists(userStringPlaylist)){
	        		System.out.format("+---------------------------------+---------------------------+------+%n");
					System.out.format("| # | TITLE                       | ALBUM                     | TIME |%n");
					System.out.format("+---------------------------------+---------------------------+------+%n");
	        		Song [] array = songReader.readPlaylist();
	        		//Go over every single line of the array and apply the getter methods to obtain the individual item.
	        		for( int i = 0; i < array.length; i++ ){
	        			System.out.printf("| %-2d| %-28s| %-26s| %-5s|\n", i+1, array[i].getTitle(), array[i].getAlbum(), array[i].getLength() );
						System.out.printf("| %-2s| %-28s| %-26s| %-5s|\n", "", array[i].getArtist(), "", "", "" );
						System.out.format("+---------------------------------+---------------------------+------+%n");
	        		}
	        		System.out.println("Look at the song directory available at Spotify. Enter the number\ncorresponding to the song you would like to add to " + "\"" + userStringPlaylist + "\".");
        			int songToPlaylist = 0;
        			songToPlaylist = keyboard.nextInt();
        			if(!addSongToPlaylist(userStringPlaylist, songToPlaylist)){
        				System.out.println("Song not part of the playlist!\n");
        			}
	        	}else{
	        		System.out.println("Playlist does not exist!");
	        	}
	        //****************************************View Playlist
        	}else if(userRequest==3){
        		System.out.println("Enter the name of the playlist you would like to view: \n");
        		String viewPlaylist = keyboard.nextLine();
        		System.out.format("+---------------------------------+---------------------------+------+%n");
				System.out.format("| # | TITLE                       | ALBUM                     | TIME |%n");
				System.out.format("+---------------------------------+---------------------------+------+%n");
        		FileReader playlistReader = new FileReader(viewPlaylist + ".txt");
        		Song [] array = playlistReader.readPlaylist();
        		for( int i = 0; i < array.length; i++ ){
	        			System.out.printf("| %-2d| %-28s| %-26s| %-5s|\n", i+1, array[i].getTitle(), array[i].getAlbum(), array[i].getLength() );
						System.out.printf("| %-2s| %-28s| %-26s| %-5s|\n", "", array[i].getArtist(), "", "", "" );
						System.out.format("+---------------------------------+---------------------------+------+%n");
	        		}

        	//*****************************************Delete Playlist
        	}else if(userRequest==4){
        		System.out.println("Enter the name of the playlist you would like to delete\n");
        		String deletePlaylistName = keyboard.nextLine();
        		System.out.println("Are you sure you want to delete " + "\n" + deletePlaylistName + "\n" + "? This action cannot be undone.\n Enter OK to continue, anything else to cancel.\n");
        		String confirmation = keyboard.nextLine();
        		if( confirmation.equalsIgnoreCase("ok") ){
        			deletePlaylist(deletePlaylistName);
        		}else{
        			System.out.println("Cancelled");
        		}
        	//******************************************View original Playlist
        	}else if(userRequest==5){
        		System.out.format("+---------------------------------+---------------------------+------+%n");
				System.out.format("| # | TITLE                       | ALBUM                     | TIME |%n");
				System.out.format("+---------------------------------+---------------------------+------+%n");
        		Song [] array = songReader.readPlaylist();
        		for( int i = 0; i < array.length; i++ ){
        			System.out.printf("| %-2d| %-28s| %-26s| %-5s|\n", i+1, array[i].getTitle(), array[i].getAlbum(), array[i].getLength() );
					System.out.printf("| %-2s| %-28s| %-26s| %-5s|\n", "", array[i].getArtist(), "", "", "" );
					System.out.format("+---------------------------------+---------------------------+------+%n");
        		}
        	//*******************************************Quit
        	}else if(userRequest==6){
        		welcomeHeader("goodBye.txt");
        	}else{
        		System.out.println("Please try again with the menu provided\n");
        	}
        }   

	} 


//************************************Methods*****************************************************************************//
	public static void welcomeHeader( String fileName ){
		try{
			Scanner spotifyHeader = new Scanner( new File( fileName ) );
			while( spotifyHeader.hasNext() ){
				System.out.println( spotifyHeader.nextLine() );
			}
		}
		catch( FileNotFoundException msg ){
			System.out.println(msg);
		}
	}//END welcomeHeader

		public static boolean fileExists( String fileName ){
		File file = new File( fileName );
		if(file.exists()){
			return true;
		}
		return false;
	}//END fileExists
		public static boolean playlistExists( String playlistName ){
		String checkPlaylist = playlistName + ".txt";
		if( fileExists(checkPlaylist) ){
			return true;
		}
	    return false;
  	}//END accountExists
  		public static int createPlaylist(String playlistName){

  			if( playlistExists(playlistName) ){
  				System.out.println("\n" + playlistName + "\"" + " playlist already Exists!\n");
  				return 1;
  			}else{
  				File file = new File(playlistName + ".txt");
  				try{
  					file.createNewFile();
  					//not only is a new txt file created, a header line is also automatically written.
  					//That way when displaying the songs, the first line is automaticall skipped and not skip songs. 
  					PrintWriter writeToFile = null;
  					writeToFile = new PrintWriter(new BufferedWriter(new FileWriter(playlistName + ".txt", true)));
					writeToFile.println( "Title, Artist, Album, Length");;
					writeToFile.close(); //Make sure to close the file or the top line will not work
  					System.out.println("\n" + playlistName + "\"" +  " has been succesfully created!\n");
  				}catch( IOException errorMsg ){
  					System.out.println( "IO Error: " + errorMsg + "\n");
  				}
  			}
  			return 0;
  	}//END createPlaylist
  		public static boolean addSongToPlaylist( String playlistName, int songChoice ){
  			FileReader songReader = new FileReader("song-directory.csv");
  			PrintWriter writeToFile = null;
  			if(playlistExists(playlistName)){
  				Song [] array = songReader.readPlaylist();
  				for( int i = 0; i < array.length; i++ ){
  					if( i+1 == songChoice){
  						try{
  							writeToFile = new PrintWriter(new BufferedWriter(new FileWriter(playlistName + ".txt", true)));
							writeToFile.println( array[i].getTitle() + "," + array[i].getArtist() + "," + array[i].getAlbum() + "," + array[i].getLength() );
							writeToFile.close(); //Make sure to close the file or the top line will not work
							System.out.println("Song was successfully added to your playlist: " + "\"" + playlistName + "\"\n");
							return true;
  						} catch ( IOException errMsg ) {
						System.out.println( "ERROR: " + errMsg  + "\n");
					}//END try catch
        			}
        		}
  			}else{
  				System.out.println("Playlist does not exist!\n");
  			}
        return false;
    }
        /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DELETE PLAYLIST
		This method deletes the given playlist by the user.
		It will take a playlistName as a parameter and be used to remove it,
		if and only if it already exists. Return true if successful, false otherwise.

        1. If the file does not exist, exit the method. You cannot delete a file
        that does not exist.

        @param playlistName: the name of the playlist given by the user
        @return true if successful, false otherwise

        METHODS USED:
            - exists()
            - delete() --> A new method from the File class (Google File Oracle)
	*/
	public static boolean deletePlaylist( String playlistName ){
		if( playlistExists(playlistName) ){
			File file = new File(playlistName + ".txt"); // file to be deleted
			if(file.delete()){
				System.out.println(playlistName + " was deleted. This action cannot be undone.");
				return true;
			}else{
				System.out.println("failed");
			}
		}
        return false;
    }
}