package Abascus.DLCCraft.common;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerDLCStats
{
    public WeakReference<EntityPlayer> player;
    public ConcurrentHashMap<String, State> states = new ConcurrentHashMap<String, State>();
    public List<DLC> dlcs;
    public DLCManager dlcManager = new DLCManager();
    public int Coins;
    
    public void init()
    {
    	for (int i = 0; i < DLCManager.names.length; ++i)
        {
            if (DLCManager.names[i] != null)
            {
            	states.put(DLCManager.names[i], new State(0));
            	DLC dlc = new DLC(i, DLCManager.names[i]);
            }
        }
    }
    
    public void saveToNBT (NBTTagCompound tags)
    {
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound dlc;

        for (int i = 0; i < DLCManager.names.length; ++i)
        {
            if (DLCManager.names[i] != null)
            {
                dlc = new NBTTagCompound();
                dlc.setInteger(DLCManager.names[i], dlcs.get(i).state);
                tagList.appendTag(dlc);
            }
        }

        tags.setTag("DLCCraft", tagList);
        dlcManager.saveToNBT(tags);
    }

    public void readFromNBT (NBTTagCompound tags)
    {
        for (int i = 0; i < DLCManager.names.length; ++i)
        {
            if (DLCManager.names[i] != null)
            {
            	/*NBTTagCompound nbttagcompound = tags.getCompoundTag("DLCCraft");
            	int s =  (nbttagcompound.getInteger(DLCManager.names[i]));
                states.put(DLCManager.names[i], new State(s));
                DLC dlc = new DLC(i, DLCManager.names[i]);
                dlc.setState(s);
            	dlcs.add(i, dlc);*/
            }
        }
        dlcManager.readFromNBT(tags);
    }

}
