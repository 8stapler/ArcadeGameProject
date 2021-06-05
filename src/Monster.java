import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Monsters can move and jump. They can be trapped inside a bubble for a limited
 * time and will be killed if the hero hits them while in this state. If it
 * touches the hero outside the bubble, the level will be restarted.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Monster extends Moveable {

	public char type;
	public Color color;
	public boolean isCaptured;
	public Bubble bubbleCapturedBy;

	public Monster(double x, double y) {
		super(x, y);
		this.alive = true;
		this.type = 'b';
		this.width = 35;
		this.height = 35;
		this.color = Color.YELLOW;
		this.setColor(color);
		this.setVelocityX(0.5);
		this.isCaptured = false;
	}

	public void escape() {
		this.isCaptured = false;
		if (this.getDirection()) {
			this.setVelocityX(.5);
		} else {
			this.setVelocityX(-.5);
		}
	}

	public void setIsCaptured(Bubble bubbleCapturedBy) {
		this.bubbleCapturedBy = bubbleCapturedBy;
		this.isCaptured = true;
	}

	public boolean getIsCaptured() {
		return this.isCaptured;
	}
}
