package net.kenvanhoeylandt.solutions.day16;

import java.util.Map;

/**
 * Dear auntie Sue is a model.
 */
public class Sue
{
	private final int mId;

	private final Map<String, Integer> mProperties;

	public Sue(int id, Map<String, Integer> properties)
	{
		mId = id;
		mProperties = properties;
	}

	public int getId()
	{
		return mId;
	}

	public String toString()
	{
		return "[Sue " + Integer.toString(mId) + "]";
	}

	public Map<String, Integer> getProperties()
	{
		return mProperties;
	}
}
