package net.kenvanhoeylandt.lang;

public class Strings
{
	public static String removeAll(String input, String[] valuesToRemove)
	{
		String output = input;

		for (int i = 0; i < valuesToRemove.length; ++i)
		{
			output = output.replace(valuesToRemove[i], "");
		}

		return output;
	}
}
