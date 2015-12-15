package net.kenvanhoeylandt.solutions.day15;

public class Ingredient
{

	private final String mName;

	private final int mCapacity;

	private final int mDurability;

	private final int mFlavor;

	private final int mTexture;

	private final int mCalories;

	public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories)
	{
		mName = name;
		mCapacity = capacity;
		mDurability = durability;
		mFlavor = flavor;
		mTexture = texture;
		mCalories = calories;
	}

	public String getName()
	{
		return mName;
	}

	public int getCapacity()
	{
		return mCapacity;
	}

	public int getDurability()
	{
		return mDurability;
	}

	public int getFlavor()
	{
		return mFlavor;
	}

	public int getTexture()
	{
		return mTexture;
	}

	public int getCalories()
	{
		return mCalories;
	}
}
