package net.kenvanhoeylandt.solutions.day21;

import net.kenvanhoeylandt.concurrent.Property;
import net.kenvanhoeylandt.solutions.Solution;

import java.util.Collections;
import java.util.Scanner;

public class Day21Solution extends Solution
{
	public Day21Solution()
	{
		super(21);
	}

	@Override
	protected Object solve(String input) throws Exception
	{
		Scanner scanner = new Scanner(input);
		scanner.skip("Hit Points: ");
		int boss_hit_points = scanner.nextInt();
		scanner.skip("\nDamage: ");
		int boss_damage = scanner.nextInt();
		scanner.skip("\nArmor: ");
		int boss_armor = scanner.nextInt();

		// We compound the boss' stats into 1 PlayerItem
		PlayerItem boss_item = new PlayerItem("Boss Item", 0, boss_damage, boss_armor);

		PlayerItem[] weapons = new PlayerItem[]
		{
			new PlayerItem("Dagger", 8, 4, 0),
			new PlayerItem("Shortsword", 10, 5, 0),
			new PlayerItem("Warhammer", 25, 6, 0),
			new PlayerItem("Longsword", 40, 7, 0),
			new PlayerItem("Greataxe", 74, 8, 0)
		};

		PlayerItem[] armors = new PlayerItem[]
		{
			new PlayerItem("Leather", 13, 0, 1),
			new PlayerItem("Chainmail", 31, 0, 2),
			new PlayerItem("Splintmail", 53, 0, 3),
			new PlayerItem("Bandedmail", 75, 0, 4),
			new PlayerItem("Platemail", 102, 0, 5),
			null
		};

		PlayerItem[] rings = new PlayerItem[]
		{
			new PlayerItem("Damage +1", 25, 1, 0),
			new PlayerItem("Damage +2", 50, 2, 0),
			new PlayerItem("Damage +3", 100, 3, 0),
			new PlayerItem("Defense +1", 20, 0, 1),
			new PlayerItem("Defense +2", 40, 0, 2),
			new PlayerItem("Defense +3", 80, 0, 3),
			null,
			null
		};

		int part_one = solvePartOne(weapons, armors, rings, boss_hit_points, boss_item);
		int part_two = solvePartTwo(weapons, armors, rings, boss_hit_points, boss_item);

		return String.format("part one: %d, part two: %d", part_one, part_two);
	}

	// Part one: Fight to win
	private int solvePartOne(PlayerItem[] weapons, PlayerItem[] armors, PlayerItem[] rings, int bossHitPoints, PlayerItem bossItem)
	{
		Property<Player> best_figher = new Property<>();

		PlayerSet.iterate(weapons, armors, rings, itemList ->
		{
			Player fighter = new Player(100, itemList);
			Player boss = new Player("Boss", bossHitPoints, Collections.singletonList(bossItem));

			// Only fight if we haven't found a fighter yet,
			// or the new fighter has a lower cost
			if (best_figher.isNull() || best_figher.get().getCost() > fighter.getCost())
			{
				Player winner = FightClub.fight(fighter, boss);

				if (winner.equals(fighter))
				{
					best_figher.set(fighter);
				}
			}
		});

		// Assume the solution is solvable and there is always a result
		return best_figher.get().getCost();
	}

	// Part two: Fight to lose and spend the most money
	private int solvePartTwo(PlayerItem[] weapons, PlayerItem[] armors, PlayerItem[] rings, int bossHitPoints, PlayerItem bossItem)
	{
		Property<Player> worst_fighter = new Property<>();

		PlayerSet.iterate(weapons, armors, rings, itemList ->
		{
			Player fighter = new Player(100, itemList);
			Player boss = new Player("Boss", bossHitPoints, Collections.singletonList(bossItem));

			// Only fight if we haven't found a fighter yet,
			// or the new fighter has a higher cost
			if (worst_fighter.isNull() || worst_fighter.get().getCost() < fighter.getCost())
			{
				Player winner = FightClub.fight(fighter, boss);

				if (winner.equals(boss))
				{
					worst_fighter.set(fighter);
				}
			}
		});

		// Assume the solution is solvable and there is always a result
		return worst_fighter.get().getCost();
	}
}
