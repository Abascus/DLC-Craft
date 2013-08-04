package Abascus.DLCCraft.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler
{

    @Override
    public void onPacketData (INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        Side side = FMLCommonHandler.instance().getEffectiveSide();

        System.out.println("Packet");
        if (packet.channel.equals("DLCCraft"))
        {
            if (side == Side.SERVER)
                handleServerPacket(packet, (EntityPlayerMP) player);
            else
                handleClientPacket(packet, (EntityPlayer) player);
        }
        
        System.out.println("Packet");
    }

    void handleClientPacket (Packet250CustomPayload packet, EntityPlayer player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        byte packetID;

        try
        {
                PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(player.username);
                stats.dlcManager.dlcs = new DLC[DLCManager.names.length];
                stats.dlcManager.Coins = inputStream.readInt();
                for (int i = 0; i < DLCManager.names.length; ++i)
    			{
    				if (DLCManager.names[i] != null)
    				{
                    DLC dlc = new DLC(i, DLCManager.names[i], DLCManager.Names[i], DLCManager.description[i], DLCManager.dep[i], DLCManager.cost[i]);
                    dlc.setState(inputStream.readByte());
                    stats.dlcManager.dlcs[i] = dlc;
    				}
                }
                DLCCraft.playerTracker.playerStats.put(player.username, stats);
        }
        catch (Exception e)
        {
            System.out.println("Failed at reading client packet for DLCCraft.");
            e.printStackTrace();
            return;
        }
    }

    void handleServerPacket (Packet250CustomPayload packet, EntityPlayerMP player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        byte packetID;

        try
        {
                PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(player.username);
                stats.dlcManager.dlcs = new DLC[DLCManager.names.length];
                stats.dlcManager.Coins = inputStream.readInt();
                for (int i = 0; i < DLCManager.names.length; ++i)
    			{
    				if (DLCManager.names[i] != null)
    				{
                    DLC dlc = new DLC(i, DLCManager.names[i], DLCManager.Names[i], DLCManager.description[i], DLCManager.dep[i], DLCManager.cost[i]);
                    dlc.setState(inputStream.readByte());
                    stats.dlcManager.dlcs[i] = dlc;
    				}
                }
                DLCCraft.playerTracker.playerStats.put(player.username, stats);
        }
        catch (Exception e)
        {
            System.out.println("Failed at reading server packet for DLCCraft.");
            e.printStackTrace();
            return;
        }
    }

    Entity getEntity (World world, int id)
    {
        for (Object o : world.loadedEntityList)
        {
            if (((Entity) o).entityId == id)
                return (Entity) o;
        }
        return null;
    }
}
