package kes5219.improvedfirstperson.hooks;

import java.nio.FloatBuffer;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import kes5219.utils.misc.PartialTickRetriever;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.potion.Potion;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.EntityRenderer;

import org.lwjgl.opengl.GL11;

public class AfterCameraTransformation
{
    public static final float EYE_OFFSET = 0.14F;
    public static final float EYE_OFFSET_HEIGHT = 0.11F;
    public static final double BODY_HEIGHT = 1.5D;
    private static FloatBuffer win_pos = GLAllocation.createDirectFloatBuffer(16);
    public static float crosshairYPos;

    private static float interpolateRotation(float rot1, float rot2, float partial)
    {
        rot2 -= rot1;
        while(rot2 < -180.0F)
        {
            rot2 += 360.0F;
        }
        while(rot2 >= 180.0F)
        {
            rot2 -= 360.0F;
        }
        return rot1 + partial * rot2;
    }

    public static void doModelRenderTransforms(ModelRenderer modelPart, boolean rotate)
    {
        GL11.glTranslatef(modelPart.offsetX, modelPart.offsetY, modelPart.offsetZ);

        GL11.glTranslatef(modelPart.rotationPointX * 0.0625F, modelPart.rotationPointY * 0.0625F, modelPart.rotationPointZ * 0.0625F);
        if(rotate)
        {
            GL11.glRotatef(-modelPart.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-modelPart.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-modelPart.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
        }
    }

    public static void undoRotation(ModelRenderer modelPart)
    {
        GL11.glRotatef(modelPart.rotateAngleX * 57.295776F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(modelPart.rotateAngleY * 57.295776F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(modelPart.rotateAngleZ * 57.295776F, 0.0F, 0.0F, 1.0F);
    }

    public static void afterCameraTransform()
    {
        if(ModImprovedFirstPerson.enableBodyRender)
        {
            Minecraft mc = IFPClientProxy.getMC();
            EntityPlayer player = (EntityPlayer)mc.renderViewEntity;
            float partialTick = PartialTickRetriever.getPartialTick();
            if((!player.isPlayerSleeping()) && (mc.gameSettings.thirdPersonView == 0))
            {
                GL11.glLoadIdentity();
                displayOverlayEffects(partialTick);
                if(mc.gameSettings.anaglyph)
                {
                    GL11.glTranslatef(-(EntityRenderer.anaglyphField * 2 - 1) * 0.07F, 0.0F, 0.0F);
                    GL11.glTranslatef((EntityRenderer.anaglyphField * 2 - 1) * 0.1F, 0.0F, 0.0F);
                }
                GL11.glTranslatef(0.0F, -0.11F, 0.0F);

                GL11.glRotatef(player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTick, 1.0F, 0.0F, 0.0F);

                Render render = RenderManager.instance.getEntityClassRenderObject(player.getClass());
                float limbSwing = player.limbSwing - player.limbSwingAmount * (1.0F - partialTick);
                float limbSwingAmount = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partialTick;
                float existedPartial = player.ticksExisted + partialTick;
                float bodyYaw = interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partialTick);
                float headYaw = interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partialTick);
                float headOffset = headYaw - bodyYaw;
                float pitch = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTick;
                /*
                 * if(IFPClientProxy.animatedPlayerInstalled)
                 * {
                 * AnimatedPlayerCamera.doAnimatedPlayerTransforms(player, render, limbSwing, limbSwingAmount, existedPartial, headOffset, pitch, partialTick);
                 * }
                 */
                GL11.glTranslatef(0.0F, 0.0625F, 0.14F);

                GL11.glRotatef(player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTick + 180.0F, 0.0F, 1.0F, 0.0F);

                ActiveRenderInfo.updateRenderInfo(mc.thePlayer, mc.gameSettings.thirdPersonView == 2);
            }
        }
    }

    private static void determineCrosshairPosition(float partialTick)
    {}

    public static void setupViewBobbing(float par1)
    {
        Minecraft mc = IFPClientProxy.getMC();
        if((mc.renderViewEntity instanceof EntityPlayer))
        {
            EntityPlayer var2 = (EntityPlayer)mc.renderViewEntity;
            float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
            float var4 = -(var2.distanceWalkedModified + var3 * par1);
            float var5 = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * par1;
            float var6 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * par1;
            GL11.glTranslatef(MathHelper.sin(var4 * 3.141593F) * var5 * 0.5F, -Math.abs(MathHelper.cos(var4 * 3.141593F) * var5), 0.0F);
            GL11.glRotatef(MathHelper.sin(var4 * 3.141593F) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(Math.abs(MathHelper.cos(var4 * 3.141593F - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    public static void hurtCameraEffect(float par1)
    {
        Minecraft mc = IFPClientProxy.getMC();
        EntityLivingBase var2 = mc.renderViewEntity;
        float var3 = var2.hurtTime - par1;
        if(var2.getHealth() <= 0.0F)
        {
            float var4 = var2.deathTime + par1;
            GL11.glRotatef(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
        }
        if(var3 >= 0.0F)
        {
            var3 /= var2.maxHurtTime;
            var3 = MathHelper.sin(var3 * var3 * var3 * var3 * 3.141593F);
            float var4 = var2.attackedAtYaw;
            GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
        }
    }

    private static void displayOverlayEffects(float partialTick)
    {
        Minecraft mc = IFPClientProxy.getMC();
        hurtCameraEffect(partialTick);
        if(mc.gameSettings.viewBobbing)
        {
            setupViewBobbing(partialTick);
        }
        float timeInPortal = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * partialTick;
        /*
         * int rendererUpdateCount = net.minecraft.client.renderer.EntityRenderer.rendererUpdateCount;
         * if(timeInPortal > 0.0F)
         * {
         * byte rotAmount = 20;
         * if(mc.thePlayer.isPotionActive(Potion.confusion))
         * {
         * rotAmount = 7;
         * }
         * float wobble = 5.0F / (timeInPortal * timeInPortal + 5.0F) - timeInPortal * 0.04F;
         * wobble *= wobble;
         * float rot = (rendererUpdateCount + partialTick) * rotAmount;
         * GL11.glRotatef(rot, 0.0F, 1.0F, 1.0F);
         * GL11.glScalef(1.0F / wobble, 1.0F, 1.0F);
         * GL11.glRotatef(-rot, 0.0F, 1.0F, 1.0F);
         * }
         */

    }
}
