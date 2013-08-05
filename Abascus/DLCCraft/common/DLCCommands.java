package Abascus.DLCCraft.common;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class DLCCommands extends CommandBase {

	@Override
	public String getCommandName() 
	{
		return "DLC";
	}
	
	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return getUsageString();
    }
	
	@Override
    public List getCommandAliases()
    {
		return Arrays.asList(new String[] {"DLC"});
    }

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) 
	{
		if(astring.length > 0)
		{
			String command = astring[0];
			
			if(astring.length == 3)
			{
				if("delet".startsWith(astring[1].toLowerCase()))
				{
					try
					{
					PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(astring[0]);
					int i = stats.dlcManager.getID(astring[2]);
					stats.dlcManager.dlcs[i].state = 0;
					DLCCraft.playerTracker.playerStats.put(astring[0], stats);
					Side side = FMLCommonHandler.instance().getEffectiveSide();
					if (side == Side.SERVER)
					{
						DLCCraft.playerTracker.sendDLCs2(stats.player.get(), stats);
					}
					else
					{
						DLCCraft.playerTracker.sendDLCs(stats.player.get(), stats);
					}
					}
					catch(Exception e)
					{
						throw new WrongUsageException(getUsageString(), new Object[0]);
					}
				}
			}
			
		}
		else
		{
			throw new WrongUsageException(getUsageString(), new Object[0]);
		}
	}
	
	public String getUsageString()
	{
		return " DLC commands\n" +
				"/DLC <player> delet <dlc name>       Set the State of an DLC to Unavailible.\n" +
				"/DLC <player> unlock <dlc name>      Set the State of an DLC to Availible.\n" +
				"/DLC <player> buy  <dlc name>        Set the State of an DLC to Bought. \n"+
				"/DLC <player> CoinsAdd  <amount>     Gives player Coins. \n";
	}

}
