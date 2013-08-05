package Abascus.DLCCraft.common.Client;

import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.SaveFormatComparator;
import Abascus.DLCCraft.common.DLC;
import Abascus.DLCCraft.common.DLCCraft;
import Abascus.DLCCraft.common.PlayerDLCStats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDLCSlot extends GuiSlot
{
	final DLCShopGUI parentDLCGui;

	public GuiDLCSlot(DLCShopGUI par1DLCShopGUI)
	{
		super(Minecraft.getMinecraft(), par1DLCShopGUI.width, par1DLCShopGUI.height, 32, par1DLCShopGUI.height - 64, 36);
		this.parentDLCGui = par1DLCShopGUI;
	}

	/**
	 * Gets the size of the current slot list.
	 */
	protected int getSize()
	{
		return DLCShopGUI.getSize(this.parentDLCGui).size();
	}

	/**
	 * the element in the slot that was clicked, boolean for wether it was double clicked or not
	 */
	protected void elementClicked(int par1, boolean par2)
	{
		DLCShopGUI.onElementSelected(this.parentDLCGui, par1);
		boolean flag1 = DLCShopGUI.getSelectedWorld(this.parentDLCGui) >= 0 && DLCShopGUI.getSelectedWorld(this.parentDLCGui) < this.getSize();
		DLCShopGUI.getSelectButton(this.parentDLCGui).enabled = flag1;

		if (par2 && flag1)
		{
			this.parentDLCGui.selectDLC(par1);
		}
	}



	/**
	 * returns true if the element passed in is currently selected
	 */
	protected boolean isSelected(int par1)
	{
		return par1 == DLCShopGUI.getSelectedWorld(this.parentDLCGui);
	}

	/**
	 * return the height of the content being scrolled
	 */
	protected int getContentHeight()
	{
		return DLCShopGUI.getSize(this.parentDLCGui).size() * 36;
	}

	protected void drawBackground()
	{
		//  this.parentDLCGui.drawDefaultBackground();
	}

	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(parentDLCGui.ep);
		DLC dlc = (DLC)DLCShopGUI.getSize(this.parentDLCGui).get(par1);
		String s = parentDLCGui.getDesciption(par1);
		String s1 = parentDLCGui.getSaveFileName(par1);
		String s2 = "Cost: " + parentDLCGui.getCost(par1);



		int slot;
		boolean c = false;
		if(parentDLCGui.buy)
		{
			if(stats.dlcManager.Coins >= stats.dlcManager.cost[dlc.id])
			{
				c = true;
			}
		}

		this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s, par2 + 2, par3 + 1, 16777215);
		this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s1, par2 + 2, par3 + 12, 8421504);
		if(parentDLCGui.buy)
		{
			if(c)
			{
				this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s2, par2 + 2, par3 + 24, 2421504);
			}
			else
			{
				this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s2, par2 + 2, par3 + 24, 11421504);
			}
		}
		else
		{
			this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s2, par2 + 2, par3 + 24, 21421504);
		}
		if(parentDLCGui.getDepend(par1) != -1)
		{
			String s3 = "Depends on: " + stats.dlcManager.Names[parentDLCGui.getDepend(par1)];
			this.parentDLCGui.drawString(Minecraft.getMinecraft().fontRenderer, s3, par2 + 2+(s2.length()*6), par3 + 24, 8421504);
		}
	}
}
