package net.kenvanhoeylandt.solutions.day17;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17Solution extends Solution
{
	public Day17Solution()
	{
		super(17);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		// Get a list of integers, from large to small
		List<Integer> values = Arrays.asList(input.split("\n"))
			.stream()
			.map(Integer::valueOf)
			.collect(Collectors.toList());

		Integer[] values_array = values.toArray(new Integer[values.size()]);
		List<List<Integer>> results = new ArrayList<>();

		// Let's calculate all the possible sets
		getSubsetSums(values_array, values_array.length, 0, 150, new ArrayList<>(), results);

		int smallest_container_size = results.stream()
			.mapToInt(List::size)
			.distinct()
			.sorted()
			.findFirst()
			.getAsInt();

		long amount_of_solutions_with_smallest_container = results.stream()
			.filter(list -> list.size() == smallest_container_size)
			.count();

		return String.format("part one: %d, part two: %d", results.size(), amount_of_solutions_with_smallest_container);
	}

	/**
	 * Based on http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
	 * @param set the set of values to work with
	 * @param index the current index (should start at set.length)
	 * @param currentSum the current calculate sum (should start at 0)
	 * @param sumTarget the target sum (e.g. 150)
	 * @param currentResult the current result set to be evaluated (should start as empty list)
	 * @param results the list of all valid results
	 */
	void getSubsetSums(Integer[] set, int index, int currentSum, int sumTarget, List<Integer> currentResult, List<List<Integer>> results)
	{
		// Base Cases
		if (currentSum == sumTarget)
		{
			results.add(new ArrayList<>(currentResult));
			return;
		}

		if (index == 0)
		{
			return;
		}

		getSubsetSums(set, index - 1, currentSum, sumTarget, currentResult, results);

		int remainder = sumTarget - currentSum;
		int next_value = set[index - 1];

		if (next_value <= remainder)
		{
			List<Integer> new_result = new ArrayList<>(currentResult);
			new_result.add(next_value);

			getSubsetSums(set, index - 1, currentSum + next_value, sumTarget, new_result, results);
		}
	}
}
