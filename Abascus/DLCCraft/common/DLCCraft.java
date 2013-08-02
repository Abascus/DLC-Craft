package Abascus.DLCCraft.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "Abascus_DLCCraft", name = "DLC Craft", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DLCCraft 
{
	@SidedProxy(clientSide = "Abascus.DLCCraft.common.Client.ClientProxy", serverSide = "Abascus.DLCCraft.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance("Abascus_DLCCraft")
	public static DLCCraft instance;

	public boolean startUpInfo = true;

	public String[] Msg;
	public String[] Capes;

	public DLCCraft()
	{

	}

	@EventHandler
	public void pre(FMLPreInitializationEvent event)
	{

	}

	@EventHandler
	public void load(FMLInitializationEvent event) 
	{

	}

	public static String[] grab(String location)
	{
		try
		{
			HttpURLConnection conn = null;
			while (location != null && !location.isEmpty())
			{
				URL url = new URL(location);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
				conn.connect();
				location = conn.getHeaderField("Location");
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			ArrayList<String> changelog = new ArrayList<String>();
			while ((line = reader.readLine()) != null)
			{
				if (line.startsWith("#"))
				{
					continue;
				}
				if (line.isEmpty())
				{
					continue;
				}

				changelog.add(line);
			}

			return changelog.toArray(new String[0]);

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return new String[]{"Unable to get Mod Information"};
	}

}
