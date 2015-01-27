/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import atv;
/*  4:   */ import aul;
/*  5:   */ import bdi;
/*  6:   */ import bgl;
/*  7:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  8:   */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  9:   */ import kes5219.utils.misc.PartialTickRetriever;
/* 10:   */ import net.minecraftforge.client.MinecraftForgeClient;
/* 11:   */ import of;
/* 12:   */ 
/* 13:   */ public class RenderEntityHook
/* 14:   */ {
/* 15:   */   public static void onRenderEntities()
/* 16:   */   {
/* 17:22 */     atv mc = IFPClientProxy.getMC();
/* 18:24 */     if ((ModImprovedFirstPerson.enableBodyRender) && (mc.u.aa == 0) && (!mc.h.bh()) && (mc.i.shouldRenderInPass(MinecraftForgeClient.getRenderPass()))) {
/* 19:28 */       bgl.a.a(mc.i, PartialTickRetriever.getPartialTick());
/* 20:   */     }
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.RenderEntityHook
 * JD-Core Version:    0.7.0.1
 */