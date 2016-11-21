package Parser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
public class FileData{
	
	public static ArrayList<String> actors = new ArrayList<String>();
	public static ArrayList<String> movies = new ArrayList<String>();
	
	// if we make these ArrayList<LinkedList<Node>> then the first Node will be the actor
	// and every subsequent Node will be the movies....
	private static void setActorsAndMovies(String[] reader){
		for(String s : reader){
			if(s.length()>0 && (!(s.substring(0,1).equals(" ")
							   || s.substring(0,1).equals("\n")))){
				actors.add(s);
			}
			else if(s.length()>0 && (s.substring(0,1).equals("\n"))){
				movies.add(s);
			}	
		}
	}
	
	private static ArrayList<String> getActors(String[] reader){
		ArrayList<String> actor = new ArrayList<String>();
		for(String s : reader){
			if(s.length()>0 && (!(s.substring(0,1).equals(" ")
							   || s.substring(0,1).equals("\n")))){
				actor.add(s);
			}	
		}
		return actor;
	}
	
	private static ArrayList<String> getMovies(String[] reader){
		ArrayList<String> movie = new ArrayList<String>();
		for(String s : reader){
			if(s.length()>0 && (s.substring(0,1).equals("\n"))){
				movie.add(s);
			}	
		}
		
		return movie;
	}

	public static void main(String[] args)throws IOException{
		String file_name = "C:/Users/Carol/Downloads/IMDB/smallTestText";
		try{
			ReadFile file = new ReadFile(file_name);
			ReadNonAsciiFile file2= new ReadNonAsciiFile(file_name);
			String[] readLines = file.OpenFile();
			PrintStream out = new PrintStream(new FileOutputStream("smallTestText_changed.txt"));
			System.setOut(out);
			for(int i = 0; i<readLines.length;i++){ // watch as 5 vs 6 is a difference of two lines////
				if(readLines[i].length()>0){
					System.out.println(readLines[i]);
				}
			}
			ReadFile newFile = new ReadFile("smallTestText_changed.txt");
			String[] reader = newFile.OpenFile();
			setActorsAndMovies(reader);
			
			System.out.print(actors.get(actors.size()-1));
			System.out.print(movies.get(movies.size()-1));
		}
		catch (IOException e){
			System.out.println( e.getMessage());
		}
	}
}
