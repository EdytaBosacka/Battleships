package battleships;

public enum CellStatus
{
	EMPTY,
	MISSED,
	HIT,
	HIDDEN_SHIP,
	// the field around the ship
	LOCKED
}
