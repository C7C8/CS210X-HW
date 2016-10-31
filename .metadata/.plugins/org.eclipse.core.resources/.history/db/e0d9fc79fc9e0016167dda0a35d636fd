import java.util.ArrayList;

public class Pet {
	ArrayList friends = new ArrayList(); // can have both Person and Pet objects
	ArrayList<Person> owners = new ArrayList<Person>();
	ArrayList<Moment> moments = new ArrayList<Moment>();

	private String name;
	private Image picture;

	public Pet(){};

	public Pet(String n, Image i){
		name = n;
		picture = i;
	}

	public String getName(){
		return name;
	}
	public Image getImage(){
		return picture;
	}
	
	public void setFriends(ArrayList<Person> f){
		for(Person x : f){
		 friends.add(x);
		}
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
