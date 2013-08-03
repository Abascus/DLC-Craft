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
		dlcs[0].Name = "Breaking Wood";
		names[1] = "sprint";
		dlcs[1].Name = "Sprinting";
		names[2] = "eat";
		dlcs[2].Name = "Eating";
		names[3] = "air";
		dlcs[3].Name = "Breathing";
		names[4] = "potion";
		dlcs[4].Name = "Drinking Potions";
		names[5] = "chest";
		dlcs[5].Name = "Using Chests";
		names[6] = "enchanting";
		dlcs[6].Name = "Enchanting";
		names[7] = "furnace";
		dlcs[7].Name = "Smelting in a Furnace";
		names[8] = "brewing";
		dlcs[8].Name = "Brewing Potions";
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
