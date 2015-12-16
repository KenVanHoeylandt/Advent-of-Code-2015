package net.kenvanhoeylandt.solutions.day6.logic;

import net.kenvanhoeylandt.solutions.day6.data.Area;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LightGridPartTwo implements LightGrid
{
	private final int[][] mLights;

	private interface LightProcessor
	{
		int process(int input);
	}

	public LightGridPartTwo()
	{
		mLights = new int[1000][1000];

		IntStream.range(0, 1000).forEach(i -> Arrays.fill(mLights[i], 0));
	}

	@Override
	public void turnOn(Area area)
	{
		process(area, input -> input + 1);
	}

	@Override
	public void turnOff(Area area)
	{
		process(area, input -> Math.max(0, input - 1));
	}

	@Override
	public void toggle(Area area)
	{
		process(area, input -> input + 2);
	}

	public void process(Area area, LightProcessor processor)
	{
		for (int x = area.getFromX(); x <= area.getToX(); ++x)
		{
			for (int y = area.getFromY(); y <= area.getToY(); ++y)
			{
				mLights[x][y] = processor.process(mLights[x][y]);
			}
		}
	}

	public long getTotalBrightness()
	{
		// Stream all X values
		return IntStream.range(0, mLights.length)
			.mapToLong(
				// Stream all Y values and count the brightness of each light
				x -> IntStream.range(0, mLights[x].length)
					.map( y -> mLights[x][y]) // return brightness of light
					.sum() // count birghtness of lights
			).sum();
	}
}
