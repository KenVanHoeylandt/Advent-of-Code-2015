package net.kenvanhoeylandt.solutions.day21;

public class FightClub
{
	/**
	 * Let two players fight and return the winning one.
	 * @param randomFighter player
	 * @param boss player
	 * @return the winning player
	 */
	public static Player fight(Player randomFighter, Player boss)
	{
		do // for each round
		{
			// Player goes first
			int boss_hp = boss.processAttack(randomFighter.getDamage());

			if (boss_hp != 0)
			{
				randomFighter.processAttack(boss.getDamage());
			}
		}
		while (randomFighter.getHitPoints() > 0 && boss.getHitPoints() > 0);

		// Return the winner
		return randomFighter.getHitPoints() <= 0 ? boss : randomFighter;
	}
}
