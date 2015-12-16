package net.kenvanhoeylandt.solutions.day16;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16Solution extends Solution
{
	public Day16Solution()
	{
		super(16);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		List<Sue> sue_list = Arrays.asList(input.split("\n"))
			.stream()
			.map(SueFactory::create)
			.collect(Collectors.toList());

		Sue part_one_match = sue_list.stream()
			.filter(SueMemory::isPartOneMatch)
			.findFirst()
			.get();

		Sue part_two_match = sue_list.stream()
			.filter(SueMemory::isPartTwoMatch)
			.findFirst()
			.get();

		return String.format("Part one: %d, part two: %d", part_one_match.getId(), part_two_match.getId());
	}
}
