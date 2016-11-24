package net.kenvanhoeylandt.solutions.day12;

import net.kenvanhoeylandt.solutions.Solution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Day12Solution extends Solution
{
	public Day12Solution()
	{
		super(12);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		if (input.startsWith("{")) {
			JSONObject json = new JSONObject(input);

			int result_part_one = new NumberProcessor(false).countIntegerValues(json);
			int result_part_two = new NumberProcessor(true).countIntegerValues(json);

			return String.format("part one: %d, part two: %d", result_part_one, result_part_two);
		} else if (input.startsWith("[")) {
			JSONArray json = new JSONArray(input);

			int result_part_one = new NumberProcessor(false).countIntegerValues(json);
			int result_part_two = new NumberProcessor(true).countIntegerValues(json);

			return String.format("part one: %d, part two: %d", result_part_one, result_part_two);
		} else {
			throw new RuntimeException("invalid input");
		}
	}
}
