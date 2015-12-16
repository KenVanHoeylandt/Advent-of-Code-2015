package net.kenvanhoeylandt.solutions.day6.logic;

import net.kenvanhoeylandt.solutions.day6.data.Area;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LightGridPartOne implements LightGrid
{
	private final boolean[][] mLights;

	private interface LightProcessor
	{
		boolean process(boolean input);
	}

	public LightGridPartOne()
	{
		mLights = new boolean[1000][1000];

		IntStream.range(0, 1000).forEach(i -> Arrays.fill(mLights[i], false));
	}

	@Override
	public void turnOn(Area area)
	{
		process(area, input -> true);
	}

	@Override
	public void turnOff(Area area)
	{
		process(area, input -> false);
	}

	@Override
	public void toggle(Area area)
	{
		process(area, input -> !input);
	}

	public void process(Area area, LightProcessor processor)
	{
		IntStream.rangeClosed(area.getFromX(), area.getToX()).forEach(x ->
		{
			IntStream.rangeClosed(area.getFromY(), area.getToY()).forEach(y ->
			{
				mLights[x][y] = processor.process(mLights[x][y]);
			});
		});
	}

	public long getLightsOnCount()
	{
		// Stream all X values
		return IntStream.range(0, mLights.length)
			.mapToLong(
				// Stream all Y values and count all the lights that are on
				x -> IntStream.range(0, mLights[x].length)
					.filter( y -> mLights[x][y]) // filter lights on
					.count() // count items that came through the filter
			).sum();
	}
}
