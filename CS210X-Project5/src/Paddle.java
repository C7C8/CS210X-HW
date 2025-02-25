import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Paddle {
	// Constants
	public static final int PADDLE_WIDTH				= 100;
	public static final int PADDLE_HEIGHT				= 5;
	public static final double INITIAL_Y_LOCATION_FRAC	= 0.8;
	public static final double MIN_Y_LOCATION_FRAC		= 0.7;
	public static final double MAX_Y_LOCATION_FRAC		= 0.9;

	// Instance variables
	private Rectangle rectangle;

	/**
	 * @return the x coordinate of the center of the paddle.
	 */
	public double getX () {
		return rectangle.getLayoutX() + rectangle.getTranslateX() + PADDLE_WIDTH/2;
	}

	/**
	 * @return the y coordinate of the center of the paddle.
	 */
	public double getY () {
		return rectangle.getLayoutY() + rectangle.getTranslateY() + PADDLE_HEIGHT/2;
	}

	/**
	 * Constructs a new Paddle whose vertical center is at INITIAL_Y_LOCATION_FRAC * GameImpl.HEIGHT.
	 */
	public Paddle () {
		final double x = PADDLE_WIDTH/2;
		final double y = INITIAL_Y_LOCATION_FRAC * GameImpl.HEIGHT;

		rectangle = new Rectangle(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
		rectangle.setLayoutX(x-PADDLE_WIDTH/2);
		rectangle.setLayoutY(y-PADDLE_HEIGHT/2);
		rectangle.setStroke(Color.GREEN);
		rectangle.setFill(Color.GREEN);
	}

	/**
	 * @return the Rectangle object that represents the paddle on the game board.
	 */
	public Rectangle getRectangle () {
		return rectangle;
	}

	/**
	 * Moves the paddle so that its center is at (newX, newY), subject to
	 * the horizontal constraint that the paddle must always be completely visible
	 * and the vertical constraint that its y coordinate must be between MIN_Y_LOCATION_FRAC
	 * and MAX_Y_LOCATION_FRAC times the game height.
	 * @param newX the newX position to move the center of the paddle.
	 * @param newY the newX position to move the center of the paddle.
	 */
	public void moveTo (double newX, double newY) {
		if (newX < PADDLE_WIDTH/2) {
			newX = PADDLE_WIDTH/2;
		} else if (newX > GameImpl.WIDTH - PADDLE_WIDTH/2) {
			newX = GameImpl.WIDTH - PADDLE_WIDTH/2;
		}

		if (newY < MIN_Y_LOCATION_FRAC * GameImpl.HEIGHT) {
			newY = MIN_Y_LOCATION_FRAC * GameImpl.HEIGHT;
		} else if (newY > MAX_Y_LOCATION_FRAC * GameImpl.HEIGHT) {
			newY = MAX_Y_LOCATION_FRAC * GameImpl.HEIGHT;
		}

		rectangle.setTranslateX(newX - (rectangle.getLayoutX() + PADDLE_WIDTH/2));
		rectangle.setTranslateY(newY - (rectangle.getLayoutY() + PADDLE_HEIGHT/2));
	}
}
