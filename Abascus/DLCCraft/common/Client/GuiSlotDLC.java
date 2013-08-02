package Abascus.DLCCraft.common.Client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSlotDLC extends GuiSlot
{
	private final List field_77251_g;
	private final Map field_77253_h;

	final DLCShopGUI languageGui;

	public GuiSlotDLC(DLCShopGUI par1DLCShopGUI)
	{
		super(par1DLCShopGUI.mc1, par1DLCShopGUI.width, par1DLCShopGUI.height, 32, par1DLCShopGUI.height - 65 + 4, 18);
		this.languageGui = par1DLCShopGUI;
		this.field_77251_g = Lists.newArrayList();
		this.field_77253_h = Maps.newHashMap();
		Iterator iterator = DLCShopGUI.func_135011_a(par1DLCShopGUI).func_135040_d().iterator();

		while (iterator.hasNext())
		{
			Language language = (Language)iterator.next();
			this.field_77253_h.put(language.func_135034_a(), language);
			this.field_77251_g.add(language.func_135034_a());
		}
	}

	/**
	 * Gets the size of the current slot list.
	 */
	 protected int getSize()
	{
		 return this.field_77251_g.size();
	}

	 /**
	  * the element in the slot that was clicked, boolean for wether it was double clicked or not
	  */
	 protected void elementClicked(int par1, boolean par2)
	 {
		 Language language = (Language)this.field_77253_h.get(this.field_77251_g.get(par1));
		 DLCShopGUI.func_135011_a(this.languageGui).func_135045_a(language);
		 DLCShopGUI.getGameSettings(this.languageGui).language = language.func_135034_a();
		 this.languageGui.mc.func_110436_a();
		 languageGui.mc1.fontRenderer.setUnicodeFlag(DLCShopGUI.func_135011_a(this.languageGui).func_135042_a());
		 languageGui.mc1.fontRenderer.setBidiFlag(DLCShopGUI.func_135011_a(this.languageGui).func_135044_b());
		 DLCShopGUI.getDoneButton(this.languageGui).displayString = I18n.func_135053_a("gui.done");
		 DLCShopGUI.getGameSettings(this.languageGui).saveOptions();
	 }

	 /**
	  * returns true if the element passed in is currently selected
	  */
	 protected boolean isSelected(int par1)
	 {
		 return ((String)this.field_77251_g.get(par1)).equals(DLCShopGUI.func_135011_a(this.languageGui).func_135041_c().func_135034_a());
	 }

	 /**
	  * return the height of the content being scrolled
	  */
	 protected int getContentHeight()
	 {
		 return this.getSize() * 18;
	 }

	 protected void drawBackground()
	 {
		 this.languageGui.drawDefaultBackground();
	 }

	 protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
	 {
		 languageGui.mc1.fontRenderer.setBidiFlag(true);
		 this.languageGui.drawCenteredString( languageGui.mc1.fontRenderer, ((Language)this.field_77253_h.get(this.field_77251_g.get(par1))).toString(), this.languageGui.width / 2, par3 + 1, 16777215);
		 languageGui.mc1.fontRenderer.setBidiFlag(DLCShopGUI.func_135011_a(this.languageGui).func_135041_c().func_135035_b());
	 }
}
