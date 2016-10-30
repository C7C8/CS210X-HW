
public class Possession extends Entity {

	private Person owner;
	private Float price;
	
	public Possession(String n, Image i, Float money){
		super(n, i);
		price = money;
	}
	
	public Float getPrice(){
		return price;
	}
	
	public void setOwner(Person p){
		owner = p;
	}
}
