package Abascus.DLCCraft.common;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
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
	
	public void process(int i1, String[] astring)
	{
		try
		{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(astring[0]);
		int i = stats.dlcManager.getID(astring[2]);
		stats.dlcManager.dlcs[i].state = i1;
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
					process(0, astring);
					
				}
				else if("unlock".startsWith(astring[1].toLowerCase()))
				{
					process(1, astring);
				}
				else if("buy".startsWith(astring[1].toLowerCase()))
				{
					process(2, astring);
				}
				else if("coinsadd".startsWith(astring[1].toLowerCase()))
				{
					try
					{
					PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(astring[0]);
					stats.dlcManager.Coins+= Integer.parseInt(astring[2]);
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
			else if(astring.length == 2)
			{
				if("delet".startsWith(astring[0].toLowerCase()))
				{
					String[] s = new String[3];
					s[0] = ((EntityPlayer)icommandsender).username;
					s[1] = "delet";					
					s[2] = astring[1];
					processCommand(icommandsender, s);
					
				}
				else if("unlock".startsWith(astring[0].toLowerCase()))
				{
					String[] s = new String[3];
					s[0] = ((EntityPlayer)icommandsender).username;
					s[1] = "unlock";					
					s[2] = astring[1];
					processCommand(icommandsender, s);
				}
				else if("buy".startsWith(astring[0].toLowerCase()))
				{
					String[] s = new String[3];
					s[0] = ((EntityPlayer)icommandsender).username;
					s[1] = "buy";					
					s[2] = astring[1];
					processCommand(icommandsender, s);
				}
				else if("coinsadd".startsWith(astring[0].toLowerCase()))
				{
					String[] s = new String[3];
					s[0] = ((EntityPlayer)icommandsender).username;
					s[1] = "coinsadd";					
					s[2] = astring[1];
					processCommand(icommandsender, s);

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
				"/DLC <player> coinsadd  <amount>     Gives player Coins. \n";
	}

}
