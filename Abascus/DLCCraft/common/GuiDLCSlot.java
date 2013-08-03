package Abascus.DLCCraft.common;

import java.util.Date;

import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.SaveFormatComparator;
import Abascus.DLCCraft.common.Client.DLCShopGUI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDLCSlot extends GuiSlot
{
    final DLCShopGUI parentDLCGui;

    public GuiDLCSlot(DLCShopGUI par1DLCShopGUI)
    {
        super(par1DLCShopGUI.mc1, par1DLCShopGUI.width, par1DLCShopGUI.height, 32, par1DLCShopGUI.height - 64, 36);
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
        this.parentDLCGui.drawDefaultBackground();
    }

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        SaveFormatComparator saveformatcomparator = (SaveFormatComparator)DLCShopGUI.getSize(this.parentDLCGui).get(par1);
        String s = saveformatcomparator.getDisplayName();

        if (s == null || MathHelper.stringNullOrLengthZero(s))
        {
            s = DLCShopGUI.func_82313_g(this.parentDLCGui) + " " + (par1 + 1);
        }

        String s1 = saveformatcomparator.getFileName();
        s1 = s1 + " (" + DLCShopGUI.func_82315_h(this.parentDLCGui).format(new Date(saveformatcomparator.getLastTimePlayed()));
        s1 = s1 + ")";
        String s2 = "";

        if (saveformatcomparator.requiresConversion())
        {
            s2 = DLCShopGUI.func_82311_i(this.parentDLCGui) + " " + s2;
        }
        else
        {
            s2 = DLCShopGUI.func_82314_j(this.parentDLCGui)[saveformatcomparator.getEnumGameType().getID()];

            if (saveformatcomparator.isHardcoreModeEnabled())
            {
                s2 = EnumChatFormatting.DARK_RED + I18n.func_135053_a("gameMode.hardcore") + EnumChatFormatting.RESET;
            }

            if (saveformatcomparator.getCheatsEnabled())
            {
                s2 = s2 + ", " + I18n.func_135053_a("selectWorld.cheats");
            }
        }

        this.parentDLCGui.drawString(this.parentDLCGui.mc1.fontRenderer, s, par2 + 2, par3 + 1, 16777215);
        this.parentDLCGui.drawString(this.parentDLCGui.mc1.fontRenderer, s1, par2 + 2, par3 + 12, 8421504);
        this.parentDLCGui.drawString(this.parentDLCGui.mc1.fontRenderer, s2, par2 + 2, par3 + 12 + 10, 8421504);
    }
}
