package Abascus.DLCCraft.common;

import java.lang.ref.WeakReference;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
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
	public void entityConstructing(EntityConstructing event)
	{
	}

	@ForgeSubscribe
	public void itemPickup(EntityItemPickupEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(event.item.getEntityItem().itemID == DLCCraft.instance.CoinID)
		{

		}
		else if(event.item.getEntityItem().itemID == DLCCraft.instance.DLCID)
		{

		}
		else
		{
			if(dlcs.getState("collectDrops") != 2)
			{
				event.setCanceled(true);
			}
		}
	}

	@ForgeSubscribe
	public void entityInteract(EntityInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
	}

	@ForgeSubscribe
	public void itemEvent(ItemEvent event)
	{
		if(event.entityItem.getEntityItem().itemID == DLCCraft.instance.CoinID)
		{
			event.entityItem.boundingBox.maxX = event.entityItem.boundingBox.maxX*3;
			event.entityItem.boundingBox.maxY = event.entityItem.boundingBox.maxY*3;
			event.entityItem.boundingBox.maxZ = event.entityItem.boundingBox.maxZ*3;
		}
	}


	@ForgeSubscribe
	public void onLivingDrop (LivingDropsEvent event)
	{
		EntityPlayer ep = (EntityPlayer)event.source.getSourceOfDamage();
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(ep.username).dlcManager;

		if(dlcs.getState("mobDrops") != 2)
		{
			event.setCanceled(true);
		}

	}

	public void rightClickBlock(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.chest.blockID)
		{

			if(dlcs.getState("chest") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.enchantmentTable.blockID)
		{

			if(dlcs.getState("enchanting") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.brewingStand.blockID)
		{

			if(dlcs.getState("brewing") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.furnaceIdle.blockID)
		{

			if(dlcs.getState("furnace") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.furnaceBurning.blockID)
		{

			if(dlcs.getState("furnace") != 2)
			{
				event.setCanceled(true);
			}
		}
	}

	public void rightClickAir(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemFood)
		{
			if(dlcs.getState("eat") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemPotion)
		{
			if(dlcs.getState("potion") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemBow)
		{
			if(dlcs.getState("bow") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemFishingRod)
		{
			if(dlcs.getState("fishing") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemBlock)
		{
			if(dlcs.getState("placeBlocks") != 2)
			{
				event.setCanceled(true);
			}
		}
	}

	public void leftClickBlock(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.wood.blockID)
		{

			if(dlcs.getState(0) != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemPickaxe && event.entityPlayer.getCurrentEquippedItem().canHarvestBlock(Block.oreDiamond))
		{

			if(dlcs.getState("useIronPick") != 2)
			{
				event.setCanceled(true);
			}
		}
	}

}
