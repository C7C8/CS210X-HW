import java.util.ArrayList;

public class Moment {
	private String name;
	private Image picture;
	ArrayList there;
	ArrayList<Float> smiles = new ArrayList<Float>();
	
	public Moment(String n, Image i, ArrayList who, ArrayList smiles){
	name = n;
	picture = i;
	there = who;
	this.smiles = smiles;
	}
	
	
}
