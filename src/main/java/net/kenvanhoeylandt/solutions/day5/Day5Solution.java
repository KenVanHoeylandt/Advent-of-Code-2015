package net.kenvanhoeylandt.solutions.day5;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.Arrays;
import java.util.List;

public class Day5Solution extends Solution
{
	public Day5Solution()
	{
		super(5);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		List<String> lines = Arrays.asList(input.split("\n"));

		long part_one = lines.parallelStream()
			.filter(Day5Solution::filterPartOne)
			.count();

		long part_two = lines.parallelStream()
			.filter(Day5Solution::filterPartTwo)
			.count();

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	private static boolean filterPartOne(String line)
	{
		return line.matches(".*[aeiou].*[aeiou].*[aeiou].*")
			&& line.matches(".*([a-z])\\1.*")
			&& !line.matches(".*(ab|cd|pq|xy).*");
	}

	private static boolean filterPartTwo(String line)
	{
		return line.matches(".*([a-z][a-z]).*\\1.*")
			&& line.matches(".*([a-z])[a-z]\\1.*");
	}
}
