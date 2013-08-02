package Abascus.DLCCraft.common;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class DLCCraftItem extends Item
{
	public int id;

	public DLCCraftItem(int par1)
	{
		id = par1;
		super(par1);
	}
	
	
	public void registerIcons(IconRegister par1IconRegister)
	{
		if(id == DLCCraft.instance.CoinID)
		{
		itemIcon = par1IconRegister.registerIcon("DLC Craft:universalCatalyst");
		}
		else
		{
			
		}
	}

}
