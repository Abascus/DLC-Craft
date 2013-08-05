package Abascus.DLCCraft.common;

import java.awt.Toolkit;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

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

	public static void spawnItemAtPlayer (EntityPlayer player, ItemStack stack)
	{
		EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, stack);
		player.worldObj.spawnEntityInWorld(entityitem);
		if (!(player instanceof FakePlayer))
		{
			entityitem.onCollideWithPlayer(player);
		}
	}

	@ForgeSubscribe
	public void itemPickup(EntityItemPickupEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer).dlcManager;
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer);
		if(event.item.getEntityItem().itemID == DLCCraft.instance.coin.itemID)
		{
			event.setCanceled(true);
			stats.dlcManager.Coins+= event.item.getEntityItem().stackSize;
			DLCCraft.playerTracker.playerStats.put(event.entityPlayer.username, stats);
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.SERVER)
			{
				DLCCraft.playerTracker.sendDLCs2(event.entityPlayer, stats);
			}
			else
			{
				DLCCraft.playerTracker.sendDLCs(event.entityPlayer, stats);
			}

			float f = (float)Math.pow(2.0D, (double)(5) / 12.0D);
			event.item.worldObj.playSoundEffect((double)event.item.posX + 0.5D, (double)event.item.posY + 0.5D, (double)event.item.posZ + 0.5D, "note.harp", 3.0F, f);
			event.item.setDead();
			event.item.attackEntityFrom(DamageSource.inFire, 10000);
		}
		else if(event.item.getEntityItem().itemID == DLCCraft.instance.dlc.itemID)
		{
			event.setCanceled(true);

			int i;
			for(i=0; i<stats.dlcManager.dlcs.length;i++)
			{
			}
			int[] ids = new int[i];
			for(i=0; i<stats.dlcManager.dlcs.length;i++)
			{
				if(stats.dlcManager.dlcs[i].state == 0)
				{
					ids[i]=stats.dlcManager.dlcs[i].id;
				}
			}
			int r = new Random().nextInt(i-1);
			stats.dlcManager.dlcs[r].state = 1;
			DLCCraft.playerTracker.playerStats.put(event.entityPlayer.username, stats);
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.SERVER)
			{
				DLCCraft.playerTracker.sendDLCs2(event.entityPlayer, stats);
			}
			else
			{
				DLCCraft.playerTracker.sendDLCs(event.entityPlayer, stats);
			}
			float f = (float)Math.pow(2.0D, (double)(5) / 12.0D);
			event.item.worldObj.playSoundEffect((double)event.item.posX + 0.5D, (double)event.item.posY + 0.5D, (double)event.item.posZ + 0.5D, "note.harp", 3.0F, f);
			event.item.setDead();
			event.item.attackEntityFrom(DamageSource.inFire, 10000);
		}
		else if(event.item.getEntityItem().itemID == DLCCraft.instance.shop.itemID)
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

		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer).dlcManager;
		if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemFood)
			{
				if(dlcs.getState("feedAnimal") != 2)
				{
					event.setCanceled(true);
				}
			}
			else if(event.entityPlayer.getCurrentEquippedItem().itemID== Item.wheat.itemID)
			{
				if(dlcs.getState("feedAnimal") != 2)
				{
					event.setCanceled(true);
				}
			}
			else if(event.entityPlayer.getCurrentEquippedItem().itemID== Item.seeds.itemID)
			{
				if(dlcs.getState("feedAnimal") != 2)
				{
					event.setCanceled(true);
				}
			}
			else if(event.entityLiving instanceof EntityVillager)
			{
				if(dlcs.getState("trade") != 2)
				{
					event.setCanceled(true);
				}
			}
		}



	}

	@ForgeSubscribe
	public void itemEvent(ItemEvent event)
	{
	}

	@ForgeSubscribe
	public void itemExpireEvent(ItemExpireEvent event)
	{
		if(event.entityItem.getEntityItem().itemID == DLCCraft.instance.coin.itemID)
		{
			event.extraLife = 9999;
			event.setCanceled(true);
		}

	}


	@ForgeSubscribe
	public void livingHurt(LivingHurtEvent event)
	{
		Entity entity = event.source.getSourceOfDamage();
		try
		{
			if(entity != null)
			{
				if(event.entityLiving instanceof EntityPlayer)
				{

					EntityPlayer ep = (EntityPlayer) event.source.getSourceOfDamage();
					DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(ep).dlcManager;

					if(dlcs.getState("PvP") != 2)
					{
						event.setCanceled(true);
					}

				}
				else if(((EntityPlayer)entity).getCurrentEquippedItem() != null)
				{
					if(Item.itemsList[((EntityPlayer)entity).getCurrentEquippedItem().itemID] instanceof ItemSword)
					{
						EntityPlayer ep = (EntityPlayer)entity;
						DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(ep).dlcManager;

						if(dlcs.getState("sword") != 2)
						{
							event.ammount = 0;
						}
					}
				}
			}
		}
		catch(Exception e){}
	}

	@ForgeSubscribe
	public void onLivingDrop (LivingDropsEvent event)
	{
		try
		{
			EntityPlayer ep = (EntityPlayer)event.source.getSourceOfDamage();
			DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(ep).dlcManager;

			if(dlcs.getState("mobDrops") != 2)
			{
				event.setCanceled(true);
			}

			if(dlcs.getState("mobCoins") != 2)
			{
				if(new Random().nextInt(2) == 0)
				{
					event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posY, event.entityLiving.posZ, event.entityLiving.posX,new ItemStack(DLCCraft.instance.coin, new Random().nextInt(3)+1)));
				}
			}
		}
		catch(Exception e){}
		if(new Random().nextInt(50) == 0)
		{
			event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posY, event.entityLiving.posZ, event.entityLiving.posX,new ItemStack(DLCCraft.instance.dlc, 1)));
		}

	}

	public void rightClickBlock(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer).dlcManager;
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer);
		if(new Random().nextInt(30) == 0)
		{
			if(stats.dlcManager.a<100)
			{
				stats.dlcManager.Coins+=new Random().nextInt(2)+1;
				stats.dlcManager.a+=1;
			}
			DLCCraft.playerTracker.playerStats.put(event.entityPlayer.username, stats);
			
		}
		
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
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.stoneButton.blockID)
		{

			if(dlcs.getState("buttonLever") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.woodenButton.blockID)
		{

			if(dlcs.getState("buttonLever") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.lever.blockID)
		{

			if(dlcs.getState("buttonLever") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.hopperBlock.blockID)
		{

			if(dlcs.getState("hopper") != 2)
			{
				event.setCanceled(true);
			}
		}
		else if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemBlock )
			{
				if(dlcs.getState("placeBlocks") != 2)
				{
					event.setCanceled(true);
				}
			}
		}
	}

	public void rightClickAir(PlayerInteractEvent event)
	{
		ItemStack diary = new ItemStack(DLCCraft.instance.shop);
		if(!event.entityPlayer.inventory.hasItemStack(diary))
		{
			if (!event.entityPlayer.inventory.addItemStackToInventory(diary))
			{
				spawnItemAtPlayer(event.entityPlayer, diary);
			}
		}

		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer).dlcManager;
		if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
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
	}

	public void leftClickBlock(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer).dlcManager;

		if(dlcs.getState("breakBlock") != 2)
		{
			event.setCanceled(true);
		}
		if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.wood.blockID)
		{

			if(dlcs.getState("punchWood") != 2)
			{
				event.setCanceled(true);
			}
		}
		if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemPickaxe && event.entityPlayer.getCurrentEquippedItem().canHarvestBlock(Block.oreDiamond))
			{

				if(dlcs.getState("useIronPick") != 2)
				{
					event.setCanceled(true);
				}
			}
		}
		if(event.entityPlayer.getCurrentEquippedItem() != null)
		{
			if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemAxe)
			{

				if(dlcs.getState("Axe") != 2)
				{
					event.setCanceled(true);
				}
			}
		}
	}

}
