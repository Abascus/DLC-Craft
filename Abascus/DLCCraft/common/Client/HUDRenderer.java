package Abascus.DLCCraft.common.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Abascus.DLCCraft.common.DLCCraft;
import Abascus.DLCCraft.common.PlayerDLCStats;

public class HUDRenderer extends Gui
{
	private Minecraft mc;

	public HUDRenderer(Minecraft mc)
	{
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
	}

	public ItemStack is = new ItemStack(DLCCraft.instance.coin, 1);
	protected static RenderItem itemRenderer = new RenderItem();
	@ForgeSubscribe
	public void postRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		if(event.type == RenderGameOverlayEvent.ElementType.ALL)
		{
			EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
			PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep.username);
			ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int width = scaledresolution.getScaledWidth();
			int height = scaledresolution.getScaledHeight();


			this.zLevel = -90.0F;

			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glTranslatef(0.0F, 0.0F, 32.0F);
			this.zLevel = 500.0F;
			itemRenderer.zLevel = 500.0F;
			itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, this.mc.func_110434_K(), is, (int)(width / 1.4), height - 20);
			this.zLevel = 0.0F;
			itemRenderer.zLevel = 0.0F;

			GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
			mc.fontRenderer.drawString(stats.Coins + "", (int)(width / 1.4)+40, height - 20, 777777, false);

		}
	}


}