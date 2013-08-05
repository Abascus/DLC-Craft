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

public class DLCCommands extends CommandBase {

	@Override
	public String getCommandName() 
	{
		return "DLC";
	}
	
	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "/" + this.getCommandName() + "           type /DLC for full list.";
    }
	
	@Override
    public List getCommandAliases()
    {
		return Arrays.asList(new String[] {"DLC"});
    }

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) 
	{
		
	}
	
	public String getUsageString()
	{
		return " DLC commands\n" +
				"/DLC <player> delet <dlc name>     Set the State of an DLC to Unavailible.\n" +
				"/DLC <player> unlock <dlc name>    Set the State of an DLC to Availible.\n" +
				"/DLC <player> buy  <dlc name>      Set the State of an DLC to Bought.";
	}

}
