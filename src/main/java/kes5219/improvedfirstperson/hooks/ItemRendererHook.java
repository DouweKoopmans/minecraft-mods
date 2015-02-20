package kes5219.improvedfirstperson.hooks;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

public class ItemRendererHook
{
  public static boolean shouldRenderItemInFirstPerson()
  {
    Minecraft mc = IFPClientProxy.getMC();
    GameSettings settings = mc.gameSettings;
    if ((ModImprovedFirstPerson.enableBodyRender) && (settings.thirdPersonView == 0) && (!mc.renderViewEntity.isPlayerSleeping()) && (!settings.hideGUI)) {
      return false;
    }
    return true;
  }
}
