package net.kenvanhoeylandt.solutions.day17;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Vector;

public class ZenArchery
{
	private static int getSum(Vector v)
	{
		int sum = 0;
		Integer n;
		for (int i = 0; i < v.size(); i++)
		{
			n = (Integer) v.elementAt(i);
			sum += n;
		}
		return sum;
	}

	public static Vector<Vector<Integer>> compute(Integer[] array, int atATime, int desiredTotal)
	{
		int[] indices;
		CombinationGenerator gen = new CombinationGenerator(array.length, atATime);
		Vector<Vector<Integer>> results = new Vector<>();
		Vector<Integer> combination;
		BigInteger numCombinations = gen.getTotal();
		System.out.println("Num combinations to test " + numCombinations.toString());
		int sum;
		Integer intObj;
		while (gen.hasMore())
		{
			combination = new Vector<>();
			indices = gen.getNext();
			for (int indice : indices)
			{
				intObj = array[indice];
				combination.addElement(intObj);
			}
			sum = getSum(combination);
			if (sum == desiredTotal)
			{
				Collections.sort(combination);
				if (!results.contains(combination))
				{
//					System.out.println(combination.toString());
					results.addElement(combination);
				}
			}
		}
		return results;
	}

}
