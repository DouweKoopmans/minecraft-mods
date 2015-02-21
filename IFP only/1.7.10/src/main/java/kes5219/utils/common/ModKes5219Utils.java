package kes5219.utils.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "kes5219_Utility", name = "Kes5219 Utility Mods", version = "R2_MC1.7.10_vrackfall")
public class ModKes5219Utils
{
    @SidedProxy(clientSide = "kes5219.utils.client.Kes5219UtilClientProxy", serverSide = "kes5219.utils.common.Kes5219UtilCommonProxy")
    public static Kes5219UtilCommonProxy proxy;
    @Mod.Instance("kes5219_Utility")
    public static ModKes5219Utils instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
