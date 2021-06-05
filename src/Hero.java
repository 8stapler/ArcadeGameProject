import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.TimeUnit;

/**
 * Hero is controlled by the player. It can jump, shoot bubbles, and kill
 * monsters when they are trapped in bubbles. It will restart the level when it
 * hits a bullet or non-trapped monster.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Hero extends Moveable {

	public int lives;

	public Hero(double x, double y) {
		super(x, y);
		this.lives = 5;
		this.setColor(Color.BLUE);
		this.width = 37.5;
		this.height = 37.5;

	}

	public void loseALife() {
		this.lives = this.lives - 1;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLivesLeft() {
		return lives;
	}

	public void respawn() {
		this.setX(originX);
		this.setY(originY);
	}
	public void respawnP2() {
		this.setX(710);
		this.setY(0);
	}

}
