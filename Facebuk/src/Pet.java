import java.util.ArrayList;

public class Pet extends Entity {
	public Pet(String nName, Image nPicture){
		super(nName, nPicture);
	}

	ArrayList<Entity> friends = new ArrayList<Entity>(); // can have both Person and Pet objects
	ArrayList<Person> owners = new ArrayList<Person>();
	ArrayList<Moment> moments = new ArrayList<Moment>();

	public void setFriends(ArrayList<Entity> f){
		for(Entity x : f){
		 friends.add(x);
		}
	}
	
	public void setMoments(ArrayList<Moment> newMoments){
		moments = newMoments;
	}
	
	public void setOwners(ArrayList<Person> f){
		for(Person x : f){
		 owners.add(x);
		}
	}

	public void removeFriend(Person p){
		friends.remove(p);
	}
}
