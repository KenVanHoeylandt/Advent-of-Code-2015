package net.kenvanhoeylandt.solutions.day2;

import net.kenvanhoeylandt.solutions.day2.data.Box;
import net.kenvanhoeylandt.solutions.day2.data.BoxFactory;
import net.kenvanhoeylandt.solutions.day2.data.BoxMath;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Solution extends net.kenvanhoeylandt.solutions.Solution
{
	public Day2Solution()
	{
		super(2);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		List<Box> box_list = Arrays.stream(lines)
			.map(BoxFactory::create)
			.collect(Collectors.toList());

		int paper_needed = box_list.parallelStream()
			.mapToInt(BoxMath::calculatePaperNeeded)
			.sum();

		int ribbon_needed = box_list.parallelStream()
			.mapToInt(BoxMath::calculateRibbonNeeded)
			.sum();

		return String.format("%d paper needed and %d ribbon needed", paper_needed, ribbon_needed);
	}
}
