package net.kenvanhoeylandt.services;

import bolts.Task;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Get input to solve challenges.
 */
public class ChallengeDataService
{
	private static ChallengeDataService sInstance;

	public static ChallengeDataService shared()
	{
		if (sInstance == null)
		{
			sInstance = new ChallengeDataService();
		}

		return sInstance;
	}

	/**
	 * Retrieve the assignment data for a specific day.
	 * @param day the day to retrieve data for
	 * @return the data as a String
	 */
	public Task<String> request(int day)
	{
		if (hasLocalFile(day))
		{
			return getFromLocalFile(day);
		}
		else
		{
			return getFromWebsite(day);
		}
	}

	/**
	 * Retrieve the assignment data for a specific day from a local file.
	 * @param day the day to retrieve data for
	 * @return the data as a String
	 */
	private Task<String> getFromLocalFile(int day)
	{
		return Task.callInBackground(() ->
		{
			File file = getLocalFile(day);

			FileInputStream input_stream = new FileInputStream(file);

			// Assignment data is not very big, so we can allocate and read it all at once
			byte[] data = new byte[(int)file.length()];

			input_stream.read(data);

			return new String(data);
		});
	}

	/**
	 * Get the local file to store the local data.
	 * The file might not exist yet.
	 *
	 * @param day the day to retrieve File for
	 * @return the file (which might not exist yet)
	 */
	private File getLocalFile(int day)
	{
		File directory = new File("data");

		if (!directory.isDirectory())
		{
			directory.mkdir();
		}

		return new File(directory, "day" + Integer.toString(day) + "");
	}

	/**
	 * Check if the assignemnt data is available as a local file
	 * @param day the day to retrieve data for
	 * @return true if the file exists
	 */
	private boolean hasLocalFile(int day)
	{
		File file = getLocalFile(day);

		return file.exists();
	}

	/**
	 * Retrieve the assignment data for a specific day from the website.
	 * @param day the day to retrieve data for
	 * @return the data as a String
	 */
	private Task<String> getFromWebsite(int day)
	{
		RequestService request_service = RequestService.shared();

		Request request = request_service.requestBuilder()
			.url(String.format("http://adventofcode.com/2015/day/%d/input", day))
			.build();

		return request_service.executeForString(request)
			.onSuccess(task ->
			{
				String input = task.getResult();

				// Strip \n from website response
				if (input.charAt(input.length() - 1) == '\n')
				{
					input = input.substring(0, input.length() - 1);
				}

				// Write data to local file
				File file = getLocalFile(day);

				if (!file.exists())
				{
					file.createNewFile();
				}

				// Assignment data is not very big, so we can write it unbuffered
				FileOutputStream output_stream = new FileOutputStream(file);
				output_stream.write(input.getBytes());
				output_stream.close();

				return input;
			});
	}
}
