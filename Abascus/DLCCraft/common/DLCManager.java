package Abascus.DLCCraft.common;

public class DLCManager 
{
	public static String[] names = new String[20];
	public DLC[] dlcs = new DLC[20];
	
	public DLCManager()
	{
		names[0] = "punchWood";
		names[1] = "sprint";
		names[2] = "eat";
		names[3] = "air";
		names[4] = "potions";
		names[5] = "chest";
		names[6] = "enchanting";
		names[7] = "furnace";
		names[8] = "brewing";
		names[9] = "mobDrops";
		
		for(int i = 0; i< dlcs.length; i++)
		{
			dlcs[i] = new DLC(i, names[i]);
		}
	}

}
