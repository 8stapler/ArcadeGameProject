import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Shooter monsters have all the features of a regular monster, but they shoot
 * monster bullets
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class ShooterMonster extends Monster {
	public ShooterMonster(double x, double y) {
		super(x, y);
		this.alive = true;
		this.type = 's';
		this.color = Color.RED;
		this.width = 40;
		this.height = 40;
		this.setColor(this.color);
		this.setVelocityX(0.3);

	}
}
