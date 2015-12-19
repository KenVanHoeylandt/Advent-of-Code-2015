package net.kenvanhoeylandt.solutions.day19;

import net.kenvanhoeylandt.solutions.Solution;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

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
		Pair<String, List<Pair<String,String>>> input_pair = AssignmentFactory.create(input);

		String molecule = input_pair.getLeft();
		List<Pair<String,String>> electron_mappings = input_pair.getRight();

		// Create a molecule factory for the provided base-molecule
		MoleculeFactory factory = new MoleculeFactory(molecule);

		// Go through all electron mappings and create variants of the provided molecule
		long part_one_result = electron_mappings.stream()
			.parallel()
			.map(factory::createVariants)
			.flatMap(Collection::stream)
			.distinct()
			.count();

		return String.format("part one result: %d", part_one_result);
	}
}
