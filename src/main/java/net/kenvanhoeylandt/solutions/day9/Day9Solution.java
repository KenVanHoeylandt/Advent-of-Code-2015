package net.kenvanhoeylandt.solutions.day9;

import net.kenvanhoeylandt.solutions.Solution;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9Solution extends Solution
{
	public Day9Solution()
	{
		super(9);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		// Process input into models
		List<Connection> connections = Arrays.asList(input.split("\n"))
			.stream()
			.map(ConnectionFactory::create)
			.collect(Collectors.toList());

		// Gather all "to" and "from" locations
		Stream<String> from_stream = connections.stream().map(Connection::getFrom);
		Stream<String> to_stream = connections.stream().map(Connection::getTo);

		// Gather all unique location names
		List<String> locations = Stream.concat(from_stream, to_stream)
			.distinct()
			.collect(Collectors.toList());

		int smallest = permutatePartOne(null, locations, new HashSet<>(), connections);
		int largest = permutatePartTwo(null, locations, new HashSet<>(), connections);

		return String.format("smallest: %d, largest: %d", smallest, largest);
	}

	private int permutatePartOne(@Nullable String fromLocation, List<String> locationsToVisit, Set<String> locationsVisited, List<Connection> connections)
	{
		if (locationsToVisit.isEmpty())
		{
			return 0;
		}

		int smallest_distance = Integer.MAX_VALUE;

		for (int i = 0; i < locationsToVisit.size(); ++i)
		{
			String visit_location  = locationsToVisit.get(i);

			List<String> to_visit = new ArrayList<>(locationsToVisit);
			to_visit.remove(i);

			int distance;

			// Starting locations
			if (fromLocation == null)
			{
				String from_location = visit_location;
				HashSet<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(from_location);

				distance = permutatePartOne(from_location, to_visit, locations_visited, connections);
			}
			else // recursive locations
			{
				int current_distance = distance(connections, fromLocation, visit_location);

				Set<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(visit_location);

				distance = current_distance + permutatePartOne(visit_location, to_visit, locations_visited, connections);
			}

			if (distance < smallest_distance)
			{
				smallest_distance = distance;
			}
		}

		return smallest_distance;
	}

	private int permutatePartTwo(@Nullable String fromLocation, List<String> locationsToVisit, Set<String> locationsVisited, List<Connection> connections)
	{
		if (locationsToVisit.isEmpty())
		{
			return 0;
		}

		int smallest_distance = Integer.MIN_VALUE;

		for (int i = 0; i < locationsToVisit.size(); ++i)
		{
			String visit_location  = locationsToVisit.get(i);

			List<String> to_visit = new ArrayList<>(locationsToVisit);
			to_visit.remove(i);

			int distance;

			// Starting locations
			if (fromLocation == null)
			{
				String from_location = visit_location;
				HashSet<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(from_location);

				distance = permutatePartTwo(from_location, to_visit, locations_visited, connections);
			}
			else // recursive locations
			{
				int current_distance = distance(connections, fromLocation, visit_location);

				Set<String> locations_visited = new HashSet<>(locationsVisited);
				locations_visited.add(visit_location);

				distance = current_distance + permutatePartTwo(visit_location, to_visit, locations_visited, connections);
			}

			if (distance > smallest_distance)
			{
				smallest_distance = distance;
			}
		}

		return smallest_distance;
	}

	public int distance(List<Connection> connections, String from, String to)
	{
		return connections.stream()
			.filter( connection ->
				( connection.getFrom().equals(from) && connection.getTo().equals(to) )
				|| ( connection.getFrom().equals(to) && connection.getTo().equals(from) )
			)
			.mapToInt(Connection::getDistance)
			.findFirst()
			.getAsInt();
	}
}
