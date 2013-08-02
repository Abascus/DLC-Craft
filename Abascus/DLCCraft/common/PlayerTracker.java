package Abascus.DLCCraft.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PlayerTracker implements IPlayerTracker
{
	/* Player */
	//public int hunger;
	public ConcurrentHashMap<String, PlayerDLCStats> playerStats = new ConcurrentHashMap<String, PlayerDLCStats>();

	@Override
	public void onPlayerLogin (EntityPlayer entityplayer)
	{
		//System.out.println("Player: "+entityplayer);
		//Lookup player
		NBTTagCompound tags = entityplayer.getEntityData();
		if (!tags.hasKey("DLCCraft"))
		{
			tags.setCompoundTag("DLCCraft", new NBTTagCompound());
		}
		PlayerDLCStats stats = new PlayerDLCStats();
		stats.player = new WeakReference<EntityPlayer>(entityplayer);		
		stats.readFromNBT(tags);
		playerStats.put(entityplayer.username, stats);
	}

	void updatePlayerInventory (EntityPlayer entityplayer, PlayerDLCStats stats)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try
		{
			outputStream.writeByte(2);
			updateClientPlayer(bos, entityplayer);
		}

		catch (Exception ex)
		{

		}
	}

	void sendDLCs (EntityPlayer entityplayer, PlayerDLCStats stats)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);

		try
		{
			outputStream.writeByte(1);
			outputStream.writeInt(DLCManager.names.length);
			for (int i = 0; i < DLCManager.names.length; ++i)
			{
				if (DLCManager.names[i] != null)
				{
					outputStream.writeChars(DLCManager.names[i]);
					outputStream.writeByte(stats.states.get(DLCManager.names[i]).state);

				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}


		updateClientPlayer(bos, entityplayer);
	}

	void updateClientPlayer (ByteArrayOutputStream bos, EntityPlayer player)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "DLCCraft";
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
	}

	@Override
	public void onPlayerLogout (EntityPlayer entityplayer)
	{
		savePlayerStats(entityplayer, true);
	}

	@Override
	public void onPlayerChangedDimension (EntityPlayer entityplayer)
	{
		savePlayerStats(entityplayer, false);
	}

	void savePlayerStats (EntityPlayer player, boolean clean)
	{
		if (player != null)
		{
			PlayerDLCStats stats = getPlayerDLCStats(player.username);
			if (stats != null && stats.states != null)
			{
				stats.saveToNBT(player.getEntityData());
				if (clean)
					playerStats.remove(player.username);
			}
			else
				//Revalidate all players
			{

			}
		}
	}

	@Override
	public void onPlayerRespawn (EntityPlayer entityplayer)
	{
		//Boom!
		PlayerDLCStats stats = getPlayerDLCStats(entityplayer.username);
		stats.player = new WeakReference<EntityPlayer>(entityplayer);

		NBTTagCompound tags = entityplayer.getEntityData();
		NBTTagCompound tTag = new NBTTagCompound();
		tags.setCompoundTag("DLCraft", tTag);

		Side side = FMLCommonHandler.instance().getEffectiveSide();
	}


	/* Find the right player */
	public PlayerDLCStats getPlayerDLCStats (String username)
	{
		PlayerDLCStats stats = playerStats.get(username);
		//System.out.println("Stats: "+stats);
		if (stats == null)
		{
			stats = new PlayerDLCStats();
			playerStats.put(username, stats);
		}
		return stats;
	}

	public EntityPlayer getEntityPlayer (String username)
	{
		PlayerDLCStats stats = playerStats.get(username);
		if (stats == null)
		{
			return null;
		}
		else
		{
			return stats.player.get();
		}
	}

	/* Modify Player */
	public void updateSize (String user, float offset)
	{
		/*EntityPlayer player = getEntityPlayer(user);
        setEntitySize(0.6F, offset, player);
        player.yOffset = offset - 0.18f;*/
	}

	public static void setEntitySize (float width, float height, Entity entity)
	{
		//System.out.println("Size: " + height);
		if (width != entity.width || height != entity.height)
		{
			entity.width = width;
			entity.height = height;
			entity.boundingBox.maxX = entity.boundingBox.minX + (double) entity.width;
			entity.boundingBox.maxZ = entity.boundingBox.minZ + (double) entity.width;
			entity.boundingBox.maxY = entity.boundingBox.minY + (double) entity.height;
		}

		float que = width % 2.0F;

		if ((double) que < 0.375D)
		{
			entity.myEntitySize = EnumEntitySize.SIZE_1;
		}
		else if ((double) que < 0.75D)
		{
			entity.myEntitySize = EnumEntitySize.SIZE_2;
		}
		else if ((double) que < 1.0D)
		{
			entity.myEntitySize = EnumEntitySize.SIZE_3;
		}
		else if ((double) que < 1.375D)
		{
			entity.myEntitySize = EnumEntitySize.SIZE_4;
		}
		else if ((double) que < 1.75D)
		{
			entity.myEntitySize = EnumEntitySize.SIZE_5;
		}
		else
		{
			entity.myEntitySize = EnumEntitySize.SIZE_6;
		}
		//entity.yOffset = height;
	}

	Random rand = new Random();

}
