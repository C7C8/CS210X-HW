import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import java.util.*;
import javafx.scene.media.*;            
import javafx.scene.layout.*; 
import javafx.scene.control.Label;
import javafx.scene.image.*;
public class GameImpl extends Pane implements Game {
	/**
	 * Defines different states of the game.
	 */
	public enum GameState {
		WON, LOST, ACTIVE, NEW
	}

	// Constants
	public static final int BRICK_ROWS 		= 5;
	public static final int BRICK_COLUMNS 	= 9;
	public static final int BRICK_GAP		= 15;
	public static final int BRICK_Y_OFFSET	= 15;
	public static final int BRICK_X_OFFSET	= 15;
	public static final int WIDTH 			= BRICK_X_OFFSET + (BRICK_COLUMNS * (BRICK_GAP + Brick.BRICK_WIDTH));
	public static final int HEIGHT 			= 600;

	// Instance variables
	private Ball ball;
	private Paddle paddle;

	public GameImpl () {
		setStyle("-fx-background-color: white;");
		restartGame(GameState.NEW);
	}

	public String getName () {
		return "Blocks";
	}

	public Pane getPane () {
		return this;
	}

	private void restartGame (GameState state) {
		getChildren().clear();  // remove all components from the game

		// Create and add ball
		ball = new Ball();
		getChildren().add(ball.getCircle());  // Add the ball to the game board
		
		// Create and add bricks
		for (int iX = 0; iX < BRICK_COLUMNS; iX++){
			for (int iY = 0; iY < BRICK_ROWS; iY++){
				getChildren().add(new Brick(iX * (Brick.BRICK_WIDTH + BRICK_GAP) + BRICK_X_OFFSET,
											iY * (Brick.BRICK_HEIGHT + BRICK_GAP) + BRICK_Y_OFFSET).getRectangle());
			}
		}

		// Create and add paddle
		paddle = new Paddle();
		getChildren().add(paddle.getRectangle());  // Add the paddle to the game board

		// Add start message
		final String message;
		if (state == GameState.LOST) {
			message = "Game Over\n";
		} else if (state == GameState.WON) {
			message = "You won!\n";
		} else {
			message = "";
		}
		final Label startLabel = new Label(message + "Click mouse to start");
		startLabel.setLayoutX(WIDTH / 2 - 50);
		startLabel.setLayoutY(HEIGHT / 2 + 100);
		getChildren().add(startLabel);

		// Add event handler to start the game
		setOnMouseClicked(e-> {
				GameImpl.this.setOnMouseClicked(null);

				//Remove start label on game start
				getChildren().remove(startLabel);
				run();
		});
		
		//Event handler for moving the paddle
		setOnMouseMoved(e -> {
			paddle.moveTo(e.getX(), paddle.getY());
		});
	}

	/**
	 * Begins the game-play by creating and starting an AnimationTimer.
	 */
	public void run () {
		// Instantiate and start an AnimationTimer to update the component of the game.
		new AnimationTimer () {
			private long lastNanoTime = -1;
			public void handle (long currentNanoTime) {
				if (lastNanoTime >= 0) {  // Necessary for first clock-tick.
					GameState state;
					if ((state = runOneTimestep(currentNanoTime - lastNanoTime)) != GameState.ACTIVE) {
						// Once the game is no longer ACTIVE, stop the AnimationTimer.
						stop();
						// Restart the game, with a message that depends on whether
						// the user won or lost the game.
						restartGame(state);
					}
				}
				// Keep track of how much time actually transpired since the last clock-tick.
				lastNanoTime = currentNanoTime;
			}
		}.start();
	}

	/**
	 * Updates the state of the game at each timestep. In particular, this method should
	 * move the ball, check if the ball collided with any of the animals, walls, or the paddle, etc.
	 * @param deltaNanoTime how much time (in nanoseconds) has transpired since the last update
	 * @return the current game state
	 */
	public GameState runOneTimestep (long deltaNanoTime) {
		ball.updatePosition(deltaNanoTime, paddle);
		
		return GameState.ACTIVE;
	}
}
/*final AudioClip sound = new AudioClip(getClass().getClassLoader().getResource(soundFilename).toString());
	...
	sound.play();

	
 * final Image image = new Image(getClass().getResourceAsStream("duck.jpg"));
		imageLabel = new Label("", new ImageView(image));
		imageLabel.setLayoutX(x - image.getWidth()/2);
		imageLabel.setLayoutY(y - image.getHeight()/2);
		...
		// Add the image to the game board
		pane.getChildren().add(imageLabel);  // pane is of type Pane
		8*/
