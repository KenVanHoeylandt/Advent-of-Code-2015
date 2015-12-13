package net.kenvanhoeylandt.concurrent;

public class IntegerProperty extends Property<Integer>
{
	public IntegerProperty()
	{
		super();
	}

	public IntegerProperty(Integer object)
	{
		super(object);
	}

	public synchronized void increase(int value)
	{
		set(get() + value);
	}

	public synchronized void decrease(int value)
	{
		set(get() - value);
	}
}
