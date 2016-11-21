
import java.util.Collection;
import java.util.ArrayList;

public class DjkNode implements Sortable<DjkNode>{
	DjkNode parent;
	Integer g;
	Node orig; //Today I have discovered exactly why "has-a" is more flexible than "is-a".
	
	DjkNode(){
		g = -1;
		parent = null;
		orig = null;
	}
	
	DjkNode(Node node){
		g = -1;
		parent = null;
		orig = node;
	}
	
	//Implemented from Sortable
	//No operator overloads? This is DISGUSTING.
	public boolean geq(Sortable<DjkNode> obj){
		return g >= ((DjkNode)obj).g;
	}

	public boolean leq(Sortable<DjkNode> obj){
		return g <= ((DjkNode)obj).g;
	}

}
