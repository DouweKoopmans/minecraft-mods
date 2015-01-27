/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import ModLoader;
/*  4:   */ import asx;
/*  5:   */ import ata;
/*  6:   */ import atc;
/*  7:   */ import atu;
/*  8:   */ import atv;
/*  9:   */ import aul;
/* 10:   */ import avj;
/* 11:   */ import awf;
/* 12:   */ import bdc;
/* 13:   */ import bdi;
/* 14:   */ import java.nio.FloatBuffer;
/* 15:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/* 16:   */ import kes5219.utils.misc.PartialTickRetriever;
/* 17:   */ import nn;
/* 18:   */ import of;
/* 19:   */ 
/* 20:   */ public class CrosshairRenderHook
/* 21:   */ {
/* 22:   */   private static float relCrosshairPos;
/* 23:23 */   private static FloatBuffer win_pos = atu.h(16);
/* 24:   */   
/* 25:   */   public static void calculateCrosshairPos()
/* 26:   */   {
/* 27:26 */     atv mc = IFPClientProxy.getMC();
/* 28:28 */     if (mc.t != null)
/* 29:   */     {
/* 30:29 */       float partialTick = PartialTickRetriever.getPartialTick();
/* 31:   */       float zDiff;
/* 32:   */       float zDiff;
/* 33:33 */       if (mc.t.g != null)
/* 34:   */       {
/* 35:34 */         double collisionBoxSize = mc.t.g.Z();
/* 36:35 */         asx var16 = mc.t.g.E.b(collisionBoxSize, collisionBoxSize, collisionBoxSize);
/* 37:36 */         atc var6 = mc.i.l(partialTick);
/* 38:37 */         double var2 = mc.c.d();
/* 39:38 */         if (mc.c.i()) {
/* 40:39 */           var2 = 6.0D;
/* 41:   */         }
/* 42:41 */         atc var7 = mc.i.j(partialTick);
/* 43:42 */         atc var8 = var6.c(var7.c * var2, var7.d * var2, var7.e * var2);
/* 44:43 */         ata var17 = var16.a(var6, var8);
/* 45:44 */         if (var17 == null) {
/* 46:46 */           return;
/* 47:   */         }
/* 48:48 */         float xDiff = (float)(var17.f.c - (mc.h.r + (mc.h.u - mc.h.r) * partialTick));
/* 49:49 */         float yDiff = (float)(var17.f.d - (mc.h.s + (mc.h.v - mc.h.s) * partialTick));
/* 50:50 */         zDiff = (float)(var17.f.e - (mc.h.t + (mc.h.w - mc.h.t) * partialTick));
/* 51:   */       }
/* 52:   */       else
/* 53:   */       {
/* 54:52 */         float xDiff = (float)(mc.t.f.c - (mc.h.r + (mc.h.u - mc.h.r) * partialTick));
/* 55:53 */         float yDiff = (float)(mc.t.f.d - (mc.h.s + (mc.h.v - mc.h.s) * partialTick));
/* 56:54 */         zDiff = (float)(mc.t.f.e - (mc.h.t + (mc.h.w - mc.h.t) * partialTick));
/* 57:   */       }
/* 58:57 */       relCrosshairPos = win_pos.get(1);
/* 59:   */     }
/* 60:   */   }
/* 61:   */   
/* 62:   */   public static boolean shouldCustomRenderCrosshair(avj gui)
/* 63:   */   {
/* 64:62 */     if (ModLoader.getMinecraftInstance().u.aa == 0)
/* 65:   */     {
/* 66:63 */       renderCustomCrosshair(gui);
/* 67:64 */       return true;
/* 68:   */     }
/* 69:66 */     return false;
/* 70:   */   }
/* 71:   */   
/* 72:   */   private static void renderCustomCrosshair(avj gui)
/* 73:   */   {
/* 74:71 */     atv mc = IFPClientProxy.getMC();
/* 75:72 */     awf var5 = new awf(mc.u, mc.d, mc.e);
/* 76:73 */     int var6 = var5.a();
/* 77:74 */     gui.b(var6 / 2 - 7, 113 - ((int)(relCrosshairPos / var5.e()) - 120), 0, 0, 16, 16);
/* 78:   */   }
/* 79:   */   
/* 80:   */   public static boolean test()
/* 81:   */   {
/* 82:78 */     return false;
/* 83:   */   }
/* 84:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.CrosshairRenderHook
 * JD-Core Version:    0.7.0.1
 */