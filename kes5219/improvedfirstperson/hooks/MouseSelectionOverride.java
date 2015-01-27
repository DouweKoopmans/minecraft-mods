/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import atp;
/*  4:   */ import atv;
/*  5:   */ import aul;
/*  6:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  7:   */ import of;
/*  8:   */ 
/*  9:   */ public class MouseSelectionOverride
/* 10:   */ {
/* 11:   */   private static double tempPosX;
/* 12:   */   private static double tempPosY;
/* 13:   */   private static double tempPosZ;
/* 14:   */   private static double tempPrevPosX;
/* 15:   */   private static double tempPrevPosY;
/* 16:   */   private static double tempPrevPosZ;
/* 17:   */   private static boolean shouldRestore;
/* 18:   */   
/* 19:   */   public static void onMethodStart()
/* 20:   */   {
/* 21:35 */     atv mc = IFPClientProxy.getMC();
/* 22:37 */     if ((mc != null) && (mc.i != null) && (mc.u != null) && (mc.u.aa == 0))
/* 23:   */     {
/* 24:39 */       shouldRestore = true;
/* 25:40 */       of viewEntity = mc.i;
/* 26:41 */       tempPosX = viewEntity.u;
/* 27:42 */       tempPosY = viewEntity.v;
/* 28:43 */       tempPosZ = viewEntity.w;
/* 29:44 */       tempPrevPosX = viewEntity.r;
/* 30:45 */       tempPrevPosY = viewEntity.s;
/* 31:46 */       tempPrevPosZ = viewEntity.t;
/* 32:47 */       viewEntity.u += atp.a;
/* 33:48 */       viewEntity.v += atp.b;
/* 34:49 */       viewEntity.w += atp.c;
/* 35:50 */       viewEntity.r = viewEntity.u;
/* 36:51 */       viewEntity.s = viewEntity.v;
/* 37:52 */       viewEntity.t = viewEntity.w;
/* 38:   */     }
/* 39:   */     else
/* 40:   */     {
/* 41:54 */       shouldRestore = false;
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void onMethodEnd()
/* 46:   */   {
/* 47:62 */     if (shouldRestore)
/* 48:   */     {
/* 49:64 */       of viewEntity = IFPClientProxy.getMC().i;
/* 50:65 */       viewEntity.u = tempPosX;
/* 51:66 */       viewEntity.v = tempPosY;
/* 52:67 */       viewEntity.w = tempPosZ;
/* 53:68 */       viewEntity.r = tempPrevPosX;
/* 54:69 */       viewEntity.s = tempPrevPosY;
/* 55:70 */       viewEntity.t = tempPrevPosZ;
/* 56:   */     }
/* 57:   */   }
/* 58:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.MouseSelectionOverride
 * JD-Core Version:    0.7.0.1
 */