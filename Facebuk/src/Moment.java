import java.util.ArrayList;

public class Moment extends Entity {
	public ArrayList<Entity> participants;
	public ArrayList<Float> smiles = new ArrayList<Float>(); //matches up with participants

	public Moment(String n, Image i, ArrayList who, ArrayList nSmiles){
		super(n, i);
		name = n;
		picture = i;
		participants = who;
		smiles = nSmiles;
	}

	public Float getHappiness(){
		Float happy = (float) .0;
		for(int x=0;x<smiles.size();x++){
			happy+= smiles.get(x);
		}
		return (happy/(smiles.size()));
	}
}
