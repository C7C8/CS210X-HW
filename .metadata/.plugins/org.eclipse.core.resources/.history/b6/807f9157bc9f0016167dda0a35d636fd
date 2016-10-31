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
	
	//Returns the person if they're in here, null otherwise
	public Entity getPersonByName(String fName)
	{
		for (Entity p : participants)
			if (p.name.equals(fName))
				return p;
		return null;
	}
	
	//Returns the happiness of a given person by name.
	//If the person isn't in the moment, returns -1.
	public Float getPersonHappinessByName(String fName)
	{
		for (int i = 0; i < participants.size(); i++)
			if (participants.get(i).name.equals(fName))
				return smiles.get(i);
		
		return -1.f;
	}
}
