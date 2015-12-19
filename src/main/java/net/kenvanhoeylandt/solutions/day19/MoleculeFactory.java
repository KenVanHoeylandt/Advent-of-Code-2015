package net.kenvanhoeylandt.solutions.day19;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoleculeFactory
{
	private final String mBaseMolecule;

	public MoleculeFactory(String baseMolecule)
	{
		mBaseMolecule = baseMolecule;
	}

	/**
	 * @param mapping an electron mapping that replaces the left value with the right value
	 * @return a list of variants of the base molecule by applying all possible replacements (in a single step) on the base molecule
	 */
	public List<String> createVariants(Pair<String, String> mapping)
	{
		List<String> result_list = new ArrayList<>();

		// Create a matcher for the given pattern
		String quote_replacement = Matcher.quoteReplacement(mapping.getLeft());
		Pattern pattern = Pattern.compile(quote_replacement);
		Matcher matcher = pattern.matcher(mBaseMolecule);

		// Go through all matches
		while (matcher.find())
		{
			// Create a new string that replaces the left-side value with the right-side value
			String new_result = mBaseMolecule.substring(0, matcher.start())
				+ mapping.getRight()
				+ mBaseMolecule.substring(matcher.end());

			result_list.add(new_result);
		}

		return result_list;
	}
}
