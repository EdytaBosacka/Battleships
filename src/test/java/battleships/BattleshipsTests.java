package battleships;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipsTests {
	private Computer computer;
	@BeforeEach
	void beforeEach() {
		computer = Computer.getInstance();
	}
	@Test
	void testIsEmptyBoard() {
		computer.initBoard();
		computer.emptyBoard();
		for (int x=0; x <10; x++) {
			for (int y=0; y<10; y++) {
				assertEquals(CellStatus.HIT,computer.getCell(x, y));
				
			}
		}
	}

}
