import java.awt.*;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	/**
	 * The width of the BRICK.
	 */
	public static final int BRICK_WIDTH = 30;
	/**
	 * The height of the BRICK.
	 */
	public static final int BRICK_HEIGHT = 30;
	
	// Instance variables
	private Rectangle rectangle;
	
	/**
	 * @return the x coordinate of the center of the BRICK.
	 */
	public double getX () {
		return rectangle.getLayoutX() + rectangle.getTranslateX() + BRICK_WIDTH/2;
	}

	/**
	 * @return the y coordinate of the center of the BRICK.
	 */
	public double getY () {
		return rectangle.getLayoutY() + rectangle.getTranslateY() + BRICK_HEIGHT/2;
	}

	public Brick (int count) {
		final double x = BRICK_WIDTH/2;
		final double y = BRICK_HEIGHT/2;

		rectangle = new Rectangle(0, 0, BRICK_WIDTH, BRICK_HEIGHT);
		setLayout(rectangle,count);
		Random rand = new Random();
		int num = rand.nextInt(4);
		Color color = Color.GREEN;
		if(num==1){
			color = Color.BLUE;
		}
		else if(num==2){
			color = Color.RED;
		}
		else if(num==3){
			color = Color.YELLOW;
		}
		else if(num==4){
			color = Color.ORANGE;
		}
		rectangle.setStroke(color);
		rectangle.setFill(color);
	}
	
	public void setLayout(Rectangle rect, int num){
		rectangle.setLayoutX(80*(num%5+1)-55);
		rectangle.setLayoutY(80*(num/5+1));
			
	}
	/**
	 * @return the Rectangle object that represents the paddle on the game board.
	 */
	public Rectangle getRectangle () {
		return rectangle;
	}

}
