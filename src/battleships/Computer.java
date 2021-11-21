package battleships;

import java.util.Random;

public class Computer {
	private static final Computer INSTANCE = new Computer();

	private CellStatus[][] board;
	private Random random;

	private Computer() {
		board = new CellStatus[10][10];
		emptyBoard();
		random = new Random();
	}

	public static Computer getInstance() {
		return INSTANCE;
	}
	
	public void emptyBoard() {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				board[x][y] = CellStatus.EMPTY;
			}
		}
	}

	public void initBoard() {
		createShip(5);
		createShip(4);
		createShip(4);
	}

	public CellStatus getCell(int x, int y) {
		return board[x][y];
	}

	public CellStatus shotCell(int x, int y) {
		if (board[x][y] == CellStatus.EMPTY || board[x][y] == CellStatus.LOCKED) {
			board[x][y] = CellStatus.MISSED;
			return board[x][y];
		} else if (board[x][y] == CellStatus.HIDDEN_SHIP) {
			board[x][y] = CellStatus.HIT;
			return board[x][y];
		}
		return null;

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
					} else {
						board[x + i][y] = CellStatus.HIDDEN_SHIP;
					}
				}
				// Securing that the ships don't touch each other either on the sides or on the
				// corners
				if (orientation == 0) {
					for (int i = x - 1; i <= x + 1; i++)
						for (int j = y - 1; j <= y + shipSize; j++) {
							if (i >= 0 && i < 10 && j >= 0 && j < 10) {
								if (board[i][j] == CellStatus.EMPTY) {
									board[i][j] = CellStatus.LOCKED;
								}
							}
						}
				} else {
					for (int i = x - 1; i <= x + shipSize; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (i >= 0 && i < 10 && j >= 0 && j < 10) {
								if (board[i][j] == CellStatus.EMPTY) {
									board[i][j] = CellStatus.LOCKED;
								}
							}

						}
					}
				}
				createdShip = true;
			}
		}
	}

	private boolean isPositionValid(int x, int y, int orientation, int shipSize) {
		for (int i = 0; i < shipSize; i++) {
			if (orientation == 0) {
				if (board[x][y + i] == CellStatus.HIDDEN_SHIP || board[x][y + i] == CellStatus.LOCKED) {
					return false;
				}
			} else if (orientation == 1) {
				if (board[x + i][y] == CellStatus.HIDDEN_SHIP || board[x + i][y] == CellStatus.LOCKED) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isGameEnded() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == CellStatus.HIDDEN_SHIP) {
					return false;
				}
			}
		}
		return true;
	}

}
