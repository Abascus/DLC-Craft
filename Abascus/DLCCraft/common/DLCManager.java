package Abascus.DLCCraft.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DLCManager 
{
	public static int lenght = 27;
	public static String[] names = new String[lenght];
	public static String[] Names = new String[lenght];
	public static int[] dep = new int[lenght];
	public static String[] description = new String[lenght];
	public static int[] cost = new int[lenght];
	public DLC[] dlcs = new DLC[lenght];
	public int Coins = 20;
	
	public DLCManager()
	{
		
		for(int i = 0;i<dep.length;i++)
		{
			dep[i] = -1;
		}
		names[0] = "breakBlock";
		names[1] = "punchWood";
		names[2] = "eat";
		names[3] = "air";
		names[4] = "potion";
		names[5] = "chest";
		names[6] = "enchanting";
		names[7] = "furnace";
		names[8] = "brewing";
		names[9] = "mobDrops";
		names[10] = "axe";
		names[11] = "sprint";
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
		names[26] = "inGameCoins";
		
		Names[0] = "Breaking Blocks";
		Names[1] = "Breaking Wood";
		Names[2] = "Eating";
		Names[3] = "Breathing";
		Names[4] = "Drinking Potions";
		Names[5] = "Using Chests";
		Names[6] = "Enchanting";
		Names[7] = "Using a Furnace";
		Names[8] = "Brewing Potions";
		Names[9] = "Mob Drops";
		Names[10] = "Axe";
		Names[11] = "Sprinting";
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
		Names[26] = "InGame Coin HUD";
		
		description[0] = "Destroy the Terrain!";		
		description[1] = "Wood is THE basic material, so get it, fast!!";		
		description[2] = "You're getting Hungry?";		
		description[3] = "Swimming is fun, drowning isn't";		
		description[4] = "Time to drink something?";		
		description[5] = "Wanna sort your Items?";		
		description[6] = "Do you wanna do some Magic?";		
		description[7] = "Normal crafting isn't enought?";		
		description[8] = "Potions potions poison";		
		description[9] = "Do you wanna start getting mob Drops?";		
		description[10] = "Timber";		
		description[11] = "You wanna run faster? You need to Sprint";		
		description[12] = "Bang bang";		
		description[13] = "Poke them with your Sword, poke them hard!";		
		description[14] = "Wait, why are you unable to collect Items?";		
		description[15] = "Higher, higher";		
		description[16] = "Build something big!";		
		description[17] = "Pickaxe upgrades?";		
		description[18] = "Blub blub, whats there in the water?";		
		description[19] = "Your not the only one who wanna eat!";		
		description[20] = "Give me your Items, and stop making noises!";		
		description[21] = "Did you thought PvP is off? You were wrong!";		
		description[22] = "Do you wanna access yout Items?";		
		description[23] = "Now sart Bbuilding whith Redstone!";		
		description[24] = "What? you haven't got enought Coins?";		
		description[25] = "Hop, hop, Hoppers!";		
		description[26] = "Do you wanna see, how many Coins you have?";		
		
		cost[0] = 5;
		cost[1] = 5;
		cost[2] = 10;
		cost[3] = 15;
		cost[4] = 25;
		cost[5] = 10;
		cost[6] = 100;
		cost[7] = 10;
		cost[8] = 30;
		cost[9] = 20;
		cost[10] = 10;
		cost[11] = 10;
		cost[12] = 30;
		cost[13] = 15;
		cost[14] = 5;
		cost[15] = 10;
		cost[16] = 10;
		cost[17] = 25;
		cost[18] = 20;
		cost[19] = 50;
		cost[20] = 50;
		cost[21] = 10;
		cost[22] = 5;
		cost[23] = 20;
		cost[24] = 150;
		cost[25] = 50;
		cost[26] = 5;
		
		for(int i = 0; i< dlcs.length; i++)
		{
			dlcs[i] = new DLC(i, names[i], Names[i], description[i], dep[i], cost[i]);
		}
		
		dlcs[0].setState(1);
		dlcs[1].setState(1);
		dlcs[2].setState(1);
		dlcs[5].setState(1);
		dlcs[13].setState(1);
		dlcs[14].setState(1);
		dlcs[15].setState(1);
		dlcs[16].setState(1);
		dlcs[21].setState(1);
		dlcs[22].setState(1);
		dlcs[26].setState(1);
		
		dep[1] = 0;
		dep[4] = 8;
	}
	
	public int getState(int id)
	{
		return dlcs[id].state;
	}
	
	public int getID(String s)
	{
		for(int i = 0; i<names.length; i++)
		{
			if(s.toLowerCase().equalsIgnoreCase(names[i].toLowerCase()))
			{
				return i;
			}
		}
		return 0;
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

	        for (int i = 0; i < dlcs.length; i++)
	        {
	            if (dlcs[i] != null)
	            {
	                dlc = new NBTTagCompound();
	                dlc.setInteger(dlcs[i].name, dlcs[i].state);
	                tagList.appendTag(dlc);
	            }
	        }
	        
	        dlc = new NBTTagCompound();
            dlc.setInteger("Coins", Coins);
            tagList.appendTag(dlc);

	        tags.setTag("DLCCraft", tagList);
	    }

	    public void readFromNBT (NBTTagCompound tags)
	    {
	    	NBTTagList nbttaglist = tags.getTagList("DLCCraft");
	    	int i = 0;
	        for (i = 0; i < DLCManager.names.length; i++)
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
	        NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
	        Coins =  (nbttagcompound.getInteger("Coins"));
	    }

}
