/*   1:    */ package kes5219.improvedfirstperson.client.renderplayerAPIbase;
/*   2:    */ 
/*   3:    */ import abw;
/*   4:    */ import api.player.render.RenderPlayerAPI;
/*   5:    */ import api.player.render.RenderPlayerBase;
/*   6:    */ import asx;
/*   7:    */ import asz;
/*   8:    */ import atd;
/*   9:    */ import att;
/*  10:    */ import atv;
/*  11:    */ import aul;
/*  12:    */ import bbj;
/*  13:    */ import bbo;
/*  14:    */ import bcp;
/*  15:    */ import bcu;
/*  16:    */ import beu;
/*  17:    */ import bgl;
/*  18:    */ import bhj;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import java.util.HashMap;
/*  21:    */ import java.util.List;
/*  22:    */ import java.util.Random;
/*  23:    */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  24:    */ import ls;
/*  25:    */ import of;
/*  26:    */ import org.lwjgl.opengl.GL11;
/*  27:    */ import uh;
/*  28:    */ import wp;
/*  29:    */ import xv;
/*  30:    */ import yc;
/*  31:    */ import ye;
/*  32:    */ import zj;
/*  33:    */ 
/*  34:    */ public class IFPRenderPlayerBase
/*  35:    */   extends RenderPlayerBase
/*  36:    */ {
/*  37:    */   public IFPRenderPlayerBase(RenderPlayerAPI renderPlayerAPI)
/*  38:    */   {
/*  39: 41 */     super(renderPlayerAPI);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void afterRenderModel(of entity, float limbSwing, float limbYaw, float existedTicksPartial, float headYawOffset, float pitch, float scale) {}
/*  43:    */   
/*  44:    */   public void renderSpecialHeadArmor(beu player, float partialTick)
/*  45:    */   {
/*  46: 56 */     atv mc = IFPClientProxy.getMC();
/*  47: 60 */     if ((player != mc.i) || (mc.u.aa > 0) || (bgl.a.j == 180.0F)) {
/*  48: 63 */       this.renderPlayer.localRenderSpecialHeadArmor(player, partialTick);
/*  49:    */     }
/*  50:    */   }
/*  51:    */   
/*  52:    */   class ArrowPosition
/*  53:    */   {
/*  54:    */     bcu modelRenderer;
/*  55:    */     float randX;
/*  56:    */     float randY;
/*  57:    */     float randZ;
/*  58:    */     float arrowX;
/*  59:    */     float arrowY;
/*  60:    */     float arrowZ;
/*  61:    */     
/*  62:    */     public ArrowPosition(bcu modelRenderer, float randX, float randY, float randZ, float arrowX, float arrowY, float arrowZ)
/*  63:    */     {
/*  64: 80 */       this.modelRenderer = modelRenderer;
/*  65: 81 */       this.randX = randX;
/*  66: 82 */       this.randY = randY;
/*  67: 83 */       this.randZ = randZ;
/*  68: 84 */       this.arrowX = arrowX;
/*  69: 85 */       this.arrowY = arrowY;
/*  70: 86 */       this.arrowZ = arrowZ;
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74: 89 */   HashMap<Integer, ArrayList<ArrowPosition>> arrowCache = new HashMap();
/*  75:    */   private static final int swingRotation = -140;
/*  76:    */   private static final int swingRotationWindup = 35;
/*  77:    */   private static final float swingCancel = 0.7F;
/*  78:    */   
/*  79:    */   public void renderArrowsStuckInEntity(of entity, float partialTick)
/*  80:    */   {
/*  81: 94 */     atv mc = IFPClientProxy.getMC();
/*  82: 96 */     if (entity == mc.h)
/*  83:    */     {
/*  84: 98 */       int arrowCount = entity.aU();
/*  85:100 */       if (arrowCount > 0)
/*  86:    */       {
/*  87:102 */         uh arrow = new uh(entity.q, entity.u, entity.v, entity.w);
/*  88:103 */         Random random = null;
/*  89:    */         
/*  90:105 */         ArrayList<ArrowPosition> arrowList = (ArrayList)this.arrowCache.get(Integer.valueOf(entity.k));
/*  91:107 */         if ((arrowList == null) || (arrowList.size() != arrowCount)) {
/*  92:108 */           arrowList = new ArrayList();
/*  93:    */         }
/*  94:110 */         for (int arrowIndex = 0; arrowIndex < arrowCount; arrowIndex++)
/*  95:    */         {
/*  96:112 */           GL11.glPushMatrix();
/*  97:    */           
/*  98:114 */           bcu modelRenderer = null;
/*  99:115 */           bcp box = null;
/* 100:    */           
/* 101:    */ 
/* 102:118 */           float randX = 0.0F;
/* 103:119 */           float randY = 0.0F;
/* 104:120 */           float randZ = 0.0F;
/* 105:121 */           float arrowX = 0.0F;
/* 106:122 */           float arrowY = 0.0F;
/* 107:123 */           float arrowZ = 0.0F;
/* 108:125 */           if (arrowList.size() == arrowCount)
/* 109:    */           {
/* 110:127 */             ArrowPosition arrowPos = (ArrowPosition)arrowList.get(arrowIndex);
/* 111:129 */             if (arrowPos != null)
/* 112:    */             {
/* 113:131 */               modelRenderer = arrowPos.modelRenderer;
/* 114:132 */               randX = arrowPos.randX;
/* 115:133 */               randY = arrowPos.randY;
/* 116:134 */               randZ = arrowPos.randZ;
/* 117:135 */               arrowX = arrowPos.arrowX;
/* 118:    */             }
/* 119:    */           }
/* 120:139 */           if (modelRenderer == null)
/* 121:    */           {
/* 122:141 */             asx headBB = asx.a().a(-0.6000000238418579D, -0.800000011920929D, -0.6000000238418579D, 0.6000000238418579D, 0.300000011920929D, 0.6000000238418579D);
/* 123:143 */             if (random == null) {
/* 124:144 */               random = new Random(entity.k);
/* 125:    */             }
/* 126:146 */             int tries = 0;
/* 127:148 */             while ((tries < 10) && ((modelRenderer == null) || ((entity == mc.i) && (headBB.a(entity.q.V().a(arrowX, arrowY, arrowZ))))))
/* 128:    */             {
/* 129:152 */               modelRenderer = this.renderPlayer.getMainModelField().a(random);
/* 130:153 */               box = (bcp)modelRenderer.l.get(random.nextInt(modelRenderer.l.size()));
/* 131:    */               
/* 132:155 */               randX = random.nextFloat();
/* 133:156 */               randY = random.nextFloat();
/* 134:157 */               randZ = random.nextFloat();
/* 135:    */               
/* 136:159 */               arrowX = (box.a + (box.d - box.a) * randX) / 16.0F;
/* 137:160 */               arrowY = (box.b + (box.e - box.b) * randY) / 16.0F;
/* 138:161 */               arrowZ = (box.c + (box.f - box.c) * randZ) / 16.0F;
/* 139:    */               
/* 140:163 */               tries++;
/* 141:    */             }
/* 142:166 */             arrowList.add(new ArrowPosition(modelRenderer, randX, randY, randZ, arrowX, arrowY, arrowZ));
/* 143:    */           }
/* 144:171 */           modelRenderer.c(0.0625F);
/* 145:    */           
/* 146:173 */           GL11.glTranslatef(arrowX, arrowY, arrowZ);
/* 147:    */           
/* 148:    */ 
/* 149:176 */           randX = randX * 2.0F - 1.0F;
/* 150:177 */           randY = randY * 2.0F - 1.0F;
/* 151:178 */           randZ = randZ * 2.0F - 1.0F;
/* 152:179 */           randX *= -1.0F;
/* 153:180 */           randY *= -1.0F;
/* 154:181 */           randZ *= -1.0F;
/* 155:182 */           float dist = ls.c(randX * randX + randZ * randZ);
/* 156:183 */           arrow.C = (arrow.A = (float)(Math.atan2(randX, randZ) * 180.0D / 3.141592653589793D));
/* 157:184 */           arrow.D = (arrow.B = (float)(Math.atan2(randY, dist) * 180.0D / 3.141592653589793D));
/* 158:    */           
/* 159:    */ 
/* 160:187 */           this.renderPlayer.getRenderManagerField().a(arrow, 0.0D, 0.0D, 0.0D, 0.0F, partialTick);
/* 161:    */           
/* 162:189 */           GL11.glPopMatrix();
/* 163:    */         }
/* 164:192 */         this.arrowCache.put(Integer.valueOf(entity.k), arrowList);
/* 165:    */       }
/* 166:    */     }
/* 167:    */     else
/* 168:    */     {
/* 169:197 */       this.renderPlayer.localRenderArrowsStuckInEntity(entity, partialTick);
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void afterPositionSpecialItemInHand(beu player, float partialTick, zj useAction, ye heldStack)
/* 174:    */   {
/* 175:207 */     atv mc = IFPClientProxy.getMC();
/* 176:209 */     if ((player == mc.h) && (mc.u.aa == 0)) {
/* 177:210 */       att.b();
/* 178:    */     }
/* 179:213 */     if ((yc.g[heldStack.d] instanceof wp))
/* 180:    */     {
/* 181:214 */       GL11.glPopMatrix();
/* 182:215 */       GL11.glPushMatrix();
/* 183:216 */       this.renderPlayer.getModelBipedMainField().g.c(0.0625F);
/* 184:217 */       GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
/* 185:    */       
/* 186:219 */       GL11.glScalef(0.625F, -0.625F, 0.625F);
/* 187:220 */       GL11.glTranslatef(0.1F, 0.0F, 0.3F);
/* 188:    */       
/* 189:222 */       float rot = 15.0F * this.renderPlayer.getModelBipedMainField().c.f;
/* 190:    */       
/* 191:224 */       float correctionsRot = rot;
/* 192:226 */       if (correctionsRot > 0.0F)
/* 193:    */       {
/* 194:228 */         correctionsRot *= 2.0F;
/* 195:    */         
/* 196:230 */         float max = 12.5F;
/* 197:232 */         if (correctionsRot > max) {
/* 198:234 */           correctionsRot = max - (correctionsRot - max);
/* 199:    */         }
/* 200:237 */         correctionsRot = ls.c(correctionsRot);
/* 201:239 */         if (correctionsRot > 0.0F) {
/* 202:240 */           GL11.glRotatef(correctionsRot, 0.0F, 0.0F, 1.0F);
/* 203:    */         }
/* 204:    */       }
/* 205:    */       else
/* 206:    */       {
/* 207:244 */         correctionsRot *= -1.6F;
/* 208:    */         
/* 209:246 */         float yOff = -0.3F;
/* 210:247 */         float zOff = -0.5F;
/* 211:    */         
/* 212:249 */         GL11.glTranslatef(0.0F, yOff, zOff);
/* 213:250 */         GL11.glRotatef(correctionsRot, 1.0F, 0.0F, 0.0F);
/* 214:251 */         GL11.glRotatef(correctionsRot * 0.8F, 0.0F, 0.0F, 1.0F);
/* 215:252 */         GL11.glRotatef(correctionsRot * 1.5F, 0.0F, 1.0F, 0.0F);
/* 216:253 */         GL11.glTranslatef(0.0F, -yOff, -zOff);
/* 217:    */       }
/* 218:256 */       GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
/* 219:    */       
/* 220:258 */       rot = Math.abs(rot);
/* 221:260 */       if (rot > 0.0F) {
/* 222:262 */         GL11.glRotatef(10.0F + rot, 0.0F, 1.0F, 0.0F);
/* 223:    */       }
/* 224:    */     }
/* 225:265 */     else if ((player.au) && (player.aE > 0.0F))
/* 226:    */     {
/* 227:267 */       yc heldItem = player.aZ().b();
/* 228:269 */       if (heldItem.n_())
/* 229:    */       {
/* 230:271 */         boolean rotatedAround = heldItem.o_();
/* 231:    */         
/* 232:273 */         float actualSwing = player.k(partialTick);
/* 233:274 */         float rot = actualSwing * -140.0F + 35.0F;
/* 234:    */         
/* 235:276 */         float cancel = actualSwing - 0.7F;
/* 236:278 */         if (cancel > 0.0F)
/* 237:    */         {
/* 238:280 */           cancel = cancel / 0.3F * -140.0F;
/* 239:281 */           rot -= cancel;
/* 240:    */         }
/* 241:284 */         if (rotatedAround)
/* 242:    */         {
/* 243:286 */           GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
/* 244:287 */           GL11.glTranslatef(0.0F, 0.125F, 0.0F);
/* 245:289 */           if (player.bM == null) {
/* 246:290 */             rot *= 0.75F;
/* 247:291 */           } else if ((heldItem instanceof xv)) {
/* 248:292 */             rot = 0.0F;
/* 249:    */           }
/* 250:    */         }
/* 251:295 */         GL11.glRotatef(rot, 1.0F, 0.0F, 0.6F);
/* 252:297 */         if (rotatedAround)
/* 253:    */         {
/* 254:299 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 255:300 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/* 256:    */         }
/* 257:    */       }
/* 258:    */     }
/* 259:    */   }
/* 260:    */   
/* 261:    */   public void positionSpecialItemInHand(beu player, float partialTick, zj action, ye stack)
/* 262:    */   {
/* 263:309 */     if (player.bM != null) {
/* 264:310 */       stack.d = yc.aT.cv;
/* 265:    */     }
/* 266:312 */     this.renderPlayer.localPositionSpecialItemInHand(player, partialTick, action, stack);
/* 267:    */   }
/* 268:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPRenderPlayerBase
 * JD-Core Version:    0.7.0.1
 */