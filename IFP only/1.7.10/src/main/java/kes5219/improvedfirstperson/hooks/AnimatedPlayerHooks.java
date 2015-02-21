/*
 * package kes5219.improvedfirstperson.hooks;
 * import kes5219.improvedfirstperson.client.IFPClientProxy;
 * import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
 * import net.minecraft.client.Minecraft;
 * import net.minecraft.client.renderer.entity.RenderManager;
 * import net.minecraft.client.settings.GameSettings;
 * import net.minecraft.entity.player.EntityPlayer;
 * import thehippomaster.animatedplayer.client.ModelPlayer;
 * public class AnimatedPlayerHooks
 * {
 * public static void beforeAnimatedPlayerRender(EntityPlayer player, ModelPlayer model)
 * {
 * Minecraft mc = IFPClientProxy.getMC();
 * model.head.isHidden = ((ModImprovedFirstPerson.enableBodyRender) && (mc.gameSettings.thirdPersonView == 0) && (player == mc.renderViewEntity) && (RenderManager.instance.playerViewY != 180.0F));
 * }
 * }
 */