package net.kenvanhoeylandt.solutions.day9;

import net.kenvanhoeylandt.validators.ArrayValidator;

public class ConnectionFactory
{
	public static Connection create(String input)
	{
		String[] logic_parts = input.split(" = ");

		ArrayValidator.assertSize(logic_parts, 2);

		String station_parts = logic_parts[0];
		String distance_part = logic_parts[1];

		String[] stations = station_parts.split(" to ");

		ArrayValidator.assertSize(stations, 2);

		int distance = Integer.parseInt(distance_part);

		return new Connection(stations[0], stations[1], distance);
	}
}
