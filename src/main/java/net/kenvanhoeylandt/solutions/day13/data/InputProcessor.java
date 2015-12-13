package net.kenvanhoeylandt.solutions.day13.data;

import net.kenvanhoeylandt.exceptions.InputParsingException;

/**
 * Processes textual input into usable chunks of data.
 */
public class InputProcessor
{
	public interface Listener
	{
		/**
		 * Tells how much happiness personA would gain/lose when being seated next to person B
		 * @param personA the person to gain/lose happiness
		 * @param personB the reference person
		 * @param happinessDelta the happiness to gain/lose
		 */
		void onInput(String personA, String personB, int happinessDelta);
	}

	public void process(String[] lines, Listener listener) throws InputParsingException
	{
		for (String line : lines)
		{
			String trimmed_line = line.substring(0, line.length() - 1) // trim trailing period
				.replace("happiness units by sitting next to ", "")
				.replace("would ", "");

			String[] splitted = trimmed_line.split(" ");

			if (splitted.length != 4)
			{
				throw new InputParsingException("splitted input should result in exactly 4 words");
			}

			String person_a = splitted[0];
			String delta_determinator = splitted[1]; // lose/gain
			int delta_value = Integer.valueOf(splitted[2]);
			String person_b = splitted[3];

			if ("lose".equals(delta_determinator))
			{
				delta_value = -delta_value;
			}

			listener.onInput(person_a, person_b, delta_value);
		}
	}
}
