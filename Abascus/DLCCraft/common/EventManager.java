package Abascus.DLCCraft.common;

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
			
		}
		else if(event.action == event.action.LEFT_CLICK_BLOCK)
		{
			
		}
		
	}
	
	public void rightClickBlock(PlayerInteractEvent event)
	{
		
	}
	
	public void rightClickAir(PlayerInteractEvent event)
	{
		
	}
	
	public void leftClickBlock(PlayerInteractEvent event)
	{
		
	}

}
