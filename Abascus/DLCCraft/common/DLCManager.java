package Abascus.DLCCraft.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DLCManager 
{
	public static String[] names = new String[32];
	public static String[] Names = new String[32];
	public static int[] dep = new int[32];
	public static String[] description = new String[32];
	public static int[] cost = new int[32];
	public DLC[] dlcs = new DLC[32];
	
	public DLCManager()
	{
		
		for(int i = 0;i<dep.length;i++)
		{
			dep[i] = -1;
		}
		names[0] = "punchWood";
		names[1] = "sprint";
		names[2] = "eat";
		names[3] = "air";
		names[4] = "potion";
		names[5] = "chest";
		names[6] = "enchanting";
		names[7] = "furnace";
		names[8] = "brewing";
		names[9] = "mobDrops";
		names[10] = "music";
		names[11] = "sounds";
		names[12] = "bow";
		names[13] = "sword";
		names[14] = "collectDrops";
		names[15] = "jump";
		names[16] = "placeBlocks";
		names[17] = "useIronPick";
		names[18] = "fishing";
		names[19] = "feedAnimal";
		names[20] = "trade";
		names[21] = "PvP";
		names[22] = "inventory";
		names[23] = "buttonLever";
		names[24] = "mobCoins";
		names[25] = "hopper";
		names[26] = "fishing";
		names[27] = "fishing";
		names[28] = "fishing";
		names[29] = "fishing";
		names[30] = "fishing";
		names[31] = "fishing";
		
		Names[0] = "Breaking Wood";
		Names[1] = "Sprinting";
		Names[2] = "Eating";
		Names[3] = "Breathing";
		Names[4] = "Drinking Potions";
		Names[5] = "Using Chests";
		Names[6] = "Enchanting";
		Names[7] = "Using a Furnace";
		Names[8] = "Brewing Potions";
		Names[9] = "Mob Drops";
		Names[10] = "Music";
		Names[11] = "Sounds";
		Names[12] = "Bow";
		Names[13] = "Sword Damage";
		Names[14] = "Collect Drops";
		Names[15] = "Jump";
		Names[16] = "Place Blocks";
		Names[17] = "Iron Pickaxe";
		Names[18] = "Fishing";
		Names[19] = "Feed Animals";
		Names[20] = "Trade with Villagers";
		Names[21] = "PvP";
		Names[22] = "Inventory";
		Names[23] = "Buttons and Levers";
		Names[24] = "Coins from Mobs";
		Names[25] = "Hopper";
		Names[26] = "Brewing Potions";
		Names[27] = "Brewing Potions";
		Names[28] = "Brewing Potions";
		Names[29] = "Brewing Potions";
		Names[30] = "Brewing Potions";
		Names[31] = "Brewing Potions";
		
		description[0] = "Wood is THE basic material, so get it, fast!!";		
		description[1] = "You wanna run faster? You need to Sprint";		
		description[2] = "You're getting Hungry?";		
		description[3] = "Swimming is fun, drowning isn't";		
		description[4] = "Time to drink something?";		
		description[5] = "Wanna sort your Items?";		
		description[6] = "Do you wanna do some Magic?";		
		description[7] = "";		
		description[8] = "";		
		description[9] = "";		
		description[10] = "";		
		description[11] = "Why it's so Quiet?";		
		description[12] = "";		
		description[13] = "";		
		description[14] = "";		
		description[15] = "";		
		description[16] = "";		
		description[17] = "";		
		description[18] = "";		
		description[19] = "";		
		description[20] = "";		
		description[21] = "";		
		description[22] = "";		
		description[23] = "";		
		description[24] = "";		
		description[25] = "";		
		description[26] = "";		
		description[27] = "";		
		description[28] = "";		
		description[29] = "";		
		description[30] = "";		
		description[31] = "";
		
		cost[0] = 5;
		cost[1] = 10;
		cost[2] = 10;
		cost[3] = 5;
		cost[4] = 5;
		cost[5] = 5;
		cost[6] = 5;
		cost[7] = 5;
		cost[8] = 5;
		cost[9] = 5;
		cost[10] = 5;
		
		for(int i = 0; i< dlcs.length; i++)
		{
			dlcs[i] = new DLC(i, names[i], Names[i], description[i], dep[i], cost[i]);
		}
		
		dlcs[0].setState(1);
		dlcs[2].setState(1);
		dlcs[5].setState(1);
		dlcs[11].setState(1);
		dlcs[13].setState(1);
		dlcs[14].setState(1);
		dlcs[15].setState(1);
		dlcs[16].setState(1);
		dlcs[21].setState(1);
		dlcs[22].setState(1);
		
		dep[4] = 8;
	}
	
	public int getState(int id)
	{
		return dlcs[id].state;
	}
	
	public int getState(String s)
	{
		for(int i = 0; i<names.length; i++)
		{
			if(s.equalsIgnoreCase(names[i]))
			{
				return dlcs[i].state;
			}
		}
		return 0;
	}
	
	 public void saveToNBT (NBTTagCompound tags)
	    {
	        NBTTagList tagList = new NBTTagList();
	        NBTTagCompound dlc;

	        for (int i = 0; i < dlcs.length; ++i)
	        {
	            if (dlcs[i] != null)
	            {
	                dlc = new NBTTagCompound();
	                dlc.setInteger(dlcs[i].name, dlcs[i].state);
	                tagList.appendTag(dlc);
	            }
	        }

	        tags.setTag("DLCCraft", tagList);
	    }

	    public void readFromNBT (NBTTagCompound tags)
	    {
	    	NBTTagList nbttaglist = tags.getTagList("DLCCraft");
	        for (int i = 0; i < DLCManager.names.length; ++i)
	        {
	            if (DLCManager.names[i] != null)
	            {
	            	 NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
	            	int s =  (nbttagcompound.getInteger(DLCManager.names[i]));
	                DLC dlc = new DLC(i, DLCManager.names[i], Names[i], description[i], dep[i], cost[i]);
	                dlc.setState(s);
	            	dlcs[i] = dlc;
	            }
	        }
	    }

}
