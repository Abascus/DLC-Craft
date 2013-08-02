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
    public int level;
    public int levelHealth;
    public int bonusHealth;
    public int hunger;
    public ConcurrentHashMap<String, State> states = new ConcurrentHashMap<String, State>();
    
    public void saveToNBT (EntityPlayer entityplayer)
    {
        NBTTagCompound tags = entityplayer.getEntityData();
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

    public void readFromNBT (EntityPlayer entityplayer)
    {
        NBTTagCompound tags = entityplayer.getEntityData();
        NBTTagList tagList = tags.getTagList("DLCCraft");
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if (itemstack != null)
            {
                this.inventory[j] = itemstack;
            }
        }
    }

}
