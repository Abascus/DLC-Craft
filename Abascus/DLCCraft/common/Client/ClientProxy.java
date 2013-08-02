package Abascus.DLCCraft.common.Client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import Abascus.DLCCraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return new DLCShopGUI(player);
	}
}
