import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Drawable is a abstract superclass for all things that can be drawn.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public abstract class Drawable {
	public double width;
	public double height;
	private Color color;
	private Shape shape;
	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	public boolean alive;
	

	public Drawable(double x, double y) {
		this.x = x;
		this.y = y;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.alive = true;
		this.setShape(new Rectangle2D.Double(x, y, this.height, this.width));
		this.setColor(Color.BLACK);
	}


	public Color getColor() {
		return this.color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Shape getShape() {
		return this.shape;
	}

	public void setShape(Shape s) {
		this.shape = s;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fill(this.shape);
	}

	public double getVelocityX() {
		return this.xVelocity;
	}

	public void setVelocityX(double x) {
		this.xVelocity = x;
	}

	public void setVelocityY(double y) {
		this.yVelocity = y;
	}

	public double getVelocityY() {
		return this.yVelocity;
	}

	public void kill() {
		this.alive = false;
		this.width = 0;
		this.height = 0;
		this.setVelocityX(0);
		this.setVelocityY(0);
	}

	public void updatePosition() {
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}

}
