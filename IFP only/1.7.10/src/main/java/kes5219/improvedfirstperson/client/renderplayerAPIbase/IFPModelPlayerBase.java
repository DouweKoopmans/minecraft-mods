package kes5219.improvedfirstperson.client.renderplayerAPIbase;

import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import kes5219.utils.misc.PartialTickRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import api.player.model.ModelPlayer;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;

public class IFPModelPlayerBase extends ModelPlayerBase
{
    private float prevStrafeOffset = 0.0F;

    public IFPModelPlayerBase(ModelPlayerAPI modelPlayerAPI)
    {
        super(modelPlayerAPI);
    }

    public void beforeSetRotationAngles(float legSwing, float legYaw, float ticksExistedPartial, float headYawOffset, float pitch, float scale, Entity entity)
    {
        EntityPlayer player = (EntityPlayer)entity;

        ItemStack item = player.getHeldItem();
        if(item != null && item.getItem() instanceof ItemBow)
        {
            modelPlayer.heldItemLeft = 1;
            modelPlayer.heldItemRight = 0;
        }
    }

    public void afterSetRotationAngles(float legSwing, float legYaw, float ticksExistedPartial, float headYawOffset, float pitch, float scale, Entity entity)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = (EntityPlayer)entity;
        float partialTick = PartialTickRetriever.getPartialTick();

        boolean clientPlayer = entity == mc.renderViewEntity;
        boolean invPlayer = RenderManager.instance.playerViewY == 180.0F;
        if(invPlayer)
        {
            this.modelPlayer.bipedHead.isHidden = false;
            this.modelPlayer.bipedHeadwear.isHidden = false;
            return;
        }
        if(((mc.gameSettings.thirdPersonView == 0) && (clientPlayer)) || (mc.renderViewEntity.isPlayerSleeping()))
        {
            this.modelPlayer.bipedHead.isHidden = true;
            this.modelPlayer.bipedHeadwear.isHidden = true;
        }
        else
        {
            this.modelPlayer.bipedHead.isHidden = false;
            this.modelPlayer.bipedHeadwear.isHidden = false;
        }
        ItemStack item = player.getHeldItem();
        if((item != null) && ((Items.filled_map == item.getItem()) || (Items.map == item.getItem())))
        {
            this.modelPlayer.bipedRightArm.rotateAngleX = -0.3490659F;
            this.modelPlayer.bipedLeftArm.rotateAngleX = -0.3490659F;
            this.modelPlayer.bipedRightArm.rotateAngleZ = 0.0F;
            this.modelPlayer.bipedLeftArm.rotateAngleZ = 0.0F;

            this.modelPlayer.bipedRightArm.rotateAngleZ += MathHelper.cos(ticksExistedPartial * 0.09F) * 0.05F + 0.05F;
            this.modelPlayer.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ticksExistedPartial * 0.09F) * 0.05F + 0.05F;
            this.modelPlayer.bipedRightArm.rotateAngleX += MathHelper.sin(ticksExistedPartial * 0.067F) * 0.05F;
            this.modelPlayer.bipedLeftArm.rotateAngleX -= MathHelper.sin(ticksExistedPartial * 0.067F) * 0.05F;
        }
        if(this.modelPlayer.aimedBow)
        {
            float rotationYaw = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * partialTick;

            this.modelPlayer.bipedLeftArm.rotateAngleY += 0.15F;

            ticksExistedPartial *= 6.0F;

            this.modelPlayer.bipedLeftArm.rotateAngleZ -= 0.15F * (MathHelper.cos(ticksExistedPartial * 0.09F) * 0.05F + 0.05F);

            this.modelPlayer.bipedLeftArm.rotateAngleX -= 0.15F * (MathHelper.sin(ticksExistedPartial * 0.067F) * 0.05F);

            float headAngle = this.modelPlayer.bipedHead.rotateAngleX * 15.0F;
            float rot = headAngle / 45.0F;

            rot *= rot * 2.5F;
            if(headAngle > 0.0F)
            {
                this.modelPlayer.bipedRightArm.rotateAngleX -= rot * 2.0F;
                this.modelPlayer.bipedRightArm.rotateAngleY -= rot;
                if((clientPlayer) && (mc.gameSettings.thirdPersonView == 0) && (ModImprovedFirstPerson.enableBodyRender))
                {
                    this.modelPlayer.bipedRightArm.rotationPointX -= rot * 3.0F;
                }
                this.modelPlayer.bipedLeftArm.rotateAngleY += rot;

                rot -= 0.5F;
                rot *= 0.75F;
                if(rot > 0.0F)
                {
                    this.modelPlayer.bipedLeftArm.rotateAngleX -= rot;
                }
            }
            else
            {
                this.modelPlayer.bipedLeftArm.rotateAngleZ -= rot;
                this.modelPlayer.bipedLeftArm.rotateAngleX += rot;
                this.modelPlayer.bipedRightArm.rotateAngleX += rot * 2.75F;
            }
            if((mc.gameSettings.thirdPersonView == 0) && ((player instanceof EntityPlayerSP)))
            {
                EntityPlayerSP playerSP = (EntityPlayerSP)player;
                float strafe = playerSP.movementInput.moveStrafe;

                float div = Math.max(2.0F, MathHelper.sqrt_float(Math.abs(this.modelPlayer.bipedHead.rotateAngleX * 40.0F)));
                float targetOffset = strafe / div;
                this.prevStrafeOffset += (targetOffset - this.prevStrafeOffset) * 0.025F;

                this.modelPlayer.bipedLeftArm.rotateAngleZ += this.prevStrafeOffset;
            }
        }
        if((item == null) || ((item.getItem() instanceof ItemBow)))
        {
            this.modelPlayer.heldItemLeft = 0;
        }
        if((entity.rotationPitch > 0.0F) && (!this.modelPlayer.isRiding) && ((ModImprovedFirstPerson.enableBodyRender) || (!clientPlayer) || (invPlayer)))
        {
            float mult = ModImprovedFirstPerson.leanAmount;

            ItemStack heldItem = player.getHeldItem();
            boolean map = (heldItem != null) && ((heldItem.getItem() instanceof ItemMapBase));
            if(!map)
            {
                float off = Math.abs(entity.rotationPitch / 250.0F * mult);
                if(player.isUsingItem())
                {
                    float div = (player.getItemInUseDuration() + partialTick) / 4.0F + 1.0F;
                    if(div < 1.0F)
                    {
                        div = 1.0F;
                    }
                    off /= div;
                }
                this.modelPlayer.bipedLeftArm.rotateAngleZ -= off;
                this.modelPlayer.bipedRightArm.rotateAngleZ += off;
            }
            if(!this.modelPlayer.isSneak)
            {
                float off = entity.rotationPitch / 180.0F * mult;
                this.modelPlayer.bipedBody.rotateAngleX += off;

                off = entity.rotationPitch / 16.0F * mult;
                this.modelPlayer.bipedRightLeg.rotationPointZ += off;
                this.modelPlayer.bipedLeftLeg.rotationPointZ += off;

                off = Math.abs(entity.rotationPitch / 50.0F * mult);
                this.modelPlayer.bipedRightLeg.rotationPointY -= off;
                this.modelPlayer.bipedLeftLeg.rotationPointY -= off;
            }
        }
        if(player.isUsingItem())
        {
            ItemStack heldItemStack = player.getHeldItem();
            if(heldItemStack != null)
            {
                Item heldItem = heldItemStack.getItem();
                EnumAction action = heldItem.getItemUseAction(heldItemStack);
                if((action == EnumAction.eat) || (action == EnumAction.drink))
                {
                    float useLeftPartial = player.getItemInUseCount() + 1.0F - partialTick;
                    float progressLeft = useLeftPartial / heldItemStack.getMaxItemUseDuration();
                    float moveAmount = progressLeft * progressLeft * progressLeft;
                    moveAmount = moveAmount * moveAmount * moveAmount;
                    moveAmount = moveAmount * moveAmount * moveAmount;
                    moveAmount = 1.0F - moveAmount;

                    float preAngleX = this.modelPlayer.bipedRightArm.rotateAngleX;
                    float preAngleY = this.modelPlayer.bipedRightArm.rotateAngleY;
                    float preAngleZ = this.modelPlayer.bipedRightArm.rotateAngleZ;

                    this.modelPlayer.bipedRightArm.rotateAngleX -= moveAmount * 1.25F;
                    this.modelPlayer.bipedRightArm.rotateAngleY -= moveAmount / 2.0F;
                    this.modelPlayer.bipedRightArm.rotateAngleZ += moveAmount / 2.0F;

                    float bodyYaw = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTick;
                    float headYaw = player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * partialTick;
                    float offset = MathHelper.wrapAngleTo180_float(headYaw - bodyYaw) / 80.0F * moveAmount;

                    this.modelPlayer.bipedRightArm.rotateAngleX += offset / 2.5F;
                    this.modelPlayer.bipedRightArm.rotateAngleY += offset + 0.2F;

                    this.modelPlayer.bipedRightArm.rotationPointZ += moveAmount;
                    this.modelPlayer.bipedRightArm.rotationPointZ += (offset - 0.2F) * 2.0F;

                    float headPitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTick;
                    offset = headPitch / 180.0F;

                    this.modelPlayer.bipedRightArm.rotateAngleY -= offset;
                    if(offset > 0.0F)
                    {
                        offset /= 1.5F;
                    }
                    this.modelPlayer.bipedRightArm.rotateAngleX += offset;

                    this.modelPlayer.bipedRightArm.rotateAngleX += MathHelper.abs(MathHelper.cos(useLeftPartial / 4.0F * 3.141593F) * 0.125F) * (progressLeft > 0.2F ? -1 : 0);

                    float diffAngleX = this.modelPlayer.bipedRightArm.rotateAngleX - preAngleX;
                    float diffAngleY = this.modelPlayer.bipedRightArm.rotateAngleY;
                    float diffAngleZ = this.modelPlayer.bipedRightArm.rotateAngleZ;

                    float mult = Math.min(progressLeft / 0.2F, 1.0F);
                    mult *= mult * mult;

                    this.modelPlayer.bipedRightArm.rotateAngleX = (preAngleX + diffAngleX * mult);
                    this.modelPlayer.bipedRightArm.rotateAngleY = (preAngleY + diffAngleY * mult);
                    this.modelPlayer.bipedRightArm.rotateAngleZ = (preAngleZ + diffAngleZ * mult);
                }
            }
        }
    }

    ModelPlayer getModelPlayer()
    {
        return this.modelPlayer;
    }
}
