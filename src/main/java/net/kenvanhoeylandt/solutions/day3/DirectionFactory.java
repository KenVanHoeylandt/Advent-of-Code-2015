package net.kenvanhoeylandt.solutions.day3;

public class DirectionFactory
{
	public static Direction create(int character)
	{
		switch (character)
		{
			case '^':
				return Direction.NORTH;

			case '>':
				return Direction.EAST;

			case 'v':
				return Direction.SOUTH;

			case '<':
				return Direction.WEST;

			default:
				throw new RuntimeException("invalid character: " + character);
		}
	}
}
