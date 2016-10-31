import java.util.ArrayList;

public class Pet extends Friendable {
	public Pet(String nName, Image nPicture){
		super(nName, nPicture);
	}

	ArrayList<Person> owners = new ArrayList<Person>();
	ArrayList<Moment> moments = new ArrayList<Moment>();

	public void setMoments(ArrayList<Moment> newMoments){
		moments = newMoments;
	}
	
	public void setOwners(ArrayList<Person> f){
		for(Person x : f){
		 owners.add(x);
		}
	}
}
