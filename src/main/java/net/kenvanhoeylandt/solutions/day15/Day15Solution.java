package net.kenvanhoeylandt.solutions.day15;

import net.kenvanhoeylandt.concurrent.LongProperty;
import net.kenvanhoeylandt.concurrent.Property;
import net.kenvanhoeylandt.solutions.Solution;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15Solution extends Solution
{
	public Day15Solution()
	{
		super(15);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		// Parse input data
		List<Ingredient> ingredients = Arrays.asList(input.split("\n"))
			.parallelStream()
			.map(IngredientFactory::create)
			.collect(Collectors.toList());

		if (ingredients.size() != 4)
		{
			throw new RuntimeException("logic only works for an ingredient list size of 4");
		}

		// Properties for asynchronous usage
		Property<List<Ingredient>> best_list_part_one = new Property<>();
		Property<List<Ingredient>> best_list_part_two = new Property<>();
		LongProperty best_score_part_one = new LongProperty(0L);
		LongProperty best_score_part_two = new LongProperty(0L);

		// Premutate through all possible ingredient combinations
		new PermutationIterator<>(ingredients).forEachRemaining(list ->
		{
			// Permutate through all possible recipes
			permutateMixtures(100, (List<Integer> mixture) ->
			{
				long score = IngredientMath.getScore(list, mixture);

				// Part one score
				if (score > best_score_part_one.get())
				{
					synchronized (best_score_part_one)
					{
						best_score_part_one.set(score);
						best_list_part_one.set(list);
					}
				}

				// Part two score
				if (score > best_score_part_two.get() && IngredientMath.getCalories(list, mixture) == 500)
				{
					synchronized (best_score_part_two)
					{
						best_score_part_two.set(score);
						best_list_part_two.set(list);
					}
				}
			});
		});

		return String.format("part one: %d, part two: %d", best_score_part_one.get(), best_score_part_two.get());
	}

	public interface MixtureProcessor
	{
		void processMixture(List<Integer> mixture);
	}

	/**
	 * Permutate through all mixtures (only works with exactly 4 ingredients)
	 */
	private void permutateMixtures(int value, MixtureProcessor listener)
	{
		IntStream.rangeClosed(0, value).parallel().forEach(a ->
		{
			IntStream.rangeClosed(0, value).parallel().forEach(b ->
			{
				final int ab = a + b;

				if (ab > value)
				{
					return;
				}

				IntStream.rangeClosed(0, value).parallel().forEach(c ->
				{

					final int abc = ab + c;

					if (abc > value)
					{
						return;
					}

					IntStream.rangeClosed(0, value).parallel().forEach(d ->
					{

						int sum = abc + d;

						if (sum == value)
						{
							listener.processMixture(Arrays.asList(a, b, c, d));
						}
					});
				});
			});
		});
	}
}