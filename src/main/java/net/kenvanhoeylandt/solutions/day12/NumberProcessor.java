package net.kenvanhoeylandt.solutions.day12;

import org.json.JSONArray;
import org.json.JSONObject;

public class NumberProcessor
{
	private final boolean mSkipRedOnes;

	public NumberProcessor(boolean skipRedOnes)
	{
		mSkipRedOnes = skipRedOnes;
	}

	public int countIntegerValues(JSONObject jsonObject)
	{
		int total = 0;

		for (String key : jsonObject.keySet())
		{
			Object child = jsonObject.get(key);

			if (mSkipRedOnes && isRed(child))
			{
				return 0;
			}

			total += countAnonymousIntegerValues(child);
		}

		return total;
	}

	public int countIntegerValues(JSONArray jsonArray)
	{
		int total = 0;

		for (int i = 0; i < jsonArray.length(); ++i)
		{
			Object child = jsonArray.get(i);

			total += countAnonymousIntegerValues(child);
		}

		return total;
	}

	private int countAnonymousIntegerValues(Object object)
	{
		int total = 0;

		if (Integer.class.equals(object.getClass()))
		{
			total += (Integer)object;
		}
		else if (Long.class.equals(object.getClass()))
		{
			total += (Long)object;
		}
		else if (JSONObject.class.equals(object.getClass()))
		{
			total += countIntegerValues((JSONObject)object);
		}
		else if (JSONArray.class.equals(object.getClass()))
		{
			total += countIntegerValues((JSONArray)object);
		}

		return total;
	}

	private static boolean isRed(Object object)
	{
		return "red".equals(object);
	}
}
