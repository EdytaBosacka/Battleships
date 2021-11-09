package battleships.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public final static int FIELD_HEIGHT = 40;
	public final static int FIELD_WIDTH = 40;
	public final static int MARGIN = 50;
	private HashMap<Integer,String> xLabels = new HashMap<>(){{
	    put(0, "A");
	    put(1, "B");
	    put(2, "C");
	    put(3, "D");
	    put(4, "E");
	    put(5, "F");
	    put(6, "G");
	    put(7, "H");
	    put(8, "I");
	    put(9, "J");
	}};  

	@Override
	public void paint(Graphics g) {
		Graphics2D graphic2d = (Graphics2D) g;
		createGrid(graphic2d);
	}

	private void createGrid(Graphics2D g) {

		for (int x = 0; x < 10; x++) {
			g.setColor(Color.black);
			g.drawString(xLabels.get(x), x * FIELD_WIDTH + MARGIN + 15, MARGIN - 10);
			for (int y = 0; y < 10; y++) {
				g.setColor(new Color(173, 216, 230));
				g.fillRect(x * FIELD_WIDTH + MARGIN, y * FIELD_HEIGHT + MARGIN, FIELD_WIDTH, FIELD_HEIGHT);
				g.setColor(Color.gray);
				g.drawRect(x * FIELD_WIDTH + MARGIN, y * FIELD_HEIGHT + MARGIN, FIELD_WIDTH, FIELD_HEIGHT);
				
			}
		}
		for (int y = 0; y < 10; y++) {
			g.setColor(Color.black);
			g.drawString(String.valueOf(y+1), MARGIN - 20, y * FIELD_WIDTH + MARGIN + 30);
		}
	}

}
