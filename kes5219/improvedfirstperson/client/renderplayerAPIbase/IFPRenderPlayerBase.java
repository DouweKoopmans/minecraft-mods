package kes5219.improvedfirstperson.client.renderplayerAPIbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import kes5219.improvedfirstperson.client.IFPClientProxy;
import kes5219.utils.misc.PartialTickRetriever;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameData;
import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.MinecraftForgeClient;

public class IFPRenderPlayerBase extends RenderPlayerBase {
	public IFPRenderPlayerBase(RenderPlayerAPI renderPlayerAPI) {
		super(renderPlayerAPI);
	}

    public void afterRenderModel(EntityLivingBase entity, float limbSwing, float limbYaw, float existedTicksPartial, float headYawOffset, float pitch, float scale)
    {
    }
	/*
	public void drawFirstPersonHand(EntityPlayer entityPlayer) {
		new Exception().printStackTrace();
		//does not get called at all since the method renderItemInFirstPerson is modified
		//do nothing
	}*/

	@Override
	public void renderSpecialHeadArmor(AbstractClientPlayer player, float partialTick) {
		Minecraft mc = IFPClientProxy.getMC();
		/*if(mc.gameSettings.thirdPersonView > 0 && var1 == mc.renderViewEntity) {
			renderPlayer.localRenderSpecialHeadArmor(var1, var2);
		}*/
		if(player != mc.renderViewEntity ||
				mc.gameSettings.thirdPersonView > 0 ||
				RenderManager.instance.playerViewY == 180/*if the inventory is open*/) {
			renderPlayerAPI.localRenderSpecialHeadArmor(player, partialTick);
		}
	}

	class ArrowPosition extends Object {
		ModelRenderer modelRenderer;
		float randX;
		float randY;
		float randZ;
		float arrowX;
		float arrowY;
		float arrowZ;

		public ArrowPosition(ModelRenderer modelRenderer,
				float randX, float randY, float randZ,
				float arrowX, float arrowY, float arrowZ)
		{
			this.modelRenderer = modelRenderer;
			this.randX = randX;
			this.randY = randY;
			this.randZ = randZ;
			this.arrowX = arrowX;
			this.arrowY = arrowY;
			this.arrowZ = arrowZ;
		}
	}
	HashMap<Integer, ArrayList<ArrowPosition>> arrowCache = new HashMap();

	@Override
	public void renderArrowsStuckInEntity(EntityLivingBase entity, float partialTick)
	{
		Minecraft mc = IFPClientProxy.getMC();
		
		if (entity == mc.thePlayer)
		{
			int arrowCount = entity.getArrowCountInEntity();
	
			if (arrowCount > 0)
			{
				EntityArrow arrow = new EntityArrow(entity.worldObj, entity.posX, entity.posY, entity.posZ);
				Random random = null;
	
				ArrayList<ArrowPosition> arrowList = arrowCache.get(entity.getEntityId());
	
				if (arrowList == null || arrowList.size() != arrowCount)
					arrowList = new ArrayList();
	
				for (int arrowIndex = 0; arrowIndex < arrowCount; ++arrowIndex)
				{
					GL11.glPushMatrix();
	
					ModelRenderer modelRenderer = null;
					ModelBox box = null;
	
					// Translation
					float randX = 0;
					float randY = 0;
					float randZ = 0;
					float arrowX = 0;
					float arrowY = 0;
					float arrowZ = 0;
	
					if (arrowList.size() == arrowCount)
					{
						ArrowPosition arrowPos = arrowList.get(arrowIndex);
	
						if (arrowPos != null)
						{
							modelRenderer = arrowPos.modelRenderer;
							randX = arrowPos.randX;
							randY = arrowPos.randY;
							randZ = arrowPos.randZ;
							arrowX = arrowPos.arrowX;
						}
					}
	
					if (modelRenderer == null)
					{
						AxisAlignedBB headBB = AxisAlignedBB.getBoundingBox(-0.6F, -0.8F, -0.6F, 0.6F, 0.3F, 0.6F);
						
						if (random == null)
							random = new Random(entity.getEntityId());
	
						int tries = 0;
	
						while (tries < 10 && (modelRenderer == null ||
								(entity == mc.renderViewEntity &&
								headBB.isVecInside(Vec3.createVectorHelper(arrowX, arrowY, arrowZ)))))
						{
							modelRenderer = renderPlayerAPI.getMainModelField().getRandomModelBox(random);
							box = (ModelBox)modelRenderer.cubeList.get(random.nextInt(modelRenderer.cubeList.size()));
	
							randX = random.nextFloat();
							randY = random.nextFloat();
							randZ = random.nextFloat();
	
							arrowX = (box.posX1 + (box.posX2 - box.posX1) * randX) / 16.0F;
							arrowY = (box.posY1 + (box.posY2 - box.posY1) * randY) / 16.0F;
							arrowZ = (box.posZ1 + (box.posZ2 - box.posZ1) * randZ) / 16.0F;
	
							tries++;
						}
	
						arrowList.add(new ArrowPosition(modelRenderer,
								randX, randY, randZ,
								arrowX, arrowY, arrowZ));
					}
	
					modelRenderer.postRender(0.0625F);
	
					GL11.glTranslatef(arrowX, arrowY, arrowZ);
	
					// Rotation
					randX = randX * 2.0F - 1.0F;
					randY = randY * 2.0F - 1.0F;
					randZ = randZ * 2.0F - 1.0F;
					randX *= -1.0F;
					randY *= -1.0F;
					randZ *= -1.0F;
					float dist = MathHelper.sqrt_float(randX * randX + randZ * randZ);
					arrow.prevRotationYaw = arrow.rotationYaw = (float)(Math.atan2(randX, randZ) * 180.0D / Math.PI);
					arrow.prevRotationPitch = arrow.rotationPitch = (float)(Math.atan2(randY, dist) * 180.0D / Math.PI);
	
					// Render
					renderPlayerAPI.getRenderManagerField().renderEntityWithPosYaw(arrow, 0, 0, 0, 0, partialTick);
	
					GL11.glPopMatrix();
				}
	
				arrowCache.put(entity.getEntityId(), arrowList);
			}
		}
		else
		{
			renderPlayerAPI.localRenderArrowsStuckInEntity(entity, partialTick);
		}
	}

	private static final int swingRotation = -140;
	private static final int swingRotationWindup = 35;
	private static final float swingCancel = 0.7F;

	@Override
	public void afterPositionSpecialItemInHand(AbstractClientPlayer player, float partialTick, EnumAction useAction, ItemStack heldStack) {
		Minecraft mc = IFPClientProxy.getMC();
		
		if (player == mc.thePlayer && mc.gameSettings.thirdPersonView == 0)
			RenderHelper.enableStandardItemLighting();

		//render bow on player's left hand
		if (heldStack.getItem() instanceof ItemBow) {
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			renderPlayerAPI.getModelBipedMainField().bipedLeftArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

			GL11.glScalef(0.625F, -0.625F, 0.625F);
			GL11.glTranslatef(0.1F, 0, 0.3F);

			float rot = 15F * renderPlayerAPI.getModelBipedMainField().bipedHead.rotateAngleX;

			float correctionsRot = rot;

			if (correctionsRot > 0)
			{
				correctionsRot *= 2;

				float max = 12.5F;

				if (correctionsRot > max)
				{
					correctionsRot = max - (correctionsRot - max);
				}

				correctionsRot = MathHelper.sqrt_float(correctionsRot);

				if (correctionsRot > 0)
					GL11.glRotatef(correctionsRot, 0, 0, 1); //Z-axis
			}
			else
			{
				correctionsRot *= -1.6F;

				float yOff = -0.3F;
				float zOff = -0.5F;

				GL11.glTranslatef(0, yOff, zOff);
				GL11.glRotatef(correctionsRot, 1, 0, 0);
				GL11.glRotatef(correctionsRot * 0.8F, 0, 0, 1);
				GL11.glRotatef(correctionsRot * 1.5F, 0, 1, 0);
				GL11.glTranslatef(0, -yOff, -zOff);
			}

			GL11.glRotatef(-120, 1, 0, 0);

			rot = Math.abs(rot);

			if (rot > 0)
			{
				GL11.glRotatef(10F + rot, 0, 1, 0); //Y-axis
			}
		}
		else if (player.isSwingInProgress && player.swingProgress > 0)
		{
			Item heldItem = player.getHeldItem().getItem();

			if (heldItem.isFull3D())
			{
				boolean rotatedAround = heldItem.shouldRotateAroundWhenRendering();
				
				float actualSwing = player.getSwingProgress(partialTick);
				float rot = actualSwing * swingRotation + swingRotationWindup;

				float cancel = actualSwing - swingCancel;

				if (cancel > 0)
				{
					cancel = cancel / (1 - swingCancel) * swingRotation;
					rot -= cancel;
				}
				
				if (rotatedAround)
				{
                    GL11.glRotatef(-180, 0, 0, 1);
                    GL11.glTranslatef(0, 0.125F, 0);
                    
                    if (player.fishEntity == null)
                    	rot *= 0.75F;
                    else if (heldItem instanceof ItemFishingRod)
                    	rot = 0;
				}
				
				GL11.glRotatef(rot, 1, 0, 0.6F);
				
				if (rotatedAround)
				{
                    GL11.glRotatef(180, 0, 0, 1);
                    GL11.glTranslatef(0, -0.125F, 0);
				}
			}
		}
	}
	
	@Override
	public void positionSpecialItemInHand(AbstractClientPlayer player, float partialTick, EnumAction action, ItemStack stack)
	{
        if (player.fishEntity != null)
        	stack = new ItemStack(GameData.getItemRegistry().getObject("fishing_rod"), stack.stackSize);
        
        renderPlayer.localPositionSpecialItemInHand(player, partialTick, action, stack);
	}
}