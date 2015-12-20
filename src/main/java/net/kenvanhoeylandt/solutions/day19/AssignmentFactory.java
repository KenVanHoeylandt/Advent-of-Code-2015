package net.kenvanhoeylandt.solutions.day19;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AssignmentFactory
{
	/**
	 * @param input the assignment input
	 * @return a pair containing a molecule and a list of electron mappings
	 */
	public static Pair<String, List<Pair<Pattern, String>>> createForPartOne(String input)
	{
		List<String> immutable_lines = Arrays.asList(input.split("\n"));
		List<String> lines = new ArrayList<>(immutable_lines);

		// Remove second-last entry as it is an empty line
		lines.remove(lines.size() - 2);

		int last_index = lines.size() - 1;
		String molecule = lines.get(last_index);
		lines.remove(last_index);

		List<Pair<Pattern,String>> mappings = lines.stream()
			.map(mapping ->
			{
				String[] parts = mapping.split(" => ");

				// Create a matcher for the given pattern
				String quote_replacement = Matcher.quoteReplacement(parts[0]);
				Pattern pattern = Pattern.compile(quote_replacement);

				return Pair.of(pattern, parts[1]);
			})
			.collect(Collectors.toList());

		return Pair.of(molecule, mappings);
	}

	/**
	 * @param input the assignment input
	 * @return a pair containing a molecule and a list of electron mappings
	 */
	public static Pair<String, List<Pair<String, Pattern>>> createForPartTwo(String input)
	{
		List<String> immutable_lines = Arrays.asList(input.split("\n"));
		List<String> lines = new ArrayList<>(immutable_lines);

		// Remove second-last entry as it is an empty line
		lines.remove(lines.size() - 2);

		int last_index = lines.size() - 1;
		String molecule = lines.get(last_index);
		lines.remove(last_index);

		List<Pair<String,Pattern>> mappings = lines.stream()
			.map(mapping ->
			{
				String[] parts = mapping.split(" => ");

				// Create a matcher for the given pattern
				String quote_replacement = Matcher.quoteReplacement(parts[1]);
				Pattern pattern = Pattern.compile(quote_replacement);

				return Pair.of(parts[0], pattern);
			})
			.collect(Collectors.toList());

		return Pair.of(molecule, mappings);
	}
}
