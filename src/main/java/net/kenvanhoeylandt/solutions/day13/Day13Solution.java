package net.kenvanhoeylandt.solutions.day13;

import net.kenvanhoeylandt.solutions.Solution;
import net.kenvanhoeylandt.solutions.day13.data.InputProcessor;
import net.kenvanhoeylandt.solutions.day13.data.Person;
import net.kenvanhoeylandt.solutions.day13.logic.PersonService;
import net.kenvanhoeylandt.solutions.day13.logic.SeatingService;

import java.util.List;

public class Day13Solution extends Solution
{
	public Day13Solution()
	{
		super(13);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		String[] lines = input.split("\n");

		// This will read the data and provide input that is easy to process
		InputProcessor input_processor = new InputProcessor();
		// This will create/manage people
		PersonService person_service = new PersonService();
		// A service to create seating arrangements
		SeatingService seating_service = new SeatingService();

		// Register every Person's relationships with the PersonService
		input_processor.process(lines, (personA, personB, happinessDelta) ->
		{
			Person person_a = person_service.getPerson(personA);
			Person person_b = person_service.getPerson(personB);

			person_a.addToHappyMap(person_b, happinessDelta);
		});

		// Get the optimal seating for the given set of people
		long part_one_happiness = seating_service.getOptimalSeating(person_service.getPeople());

		// Create "self" Person and add him to the seating arrangement
		person_service.createPerson("Ken");

		// Get the new seating arrangement
		long part_two_happiness = seating_service.getOptimalSeating(person_service.getPeople());

		return String.format("part one: %d, part two: %d", part_one_happiness, part_two_happiness);
	}
}
