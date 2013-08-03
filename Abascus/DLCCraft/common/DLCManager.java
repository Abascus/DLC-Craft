package Abascus.DLCCraft.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DLCManager 
{
	public static String[] names = new String[32];
	public DLC[] dlcs = new DLC[32];
	
	public DLCManager()
	{
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
		
		for(int i = 0; i< dlcs.length; i++)
		{
			dlcs[i] = new DLC(i, names[i]);
		}
		dlcs[0].Name = "Breaking Wood";
		dlcs[0].desciption = "Do you wanna be able to get Wood? Then you should buy this!";
		dlcs[1].Name = "Sprinting";
		dlcs[1].desciption = "You wanna run faster? You need to Sprint";
		dlcs[2].Name = "Eating";
		dlcs[2].desciption = "You're getting Hungry?";
		dlcs[3].Name = "Breathing";
		dlcs[3].desciption = "";
		dlcs[4].Name = "Drinking Potions";
		dlcs[4].desciption = "";
		dlcs[5].Name = "Using Chests";
		dlcs[5].desciption = "";
		dlcs[6].Name = "Enchanting";
		dlcs[6].desciption = "";
		dlcs[7].Name = "Using a Furnace";
		dlcs[7].desciption = "";
		dlcs[8].Name = "Brewing Potions";
		dlcs[8].desciption = "";
		dlcs[9].Name = "Mob Drops";
		dlcs[9].desciption = "";
		dlcs[10].Name = "Music";
		dlcs[10].desciption = "";
		dlcs[11].Name = "Sounds";
		dlcs[11].desciption = "";
		dlcs[12].Name = "Bow";
		dlcs[12].desciption = "";
		dlcs[13].Name = "Sword Damage";
		dlcs[13].desciption = "";
		dlcs[14].Name = "Collect Drops";
		dlcs[14].desciption = "";
		dlcs[15].Name = "Jump";
		dlcs[15].desciption = "";
		dlcs[16].Name = "Playe Blocks";
		dlcs[16].desciption = "";
		dlcs[17].Name = "Iron Pickaxe";
		dlcs[17].desciption = "";
		dlcs[18].Name = "Fishing";
		dlcs[18].desciption = "";
		dlcs[19].Name = "Feed Animals";
		dlcs[19].desciption = "";
		dlcs[20].Name = "Trade with Villagers";
		dlcs[20].desciption = "";
		dlcs[21].Name = "PvP";
		dlcs[21].desciption = "";
		dlcs[22].Name = "Inventory";
		dlcs[22].desciption = "";
		dlcs[23].Name = "Buttons and Levers";
		dlcs[23].desciption = "";
		dlcs[24].Name = "Coins from Mobs";
		dlcs[24].desciption = "";
		dlcs[25].Name = "Hopper";
		dlcs[25].desciption = "";
		dlcs[26].Name = "Brewing Potions";
		dlcs[26].desciption = "";
		dlcs[27].Name = "Brewing Potions";
		dlcs[27].desciption = "";
		dlcs[28].Name = "Brewing Potions";
		dlcs[28].desciption = "";
		dlcs[29].Name = "Brewing Potions";
		dlcs[29].desciption = "";
		dlcs[30].Name = "Brewing Potions";
		dlcs[30].desciption = "";
		dlcs[31].Name = "Brewing Potions";
		dlcs[31].desciption = "";
		
		
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
		
		dlcs[4].depend = 8;
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
	                DLC dlc = new DLC(i, DLCManager.names[i]);
	                dlc.setState(s);
	            	dlcs[i] = dlc;
	            }
	        }
	    }

}
