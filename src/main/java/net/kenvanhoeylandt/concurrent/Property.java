package net.kenvanhoeylandt.concurrent;

/**
 * Thread-safe property setter/getter.
 * Handy for usage with lambda scoping.
 * @param <Type> the type of the property to set
 */
public class Property<Type>
{
	private Type mObject;

	public Property()
	{
		mObject = null;
	}

	public Property(Type object)
	{
		mObject = object;
	}

	public synchronized void set(Type object)
	{
		mObject = object;
	}

	public synchronized Type get()
	{
		return mObject;
	}

	public synchronized boolean isNull()
	{
		return mObject == null;
	}

	public synchronized boolean isNotNull()
	{
		return mObject != null;
	}
}
