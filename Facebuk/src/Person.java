import java.util.ArrayList;

public class Person {

	private ArrayList<Person> friends = new ArrayList<Person>();
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private ArrayList<Possession> items = new ArrayList<Possession>();
	private ArrayList<Moment> moments = new ArrayList<Moment>();
	private String name;
	private Image picture;

	public Person(){};
	public Person (String n, Image i){
		name = n;
		picture = i;
	}
	
	public Image getImage(){
		return picture;
	}
	
	public String getName(){
		return name;
	}
	
	public void addFriend(Person p){
	friends.add(p);	
	}
	
	public void setFriends(ArrayList<Person> f){
		for(Person x : f){
		 friends.add(x);
		}
	}
	
	public void removeFriend(Person p){
		friends.remove(p);
	}
	
	public void addPet(Pet p){
		pets.add(p);
	}
	
	public void removePet(Pet p){
		pets.remove(p);
	}
	
	public void addPoss(Possession p){
		items.add(p);
	}
	
	public void removePoss(Possession p){
		items.remove(p);
	}
	
	public void setMoments(ArrayList<Moment> mom){
		for(Moment x : mom){
			moments.add(x);
		}
	}
	
	public Moment getHappiestMoment(){
		return null;
	}
	
	public Person getFriendWithWhomIAmHappiest(){
		return null;
	}
}

