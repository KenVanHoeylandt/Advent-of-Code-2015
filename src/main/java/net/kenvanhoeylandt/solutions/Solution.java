package net.kenvanhoeylandt.solutions;

import net.kenvanhoeylandt.exceptions.InputParsingException;
import net.kenvanhoeylandt.performance.Profiler;
import net.kenvanhoeylandt.performance.SimpleProfiler;
import net.kenvanhoeylandt.services.ChallengeDataService;

public abstract class Solution implements Runnable
{
	private final int mDay;

	public Solution(int day)
	{
		mDay = day;
	}

	@Override
	public final void run()
	{
		// Request data -> Solve with data -> Output result/error

		System.out.println("Retrieving assignment data...");

		Profiler profiler = new SimpleProfiler();

		ChallengeDataService.shared().request(mDay)
			.onSuccess(task ->
			{
				System.out.println("Solving assignment...");

				profiler.start("Solution");

				Object result = solve(task.getResult());

				profiler.stop();

				return result;
			})
			.continueWith(task ->
			{
				if (task.isFaulted())
				{
					System.out.println("error when solving assignment");

					Exception exception = task.getError();
					String message = exception.getMessage();

					if (message != null)
					{
						System.err.println("Solution failed: " + message);
						exception.printStackTrace();
					}
					else
					{
						exception.printStackTrace();
					}

					if (InputParsingException.class.isAssignableFrom(exception.getClass()))
					{
						System.err.println("Make sure that you provide a valid token and that Advent of Code 2016 is still available online.");
					}
				}
				else
				{
					System.out.println(String.format("Solution for day %d: %s", mDay, task.getResult()));
				}

				return null;
			});
	}

	abstract protected Object solve(String input) throws Exception;
}
