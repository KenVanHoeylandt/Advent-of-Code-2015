package net.kenvanhoeylandt.solutions.day7.logic;

public abstract class Gate
{
	private final String mOutputName;

	private final GateManager mGateManager;

	public Gate(GateManager gateManager, String outputName)
	{
		mGateManager = gateManager;
		mOutputName = outputName;

		mGateManager.register(this);
	}

	public final String getOutputName()
	{
		return mOutputName;
	}

	abstract public int getValue();
}
