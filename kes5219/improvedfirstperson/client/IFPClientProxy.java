package kes5219.improvedfirstperson.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
import thehippomaster.AnimatedPlayer.AnimatedPlayer;

*/
import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPModelPlayerBase;
import kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPRenderPlayerBase;
import kes5219.improvedfirstperson.common.IFPCommonProxy;
import kes5219.improvedfirstperson.hooks.AfterCameraTransformation;
import kes5219.utils.misc.ObjLoader;
import kes5219.utils.misc.PartialTickRetriever;

public class IFPClientProxy extends IFPCommonProxy {
	
	private static Minecraft mc;
	
	public static boolean animatedPlayerInstalled = false;
	
	public void preInit(FMLPreInitializationEvent event)
	{
		RenderPlayerAPI.register("kes5219_improvedfirstperson", IFPRenderPlayerBase.class);
		ModelPlayerAPI.register("kes5219_improvedfirstperson", IFPModelPlayerBase.class);
        
		FMLCommonHandler.instance().bus().register(new IFPKeyHandler());
		
/*		
		try
		{
			if (AnimatedPlayer.instance != null)
			{
				System.out.println("Animated Player mod detected by Improved First Person.");
				animatedPlayerInstalled = true;
			}
		}
		catch (LinkageError e)
		{
			
		}
*/		
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
	
	public void init(FMLInitializationEvent event)
	{
		//AfterCameraTransformation.init();
		MinecraftForgeClient.registerItemRenderer(Items.filled_map, new FirstPersonMapRenderer());
		MinecraftForgeClient.registerItemRenderer(Items.map, new FirstPersonMapRenderer());		
	}
	
	//@ForgeSubscribe
	public void renderHelmetOverlay()
	{
		
	}
	
	public static Minecraft getMC()
	{
		if (mc == null)
		{
			mc = Minecraft.getMinecraft();
		}
		
		return mc;
	}

	public static boolean isGamePaused() {
		return getMC().isGamePaused();
	}
	
}