
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
public class FileData{

	private static HashMap<String,IMDBNode> actors = new HashMap<String,IMDBNode>();
	private static HashMap<String, IMDBNode> movies = new HashMap<String,IMDBNode>();

	public static void setActorsAndMovies(String[] reader){
		String lastActor="";
		String movieStr="";
		for(String s : reader){
			int cutOff = s.indexOf(":");
			if(s.length()==0){
				
			}
			else{
				if(cutOff>0 && !(actors.containsKey(s.substring(0, cutOff)))){
					lastActor = s.substring(0,cutOff);
					IMDBNode x = new IMDBNode(lastActor);
					actors.put(lastActor, x);
				}
				movieStr = s.substring(cutOff+1);
				if(!(movies.containsKey(s.substring(cutOff+1)))){
					IMDBNode m = new IMDBNode(movieStr);
					movies.put(movieStr,m);
					m.addNeighbor(actors.get(lastActor));
					actors.get(lastActor).addNeighbor(m);
				}
				else{
					if(lastActor.length()>0 && !(movies.get(movieStr).getNeighbors().contains(actors.get(lastActor)))){
					movies.get(movieStr).addNeighbor(actors.get(lastActor));
					actors.get(lastActor).addNeighbor(movies.get(movieStr));
					}
				}
			}
		}
		/*System.out.println(movies.size());
		Iterator Iter = actors.get("Rogan, Seth").getNeighbors().iterator();
		while(Iter.hasNext()){
			System.out.println(Iter.next());
		}*/
	}

	public static void populateActorsAndMovies(String file1, String file2) throws IOException{
		oneFilePop(file1);
		oneFilePop(file2);
	}

	private static void oneFilePop(String file1) throws IOException{
		String file_name = file1;
		try{
			ReadFile file = new ReadFile(file_name);
			file.OpenFile();
			/*PrintStream out = new PrintStream(new FileOutputStream(s));
			System.setOut(out);
			for(int i = 0; i<readLines.length;i++){
				if(readLines[i].length()>0){
					System.out.println(readLines[i]);
				}
			}
			ReadFile newFile = new ReadFile(s);
			String[] reader = newFile.OpenFile();
			file = new ReadFile(file);
			setActorsAndMovies(readLines);*/
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


