import java.util.ArrayList;

public class Person extends Friendable {
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private ArrayList<Possession> items = new ArrayList<Possession>();
	private ArrayList<Moment> moments = new ArrayList<Moment>();
		
	public Person(String nName, Image nPicture){
		super(nName, nPicture);
	}
	
	public void setPets(ArrayList<Pet> newPets){
		pets = newPets;
	}
	
	public void setPossessions(ArrayList<Possession> newItems){
		items = newItems;
	}
	
	public void setMoments(ArrayList<Moment> mom){
		for(Moment x : mom){
			moments.add(x);
		}
	}
	
	public Moment getOverallHappiestMoment(){
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
	
	public Friendable getFriendWithWhomIAmHappiest(){
		if (friends.isEmpty() || moments.isEmpty())
			return null; //Idiot check
		
		Friendable maxPerson = null; //The person with the highest happiness level so far.
		float maxHappy = 0;   	 //Happiness value
		
		for (Friendable friend : friends) {
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
}
