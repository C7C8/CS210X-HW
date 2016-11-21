public class DjkNode implements Comparable<DjkNode>{
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

	@Override
	public int compareTo(DjkNode o) {
		if (g < o.g)
			return -1;
		else if (g > o.g)
			return 1;
		return 0;
	}

}
