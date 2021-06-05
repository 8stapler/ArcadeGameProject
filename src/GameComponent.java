
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.shape.Circle;
import java.awt.Color;

/**
 * Game component tells each class what to do every 5 milliseconds This is
 * the heart of the game and how it is implemented.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */

public class GameComponent extends JComponent {
	public int lives;
	private static final int UPDATE_INTERVAL = 5;
	private ArrayList<Drawable> drawables;
	private ArrayList<Platform> platforms;
	private ArrayList<Bubble> bubbles;
	private ArrayList<Monster> monsters;
	private ArrayList<MonsterBullet> bullets;
	private ArrayList<Fruit> fruit;
	private ArrayList<Monster> monstersToKill;
	private ArrayList<Bubble> bubblesToRemove;
	private ArrayList<Fruit> fruitToRemove;

	private int players;
	private Hero hero;
	private Hero hero2;
	private Level level;
	private int levelNumber;
	public GameKeyListener gk;
	private double bulletTimer;
	private int score;
	JLabel label;

	public GameComponent(JPanel panel, int players) {
		this.players = players;
		this.level = new Level(players);
		this.levelNumber = 1;
		this.drawables = level.loadLevel(levelNumber);
		this.monsters = level.getMonsters();
		this.platforms = level.getPlatforms();
		this.hero = level.getHero();
		if (players == 2) {
			this.hero2 = level.getHero2();
			this.hero2.setColor(Color.ORANGE);
		}
		else {
			this.hero2 = new Hero(-100, 0);
		}
		this.bubbles = new ArrayList<>();
		this.fruit = new ArrayList<>();
		this.bullets = new ArrayList<>();
		this.score = 0;
		this.bulletTimer = 0;
		this.monstersToKill = new ArrayList<>();
		this.bubblesToRemove = new ArrayList<>();
		this.fruitToRemove = new ArrayList<>();
		JLabel label = new JLabel(
				"Level: " + this.levelNumber + "                                                                 "
						+ "Lives: " + this.hero.getLivesLeft()
						+ "                                                             " + "Score: " + this.score);
		panel.add(label);
		this.label = label;

		Timer advanceStateTimer = new Timer(UPDATE_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePositions();
				repaint();
			}
		});
		advanceStateTimer.start();
	}

	public void createBubble(Hero hero) {
		Bubble bubble = new Bubble(hero.getX(), hero.getY(), hero.getDirection());
		bubbles.add(bubble);
	}

	public void createFruit(double x, double y) {
		Fruit fruit = new Fruit(x, y);
		this.fruit.add(fruit);
	}

	protected void updatePositions() {
		if (level.getMonsters().size() == 0 && levelNumber < 4) {
			levelUp();
		}
		hero.updatePosition(this.platforms);
		
		if (players == 2) {
		hero2.updatePosition(this.platforms);
		}
		
		checkMonsterCollision();
		
		checkBubbleCollision();

		checkFruitCollision();

		shootBullets();

		checkBulletCollision();

		if (monstersToKill.size() > 0) {
			for (Monster m : this.monstersToKill) {
				m.alive = false;
				monsters.remove(m);				
			}
			monstersToKill = new ArrayList<Monster>();
		}
		
		if (bubblesToRemove.size() > 0) {
			for (Bubble b : this.bubblesToRemove) {
				bubbles.remove(b);
			}
			bubblesToRemove = new ArrayList<Bubble>();
		}
		
		if (fruitToRemove.size() > 0) {
			for (Fruit f : this.fruitToRemove) {
				fruit.remove(f);
			}
			reprintLabel();
			fruitToRemove = new ArrayList<Fruit>();
		}
	}
	public void checkBulletCollision() {
		for (MonsterBullet bullet : this.bullets) {
			bullet.moveBullet();
			if (bullet.isTouching(hero) || bullet.isTouching(hero2)) {
				heroHit();
				reprintLabel();
				if(hero.isTouching(bullet) && lives != 5) {
					hero.respawn();	
				}
				if (players == 2) {
					if (hero2.isTouching(bullet) && lives != 5) {
						hero2.respawnP2();
					}
				}
			}
		}
	}
	
	public void shootBullets() {
		bulletTimer += .1;
		if (bulletTimer > 15) {
			for (ShooterMonster s : this.level.getShooters()) {
				if (!s.isCaptured && s.alive == true) {
					MonsterBullet b = new MonsterBullet(s.getX(), s.getY(), s.getDirection());
					bullets.add(b);
					bulletTimer = 0;
				}
			}
		}
	}
	public void checkMonsterCollision() {
		
		for (Monster m : this.monsters) {
			m.updatePosition(platforms);
			if (m.getX() < 3 || m.getX() > 700) {
				m.switchDirections('x');
			}
			if (new Random().nextInt(100) == 1 && !m.isCaptured) {
				m.jump();
			}
			if (hero.isTouching(m) || hero2.isTouching(m)) {
				if (!m.isCaptured) {
					if(hero.isTouching(m) && lives != 1) {
						hero.respawn();	
					}
					if (players == 2) {
						if(hero2.isTouching(m) && lives != 1) {
							hero2.respawnP2();
						}
					}
					heroHit();
					reprintLabel();
				} else {
					monstersToKill.add(m);
					bubblesToRemove.add(m.bubbleCapturedBy);
					this.score += 100;
					createFruit(m.bubbleCapturedBy.getX(), m.bubbleCapturedBy.getY());
					reprintLabel();
				}
			}

		}
	}
	
	public void checkBubbleCollision() {
		for (Bubble bub : this.bubbles) {
			bub.moveBubble();
			if ((hero.isTouching(bub) || hero2.isTouching(bub)) && bub.getIsFilled()) {
				bubblesToRemove.add(bub);
				monstersToKill.add(bub.getCapturedMonster());
				this.score += 100;
				reprintLabel();
				createFruit(bub.getX(), bub.getY());
			} else if (bub.isPopped) {
				bubblesToRemove.add(bub);
			}
			for (Monster m : this.monsters) {
				if (bub.isTouching(m) && !bub.getIsFilled() && !m.getIsCaptured()) {
					bub.setCapturedMonster(m.getX(), m.getY(), m);
				}
			}
		}
	}
	
	public void checkFruitCollision() {
		for (Fruit f : this.fruit) {
			if ((f.isTouching(this.hero) || f.isTouching(this.hero2)) && f.getVelocityY() == 0) {
				fruitToRemove.add(f);
				score += 300;
				reprintLabel();
			}
			f.updatePosition(platforms);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		for (Platform p : platforms) {
			p.draw(g2);
		}
		this.hero.draw(g2);
		this.hero2.draw(g2);
		for (Bubble b : bubbles) {
			b.draw(g2);
		}
		for (Monster m : monsters) {
			g.setColor(m.color);
			m.draw(g2);
		}
		for (MonsterBullet bullet : bullets) {
			bullet.draw(g2);
		}
		for (Fruit f : this.fruit) {
			f.draw(g2);
		}
	}
	
	public void heroHit() {
		hero.loseALife();
		if (hero.getLivesLeft() == 0) {
			setLevel(1);
			hero.setLives(5);
			this.score = 0;
		}
	}

	public void levelUp() {
		if (this.levelNumber < 4) {
			this.levelNumber++;
			prepareLevel();
		}
	}

	public void levelDown() {
		if (this.levelNumber > 1) {
			this.levelNumber -= 1;
			prepareLevel();
		}
	}

	public void setLevel(int levelNumber) {
		this.levelNumber = levelNumber;
		prepareLevel();
	}
	
	public void prepareLevel() {
		drawables = level.loadLevel(this.levelNumber);
		platforms = level.getPlatforms();
		monsters = level.getMonsters();
		hero = level.getHero();
		if (players == 2) {
			hero2 = level.getHero2();
			this.hero2.setColor(Color.ORANGE);
			this.gk.setHero2(hero2);
		}
		this.gk.setHero(hero);
		bubbles = new ArrayList<>();
		bullets = new ArrayList<>();
		fruit = new ArrayList<>();
		reprintLabel();
	}
	
	public void reprintLabel() {
		this.label.setText("Level: " + this.levelNumber + "                                                                 " + "Lives: " + this.hero.getLivesLeft()+ "                                                             " + "Score: " + this.score);
	}

	public void setKeyListener(GameKeyListener gk) {
		this.gk = gk;
	}

	public Hero getHero() {
		return this.hero;
	}
	
	public Hero getHero2() {
		return this.hero2;
	}

	public int getLevelNumber() {
		return this.levelNumber;
	}

	public ArrayList<Platform> getPlatforms() {
		return this.platforms;
	}

	public Point2D getHerosLocation() {
		return new Point2D.Double(hero.getX(), hero.getY());
	}
	public int getPlayers() {
		return this.players;
	}
}