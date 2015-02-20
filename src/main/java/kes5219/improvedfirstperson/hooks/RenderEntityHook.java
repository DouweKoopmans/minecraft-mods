package kes5219.improvedfirstperson.hooks;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import kes5219.utils.misc.PartialTickRetriever;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderEntityHook
{
  public static void onRenderEntities()
  {
    Minecraft mc = IFPClientProxy.getMC();
    if ((ModImprovedFirstPerson.enableBodyRender) && (mc.gameSettings.thirdPersonView == 0) && (!mc.thePlayer.isPlayerSleeping()) && (mc.renderViewEntity.shouldRenderInPass(MinecraftForgeClient.getRenderPass()))) {
      RenderManager.instance.renderEntitySimple(mc.renderViewEntity, PartialTickRetriever.getPartialTick());
    }
  }
}
