package djksolver;
import java.util.ArrayList;
import java.util.List;

/**
 * Original implementation of Dijstra's algorithm. Adapted from "Pathviz-2",
 * a pathfinding algorithm visualizer. https://github.com/Sourec/pathviz-2
 * Don't worry, Pathviz-2 was written by one of the authors of this program -
 * no stealing of code from the outside occurred.
 * 
 * I am intensely suspicious of this algorithm. It seems much too simple
 * to be correct.
 * 
 * @author sourec
 *
 */
public class GraphSearchEngineImpl implements GraphSearchEngine
{
	SortedList<DjkNode> openList;
	
	public GraphSearchEngineImpl(){
		openList = new SortedList<DjkNode>();
	}
	
	
	public List<Node> findShortestPath(Node s, Node t){
		openList.push(new DjkNode(s));
		DjkNode end = null;
		
		boolean complete = false;
		while (!complete){
			if (openList.size() == 0)
				return null; //No path exists.
			
			//Get the lowest g-value node
			DjkNode node = (DjkNode) openList.pop();
			
			for (Node e : node.orig.getNeighbors()){
				if (e == t){
					//Algorithm complete!
					end = new DjkNode(e);
					end.parent = node;
					complete = true;
					break;
				}
				
				if (e == node)
					continue; //Skip self
				
				DjkNode expNode = new DjkNode(e);
				expNode.parent = node;
				expNode.g = node.g + 1;
				openList.push(expNode);
			}
		}
		
		//Back track from t to s
		List<Node> cList = new ArrayList<Node>();
		for (DjkNode node = end; node.parent != null; node = node.parent)
			cList.add(node.orig);
		cList.add(s);
		
		openList.clear(); //Clean up! All those unneeded DjkNodes go away now.
		return cList;
	}

}
