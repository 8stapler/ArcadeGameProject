import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Moveable is a superclass for all things that move.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Moveable extends Drawable {

	public double originX;
	public double originY;
	public boolean isOnGround;
	public boolean gravivityAffected;
	public boolean collision;
	public boolean direction;

	public Moveable(double x, double y) {
		super(x, y);
		this.isOnGround = true;
		this.originX = 0;
		this.originY = 0;
		this.direction = true;
	}

	public boolean isTouching(Moveable Target) {
		if ((this.getShape()).intersects((Rectangle2D) Target.getShape())) {
			return true;
		}
		return false;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean getDirection() {
		return this.direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public boolean checkIsOnGround() {
		return this.isOnGround;
	}

	public void setIsOnGround(boolean g) {
		this.isOnGround = g;
	}

	public boolean isGravityApplied() {
		return this.gravivityAffected;
	}

	public void setGravityAffected(boolean g) {
		this.gravivityAffected = g;
	}

	public void applyGravity() {
		if (this.isOnGround == false) {
			if (this.getVelocityY() < 1.5) {
				this.setVelocityY(this.getVelocityY() + .1);
			}
			this.setGravityAffected(true);
		}
	}

	public void updatePosition(ArrayList<Platform> platforms) {
		this.applyGravity();
		this.checkBounds();
		this.setX(getX() + getVelocityX());
		this.setY(getY() + getVelocityY());
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
		if (this.getVelocityY() >= 0)
			this.checkIfInAir(platforms);
		if (isOnGround)
			checkIfFalling(platforms);
	}

	public void checkBounds() {
		int mostLeft = 0;
		int mostRight = 750;
		int mostUp = 0;
		int mostDown = 600;

		if (this.getX() <= mostLeft) {
			this.setVelocityX(0.1);
		}
		if (this.getX() + this.width >= mostRight) {
			this.setVelocityX(-0.1);
		}
		if (this.getY() <= mostUp) {
			this.setVelocityY(0.1);
		}
		if (this.getY() + this.height >= mostDown) {
			this.setVelocityY(-0.1);
			this.isOnGround = true;
		}
	}

	public boolean onPlatform(Platform p) {
		double oRight = this.getX() + this.width;
		double oLeft = this.getX();
		double platformRight = p.getX() + 50;
		double platformLeft = p.getX();
		boolean onPlatformRight = platformRight > oRight && platformLeft < oRight;
		boolean onPlatformLeft = platformRight > oLeft && platformLeft < oLeft;
		if (onPlatformRight) {
			return true;
		} else if (onPlatformLeft) {
			return true;
		}
		return false;
	}

	public boolean atPlatformMaxHeight(Platform p) {
		double threshold = Math.abs(p.getY() - this.getY() - this.height);
		return threshold <= this.getVelocityY();
	}

	public boolean isOnPlatform(Platform p) {
		if (p.getX() <= this.getX() + this.width && this.getX() <= p.getX() + this.width + 10
				&& Math.abs(p.getY() - this.getY() - this.height) <= 2) {
			return true;
		}
		return false;
	}

	public void checkIfInAir(ArrayList<Platform> platforms) {
		for (Platform p : platforms) {
			if (atPlatformMaxHeight(p)) {
				if (onPlatform(p)) {
					if (isGravityApplied()) {
						this.setVelocityY(0);
						this.setY(p.getY() - this.height);
						this.setGravityAffected(false);
					}
					this.setIsOnGround(true);
				}
			}
		}
	}

	protected boolean isOnEdgeOfPlatform(Platform p) {
		if (p.isItLeftEdge() && (this.getX() <= p.getX() && this.getX() + this.width >= p.getX())
				&& this.isOnPlatform(p)) {
			return true;
		} else if (p.isItRightEdge() && (this.getX() + this.width >= p.getX() + 50 && this.getX() <= p.getX() + 50
				&& this.isOnPlatform(p))) {
			return true;
		}
		return false;
	}

	protected void switchDirections(char DtoChange) {
		if (DtoChange == 'x') {
			this.setVelocityX(-this.getVelocityX());
			if (this.direction == true) {
				this.direction = false;
			} else {
				this.direction = true;
			}
		}
		if (DtoChange == 'y') {
			this.setVelocityY(-this.getVelocityY());
		}

	}

	public void jump() {
		if (isOnGround == true) {
			this.setVelocityY(-5);
			isOnGround = false;
			gravivityAffected = true;
		}
	}

	public void checkIfFalling(ArrayList<Platform> platforms) {
		for (Platform p : platforms) {
			if (this.atPlatformMaxHeight(p)) {
				if (this.isOnPlatform(p)) {
					return;
				}
			}
		}
		this.isOnGround = false;
	}
}
