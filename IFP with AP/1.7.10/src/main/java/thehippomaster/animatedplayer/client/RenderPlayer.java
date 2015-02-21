package thehippomaster.animatedplayer.client;

import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post;
import net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import thehippomaster.AnimationAPI.client.ModelJoint;
import thehippomaster.AnimationAPI.client.ModelObjRenderer;
import thehippomaster.animatedplayer.AnimatedPlayer;
import thehippomaster.animatedplayer.PlayerData;
import thehippomaster.animatedplayer.PlayerData.TextureInfo;

@SideOnly(Side.CLIENT)
public class RenderPlayer
  extends net.minecraft.client.renderer.entity.RenderPlayer
{
  public ModelPlayer playerModel;
  public ArmorModels playerArmor;
  private HashMap<ModelBiped, ArmorModels> customArmorMap;
  private static final ResourceLocation defaultTexture = new ResourceLocation("AnimatedPlayer:textures/steve.png");
  
  public RenderPlayer()
  {
    this.playerModel = new ModelPlayer(0.0F);
    this.playerArmor = new ArmorModels();
    this.mainModel = this.playerModel;
    this.customArmorMap = new HashMap();
  }
  
  protected ResourceLocation func_110775_a(AbstractClientPlayer player)
  {
    PlayerData data = PlayerData.getPlayerData(player);
    if ((AnimatedPlayer.replaceDefaultPlayerTexture) && (data.textureInfo.defaultTexture)) {
      return defaultTexture;
    }
    return super.func_110775_a(player);
  }
  
  private void setRotationsTo(ModelRenderer original, ModelRenderer target)
  {
    target.rotateAngleX = original.rotateAngleX;
    target.rotateAngleY = original.rotateAngleY;
    target.rotateAngleZ = original.rotateAngleZ;
  }
  
  private void transferToJoint(ModelRenderer temp, ModelJoint tempJoint)
  {
    setRotationsTo(temp, tempJoint);
    tempJoint.func_78793_a(temp.rotationPointX, temp.rotationPointY, temp.rotationPointZ);
    tempJoint.getModel().mirror = temp.mirror;
    tempJoint.getModel().cubeList = temp.cubeList;
    tempJoint.getModel().childModels = temp.childModels;
  }
  
  private void createCustomArmorModel(ModelBiped biped)
  {
    ArmorModels models = new ArmorModels();
    ModelPlayer model = null;
    ModelRenderer temp = null;
    ModelJoint tempJoint = null;
    

    models.head.head.func_78792_a(biped.bipedHead);
    models.head.headwear.addChild(biped.bipedHeadwear);
    

    temp = biped.bipedBody;
    biped.bipedBody = (tempJoint = new ModelJoint(biped, 16, 16));
    transferToJoint(temp, tempJoint);
    tempJoint.getModel().setRotationPoint(0.0F, -12.0F, 0.0F);
    models.chest.chest.addChild(biped.bipedBody);
    

    temp = biped.bipedLeftLeg;
    biped.bipedLeftLeg = (tempJoint = new ModelJoint(biped, 0, 16));
    transferToJoint(temp, tempJoint);
    tempJoint.getModel().setRotationPoint(-1.9F, -12.0F, 0.0F);
    temp = biped.bipedRightLeg;
    biped.bipedRightLeg = (tempJoint = new ModelJoint(biped, 0, 16));
    transferToJoint(temp, tempJoint);
    tempJoint.getModel().setRotationPoint(1.9F, -12.0F, 0.0F);
    models.mainArmor.arm1.addChild(biped.bipedLeftArm);
    models.mainArmor.arm2.addChild(biped.bipedRightArm);
    models.mainArmor.leg1.func_78792_a(biped.bipedLeftLeg);
    models.mainArmor.leg2.func_78792_a(biped.bipedRightLeg);
    
    this.customArmorMap.put(biped, models);
  }
  
  protected int func_77032_a(AbstractClientPlayer par1AbstractClientPlayer, int par2, float par3)
  {
    ItemStack itemstack = par1AbstractClientPlayer.inventory.armorItemInSlot(3 - par2);
    
    RenderPlayerEvent.SetArmorModel event = new RenderPlayerEvent.SetArmorModel(par1AbstractClientPlayer, this, 3 - par2, par3, itemstack);
    MinecraftForge.EVENT_BUS.post(event);
    if (event.result != -1) {
      return event.result;
    }
    if (itemstack != null)
    {
      Item item = itemstack.getItem();
      if ((item instanceof ItemArmor))
      {
        ItemArmor itemarmor = (ItemArmor)item;
        bindTexture(RenderBiped.getArmorResource(par1AbstractClientPlayer, itemstack, par2, null));
        ModelPlayer model = par2 == 2 ? this.playerArmor.mainArmor : this.playerArmor.chest;
        if (par2 == 0) {
          model = this.playerArmor.head;
        }
        ModelBiped biped = ForgeHooksClient.getArmorModel(par1AbstractClientPlayer, itemstack, par2, model);
        ArmorModels models = null;
        if (((biped instanceof ModelPlayer)) && (model != biped))
        {
          model = (ModelPlayer)biped;
        }
        else if (!(biped instanceof ModelPlayer))
        {
          if (!this.customArmorMap.containsKey(biped)) {
            createCustomArmorModel(biped);
          }
          models = (ArmorModels)this.customArmorMap.get(biped);
          model = par2 == 2 ? models.mainArmor : models.chest;
          if (par2 == 0) {
            model = models.head;
          }
        }
        model.head.field_78806_j = (par2 == 0);
        model.headwear.showModel = (par2 == 0);
        model.chest.showModel = ((par2 == 1) || (par2 == 2) || (par2 == 0));
        model.arm1.showModel = (par2 == 1);
        model.arm2.showModel = (par2 == 1);
        model.leg1.field_78806_j = ((par2 == 2) || (par2 == 3));
        model.leg2.field_78806_j = ((par2 == 2) || (par2 == 3));
        model.foot1.field_78806_j = ((par2 == 2) || (par2 == 3));
        model.foot2.field_78806_j = ((par2 == 2) || (par2 == 3));
        if (models != null) {
          if (model == models.mainArmor)
          {
            if (!biped.bipedLeftArm.showModel) {
              models.mainArmor.arm1.showModel = false;
            }
            if (!biped.bipedRightArm.showModel) {
              models.mainArmor.arm2.showModel = false;
            }
            if (!biped.bipedLeftLeg.showModel) {
              models.mainArmor.leg1.field_78806_j = false;
            }
            if (!biped.bipedRightLeg.showModel) {
              models.mainArmor.leg2.field_78806_j = false;
            }
          }
          else if (model == models.chest)
          {
            if (!biped.bipedBody.showModel) {
              models.chest.chest.showModel = false;
            }
          }
          else if (model == models.head)
          {
            if (!biped.bipedHeadwear.showModel) {
              models.head.headwear.showModel = false;
            }
          }
        }
        setRenderPassModel(model);
        model.onGround = this.mainModel.onGround;
        model.isRiding = this.mainModel.isRiding;
        model.isChild = this.mainModel.isChild;
        
        int j = itemarmor.getColor(itemstack);
        if (j != -1)
        {
          float f2 = (j >> 16 & 0xFF) / 255.0F;
          float f3 = (j >> 8 & 0xFF) / 255.0F;
          float f4 = (j & 0xFF) / 255.0F;
          GL11.glColor3f(f2, f3, f4);
          if (itemstack.isItemEnchanted()) {
            return 31;
          }
          return 16;
        }
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        if (itemstack.isItemEnchanted()) {
          return 15;
        }
        return 1;
      }
    }
    return -1;
  }
  
  public void func_76986_a(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6, float par8, float par9)
  {
    float f2 = 1.0F;
    GL11.glColor3f(f2, f2, f2);
    ItemStack itemstack = par1AbstractClientPlayer.inventory.getCurrentItem();
    this.playerArmor.chest.field_78120_m = (this.playerArmor.mainArmor.field_78120_m = this.playerModel.field_78120_m = itemstack != null ? 1 : 0);
    this.playerArmor.head.field_78120_m = this.playerArmor.chest.field_78120_m;
    if ((itemstack != null) && (par1AbstractClientPlayer.getItemInUseCount() > 0))
    {
      EnumAction enumaction = itemstack.getItemUseAction();
      if (enumaction == EnumAction.bow)
      {
        this.playerArmor.chest.field_78118_o = (this.playerArmor.mainArmor.field_78118_o = this.playerModel.field_78118_o = par1AbstractClientPlayer.getItemInUseCount());
        this.playerArmor.head.field_78118_o = this.playerArmor.chest.field_78118_o;
      }
    }
    double d3 = par4 - par1AbstractClientPlayer.yOffset;
    
    super.func_76986_a(par1AbstractClientPlayer, par2, par4, par6, par8, par9);
    this.playerArmor.chest.field_78118_o = (this.playerArmor.mainArmor.field_78118_o = this.playerModel.field_78118_o = 0);
    this.playerArmor.head.field_78118_o = 0;
    this.playerArmor.chest.field_78120_m = (this.playerArmor.mainArmor.field_78120_m = this.playerModel.field_78120_m = 0);
    this.playerArmor.head.field_78120_m = 0;
  }
  
  protected void renderArrowsStuckInEntity(EntityLivingBase par1EntityLiving, float par2)
  {
    int i = par1EntityLiving.getArrowCountInEntity();
    if (i > 0)
    {
      EntityArrow entityarrow = new EntityArrow(par1EntityLiving.worldObj, par1EntityLiving.posX, par1EntityLiving.posY, par1EntityLiving.posZ);
      Random random = new Random(par1EntityLiving.getEntityId());
      RenderHelper.disableStandardItemLighting();
      for (int j = 0; j < i; j++)
      {
        GL11.glPushMatrix();
        ModelRenderer modelrenderer = this.mainModel.getRandomModelBox(random);
        if (modelrenderer.cubeList.isEmpty())
        {
          if ((modelrenderer.childModels == null) || (modelrenderer.childModels.isEmpty()))
          {
            GL11.glPopMatrix();
            continue;
          }
          modelrenderer = (ModelRenderer)modelrenderer.childModels.get(0);
          if ((modelrenderer == null) || (modelrenderer.cubeList.isEmpty()))
          {
            GL11.glPopMatrix();
            continue;
          }
        }
        ModelBox modelbox = (ModelBox)modelrenderer.cubeList.get(random.nextInt(modelrenderer.cubeList.size()));
        this.playerModel.postRender(0.0625F, modelrenderer);
        float f1 = random.nextFloat();
        float f2 = random.nextFloat();
        float f3 = random.nextFloat();
        float f4 = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f1) / 16.0F;
        float f5 = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f2) / 16.0F;
        float f6 = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f3) / 16.0F;
        GL11.glTranslatef(f4, f5, f6);
        f1 = f1 * 2.0F - 1.0F;
        f2 = f2 * 2.0F - 1.0F;
        f3 = f3 * 2.0F - 1.0F;
        f1 *= -1.0F;
        f2 *= -1.0F;
        f3 *= -1.0F;
        float f7 = MathHelper.sqrt_float(f1 * f1 + f3 * f3);
        entityarrow.prevRotationYaw = (entityarrow.rotationYaw = (float)(Math.atan2(f1, f3) * 180.0D / 3.141592653589793D));
        entityarrow.prevRotationPitch = (entityarrow.rotationPitch = (float)(Math.atan2(f2, f7) * 180.0D / 3.141592653589793D));
        double d0 = 0.0D;
        double d1 = 0.0D;
        double d2 = 0.0D;
        float f8 = 0.0F;
        this.renderManager.func_147940_a(entityarrow, d0, d1, d2, f8, par2);
        GL11.glPopMatrix();
      }
      RenderHelper.enableStandardItemLighting();
    }
  }
  
  protected void func_77029_c(AbstractClientPlayer player, float par2)
  {
    RenderPlayerEvent.Specials.Pre event = new RenderPlayerEvent.Specials.Pre(player, this, par2);
    if (MinecraftForge.EVENT_BUS.post(event)) {
      return;
    }
    float f1 = 1.0F;
    GL11.glColor3f(f1, f1, f1);
    
    renderArrowsStuckInEntity(player, par2);
    ItemStack itemstack = player.inventory.armorItemInSlot(3);
    if (itemstack != null)
    {
      GL11.glPushMatrix();
      this.playerModel.pelvis.postRender(0.0625F);
      this.playerModel.chest.postRender(0.0625F);
      this.playerModel.head.func_78794_c(0.0625F);
      this.playerModel.head.getModel().postRender(0.0625F);
      if ((itemstack != null) && ((itemstack.getItem() instanceof ItemBlock)))
      {
        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
        boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
        if ((is3D) || (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())))
        {
          float f2 = 0.625F;
          GL11.glTranslatef(0.0F, -0.25F, 0.0F);
          GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(f2, -f2, -f2);
        }
        this.renderManager.itemRenderer.renderItem(player, itemstack, 0);
      }
      else if (itemstack.getItem() == Items.skull)
      {
        float f2 = 1.0625F;
        GL11.glScalef(f2, -f2, -f2);
        String s = "";
        if ((itemstack.hasTagCompound()) && (itemstack.getTagCompound().hasKey("SkullOwner"))) {
          s = itemstack.getTagCompound().getString("SkullOwner");
        }
        TileEntitySkullRenderer.field_147536_b.func_147530_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), s);
      }
      GL11.glPopMatrix();
    }
    if ((player.getCommandSenderName().equals("deadmau5")) && (player.getTextureSkin().isTextureUploaded())) {
      for (int i = 0; i < 2; i++)
      {
        float f5 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * par2 - (player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2);
        float f3 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * par2;
        GL11.glPushMatrix();
        GL11.glRotatef(f5, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(0.375F * (i * 2 - 1), 0.0F, 0.0F);
        GL11.glTranslatef(0.0F, -0.375F, 0.0F);
        GL11.glRotatef(-f3, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-f5, 0.0F, 1.0F, 0.0F);
        float f4 = 1.333333F;
        GL11.glScalef(f4, f4, f4);
        this.playerModel.renderEars(0.0625F);
        GL11.glPopMatrix();
      }
    }
    boolean loadCapeTexture = player.getTextureCape().isTextureUploaded();
    boolean playerVisible = !player.isInvisible();
    boolean showCape = !player.getHideCape();
    if ((loadCapeTexture) && (playerVisible) && (showCape))
    {
      bindTexture(player.getLocationCape());
      GL11.glPushMatrix();
      double d0 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * par2 - (player.prevPosX + (player.posX - player.prevPosX) * par2);
      double d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * par2 - (player.prevPosY + (player.posY - player.prevPosY) * par2);
      double d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * par2);
      float f6 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
      double d3 = MathHelper.sin(f6 * 3.141593F / 180.0F);
      double d4 = -MathHelper.cos(f6 * 3.141593F / 180.0F);
      float f7 = (float)d1 * 10.0F;
      if (f7 < -6.0F) {
        f7 = -6.0F;
      }
      if (f7 > 32.0F) {
        f7 = 32.0F;
      }
      float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
      if (f8 < 0.0F) {
        f8 = 0.0F;
      }
      float f10 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * par2;
      f7 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;
      this.playerModel.bipedCloak.rotateAngleX = (-(6.0F + f8 / 2.0F + f7) * 3.141593F / 180.0F);
      this.playerModel.bipedCloak.rotateAngleY = 3.141593F;
      this.playerModel.pelvis.postRender(0.0625F);
      this.playerModel.chest.postRender(0.0625F);
      this.playerModel.bipedCloak.render(0.0625F);
      GL11.glPopMatrix();
    }
    ItemStack itemstack1 = player.inventory.getCurrentItem();
    if (itemstack1 != null)
    {
      GL11.glPushMatrix();
      this.playerModel.pelvis.postRender(0.0625F);
      this.playerModel.chest.postRender(0.0625F);
      this.playerModel.arm1.postRender(0.0625F);
      this.playerModel.forearm1.func_78794_c(0.0625F);
      GL11.glTranslatef(0.03F, 0.135F, -0.01F);
      if (player.fishEntity != null) {
        itemstack1 = new ItemStack(Items.stick);
      }
      EnumAction enumaction = null;
      if (player.getItemInUseCount() > 0) {
        enumaction = itemstack1.getItemUseAction();
      }
      IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
      boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));
      if (((itemstack1.getItem() instanceof ItemBlock)) && ((is3D) || (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType()))))
      {
        float f3 = 0.5F;
        GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
        f3 *= 0.75F;
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-f3, -f3, f3);
      }
      else if (itemstack1.getItem() == Items.bow)
      {
        float f3 = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f3, -f3, f3);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      }
      else if (itemstack1.getItem().isFull3D())
      {
        float f3 = 0.625F;
        if (itemstack1.getItem().shouldRotateAroundWhenRendering())
        {
          GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
          GL11.glTranslatef(0.0F, -0.125F, 0.0F);
        }
        GL11.glTranslatef(0.0F, 0.1875F, -0.0F);
        GL11.glScalef(f3, -f3, f3);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      }
      else
      {
        float f3 = 0.375F;
        GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
        GL11.glScalef(f3, f3, f3);
        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }
      if (itemstack1.getItem().requiresMultipleRenderPasses()) {
        for (int j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); j++)
        {
          int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
          float f12 = (k >> 16 & 0xFF) / 255.0F;
          float f11 = (k >> 8 & 0xFF) / 255.0F;
          float f6 = (k & 0xFF) / 255.0F;
          GL11.glColor4f(f12, f11, f6, 1.0F);
          this.renderManager.itemRenderer.renderItem(player, itemstack1, j);
        }
      }
      int j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
      float f4 = (j >> 16 & 0xFF) / 255.0F;
      float f12 = (j >> 8 & 0xFF) / 255.0F;
      float f11 = (j & 0xFF) / 255.0F;
      GL11.glColor4f(f4, f12, f11, 1.0F);
      this.renderManager.itemRenderer.renderItem(player, itemstack1, 0);
      

      GL11.glPopMatrix();
    }
    MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Specials.Post(player, this, par2));
  }
  
  private static class ArmorModels
  {
    public ModelPlayer mainArmor;
    public ModelPlayer chest;
    public ModelPlayer head;
    
    public ArmorModels()
    {
      this.mainArmor = new ModelPlayer(0.5F, true);
      this.chest = new ModelPlayer(1.0F, true);
      this.head = new ModelPlayer(0.5F, true);
      this.head.chest.cubeList.clear();
    }
    
    public ArmorModels emptyModels()
    {
      for (int i = 0; i < this.mainArmor.boxList.size(); i++)
      {
        ModelRenderer model = (ModelRenderer)this.mainArmor.boxList.get(i);
        ModelRenderer model1 = (ModelRenderer)this.chest.boxList.get(i);
        ModelRenderer model2 = (ModelRenderer)this.head.boxList.get(i);
        model.cubeList.clear();
        model1.cubeList.clear();
        model2.cubeList.clear();
      }
      return this;
    }
  }
}
