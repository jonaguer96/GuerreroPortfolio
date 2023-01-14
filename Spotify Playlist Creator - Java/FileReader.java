import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileReader {
    private String fileName;

    public FileReader( String fileName ){
        this.fileName = fileName;
    }


    //Declare attribute

    /*  CONSTRUCTOR:
        Takes no parameters and sets "song-directory.csv" to attribute
    */

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CREATE SONG
		The method below will take a line from a file as a parameter,
		and turn that line into a song object and return it.
		This line must be split before as such:

			token[0] = Name of song
			token[1] = Name of artist
			token[2] = Name of album
			token[3] = Length of the song

        This is because in your file, your songs are viewed like this:
        Daylily,movements,Feel Something,3:28

        @param line: represents a line read from a file
        @return the line converted into a song object

        METHODS USED:
            - split()
	*/
    private Song createSong( String line ){
        //Change code as instructed
		Song song = null;
		return song;
	}

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CREATE PLAYLIST
		The method will take a playlistName and create a new file.
        Print true if successful, false otherwise

        1. Check if the playlist already exists. If it does, exit the method
        2. If the playlist does not exist, create it by using:
            - createNewFile()       HINT: This method only exists in the File class

        @param playlistName: the name of the playlist given by the user

        METHODS USED:
            - exists()
            - createNewFile()
	*/

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ADD SONG TO PLAYLIST
		The method will take a song choice, which is the number of the song
		from the file as well as the name of the playlist, and will store 
		its information into the given playlists text file.

        1. If the file does not exist, exit the method and return false
        2. If the file exists, traverse through this.songDirectory, you will traverse it
        songChoice number of times. Once songChoice is equal to the line you are currently
        in your file, you will save that line (From the file) into the text file of the
        playlistName.

        @param playlistName: the name of the playlist given by the user
        @param songChoice: the number of the song the user chose to save to their playlist
        @return true if successful, false otherwise

        METHODS USED:
            - exists()
	*/
	public boolean addSongToPlaylist( String playlistName, int songChoice ){
        return false;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CHECK PLAYLIST EXISTANCE
		The method will take the name of a playlist and check if it exists
		or not. Return true if it exists, false otherwise. 

        IMPORTANT: This method is exclusively for the main to use it.

        1. If the playlist already exists, return true
        2. False otherwise

        @param playlistName: the name of the playlist given by the user

        METHODS USED:
            - exists()
	*/
	public boolean checkPlaylistExistance( String playlistName ){
        return false;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DISPLAY SONG DIRECTORY
		This method will traverse through this.songDirectory and convert each 
        line into a song object, and print the song object in a nice output as
        shown on your comprehensive lab 3 instruction document.

        1. If the playlist does not exist, exit the method (return)
        2. If the playlist exists, you must convert each line into a song object
        and print it out in a nice and readable format.

        METHODS USED:
            File Class Method
                - exists( )
            FileReader Class Method
                - createSong( )
            Song Class Methods
                - getName( )
                - getAlbum( )
                - getLength( )
                - getArtist( )
	*/
	public void displaySongDirectory( ){
        /*  USE THE CODE BELOW IN THE CORRECT PLACES
            Only thing you need to fix is to fill in the [?] blocks. Leave everything else the same :)

         *  System.out.format("+---------------------------------+---------------------------+------+%n");
			System.out.format("| # | TITLE                       | ALBUM                     | TIME |%n");
			System.out.format("+---------------------------------+---------------------------+------+%n");

            System.out.printf("| %-2d| %-28s| %-26s| %-5s|\n", [?], [?], [?], [?] );
			System.out.printf("| %-2s| %-28s| %-26s| %-5s|\n", "", [?], "", "", "" );
			System.out.format("+---------------------------------+---------------------------+------+%n");
         */
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - TOTAL SONGS
        This method will traverse through a given playlist and return the total number
        of songs in that file. This will help set up the size of the songList in the
        Playlist class!

        1. If the file does not exist, exit the method (by returning 0)
        2. If it does exist, traverse through the playlist file and count the number
        of lines that exist within that file (same as your CL2)
        
        @param playlistName: the name of the playlist given by the user
     */
    private int totalSongs( ){
        File file = new File( this.fileName );
        Scanner readFile = null;
        int count = 0;
        try{
            readFile = new Scanner( file );
            //skip header file
            readFile.nextLine();
            while(readFile.hasNext() ){
                count++;
                readFile.nextLine();
            }
            return count;
        }catch(IOException errMsg){
            System.out.println("There was an error reading your file.");
        }
        return 0;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - READ PLAYLIST
		This method reads the playlist text file of any playlist specified, converts
        each line into a Song object, and stores it into a Song array.
        Lastly, it will return an array of Song

        @param playlistName: the name of the playlist given by the user
        @return an array of Song
        
        METHODS USED:
            - totalSongs()
            - createSong()
	*/
	public Song [] readPlaylist( ){
        File file = new File( this.fileName );
        Song [] songList = null;

        Song song = null;

        // ^^ "this." is put to make sure that it is referring to the attribute. 
        if( !file.exists() ){
            System.out.println(this.fileName + " does not exist.");
            songList = new Song[0];
            return songList;
        }

        Scanner readFile = null;
        int i = 0;
        String currLine = null;
        String [] splitCommand = null;
        songList = new Song[ totalSongs() ];

        try{
            readFile = new Scanner(file);
            //Skip header line
            readFile.nextLine();

            while( readFile.hasNext() ){
                currLine = readFile.nextLine();
                splitCommand = currLine.split(",");

                //Split command[0] = title
                //Split command[1] = artist
                //Split command[2] = album
                //Split command[3] = length (as a string)

                song = new Song( splitCommand[0], splitCommand[1], splitCommand[2], splitCommand[3] );

                songList[i] = song;
                i++;
            }// END loop

            readFile.close();
            return songList;
        }catch(IOException errMsg){
            System.out.println("Couldn't read your file");

        }


        //Change the code below as needed
        songList = new Song[0];
        return songList;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DISPLAY PLAYLIST
		This method will traverse through a specified playlist, turn it into a Playlist
		object, and display the playlist in a nice format.

        1. If the playlist file does not exist, exit the method (Return)
        2. If it does exist, create a new Playlist object and send over the name
        of the playlist as well as an array of Song to be set to the object.

        @param playlistName: the name of the playlist given by the user
        
        METHODS USED
            - exists()
            - readPlaylist()
            - viewPlaylist() --> from the Playlist class
	*/
	public void displayPlaylist( String playlistName ){
        //Playlist playlist = null;
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
	public boolean deletePlaylist( String playlistName ){
        return false;
    }
}
