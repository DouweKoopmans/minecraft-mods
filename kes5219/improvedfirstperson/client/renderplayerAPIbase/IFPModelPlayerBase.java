/*   1:    */ package kes5219.improvedfirstperson.client.renderplayerAPIbase;
/*   2:    */ 
/*   3:    */ import api.player.model.ModelPlayer;
/*   4:    */ import api.player.model.ModelPlayerAPI;
/*   5:    */ import api.player.model.ModelPlayerBase;
/*   6:    */ import atv;
/*   7:    */ import aul;
/*   8:    */ import bcu;
/*   9:    */ import bev;
/*  10:    */ import bex;
/*  11:    */ import bgl;
/*  12:    */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  13:    */ import kes5219.utils.misc.PartialTickRetriever;
/*  14:    */ import ls;
/*  15:    */ import nn;
/*  16:    */ import of;
/*  17:    */ import uf;
/*  18:    */ import wp;
/*  19:    */ import wv;
/*  20:    */ import xn;
/*  21:    */ import yc;
/*  22:    */ import ye;
/*  23:    */ import yh;
/*  24:    */ import zj;
/*  25:    */ 
/*  26:    */ public class IFPModelPlayerBase
/*  27:    */   extends ModelPlayerBase
/*  28:    */ {
/*  29: 28 */   private float prevStrafeOffset = 0.0F;
/*  30:    */   
/*  31:    */   public IFPModelPlayerBase(ModelPlayerAPI modelPlayerAPI)
/*  32:    */   {
/*  33: 31 */     super(modelPlayerAPI);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void beforeSetRotationAngles(float legSwing, float legYaw, float ticksExistedPartial, float headYawOffset, float pitch, float scale, nn entity)
/*  37:    */   {
/*  38: 37 */     uf player = (uf)entity;
/*  39:    */     
/*  40:    */ 
/*  41:    */ 
/*  42:    */ 
/*  43: 42 */     ye item = player.aZ();
/*  44: 44 */     if ((item != null) && ((yc.g[item.d] instanceof wp)))
/*  45:    */     {
/*  46: 45 */       this.modelPlayer.l = 1;
/*  47: 46 */       this.modelPlayer.m = 0;
/*  48:    */     }
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void afterSetRotationAngles(float legSwing, float legYaw, float ticksExistedPartial, float headYawOffset, float pitch, float scale, nn entity)
/*  52:    */   {
/*  53: 52 */     atv mc = atv.w();
/*  54: 53 */     uf player = (uf)entity;
/*  55: 54 */     float partialTick = PartialTickRetriever.getPartialTick();
/*  56:    */     
/*  57: 56 */     boolean clientPlayer = entity == mc.i;
/*  58: 57 */     boolean invPlayer = bgl.a.j == 180.0F;
/*  59: 59 */     if (invPlayer)
/*  60:    */     {
/*  61: 61 */       this.modelPlayer.c.k = false;
/*  62: 62 */       this.modelPlayer.d.k = false;
/*  63: 63 */       return;
/*  64:    */     }
/*  65: 66 */     if (((mc.u.aa == 0) && (clientPlayer)) || (mc.i.bh()))
/*  66:    */     {
/*  67: 67 */       this.modelPlayer.c.k = true;
/*  68: 68 */       this.modelPlayer.d.k = true;
/*  69:    */     }
/*  70:    */     else
/*  71:    */     {
/*  72: 70 */       this.modelPlayer.c.k = false;
/*  73: 71 */       this.modelPlayer.d.k = false;
/*  74:    */     }
/*  75: 74 */     ye item = player.aZ();
/*  76: 76 */     if ((item != null) && ((yc.bf.cv == item.d) || (yc.bQ.cv == item.d)))
/*  77:    */     {
/*  78: 77 */       this.modelPlayer.f.f = -0.3490659F;
/*  79: 78 */       this.modelPlayer.g.f = -0.3490659F;
/*  80: 79 */       this.modelPlayer.f.h = 0.0F;
/*  81: 80 */       this.modelPlayer.g.h = 0.0F;
/*  82:    */       
/*  83: 82 */       this.modelPlayer.f.h += ls.b(ticksExistedPartial * 0.09F) * 0.05F + 0.05F;
/*  84: 83 */       this.modelPlayer.g.h -= ls.b(ticksExistedPartial * 0.09F) * 0.05F + 0.05F;
/*  85: 84 */       this.modelPlayer.f.f += ls.a(ticksExistedPartial * 0.067F) * 0.05F;
/*  86: 85 */       this.modelPlayer.g.f -= ls.a(ticksExistedPartial * 0.067F) * 0.05F;
/*  87:    */     }
/*  88: 88 */     if (this.modelPlayer.o)
/*  89:    */     {
/*  90: 89 */       float rotationYaw = player.aQ + (player.aP - player.aQ) * partialTick;
/*  91:    */       
/*  92: 91 */       this.modelPlayer.g.g += 0.15F;
/*  93:    */       
/*  94: 93 */       ticksExistedPartial *= 6.0F;
/*  95:    */       
/*  96: 95 */       this.modelPlayer.g.h -= 0.15F * (ls.b(ticksExistedPartial * 0.09F) * 0.05F + 0.05F);
/*  97:    */       
/*  98: 97 */       this.modelPlayer.g.f -= 0.15F * (ls.a(ticksExistedPartial * 0.067F) * 0.05F);
/*  99:    */       
/* 100: 99 */       float headAngle = this.modelPlayer.c.f * 15.0F;
/* 101:100 */       float rot = headAngle / 45.0F;
/* 102:    */       
/* 103:102 */       rot *= rot * 2.5F;
/* 104:104 */       if (headAngle > 0.0F)
/* 105:    */       {
/* 106:106 */         this.modelPlayer.f.f -= rot * 2.0F;
/* 107:107 */         this.modelPlayer.f.g -= rot;
/* 108:109 */         if ((clientPlayer) && (mc.u.aa == 0) && (ModImprovedFirstPerson.enableBodyRender)) {
/* 109:110 */           this.modelPlayer.f.c -= rot * 3.0F;
/* 110:    */         }
/* 111:112 */         this.modelPlayer.g.g += rot;
/* 112:    */         
/* 113:114 */         rot -= 0.5F;
/* 114:115 */         rot *= 0.75F;
/* 115:117 */         if (rot > 0.0F) {
/* 116:118 */           this.modelPlayer.g.f -= rot;
/* 117:    */         }
/* 118:    */       }
/* 119:    */       else
/* 120:    */       {
/* 121:122 */         this.modelPlayer.g.h -= rot;
/* 122:123 */         this.modelPlayer.g.f += rot;
/* 123:124 */         this.modelPlayer.f.f += rot * 2.75F;
/* 124:    */       }
/* 125:127 */       if ((mc.u.aa == 0) && ((player instanceof bex)))
/* 126:    */       {
/* 127:129 */         bex playerSP = (bex)player;
/* 128:130 */         float strafe = playerSP.c.a;
/* 129:    */         
/* 130:132 */         float div = Math.max(2.0F, ls.c(Math.abs(this.modelPlayer.c.f * 40.0F)));
/* 131:133 */         float targetOffset = strafe / div;
/* 132:134 */         this.prevStrafeOffset += (targetOffset - this.prevStrafeOffset) * 0.025F;
/* 133:    */         
/* 134:136 */         this.modelPlayer.g.h += this.prevStrafeOffset;
/* 135:    */       }
/* 136:    */     }
/* 137:140 */     if ((item == null) || ((yc.g[item.d] instanceof wp))) {
/* 138:141 */       this.modelPlayer.l = 0;
/* 139:    */     }
/* 140:145 */     if ((entity.B > 0.0F) && (!this.modelPlayer.q) && ((ModImprovedFirstPerson.enableBodyRender) || (!clientPlayer) || (invPlayer)))
/* 141:    */     {
/* 142:149 */       float mult = ModImprovedFirstPerson.leanAmount;
/* 143:    */       
/* 144:151 */       ye heldItem = player.aZ();
/* 145:152 */       boolean map = (heldItem != null) && ((heldItem.b() instanceof wv));
/* 146:154 */       if (!map)
/* 147:    */       {
/* 148:156 */         float off = Math.abs(entity.B / 250.0F * mult);
/* 149:158 */         if (player.br())
/* 150:    */         {
/* 151:160 */           float div = (player.bs() + partialTick) / 4.0F + 1.0F;
/* 152:162 */           if (div < 1.0F) {
/* 153:163 */             div = 1.0F;
/* 154:    */           }
/* 155:165 */           off /= div;
/* 156:    */         }
/* 157:168 */         this.modelPlayer.g.h -= off;
/* 158:169 */         this.modelPlayer.f.h += off;
/* 159:    */       }
/* 160:172 */       if (!this.modelPlayer.n)
/* 161:    */       {
/* 162:174 */         float off = entity.B / 180.0F * mult;
/* 163:175 */         this.modelPlayer.e.f += off;
/* 164:    */         
/* 165:177 */         off = entity.B / 16.0F * mult;
/* 166:178 */         this.modelPlayer.h.e += off;
/* 167:179 */         this.modelPlayer.i.e += off;
/* 168:    */         
/* 169:181 */         off = Math.abs(entity.B / 50.0F * mult);
/* 170:182 */         this.modelPlayer.h.d -= off;
/* 171:183 */         this.modelPlayer.i.d -= off;
/* 172:    */       }
/* 173:    */     }
/* 174:188 */     if (player.br())
/* 175:    */     {
/* 176:190 */       ye heldItemStack = player.aZ();
/* 177:192 */       if (heldItemStack != null)
/* 178:    */       {
/* 179:194 */         yc heldItem = heldItemStack.b();
/* 180:195 */         zj action = heldItem.c_(heldItemStack);
/* 181:197 */         if ((action == zj.b) || (action == zj.c))
/* 182:    */         {
/* 183:199 */           float useLeftPartial = player.bq() + 1.0F - partialTick;
/* 184:200 */           float progressLeft = useLeftPartial / heldItemStack.n();
/* 185:201 */           float moveAmount = progressLeft * progressLeft * progressLeft;
/* 186:202 */           moveAmount = moveAmount * moveAmount * moveAmount;
/* 187:203 */           moveAmount = moveAmount * moveAmount * moveAmount;
/* 188:204 */           moveAmount = 1.0F - moveAmount;
/* 189:    */           
/* 190:206 */           float preAngleX = this.modelPlayer.f.f;
/* 191:207 */           float preAngleY = this.modelPlayer.f.g;
/* 192:208 */           float preAngleZ = this.modelPlayer.f.h;
/* 193:    */           
/* 194:210 */           this.modelPlayer.f.f -= moveAmount * 1.25F;
/* 195:211 */           this.modelPlayer.f.g -= moveAmount / 2.0F;
/* 196:212 */           this.modelPlayer.f.h += moveAmount / 2.0F;
/* 197:    */           
/* 198:214 */           float bodyYaw = player.aO + (player.aN - player.aO) * partialTick;
/* 199:215 */           float headYaw = player.aQ + (player.aP - player.aQ) * partialTick;
/* 200:216 */           float offset = ls.g(headYaw - bodyYaw) / 80.0F * moveAmount;
/* 201:    */           
/* 202:218 */           this.modelPlayer.f.f += offset / 2.5F;
/* 203:219 */           this.modelPlayer.f.g += offset + 0.2F;
/* 204:    */           
/* 205:221 */           this.modelPlayer.f.e += moveAmount;
/* 206:222 */           this.modelPlayer.f.e += (offset - 0.2F) * 2.0F;
/* 207:    */           
/* 208:224 */           float headPitch = player.D + (player.B - player.D) * partialTick;
/* 209:225 */           offset = headPitch / 180.0F;
/* 210:    */           
/* 211:227 */           this.modelPlayer.f.g -= offset;
/* 212:229 */           if (offset > 0.0F) {
/* 213:230 */             offset /= 1.5F;
/* 214:    */           }
/* 215:232 */           this.modelPlayer.f.f += offset;
/* 216:    */           
/* 217:234 */           this.modelPlayer.f.f += ls.e(ls.b(useLeftPartial / 4.0F * 3.141593F) * 0.125F) * (progressLeft > 0.2F ? -1 : 0);
/* 218:    */           
/* 219:    */ 
/* 220:237 */           float diffAngleX = this.modelPlayer.f.f - preAngleX;
/* 221:238 */           float diffAngleY = this.modelPlayer.f.g - preAngleY;
/* 222:239 */           float diffAngleZ = this.modelPlayer.f.h - preAngleZ;
/* 223:    */           
/* 224:241 */           float mult = Math.min(progressLeft / 0.2F, 1.0F);
/* 225:242 */           mult *= mult * mult;
/* 226:    */           
/* 227:244 */           this.modelPlayer.f.f = (preAngleX + diffAngleX * mult);
/* 228:245 */           this.modelPlayer.f.g = (preAngleY + diffAngleY * mult);
/* 229:246 */           this.modelPlayer.f.h = (preAngleZ + diffAngleZ * mult);
/* 230:    */         }
/* 231:    */       }
/* 232:    */     }
/* 233:    */   }
/* 234:    */   
/* 235:    */   ModelPlayer getModelPlayer()
/* 236:    */   {
/* 237:258 */     return this.modelPlayer;
/* 238:    */   }
/* 239:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.renderplayerAPIbase.IFPModelPlayerBase
 * JD-Core Version:    0.7.0.1
 */