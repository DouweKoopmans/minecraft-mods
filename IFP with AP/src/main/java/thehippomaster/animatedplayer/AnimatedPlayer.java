package thehippomaster.animatedplayer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid="animatedplayer", name="Animated Player Mod", version="1.7.10_1.6.0_vrackfall")
public class AnimatedPlayer
{
  @Mod.Instance("animatedplayer")
  public static AnimatedPlayer instance;
  @SidedProxy(clientSide="thehippomaster.animatedplayer.client.ClientProxy", serverSide="thehippomaster.animatedplayer.CommonProxy")
  public static CommonProxy proxy;
  public static APHandler apHandler;
  public static long clientTick;
  public static boolean replaceDefaultPlayerTexture;
  public static boolean animateFace;
  public static boolean betterHat;
  
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent e)
  {
    Configuration cfg = new Configuration(e.getSuggestedConfigurationFile());
    cfg.load();
    replaceDefaultPlayerTexture = cfg.get("general", "ReplaceDefaultPlayerTexture", true).getBoolean(true);
    animateFace = cfg.get("general", "AnimateFace", true).getBoolean(true);
    betterHat = cfg.get("general", "BetterHat", true).getBoolean(true);
    cfg.save();
    
    apHandler = new APHandler();
    FMLCommonHandler.instance().bus().register(apHandler);
  }
  
  @Mod.EventHandler
  public void init(FMLInitializationEvent e)
  {
    clientTick = 0L;
    proxy.registerRenderers();
  }
  
  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent e)
  {
    proxy.registerRenderers();
  }
  
  public static final String[] fTimer = { "field_71428_T", "S", "timer" };
  public static final String[] fTextureOffsetX = { "field_78803_o", "r", "textureOffsetX" };
  public static final String[] fTextureOffsetY = { "field_78813_p", "s", "textureOffsetY" };
  public static final String[] fPlayerImgBuffer = { "field_110560_d", "d", "bufferedImage" };
}
