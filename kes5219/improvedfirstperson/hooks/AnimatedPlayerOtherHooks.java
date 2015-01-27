/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import net.minecraft.util.MathHelper;
/*  4:   */ import thehippomaster.AnimatedPlayer.PlayerData;
/*  5:   */ 
/*  6:   */ public class AnimatedPlayerOtherHooks
/*  7:   */ {
/*  8:   */   public static int getJumpTickIncrementDown(PlayerData data)
/*  9:   */   {
/* 10:18 */     if (data.jumpTick > 4) {
/* 11:20 */       return 4;
/* 12:   */     }
/* 13:24 */     switch (data.jumpTick)
/* 14:   */     {
/* 15:   */     case 4: 
/* 16:27 */       return 2;
/* 17:   */     case 2: 
/* 18:29 */       return 1;
/* 19:   */     case 1: 
/* 20:31 */       return 0;
/* 21:   */     }
/* 22:35 */     return MathHelper.func_76125_a(data.jumpTick - 1, 0, 4);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.AnimatedPlayerOtherHooks
 * JD-Core Version:    0.7.0.1
 */