package Abascus.DLCCraft.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.FakePlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PlayerTracker implements IPlayerTracker
{
	public ConcurrentHashMap<String, PlayerDLCStats> playerStats = new ConcurrentHashMap<String, PlayerDLCStats>();

	@Override
	public void onPlayerLogin (EntityPlayer entityplayer)
	{
		PlayerDLCStats stats = new PlayerDLCStats();
		NBTTagCompound tags = entityplayer.getEntityData();
		if (!tags.hasKey("DLCCraft"))
		{
			tags.setCompoundTag("DLCCraft", new NBTTagCompound());
			NBTTagList tagList = new NBTTagList();
			NBTTagCompound dlc;

			for (int i = 0; i < DLCManager.names.length; ++i)
			{
				if (DLCManager.names[i] != null)
				{
					dlc = new NBTTagCompound();
					dlc.setInteger(DLCManager.names[i], (byte) 0);
					tagList.appendTag(dlc);
				}
			}

			tags.setTag("DLCCraft", tagList);
			stats.init();
		}

		stats.player = new WeakReference<EntityPlayer>(entityplayer);		
		stats.readFromNBT(tags);
		playerStats.put(entityplayer.username, stats);
	}

	public void sendDLCs (EntityPlayer entityplayer, PlayerDLCStats stats)
	{
		System.out.println("send");
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);

		try
		{
			//outputStream.writeByte(1);
			outputStream.writeInt(stats.Coins);
			for (int i = 0; i < DLCManager.names.length; ++i)
			{
				if (DLCManager.names[i] != null)
				{
					//outputStream.writeByte(i);
					outputStream.writeByte(stats.dlcManager.dlcs[i].state);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		updateClientPlayer(bos, entityplayer);
	}
	
	public void sendDLCs2 (EntityPlayer entityplayer, PlayerDLCStats stats)
	{
		System.out.println("send");
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);

		try
		{
			//outputStream.writeByte(1);
			outputStream.writeInt(stats.Coins);
			for (int i = 0; i < DLCManager.names.length; ++i)
			{
				if (DLCManager.names[i] != null)
				{
					//outputStream.writeByte(i);
					outputStream.writeByte(stats.dlcManager.dlcs[i].state);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		updateClientPlayer(bos, entityplayer);
	}

	void updateServer (ByteArrayOutputStream bos, EntityPlayer player)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "DLCCraft";
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
	}
	
	void updateClientPlayer (ByteArrayOutputStream bos, EntityPlayer player)
	{
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "DLCCraft";
		packet.data = bos.toByteArray();
		packet.length = bos.size();

		PacketDispatcher.sendPacketToServer(packet);
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
			if (stats != null)
			{
				stats.saveToNBT(player.getEntityData());
				if (clean)
					playerStats.remove(player.username);
			}
			else
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

		ItemStack diary = new ItemStack(DLCCraft.instance.shop);
		if (!entityplayer.inventory.addItemStackToInventory(diary))
		{
			spawnItemAtPlayer(entityplayer, diary);
		}

		Side side = FMLCommonHandler.instance().getEffectiveSide();
	}

	public static void spawnItemAtPlayer (EntityPlayer player, ItemStack stack)
	{
		EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, stack);
		player.worldObj.spawnEntityInWorld(entityitem);
		if (!(player instanceof FakePlayer))
		{
			entityitem.onCollideWithPlayer(player);
		}
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
