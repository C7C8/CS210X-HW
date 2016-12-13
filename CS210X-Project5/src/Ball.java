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
	public static final double INITIAL_VEL 	= 1e-7;
	public static final double MAX_VEL		= 4e-7; //Max vel is actually this + INITIAL_VEL

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
	
	/**
	 * Handle the physics side of rectangle collision; this function does
	 * not check for an actual intersection of the ball and rectangle.
	 * @param rect Rectangle to collide with.
	 */
	public void collideWith(Rectangle rect){
		final double dX = x - rect.getX() + ((rect.getLayoutX() + rect.getTranslateX()) / 2.0);
		final double dY = y - rect.getY() + ((rect.getLayoutY() + rect.getTranslateY()) / 2.0);
		final double angle = Math.atan2(dY, dX);
		
		if (angle >= 7.0 * Math.PI / 4.0 || angle <= Math.PI / 4.0)
			vx *= -1;
		else if (angle > Math.PI / 4.0 && angle <= 3.0 * Math.PI / 4.0)
			vy *= -1;
		else if (angle > 3.0 * Math.PI / 4.0 && angle <= 5.0 * Math.PI / 4.0)
			vx *= -1;
		else if (angle > 5. * Math.PI / 4.0 && angle <= 7.0 * Math.PI / 4.0)
			vy *= -1;
	}

	/**
	 * Set the speed of this ball, but in the same direction of travel.
	 * @param newSpeed New speed. MAKE THIS VERY LOW!
	 */
	public void setSpeed(double newSpeed){
		double angle = Math.atan2(vy, vx);
		vy = newSpeed * Math.sin(angle);
		vx = newSpeed * Math.cos(angle);
	}
	
	/**
	 * Determines if the ball and a given rectangle are colliding.
	 * @param rect Rectangle to check against for collisions
	 * @return True if there's a collision, false otherwise.
	 */
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
			//This can NOT use the collideWith function because the collisions are not
			//"perfect" -- the ball bounces off the paddle at different angles depending on
			//where it hits it.
			double relativeIntersect = (paddle.getX() + Paddle.PADDLE_WIDTH/ 2.0) - x;
			double normalizedRIntersect = relativeIntersect / (Paddle.PADDLE_WIDTH / 2.0);
			double angle = normalizedRIntersect * (5.0 * Math.PI / 12.0); //75 degrees
			double speed = Math.sqrt(vx*vx + vy*vy);
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
