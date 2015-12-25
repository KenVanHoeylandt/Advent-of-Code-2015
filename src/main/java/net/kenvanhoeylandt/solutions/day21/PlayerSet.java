package net.kenvanhoeylandt.solutions.day21;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.List;

public class PlayerSet
{
	public interface SetCallback
	{
		void onPlayerSet(List<PlayerItem> itemList);
	}

	/**
	 * Iterate through all possible combinations of weapons, armors, and rings.
	 *
	 * @param weapons
	 * @param armors
	 * @param rings
	 * @param callback
	 */
	public static void iterate(PlayerItem[] weapons, PlayerItem[] armors, PlayerItem[] rings, SetCallback callback)
	{
		ICombinatoricsVector<PlayerItem> rings_vector = Factory.createVector(rings);

		Generator<PlayerItem> ring_set_generator = Factory.createSimpleCombinationGenerator(rings_vector, 2);

		for (PlayerItem weapon : weapons)
		{
			for (PlayerItem armor : armors)
			{
				for (ICombinatoricsVector<PlayerItem> combination : ring_set_generator)
				{
					// Our set has at most 4 items
					ArrayList<PlayerItem> item_list = new ArrayList<>(4);

					item_list.add(weapon);

					// Armor is optional, so it can be null
					if (armor != null)
					{
						item_list.add(armor);
					}

					combination.forEach(ring ->
					{
						// Rings is optional, so they can be null
						if (ring != null)
						{
							item_list.add(ring);
						}
					});

					callback.onPlayerSet(item_list);
				}
			}
		}
	}
}
