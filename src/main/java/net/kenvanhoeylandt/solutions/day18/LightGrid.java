package net.kenvanhoeylandt.solutions.day18;

import java.util.stream.IntStream;

public class LightGrid
{
	private final int mWidth;
	private final int mHeight;

	private final boolean mCornersAreStuck;

	private boolean[][] mLights;

	public LightGrid(int width, int height, boolean cornersAreStuck)
	{
		mWidth = width;
		mHeight = height;
		mCornersAreStuck = cornersAreStuck;

		mLights = new boolean[mHeight][mWidth];
	}

	public void setLight(int x, int y, boolean state)
	{
		mLights[y][x] = state;
	}

	public void animate()
	{
		boolean[][] new_lights = new boolean[mHeight][mWidth];

		IntStream.range(0, mWidth).forEach(x -> {
			IntStream.range(0, mHeight).forEach(y -> {
				animate(x, y, mLights, new_lights);
			});
		});

		mLights = new_lights;
	}

	private void animate(int x, int y, boolean[][] fromGrid, boolean[][] toGrid)
	{
		int count = 0;

		if (getState(x - 1, y - 1, fromGrid))
		{
			count++;
		}

		if (getState(x, y - 1, fromGrid))
		{
			count++;
		}

		if (getState(x + 1, y - 1, fromGrid))
		{
			count++;
		}

		if (getState(x + 1, y, fromGrid))
		{
			count++;
		}

		if (getState(x + 1, y + 1, fromGrid))
		{
			count++;
		}

		if (getState(x, y + 1, fromGrid))
		{
			count++;
		}

		if (getState(x - 1, y + 1, fromGrid))
		{
			count++;
		}

		if (getState(x - 1, y, fromGrid))
		{
			count++;
		}

		boolean current_state = getState(x, y, fromGrid);

		if (current_state)
		{
			if (count == 2 || count == 3)
			{
				toGrid[y][x] = true;
			}
			else
			{
				toGrid[y][x] = false;
			}
		}
		else
		{
			if (count == 3)
			{
				toGrid[y][x] = true;
			}
			else
			{
				toGrid[y][x] = false;
			}
		}

		if (mCornersAreStuck)
		{
			boolean is_upper_left = (x == 0) && (y == 0);
			boolean is_upper_right = (x == mWidth - 1) && (y == 0);
			boolean is_lower_left = (x == 0) && (y == mHeight- 1);
			boolean is_lower_right = (x == mWidth - 1) && (y == mHeight - 1);

			if (is_upper_left || is_upper_right || is_lower_left || is_lower_right)
			{
				toGrid[y][x] = true;
			}
		}
	}

	private boolean getState(int x, int y, boolean[][] grid)
	{
		if (x < 0 || y < 0 || x >= mWidth || y >= mHeight)
		{
			return false;
		}
		else
		{
			return grid[y][x];
		}
	}

	public int countLightsOn()
	{
		int count = 0;

		for (int x = 0; x < mWidth; x++)
		{
			for (int y = 0; y < mHeight; y++)
			{
				if (mLights[y][x])
				{
					count++;
				}
			}
		}

		return count;
	}

	public void print()
	{
		for (int y = 0; y < mHeight; y++)
		{
			for (int x = 0; x < mWidth; x++)
			{
				System.out.print(mLights[y][x] ? '#' : '.');
			}

			System.out.println();
		}

		System.out.println();
	}
}
