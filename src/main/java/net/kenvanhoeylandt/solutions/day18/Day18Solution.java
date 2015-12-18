package net.kenvanhoeylandt.solutions.day18;

import net.kenvanhoeylandt.solutions.Solution;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day18Solution extends Solution
{
	public Day18Solution()
	{
		super(18);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		List<String> inputs = Arrays.asList(input.split("\n"));

		int width = inputs.get(0).length();
		int height = inputs.size();

		LightGrid part_one_grid = new LightGrid(width, height, false);
		fillGrid(part_one_grid, inputs);

		LightGrid part_two_grid = new LightGrid(width, height, true);
		fillGrid(part_two_grid, inputs);
		part_two_grid.setLight(0, 0, true);
		part_two_grid.setLight(0, height - 1, true);
		part_two_grid.setLight(width - 1, height - 1, true);
		part_two_grid.setLight(width - 1, 0, true);

		// Animate 100 times
		IntStream.range(0, 100).forEach(x ->
		{
			part_one_grid.animate();
			part_two_grid.animate();
		});

		return String.format("part one: %d, part two: %d", part_one_grid.countLightsOn(), part_two_grid.countLightsOn());
	}

	private void fillGrid(LightGrid grid, List<String> inputs)
	{
		// Process all input rows (y-axis)
		IntStream.range(0, inputs.size()).forEach(y ->
		{
			final String line = inputs.get(y);

			// Process each row separtely (x-axis)
			IntStream.range(0, line.length()).forEach(x ->
			{
				char character = line.charAt(x);

				if (character == '#')
				{
					grid.setLight(x, y, true);
				}
				else if (character == '.')
				{
					grid.setLight(x, y, false);
				}
				else
				{
					throw new RuntimeException("invalid character input");
				}
			});
		});
	}
}
