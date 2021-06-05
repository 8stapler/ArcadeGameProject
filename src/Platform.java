
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Platforms will stop objects from falling and can be walked on.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Platform extends Drawable {
	private double width = 50;
	private double height = 50;
	private boolean isOnRightEdge;
	private boolean isOnLeftEdge;

	public Platform(double x, double y) {
		super(x, y);
		this.setShape(new Rectangle2D.Double(x, y, this.width, this.height));
		this.isOnLeftEdge = false;
		this.isOnRightEdge = false;
	}

	public void setIsRightEdge() {
		this.isOnRightEdge = true;
	}

	public void setIsLeftEdge() {
		this.isOnLeftEdge = true;
	}

	public boolean isItLeftEdge() {
		return this.isOnLeftEdge;
	}

	public boolean isItRightEdge() {
		return this.isOnRightEdge;
	}

}
