import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//Does what it says on the box.
public class IMDBParser {
	static HashMap<String, IMDBNode> actors = new HashMap<String, IMDBNode>();
	static HashMap<String, IMDBNode> movies = new HashMap<String, IMDBNode>();
	
	public static HashMap<String, IMDBNode> getActors() {
		return actors;
	}

	public static HashMap<String, IMDBNode> getMovies() {
		return movies;
	}

	public static void parse(String fname) throws IOException{
		System.out.printf("Opening file %s...\n", fname);
		Scanner fscan = new Scanner(new File(fname), "ISO-8859-1");
		System.out.println("File opened!");
		
		//Advance the scanner until "^----\\t" is detected
		while (fscan.hasNext() && !fscan.nextLine().startsWith("----\t"));
		
		IMDBNode curActor = null;
		while (fscan.hasNextLine()){
			String line = fscan.nextLine();
			//TODO: make parser stop at end of giant file
		
			//Actor detected
			if (!line.startsWith("\t") && !line.isEmpty()){
				System.out.printf("Processing \"%s\"...\n", line.split("\\t+")[0]);
			}
			
			//Movie detected
		}
	}
	
	public static boolean populated(){
		return !(actors.isEmpty() && actors.isEmpty());
	}
}
