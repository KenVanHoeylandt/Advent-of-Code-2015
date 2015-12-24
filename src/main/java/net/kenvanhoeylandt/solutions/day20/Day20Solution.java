package net.kenvanhoeylandt.solutions.day20;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.stream.IntStream;

public class Day20Solution extends Solution
{

	public Day20Solution()
	{
		super(20);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		int target = Integer.valueOf(input);

		int part_one = solvePartOne(target);
		int part_two = solvePartTwo(target);

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	private int solvePartOne(int target)
	{
		final int[] house = new int[target / 10];

		// For each starting elf
		IntStream.range(1, target / 10).forEach(i ->
		{
			// Visit the relevant houses with this elf
			for (int j = i; j < target / 10; j +=i)
			{
				house[j] += i * 10;
			}
		});

		// Find the first house that hits the target amount of presents
		return IntStream.range(0, house.length)
			.filter(i -> house[i] > target)
			.findFirst()
			.getAsInt();
	}

	private int solvePartTwo(int target)
	{
		final int[] house = new int[target / 11];

		// For each starting elf
		IntStream.range(1, target / 11).forEach(i ->
		{
			int houses_visited = 0;

			// Visit the relevant houses with this elf, but only up to 50 houses
			for (int j = i; j < target / 11; j +=i)
			{
				house[j] += i * 11;

				houses_visited++;

				if (houses_visited == 50)
				{
					break;
				}
			}
		});

		// Find the first house that hits the target amount of presents
		return IntStream.range(0, house.length)
			.filter(i -> house[i] > target)
			.findFirst()
			.getAsInt();
	}
}
