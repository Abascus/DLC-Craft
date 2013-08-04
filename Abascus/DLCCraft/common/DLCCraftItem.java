package Abascus.DLCCraft.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

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
	


	public Icon getItemIcon()
	{
		return this.itemIcon;
	}

}
