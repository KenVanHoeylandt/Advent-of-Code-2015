package net.kenvanhoeylandt.solutions.day21;

import java.util.List;

/**
 * A player with PlayerItems that can fight with another player and lose hit points while doing so.
 */
public class Player
{
	private static int sPlayerId = 0;

	private int mHitPoints;

	private final int mDamage;

	private final int mArmor;

	private final List<PlayerItem> mItems;

	private final int mCost;

	private final String mName;

	public Player(String name, int hitPoints, List<PlayerItem> items)
	{
		mName = name;

		// Pre-cache values for easy computation later on

		int damage = 0;
		int armor = 0;
		int cost = 0;

		for (PlayerItem item : items)
		{
			damage += item.getDamage();
			armor += item.getArmor();
			cost += item.getCost();
		}

		mHitPoints = hitPoints;
		mItems = items;
		mDamage = damage;
		mArmor = armor;
		mCost = cost;
	}

	public Player(int hitPoints, List<PlayerItem> items)
	{
		mName = String.format("Random fPlayer %d", ++sPlayerId);

		// Pre-cache values for easy computation later on

		int damage = 0;
		int armor = 0;
		int cost = 0;

		for (PlayerItem item : items)
		{
			damage += item.getDamage();
			armor += item.getArmor();
			cost += item.getCost();
		}

		mHitPoints = hitPoints;
		mItems = items;
		mDamage = damage;
		mArmor = armor;
		mCost = cost;
	}

	public int getHitPoints()
	{
		return mHitPoints;
	}

	public int getDamage()
	{
		return mDamage;
	}

	public int getArmor()
	{
		return mArmor;
	}

	public String getName()
	{
		return mName;
	}

	public int getCost()
	{
		return mCost;
	}

	/**
	 * @param attackScore maximum damage to deal
	 * @return remaining hitpoints
	 */
	public int processAttack(int attackScore)
	{
		int damage = Math.max(1, attackScore - mArmor);

		mHitPoints = Math.max(0, mHitPoints - damage);

		return mHitPoints;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("[Player with ");
		builder.append(Integer.toString(mHitPoints));
		builder.append(" hitpoints, ");
		builder.append(Integer.toString(mArmor));
		builder.append(" armor, ");
		builder.append(Integer.toString(mCost));
		builder.append(" cost:");

		for (PlayerItem item : mItems)
		{
			builder.append(' ');
			builder.append(item.toString());
		}

		builder.append(']');

		return builder.toString();
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == null)
		{
			return false;
		}

		// Compare Player names
		return object.getClass().equals(getClass())
			&& getName().equals(((Player)object).getName());
	}
}
