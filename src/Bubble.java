import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

/**
 * Bubbles are shot by the hero by player input. They extend the projectile
 * class and trap monsters until they break free or are killed.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Bubble extends Projectile {

	public boolean projectileFilled;
	public Monster capturedMonster;
	Timer bubbleReleaseTimer;
	public boolean isPopped;

	public Bubble(double x, double y, boolean direction) {
		super(x, y, direction);
		this.projectileFilled = false;
		this.setColor(Color.GREEN);

		// Unique to bubbles.
		bubbleReleaseTimer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				release();
			}
		});
	}

	public void moveBubble() {
		if (!this.projectileFilled) {
			if (direction == true) {
				this.setVelocityX(1.5);
				this.setX(getX() + getVelocityX());
			}
			if (direction == false) {
				this.setVelocityX(-1.5);
				this.setX(getX() + getVelocityX());
			}
		} else {
			this.capturedMonster.setY(this.getY());
			this.capturedMonster.setVelocityX(0);
			if (this.getY() >= 3) {
				this.setVelocityY(-0.5);
				checkBounds();
				this.setY(getY() + getVelocityY());
			} else {
				this.topCeiling = true;
				bubbleReleaseTimer.start();
			}
		}
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}

	public void setCapturedMonster(double x, double y, Monster capturedMonster) {
		this.projectileFilled = true;
		this.setColor(Color.RED);
		this.capturedMonster = capturedMonster;
		this.capturedMonster.setIsCaptured(this);
	}

	public Monster getCapturedMonster() {
		return this.capturedMonster;
	}

	public void release() {
		this.isPopped = true;
		this.capturedMonster.escape();
		if (this.capturedMonster.direction) {
			this.capturedMonster.setVelocityX(1);
		} else {
			this.capturedMonster.setVelocityX(-1);
		}

	}

	public boolean getIsFilled() {
		return this.projectileFilled;
	}

	@Override
	public void shoot() {
		// do nothing
	}
}