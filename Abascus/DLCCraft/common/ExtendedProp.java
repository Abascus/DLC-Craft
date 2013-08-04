package Abascus.DLCCraft.common;

import java.lang.ref.WeakReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 *  The IExtendedProperties implementation is all we really care about here - this is where you put your properties,
 *  methods, as well as handle save/load to NBT, etc.
 **/
public class ExtendedProp implements IExtendedEntityProperties
{
	public EntityPlayer ep;


	public static final String identifier = "DLCCraft";

	public static ExtendedProp For(EntityPlayer entity)
	{
		ExtendedProp exp = (ExtendedProp)entity.getExtendedProperties(identifier);
		exp.ep = entity;
		return exp;
	}

	public void saveNBTData(NBTTagCompound tag) 
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep.username);
		stats.saveToNBT(tag);

	}

	public void loadNBTData(NBTTagCompound tag)
	{
		PlayerDLCStats stats = new PlayerDLCStats();
		stats.player = new WeakReference<EntityPlayer>(ep);		
		stats.readFromNBT(tag);
		 DLCCraft.playerTracker.playerStats.put(ep.username, stats);
	}

	public void init(Entity entity, World world) 
	{

	}

}