package net.kenvanhoeylandt.solutions.day3;

import java.util.HashMap;
import java.util.Map;

public class Grid
{
	// Maps X-Y coordinate string onto present count
	Map<String, Integer> mMap = new HashMap<>();

	public void put(int x, int y)
	{
		String key = getKey(x, y);

		Integer value = mMap.get(key);

		if (value == null)
		{
			mMap.put(key, 1);
		}
		else
		{
			mMap.put(key, value + 1);
		}
	}

	private String getKey(int x, int y)
	{
		return String.format("%d.%d", x, y);
	}

	public int getVisitCount()
	{
		return mMap.size();
	}
}
