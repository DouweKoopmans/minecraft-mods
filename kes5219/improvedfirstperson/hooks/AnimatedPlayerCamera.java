/*  1:   */ package kes5219.improvedfirstperson.hooks;
/*  2:   */ 
/*  3:   */ import bgm;
/*  4:   */ import thehippomaster.AnimatedPlayer.PlayerData;
/*  5:   */ import thehippomaster.AnimatedPlayer.client.ModelPlayer;
/*  6:   */ import thehippomaster.AnimatedPlayer.client.RenderPlayer;
/*  7:   */ import uf;
/*  8:   */ 
/*  9:   */ public class AnimatedPlayerCamera
/* 10:   */ {
/* 11:   */   public static void doAnimatedPlayerTransforms(uf player, bgm render, float limbSwing, float limbSwingAmount, float existedPartial, float headOffset, float pitch, float partialTick)
/* 12:   */   {
/* 13:12 */     RenderPlayer apRender = (RenderPlayer)render;
/* 14:13 */     ModelPlayer model = apRender.playerModel;
/* 15:14 */     PlayerData data = PlayerData.getPlayerData(player);
/* 16:   */     
/* 17:16 */     model.partialTick = partialTick;
/* 18:17 */     model.setAngles(data);
/* 19:18 */     model.animate(player, data, limbSwing, limbSwingAmount, existedPartial, headOffset, pitch, 0.0625F);
/* 20:   */     
/* 21:20 */     AfterCameraTransformation.doModelRenderTransforms(model.pelvis, true);
/* 22:21 */     AfterCameraTransformation.doModelRenderTransforms(model.chest, true);
/* 23:22 */     AfterCameraTransformation.doModelRenderTransforms(model.head, false);
/* 24:23 */     AfterCameraTransformation.undoRotation(model.chest);
/* 25:24 */     AfterCameraTransformation.undoRotation(model.pelvis);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.hooks.AnimatedPlayerCamera
 * JD-Core Version:    0.7.0.1
 */