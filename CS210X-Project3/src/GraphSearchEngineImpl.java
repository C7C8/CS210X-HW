
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphSearchEngineImpl implements GraphSearchEngine
{
	HashMap<String, DjkNode> openList;
	
	public GraphSearchEngineImpl(){
		openList = new HashMap<String, DjkNode>();
	}


	public List<Node> findShortestPath(Node s, Node t){
		openList.put(s.getName(), new DjkNode(s));
		DjkNode end = new DjkNode(t);


		System.out.println("Starting search!");
		boolean complete = false;


		HashMap<String, DjkNode> nextOpenList = new HashMap<String, DjkNode>();
		while (!complete && !openList.isEmpty()){

			//Loop through all of the open list
			for (String key : openList.keySet()){
				DjkNode node = openList.get(key);

				//Explore the neighbors...
				for (Node e : node.orig.getNeighbors()){
					if (e == t){
						end.parent = node; //Algorithm complete!
						complete = true;
						break;
					}

					if (openList.containsKey(e.getName()) || (node.parent != null && node.parent.orig == e))
						continue; //Skip nodes that were already explored or that are about to be explored.

					DjkNode expNode = new DjkNode(e);
					expNode.parent = node;
					nextOpenList.put(e.getName(), expNode);
				}

				if (complete)
					break;
			}

			openList.clear();
			HashMap<String, DjkNode> temp = openList;
			openList = nextOpenList;
			nextOpenList = temp;
		}

		if (!complete)
			return null; //No path

		//Back track from t to s
		List<Node> cList = new ArrayList<Node>();
		for (DjkNode node = end; node.parent != null; node = node.parent)
			cList.add(node.orig);
		cList.add(s);

		return cList;
	}

	private static class DjkNode {
		DjkNode parent;
		Integer g;
		Node orig; //Today I have discovered exactly why "has-a" is more flexible than "is-a".

		DjkNode(Node node){
			parent = null;
			orig = node;
		}
	}
}
