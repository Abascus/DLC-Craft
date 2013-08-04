package Abascus.DLCCraft.common.Client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Abascus.DLCCraft.common.DLCCraft;
import Abascus.DLCCraft.common.PlayerDLCStats;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderTickHandler implements ITickHandler
{

	public RenderTickHandler()
	{
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}


	public ItemStack is = new ItemStack(DLCCraft.instance.coin, 1);
	protected static RenderItem itemRenderer = new RenderItem();

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		try
		{
			if(Minecraft.getMinecraft().currentScreen == null)
			{
				EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
				PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep.username);
				ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
				int width = scaledresolution.getScaledWidth();
				int height = scaledresolution.getScaledHeight();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				GL11.glTranslatef(0.0F, 0.0F, 32.0F);
				itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().func_110434_K(), is, (int)(width / 1.4), height - 20);


				Minecraft.getMinecraft().fontRenderer.drawString(stats.Coins + "", (int)(width / 1.4)+20, height - 20, 11421504, false);
			}
		}
		catch(Exception e){}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		return "DLC Craft - HUD Renderer - Client";
	}

}
