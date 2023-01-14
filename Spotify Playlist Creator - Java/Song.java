import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
//*********************************************************************OBJECTS********************************************************************
public class Song{
    private String title;
    private String artist;
    private String album;
    private String length;

    public Song(){
        title = null;
        artist = null;
        album = null;
        length = null;
    }

    public Song(String title, String artist, String album, String length){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    //*************getter Title**********
    public String getTitle(){
        return this.title;
    }

    //*************Setter Title************
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    //**************Getter Artist***********
    public String getArtist(){
        return this.artist;
    }
    //***************Setter Artist**********
    public void setArtist(String newArtist){
        this.artist = newArtist;
    }
    //***************Getter Album************
    public String getAlbum(){
        return this.album;
    }
    //****************Setter Album***********
    public void setAlbum(String newAlbum){
        this.album = newAlbum;
    }
    //*****************Getter Length***********
    public String getLength(){
        return this.length;
    }
    //*****************Setter Length************
    public void setLength(String newLength){
        this.length = newLength;
    }

    //Method Overriding
    public String toString(){
        return "Title: " + this.title + "\n" 
        + "Artist: " + this.artist + "\n"
        + "Album: " + this.album + "\n"
        + "Length: " + this.length + "\n";
    }


}