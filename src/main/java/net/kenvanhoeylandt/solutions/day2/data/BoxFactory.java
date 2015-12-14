package net.kenvanhoeylandt.solutions.day2.data;

public class BoxFactory
{
	public static Box create(String data)
	{
		String[] entries = data.split("x");

		if (entries.length != 3)
		{
			throw new RuntimeException("entry not in format [length]x[width]x[height]");
		}

		try
		{
			int length = Integer.parseInt(entries[0]);
			int width = Integer.parseInt(entries[1]);
			int height = Integer.parseInt(entries[2]);

			return new Box(length, width, height);
		}
		catch (Exception e)
		{
			throw new RuntimeException("failed to parse numbers in entry");
		}
	}
}
