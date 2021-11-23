package battleships;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import battleships.gui.GamePanel;

public class Game {

	public static void main(String[] args) {
		Computer computer = Computer.getInstance();
		computer.initBoard();
		
		ImageIcon img = new ImageIcon("src/main/resources/BattleshipIcon.png");
		
		JFrame frame = new JFrame("Battleships");
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.add(new GamePanel(computer));
		frame.setSize(GamePanel.WINDOW_WIDTH, GamePanel.WINDOW_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setIconImage(img.getImage());
	}

}
