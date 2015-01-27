/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import atv;
/*  4:   */ import aul;
/*  5:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  6:   */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  7:   */ 
/*  8:   */ public class RenderFishHook
/*  9:   */ {
/* 10:   */   private static int thirdPersonViewTemp;
/* 11:   */   
/* 12:   */   public static void onMethodStart()
/* 13:   */   {
/* 14:18 */     if (ModImprovedFirstPerson.enableBodyRender)
/* 15:   */     {
/* 16:20 */       atv mc = IFPClientProxy.getMC();
/* 17:21 */       thirdPersonViewTemp = mc.u.aa;
/* 18:22 */       mc.u.aa = 1;
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static void onMethodEnd()
/* 23:   */   {
/* 24:29 */     if (ModImprovedFirstPerson.enableBodyRender) {
/* 25:31 */       IFPClientProxy.getMC().u.aa = thirdPersonViewTemp;
/* 26:   */     }
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.RenderFishHook
 * JD-Core Version:    0.7.0.1
 */