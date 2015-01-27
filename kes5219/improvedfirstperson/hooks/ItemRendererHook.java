/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import atv;
/*  4:   */ import aul;
/*  5:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  6:   */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  7:   */ import of;
/*  8:   */ 
/*  9:   */ public class ItemRendererHook
/* 10:   */ {
/* 11:   */   public static boolean shouldRenderItemInFirstPerson()
/* 12:   */   {
/* 13:20 */     atv mc = IFPClientProxy.getMC();
/* 14:21 */     aul settings = mc.u;
/* 15:23 */     if ((ModImprovedFirstPerson.enableBodyRender) && (settings.aa == 0) && (!mc.i.bh()) && (!settings.Z)) {
/* 16:26 */       return false;
/* 17:   */     }
/* 18:31 */     return true;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.ItemRendererHook
 * JD-Core Version:    0.7.0.1
 */