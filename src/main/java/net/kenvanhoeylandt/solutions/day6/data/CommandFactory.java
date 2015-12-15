package net.kenvanhoeylandt.solutions.day6.data;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.validators.ArrayValidator;

public class CommandFactory
{
	final static String sCommandTurnOffPrefix = "turn off ";
	final static String sCommandTurnOnPrefix = "turn on ";
	final static String sCommandTogglePrefix = "toggle ";

	public static Command parse(String input) throws InputParsingException
	{
		Command.Action action = getCommandAction(input);

		int skip_chars = getCommandActionCutOff(action);
		String area_input = input.substring(skip_chars);
		Area area = getArea(area_input);

		return new Command(action, area);
	}

	public static Command[] parse(String[] inputs) throws InputParsingException
	{
		Command[] commands = new Command[inputs.length];

		for (int i = 0; i < inputs.length; ++i)
		{
			commands[i] = parse(inputs[i]);
		}

		return commands;
	}

	private static Command.Action getCommandAction(String input) throws InputParsingException
	{
		if (input.startsWith(sCommandTogglePrefix))
		{
			return Command.Action.TOGGLE;
		}
		else if (input.startsWith(sCommandTurnOnPrefix))
		{
			return Command.Action.TURN_ON;
		}
		else if (input.startsWith(sCommandTurnOffPrefix))
		{
			return Command.Action.TURN_OFF;
		}
		else
		{
			throw new InputParsingException("unknown prefix for \"" + input + "\"");
		}
	}

	private static Area getArea(String input) throws InputParsingException
	{
		String[] parts = input.split(" through ");

		ArrayValidator.assertSize(parts, 2);

		String from_input = parts[0];
		String to_input = parts[1];

		String[] from_input_parts = from_input.split(",");
		ArrayValidator.assertSize(from_input_parts, 2);

		String[] to_input_parts = to_input.split(",");
		ArrayValidator.assertSize(to_input_parts, 2);

		int from_x = Integer.valueOf(from_input_parts[0]);
		int from_y = Integer.valueOf(from_input_parts[1]);

		int to_x = Integer.valueOf(to_input_parts[0]);
		int to_y = Integer.valueOf(to_input_parts[1]);

		return new Area(from_x, from_y, to_x, to_y);
	}

	private static int getCommandActionCutOff(Command.Action action)
	{
		switch (action)
		{
			case TOGGLE:
				return sCommandTogglePrefix.length();

			case TURN_OFF:
				return sCommandTurnOffPrefix.length();

			case TURN_ON:
				return sCommandTurnOnPrefix.length();

			default:
				throw new RuntimeException("unsupported action: " + action.toString());
		}
	}
}
