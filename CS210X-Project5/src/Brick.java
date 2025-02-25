import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Brick {
	public static final int BRICK_WIDTH = 30;
	public static final int BRICK_HEIGHT = 30;
	
	// Instance variables
	private Rectangle rectangle;
	private Label img;
	
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
	public Brick(int newX, int newY) {
		rectangle = new Rectangle(0, 0, BRICK_WIDTH, BRICK_HEIGHT);
		rectangle.setLayoutX(newX);
		rectangle.setLayoutY(newY);
		int rand = Math.abs((new Random()).nextInt());
		Image brickImg = GameImpl.brick_red;
		if (rand % 3 == 0)
			brickImg = GameImpl.brick_blue;
		else if (rand % 3 == 1)
			brickImg = GameImpl.brick_red;
		else if (rand % 3 == 2)
			brickImg = GameImpl.brick_green;
		
		img = new Label("", new ImageView(brickImg));
		img.setLayoutX(getX() - BRICK_WIDTH/2);
		img.setLayoutY(getY() - BRICK_HEIGHT/2);
	}
	
	/**
	 * Gets the image associated with this brick.
	 * @return Image in the form of a label! That makes sense, right?
	 */
	public Label getImg() {
		return img;
	}

	/**
	 * Gets the underlying rectangle for this brick
	 * @return An appropriately placed and sized rectangle
	 */
	public Rectangle getRectangle () {
		return rectangle;
	}

}
