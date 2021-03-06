package kes5219.improvedfirstperson.hooks;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLivingBaseHook
{
  public static void handleRotation(EntityLivingBase entity, float startingOffset, float plannedOffset)
  {
    if ((entity instanceof EntityPlayer))
    {
      EntityPlayer player = (EntityPlayer)entity;
      boolean isRemote = player.worldObj.isRemote;
      if (!isRemote) {
        return;
      }
      float target = startingOffset;
      float limitMult = MathHelper.sqrt_float(1.0F - Math.max(entity.rotationPitch, 0.0F) / 100.0F);
      if (player.swingProgress > 0.0F)
      {
        target = entity.rotationYawHead;
      }
      else
      {
        double dX = entity.posX - entity.prevPosX;
        double dZ = entity.posZ - entity.prevPosZ;
        float dist = (float)(dX * dX + dZ * dZ);
        if (dist > 0.0005F)
        {
          float moveDir = (float)(Math.atan2(dZ, dX) * 180.0D / 3.141592653589793D) - 90.0F;
          
          target = moveDir;
          if (Math.abs(MathHelper.wrapAngleTo180_float(moveDir - entity.rotationYawHead)) > 95.0F) {
            target += 180.0F;
          }
          limitMult *= dist / 0.0025F;
        }
      }
      limitMult = MathHelper.clamp_float(limitMult, 0.0F, 1.0F);
      
      float offset = MathHelper.wrapAngleTo180_float(target - entity.rotationYawHead);
      
      float limit = 75.0F * limitMult;
      
      float pastLimit = Math.max(0.0F, Math.abs(offset) - limit);
      
      target = entity.rotationYawHead + MathHelper.clamp_float(offset, -limit, limit);
      
      float moveAmount = target - startingOffset;
      float smoothed = moveAmount * 0.3F;
      
      target = startingOffset + smoothed;
      
      entity.prevRenderYawOffset = startingOffset;
      entity.renderYawOffset = target;
      
      ItemStack stack = entity.getHeldItem();
      if ((stack != null) && ((stack.getItem() instanceof ItemBow)) && (((EntityPlayer)entity).isUsingItem())) {
        if (IFPClientProxy.animatedPlayerInstalled) {
          entity.renderYawOffset = (entity.rotationYaw - 40.0F);
        } else {
          entity.renderYawOffset = (entity.rotationYaw + 40.0F);
        }
      }
    }
  }
}
