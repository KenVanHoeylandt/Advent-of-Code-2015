package net.kenvanhoeylandt;

import net.kenvanhoeylandt.services.SessionService;
import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day1.Day1Solution;
import net.kenvanhoeylandt.solutions.day10.Day10Solution;
import net.kenvanhoeylandt.solutions.day11.Day11Solution;
import net.kenvanhoeylandt.solutions.day12.Day12Solution;
import net.kenvanhoeylandt.solutions.day13.Day13Solution;
import net.kenvanhoeylandt.solutions.day14.Day14Solution;
import net.kenvanhoeylandt.solutions.day2.Day2Solution;
import net.kenvanhoeylandt.solutions.day3.Day3Solution;
import net.kenvanhoeylandt.solutions.day4.Day4Solution;
import net.kenvanhoeylandt.solutions.day5.Day5Solution;
import net.kenvanhoeylandt.solutions.day6.Day6Solution;
import net.kenvanhoeylandt.solutions.day7.Day7Solution;
import net.kenvanhoeylandt.solutions.day8.Day8Solution;
import net.kenvanhoeylandt.solutions.day9.Day9Solution;

public class Application
{
	private static final Class[] mSolutionClasses = new Class[]
	{
		Day1Solution.class,
		Day2Solution.class,
		Day3Solution.class,
		Day4Solution.class,
		Day5Solution.class,
		Day6Solution.class,
		Day7Solution.class,
		Day8Solution.class,
		Day9Solution.class,
		Day10Solution.class,
		Day11Solution.class,
		Day12Solution.class,
		Day13Solution.class,
		Day14Solution.class
	};

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.err.println("Expected 2 arguments");
			printUsage();
			return;
		}

		String session_token = args[0];
		String day_string = args[1];
		int day = Integer.valueOf(day_string);

		if (day < 1 || day > mSolutionClasses.length)
		{
			System.err.println("No solution found for day '" + day_string + "'");
			printUsage();
			return;
		}

		SessionService.shared().setSessionToken(session_token);

		System.out.println("Advent of Code solutions by Ken Van Hoeylandt");

		try
		{
			Solution solution = getSolution(day - 1);

			solution.run();
		}
		catch (Exception e)
		{
			System.err.println("Failed to get solution: " + e.getMessage());
		}
	}

	private static void printUsage()
	{
		System.out.println("Usage:");
		System.out.println("\tjava -jar AdventOfCode2016.jar [sessionToken] [day]");
	}

	private static Solution getSolution(int index) throws Exception
	{
		if (index < 0 || index >= mSolutionClasses.length)
		{
			throw new Exception("invalid solution index");
		}

		Class class_object = mSolutionClasses[index];

		if (!Solution.class.isAssignableFrom(class_object))
		{
			throw new Exception("class is not a Solution type");
		}

		@SuppressWarnings("unchecked")
		Class<Solution> solution_class_object = (Class<Solution>)class_object;

		return solution_class_object.newInstance();
	}
}
