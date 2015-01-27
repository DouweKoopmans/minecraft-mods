/*   1:    */ package kes5219.improvedfirstperson.hooks;
/*   2:    */ 
/*   3:    */ import atp;
/*   4:    */ import atu;
/*   5:    */ import atv;
/*   6:    */ import aul;
/*   7:    */ import bcu;
/*   8:    */ import bdi;
/*   9:    */ import bfe;
/*  10:    */ import bgl;
/*  11:    */ import bgm;
/*  12:    */ import java.nio.FloatBuffer;
/*  13:    */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  14:    */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  15:    */ import kes5219.utils.misc.PartialTickRetriever;
/*  16:    */ import ls;
/*  17:    */ import ni;
/*  18:    */ import of;
/*  19:    */ import org.lwjgl.opengl.GL11;
/*  20:    */ import uf;
/*  21:    */ 
/*  22:    */ public class AfterCameraTransformation
/*  23:    */ {
/*  24:    */   public static final float EYE_OFFSET = 0.14F;
/*  25:    */   public static final float EYE_OFFSET_HEIGHT = 0.11F;
/*  26:    */   public static final double BODY_HEIGHT = 1.5D;
/*  27: 41 */   private static FloatBuffer win_pos = atu.h(16);
/*  28:    */   public static float crosshairYPos;
/*  29:    */   
/*  30:    */   private static float interpolateRotation(float rot1, float rot2, float partial)
/*  31:    */   {
/*  32: 46 */     rot2 -= rot1;
/*  33: 48 */     while (rot2 < -180.0F) {
/*  34: 50 */       rot2 += 360.0F;
/*  35:    */     }
/*  36: 53 */     while (rot2 >= 180.0F) {
/*  37: 55 */       rot2 -= 360.0F;
/*  38:    */     }
/*  39: 58 */     return rot1 + partial * rot2;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public static void doModelRenderTransforms(bcu modelPart, boolean rotate)
/*  43:    */   {
/*  44: 63 */     GL11.glTranslatef(modelPart.o, modelPart.p, modelPart.q);
/*  45:    */     
/*  46: 65 */     GL11.glTranslatef(modelPart.c * 0.0625F, modelPart.d * 0.0625F, modelPart.e * 0.0625F);
/*  47: 69 */     if (rotate)
/*  48:    */     {
/*  49: 71 */       GL11.glRotatef(-modelPart.h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*  50: 72 */       GL11.glRotatef(-modelPart.g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*  51: 73 */       GL11.glRotatef(-modelPart.f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static void undoRotation(bcu modelPart)
/*  56:    */   {
/*  57: 79 */     GL11.glRotatef(modelPart.f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*  58: 80 */     GL11.glRotatef(modelPart.g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*  59: 81 */     GL11.glRotatef(modelPart.h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static void afterCameraTransform()
/*  63:    */   {
/*  64: 92 */     if (ModImprovedFirstPerson.enableBodyRender)
/*  65:    */     {
/*  66: 94 */       atv mc = IFPClientProxy.getMC();
/*  67: 95 */       uf player = (uf)mc.i;
/*  68: 96 */       float partialTick = PartialTickRetriever.getPartialTick();
/*  69: 98 */       if ((!player.bh()) && (mc.u.aa == 0))
/*  70:    */       {
/*  71:100 */         GL11.glLoadIdentity();
/*  72:101 */         displayOverlayEffects(partialTick);
/*  73:103 */         if (mc.u.g)
/*  74:    */         {
/*  75:105 */           GL11.glTranslatef(-(bfe.b * 2 - 1) * 0.07F, 0.0F, 0.0F);
/*  76:106 */           GL11.glTranslatef((bfe.b * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*  77:    */         }
/*  78:109 */         GL11.glTranslatef(0.0F, -0.11F, 0.0F);
/*  79:    */         
/*  80:111 */         GL11.glRotatef(player.D + (player.B - player.D) * partialTick, 1.0F, 0.0F, 0.0F);
/*  81:    */         
/*  82:113 */         bgm render = bgl.a.a(player.getClass());
/*  83:114 */         float limbSwing = player.aH - player.aG * (1.0F - partialTick);
/*  84:115 */         float limbSwingAmount = player.aF + (player.aG - player.aF) * partialTick;
/*  85:116 */         float existedPartial = player.ac + partialTick;
/*  86:117 */         float bodyYaw = interpolateRotation(player.aO, player.aN, partialTick);
/*  87:118 */         float headYaw = interpolateRotation(player.aQ, player.aP, partialTick);
/*  88:119 */         float headOffset = headYaw - bodyYaw;
/*  89:120 */         float pitch = player.D + (player.B - player.D) * partialTick;
/*  90:122 */         if (IFPClientProxy.animatedPlayerInstalled) {
/*  91:124 */           AnimatedPlayerCamera.doAnimatedPlayerTransforms(player, render, limbSwing, limbSwingAmount, existedPartial, headOffset, pitch, partialTick);
/*  92:    */         }
/*  93:137 */         GL11.glTranslatef(0.0F, 0.0625F, 0.14F);
/*  94:    */         
/*  95:139 */         GL11.glRotatef(player.C + (player.A - player.C) * partialTick + 180.0F, 0.0F, 1.0F, 0.0F);
/*  96:    */         
/*  97:    */ 
/*  98:    */ 
/*  99:    */ 
/* 100:    */ 
/* 101:    */ 
/* 102:    */ 
/* 103:    */ 
/* 104:    */ 
/* 105:    */ 
/* 106:    */ 
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:    */ 
/* 117:    */ 
/* 118:    */ 
/* 119:    */ 
/* 120:    */ 
/* 121:    */ 
/* 122:    */ 
/* 123:    */ 
/* 124:    */ 
/* 125:    */ 
/* 126:    */ 
/* 127:171 */         atp.a(mc.h, mc.u.aa == 2);
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   private static void determineCrosshairPosition(float partialTick) {}
/* 133:    */   
/* 134:    */   public static void setupViewBobbing(float par1)
/* 135:    */   {
/* 136:184 */     atv mc = IFPClientProxy.getMC();
/* 137:186 */     if ((mc.i instanceof uf))
/* 138:    */     {
/* 139:188 */       uf var2 = (uf)mc.i;
/* 140:189 */       float var3 = var2.R - var2.Q;
/* 141:190 */       float var4 = -(var2.R + var3 * par1);
/* 142:191 */       float var5 = var2.bs + (var2.bt - var2.bs) * par1;
/* 143:192 */       float var6 = var2.aJ + (var2.aK - var2.aJ) * par1;
/* 144:193 */       GL11.glTranslatef(ls.a(var4 * 3.141593F) * var5 * 0.5F, -Math.abs(ls.b(var4 * 3.141593F) * var5), 0.0F);
/* 145:194 */       GL11.glRotatef(ls.a(var4 * 3.141593F) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
/* 146:195 */       GL11.glRotatef(Math.abs(ls.b(var4 * 3.141593F - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
/* 147:    */     }
/* 148:    */   }
/* 149:    */   
/* 150:    */   public static void hurtCameraEffect(float par1)
/* 151:    */   {
/* 152:202 */     atv mc = IFPClientProxy.getMC();
/* 153:203 */     of var2 = mc.i;
/* 154:204 */     float var3 = var2.ay - par1;
/* 155:207 */     if (var2.aN() <= 0.0F)
/* 156:    */     {
/* 157:209 */       float var4 = var2.aB + par1;
/* 158:210 */       GL11.glRotatef(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
/* 159:    */     }
/* 160:213 */     if (var3 >= 0.0F)
/* 161:    */     {
/* 162:215 */       var3 /= var2.az;
/* 163:216 */       var3 = ls.a(var3 * var3 * var3 * var3 * 3.141593F);
/* 164:217 */       float var4 = var2.aA;
/* 165:218 */       GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
/* 166:219 */       GL11.glRotatef(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
/* 167:220 */       GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
/* 168:    */     }
/* 169:    */   }
/* 170:    */   
/* 171:    */   private static void displayOverlayEffects(float partialTick)
/* 172:    */   {
/* 173:226 */     atv mc = IFPClientProxy.getMC();
/* 174:227 */     hurtCameraEffect(partialTick);
/* 175:229 */     if (mc.u.f) {
/* 176:231 */       setupViewBobbing(partialTick);
/* 177:    */     }
/* 178:234 */     float timeInPortal = mc.h.bO + (mc.h.bN - mc.h.bO) * partialTick;
/* 179:    */     
/* 180:    */ 
/* 181:    */ 
/* 182:238 */     int rendererUpdateCount = mc.p.s;
/* 183:240 */     if (timeInPortal > 0.0F)
/* 184:    */     {
/* 185:242 */       byte rotAmount = 20;
/* 186:244 */       if (mc.h.a(ni.k)) {
/* 187:246 */         rotAmount = 7;
/* 188:    */       }
/* 189:249 */       float wobble = 5.0F / (timeInPortal * timeInPortal + 5.0F) - timeInPortal * 0.04F;
/* 190:250 */       wobble *= wobble;
/* 191:251 */       float rot = (rendererUpdateCount + partialTick) * rotAmount;
/* 192:252 */       GL11.glRotatef(rot, 0.0F, 1.0F, 1.0F);
/* 193:253 */       GL11.glScalef(1.0F / wobble, 1.0F, 1.0F);
/* 194:254 */       GL11.glRotatef(-rot, 0.0F, 1.0F, 1.0F);
/* 195:    */     }
/* 196:    */   }
/* 197:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.AfterCameraTransformation
 * JD-Core Version:    0.7.0.1
 */