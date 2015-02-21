package kes5219.improvedfirstperson.hooks;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

public class MouseSelectionOverride
{
  private static double tempPosX;
  private static double tempPosY;
  private static double tempPosZ;
  private static double tempPrevPosX;
  private static double tempPrevPosY;
  private static double tempPrevPosZ;
  private static boolean shouldRestore;
  
  public static void onMethodStart()
  {
    Minecraft mc = IFPClientProxy.getMC();
    if ((mc != null) && (mc.renderViewEntity != null) && (mc.gameSettings != null) && (mc.gameSettings.thirdPersonView == 0))
    {
      shouldRestore = true;
      EntityLivingBase viewEntity = mc.renderViewEntity;
      tempPosX = viewEntity.posX;
      tempPosY = viewEntity.posY;
      tempPosZ = viewEntity.posZ;
      tempPrevPosX = viewEntity.prevPosX;
      tempPrevPosY = viewEntity.prevPosY;
      tempPrevPosZ = viewEntity.prevPosZ;
      viewEntity.posX += ActiveRenderInfo.objectX;
      viewEntity.posY += ActiveRenderInfo.objectY;
      viewEntity.posZ += ActiveRenderInfo.objectZ;
      viewEntity.prevPosX = viewEntity.posX;
      viewEntity.prevPosY = viewEntity.posY;
      viewEntity.prevPosZ = viewEntity.posZ;
    }
    else
    {
      shouldRestore = false;
    }
  }
  
  public static void onMethodEnd()
  {
    if (shouldRestore)
    {
      EntityLivingBase viewEntity = IFPClientProxy.getMC().renderViewEntity;
      viewEntity.posX = tempPosX;
      viewEntity.posY = tempPosY;
      viewEntity.posZ = tempPosZ;
      viewEntity.prevPosX = tempPrevPosX;
      viewEntity.prevPosY = tempPrevPosY;
      viewEntity.prevPosZ = tempPrevPosZ;
    }
  }
}
