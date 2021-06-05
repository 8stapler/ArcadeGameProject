import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * Monster bullets are shot by shooter monsters and will kill the hero if they
 * collide.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class MonsterBullet extends Projectile {

	public int velocityDirection;

	public MonsterBullet(double x, double y, boolean direction) {
		super(x, y, direction);
		this.direction = direction;
		this.c = Color.BLACK;
		this.width = 15;
		this.height = 15;
		setVelocityDirection();
		this.setColor(this.c);
	}

	private void setVelocityDirection() {
		if (this.direction == true) {
			this.velocityDirection = 1;
		} else {
			this.velocityDirection = -1;
		}
	}

	public void moveBullet() {
		if (direction == true) {
			this.setVelocityX(1);
			this.setX(getX() + getVelocityX());
		}
		if (direction == false) {
			this.setVelocityX(-1);
			this.setX(getX() + getVelocityX());
		}
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));

	}

	@Override
	public void shoot() {
		this.setX(getX() + getVelocityX());
		this.setY(getY() + getVelocityY());
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}

}
