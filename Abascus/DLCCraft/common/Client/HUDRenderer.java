package Abascus.DLCCraft.common.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class HUDRenderer extends Gui
{
	private Minecraft mc;

	public HUDRenderer(Minecraft mc)
	{
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
	}

	private static final int BUFF_ICON_SIZE = 18;
	private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
	private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	private static final int BUFF_ICON_BASE_V_OFFSET = 198;
	private static final int BUFF_ICONS_PER_ROW = 8;
	protected static final ResourceLocation resourceLocation = new ResourceLocation("dlc craft/items/Coin.png");
	
	@ForgeSubscribe
	public void postRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		if(event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
		{
			ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int width = scaledresolution.getScaledWidth();
			int height = scaledresolution.getScaledHeight();

			if (!this.mc.playerController.enableEverythingIsScrewedUpMode()) {
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

				this.mc.renderEngine.func_110577_a(resourceLocation);
				this.zLevel = -90.0F;

				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

				drawTexturedModalRect(width / 2 - 91 - 15 - 62, height - 22, 0, 0, 31, 22);
				drawTexturedModalRect(width / 2 - 91 - 15 - 31, height - 22, 151, 0, 31, 22);




				GL11.glDisable(GL11.GL_BLEND);


			}



		}
	}


}