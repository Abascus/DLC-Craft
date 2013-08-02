package Abascus.DLCCraft.common.Client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class CoinRenderer implements IItemRenderer
{

	private static RenderItem renderItem = new RenderItem();
	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,  ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data)
	{
		Icon icon = itemStack.getIconIndex();
		renderItem.renderIcon(0, 0, icon, 64, 64);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(GL11.GL_QUADS);
		tessellator.setColorRGBA(0, 0, 0, 512);
		tessellator.addVertex(0, 0, 0);
		tessellator.addVertex(0, 32, 0);
		tessellator.addVertex(32, 32, 0);
		tessellator.addVertex(32, 0, 0);
		tessellator.draw();

		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);

				GL11.glEnable(GL11.GL_TEXTURE_2D);
		String text = Integer.toString(itemStack.getItemDamage());
		//fontRenderer.drawStringWithShadow(text, 1, 1, 0xFFFFFF);
	}
}