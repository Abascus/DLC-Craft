package Abascus.DLCCraft.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventManager 
{
	
	@ForgeSubscribe
	public void playerInteract(PlayerInteractEvent event)
	{
		if(event.action == event.action.RIGHT_CLICK_BLOCK)
		{
			rightClickBlock(event);
		}
		else if(event.action == event.action.RIGHT_CLICK_AIR)
		{
			rightClickAir(event);
		}
		else if(event.action == event.action.LEFT_CLICK_BLOCK)
		{
			leftClickBlock(event);
		}
		
	}
	
	public void rightClickBlock(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		
	}
	
	public void rightClickAir(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		
	}
	
	public void leftClickBlock(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		if(Minecraft.getMinecraft().theWorld.getBlockId(event.x, event.y, event.z) == Block.wood.blockID)
		{
			
		if(stats.states.get("punchWood").state != 2)
		{
			event.setCanceled(true);
		}
		}
	}

}
