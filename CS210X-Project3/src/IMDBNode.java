import java.util.ArrayList;

public class IMDBNode implements Node {
	ArrayList<Node> neighbors = new ArrayList<Node>();
	String name;
	
	public IMDBNode(String s){
		name = s;
	}
	public String getName(){
		return name;
	}
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}
	public void addNeighbor(Node n){
		neighbors.add(n);
	}
}
