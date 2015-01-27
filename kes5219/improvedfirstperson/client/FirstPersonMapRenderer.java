/*  1:   */ package kes5219.improvedfirstperson.client;
/*  2:   */ 
/*  3:   */ import ali;
/*  4:   */ import atv;
/*  5:   */ import avv;
/*  6:   */ import bfe;
/*  7:   */ import bfj;
/*  8:   */ import bfq;
/*  9:   */ import bim;
/* 10:   */ import bjo;
/* 11:   */ import net.minecraftforge.client.IItemRenderer;
/* 12:   */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/* 13:   */ import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
/* 14:   */ import org.lwjgl.opengl.GL11;
/* 15:   */ import yc;
/* 16:   */ import ye;
/* 17:   */ import yh;
/* 18:   */ 
/* 19:   */ public class FirstPersonMapRenderer
/* 20:   */   implements IItemRenderer
/* 21:   */ {
/* 22:15 */   public static bjo mapBackLoc = new bjo("textures/map/map_background.png");
/* 23:   */   
/* 24:   */   public boolean handleRenderType(ye item, IItemRenderer.ItemRenderType type)
/* 25:   */   {
/* 26:22 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
/* 27:24 */       return true;
/* 28:   */     }
/* 29:27 */     return false;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ye item, IItemRenderer.ItemRendererHelper helper)
/* 33:   */   {
/* 34:34 */     return false;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void renderItem(IItemRenderer.ItemRenderType type, ye item, Object... data)
/* 38:   */   {
/* 39:39 */     atv mc = atv.w();
/* 40:40 */     float f14 = 0.8F;
/* 41:41 */     GL11.glScalef(-f14, -f14, f14);
/* 42:42 */     GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
/* 43:43 */     GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
/* 44:44 */     GL11.glTranslatef(-2.4F, -2.0F, 0.0F);
/* 45:45 */     float f20 = 0.015625F;
/* 46:46 */     GL11.glScalef(f20, f20, f20);
/* 47:47 */     mc.N.a(mapBackLoc);
/* 48:48 */     bfq tessellator = bfq.a;
/* 49:49 */     GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 50:50 */     tessellator.b();
/* 51:51 */     byte byte0 = 7;
/* 52:52 */     tessellator.a(0 - byte0, 128 + byte0, 0.0D, 0.0D, 1.0D);
/* 53:53 */     tessellator.a(128 + byte0, 128 + byte0, 0.0D, 1.0D, 1.0D);
/* 54:54 */     tessellator.a(128 + byte0, 0 - byte0, 0.0D, 1.0D, 0.0D);
/* 55:55 */     tessellator.a(0 - byte0, 0 - byte0, 0.0D, 0.0D, 0.0D);
/* 56:56 */     tessellator.a();
/* 57:58 */     if (item.d == yc.bf.cv)
/* 58:   */     {
/* 59:59 */       ali mapdata = yc.bf.a(item, mc.f);
/* 60:60 */       if (mapdata != null) {
/* 61:61 */         mc.p.c.a.a(mc.h, mc.N, mapdata);
/* 62:   */       }
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.FirstPersonMapRenderer
 * JD-Core Version:    0.7.0.1
 */