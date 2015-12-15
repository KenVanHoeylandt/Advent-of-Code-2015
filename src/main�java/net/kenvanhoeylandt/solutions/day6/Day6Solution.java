package net.kenvanhoeylandt.solutions.day6;

import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day6.data.Command;
import net.kenvanhoeylandt.solutions.day6.data.CommandFactory;
import net.kenvanhoeylandt.solutions.day6.logic.LightGrid;
import net.kenvanhoeylandt.solutions.day6.logic.LightGridPartOne;
import net.kenvanhoeylandt.solutions.day6.logic.LightGridPartTwo;

public class Day6Solution extends Solution
{
	public Day6Solution()
	{
		super(6);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] input_list = input.split("\n");

		Command[] commands = CommandFactory.parse(input_list);

		LightGridPartOne grid_part_one = new LightGridPartOne();
		LightGridPartTwo grid_part_two = new LightGridPartTwo();

		solve(grid_part_one, commands);
		solve(grid_part_two, commands);

		return String.format("part one: %d lights, part two: %d brightness", grid_part_one.getLightsOnCount(), grid_part_two.getTotalBrightness());
	}

	private void solve(LightGrid grid, Command[] commands)
	{
		// Apply all commands to the grid
		for (Command command : commands)
		{
			switch (command.getAction())
			{
				case TURN_ON:
					grid.turnOn(command.getArea());
					break;

				case TURN_OFF:
					grid.turnOff(command.getArea());
					break;

				case TOGGLE:
					grid.toggle(command.getArea());
					break;
			}
		}
	}
}
