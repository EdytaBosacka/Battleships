package battleships.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JPanel;

import battleships.CellStatus;
import battleships.Computer;

public class GamePanel extends JPanel implements MouseListener {
	public final static int FIELD_HEIGHT = 40;
	public final static int FIELD_WIDTH = 40;
	public final static int MARGIN = 50;
	private Computer computer;
	private HashMap<Integer, String> xLabels = new HashMap<>() {
		{
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
		}
	};

	public GamePanel(Computer computer) {
		this.computer = computer;
		addMouseListener(this);
	}

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
				CellStatus status = computer.getCell(x, y);
				if (status == CellStatus.EMPTY || status == CellStatus.HIDDEN_SHIP ) {
					g.setColor(new Color(173, 216, 230));
				} else if (status == CellStatus.HIT) {
					g.setColor(new Color(220, 20, 60));
				}else if(status == CellStatus.MISSED) {
					g.setColor(new Color(79,174,246));
				}
				g.fillRect(x * FIELD_WIDTH + MARGIN, y * FIELD_HEIGHT + MARGIN, FIELD_WIDTH, FIELD_HEIGHT);
				g.setColor(Color.gray);
				g.drawRect(x * FIELD_WIDTH + MARGIN, y * FIELD_HEIGHT + MARGIN, FIELD_WIDTH, FIELD_HEIGHT);

			}
		}
		for (int y = 0; y < 10; y++) {
			g.setColor(Color.black);
			g.drawString(String.valueOf(y + 1), MARGIN - 20, y * FIELD_WIDTH + MARGIN + 30);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int xCellNumber = (x - MARGIN) / FIELD_WIDTH;
		int y = e.getY();
		int yCellNumber = (y - MARGIN) / FIELD_HEIGHT;

		if ((x - MARGIN) >= 0 && (y - MARGIN) >= 0 && xCellNumber >= 0 && xCellNumber < 10 && yCellNumber >= 0
				&& yCellNumber < 10) {
			computer.shotCell(xCellNumber, yCellNumber);
			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
