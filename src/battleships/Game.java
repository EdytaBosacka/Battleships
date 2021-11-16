package battleships;
import javax.swing.JFrame;
import javax.swing.JPanel;

import battleships.gui.GamePanel;

public class Game {

	public static void main(String[] args) {
		Computer computer = Computer.getInstance();
		computer.initBoard();
		JFrame frame = new JFrame("Battleships");
		frame.add(new GamePanel(computer));
		frame.setSize(800, 550);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}

}
