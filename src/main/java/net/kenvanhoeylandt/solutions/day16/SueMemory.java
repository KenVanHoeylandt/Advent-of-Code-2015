package net.kenvanhoeylandt.solutions.day16;

import java.util.HashMap;
import java.util.Map;

public class SueMemory
{
	private final static Map<String, Integer> sProperties = new HashMap<>();

	static {
		sProperties.put("children", 3);
		sProperties.put("cats", 7);
		sProperties.put("samoyeds", 2);
		sProperties.put("pomeranians", 3);
		sProperties.put("akitas", 0);
		sProperties.put("vizslas", 0);
		sProperties.put("goldfish", 5);
		sProperties.put("trees", 3);
		sProperties.put("cars", 2);
		sProperties.put("perfumes", 1);
	}

	public static boolean isPartOneMatch(Sue sue)
	{
		return sue.getProperties()
			.entrySet()
			.stream()
			.allMatch(entry ->
			{
				Integer known_value = sProperties.get(entry.getKey());
				return (known_value == null) || (known_value.equals(entry.getValue()));
			});
	}

	public static boolean isPartTwoMatch(Sue sue)
	{
		return sue.getProperties()
			.entrySet()
			.stream()
			.allMatch(entry ->
			{
				Integer known_value = sProperties.get(entry.getKey());

				if (known_value == null)
				{
					return true;
				}

				switch (entry.getKey())
				{
					case "cats":
					case "trees":
						return known_value < entry.getValue();

					case "pomeranians":
					case "goldfish":
						return known_value > entry.getValue();

					default:
						return known_value.equals(entry.getValue());
				}
			});
	}
}
