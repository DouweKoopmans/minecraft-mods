package kes5219.improvedfirstperson.hooks;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

public class RenderFishHook
{
  private static int thirdPersonViewTemp;
  
  public static void onMethodStart()
  {
    if (ModImprovedFirstPerson.enableBodyRender)
    {
      Minecraft mc = IFPClientProxy.getMC();
      thirdPersonViewTemp = mc.gameSettings.thirdPersonView;
      mc.gameSettings.thirdPersonView = 1;
    }
  }
  
  public static void onMethodEnd()
  {
    if (ModImprovedFirstPerson.enableBodyRender) {
      IFPClientProxy.getMC().gameSettings.thirdPersonView = thirdPersonViewTemp;
    }
  }
}
