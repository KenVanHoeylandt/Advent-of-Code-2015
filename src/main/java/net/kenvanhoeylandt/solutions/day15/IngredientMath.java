package net.kenvanhoeylandt.solutions.day15;

import java.util.List;

public class IngredientMath
{
	public static long getScore(List<Ingredient> list, List<Integer> mixture)
	{
		if (list.size() != mixture.size())
		{
			throw new RuntimeException("ingredient list doesn't match mixture list size");
		}

		long capacity = 0;
		long durability = 0;
		long flavor = 0;
		long texture = 0;

		for (int i = 0; i < list.size(); ++i)
		{
			capacity += list.get(i).getCapacity() * mixture.get(i);
			durability += list.get(i).getDurability() * mixture.get(i);
			flavor += list.get(i).getFlavor() * mixture.get(i);
			texture += list.get(i).getTexture() * mixture.get(i);
		}

		return Math.max(0, capacity)
			* Math.max(0, durability)
			* Math.max(0, flavor)
			* Math.max(0, texture);
	}

	public static long getCalories(List<Ingredient> list, List<Integer> mixture)
	{
		long calories = 0;

		for (int i = 0; i < list.size(); ++i)
		{
			calories += list.get(i).getCalories() * mixture.get(i);
		}

		return calories;
	}
}
