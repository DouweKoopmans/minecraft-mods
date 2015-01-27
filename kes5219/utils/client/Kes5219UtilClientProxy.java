/*  1:   */ package kes5219.utils.client;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  4:   */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  5:   */ import cpw.mods.fml.common.registry.TickRegistry;
/*  6:   */ import cpw.mods.fml.relauncher.Side;
/*  7:   */ import kes5219.utils.common.Kes5219UtilCommonProxy;
/*  8:   */ import kes5219.utils.misc.PartialTickRetriever;
/*  9:   */ 
/* 10:   */ public class Kes5219UtilClientProxy
/* 11:   */   extends Kes5219UtilCommonProxy
/* 12:   */ {
/* 13:   */   public void preInit(FMLPreInitializationEvent event) {}
/* 14:   */   
/* 15:   */   public void init(FMLInitializationEvent event)
/* 16:   */   {
/* 17:17 */     TickRegistry.registerTickHandler(new PartialTickRetriever(), Side.CLIENT);
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.client.Kes5219UtilClientProxy
 * JD-Core Version:    0.7.0.1
 */