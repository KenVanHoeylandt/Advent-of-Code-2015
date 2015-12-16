package net.kenvanhoeylandt.solutions.day16;

import net.kenvanhoeylandt.lang.Strings;

import java.util.HashMap;
import java.util.Scanner;

public class SueFactory
{
	public static Sue create(String input)
	{
		String stripped = Strings.removeAll(input, new String[] { "Sue ", ":", "," });

		Scanner scanner = new Scanner(stripped);

		int id = scanner.nextInt();

		HashMap<String, Integer> map = new HashMap<>(10);

		while (scanner.hasNext())
		{
			String key = scanner.next();
			int value = scanner.nextInt();

			map.put(key, value);
		}

		return new Sue(id, map);
	}
}
