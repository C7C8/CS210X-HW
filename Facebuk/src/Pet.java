import java.util.ArrayList;

public class Pet extends Friendable {
	public Pet(String nName, Image nPicture){
		super(nName, nPicture, new ArrayList<Friendable>());
	}

	ArrayList<Entity> friends = new ArrayList<Entity>(); // can have both Person and Pet objects
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
