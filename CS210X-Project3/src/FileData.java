
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
public class FileData{

	private static HashMap<String,IMDBNode> actors = new HashMap<String,IMDBNode>();
	private static HashMap<String, IMDBNode> movies = new HashMap<String,IMDBNode>();
	
	private static void setActorsAndMovies(String[] reader){
		String lastActor="";
		for(String s : reader){
			if(s.length()>0 && (!(s.substring(0,1).equals(" ")
					|| s.substring(0,1).equals("\n")))){
				IMDBNode x = new IMDBNode(s);
				actors.put(s,x);
				lastActor = s;
			}
			else if(s.length()>0 && (s.substring(0,1).equals("\n"))){
				if(movies.containsKey(s)){
					movies.get(s).addNeighbor(actors.get(lastActor));
					actors.get(lastActor).addNeighbor(movies.get(s));
				}
				else{
					IMDBNode m = new IMDBNode(s);
					movies.put(s,m);
					m.addNeighbor(actors.get(lastActor));
					actors.get(lastActor).addNeighbor(m);
				}
			}	
		}
	}

	public static void populateActorsAndMovies(String file1, String file2) throws IOException{
		oneFilePop(file1);
		oneFilePop(file2);
	}
	
	private static void oneFilePop(String file1) throws IOException{
		String file_name = file1;
		try{
			ReadFile file = new ReadFile(file_name);
			String[] readLines = file.OpenFile();
			/*PrintStream out = new PrintStream(new FileOutputStream(s));
			System.setOut(out);
			for(int i = 0; i<readLines.length;i++){
				if(readLines[i].length()>0){
					System.out.println(readLines[i]);
				}
			}
			ReadFile newFile = new ReadFile(s);
			String[] reader = newFile.OpenFile();*/
			setActorsAndMovies(readLines);
		}
		catch (IOException e){
			System.out.println( e.getMessage());
		}
	}
	public static HashMap<String,IMDBNode> getActorHashMap(){
		return actors;
	}
	public static HashMap<String,IMDBNode> getMoviesHashMap(){
		return movies;
	}
}


