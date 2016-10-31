import java.util.ArrayList;

public class Person extends Entity {
	private ArrayList<Person> friends = new ArrayList<Person>();
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private ArrayList<Possession> items = new ArrayList<Possession>();
	private ArrayList<Moment> moments = new ArrayList<Moment>();
		
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
	
	
	
	public void setMoments(ArrayList<Moment> mom){
		for(Moment x : mom){
			moments.add(x);
		}
	}
	
	/**
	 * getHappiestMoment() takes an Entity object and determines which Moment was its happiest 
	 * "Happiest" based on the average smileValue for all participants in that Moment
	 * @return Moment, null if there are no moments.
	 */
	public Moment getHappiestMoment(){
		if(moments.isEmpty()){
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
				if (moment.getPersonByName(friend.name) == null)
					continue;
				
				timesAppeared++;
				totalHappy = totalHappy + moment.getPersonHappinessByName(friend.name);
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

