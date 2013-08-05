package Abascus.DLCCraft.common;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import Abascus.DLCCraft.common.Client.DLCShopGUI;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class Tickhandler implements ITickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		EntityPlayer player = (EntityPlayer)tickData[0];
		if(type.equals(EnumSet.of(TickType.PLAYER)))
		{
			DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(player).dlcManager;
			if(dlcs.getState("air") != 2)
			{
				if(player.isInWater())
				{
					player.attackEntityFrom(DamageSource.drown, 2);
				}
			}
			if(dlcs.getState("sprint") != 2)
			{
				player.setSprinting(false);
			}
			if(dlcs.getState("jump") != 2)
			{
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, -3));
			}
			
		}

	}
	public static int b = 20;


	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{

	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel()
	{
		return "DLC Craft - Player update tick - General";
	}

}
