package net.kenvanhoeylandt.solutions.day13.logic;

import net.kenvanhoeylandt.concurrent.IntegerProperty;
import net.kenvanhoeylandt.concurrent.Property;
import net.kenvanhoeylandt.solutions.day13.data.Person;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.Collection;
import java.util.List;

/**
 * Calculates optimal seatings for people.
 */
public class SeatingService
{
	private interface SeatingIterator
	{
		void iterate(List<Person> seating);
	}

	private interface SeatingPersonIterator
	{
		void iterate(Person current, Person left, Person right);
	}

	/**
	 * Get the optimal seating of a bunch of people.
	 *
	 * @param people a bunch of people
	 * @return an array representing a circle of people (first and last person in the list are seated next to eachother
	 */
	public List<Person> getOptimalSeating(Collection<Person> people)
	{
		Property<List<Person>> seating_property = new Property<>();
		IntegerProperty happiness_property = new IntegerProperty(Integer.MIN_VALUE);

		iteratePossibleSeatings(people, seating ->
		{
			int happiness = getHappiness(seating);

			if (happiness > happiness_property.get())
			{
				happiness_property.set(happiness);
				seating_property.set(seating);
			}
		});

		return seating_property.get();
	}

	/**
	 * Get the total happiness for a specific seating
	 * @param seating the seating list
	 * @return the total happiness value
	 */
	public int getHappiness(List<Person> seating)
	{
		IntegerProperty happiness = new IntegerProperty(0);

		iterateSeating(seating, (current, left, right) ->
		{
			happiness.increase(current.getHappiness(left));
			happiness.increase(current.getHappiness(right));
		});

		return happiness.get();
	}

	/**
	 * Iterate through a specific seating arrangement
	 * @param seating the arrangement of people
	 * @param iterator the iteration interface
	 */
	private void iterateSeating(List<Person> seating, SeatingPersonIterator iterator)
	{
		for (int i = 0; i < seating.size(); ++i)
		{
			Person current = seating.get(i);

			int left_index = getIndexLeftOf(i, seating.size());
			int right_index = getIndexRightOf(i, seating.size());

			Person left_person = seating.get(left_index);
			Person right_person = seating.get(right_index);

			iterator.iterate(current, left_person, right_person);
		}
	}

	/**
	 * Iterate through all possible seatings
	 * @param people the Collection of People
	 * @param seatingIterator the iteration interface
	 */
	private void iteratePossibleSeatings(Collection<Person> people, SeatingIterator seatingIterator)
	{
		PermutationIterator<Person> iterator = new PermutationIterator<>(people);

		while (iterator.hasNext())
		{
			seatingIterator.iterate(iterator.next());
		}
	}

	/**
	 * Retrieve the index of the person to the left of the given seating index.
	 * @param index the index in the list
	 * @param length the list length
	 * @return the index of the person to the left
	 */
	public int getIndexLeftOf(int index, int length)
	{
		if (index == 0)
		{
			return length - 1;
		}
		else
		{
			return index - 1;
		}
	}

	/**
	 * Retrieve the index of the person to the right of the given seating index.
	 * @param index the index in the list
	 * @param length the list length
	 * @return the index of the person to the right
	 */
	public int getIndexRightOf(int index, int length)
	{
		if (index == (length - 1))
		{
			return 0;
		}
		else
		{
			return index + 1;
		}
	}
}
