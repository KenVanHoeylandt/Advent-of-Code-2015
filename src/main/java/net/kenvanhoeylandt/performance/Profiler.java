package net.kenvanhoeylandt.performance;

public interface Profiler
{
	/**
	 * Start profiling for the provided tag.
	 * @param tag the tag to profile data for
	 */
	void start(String tag);

	/**
	 * Stop profiling for the last provided tag.
	 */
	void stop();
}
