package net.kenvanhoeylandt.solutions.day7.logic;

import net.kenvanhoeylandt.solutions.day7.logic.gates.*;
import net.kenvanhoeylandt.validators.ArrayValidator;

public class GateFactory
{
	private static final String sNotPart = "NOT ";
	private static final String sOrPart = " OR ";
	private static final String sAndPart = " AND ";
	private static final String sLeftShiftPart = " LSHIFT ";
	private static final String sRightShiftPart = " RSHIFT ";

	public Gate create(GateManager manager, String input)
	{
		String[] parts = input.split(" -> ");

		ArrayValidator.assertSize(parts, 2);

		String input_part = parts[0];
		String output_name = parts[1];

		if (input_part.startsWith(sNotPart))
		{
			return createNotGate(manager, input_part, output_name);
		}
		else if (input_part.contains(sOrPart))
		{
			return createOrGate(manager, input_part, output_name);
		}
		else if (input_part.contains(sAndPart))
		{
			return createAndGate(manager, input_part, output_name);
		}
		else if (input_part.contains(sLeftShiftPart))
		{
			return createLeftShiftGate(manager, input_part, output_name);
		}
		else if (input_part.contains(sRightShiftPart))
		{
			return createRightShiftGate(manager, input_part, output_name);
		}
		else
		{
			return createValueGate(manager, input_part, output_name);
		}
	}

	private Gate createNotGate(GateManager manager, String inputPart, String outputName)
	{
		String input_name = inputPart.substring(sNotPart.length());

		return new NotGate(manager, outputName, input_name);
	}

	private Gate createOrGate(GateManager manager, String inputPart, String outputName)
	{
		String[] input_parts = inputPart.split(sOrPart);

		ArrayValidator.assertSize(input_parts, 2);

		return new OrGate(manager, outputName, input_parts[0], input_parts[1]);
	}

	private Gate createAndGate(GateManager manager, String inputPart, String outputName)
	{
		String[] input_parts = inputPart.split(sAndPart);

		ArrayValidator.assertSize(input_parts, 2);

		return new AndGate(manager, outputName, input_parts[0], input_parts[1]);
	}

	private Gate createLeftShiftGate(GateManager manager, String inputPart, String outputName)
	{
		String[] input_parts = inputPart.split(sLeftShiftPart);

		ArrayValidator.assertSize(input_parts, 2);

		int shift = Integer.valueOf(input_parts[1]);

		return new LeftShiftGate(manager, outputName, input_parts[0], shift);
	}

	private Gate createRightShiftGate(GateManager manager, String inputPart, String outputName)
	{
		String[] input_parts = inputPart.split(sRightShiftPart);

		ArrayValidator.assertSize(input_parts, 2);

		int shift = Integer.valueOf(input_parts[1]);

		return new RightShiftGate(manager, outputName, input_parts[0], shift);
	}

	private Gate createValueGate(GateManager manager, String inputPart, String outputName)
	{
		return new ValueGate(manager, outputName, inputPart);
	}
}
