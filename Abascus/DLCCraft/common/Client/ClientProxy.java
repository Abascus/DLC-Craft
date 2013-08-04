package Abascus.DLCCraft.common.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import Abascus.DLCCraft.common.CommonProxy;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
		TickRegistry.registerTickHandler(new RenderTickHandler(), Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new HUDRenderer(Minecraft.getMinecraft()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return new DLCShopGUI(player);
	}
}
