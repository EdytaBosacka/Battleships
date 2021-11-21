package battleships.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import battleships.CellStatus;
import battleships.Computer;

public class GamePanel extends JPanel implements MouseListener {
	public final static int FIELD_HEIGHT = 40;
	public final static int FIELD_WIDTH = 40;
	public final static int LEGEND_FIELD_HEIGHT = 30;
	public final static int LEGEND_FIELD_WIDTH = 30;
	public final static int STATUS_FIELD_HEIGHT = 50;
	public final static int STATUS_FIELD_WIDTH = 150;
	public final static int MARGIN = 50;
	public final static int LEGEND_X_POSITION = 10 * FIELD_WIDTH + MARGIN + 100;
	public final static int LEGEND_Y_POSITION = MARGIN;
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 550;
	private CellStatus status;
	private Computer computer;
	private BufferedImage background;
	private boolean isGameEnded = false;
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
		try {
			this.background = ImageIO.read(new File("src/resources/BattleshipBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(this);
		createRestartButton();
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphic2d = (Graphics2D) g;
		graphic2d.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
		createGrid(graphic2d);
		createLegend(graphic2d);
		createStatusField(graphic2d);
	}

	private void createGrid(Graphics2D g) {
		for (int x = 0; x < 10; x++) {
			g.setColor(Color.black);
			g.drawString(xLabels.get(x), x * FIELD_WIDTH + MARGIN + 15, MARGIN - 10);
			for (int y = 0; y < 10; y++) {
				CellStatus status = computer.getCell(x, y);
				if (status == CellStatus.EMPTY || status == CellStatus.HIDDEN_SHIP || status == CellStatus.LOCKED) {
					g.setColor(new Color(173, 216, 230));
				} else if (status == CellStatus.HIT) {
					g.setColor(new Color(220, 20, 60));
				} else if (status == CellStatus.MISSED) {
					g.setColor(new Color(79, 174, 246));
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

	private void createLegend(Graphics2D g) {
		// Drawing legend
		g.setColor(new Color(173, 216, 230));
		g.fillRect(LEGEND_X_POSITION, LEGEND_Y_POSITION, LEGEND_FIELD_WIDTH, LEGEND_FIELD_HEIGHT);
		g.setColor(new Color(79, 174, 246));
		g.fillRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + LEGEND_FIELD_HEIGHT, LEGEND_FIELD_WIDTH, LEGEND_FIELD_HEIGHT);
		g.setColor(new Color(220, 20, 60));
		g.fillRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + 2 * LEGEND_FIELD_HEIGHT, LEGEND_FIELD_WIDTH,
				LEGEND_FIELD_HEIGHT);

		g.setColor(Color.gray);
		g.drawRect(LEGEND_X_POSITION, LEGEND_Y_POSITION, LEGEND_FIELD_WIDTH, LEGEND_FIELD_HEIGHT);
		g.drawRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + LEGEND_FIELD_HEIGHT, LEGEND_FIELD_WIDTH, LEGEND_FIELD_HEIGHT);
		g.drawRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + 2 * LEGEND_FIELD_HEIGHT, LEGEND_FIELD_WIDTH,
				LEGEND_FIELD_HEIGHT);

		// Drawing legend labels
		g.setColor(Color.black);
		g.drawString("Unknown", LEGEND_X_POSITION + LEGEND_FIELD_WIDTH + 10, MARGIN + 20);
		g.drawString("Missed", LEGEND_X_POSITION + LEGEND_FIELD_WIDTH + 10, MARGIN + LEGEND_FIELD_HEIGHT + 20);
		g.drawString("Hit", LEGEND_X_POSITION + LEGEND_FIELD_WIDTH + 10, MARGIN + 2 * LEGEND_FIELD_HEIGHT + 20);

	}

	private void createStatusField(Graphics2D g) {
		if (status != null) {
			if (isGameEnded) {
				g.setColor(new Color(190, 247, 141));
				g.fillRoundRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + 130, STATUS_FIELD_WIDTH, STATUS_FIELD_HEIGHT + 30, 50,
						50);
				g.setFont(new Font("Dialog", Font.BOLD, 13));
				g.setColor(new Color(83, 156, 20));
				g.drawString("Congratulations!", LEGEND_X_POSITION + 25, LEGEND_Y_POSITION + 160);
				g.drawString("You WON!", LEGEND_X_POSITION + 45, LEGEND_Y_POSITION + 190);
				
			}else if (status == CellStatus.HIT) {
				g.setColor(new Color(255, 187, 184));
				g.fillRoundRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + 130, STATUS_FIELD_WIDTH, STATUS_FIELD_HEIGHT, 50,
						50);
				g.setFont(new Font("Dialog", Font.BOLD, 13));
				g.setColor(new Color(220, 20, 60));
				g.drawString("You hit the ship!", LEGEND_X_POSITION + 25, LEGEND_Y_POSITION + 160);
			} else if (status == CellStatus.MISSED) {
				g.setColor(new Color(201, 240, 253));
				g.fillRoundRect(LEGEND_X_POSITION, LEGEND_Y_POSITION + 130, STATUS_FIELD_WIDTH, STATUS_FIELD_HEIGHT, 50,
						50);
				g.setFont(new Font("Dialog", Font.BOLD, 13));
				g.setColor(new Color(79, 174, 246));
				g.drawString("You missed!", LEGEND_X_POSITION + 40, LEGEND_Y_POSITION + 160);

			}
		}

	}
	
	private void createRestartButton() {
		JButton myButton = new JButton("My Button");
		myButton.setBounds(LEGEND_X_POSITION, LEGEND_Y_POSITION, FIELD_WIDTH, FIELD_HEIGHT);
		myButton.revalidate();
		myButton.setVisible(true);
        this.add(myButton);
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
			status = computer.shotCell(xCellNumber, yCellNumber);
			if (status == CellStatus.HIT) {
				isGameEnded = computer.isGameEnded();
			}
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
