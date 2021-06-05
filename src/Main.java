import java.awt.Component;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and methods.
 * 
 * @authors Brett Huizinga, Russel Staples, Gerardo Santana
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bubble Bobble");
		frame.setSize(200, 120);
		JPanel panel = new JPanel();
		JButton onePlayerButton = new JButton("Start 1P Game");
		JButton twoPlayerButton = new JButton("Start 2P Game");
		onePlayerButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
				JFrame frame2 = new JFrame("Bubble Bobble");
				JPanel panel = new JPanel();
				GameComponent gc = new GameComponent(panel, 1);
				GameKeyListener gk = new GameKeyListener(gc);
				frame2.setSize(775, 650);
				frame2.add(gc);
				frame2.addKeyListener(gk);
				frame2.add(panel, BorderLayout.NORTH);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
				frame.dispose();
		    }
		});
		twoPlayerButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
				JFrame frame2 = new JFrame("Bubble Bobble");
				JPanel panel = new JPanel();
				GameComponent gc = new GameComponent(panel, 2);
				GameKeyListener gk = new GameKeyListener(gc);
				frame2.setSize(775, 650);
				frame2.add(gc);
				frame2.addKeyListener(gk);
				frame2.add(panel, BorderLayout.NORTH);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
				frame.dispose();
		    }
		});
		frame.add(panel);
		panel.add(onePlayerButton, BorderLayout.NORTH);
		panel.add(twoPlayerButton, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}

