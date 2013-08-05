package Abascus.DLCCraft.common.Client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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

	private static final ResourceLocation rr = new ResourceLocation("dlc craft/items/coin.png");
    
	int zLevel;
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		
		try
		{
			if(Minecraft.getMinecraft().currentScreen == null)
			{
				EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
				PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep);
				
				if(stats.dlcManager.getState("inGameCoins") == 2)
				{				
				ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
				int width = scaledresolution.getScaledWidth();
				int height = scaledresolution.getScaledHeight();
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				GL11.glTranslatef(0.0F, 0.0F, 32.0F);
				
				itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().func_110434_K(), is, (int)(width / 1.3), 10);
				Minecraft.getMinecraft().func_110434_K().func_110577_a(rr);
				drawTexturedModalRect((int)(width / 1.3), 10, 0, 0, 64, 64);
				Minecraft.getMinecraft().fontRenderer.drawString(stats.dlcManager.Coins + "", (int)(width / 1.3)+40, 14, 11421504, false);
				}
				}
		}
		catch(Exception e){}
	}
	
	 public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
	    {
	        float f = 0.00390625F;
	        float f1 = 0.00390625F;
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
	        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
	        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
	        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
	        tessellator.draw();
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
