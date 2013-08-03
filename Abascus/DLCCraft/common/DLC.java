package Abascus.DLCCraft.common;

public class DLC 
{
	public int id;
	public String name;
	public String Name;
	public String desciption;
	public int depend = -1;
	public int state;
	public int cost;
	
	public DLC(int i, String n, String N, String d, int dep, int c)
	{
		id = i;
		name = n;
		Name = N;
		desciption = d;
		depend = dep;
		cost = c;
		state = 0;
	}
	
	public void setState(int i)
	{
		state = i;
	}

}
