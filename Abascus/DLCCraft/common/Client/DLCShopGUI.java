package Abascus.DLCCraft.common.Client;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Abascus.DLCCraft.common.ContainerDLCShop;
import Abascus.DLCCraft.common.DLC;
import Abascus.DLCCraft.common.DLCCraft;
import Abascus.DLCCraft.common.DLCGuiTabs;
import Abascus.DLCCraft.common.DLCManager;
import Abascus.DLCCraft.common.PlayerDLCStats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DLCShopGUI extends GuiContainer
{
	private static final ResourceLocation field_110424_t = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	private static InventoryBasic inventory = new InventoryBasic("tmp", true, 45);

	/** Currently selected creative inventory tab index. */
	private static int selectedTabIndex = DLCGuiTabs.tabFound.getTabIndex();

	/** Amount scrolled in Creative mode inventory (0 = top, 1 = bottom) */
	private float currentScroll;
	private boolean isScrolling;

	/**
	 * True if the left mouse button was held down last time drawScreen was called.
	 */
	private boolean wasClicking;
	private GuiTextField searchField;


	protected String screenTitle = "DLC Shop";

	private boolean selected;

	private int selectedDLC;

	private List<DLC> buyList = new ArrayList<DLC>();
	public List<DLC> dlcList = new ArrayList<DLC>();
	private GuiDLCSlot dlcSlotContainer;
	public boolean buy = true;


	private GuiButton buttonBuy;
	private GuiButton buttonDone;
	private GuiButton buttonAvalible;
	private GuiButton buttonBought;

	private GuiButton buttonReset;
	private GuiButton buttonUnlock;
	private GuiButton buttonAllBuy;

	/**
	 * Used to back up the ContainerDLCShop's inventory slots before filling it with the player's inventory slots for
	 * the inventory tab.
	 */
	private List backupContainerSlots;
	private Slot field_74235_v;
	private boolean field_74234_w;
	private CreativeCrafting field_82324_x;
	private static int tabPage = 0;
	private int maxPages = 0;
	public EntityPlayer ep;
	public Minecraft mc1;


	public DLCShopGUI(EntityPlayer par1EntityPlayer)
	{
		super(new ContainerDLCShop(par1EntityPlayer));
		ep = par1EntityPlayer;
		par1EntityPlayer.openContainer = this.inventorySlots;
		this.allowUserInput = true;
		this.ySize = 136;
		this.xSize = 195;
		mc1 = mc;
	}

	protected void handleMouseClick(Slot par1Slot, int par2, int par3, int par4)
	{
		this.field_74234_w = true;
		boolean flag = par4 == 1;
		par4 = par2 == -999 && par4 == 0 ? 4 : par4;

		if (par1Slot == null && par4 != 5)
		{
			int l;

			if (par1Slot == this.field_74235_v && flag)
			{
				for (l = 0; l < this.mc.thePlayer.inventoryContainer.getInventory().size(); ++l)
				{
					this.mc.playerController.sendSlotPacket((ItemStack)null, l);
				}
			}
			else
			{              

				this.inventorySlots.slotClick(par1Slot == null ? par2 : par1Slot.slotNumber, par3, par4, this.mc.thePlayer);

				if (Container.func_94532_c(par3) == 2)
				{
					for (l = 0; l < 9; ++l)
					{
						this.mc.playerController.sendSlotPacket(this.inventorySlots.getSlot(45 + l).getStack(), 36 + l);
					}
				}
			}
		}
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{

		super.initGui();
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.searchField = new GuiTextField(this.fontRenderer, this.guiLeft + 82, this.guiTop + 6, 89, this.fontRenderer.FONT_HEIGHT);
		this.searchField.setMaxStringLength(15);
		this.searchField.setEnableBackgroundDrawing(false);
		this.searchField.setVisible(false);
		this.searchField.setTextColor(16777215);
		int i = selectedTabIndex;
		selectedTabIndex = -1;
		this.setCurrentCreativeTab(DLCGuiTabs.creativeTabArray[i]);
		this.field_82324_x = new CreativeCrafting(this.mc);
		this.mc.thePlayer.inventoryContainer.addCraftingToCrafters(this.field_82324_x);
		int tabCount = DLCGuiTabs.creativeTabArray.length;
		if (tabCount > 12)
		{
			buttonList.add(new GuiButton(101, guiLeft,              guiTop - 50, 20, 20, "<"));
			buttonList.add(new GuiButton(102, guiLeft + xSize - 20, guiTop - 50, 20, 20, ">"));
			maxPages = ((tabCount - 12) / 10) + 1;
		}

		this.loadSaves();
		this.dlcSlotContainer = new GuiDLCSlot(this);
		this.dlcSlotContainer.registerScrollButtons(4, 5);
		this.initButtons();
	}

	public void initButtons()
	{
		this.buttonList.add(this.buttonBuy = new GuiButton(1, this.width / 2 - 76, this.height - 28, 72, 20, I18n.func_135053_a("Buy")));
		this.buttonList.add(this.buttonDone = new GuiButton(2, this.width / 2 + 4, this.height - 28, 72, 20, I18n.func_135053_a("gui.done")));

		this.buttonList.add(this.buttonAvalible = new GuiButton(3, this.width / 2 - 100,  10, 72, 20, I18n.func_135053_a("Avalible DLC's")));
		this.buttonList.add(this.buttonBought = new GuiButton(4, this.width / 2 + 30, 10, 72, 20, I18n.func_135053_a("Bought DLC's")));
		if(ep.capabilities.isCreativeMode)
		{
			this.buttonList.add(this.buttonReset = new GuiButton(5, (int)(this.width/1.2), this.height/2-20, 72, 20, I18n.func_135053_a("Reset")));
			this.buttonList.add(this.buttonUnlock = new GuiButton(6, (int)(this.width/1.2), this.height/2, 72, 20, I18n.func_135053_a("Unlock all")));
			this.buttonList.add(this.buttonAllBuy = new GuiButton(7, (int)(this.width/1.2), this.height/2+20, 72, 20, I18n.func_135053_a("Buy all")));
		}
		this.buttonAvalible.enabled = false;
	}

	public void selectDLC(int par1)
	{
		this.mc.displayGuiScreen((GuiScreen)null);

		if (!this.selected)
		{
			this.selected = true;
			String s = this.getSaveFileName(par1);

			if (s == null)
			{
				s = "World" + par1;
			}

			String s1 = this.getDesciption(par1);

			if (s1 == null)
			{
				s1 = "World" + par1;
			}

			if (this.mc.getSaveLoader().canLoadWorld(s))
			{
				this.mc.launchIntegratedServer(s, s1, (WorldSettings)null);
				this.mc.statFileWriter.readStat(StatList.loadWorldStat, 1);
			}
		}
	}

	public String getSaveFileName(int par1)
	{
		DLC dlc;
		if(buy)
		{
			dlc = (DLC)this.buyList.get(par1);
			return DLCManager.description[dlc.id];
		}
		else
		{
			dlc = (DLC)this.dlcList.get(par1);
			return DLCManager.description[dlc.id];
		}
	}

	public String getDesciption(int par1)
	{
		DLC dlc;
		if(buy)
		{
			dlc = (DLC)this.buyList.get(par1);
			return DLCManager.Names[dlc.id];
		}
		else
		{
			dlc = (DLC)this.dlcList.get(par1);
			return DLCManager.Names[dlc.id];
		}
	}

	public int getDepend(int par1)
	{
		DLC dlc;
		if(buy)
		{
			dlc = (DLC)this.buyList.get(par1);
			return DLCManager.dep[dlc.id];
		}
		else
		{
			dlc = (DLC)this.dlcList.get(par1);
			return DLCManager.dep[dlc.id];
		}
	}

	public int getCost(int par1)
	{
		DLC dlc;
		if(buy)
		{
			dlc = (DLC)this.buyList.get(par1);
			return DLCManager.cost[dlc.id];
		}
		else
		{
			dlc = (DLC)this.dlcList.get(par1);
			return DLCManager.cost[dlc.id];
		}
	}


	public static List getSize(DLCShopGUI gui)
	{
		if(gui.buy)
		{
			return gui.buyList;
		}
		else
		{
			return gui.dlcList;
		}
	}

	public static int onElementSelected(DLCShopGUI gui, int par1)
	{
		return gui.selectedDLC = par1;
	}

	public static int getSelectedWorld(DLCShopGUI gui)
	{
		return gui.selectedDLC;
	}
	public static GuiButton getSelectButton(DLCShopGUI gui)
	{
		return gui.buttonBuy;
	}

	private void loadSaves()
	{
		buyList.clear();
		dlcList.clear();

		DLCManager dlcManager = DLCCraft.playerTracker.playerStats.get(ep.username).dlcManager;
		for(int i =0;i<dlcManager.dlcs.length;i++)
		{
			DLC dlc = dlcManager.dlcs[i];

			if(dlcManager.dlcs[i].state == 1)
			{
				buyList.add(dlc);
			}
			else if(dlcManager.dlcs[i].state == 2)
			{
				dlcList.add(dlc);
			}

		}
	}


	public void onGuiClosed()
	{
		super.onGuiClosed();

		if (this.mc.thePlayer != null && this.mc.thePlayer.inventory != null)
		{
			this.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.field_82324_x);
		}

		Keyboard.enableRepeatEvents(false);
	}

	/**
	 * Called when the mouse is clicked.
	 */
	protected void mouseClicked(int par1, int par2, int par3)
	{
		if (par3 == 0)
		{
			int l = par1 - this.guiLeft;
			int i1 = par2 - this.guiTop;
			DLCGuiTabs[] aDLCGuiTabs = DLCGuiTabs.creativeTabArray;
			int j1 = aDLCGuiTabs.length;

			for (int k1 = 0; k1 < j1; ++k1)
			{
				DLCGuiTabs DLCGuiTabs = aDLCGuiTabs[k1];

				if (this.func_74232_a(DLCGuiTabs, l, i1))
				{
					return;
				}
			}
		}

		super.mouseClicked(par1, par2, par3);
	}

	/**
	 * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
	 * mouseMove, which==0 or which==1 is mouseUp
	 */
	protected void mouseMovedOrUp(int par1, int par2, int par3)
	{
		if (par3 == 0)
		{
			int l = par1 - this.guiLeft;
			int i1 = par2 - this.guiTop;
			DLCGuiTabs[] aDLCGuiTabs = DLCGuiTabs.creativeTabArray;
			int j1 = aDLCGuiTabs.length;

			for (int k1 = 0; k1 < j1; ++k1)
			{
				DLCGuiTabs DLCGuiTabs = aDLCGuiTabs[k1];

				if (DLCGuiTabs != null && func_74232_a(DLCGuiTabs, l, i1))
				{
					this.setCurrentCreativeTab(DLCGuiTabs);
					return;
				}
			}
		}

		super.mouseMovedOrUp(par1, par2, par3);
	}

	/**
	 * returns (if you are not on the inventoryTab) and (the flag isn't set) and( you have more than 1 page of items)
	 */
	private boolean needsScrollBars()
	{
		return true;
	}

	private void setCurrentCreativeTab(DLCGuiTabs par1DLCGuiTabs)
	{
		if (par1DLCGuiTabs == null)
		{
			return;
		}

		int i = selectedTabIndex;
		selectedTabIndex = par1DLCGuiTabs.getTabIndex();
		ContainerDLCShop ContainerDLCShop = (ContainerDLCShop)this.inventorySlots;
		this.field_94077_p.clear();
		ContainerDLCShop.dlcList.clear();

		this.currentScroll = 0.0F;
		ContainerDLCShop.scrollTo(0.0F);
	}

	/**
	 * Handles mouse input.
	 */
	public void handleMouseInput()
	{
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();

		if (i != 0 && this.needsScrollBars())
		{
			int j = ((ContainerDLCShop)this.inventorySlots).dlcList.size() / 9 - 5 + 1;

			if (i > 0)
			{
				i = 1;
			}

			if (i < 0)
			{
				i = -1;
			}

			this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);

			if (this.currentScroll < 0.0F)
			{
				this.currentScroll = 0.0F;
			}

			if (this.currentScroll > 1.0F)
			{
				this.currentScroll = 1.0F;
			}

			((ContainerDLCShop)this.inventorySlots).scrollTo(this.currentScroll);
		}
	}

	public ItemStack is = new ItemStack(DLCCraft.instance.coin, 1);
	public void drawScreen(int par1, int par2, float par3)
	{

		dlcSlotContainer.drawScreen(par1, par2, par3);
		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
		this.buttonBuy.drawButton = true;
		this.buttonBuy.drawButton(mc, par1, par2);
		this.buttonDone.drawButton = true;
		this.buttonDone.drawButton(mc, par1, par2);
		this.buttonAvalible.drawButton = true;
		this.buttonAvalible.drawButton(mc, par1, par2);
		this.buttonBought.drawButton = true;
		this.buttonBought.drawButton(mc, par1, par2);
		if(ep.capabilities.isCreativeMode)
		{
			this.buttonReset.drawButton(mc, par1, par2);
			this.buttonUnlock.drawButton(mc, par1, par2);
			this.buttonAllBuy.drawButton(mc, par1, par2);
		}
		this.zLevel = -90.0F;

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 500.0F;
		itemRenderer.zLevel = 500.0F;
		FontRenderer font = null;
		if (is != null)
		{
			font = is.getItem().getFontRenderer(is);
		}
		if (font == null)
		{
			font = fontRenderer;
		}
		itemRenderer.renderItemAndEffectIntoGUI(font, this.mc.func_110434_K(), is, (int)(width / 1.3), 10);
		this.zLevel = 0.0F;
		itemRenderer.zLevel = 0.0F;

		GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.0F);
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep);
		mc.fontRenderer.drawString(stats.dlcManager.Coins + "", (int)(width / 1.3)+40, 14, 2222222, false);


	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
	}

	protected boolean func_74232_a(DLCGuiTabs par1DLCGuiTabs, int par2, int par3)
	{

		int k = par1DLCGuiTabs.getTabColumn();
		int l = 28 * k;
		byte b0 = 0;

		if (k == 5)
		{
			l = this.xSize - 28 + 2;
		}
		else if (k > 0)
		{
			l += k;
		}

		int i1;

		if (par1DLCGuiTabs.isTabInFirstRow())
		{
			i1 = b0 - 32;
		}
		else
		{
			i1 = b0 + this.ySize;
		}

		return par2 >= l && par2 <= l + 28 && par3 >= i1 && par3 <= i1 + 32;
	}

	/**
	 * Renders the creative inventory hovering text if mouse is over it. Returns true if did render or false otherwise.
	 * Params: current creative tab to be checked, current mouse x position, current mouse y position.
	 */
	protected boolean renderCreativeInventoryHoveringText(DLCGuiTabs par1DLCGuiTabs, int par2, int par3)
	{
		int k = par1DLCGuiTabs.getTabColumn();
		int l = 28 * k;
		byte b0 = 0;

		if (k == 5)
		{
			l = this.xSize - 28 + 2;
		}
		else if (k > 0)
		{
			l += k;
		}

		int i1;

		if (par1DLCGuiTabs.isTabInFirstRow())
		{
			i1 = b0 - 32;
		}
		else
		{
			i1 = b0 + this.ySize;
		}

		if (this.isPointInRegion(l + 3, i1 + 3, 23, 27, par2, par3))
		{
			this.drawCreativeTabHoveringText(I18n.func_135053_a(par1DLCGuiTabs.getTranslatedTabLabel()), par2, par3);
			return true;
		}
		else
		{
			return false;
		}
	}
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(ep);
		if (par1GuiButton.id == 1)
		{
			DLC dlc;
			if(buy)
			{
				if(this.buyList.toArray().length>=selectedDLC+1)
				{
					dlc = (DLC)this.buyList.get(selectedDLC);

					if(stats.dlcManager.Coins >= stats.dlcManager.cost[dlc.id] || ep.capabilities.isCreativeMode)
					{
						if(getDepend(selectedDLC) != -1)
						{
							for(DLC dlc1 : dlcList)
							{
								if(dlc1.id == getDepend(selectedDLC))
								{
									if(!ep.capabilities.isCreativeMode)
									{
										stats.dlcManager.Coins-=stats.dlcManager.cost[dlc.id];
									}
									stats.dlcManager.dlcs[dlc.id].state=2;
									DLCCraft.playerTracker.playerStats.put(ep.username, stats);
									DLCCraft.playerTracker.sendDLCs(ep, stats);
									break;
								}	
							}
						}
						else
						{
							if(!ep.capabilities.isCreativeMode)
							{
								stats.dlcManager.Coins-=stats.dlcManager.cost[dlc.id];
							}
							stats.dlcManager.dlcs[dlc.id].state=2;
							DLCCraft.playerTracker.playerStats.put(ep.username, stats);
							DLCCraft.playerTracker.sendDLCs(ep, stats);
						}
						this.loadSaves();

					}
				}
			}

		}
		else if (par1GuiButton.id == 2)
		{
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		}
		else if (par1GuiButton.id == 3)
		{
			buy=true;
			this.buttonAvalible.enabled = false;
			this.buttonBought.enabled = true;
		}
		else if (par1GuiButton.id == 4)
		{
			buy = false;
			this.buttonAvalible.enabled = true;
			this.buttonBought.enabled = false;
		}
		else if (par1GuiButton.id == 5)
		{
			stats.init();
			stats.dlcManager.Coins = 30;
			DLCCraft.playerTracker.playerStats.put(ep.username, stats);
			DLCCraft.playerTracker.sendDLCs(ep, stats);
			this.loadSaves();

		}
		else if (par1GuiButton.id == 6)
		{
			for(int i = 0;i<stats.dlcManager.dlcs.length;i++)
			{
				stats.dlcManager.dlcs[i].state=1;
			}

			DLCCraft.playerTracker.playerStats.put(ep.username, stats);
			DLCCraft.playerTracker.sendDLCs(ep, stats);
			this.loadSaves();

		}
		else if (par1GuiButton.id == 7)
		{
			for(int i = 0;i<stats.dlcManager.dlcs.length;i++)
			{
				stats.dlcManager.dlcs[i].state=2;
			}
			DLCCraft.playerTracker.playerStats.put(ep.username, stats);
			DLCCraft.playerTracker.sendDLCs(ep, stats);
			this.loadSaves();

		}
		else
		{
			this.dlcSlotContainer.actionPerformed(par1GuiButton);
		}
	}

	/**
	 * Returns the current creative tab index.
	 */
	public int getCurrentTabIndex()
	{
		return selectedTabIndex;
	}

	/**
	 * Returns the creative inventory
	 */
	public static InventoryBasic getInventory()
	{
		return inventory;
	}
}
