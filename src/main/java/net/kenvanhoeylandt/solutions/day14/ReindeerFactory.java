package net.kenvanhoeylandt.solutions.day14;

import net.kenvanhoeylandt.lang.Strings;

public class ReindeerFactory
{
	public static Reindeer create(String input)
	{
		String stripped = Strings.removeAll(input,
			new String[]
			{
				"can fly ",
				"km/s for ",
				"seconds, but then must rest for ",
				"seconds."
			});

		String[] tokens = stripped.split(" ");

		String name = tokens[0];
		int velocity = Integer.valueOf(tokens[1]);
		int velocity_duration = Integer.valueOf(tokens[2]);
		int rest_duration = Integer.valueOf(tokens[3]);

		return new Reindeer(name, velocity, velocity_duration, rest_duration);
	}
}
