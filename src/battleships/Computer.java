package battleships;

import java.util.Random;

public class Computer {
	private static final Computer INSTANCE = new Computer();

	private CellStatus[][] board;
	private Random random;

	private Computer() {
		board = new CellStatus[10][10];
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				board[x][y] = CellStatus.EMPTY;
			}
		}
		random = new Random();
	}

	public static Computer getInstance() {
		return INSTANCE;
	}

	public void initBoard() {
		createShip(5);
		createShip(4);
		createShip(4);
	}
	
	public CellStatus getCell(int x, int y) {
		return board[x][y];
	}
	
	public void shotCell(int x, int y) {
		if(board[x][y] == CellStatus.EMPTY) {
			board[x][y] = CellStatus.MISSED;
		}else if(board[x][y] == CellStatus.HIDDEN_SHIP) {
			board[x][y] = CellStatus.HIT;
		}
	}

	private void createShip(int shipSize) {
		boolean createdShip = false;
		while (!createdShip) {
			// Orientation for battleship: vertical - 0, horizontal - 1
			int orientation = random.nextInt(2);
			int x;
			int y;
			if (orientation == 0) {
				x = random.nextInt(10);
				y = random.nextInt(10 - shipSize + 1);
			} else {
				x = random.nextInt(10 - shipSize + 1);
				y = random.nextInt(10);
			}
			if (isPositionValid(x, y, orientation, shipSize)) {
				for (int i = 0; i < shipSize; i++) {
					if (orientation == 0) {
						board[x][y + i] = CellStatus.HIDDEN_SHIP;
					} else if (orientation == 1) {
						board[x + i][y] = CellStatus.HIDDEN_SHIP;
					}
				}
				createdShip = true;
			}
		}
	}

	private boolean isPositionValid(int x, int y, int orientation, int shipSize) {
		for (int i = 0; i < shipSize; i++) {
			if (orientation == 0) {
				if (board[x][y + i] == CellStatus.HIDDEN_SHIP) {
					return false;
				}
			} else if (orientation == 1) {
				if (board[x + i][y] == CellStatus.HIDDEN_SHIP) {
					return false;
				}
			}
		}
		return true;
	}
}
