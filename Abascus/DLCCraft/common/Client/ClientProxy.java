package Abascus.DLCCraft.common.Client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import Abascus.DLCCraft.common.CommonProxy;
import Abascus.DLCCraft.common.DLCCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
		MinecraftForgeClient.registerItemRenderer(DLCCraft.instance.coin.itemID, new CoinRenderer());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return new DLCShopGUI(player);
	}
}
