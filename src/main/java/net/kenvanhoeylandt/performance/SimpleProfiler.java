package net.kenvanhoeylandt.performance;

import java.util.Date;
import java.util.Stack;

public class SimpleProfiler implements Profiler
{
	Stack<Entry> mEntryStack = new Stack<>();

	private static class Entry
	{
		private final String mName;

		private long mStartTime;

		public Entry(String name)
		{
			mName = name;
		}

		public void initStartTime()
		{
			mStartTime = new Date().getTime();
		}

		public String getName()
		{
			return mName;
		}

		public long getStartTime()
		{
			return mStartTime;
		}
	}

	@Override
	public void start(String tag)
	{
		Entry entry = new Entry(tag);
		mEntryStack.push(entry);

		// Ensure the stack push time is not counted as profiling time
		entry.initStartTime();
	}

	@Override
	public void stop()
	{
		long now = new Date().getTime();

		Entry entry = mEntryStack.pop();

		if (entry == null)
		{
			throw new RuntimeException("popping item from an empty stack - you might be calling Profiler.stop() too many times");
		}

		long total_time = now - entry.getStartTime();

		System.out.println(String.format("Profiler: %s %dms", entry.getName(), total_time));
	}
}
