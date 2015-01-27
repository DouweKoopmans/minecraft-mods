/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import abw;
/*  4:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  5:   */ import ls;
/*  6:   */ import of;
/*  7:   */ import uf;
/*  8:   */ import wp;
/*  9:   */ import ye;
/* 10:   */ 
/* 11:   */ public class EntityLivingBaseHook
/* 12:   */ {
/* 13:   */   public static void handleRotation(of entity, float startingOffset, float plannedOffset)
/* 14:   */   {
/* 15:14 */     if ((entity instanceof uf))
/* 16:   */     {
/* 17:16 */       uf player = (uf)entity;
/* 18:17 */       boolean isRemote = player.q.I;
/* 19:19 */       if (!isRemote) {
/* 20:20 */         return;
/* 21:   */       }
/* 22:22 */       float target = startingOffset;
/* 23:23 */       float limitMult = ls.c(1.0F - Math.max(entity.B, 0.0F) / 100.0F);
/* 24:25 */       if (player.aE > 0.0F)
/* 25:   */       {
/* 26:27 */         target = entity.aP;
/* 27:   */       }
/* 28:   */       else
/* 29:   */       {
/* 30:31 */         double dX = entity.u - entity.r;
/* 31:32 */         double dZ = entity.w - entity.t;
/* 32:33 */         float dist = (float)(dX * dX + dZ * dZ);
/* 33:35 */         if (dist > 0.0005F)
/* 34:   */         {
/* 35:37 */           float moveDir = (float)(Math.atan2(dZ, dX) * 180.0D / 3.141592653589793D) - 90.0F;
/* 36:   */           
/* 37:39 */           target = moveDir;
/* 38:41 */           if (Math.abs(ls.g(moveDir - entity.aP)) > 95.0F) {
/* 39:43 */             target += 180.0F;
/* 40:   */           }
/* 41:46 */           limitMult *= dist / 0.0025F;
/* 42:   */         }
/* 43:   */       }
/* 44:50 */       limitMult = ls.a(limitMult, 0.0F, 1.0F);
/* 45:   */       
/* 46:52 */       float offset = ls.g(target - entity.aP);
/* 47:   */       
/* 48:54 */       float limit = 75.0F * limitMult;
/* 49:   */       
/* 50:56 */       float pastLimit = Math.max(0.0F, Math.abs(offset) - limit);
/* 51:   */       
/* 52:58 */       target = entity.aP + ls.a(offset, -limit, limit);
/* 53:   */       
/* 54:60 */       float moveAmount = target - startingOffset;
/* 55:61 */       float smoothed = moveAmount * 0.3F;
/* 56:   */       
/* 57:63 */       target = startingOffset + smoothed;
/* 58:   */       
/* 59:65 */       entity.aO = startingOffset;
/* 60:66 */       entity.aN = target;
/* 61:   */       
/* 62:68 */       ye stack = entity.aZ();
/* 63:70 */       if ((stack != null) && ((stack.b() instanceof wp)) && (((uf)entity).br())) {
/* 64:72 */         if (IFPClientProxy.animatedPlayerInstalled) {
/* 65:74 */           entity.aN = (entity.A - 40.0F);
/* 66:   */         } else {
/* 67:78 */           entity.aN = (entity.A + 40.0F);
/* 68:   */         }
/* 69:   */       }
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.EntityLivingBaseHook
 * JD-Core Version:    0.7.0.1
 */