package net.kenvanhoeylandt.solutions.day13.logic;

import net.kenvanhoeylandt.solutions.day13.data.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores and created people.
 */
public class PersonService
{
	private final Map<String, Person> mPersonMap = new HashMap<>();

	/**
	 * Get or create a Person by name.
	 * @param name the Peron's name
	 * @return the Person object
	 */
	public Person getPerson(String name)
	{
		Person person = mPersonMap.get(name);

		if (person == null)
		{
			person = createPerson(name);
		}

		return person;
	}

	/**
	 * Create a Person and add him/her to the map
	 * @param name the Person's name
	 * @return the created Person
	 */
	public Person createPerson(String name)
	{
		Person person = new Person(name);

		mPersonMap.put(name, person);

		return person;
	}

	public final Collection<Person> getPeople()
	{
		return mPersonMap.values();
	}
}
