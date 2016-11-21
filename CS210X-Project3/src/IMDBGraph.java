import java.io.IOException;
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
		return (Collection<? extends Node>) nodes; //I hope this works!
	}

	@Override
	public Node getNodeByName(String name){
		return nodes.get(name);
	}
	
}
