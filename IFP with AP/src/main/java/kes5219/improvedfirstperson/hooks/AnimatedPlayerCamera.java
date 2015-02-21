package kes5219.improvedfirstperson.hooks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.entity.Render;
import thehippomaster.animatedplayer.PlayerData;
import thehippomaster.animatedplayer.client.ModelPlayer;
import thehippomaster.animatedplayer.client.RenderPlayer;

public class AnimatedPlayerCamera
{
  public static void doAnimatedPlayerTransforms(EntityPlayer player, Render render, float limbSwing, float limbSwingAmount, float existedPartial, float headOffset, float pitch, float partialTick)
  {
    RenderPlayer apRender = (RenderPlayer)render;
    ModelPlayer model = apRender.playerModel;
    PlayerData data = PlayerData.getPlayerData(player);
    
    model.partialTick = partialTick;
    model.setAngles(data);
    model.animate(player, data, limbSwing, limbSwingAmount, existedPartial, headOffset, pitch, 0.0625F);
    
    AfterCameraTransformation.doModelRenderTransforms(model.pelvis, true);
    AfterCameraTransformation.doModelRenderTransforms(model.chest, true);
    AfterCameraTransformation.doModelRenderTransforms(model.head, false);
    AfterCameraTransformation.undoRotation(model.chest);
    AfterCameraTransformation.undoRotation(model.pelvis);
  }
}
