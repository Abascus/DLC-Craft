package Abascus.DLCCraft.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class DLCCraftItem extends Item
{
	public int id;

	public DLCCraftItem(int par1)
	{
		super(par1);
		id = par1;
		
	}
	
	
	public void registerIcons(IconRegister par1IconRegister)
	{
		if(id == DLCCraft.instance.CoinID)
		{
			itemIcon = par1IconRegister.registerIcon("DLC Craft:coin");
		}
		else if(id == DLCCraft.instance.DLCID)
		{
			itemIcon = par1IconRegister.registerIcon("DLC Craft:dlc");
		}
		else
		{
			itemIcon = par1IconRegister.registerIcon("DLC Craft:shop");
		}
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
		if(par1ItemStack.itemID == DLCCraft.instance.shop.itemID)
		{
			player.openGui(DLCCraft.instance, 0, world, player.posX, player.posY, player.posZ);
		}
		return par1ItemStack;
    }
	


	public Icon getItemIcon()
	{
		return this.itemIcon;
	}

}
