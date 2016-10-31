import java.util.ArrayList;

public abstract class Friendable extends Entity {
	public ArrayList<Friendable> friends;
	
	public Friendable(String nName, Image nPicture)
	{
		super(nName, nPicture);
		friends = new ArrayList<Friendable>();
	}
	
	public void setFriends(ArrayList<Friendable> nFriends){
		friends = nFriends;
	}
	
}