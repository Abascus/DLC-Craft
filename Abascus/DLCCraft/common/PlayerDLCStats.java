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
	public DLCManager dlcManager = new DLCManager();

	public void init()
	{
		dlcManager = new DLCManager();
	}

	public void saveToNBT (NBTTagCompound tags)
	{
		dlcManager.saveToNBT(tags);
	}

	public void readFromNBT (NBTTagCompound tags)
	{
		dlcManager.readFromNBT(tags);
	}

}
