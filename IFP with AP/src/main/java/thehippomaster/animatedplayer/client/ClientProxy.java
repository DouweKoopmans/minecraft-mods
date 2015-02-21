package thehippomaster.animatedplayer.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import thehippomaster.animatedplayer.AnimatedPlayer;
import thehippomaster.animatedplayer.CommonProxy;
import thehippomaster.animatedplayer.PlayerData;

@SideOnly(Side.CLIENT)
public class ClientProxy
  extends CommonProxy
{
  public static IModelCustom forearm1;
  public static IModelCustom forearm2;
  public static IModelCustom foot1;
  public static IModelCustom foot2;
  public static IModelCustom chest;
  
  public boolean isClient()
  {
    return true;
  }
  
  public void registerRenderers()
  {
    forearm1 = AdvancedModelLoader.loadModel(new ResourceLocation("AnimatedPlayer:models/forearm/SteveRightForeArm.obj"));
    forearm2 = AdvancedModelLoader.loadModel(new ResourceLocation("AnimatedPlayer:models/forearm/SteveLeftForeArm.obj"));
    foot1 = AdvancedModelLoader.loadModel(new ResourceLocation("AnimatedPlayer:models/legs/SteveRightLeg.obj"));
    foot2 = AdvancedModelLoader.loadModel(new ResourceLocation("AnimatedPlayer:models/legs/SteveLeftLeg.obj"));
    chest = AdvancedModelLoader.loadModel(new ResourceLocation("AnimatedPlayer:models/body/SteveBody.obj"));
    
    addRenderer(EntityPlayer.class, new RenderPlayer());
  }
  
  private void addRenderer(Class<? extends Entity> class1, Render renderer)
  {
    RenderingRegistry.registerEntityRenderingHandler(class1, renderer);
  }
  
  public void onClientTick()
  {
    World world = Minecraft.getMinecraft().theWorld;
    if (world == null) {
      return;
    }
    AnimatedPlayer.clientTick += 1L;
    if (AnimatedPlayer.clientTick % 200L == 0L) {
      PlayerData.recycleMap(world);
    }
    for (int i = 0; i < world.playerEntities.size(); i++)
    {
      EntityPlayer player = (EntityPlayer)world.playerEntities.get(i);
      PlayerData data = PlayerData.getPlayerData(player);
      ItemStack currentItem = player.getCurrentEquippedItem();
      if ((player.ticksExisted > 0) && (player.ticksExisted % 15 == 0)) {
        data.initTextureInfo((AbstractClientPlayer)player);
      }
      if (player != Minecraft.getMinecraft().thePlayer)
      {
        boolean prevNoClip = player.noClip;
        double oldX = player.posX;double oldY = player.posY;double oldZ = player.posZ;
        player.noClip = false;
        player.moveEntity(0.0D, -0.03999999910593033D, 0.0D);
        if (!player.onGround) {
          player.moveEntity(0.0D, 0.03999999910593033D, 0.0D);
        }
        player.noClip = prevNoClip;
      }
      double dX = player.posX - player.prevPosX;double dZ = player.posZ - player.prevPosZ;double dY = player.posY - player.prevPosY;
      double motion = dX * dX + dZ * dZ;
      float yaw = -(float)Math.atan2(dX, dZ) * 180.0F / 3.141593F;
      while (yaw <= player.rotationYaw - 180.0F) {
        yaw += 360.0F;
      }
      while (yaw > player.rotationYaw + 180.0F) {
        yaw -= 360.0F;
      }
      float tempYaw = player.rotationYaw - yaw;float tempSign = tempYaw / Math.abs(tempYaw);
      data.moveBackwards = ((motion > 0.0001D) && (Math.abs(tempYaw) > 100.0F));
      data.moveBackwards2 = (((motion > 0.0001D) && (Math.abs(tempYaw) > 100.0F)) || ((motion < 0.0001D) && (data.moveBackwards2)));
      if (data.moveBackwards)
      {
        float addYaw = (180.0F * tempSign - tempYaw) * 0.7F;
        player.renderYawOffset = (player.rotationYaw + addYaw);
      }
      data.holdingSword = false;
      if (currentItem != null)
      {
        Item item = currentItem.getItem();
        if ((item != null) && ((item instanceof ItemSword))) {
          data.holdingSword = true;
        }
      }
      data.prevExhaustionTick = data.exhaustionTick;
      if (data.updateExhaustion(player)) {
        data.exhaustionTick = Math.min(10, data.exhaustionTick + 1);
      } else {
        data.exhaustionTick = Math.max(0, data.exhaustionTick - 1);
      }
      data.prevBrowTick = data.browTick;
      if (data.shouldArchBrows(player, currentItem)) {
        data.browTick = Math.min(8, data.browTick + 1);
      } else {
        data.browTick = Math.max(0, data.browTick - 1);
      }
      data.prevSprintTick = data.sprintTick;
      if (player.isSprinting()) {
        data.sprintTick = Math.min(8, data.sprintTick + 1);
      } else {
        data.sprintTick = Math.max(0, data.sprintTick - 1);
      }
      data.prevSneakTick = data.sneakTick;
      if (player.isSneaking()) {
        data.sneakTick = Math.min(6, data.sneakTick + 1);
      } else {
        data.sneakTick = Math.max(0, data.sneakTick - 1);
      }
      data.prevJumpTick = data.jumpTick;
      if (data.isAirborne(player)) {
        data.jumpTick = Math.min(10, data.jumpTick + 1);
      } else {
        data.jumpTick = 0;
      }
      if ((data.jumpTick == 0) && (data.prevJumpTick != 0)) {
        data.jumpRight = (!data.jumpRight);
      }
      data.prevSwimTick = data.swimTick;
      data.prevPitch = data.pitch;
      float pitch = (float)Math.atan2(-dY, Math.sqrt(dX * dX + dZ * dZ));
      data.pitch += (pitch - data.pitch) * 0.1F;
      if ((player.isInWater()) && (!player.onGround)) {
        data.swimTick = Math.min(16, data.swimTick + 1);
      } else if (data.swimTick > 12) {
        data.swimTick = Math.max(0, data.swimTick - 1);
      } else {
        data.swimTick = Math.max(0, data.swimTick - 2);
      }
      data.prevBlockTick = data.blockTick;
      if (data.isBlocking(player, currentItem)) {
        data.blockTick = 1;
      } else {
        data.blockTick = 0;
      }
      data.prevClimbingTick = data.climbingTick;
      data.prevClimbingOffset = data.climbingOffset;
      if (player.isOnLadder())
      {
        data.climbingTick = Math.min(8, data.climbingTick + 1); PlayerData 
          tmp971_969 = data;tmp971_969.climbingOffset = ((float)(tmp971_969.climbingOffset + (player.posY - player.prevPosY) / 0.2D));
      }
      else
      {
        data.climbingTick = Math.max(0, data.climbingTick - 1);
      }
      data.prevEatTick = data.eatTick;
      if (data.isEating(player, currentItem)) {
        data.eatTick = Math.min(8, data.eatTick + 1);
      } else {
        data.eatTick = Math.max(0, data.eatTick - 1);
      }
      data.prevDrinkTick = data.drinkTick;
      if (data.isDrinking(player, currentItem)) {
        data.drinkTick = Math.min(8, data.drinkTick + 1);
      } else {
        data.drinkTick = Math.max(0, data.drinkTick - 1);
      }
      data.prevFlyingTick = data.flyingTick;
      data.prevPosTick = data.posTick;
      double d = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
      float pos = (float)Math.min(1.0D, d * 4.0D) * (data.moveBackwards ? -0.5F : 0.9F);
      data.posTick = (data.prevPosTick + (pos - data.prevPosTick) * 0.3F);
      if (player.capabilities.isFlying) {
        data.flyingTick = Math.min(5, data.flyingTick + 1);
      } else {
        data.flyingTick = Math.max(0, data.flyingTick - 1);
      }
    }
  }
}
