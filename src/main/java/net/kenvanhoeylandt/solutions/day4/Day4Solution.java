package net.kenvanhoeylandt.solutions.day4;

import net.kenvanhoeylandt.solutions.Solution;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Day4Solution extends Solution
{
	public Day4Solution()
	{
		super(4);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		int five_zeroes = findZeroPrefix(input, 5);
		int six_zeroes = findZeroPrefix(input, 6);

		return String.format("five zeroes: %d, six zeroes: %d", five_zeroes, six_zeroes);
	}

	private int findZeroPrefix(String input, int zeroes) throws NoSuchAlgorithmException
	{
		// Create the zeroes prefix String
		char[] prefix_chars = new char[zeroes];
		Arrays.fill(prefix_chars, '0');
		String prefix = new String(prefix_chars);

		String hash = "";
		int count = 0;
		String secret;

		MessageDigest digest = MessageDigest.getInstance("MD5");

		// Calculate the hash until we found one that has the prefix we are looking for
		while (!hash.startsWith(prefix))
		{
			count++;

			secret = input + count;

			byte[] result_bytes = digest.digest(secret.getBytes());

			hash = DatatypeConverter.printHexBinary(result_bytes);
		}

		return count;
	}
}
