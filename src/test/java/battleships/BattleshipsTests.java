package battleships;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipsTests
{
	private Computer computer;

	@BeforeEach
	void beforeEach()
	{
		computer = Computer.getInstance();
	}

	@Test
	void testIsEmptyBoard()
	{
		computer.initBoard();
		computer.emptyBoard();
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
			{
				assertEquals(CellStatus.EMPTY, computer.getCell(x, y));

			}
		}
	}

	@Test
	void testShipPlacement()
	{
		computer.emptyBoard();
		computer.initBoard();
		int shipCells = 0;
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
			{
				if (computer.getCell(x, y) == CellStatus.HIDDEN_SHIP)
				{
					shipCells++;
				}
			}
		}
		assertEquals(13, shipCells);
		;
	}

	@Test
	void testShotEmptyCell()
	{
		computer.emptyBoard();
		Random random = new Random();
		int x = random.nextInt(10);
		int y = random.nextInt(10);
		assertEquals(CellStatus.MISSED, computer.shotCell(x, y));
		assertNull(computer.shotCell(x, y));

	}

	@Test
	void testIsGameEnded()
	{
		computer.emptyBoard();
		computer.initBoard();
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
			{
				computer.shotCell(x, y);
			}
		}
		assertTrue(computer.isGameEnded());
	}

}
