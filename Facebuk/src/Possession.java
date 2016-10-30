
public class Possession {

	private Person owner;
	private String name;
	private Image picture;
	private Float price;
	
	public Possession(String n, Image i, Float money){
		name = n;
		picture = i;
		price = money;
	}
	
	public Float getPrice(){
		return price;
	}
	
	public Image getImage(){
		return picture;
	}
	
	public void setOwner(Person p){
		owner = p;
	}
}
