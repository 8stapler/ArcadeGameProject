import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This loads the text files in order to create the objects for the game such as
 * the platforms, hero, and various monster types.
 * 
 * @author Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */

public class Level {
	int players;
	Hero hero = null;
	Hero hero2 = null;
	ArrayList<Platform> platforms;
	ArrayList<Drawable> drawables;
	ArrayList<Monster> monsters;
	ArrayList<ShooterMonster> shooters;

	public Level(int players) {
		this.players = players;
	}

	public ArrayList<Drawable> loadLevel(int number) {
		File file = new File("level" + number + ".txt");
		this.drawables = new ArrayList<Drawable>();
		this.platforms = new ArrayList<Platform>();
		this.monsters = new ArrayList<Monster>();
		this.shooters = new ArrayList<ShooterMonster>();

		try {
			Scanner scanner = new Scanner(file);
			int count = 0;
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				for (int i = 0; i < str.length(); i++) {
					char charAt = str.charAt(i);
					switch (charAt) {
					case 'L':
						Platform leftEdge = new Platform(i * 50, count * 50);
						leftEdge.setIsLeftEdge();
						drawables.add(leftEdge);
						platforms.add(leftEdge);
						break;
					case 'R':
						Platform rightEdge = new Platform(i * 50, count * 50);
						rightEdge.setIsRightEdge();
						drawables.add(rightEdge);
						platforms.add(rightEdge);
						break;
					case 'P':
						Platform platform = new Platform(i * 50, count * 50);
						drawables.add(platform);
						platforms.add(platform);
						break;
					case 'H':
						hero = new Hero(i * 50, count * 50 + 12.5);
						break;
					case 'J':
						if (players == 2) {
						hero2 = new Hero(i * 50, count * 50 + 12.5);
						}
						break;
					case 'B':
						Monster basicMonster = new Monster(i * 50, count * 50 + 15);
						drawables.add(basicMonster);
						monsters.add(basicMonster);
						break;
					case 'S':
						ShooterMonster shooterMonster = new ShooterMonster(i * 50, count * 50 + 10);
						drawables.add(shooterMonster);
						monsters.add(shooterMonster);
						shooters.add(shooterMonster);
						break;
					}

				}
				count++;

			}
			scanner.close();
			return drawables;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}

	public Hero getHero() {
		return this.hero;
	}
	
	public Hero getHero2() {
		return this.hero2;
	}

	public ArrayList<Platform> getPlatforms() {
		return this.platforms;
	}

	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public ArrayList<ShooterMonster> getShooters() {
		return this.shooters;
	}

}