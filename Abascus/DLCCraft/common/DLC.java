package Abascus.DLCCraft.common;

public class DLC 
{
	public int id;
	public String name;
	public int depend;
	public int state;
	
	public DLC(int i, String s)
	{
		id = i;
		name = s;
		state = 0;
	}
	
	public void setState(int i)
	{
		state = i;
	}

}
