import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * GameKeyListener handles what happens when a key pressed
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class GameKeyListener implements KeyListener {
	private GameComponent gc;
	private Hero hero;
	private Hero hero2;

	public GameKeyListener(GameComponent gc) {
		this.gc = gc;
		this.hero = gc.getHero();
		this.hero2 = gc.getHero2();
		gc.setKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not Used

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			moveRight(this.hero);
			break;

		case KeyEvent.VK_LEFT:
			moveLeft(this.hero);
			break;

		case KeyEvent.VK_UP:
			this.hero.jump();
			break;

		case KeyEvent.VK_D:
			gc.levelDown();
			break;

		case KeyEvent.VK_U:
			gc.levelUp();
			break;
			
		case KeyEvent.VK_SPACE:
			gc.createBubble(this.hero);
			break;
			
		case KeyEvent.VK_E:
			if (gc.getPlayers() == 2) {
			this.hero2.jump();
			}
			break;
	
		case KeyEvent.VK_S:
			if (gc.getPlayers() == 2) {
			moveLeft(this.hero2);
			}
			break;
			
		case KeyEvent.VK_F:
			if (gc.getPlayers() == 2) {
			moveRight(this.hero2);
			}
			break;
			
		case KeyEvent.VK_Y:
			if (gc.getPlayers() == 2) {
			gc.createBubble(this.hero2);
			}
			break;
		
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			this.hero.setVelocityX(0);
			break;
		case KeyEvent.VK_LEFT:
			this.hero.setVelocityX(0);
			break;
		case KeyEvent.VK_S:
			if (gc.getPlayers() == 2) {
			this.hero2.setVelocityX(0);
			}
			break;
		case KeyEvent.VK_F:
			if (gc.getPlayers() == 2) {
			this.hero2.setVelocityX(0);
			}
			break;
		}
	}
	
	public void moveLeft(Hero hero) {
		hero.setVelocityX(-1);
		hero.setDirection(false);
	}
	
	public void moveRight(Hero hero) {
		hero.setVelocityX(1);
		hero.setDirection(true);
	}

	public void setHero(Hero h) {
		this.hero = h;
	}
	public void setHero2(Hero h) {
		this.hero2 = h;
	}
}
