import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	public static final int BRICK_WIDTH = 30;
	public static final int BRICK_HEIGHT = 30;
	
	// Instance variables
	private Rectangle rectangle;
	
	public double getX () {
		return rectangle.getLayoutX() + rectangle.getTranslateX() + BRICK_WIDTH/2;
	}

	public double getY () {
		return rectangle.getLayoutY() + rectangle.getTranslateY() + BRICK_HEIGHT/2;
	}

	public Brick (int newX, int newY) {
		rectangle = new Rectangle(0, 0, BRICK_WIDTH, BRICK_HEIGHT);
		rectangle.setLayoutX(newX);
		rectangle.setLayoutY(newY);
		Random rand = new Random();
		Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
		
		rectangle.setStroke(color);
		rectangle.setFill(color);
	}
	
	public void setLayout(Rectangle rect, int num){
		rectangle.setLayoutX(80*(num%5+1)-55);
		rectangle.setLayoutY(80*(num/5+1));
			
	}
	
	public Rectangle getRectangle () {
		return rectangle;
	}

}
