package net.kenvanhoeylandt.solutions.day13.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A model representing a person with a happiness map relative to other people.
 */
public class Person
{
	// Names are the unique identifier for a Person in our scenario
	private final String mName;

	private final Map<Person, Integer> mHappyMap;

	public Person(String name)
	{
		mName = name;
		mHappyMap = new HashMap<>();
	}

	public String getName()
	{
		return mName;
	}

	public void addToHappyMap(Person person, int happinessDelta)
	{
		mHappyMap.put(person, happinessDelta);
	}

	/**
	 * Get happiness sitting next to a Person.
	 * Returns 0 when there is no known data.
	 * @param person the person seated next to you
	 * @return happiness or 0
	 */
	public int getHappiness(Person person)
	{
		Integer happiness = mHappyMap.get(person);

		return happiness != null ? happiness : 0;
	}

	@Override
	public String toString()
	{
		return mName;
	}

	@Override
	public boolean equals(Object object)
	{
		return object.getClass().isAssignableFrom(this.getClass())
			&& ((Person)object).mName.equals(mName);
	}

	@Override
	public int hashCode()
	{
		return mName.hashCode();
	}
}
