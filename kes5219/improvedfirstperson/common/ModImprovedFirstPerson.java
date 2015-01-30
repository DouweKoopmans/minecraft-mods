package kes5219.improvedfirstperson.common;

import java.util.HashMap;

import kes5219.improvedfirstperson.common.IFPCommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModImprovedFirstPerson.MOD_ID, name = "Improved First Person View Mod", version = "1.7.10_vrackfall")
public class ModImprovedFirstPerson
{
    public static final String MOD_ID = "kes5219_improvedfirstperson";

    public static boolean enableBodyRender = true;

    @SidedProxy(clientSide = "kes5219.improvedfirstperson.client.IFPClientProxy", serverSide = "kes5219.improvedfirstperson.common.IFPCommonProxy")
    public static IFPCommonProxy proxy;

    @Instance(ModImprovedFirstPerson.MOD_ID)
    public static ModImprovedFirstPerson instance;

    public static Configuration config;
    public static HashMap<String, String> configComments = new HashMap()
    {
        {
            put("leanAmount", "The amount the player leans over when looking down. (default = 0.75)");
            put("wibblyWobblyHUD", "With this on, the HUD will act somewhat like the \"3D\" HUDs in modern games.");
        }
    };
    public static float leanAmount = 0.75F;
    public static boolean wibblyWobblyHUD = false;

    private static Property getProperty(String category, String key, Object defaultValue)
    {
        Property property = null;

        if(defaultValue instanceof String)
            property = config.get(category, key, (String)defaultValue);
        else if(defaultValue instanceof Integer)
            property = config.get(category, key, (Integer)defaultValue);
        else if(defaultValue instanceof Double)
            property = config.get(category, key, (Double)defaultValue);
        else if(defaultValue instanceof Boolean)
            property = config.get(category, key, (Boolean)defaultValue);
        else if(defaultValue instanceof String[])
            property = config.get(category, key, (String[])defaultValue);
        else if(defaultValue instanceof int[])
            property = config.get(category, key, (int[])defaultValue);
        else if(defaultValue instanceof boolean[])
            property = config.get(category, key, (boolean[])defaultValue);

        String comment = configComments.get(key);

        if(comment != null)
            property.comment = comment;

        return property;
    }

    private static Property getGenProperty(String key, Object defaultValue)
    {
        return getProperty(Configuration.CATEGORY_GENERAL, key, defaultValue);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        config = new Configuration(event.getSuggestedConfigurationFile());

        leanAmount = (float)getGenProperty("leanAmount", (double)leanAmount).getDouble(leanAmount);
        wibblyWobblyHUD = getGenProperty("wibblyWobblyHUD", wibblyWobblyHUD).getBoolean(wibblyWobblyHUD);

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}