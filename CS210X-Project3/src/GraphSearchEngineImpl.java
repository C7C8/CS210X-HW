
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphSearchEngineImpl implements GraphSearchEngine
{
	ArrayList<DjkNode> openList;
	HashMap<String, DjkNode> closedList;
	
	public GraphSearchEngineImpl(){
		openList = new ArrayList<DjkNode>();
		closedList = new HashMap<String, DjkNode>();
	}


	public List<Node> findShortestPath(Node s, Node t){
		openList.add(new DjkNode(s));
		DjkNode end = new DjkNode(t);


		System.out.println("Starting search!");
		boolean complete = false;

		while (!complete && !openList.isEmpty()){
			ArrayList<DjkNode> nextOpenList = new ArrayList<DjkNode>();

			//Loop through all of the open list
			for (DjkNode node : openList){

				//Explore the neighbors...
				for (Node e : node.orig.getNeighbors()){
					if (e == t){
						end.parent = node; //Algorithm complete!
						complete = true;
						break;
					}

					if (closedList.containsKey(e.getName()))
						continue; //Skip nodes that were already explored

					DjkNode expNode = new DjkNode(e);
					expNode.parent = node;
					nextOpenList.add(expNode);
				}

				if (complete)
					break;

				closedList.put(node.orig.getName(), node);
			}

			//By now the open list should be empty - swap to the "next" open list.
			//Thanks to java's bizarre way of handling objects, this is extremely
			//fast
			ArrayList<DjkNode> temp = openList;
			openList = nextOpenList;
			nextOpenList = temp;
		}

		openList.clear();
		closedList.clear();

		if (!complete)
			return null; //No path

		//Back track from t to s
		List<Node> cList = new ArrayList<Node>();
		for (DjkNode node = end; node.parent != null; node = node.parent)
			cList.add(node.orig);
		cList.add(s);

		return cList;
	}

}
