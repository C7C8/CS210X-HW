import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	public static final int BRICK_WIDTH = 30;
	public static final int BRICK_HEIGHT = 30;
	
	// Instance variables
	private Rectangle rectangle;
	
	/**
	 * Gets the y position of the brick
	 * @return Y value
	 */
	public double getX () {
		return rectangle.getLayoutX() + rectangle.getTranslateX() + BRICK_WIDTH/2;
	}

	/**
	 * Gets the x position of the brick
	 * @return X value
	 */
	public double getY () {
		return rectangle.getLayoutY() + rectangle.getTranslateY() + BRICK_HEIGHT/2;
	}

	/**
	 * Constructs a new brick at the given X,Y coordinate. Height and width
	 * are defined by constants elsewhere
	 * @param newX X position of brick
	 * @param newY Y position of brick
	 */
	public Brick (int newX, int newY) {
		rectangle = new Rectangle(0, 0, BRICK_WIDTH, BRICK_HEIGHT);
		rectangle.setLayoutX(newX);
		rectangle.setLayoutY(newY);
		Random rand = new Random();
		
		//Create a maximum opacity rectangle of random color. Since there are 256 RGB values
		//per color, this means there can be 2^24 = 16,777,216 kinds of bricks!
		Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
		
		rectangle.setStroke(color);
		rectangle.setFill(color);
	}
	
	/**
	 * Gets the underlying rectangle for this brick
	 * @return An appropriately placed and sized rectangle
	 */
	public Rectangle getRectangle () {
		return rectangle;
	}

}
