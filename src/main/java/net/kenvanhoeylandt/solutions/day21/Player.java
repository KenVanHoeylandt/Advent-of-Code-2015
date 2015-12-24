package net.kenvanhoeylandt.solutions.day21;

public class Player
{
	private final int mHitPoints;

	private final int mDamage;

	private final int mArmor;

	public Player(int hitPoints, int damage, int armor)
	{
		mHitPoints = hitPoints;
		mDamage = damage;
		mArmor = armor;
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
}
