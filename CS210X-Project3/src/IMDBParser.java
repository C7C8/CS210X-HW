import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

//Does what it says on the box.
public class IMDBParser {
	static HashMap<String, IMDBNode> actors = new HashMap<String, IMDBNode>();
	static HashMap<String, IMDBNode> movies = new HashMap<String, IMDBNode>();
	static int count = 0;
	
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
			count++;
			String line = fscan.nextLine();
			
			//THIS IS CHEATING AT FILE ENDING!
			if (line.contains("-----------------------------------------------------------------------------"))
				break;
		
			//Actor detected
			if (!line.startsWith("\t") && !line.isEmpty()){
				if (count > 10000){
					System.out.printf("I'm still here! Processing '%s'!\n", line);
					count = 0;
				}
				
				//Special handling is needed for this line, there's a movie name after the split
				curActor = new IMDBNode(line.split("\\t+")[0]);
				link(line.split("\\t+")[1], curActor);
			}
			
			//Movie detected
			if (line.startsWith("\t")){
				System.out.printf("\t");
				link(line.split("\\t+")[1], curActor);
			}
			
			//End times detected... I mean, newline!
			if (line.isEmpty()){
				if (!curActor.getNeighbors().isEmpty())
					actors.put(curActor.getName(), curActor);
			}
		}
		
		System.out.printf("Got %d actors/actresses and %d movies!", actors.size(), movies.size());
		fscan.close();
	}
	
	private static String parseMovie(String name){
		if (name.contains("\"") || //TV
			name.contains("(TV)")) //TV movie
			return null;
		
		Scanner scan = new Scanner(name);
		//Read: line noise
		String temp = scan.findInLine("([\\w\\d\\:\\-]+\\s)+\\([\\d\\?]{4}\\)");
		scan.close();
		return temp;
	}
	
	//Links an actor and a movie together
	private static void link(String name, IMDBNode actor){
		String mName = parseMovie(name);
		if (mName == null)
			return;
		
		IMDBNode movie;
		if (movies.containsKey(mName))
			movie = movies.get(mName);
		else{
			movie = new IMDBNode(mName);
			movies.put(mName, movie);
		}
		
		actor.addNeighbor(movie);
		movie.addNeighbor(actor);
	}
	
	public static boolean populated(){
		return !(actors.isEmpty() && actors.isEmpty());
	}
}
