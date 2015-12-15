package net.kenvanhoeylandt.solutions.day15;

import net.kenvanhoeylandt.lang.Strings;

public class IngredientFactory
{
	public static Ingredient create(String input)
	{
		String stripped = Strings.removeAll(input, new String[]
		{
			": capacity",
			", durability",
			", flavor",
			", texture",
			", calories"
		});

		String[] parts = stripped.split(" ");

		String name = parts[0];
		int capacity = Integer.valueOf(parts[1]);
		int durability = Integer.valueOf(parts[2]);
		int flavor = Integer.valueOf(parts[3]);
		int texture = Integer.valueOf(parts[4]);
		int calories = Integer.valueOf(parts[5]);

		return new Ingredient(name, capacity, durability, flavor, texture, calories);
	}
}
