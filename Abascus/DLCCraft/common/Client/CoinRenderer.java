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
	}
}