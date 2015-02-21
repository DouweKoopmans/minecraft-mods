package kes5219.improvedfirstperson.client;

import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraft.client.entity.EntityPlayerSP;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler
{
    private double lastOffX = 0.0D;
    private double lastOffY = 0.0D;
    private double lastScale = 1.0D;

    @SubscribeEvent
    public void jumpEvent(LivingEvent.LivingJumpEvent event)
    {}

    @SubscribeEvent
    public void preRenderHUD(RenderGameOverlayEvent.Post event)
    {
        if(ModImprovedFirstPerson.wibblyWobblyHUD)
        {
            Minecraft mc = IFPClientProxy.getMC();

            GuiIngameForge hud = (GuiIngameForge)mc.ingameGUI;
            if((GuiIngameForge.renderCrosshairs) && (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS))
            {
                ScaledResolution res = event.resolution;
                double width = res.getScaledWidth_double();
                double height = res.getScaledHeight_double();
                double width2 = width / 2.0D;
                double height2 = height / 2.0D;
                double scaleFac = res.getScaleFactor();

                GL11.glPushMatrix();

                double offX = this.lastOffX;
                double offY = this.lastOffY;

                double scale = this.lastScale;
                if(!IFPClientProxy.isGamePaused())
                {
                    double dX = mc.thePlayer.posX - mc.thePlayer.prevPosX;
                    double dY = mc.thePlayer.posY - mc.thePlayer.prevPosY;
                    double dZ = mc.thePlayer.posZ - mc.thePlayer.prevPosZ;

                    float rotYaw = mc.thePlayer.rotationYawHead * -0.01745329F;
                    float rotPitch = mc.thePlayer.rotationPitch * -0.01745329F;

                    double dYaw = mc.thePlayer.rotationYaw - mc.thePlayer.renderArmYaw;
                    double dPitch = mc.thePlayer.rotationPitch - mc.thePlayer.renderArmPitch;
                    if(mc.gameSettings.thirdPersonView == 2)
                    {
                        rotYaw += -3.141593F;
                        rotPitch *= -1.0F;
                        dYaw *= -1.0D;
                    }
                    float cosYaw = MathHelper.cos(rotYaw);
                    float sinYaw = MathHelper.sin(rotYaw);
                    float cosPitch = MathHelper.cos(rotPitch);
                    float sinPitch = MathHelper.sin(rotPitch);

                    float moveDir = (float)Math.atan2(dZ, dX) * 180.0F / 3.141593F - 90.0F;

                    double localYawZ = dX * sinYaw + dZ * cosYaw;

                    double localX = -dX * cosYaw + dZ * sinYaw;
                    double localY = dY * cosPitch - localYawZ * sinPitch;
                    double localZ = localYawZ * cosPitch + dY * sinPitch;

                    offX = localX * -50.0D - dYaw * 0.5D;
                    offY = localY * 25.0D - dPitch * 0.4D;

                    scale = 0.9D + localZ * 0.3D;
                    scale = Math.min(Math.max(scale, 0.8D), 1.0D);

                    double moveAmt = 3.0D / scaleFac;

                    offX /= moveAmt;
                    offY /= moveAmt;
                    scale = (scale - 1.0D) / moveAmt + 1.0D;

                    offX = this.lastOffX + (offX - this.lastOffX) * 0.3D;
                    offY = this.lastOffY + (offY - this.lastOffY) * 0.3D;
                    scale = this.lastScale + (scale - this.lastScale) * 0.3D;
                }
                GL11.glTranslated(width2, height2, 0.0D);
                GL11.glScaled(scale, scale, 1.0D);
                GL11.glTranslated(-width2, -height2, 0.0D);

                GL11.glTranslated(offX, offY, 0.0D);

                this.lastOffX = offX;
                this.lastOffY = offY;
                this.lastScale = scale;
            }
            else if(event.type == RenderGameOverlayEvent.ElementType.ALL)
            {
                GL11.glPopMatrix();
            }
        }
    }
}
