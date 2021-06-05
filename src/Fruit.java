import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Fruit is dropped by killed enemies. It will fall to the platform below and
 * can be collected.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Fruit extends Moveable {
	public Fruit(double x, double y) {
		super(x, y);
		this.setColor(Color.MAGENTA);
		this.setHeight(15);
		this.setWidth(15);
		this.setVelocityY(.1);
	}

}
