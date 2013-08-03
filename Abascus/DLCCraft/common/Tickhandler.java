package Abascus.DLCCraft.common;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.DamageSource;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class Tickhandler implements ITickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{

		playerTick((EntityPlayer)tickData[0]);
		if(type.equals(EnumSet.of(TickType.PLAYER)))
		{
			if(Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.loadedEntityList.size() > 0)
			{
				List<EntityPlayer> players = Minecraft.getMinecraft().theWorld.playerEntities;
				for(EntityPlayer player : players)
				{
					DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(player.username).dlcManager;
					if(dlcs.getState("air") != 2)
					{
						if(player.isInWater())
						{
							player.attackEntityFrom(DamageSource.drown, 2);
						}
					}
					else if(dlcs.getState("sprint") != 2)
					{
						if(player.isInWater())
						{
							player.setSprinting(false);
						}
					}
				}
			}
		}

	}
	static int b = 80;
	public static void playerTick(EntityPlayer player)
	{
		if(DLCKeyBinding.keyPressed)
		{
			if(b>2)
			{
				if(player.openContainer instanceof ContainerPlayer)
				{
				player.openGui(DLCCraft.instance, 1, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posY);
				}
				b=0;
			}
			else
			{
				b++;
			}
		}
	}


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
