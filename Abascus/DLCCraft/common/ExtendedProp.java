package Abascus.DLCCraft.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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

	/**
	 * This method is a great static accessor; I strongly recommend doing it this way.  It also is nice and
	 * clean for access, especially if you have multiple extended property classes.
	 **/
	public static ExtendedProp For(EntityLiving entity)
	{
		ExtendedProp exp = (ExtendedProp)entity.getExtendedProperties(identifier);
		exp.ep = entity;
		return exp;
	}

	public void saveNBTData(NBTTagCompound tag) 
	{

	}

	public void loadNBTData(NBTTagCompound tag)
	{

	}

	public void init(Entity entity, World world) 
	{

	}

}