package net.kenvanhoeylandt.solutions.day14;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14Solution extends Solution
{
	public Day14Solution()
	{
		super(14);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] inputs = input.split("\n");

		List<Reindeer> reindeer_list = Arrays.stream(inputs)
			.map(ReindeerParser::map)
			.collect(Collectors.toList());

		int time_passed = 2503;

		int part_one_solution = solvePartOne(reindeer_list, time_passed);
		int part_two_solution = solvePartTwo(reindeer_list, time_passed);

		return String.format("part one: %d, part two: %d", part_one_solution, part_two_solution);
	}

	private int solvePartOne(List<Reindeer> reindeerList, int timePassed)
	{
		return reindeerList.stream()
			.map( (reindeer) -> ReindeerMath.getDistanceTravelled(reindeer, timePassed) )
			.sorted( (first, second) -> Integer.compare(second.getDistanceTravelled(), first.getDistanceTravelled()) )
			.collect(Collectors.toList())
			.get(0)
			.getDistanceTravelled();
	}

	private int solvePartTwo(List<Reindeer> reindeerList, int totalTime)
	{
		Map<Reindeer, Integer> score_map = new HashMap<>(reindeerList.size());

		// Set all scores to 0
		reindeerList.stream().forEach( (reindeer -> score_map.put(reindeer, 0)) );

		// Brute-force this thing (in parallel though) and calculate each time separtely
		IntStream.rangeClosed(1, totalTime).parallel().forEach(value ->
		{
			// Collect results for the current time
			List<ReindeerMath.ReindeerTravelResult> travel_results = reindeerList.stream()
				.map( (reindeer) -> ReindeerMath.getDistanceTravelled(reindeer, value) )
				.sorted( (first, second) -> Integer.compare(second.getDistanceTravelled(), first.getDistanceTravelled()) )
				.collect(Collectors.toList());

			// Processes the results (also in parallel)
			mapPartTwoResults(travel_results, score_map);
		});

		// Reverse-sort and get first item
		return score_map.values()
			.stream()
			.sorted((first, second) -> Integer.compare(second, first))
			.findFirst()
			.get();
	}

	private void mapPartTwoResults(List<ReindeerMath.ReindeerTravelResult> results, Map<Reindeer, Integer> scoreMap)
	{
		int lead_result = results.get(0).getDistanceTravelled();

		// Go through each result and award points
		results.parallelStream().forEach(result ->
		{
			if (result.getDistanceTravelled() == lead_result)
			{
				// This runs in a parallel stream, so we have to synchronize
				synchronized (scoreMap)
				{
					Reindeer reindeer = result.getReindeer();
					int score = scoreMap.get(reindeer);
					scoreMap.put(reindeer, score + 1);
				}
			}
		});
	}
}
