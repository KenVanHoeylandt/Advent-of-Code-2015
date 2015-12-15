package net.kenvanhoeylandt.concurrent;

public class LongProperty extends Property<Long>
{
	public LongProperty()
	{
		super();
	}

	public LongProperty(Long object)
	{
		super(object);
	}

	public synchronized void increase(long value)
	{
		set(get() + value);
	}

	public synchronized void decrease(long value)
	{
		set(get() - value);
	}
}