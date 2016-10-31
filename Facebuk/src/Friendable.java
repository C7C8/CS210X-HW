import java.util.ArrayList;

public abstract class Friendable extends Entity {
	public ArrayList<Friendable> friends;
	
	public Friendable(String nName, Image nPicture, ArrayList<Friendable> nFriends)
	{
		super(nName, nPicture);
		friends = nFriends;
	}
	
	public void setFriends(ArrayList<Friendable> nFriends){
		friends = nFriends;
	}
	
}