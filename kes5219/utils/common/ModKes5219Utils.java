/*  1:   */ package kes5219.utils.common;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.Mod;
/*  4:   */ import cpw.mods.fml.common.Mod.EventHandler;
/*  5:   */ import cpw.mods.fml.common.Mod.Instance;
/*  6:   */ import cpw.mods.fml.common.SidedProxy;
/*  7:   */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  8:   */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*  9:   */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/* 11:   */ 
/* 12:   */ @Mod(modid="kes5219_Utility", name="Kes5219 Utility Mods", version="R2_MC1.7.10_vrackfall")

/* 14:   */ public class ModKes5219Utils
/* 15:   */ {
/* 16:   */   @SidedProxy(clientSide="kes5219.utils.client.Kes5219UtilClientProxy", serverSide="kes5219.utils.common.Kes5219UtilCommonProxy")
/* 17:   */   public static Kes5219UtilCommonProxy proxy;
/* 18:   */   @Mod.Instance("kes5219_Utility")
/* 19:   */   public static ModKes5219Utils instance;
/* 20:   */   
/* 21:   */   @Mod.EventHandler
/* 22:   */   public void preInit(FMLPreInitializationEvent event)
/* 23:   */   {
/* 24:27 */     proxy.preInit(event);
/* 25:   */   }
/* 26:   */   
/* 27:   */   @Mod.EventHandler
/* 28:   */   public void init(FMLInitializationEvent event)
/* 29:   */   {
/* 30:32 */     proxy.init(event);
/* 31:   */   }
/* 32:   */   
/* 33:   */   @Mod.EventHandler
/* 34:   */   public static void postInit(FMLPostInitializationEvent event)
/* 35:   */   {
/* 36:37 */     proxy.postInit(event);
/* 37:   */   }
/* 38:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.common.ModKes5219Utils
 * JD-Core Version:    0.7.0.1
 */