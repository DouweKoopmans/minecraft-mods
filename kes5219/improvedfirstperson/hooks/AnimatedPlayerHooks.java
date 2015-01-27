/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import kes5219.improvedfirstperson.client.IFPClientProxy;
/*  4:   */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  5:   */ import net.minecraft.client.Minecraft;
/*  6:   */ import net.minecraft.client.renderer.entity.RenderManager;
/*  7:   */ import net.minecraft.client.settings.GameSettings;
/*  8:   */ import net.minecraft.entity.player.EntityPlayer;
/*  9:   */ import thehippomaster.AnimatedPlayer.client.ModelPlayer;
/* 10:   */ 
/* 11:   */ public class AnimatedPlayerHooks
/* 12:   */ {
/* 13:   */   public static void beforeAnimatedPlayerRender(EntityPlayer player, ModelPlayer model)
/* 14:   */   {
/* 15:14 */     Minecraft mc = IFPClientProxy.getMC();
/* 16:   */     
/* 17:16 */     model.head.field_78807_k = ((ModImprovedFirstPerson.enableBodyRender) && (mc.field_71474_y.field_74320_O == 0) && (player == mc.field_71451_h) && (RenderManager.field_78727_a.field_78735_i != 180.0F));
/* 18:   */   }
/* 19:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.AnimatedPlayerHooks
 * JD-Core Version:    0.7.0.1
 */