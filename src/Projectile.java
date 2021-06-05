import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

/**
 * Projectiles will move horizontally whichever way the thing that shot them was
 * facing.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public abstract class Projectile extends Moveable {

	public boolean direction;
	public boolean topCeiling = false;
	public Color c;

	public Projectile(double x, double y, boolean direction) {
		super(x, y + 10);
		this.width = 20;
		this.height = 20;
		this.direction = direction;
		this.setColor(c);

	}

	public abstract void shoot();

	public boolean isAtTop() {
		return topCeiling;
	}

	public void setAtTop(boolean atTop) {
		this.topCeiling = atTop;
	}

}
