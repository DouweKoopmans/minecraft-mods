/*   1:    */ package kes5219.improvedfirstperson.client;
/*   2:    */ 
/*   3:    */ import atv;
/*   4:    */ import aul;
/*   5:    */ import awf;
/*   6:    */ import bdi;
/*   7:    */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*   8:    */ import ls;
/*   9:    */ import net.minecraftforge.client.GuiIngameForge;
/*  10:    */ import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
/*  11:    */ import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
/*  12:    */ import net.minecraftforge.event.ForgeSubscribe;
/*  13:    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
/*  14:    */ import org.lwjgl.opengl.GL11;
/*  15:    */ 
/*  16:    */ public class ClientEventHandler
/*  17:    */ {
/*  18: 44 */   private double lastOffX = 0.0D;
/*  19: 45 */   private double lastOffY = 0.0D;
/*  20: 46 */   private double lastScale = 1.0D;
/*  21:    */   
/*  22:    */   @ForgeSubscribe
/*  23:    */   public void jumpEvent(LivingEvent.LivingJumpEvent event) {}
/*  24:    */   
/*  25:    */   @ForgeSubscribe
/*  26:    */   public void preRenderHUD(RenderGameOverlayEvent.Post event)
/*  27:    */   {
/*  28: 51 */     if (ModImprovedFirstPerson.wibblyWobblyHUD)
/*  29:    */     {
/*  30: 53 */       atv mc = IFPClientProxy.getMC();
/*  31:    */       
/*  32: 55 */       GuiIngameForge hud = (GuiIngameForge)mc.r;
/*  33: 57 */       if ((GuiIngameForge.renderCrosshairs) && (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS))
/*  34:    */       {
/*  35: 59 */         awf res = event.resolution;
/*  36: 60 */         double width = res.c();
/*  37: 61 */         double height = res.d();
/*  38: 62 */         double width2 = width / 2.0D;
/*  39: 63 */         double height2 = height / 2.0D;
/*  40: 64 */         double scaleFac = res.e();
/*  41:    */         
/*  42: 66 */         GL11.glPushMatrix();
/*  43:    */         
/*  44: 68 */         double offX = this.lastOffX;
/*  45: 69 */         double offY = this.lastOffY;
/*  46:    */         
/*  47: 71 */         double scale = this.lastScale;
/*  48: 73 */         if (!IFPClientProxy.isGamePaused())
/*  49:    */         {
/*  50: 75 */           double dX = mc.h.u - mc.h.r;
/*  51: 76 */           double dY = mc.h.v - mc.h.s;
/*  52: 77 */           double dZ = mc.h.w - mc.h.t;
/*  53:    */           
/*  54: 79 */           float rotYaw = mc.h.aP * -0.01745329F;
/*  55: 80 */           float rotPitch = mc.h.B * -0.01745329F;
/*  56:    */           
/*  57: 82 */           double dYaw = mc.h.A - mc.h.g;
/*  58: 83 */           double dPitch = mc.h.B - mc.h.h;
/*  59: 85 */           if (mc.u.aa == 2)
/*  60:    */           {
/*  61: 87 */             rotYaw += -3.141593F;
/*  62: 88 */             rotPitch *= -1.0F;
/*  63: 89 */             dYaw *= -1.0D;
/*  64:    */           }
/*  65: 92 */           float cosYaw = ls.b(rotYaw);
/*  66: 93 */           float sinYaw = ls.a(rotYaw);
/*  67: 94 */           float cosPitch = ls.b(rotPitch);
/*  68: 95 */           float sinPitch = ls.a(rotPitch);
/*  69:    */           
/*  70: 97 */           float moveDir = (float)Math.atan2(dZ, dX) * 180.0F / 3.141593F - 90.0F;
/*  71:    */           
/*  72: 99 */           double localYawZ = dX * sinYaw + dZ * cosYaw;
/*  73:    */           
/*  74:101 */           double localX = -dX * cosYaw + dZ * sinYaw;
/*  75:102 */           double localY = dY * cosPitch - localYawZ * sinPitch;
/*  76:103 */           double localZ = localYawZ * cosPitch + dY * sinPitch;
/*  77:    */           
/*  78:105 */           offX = localX * -50.0D - dYaw * 0.5D;
/*  79:106 */           offY = localY * 25.0D - dPitch * 0.4D;
/*  80:    */           
/*  81:108 */           scale = 0.9D + localZ * 0.3D;
/*  82:109 */           scale = Math.min(Math.max(scale, 0.8D), 1.0D);
/*  83:    */           
/*  84:111 */           double moveAmt = 3.0D / scaleFac;
/*  85:    */           
/*  86:    */ 
/*  87:114 */           offX /= moveAmt;
/*  88:115 */           offY /= moveAmt;
/*  89:116 */           scale = (scale - 1.0D) / moveAmt + 1.0D;
/*  90:    */           
/*  91:    */ 
/*  92:119 */           offX = this.lastOffX + (offX - this.lastOffX) * 0.3D;
/*  93:120 */           offY = this.lastOffY + (offY - this.lastOffY) * 0.3D;
/*  94:121 */           scale = this.lastScale + (scale - this.lastScale) * 0.3D;
/*  95:    */         }
/*  96:124 */         GL11.glTranslated(width2, height2, 0.0D);
/*  97:125 */         GL11.glScaled(scale, scale, 1.0D);
/*  98:126 */         GL11.glTranslated(-width2, -height2, 0.0D);
/*  99:    */         
/* 100:128 */         GL11.glTranslated(offX, offY, 0.0D);
/* 101:    */         
/* 102:130 */         this.lastOffX = offX;
/* 103:131 */         this.lastOffY = offY;
/* 104:132 */         this.lastScale = scale;
/* 105:    */       }
/* 106:134 */       else if (event.type == RenderGameOverlayEvent.ElementType.ALL)
/* 107:    */       {
/* 108:136 */         GL11.glPopMatrix();
/* 109:    */       }
/* 110:    */     }
/* 111:    */   }
/* 112:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.ClientEventHandler
 * JD-Core Version:    0.7.0.1
 */