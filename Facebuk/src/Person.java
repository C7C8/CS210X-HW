import java.util.ArrayList;

public class Person extends Entity {
	private ArrayList<Person> friends = new ArrayList<Person>();
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private ArrayList<Possession> items = new ArrayList<Possession>();
	private ArrayList<Moment> moments = new ArrayList<Moment>();
	private String name;
	private Image picture;
	
	public Person(String nName, Image nPicture){
		super(nName, nPicture);
	}
	
	public boolean equals(Person p) {
		return (name.equals(p.name) &&
				picture.equals(p.picture) &&
				friends.equals(p.friends) &&
				pets.equals(p.pets) &&
				items.equals(p.items) &&
				moments.equals(p.moments));
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
	
	public Person getFriendWithWhomIAmHappiest(){
		if (friends.isEmpty() || moments.isEmpty())
			return null; //Idiot check
		
		Person maxPerson = null; //The person with the highest happiness level so far.
		float maxHappy = 0;   	 //Happiness value
		
		for (Person friend : friends) {
			float timesAppeared = 0;
			float totalHappy = 0;
			for (Moment moment : moments) {
				boolean inMoment = false;
				for (Entity tempFriend : moment.participants)
					if (tempFriend.name.equals(friend.name))
						inMoment = true;
				if (!inMoment)
					continue;
				
				
				timesAppeared++;
				//Find the person's associated happiness value
				for (int i = 0; i < moment.participants.size(); i++)
					if (moment.participants.get(i).name.equals(name))
						totalHappy += moment.smiles.get(i);
			}
			
			if (totalHappy / timesAppeared > maxHappy) {
				maxPerson = friend;
				maxHappy = totalHappy / timesAppeared;
			}
		}
		
		return maxPerson;
	}

	//May be *slightly* type-unsafe. Fun!
	public Float getFriendHappiness(Object o){
		Float happy = (float) 0;
		ArrayList who;
		ArrayList smiles;
		for(int outter=0;outter<moments.size();outter++){
			who = moments.get(outter).participants;
			smiles = moments.get(outter).smiles;
			for(int inner = outter; inner < who.size();inner++){
				if(((ArrayList<Object>) who.get(inner)).contains(o)){
					happy = happy + (float)(smiles.get(inner));
				}
			}
		}
		return happy;
	}
}

