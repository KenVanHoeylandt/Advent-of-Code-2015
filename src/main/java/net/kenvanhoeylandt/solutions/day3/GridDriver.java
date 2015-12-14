package net.kenvanhoeylandt.solutions.day3;

import java.util.List;

public class GridDriver
{
	private final Grid mGrid;

	private final List<Direction> mDirections;

	private int mPositionX = 0;

	private int mPositionY = 0;

	public GridDriver(Grid grid, List<Direction> directions)
	{
		mGrid = grid;
		mDirections = directions;
	}

	public void drive()
	{
		// Save initial position
		saveCurrentPosition();

		mDirections.forEach(direction ->
		{
			updatePosition(direction);
			saveCurrentPosition();
		});
	}

	private void updatePosition(Direction direction)
	{
		switch (direction)
		{
			case NORTH:
				mPositionY++;
				break;

			case EAST:
				mPositionX++;
				break;

			case SOUTH:
				mPositionY--;
				break;

			case WEST:
				mPositionX--;
				break;
		}
	}

	private void saveCurrentPosition()
	{
		mGrid.put(mPositionX, mPositionY);
	}
}
