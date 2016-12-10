import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Class that implements a ball with a position and velocity.
 */
public class Ball {
	// Constants
	public static final int BALL_RADIUS 	= 8;
	public static final double INITIAL_VEL 	= 3e-7;

	// Instance variables
	// (x,y) is the position of the center of the ball.
	private double x, y;
	private double vx, vy;
	private Circle circle;
	
	/**
	 * @return the x position of the ball.
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * @return the y position of the ball.
	 */
	public double getY(){
		return y;
	}

	/**
	 * Constructs a new Ball object at the centroid of the game board
	 * with a default velocity that points down and right.
	 */
	public Ball () {
		x = GameImpl.WIDTH/2;
		y = GameImpl.HEIGHT/2;
		Random rand = new Random();
		vx = INITIAL_VEL * Math.cos(rand.nextDouble() * Math.PI);
		vy = INITIAL_VEL * Math.sin(rand.nextDouble() * Math.PI);

		circle = new Circle(BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		circle.setLayoutX(x - BALL_RADIUS);
		circle.setLayoutY(y - BALL_RADIUS);
		circle.setFill(Color.BLACK);
	}

	/**
	 * @return the Circle object that represents the ball on the game board.
	 */
	public Circle getCircle () {
		return circle;
	}

	public double getSpeed(){
		return Math.sqrt(vx*vx+vy*vy);
	}

	public void setSpeed(double newSpeed){
		double angle = Math.atan2(vy, vx);
		vy = newSpeed * Math.sin(angle);
		vx = newSpeed * Math.cos(angle);
	}
	
	public boolean interesectRect(Rectangle rect){
		return  rect.getLayoutX() + rect.getTranslateX() - rect.getWidth() < x + BALL_RADIUS &&
				rect.getLayoutX() + rect.getTranslateX() + rect.getWidth() > x - BALL_RADIUS &&
				rect.getLayoutY() + rect.getTranslateY() - rect.getHeight() < y + BALL_RADIUS &&
				rect.getLayoutY() + rect.getTranslateY() + rect.getHeight() > y - BALL_RADIUS;
	}

	/**
	 * Updates the position of the ball, given its current position and velocity,
	 * based on the specified elapsed time since the last update.
	 * @param deltaNanoTime the number of nanoseconds that have transpired since the last update
	 */
	public void updatePosition (long deltaNanoTime, Paddle paddle) {
		if (interesectRect(paddle.getRectangle())){
			
			//Ball reflection math courtesy of a very nice internet person on stack exchange
			double relativeIntersect = (paddle.getX() + Paddle.PADDLE_WIDTH/ 2.0) - x;
			double normalizedRIntersect = relativeIntersect / (Paddle.PADDLE_WIDTH / 2.0);
			double angle = normalizedRIntersect * (5.0 * Math.PI / 12.0); //75 degrees
			double speed = getSpeed();
			vx = speed*Math.cos(angle);
			vy = (y < paddle.getY() ? -1 : 1) * speed*Math.sin(angle);
		}

		if (x > GameImpl.WIDTH - BALL_RADIUS|| x < BALL_RADIUS){
			vx *= -1;
			x += x < BALL_RADIUS ? 1 : -1; //Don't get stuck in the edges
		}
		if (y > GameImpl.HEIGHT - BALL_RADIUS || y < BALL_RADIUS){
			vy *= -1;
			y += y < BALL_RADIUS ? 1 : -1;
		}
		
		
		double dx = vx * deltaNanoTime;
		double dy = vy * deltaNanoTime;
		x += dx;
		y += dy;

		circle.setTranslateX(x - (circle.getLayoutX() + BALL_RADIUS));
		circle.setTranslateY(y - (circle.getLayoutY() + BALL_RADIUS));
	}
}
