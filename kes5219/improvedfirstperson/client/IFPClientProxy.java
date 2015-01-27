/*  1:   */ package kes5219.improvedfirstperson.client;
/*  2:   */ 
/*  3:   */ import api.player.model.ModelPlayerAPI;
/*  4:   */ import api.player.render.RenderPlayerAPI;
/*  5:   */ import atv;
/*  6:   */ import bcw;
/*  7:   */ import bdi;
/*  8:   */ import cn;
/*  9:   */ import cpw.mods.fml.client.registry.KeyBindingRegistry;
/* 10:   */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/* 11:   */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/* 12:   */ import java.io.PrintStream;
/* 13:   */ import kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPModelPlayerBase;
/* 14:   */ import kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPRenderPlayerBase;
/* 15:   */ import kes5219.improvedfirstperson.common.IFPCommonProxy;
/* 16:   */ import net.minecraftforge.client.MinecraftForgeClient;
/* 17:   */ import net.minecraftforge.common.MinecraftForge;
/* 18:   */ import net.minecraftforge.event.EventBus;
/* 19:   */ import thehippomaster.AnimatedPlayer.AnimatedPlayer;
/* 20:   */ import yc;
/* 21:   */ import yh;
/* 22:   */ 
/* 23:   */ public class IFPClientProxy
/* 24:   */   extends IFPCommonProxy
/* 25:   */ {
/* 26:   */   private static final int EMPTYMAPITEMID = 395;
/* 27:   */   private static atv mc;
/* 28:   */   private static cn memConnection;
/* 29:39 */   public static boolean animatedPlayerInstalled = false;
/* 30:   */   
/* 31:   */   public void preInit(FMLPreInitializationEvent event)
/* 32:   */   {
/* 33:43 */     RenderPlayerAPI.register("kes5219_improvedfirstperson", IFPRenderPlayerBase.class);
/* 34:44 */     ModelPlayerAPI.register("kes5219_improvedfirstperson", IFPModelPlayerBase.class);
/* 35:   */     
/* 36:46 */     KeyBindingRegistry.registerKeyBinding(new IFPKeyHandler());
/* 37:   */     try
/* 38:   */     {
/* 39:50 */       if (AnimatedPlayer.instance != null)
/* 40:   */       {
/* 41:52 */         System.out.println("Animated Player mod detected by Improved First Person.");
/* 42:53 */         animatedPlayerInstalled = true;
/* 43:   */       }
/* 44:   */     }
/* 45:   */     catch (LinkageError e) {}
/* 46:61 */     MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void init(FMLInitializationEvent event)
/* 50:   */   {
/* 51:67 */     MinecraftForgeClient.registerItemRenderer(yc.bf.cv, new FirstPersonMapRenderer());
/* 52:68 */     MinecraftForgeClient.registerItemRenderer(395, new FirstPersonMapRenderer());
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void renderHelmetOverlay() {}
/* 56:   */   
/* 57:   */   public static atv getMC()
/* 58:   */   {
/* 59:79 */     if (mc == null) {
/* 60:81 */       mc = atv.w();
/* 61:   */     }
/* 62:84 */     return mc;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static boolean isGamePaused()
/* 66:   */   {
/* 67:88 */     if (memConnection == null) {
/* 68:89 */       memConnection = (cn)mc.h.a.g();
/* 69:   */     }
/* 70:91 */     return memConnection.h();
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.IFPClientProxy
 * JD-Core Version:    0.7.0.1
 */