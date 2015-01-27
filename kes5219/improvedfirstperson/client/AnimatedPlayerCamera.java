/*  1:   */ package kes5219.improvedfirstperson.client;
/*  2:   */ 
/*  3:   */ import bgm;
/*  4:   */ import kes5219.improvedfirstperson.hooks.AfterCameraTransformation;
/*  5:   */ import thehippomaster.AnimatedPlayer.PlayerData;
/*  6:   */ import thehippomaster.AnimatedPlayer.client.ModelPlayer;
/*  7:   */ import thehippomaster.AnimatedPlayer.client.RenderPlayer;
/*  8:   */ import uf;
/*  9:   */ 
/* 10:   */ public class AnimatedPlayerCamera
/* 11:   */ {
/* 12:   */   public static void doAnimatedPlayerTransforms(uf player, bgm render, float limbSwing, float limbSwingAmount, float existedPartial, float headOffset, float pitch, float partialTick)
/* 13:   */   {
/* 14:13 */     RenderPlayer apRender = (RenderPlayer)render;
/* 15:14 */     ModelPlayer model = apRender.playerModel;
/* 16:15 */     PlayerData data = PlayerData.getPlayerData(player);
/* 17:   */     
/* 18:17 */     model.partialTick = partialTick;
/* 19:18 */     model.setAngles(data);
/* 20:19 */     model.animate(player, data, limbSwing, limbSwingAmount, existedPartial, headOffset, pitch, 0.0625F);
/* 21:   */     
/* 22:21 */     AfterCameraTransformation.doModelRenderTransforms(model.pelvis, true);
/* 23:22 */     AfterCameraTransformation.doModelRenderTransforms(model.chest, true);
/* 24:23 */     AfterCameraTransformation.doModelRenderTransforms(model.head, false);
/* 25:24 */     AfterCameraTransformation.undoRotation(model.chest);
/* 26:25 */     AfterCameraTransformation.undoRotation(model.pelvis);
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.AnimatedPlayerCamera
 * JD-Core Version:    0.7.0.1
 */