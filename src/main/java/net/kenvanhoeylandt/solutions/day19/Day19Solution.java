package net.kenvanhoeylandt.solutions.day19;

import net.kenvanhoeylandt.solutions.Solution;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day19Solution extends Solution
{
	public Day19Solution()
	{
		super(19);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		// Get the molecule and the list of mappings
		Pair<String, List<Pair<Pattern,String>>> part_one_input = AssignmentFactory.createForPartOne(input);

		String molecule = part_one_input.getLeft();
		List<Pair<Pattern,String>> electron_mappings = part_one_input.getRight();

		// Create a molecule factory for the provided base-molecule
		MoleculeFactory factory = new MoleculeFactory(molecule);

		// Go through all electron mappings and create variants of the provided molecule
		long part_one_result = electron_mappings.stream()
			.parallel()
			.map(factory::createVariants)
			.flatMap(Collection::stream)
			.distinct()
			.count();

		Pair<String, List<Pair<String,Pattern>>> part_two_input = AssignmentFactory.createForPartTwo(input);
		List<Pair<String,Pattern>> part_two_electron_mappings = part_two_input.getRight();

		long part_two_result = solvePartTwo(molecule, part_two_electron_mappings);

		return String.format("part one: %d, part two: %d", part_one_result, part_two_result);
	}


	/**
	 * Warning: this works for my assignment input, but not for the demo input because it doesn't
	 * do all necessary matching.
	 */
	private long solvePartTwo(String molecule, List<Pair<String, Pattern>> electronMappings)
	{
		String current_molecule = molecule;
		int depth = 0;

		while (!"e".equals(current_molecule))
		{
			boolean found_one = false;

			for (Pair<String, Pattern> mapping : electronMappings)
			{
				// Create a matcher for the given pattern
				Matcher matcher = mapping.getRight().matcher(current_molecule);

				// Only matches the first character
				// Warning: this works for my assignment input, but not for the demo input
				if (matcher.find())
				{
					// Create a new string that replaces the left-side value with the right-side value
					current_molecule = current_molecule.substring(0, matcher.start())
						+ mapping.getLeft()
						+ current_molecule.substring(matcher.end());

					depth++;

					found_one = true;
				}
			}

			if (!found_one)
			{
				throw new RuntimeException("dead end");
			}
		}

		return depth;
	}
}
