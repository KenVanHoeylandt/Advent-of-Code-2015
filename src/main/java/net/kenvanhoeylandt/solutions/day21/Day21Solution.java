package net.kenvanhoeylandt.solutions.day21;

import net.kenvanhoeylandt.solutions.Solution;

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

		Player boss_player = new Player(boss_hit_points, boss_damage, boss_armor);

		PlayerItem[] weapons = new PlayerItem[]
		{
			new PlayerItem("Dagger", 8, 4, 0),
			new PlayerItem("Shortsword", 10, 5, 0),
			new PlayerItem("Warhammer", 25, 6, 0),
			new PlayerItem("Longsword", 40, 7, 0),
			new PlayerItem("Greataxe", 74, 8, 0)
		};

		PlayerItem[] armor = new PlayerItem[]
		{
			new PlayerItem("Leather", 13, 0, 1),
			new PlayerItem("Chainmail", 31, 0, 2),
			new PlayerItem("Splintmail", 53, 0, 3),
			new PlayerItem("Bandedmail", 75, 0, 4),
			new PlayerItem("Platemail", 102, 0, 5)
		};

		PlayerItem[] rings = new PlayerItem[]
		{
			new PlayerItem("Damage +1", 25, 1, 0),
			new PlayerItem("Damage +2", 50, 2, 0),
			new PlayerItem("Damage +3", 100, 3, 0),
			new PlayerItem("Defense +1", 20, 0, 1),
			new PlayerItem("Defense +2", 40, 0, 2),
			new PlayerItem("Defense +3", 80, 0, 3)
		};

		return null;
	}
}
