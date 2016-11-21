import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class IMDBGraph implements Graph {
	HashMap<String, IMDBNode> nodes;
	
	IMDBGraph(String actorsFilename, String actressesFilename) throws IOException {
		FileData.populateActorsAndMovies(actorsFilename, actressesFilename);
	}
	
	@Override
	public Collection<? extends Node> getNodes(){
		//For lack of a better solution...
		ArrayList<IMDBNode> list = new ArrayList<IMDBNode>();
		for (String key : nodes.keySet())
			list.add(nodes.get(key));
		return list;
	}

	@Override
	public Node getNodeByName(String name){
		return nodes.get(name);
	}
	
}
