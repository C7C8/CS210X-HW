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

	public Moment getMoments(){
		return moments.get(0);
	}

	public Moment getHappiestMoment(){
		if(moments.size()<1){
			return null;
		}
		Moment happiest = moments.get(0);
		for(int outter = 1; outter<moments.size();outter++){
			for(int inner = outter; inner<moments.size();inner++)
				if(moments.get(inner).getHappiness() > happiest.getHappiness()){
					happiest = moments.get(inner);
					break;
				}
		}
		return happiest;
	}
/**
	public Person getFriendWithWhomIAmHappiest(){
		if(moments.size()<1){
			return null;
		}
		Person mostHappy = friends.get(0);
		for(int outter = 1;outter<friends.size();outter++){
			for(int inner = outter;inner<friends.size();inner++){
				if(getFriendHappiness(friends.get(inner))>getFriendHappiness(mostHappy)){
					mostHappy = friends.get(inner);
					break;
				}
			}
		}
		return mostHappy;
	}

	public Float getFriendHappiness(Object o){
		Float happy = (float) 0;
		ArrayList who;
		ArrayList smiles;
		for(int outter=0;outter<moments.size();outter++){
			who = moments.get(outter).getThere();
			smiles = moments.get(outter).getSmiles();
			for(int inner = outter; inner < who.size();inner++){
				if(((ArrayList<Object>) who.get(inner)).contains(o)){
					happy = happy + (float)(smiles.get(inner));
				}
			}
		}
		return happy;
	}**/
}

