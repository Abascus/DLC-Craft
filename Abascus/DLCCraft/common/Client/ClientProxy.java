package Abascus.DLCCraft.common.Client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import Abascus.DLCCraft.common.CommonProxy;
import Abascus.DLCCraft.common.DLCCraft;
import Abascus.DLCCraft.common.DLCKeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public static KeyBinding[] key = {new KeyBinding("DLC Shop", Keyboard.KEY_F)};
	boolean[] repeat = {false};
	
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
		MinecraftForge.EVENT_BUS.register(new HUDRenderer(Minecraft.getMinecraft()));
		KeyBindingRegistry.registerKeyBinding(new DLCKeyBinding(key, repeat));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z)
	{
		return new DLCShopGUI(player);
	}
}
