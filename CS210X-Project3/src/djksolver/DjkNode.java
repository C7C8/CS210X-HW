package djksolver;
import java.util.Collection;
import java.util.ArrayList;

public class DjkNode implements Node, Sortable<DjkNode>{
	String name;
	ArrayList<DjkNode> nbs; //I am NOT writing out neighbors every time!
	DjkNode parent;
	Integer g;
	
	DjkNode(String name){
		g = -1;
		parent = null;
		nbs = new ArrayList<DjkNode>();
		this.name = name;
	}
	
	
	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends Node> getNeighbors()
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean geq(Sortable<DjkNode> obj)
	{
		return g >= ((DjkNode)obj).g;
	}


	@Override
	public boolean leq(Sortable<DjkNode> obj)
	{
		return g <= ((DjkNode)obj).g;
	}

}
