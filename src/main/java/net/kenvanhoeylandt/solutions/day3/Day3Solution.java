package net.kenvanhoeylandt.solutions.day3;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3Solution extends Solution
{
	public Day3Solution()
	{
		super(3);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		List<Direction> directions = input.chars()
			.mapToObj(DirectionFactory::create)
			.collect(Collectors.toList());

		int part_one_result = solvePartOne(directions);
		int part_two_result = solvePartTwo(directions);

		return String.format("part one: %d, part two: %d", part_one_result, part_two_result);
	}

	private int solvePartOne(List<Direction> directions) throws InputParsingException
	{

		Grid grid = new Grid();
		GridDriver driver = new GridDriver(grid, directions);

		driver.drive();

		return grid.getVisitCount();
	}

	private int solvePartTwo(List<Direction> directions) throws InputParsingException
	{
		List<Direction> santa_directions = new ArrayList<>(directions.size()/ 2);
		List<Direction> robosanta_directions = new ArrayList<>(directions.size()/ 2);

		IntStream.range(0, directions.size()).forEach(index ->
		{
			Direction direction = directions.get(index);

			if (index % 2 == 0)
			{
				santa_directions.add(direction);
			}
			else
			{
				robosanta_directions.add(direction);
			}
		});

		Grid grid = new Grid();

		GridDriver santa_driver = new GridDriver(grid, santa_directions);
		GridDriver robosanta_driver = new GridDriver(grid, robosanta_directions);

		santa_driver.drive();
		robosanta_driver.drive();

		return grid.getVisitCount();
	}
}
