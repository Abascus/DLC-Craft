package Abascus.DLCCraft.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

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
		
		dlcs[0].setState(1);
		dlcs[2].setState(1);
		dlcs[5].setState(1);
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

	        tags.setTag("DLCCraft.DLCManager", tagList);
	    }

	    public void readFromNBT (NBTTagCompound tags)
	    {
	        for (int i = 0; i < DLCManager.names.length; ++i)
	        {
	            if (DLCManager.names[i] != null)
	            {
	            	NBTTagCompound nbttagcompound = tags.getCompoundTag("DLCCraft.DLCManager");
	            	int s =  (nbttagcompound.getInteger(DLCManager.names[i]));
	                DLC dlc = new DLC(i, DLCManager.names[i]);
	                dlc.setState(s);
	            	dlcs[i] = dlc;
	            }
	        }
	    }

}
