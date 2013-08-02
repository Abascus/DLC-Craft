package Abascus.DLCCraft.common.Client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.SideOnly;
import Abascus.DLCCraft.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return null;
	}
}
