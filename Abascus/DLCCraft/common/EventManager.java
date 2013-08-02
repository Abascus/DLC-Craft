package Abascus.DLCCraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
	
	@ForgeSubscribe
    public void onLivingDrop (LivingDropsEvent event)
    {
		try
		{
			EntityPlayer ep = (EntityPlayer)event.source.getSourceOfDamage();
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep.username);
		State s = stats.states.get("mobDrops");
		//if(s.state != 2)
		//{
			event.setCanceled(true);
		//}
		}
		catch(Exception e){}
    }
	
	public void rightClickBlock(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		
	}
	
	public void rightClickAir(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemFood)
		{
			State s = stats.states.get("eat");
			if(s.state != 2)
			{
				event.setCanceled(true);
			}
		}
	}
	
	public void leftClickBlock(PlayerInteractEvent event)
	{
		/*PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);
		if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.wood.blockID)
		{
			
		if(stats.states.get("punchWood").state != 2)
		{
			event.setCanceled(true);
		}
		}*/
	}

}
