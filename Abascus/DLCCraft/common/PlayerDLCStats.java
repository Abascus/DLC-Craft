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
    
    public void saveToNBT (NBTTagCompound tags)
    {
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound dlc;

        for (int i = 0; i < DLCManager.names.length; ++i)
        {
            if (DLCManager.names[i] != null)
            {
                dlc = new NBTTagCompound();
                dlc.setByte(DLCManager.names[i], states.get(DLCManager.names[i]).state);
                tagList.appendTag(dlc);
            }
        }

        tags.setTag("DLCCraft", tagList);
    }

    public void readFromNBT (NBTTagCompound tags)
    {

        for (int i = 0; i < DLCManager.names.length; ++i)
        {
            if (DLCManager.names[i] != null)
            {
            	NBTTagCompound nbttagcompound = tags.getCompoundTag("DLCCraft");
            	byte s = (byte) (nbttagcompound.getByte(DLCManager.names[i]) & 255);
                states.put(DLCManager.names[i], new State(s));
            }
        }
    }

}
