package net.kenvanhoeylandt.solutions.day14;

public class ReindeerMath
{
	public static class ReindeerTravelResult
	{
		private final Reindeer mReindeer;

		private final int mDistanceTravelled;

		ReindeerTravelResult(Reindeer reindeer, int distanceTravelled)
		{
			mReindeer = reindeer;
			mDistanceTravelled = distanceTravelled;
		}

		public Reindeer getReindeer()
		{
			return mReindeer;
		}

		public int getDistanceTravelled()
		{
			return mDistanceTravelled;
		}
	}

	public static ReindeerTravelResult getDistanceTravelled(Reindeer reindeer, int duration)
	{
		int sprint_with_rest = reindeer.getVelocityDuration() + reindeer.getRestDuration();

		int remainder = duration % sprint_with_rest;
		int sprint_count = (duration - remainder) / sprint_with_rest;

		int distance_travelled = sprint_count * reindeer.getVelocity() * reindeer.getVelocityDuration();

		if (remainder > 0)
		{
			distance_travelled += Math.min(remainder, reindeer.getVelocityDuration()) * reindeer.getVelocity();
		}

		return new ReindeerTravelResult(reindeer, distance_travelled);
	}
}
