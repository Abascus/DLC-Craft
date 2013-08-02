package Abascus.DLCCraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommonProxy implements IGuiHandler 
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z)
	{
		return new ContainerDLCShop(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return null;
	}

}
