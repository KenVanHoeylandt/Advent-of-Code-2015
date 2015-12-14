package net.kenvanhoeylandt.solutions.day14;

public class Reindeer
{
	private final String mName;

	private final int mVelocity;

	private final int mVelocityDuration;

	private final int mRestDuration;

	public Reindeer(String name, int velocity, int velocityDuration, int restDuration)
	{
		mName = name;
		mVelocity = velocity;
		mVelocityDuration = velocityDuration;
		mRestDuration = restDuration;
	}

	public String getName()
	{
		return mName;
	}

	public int getVelocity()
	{
		return mVelocity;
	}

	public int getVelocityDuration()
	{
		return mVelocityDuration;
	}

	public int getRestDuration()
	{
		return mRestDuration;
	}
}
