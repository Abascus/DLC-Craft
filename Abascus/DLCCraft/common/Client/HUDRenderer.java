package Abascus.DLCCraft.common.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
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
	@ForgeSubscribe
	public void postRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		if(event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
		{
			EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
			PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep.username);
			ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int width = scaledresolution.getScaledWidth();
			int height = scaledresolution.getScaledHeight();
				

				this.mc.renderEngine.func_110577_a(new ResourceLocation("dlc craft/textures/items/Coin.png"));
				this.zLevel = -90.0F;

				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				drawTexturedModelRectFromIcon((int)(width / 1.4), height - 40, DLCCraft.instance.coin.getItemIcon(), 32, 32);
				GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
				mc.fontRenderer.drawString(stats.Coins + "", (int)(width / 1.4)+40, height - 20, 777777, false);

		}
	}


}