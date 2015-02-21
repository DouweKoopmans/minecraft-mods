package thehippomaster.animatedplayer.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import thehippomaster.AnimationAPI.AnimationAPI;
import thehippomaster.AnimationAPI.CommonProxy;
import thehippomaster.AnimationAPI.client.ModelJoint;
import thehippomaster.AnimationAPI.client.ModelObjRenderer;
import thehippomaster.animatedplayer.AnimatedPlayer;
import thehippomaster.animatedplayer.PlayerData;
import thehippomaster.animatedplayer.PlayerData.TextureInfo;

@SideOnly(Side.CLIENT)
public class ModelPlayer extends ModelBiped
{
    public int field_78119_l;
    public int field_78120_m;
    public int field_78118_o;
    public boolean isArmor;
    public ModelJoint head;
    public ModelRenderer headwear;
    public ModelRenderer brow1;
    public ModelRenderer brow2;
    public ModelRenderer[][] iris;
    public ModelRenderer[] rightEye;
    public ModelRenderer[] leftEye;
    public ModelRenderer upperLip;
    public ModelRenderer lowerLip;
    public ModelRenderer insideMouth;
    public ModelRenderer mouth;
    public ModelRenderer upperTeeth;
    public ModelRenderer lowerTeeth;
    public ModelRenderer chest;
    public ModelRenderer pelvis;
    public ModelRenderer arm1;
    public ModelRenderer arm2;
    public ModelObjRenderer forearm1;
    public ModelObjRenderer forearm2;
    public ModelJoint leg1;
    public ModelJoint leg2;
    public ModelJoint foot1;
    public ModelJoint foot2;
    public ModelObjRenderer footObj1;
    public ModelObjRenderer footObj2;
    public static final float PI = 3.141593F;
    public static float partialTick = 0.0F;

    private void initImprovedHat(float f)
    {
        ModelBoxUV box = null;
        float scale = 1.14F;
        for(int x = -4; x < 4; x++)
        {
            for(int z = -4; z < 4; z++)
            {
                box = ModelBoxUV.addBox(this.headwear, x * scale, -7.5F * scale - 0.01F, z * scale, 1, 1, 1, f + 0.07F);
                box.setAllUV(44 + x, 3 - z);
                box = ModelBoxUV.addBox(this.headwear, x * scale, -0.5F * scale + 0.01F, z * scale, 1, 1, 1, f + 0.07F);
                box.setAllUV(52 + x, 3 - z);
            }
        }
        for(int x = -4; x < 4; x++)
        {
            for(int y = -8; y < 0; y++)
            {
                box = ModelBoxUV.addBox(this.headwear, x * scale, (y + 0.5F) * scale, -4.0F * scale - 0.01F, 1, 1, 1, f + 0.07F);
                box.setAllUV(44 + x, 16 + y);
                box = ModelBoxUV.addBox(this.headwear, x * scale, (y + 0.5F) * scale, 3.0F * scale + 0.01F, 1, 1, 1, f + 0.07F);
                box.setAllUV(60 + x, 16 + y);
            }
        }
        for(int z = -4; z < 4; z++)
        {
            for(int y = -8; y < 0; y++)
            {
                box = ModelBoxUV.addBox(this.headwear, -4.0F * scale - 0.01F, (y + 0.5F) * scale, z * scale, 1, 1, 1, f + 0.07F);
                box.setAllUV(52 + z, 16 + y);
                box = ModelBoxUV.addBox(this.headwear, 3.0F * scale + 0.01F, (y + 0.5F) * scale, z * scale, 1, 1, 1, f + 0.07F);
                box.setAllUV(36 - z - 1, 16 + y);
            }
        }
        box.initQuads();
    }

    private void initEyes(int position, float f)
    {
        int index = position - 1;
        int yOffset = position - 2;
        ModelBoxUV box = null;
        this.iris[0][index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.iris[0][index], -2.0F, -4.0F + yOffset, -4.001F, 1, 1, 1, f);
        box.setAllUV(27, index).initQuads();
        this.head.func_78792_a(this.iris[0][index]);
        this.iris[1][index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.iris[1][index], -3.0F, -4.0F + yOffset, -4.001F, 1, 1, 1, f);
        box.setAllUV(27, index).initQuads();
        this.head.func_78792_a(this.iris[1][index]);
        this.iris[2][index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.iris[2][index], 1.0F, -4.0F + yOffset, -4.001F, 1, 1, 1, f);
        box.setAllUV(31, index).initQuads();
        this.head.func_78792_a(this.iris[2][index]);
        this.iris[3][index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.iris[3][index], 2.0F, -4.0F + yOffset, -4.001F, 1, 1, 1, f);
        box.setAllUV(31, index).initQuads();
        this.head.func_78792_a(this.iris[3][index]);
        this.rightEye[index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.rightEye[index], -0.5F, 0.0F, 0.0F, 1, 1, 1, f);
        box.setAllUV(28, index).initQuads();
        this.rightEye[index].setRotationPoint(-1.7F, -4.0F + yOffset, -4.002F);
        this.head.func_78792_a(this.rightEye[index]);
        this.leftEye[index] = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.leftEye[index], -0.5F, 0.0F, 0.0F, 1, 1, 1, f);
        box.setAllUV(30, index).initQuads();
        this.leftEye[index].setRotationPoint(1.7F, -4.0F + yOffset, -4.002F);
        this.head.func_78792_a(this.leftEye[index]);
    }

    public ModelPlayer(float f)
    {
        this(f, false);
    }

    public ModelPlayer(float f, boolean armor)
    {
        this.field_78119_l = 0;
        this.field_78120_m = 0;
        this.field_78118_o = 0;
        this.isArmor = armor;

        ModelBoxUV box = null;
        this.pelvis = new ModelRenderer(this);
        this.pelvis.setRotationPoint(0.0F, 12.0F, 0.0F);
        if(armor)
        {
            this.chest = new ModelRenderer(this, 16, 16);
            this.chest.addBox(-4.0F, -12.0F, -2.0F, 8, 12, 4, f);
        }
        else
        {
            this.chest = new ModelObjRenderer(this, ClientProxy.chest);
        }
        this.pelvis.addChild(this.chest);

        this.head = new ModelJoint(this, 0, 0);
        this.head.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
        this.head.func_78793_a(0.0F, -12.0F, 0.0F);
        this.chest.addChild(this.head);
        this.headwear = new ModelRenderer(this, 32, 0);
        if((!AnimatedPlayer.betterHat) || (armor))
        {
            this.headwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f + 0.5F);
        }
        else
        {
            initImprovedHat(f);
        }
        this.head.func_78792_a(this.headwear);

        this.iris = new ModelRenderer[4][3];
        this.rightEye = new ModelRenderer[3];
        this.leftEye = new ModelRenderer[3];
        initEyes(1, f);
        initEyes(2, f);
        initEyes(3, f);
        this.brow1 = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.brow1, -1.5F, -0.5F, 0.0F, 3, 1, 1, f - 0.24F);
        box.setAllUV(24, 0).initQuads();
        this.brow1.setRotationPoint(-2.0F, -4.6F, -4.3F);
        this.head.func_78792_a(this.brow1);
        this.brow2 = new ModelRenderer(this);
        this.brow2.mirror = true;
        box = ModelBoxUV.addBox(this.brow2, -1.5F, -0.5F, 0.0F, 3, 1, 1, f - 0.24F);
        box.setAllUV(24, 0).initQuads();
        this.brow2.setRotationPoint(2.0F, -4.6F, -4.3F);
        this.head.func_78792_a(this.brow2);

        this.upperLip = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.upperLip, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(11, 13).initQuads();
        this.upperLip.setRotationPoint(0.0F, -2.0F, -4.004F);
        this.head.func_78792_a(this.upperLip);
        this.lowerLip = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.lowerLip, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(11, 15).initQuads();
        this.lowerLip.setRotationPoint(0.0F, 0.0F, -4.004F);
        this.head.func_78792_a(this.lowerLip);
        this.insideMouth = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.insideMouth, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(24, 2).initQuads();
        this.insideMouth.setRotationPoint(0.0F, -1.0F, -4.001F);
        this.head.func_78792_a(this.insideMouth);
        this.mouth = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.mouth, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(11, 14).initQuads();
        this.mouth.setRotationPoint(0.0F, -1.2F, -4.003F);
        this.head.func_78792_a(this.mouth);
        this.upperTeeth = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.upperTeeth, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(24, 1).initQuads();
        this.upperTeeth.setRotationPoint(0.0F, 0.1F, 0.001F);
        this.mouth.addChild(this.upperTeeth);
        this.lowerTeeth = new ModelRenderer(this);
        box = ModelBoxUV.addBox(this.lowerTeeth, -1.0F, -1.0F, 0.0F, 2, 1, 1, f);
        box.setAllUV(24, 3).initQuads();
        this.lowerTeeth.setRotationPoint(0.0F, -0.1F, -4.002F);
        this.head.func_78792_a(this.lowerTeeth);

        this.arm1 = new ModelRenderer(this, 40, 16);
        box = ModelBoxUV.addBox(this.arm1, -2.0F, -2.0F, -2.0F, 4, 6, 4, f + 0.01F);
        box.setSideUV(3, 52, 16).initQuads();
        this.arm1.setRotationPoint(-6.0F, -10.0F, 0.0F);
        this.chest.addChild(this.arm1);
        this.arm2 = new ModelRenderer(this, 40, 16);
        this.arm2.mirror = true;
        box = ModelBoxUV.addBox(this.arm2, -2.0F, -2.0F, -2.0F, 4, 6, 4, f + 0.01F);
        box.setSideUV(3, 52, 16).initQuads();
        this.arm2.setRotationPoint(6.0F, -10.0F, 0.0F);
        this.chest.addChild(this.arm2);
        this.forearm1 = new ModelObjRenderer(this);

        this.forearm1.func_78793_a(0.0F, 4.0F, 0.0F);
        this.arm1.addChild(this.forearm1);
        this.forearm2 = new ModelObjRenderer(this);

        this.forearm2.func_78793_a(0.0F, 4.0F, 0.0F);
        this.arm2.addChild(this.forearm2);

        this.leg1 = new ModelJoint(this, 0, 16);
        box = ModelBoxUV.addBox(this.leg1.getModel(), -2.0F, 0.0F, -2.0F, 4, 6, 4, f + 0.1F);
        box.setSideUV(3, 12, 16).initQuads();
        this.leg1.func_78793_a(-2.0F, 0.0F, 0.0F);
        this.pelvis.addChild(this.leg1);
        this.leg2 = new ModelJoint(this, 0, 16);
        this.leg2.getModel().mirror = true;
        box = ModelBoxUV.addBox(this.leg2.getModel(), -2.0F, 0.0F, -2.0F, 4, 6, 4, f + 0.1F);
        box.setSideUV(3, 12, 16).initQuads();
        this.leg2.func_78793_a(2.0F, 0.0F, 0.0F);
        this.pelvis.addChild(this.leg2);
        this.foot1 = new ModelJoint(this, 0, 22);
        if(armor)
        {
            box = ModelBoxUV.addBox(this.foot1.getModel(), -2.0F, 0.0F, -2.0F, 4, 6, 4, f);
            box.setSideUV(2, 0, 16).setSideUV(3, 8, 16).initQuads();
        }
        else
        {
            this.foot1.setModel(this.footObj1 = new ModelObjRenderer(this, ClientProxy.foot1));
        }
        this.foot1.func_78793_a(0.0F, 6.0F, 0.0F);
        this.leg1.func_78792_a(this.foot1);
        this.foot2 = new ModelJoint(this, 0, 22);
        this.foot2.getModel().mirror = true;
        if(armor)
        {
            box = ModelBoxUV.addBox(this.foot2.getModel(), -2.0F, 0.0F, -2.0F, 4, 6, 4, f);
            box.setSideUV(2, 0, 16).setSideUV(3, 8, 16).initQuads();
        }
        else
        {
            this.foot2.setModel(this.footObj2 = new ModelObjRenderer(this, ClientProxy.foot2));
        }
        this.foot2.func_78793_a(0.0F, 6.0F, 0.0F);
        this.leg2.func_78792_a(this.foot2);

        this.bipedCloak.setRotationPoint(0.0F, -12.0F, 2.0F);
    }

    private void initModels()
    {
        if(this.forearm1.model == null)
        {
            this.forearm1.model = ClientProxy.forearm1;
        }
        if(this.forearm2.model == null)
        {
            this.forearm2.model = ClientProxy.forearm2;
        }
        if(!this.isArmor)
        {
            if(this.footObj1.model == null)
            {
                this.footObj1.model = ClientProxy.foot1;
            }
            if(this.footObj2.model == null)
            {
                this.footObj2.model = ClientProxy.foot2;
            }
            ModelObjRenderer chestObj = (ModelObjRenderer)this.chest;
            if(chestObj.model == null)
            {
                chestObj.model = ClientProxy.chest;
            }
        }
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        EntityPlayer player = (EntityPlayer)entity;
        PlayerData data = PlayerData.getPlayerData(player);
        partialTick = AnimationAPI.proxy.getPartialTick();
        initModels();
        setShowModels(data);
        setAngles(data);
        animate(player, data, f, f1, f2, f3, f4, f5);
        for(int i = 0; i < this.rightEye.length; i++)
        {
            if(this.rightEye[i].rotationPointX < -2.5F)
            {
                this.rightEye[i].rotationPointX = -2.5F;
            }
            if(this.rightEye[i].rotationPointX > -1.5F)
            {
                this.rightEye[i].rotationPointX = -1.5F;
            }
            if(this.leftEye[i].rotationPointX < 1.5F)
            {
                this.leftEye[i].rotationPointX = 1.5F;
            }
            if(this.leftEye[i].rotationPointX > 2.5F)
            {
                this.leftEye[i].rotationPointX = 2.5F;
            }
        }
        this.pelvis.render(f5);
    }

    private void setShowModels(PlayerData data)
    {
        for(int i = 0; i < this.iris.length; i++)
        {
            this.iris[i][0].showModel = data.textureInfo.animateEyes1;
            this.iris[i][1].showModel = data.textureInfo.animateEyes2;
            this.iris[i][2].showModel = data.textureInfo.animateEyes3;
        }
        this.rightEye[0].showModel = data.textureInfo.animateEyes1;
        this.rightEye[1].showModel = data.textureInfo.animateEyes2;
        this.rightEye[2].showModel = data.textureInfo.animateEyes3;
        this.leftEye[0].showModel = data.textureInfo.animateEyes1;
        this.leftEye[1].showModel = data.textureInfo.animateEyes2;
        this.leftEye[2].showModel = data.textureInfo.animateEyes3;

        this.brow1.showModel = data.textureInfo.animateEyebrows;
        this.brow2.showModel = data.textureInfo.animateEyebrows;

        this.mouth.showModel = data.textureInfo.animateMouth;
        this.insideMouth.showModel = data.textureInfo.animateMouth;
        this.upperTeeth.showModel = data.textureInfo.animateMouth;
        this.lowerTeeth.showModel = data.textureInfo.animateMouth;
    }

    public static void resetAngles(ModelRenderer... boxes)
    {
        for(int i = 0; i < boxes.length; i++)
        {
            boxes[i].rotateAngleX = 0.0F;
            boxes[i].rotateAngleY = 0.0F;
            boxes[i].rotateAngleZ = 0.0F;
        }
    }

    public static void multiplyAngles(float scale, ModelRenderer... boxes)
    {
        for(int i = 0; i < boxes.length; i++)
        {
            boxes[i].rotateAngleX *= scale;
            boxes[i].rotateAngleY *= scale;
            boxes[i].rotateAngleZ *= scale;
        }
    }

    public void setAngles(PlayerData data)
    {
        resetAngles(new ModelRenderer[] {this.head, this.headwear, this.chest, this.pelvis});
        resetAngles(new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
        resetAngles(new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
        resetAngles(new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

        int browAdd = 0;
        if(data.textureInfo.animateEyes1)
        {
            browAdd = -1;
        }
        else if((!data.textureInfo.animateEyes2) && (data.textureInfo.animateEyes3))
        {
            browAdd = 1;
        }
        this.pelvis.rotationPointY = 12.8F;
        this.pelvis.rotationPointZ = 0.0F;
        for(int i = 0; i < this.rightEye.length; i++)
        {
            this.rightEye[i].rotationPointX = -1.7F;
            this.leftEye[i].rotationPointX = 1.7F;
        }
        this.brow1.rotationPointY = (-4.6F + browAdd);
        this.brow1.rotateAngleZ = 0.1208305F;
        this.brow2.rotationPointY = (-4.6F + browAdd);
        this.brow2.rotateAngleZ = -0.1208305F;
        this.mouth.rotationPointY = -1.2F;

        this.arm1.rotateAngleX = 0.1F;
        this.arm1.rotateAngleZ = 0.1F;
        this.arm2.rotateAngleX = 0.1F;
        this.arm2.rotateAngleZ = -0.1F;
        this.forearm1.field_78795_f = -0.3F;
        this.forearm2.field_78795_f = -0.3F;
        this.leg1.field_78795_f = -0.15F;
        this.leg1.field_78808_h = 0.08F;
        this.leg2.field_78795_f = -0.15F;
        this.leg2.field_78808_h = -0.08F;
        this.foot1.field_78795_f = 0.25F;
        this.foot1.field_78808_h = -0.04F;
        this.foot2.field_78795_f = 0.25F;
        this.foot2.field_78808_h = 0.04F;
    }

    public void animate(EntityPlayer player, PlayerData data, float f, float f1, float f2, float f3, float f4, float f5)
    {
        float oldF1 = Math.min(1.0F, f1);
        float invF1 = 1.0F - f1;
        float breatheAnim = MathHelper.sin(f2 * 0.1F);
        float faceYaw = f3 * 3.141593F / 180.0F;
        float facePitch = f4 * 3.141593F / 180.0F;
        float f6 = 0.0F;

        this.brow1.rotateAngleZ -= (1.0F - player.getFoodStats().getFoodLevel() / 20.0F) * 3.141593F / 13.0F;
        this.brow2.rotateAngleZ += (1.0F - player.getFoodStats().getFoodLevel() / 20.0F) * 3.141593F / 13.0F;
        float browAnim = data.prevBrowTick + (data.browTick - data.prevBrowTick) * partialTick;
        f1 = browAnim / 8.0F;
        f6 = 1.0F - f1;
        this.brow1.rotationPointY -= f6 * 0.2F;
        this.brow2.rotationPointY -= f6 * 0.2F;
        if(this.brow1.rotateAngleZ > 0.0F)
        {
            this.brow1.rotateAngleZ *= (1.0F - f6 * 0.8F);
        }
        if(this.brow2.rotateAngleZ < 0.0F)
        {
            this.brow2.rotateAngleZ *= (1.0F - f6 * 0.8F);
        }
        float walkSpeed = 0.6F;
        float walkAnim1 = MathHelper.sin(f * walkSpeed);
        float walkAnim2 = -MathHelper.sin(f * walkSpeed + (data.moveBackwards ? -1.4F : 1.4F));
        f1 = oldF1;
        if(!data.isAirborne)
        {
            this.pelvis.rotationPointY += Math.abs(walkAnim1) * 1.4F * f1;
        }
        this.arm1.rotateAngleX -= walkAnim1 * 0.8F * f1;
        this.arm2.rotateAngleX += walkAnim1 * 0.8F * f1;
        this.forearm1.field_78795_f -= Math.max(0.0F, walkAnim1) * 0.5F * f1;
        this.forearm2.field_78795_f += Math.min(0.0F, walkAnim1) * 0.5F * f1;
        this.leg1.field_78795_f += walkAnim1 * 0.9F * f1;
        this.leg2.field_78795_f -= walkAnim1 * 0.9F * f1;
        this.foot1.field_78795_f += (0.5F + walkAnim2 * 0.9F) * f1;
        this.foot2.field_78795_f += (0.5F - walkAnim2 * 0.9F) * f1;

        this.arm1.rotateAngleZ -= f1 * 0.06F;
        this.arm2.rotateAngleZ += f1 * 0.06F;
        this.leg1.field_78808_h -= f1 * 0.05F;
        this.leg2.field_78808_h += f1 * 0.05F;
        if((data.sprintTick > 0) || (data.prevSprintTick > 0))
        {
            int sprintTick = data.sprintTick;
            int prevSprintTick = data.prevSprintTick;
            float sprint = prevSprintTick + (sprintTick - prevSprintTick) * partialTick;
            f6 = 1.0F - sprint / 8.0F;
            if(!data.isAirborne)
            {
                this.pelvis.rotationPointY = (12.0F + (this.pelvis.rotationPointY - 12.0F) * f6);
            }
            multiplyAngles(f6, new ModelRenderer[] {this.chest, this.pelvis});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

            float runSpeed = 0.7F;
            float runAnim1 = MathHelper.sin(f * runSpeed);
            float runAnim2 = -MathHelper.sin(f * runSpeed + 1.4F);
            f1 = sprint / 10.0F;
            if(!data.isAirborne)
            {
                this.pelvis.rotationPointY += Math.abs(runAnim1) * 1.8F * f1;
            }
            this.chest.rotateAngleX += f1 * 0.3F;
            this.head.field_78795_f -= f1 * 0.3F;
            this.arm1.rotateAngleX -= runAnim1 * 1.2F * f1;
            this.arm2.rotateAngleX += runAnim1 * 1.2F * f1;
            this.forearm1.field_78795_f -= Math.max(0.3F, runAnim1) * 1.2F * f1;
            this.forearm2.field_78795_f += Math.min(-0.3F, runAnim1) * 1.2F * f1;
            this.leg1.field_78795_f += runAnim1 * 1.4F * f1;
            this.leg2.field_78795_f -= runAnim1 * 1.4F * f1;
            this.foot1.field_78795_f += (0.9F + runAnim2 * 1.1F) * f1;
            this.foot2.field_78795_f += (0.9F - runAnim2 * 1.1F) * f1;

            this.arm1.rotateAngleZ += f1 * 0.16F;
            this.arm2.rotateAngleZ -= f1 * 0.16F;
            this.leg1.field_78808_h += f1 * 0.03F;
            this.leg2.field_78808_h -= f1 * 0.03F;
        }
        if((data.sneakTick > 0) || (data.prevSneakTick > 0))
        {
            float sneak = data.prevSneakTick + (data.sneakTick - data.prevSneakTick) * partialTick;
            f1 = sneak / 6.0F;
            f6 = 1.0F - f1;

            multiplyAngles(f6, new ModelRenderer[] {this.chest, this.pelvis});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

            this.chest.rotateAngleX += 0.3926991F * f1;
            this.head.field_78795_f -= 0.5235988F * f1;
            this.arm1.rotateAngleX += 0.261799F * f1;
            this.arm1.rotateAngleZ += 0.2243995F * f1;
            this.arm2.rotateAngleX += 0.261799F * f1;
            this.arm2.rotateAngleZ -= 0.2243995F * f1;
            this.forearm1.field_78795_f -= 0.5235988F * f1;
            this.forearm2.field_78795_f -= 0.5235988F * f1;
            this.leg1.getModel().rotateAngleX -= 0.5235988F * f1;
            this.leg1.getModel().rotateAngleZ += 0.2243995F * f1;
            this.leg2.getModel().rotateAngleX -= 0.5235988F * f1;
            this.leg2.getModel().rotateAngleZ -= 0.2243995F * f1;
            this.foot1.field_78795_f += 0.7853982F * f1;
            this.foot1.field_78808_h -= 0.1963496F * f1;
            this.foot2.field_78795_f += 0.7853982F * f1;
            this.foot2.field_78808_h += 0.1963496F * f1;

            float sneakSpeed = 0.6F;
            float sneakAnim1 = MathHelper.sin(f * sneakSpeed);
            float sneakAnim2 = -MathHelper.sin(f * sneakSpeed + (data.moveBackwards2 ? -0.8F : 0.8F));
            this.pelvis.rotationPointY += (-0.5F + Math.abs(sneakAnim1) * 1.4F) * f1;
            this.arm1.rotateAngleX -= sneakAnim1 * 0.8F * f1;
            this.arm2.rotateAngleX += sneakAnim1 * 0.8F * f1;
            this.forearm1.field_78795_f -= Math.max(0.0F, sneakAnim1) * 0.6F * f1;
            this.forearm2.field_78795_f += Math.min(0.0F, sneakAnim1) * 0.6F * f1;
            this.leg1.field_78795_f += (-0.1F + sneakAnim1 * 0.7F) * f1;
            this.leg2.field_78795_f += (-0.1F - sneakAnim1 * 0.7F) * f1;
            this.foot1.getModel().rotateAngleX += (0.4F + sneakAnim2 * 0.6F) * f1;
            this.foot2.getModel().rotateAngleX += (0.4F - sneakAnim2 * 0.6F) * f1;
        }
        if((data.blockTick > 0) || (data.prevBlockTick > 0))
        {
            f1 = data.prevBlockTick + (data.blockTick - data.prevBlockTick) * partialTick;
            f6 = 1.0F - f1;
            float blockF1 = Math.min(0.25F, oldF1);
            multiplyAngles(f6, new ModelRenderer[] {this.chest, this.pelvis, this.head});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});
            this.pelvis.rotationPointY += 1.0F + Math.abs(MathHelper.sin(f * 0.85F) * 0.6F) * blockF1 * f1;
            this.chest.rotateAngleX += 0.261799F * f1;
            this.head.field_78795_f -= 0.261799F * f1;
            this.arm1.rotateAngleX -= 0.3926991F * f1;
            this.arm1.rotateAngleY -= 0.7853982F * f1;
            this.arm1.rotateAngleZ += 0.7853982F * f1;
            this.arm2.rotateAngleZ -= 0.5235988F * f1;
            this.forearm1.field_78795_f -= 0.3141593F * f1;
            this.forearm2.field_78795_f -= 0.5235988F * f1;
            this.leg1.field_78795_f -= 0.3926991F * f1;
            this.leg1.field_78808_h += 0.2243995F * f1;
            this.leg2.field_78795_f -= 0.3926991F * f1;
            this.leg2.field_78808_h -= 0.2243995F * f1;
            this.foot1.field_78808_h -= 0.1745329F * f1;
            this.foot2.field_78808_h += 0.1745329F * f1;
            this.foot1.getModel().rotateAngleX += 0.5235988F * f1;
            this.foot2.getModel().rotateAngleX += 0.5235988F * f1;
            this.leg1.field_78795_f += MathHelper.sin(f * 0.85F) * 2.1F * blockF1 * f1;
            this.leg2.field_78795_f -= MathHelper.sin(f * 0.85F) * 2.1F * blockF1 * f1;
            this.foot1.field_78795_f += (0.5F + MathHelper.sin(f * 0.85F - 1.2F) * 2.1F) * blockF1 * f1;
            this.foot2.field_78795_f += (0.5F - MathHelper.sin(f * 0.85F - 1.2F) * 2.1F) * blockF1 * f1;
        }
        if((data.jumpTick > 0) || (data.prevJumpTick > 0))
        {
            float jumpAnim = data.prevJumpTick + (data.jumpTick - data.prevJumpTick) * partialTick;
            f1 = Math.min(4.0F, jumpAnim) / 5.0F;
            f6 = 1.0F - f1;
            float jumpClamp = jumpAnim / 10.0F;
            float jumpMove = MathHelper.sin(jumpClamp * 3.141593F) * (data.jumpRight ? 1 : -1);

            multiplyAngles(f6, new ModelRenderer[] {this.pelvis, this.head});
            multiplyAngles(f6 * 0.6F, new ModelRenderer[] {this.chest});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

            this.pelvis.rotateAngleX += 0.261799F * f1;
            this.head.field_78795_f -= 0.261799F * f1;
            this.arm1.rotateAngleX += 3.141593F / (4.0F - oldF1 * 2.0F) * jumpMove * f1;
            this.arm1.rotateAngleZ += (0.261799F + MathHelper.sin(f2 * 0.3F - 0.7F) * 0.2F) * f1;
            this.arm2.rotateAngleX += -3.141593F / (4.0F - oldF1 * 2.0F) * jumpMove * f1;
            this.arm2.rotateAngleZ -= (0.261799F + MathHelper.sin(f2 * 0.3F - 1.5F) * 0.2F) * f1;
            this.forearm1.field_78795_f -= 0.5235988F * f1;
            this.forearm2.field_78795_f -= 0.5235988F * f1;
            this.leg1.field_78795_f += (-0.7853982F + -3.141593F / (5.0F - oldF1 * 2.5F) * jumpMove) * f1;
            this.leg1.field_78795_f += MathHelper.sin(f2 * 0.3F) * 0.3F * f1;
            this.leg1.field_78808_h += 0.2243995F * f1;
            this.leg2.field_78795_f += (-0.7853982F + 3.141593F / (5.0F - oldF1 * 2.5F) * jumpMove) * f1;
            this.leg2.field_78795_f += MathHelper.sin(f2 * 0.3F - 1.1F) * 0.3F * f1;
            this.leg2.field_78808_h -= 0.2243995F * f1;
            this.foot1.field_78795_f += 1.256637F * f1;
            this.foot2.field_78795_f += 1.256637F * f1;
        }
        if(((data.swimTick > 0) || (data.prevSwimTick > 0)) && (data.flyingTick <= 0) && (data.prevFlyingTick <= 0))
        {
            float swimAnim = data.prevSwimTick + (data.swimTick - data.prevSwimTick) * partialTick;
            float pitch = data.prevPitch + (data.pitch - data.prevPitch) * partialTick - 1.570796F;
            f1 = swimAnim / 16.0F;
            f6 = 1.0F - f1;

            multiplyAngles(f6, new ModelRenderer[] {this.chest, this.pelvis});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

            float swimF1 = Math.min(1.0F, oldF1 * 2.5F);
            float swimInvF1 = 1.0F - swimF1;
            this.pelvis.rotationPointY += f1 * 6.0F * swimInvF1;
            this.pelvis.rotateAngleX += (0.448799F + 1.2F * swimF1) * f1;
            this.pelvis.rotateAngleX += (0.448799F + 1.2F * swimF1 + swimF1 * pitch) * f1;
            this.head.field_78795_f -= this.pelvis.rotateAngleX * f1;
            this.arm1.rotateAngleX -= (MathHelper.sin(f * 0.5F) * 1.4F + 1.8F) * swimF1 * f1;
            this.arm1.rotateAngleY -= Math.min(0.0F, MathHelper.sin(f * 0.5F + 2.2F) * 1.6F) * swimF1 * f1;
            this.arm1.rotateAngleZ += (0.7853982F + MathHelper.sin(f2 * 0.15F) * 0.2F) * swimInvF1 * f1;
            this.arm2.rotateAngleX -= (MathHelper.sin(f * 0.5F) * 1.4F + 1.8F) * swimF1 * f1;
            this.arm2.rotateAngleY += Math.min(0.0F, MathHelper.sin(f * 0.5F + 2.2F) * 1.6F) * swimF1 * f1;
            this.arm2.rotateAngleZ -= (0.7853982F + MathHelper.sin(f2 * 0.15F) * 0.2F) * swimInvF1 * f1;
            this.forearm1.field_78795_f -= 0.5235988F * swimInvF1 * f1;
            this.forearm2.field_78795_f -= 0.5235988F * swimInvF1 * f1;
            this.leg1.field_78795_f -= (0.3141593F + MathHelper.sin(f2 * 0.3F) * 0.2F * swimInvF1 + MathHelper.sin(f * 1.1F) * 0.8F * swimF1) * f1;
            this.leg1.field_78808_h += (0.3926991F - 0.4F * swimF1) * f1;
            this.leg2.field_78795_f -= (0.3141593F - MathHelper.sin(f2 * 0.3F) * 0.2F * swimInvF1 - MathHelper.sin(f * 1.1F) * 0.8F * swimF1) * f1;
            this.leg2.field_78808_h -= (0.3926991F - 0.4F * swimF1) * f1;
            this.foot1.field_78795_f += 0.5235988F * invF1 * f1;
            this.foot2.field_78795_f += 0.5235988F * invF1 * f1;
        }
        if((data.climbingTick > 0) || (data.prevClimbingTick > 0))
        {
            float climbAnim = data.prevClimbingTick + (data.climbingTick - data.prevClimbingTick) * partialTick;
            f1 = climbAnim / 8.0F;
            f6 = 1.0F - f1;

            multiplyAngles(f6, new ModelRenderer[] {this.chest, this.pelvis});
            multiplyAngles(f6, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(f6, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(f6, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});

            float offset = data.prevClimbingOffset + (data.climbingOffset - data.prevClimbingOffset) * partialTick;
            float climb1 = MathHelper.sin(offset * 0.6F);
            float climb2 = MathHelper.sin(offset * 0.6F + 1.4F);
            this.arm1.rotateAngleX -= 1.396264F * f1;
            this.arm2.rotateAngleX -= 1.396264F * f1;
            this.forearm1.field_78795_f -= 0.6283186F * f1;
            this.forearm2.field_78795_f -= 0.6283186F * f1;
            this.leg1.field_78808_h += 0.09817477F * f1;
            this.leg1.getModel().rotateAngleX -= 1.047198F * f1;
            this.leg2.field_78808_h -= 0.09817477F;
            this.leg2.getModel().rotateAngleX -= 1.047198F * f1;
            this.foot1.field_78795_f += 0.7853982F * f1;
            this.foot2.field_78795_f += 0.7853982F * f1;
            this.arm1.rotateAngleX += climb1 * 0.6F * f1;
            this.arm2.rotateAngleX -= climb1 * 0.6F * f1;
            this.forearm1.field_78795_f += climb2 * 0.6F * f1;
            this.forearm2.field_78795_f -= climb2 * 0.6F * f1;
            this.leg1.field_78795_f -= climb1 * 0.5F * f1;
            this.leg2.field_78795_f += climb1 * 0.5F * f1;
            this.foot1.field_78795_f += (0.4F + climb2) * 0.5F * f1;
            this.foot2.field_78795_f += (0.4F - climb2) * 0.5F * f1;
        }
        if((data.flyingTick > 0) || (data.prevFlyingTick > 0))
        {
            float flyAnim = data.prevFlyingTick + (data.flyingTick - data.prevFlyingTick) * partialTick;
            float pitch = data.prevPitch + (data.pitch - data.prevPitch) * partialTick + 1.570796F;
            float pos = data.posTick + (data.posTick - data.prevPosTick) * partialTick;
            f1 = flyAnim / 5.0F;
            f6 = 1.0F - f1;
            float temp = 1.0F - Math.abs(pos * 0.8F) * f1;
            float temp1 = 1.0F - Math.abs(pos) * f1;
            multiplyAngles(temp, new ModelRenderer[] {this.chest, this.pelvis});
            multiplyAngles(temp, new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            multiplyAngles(temp1, new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            multiplyAngles(temp, new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});
            temp = pos * f1;
            this.pelvis.rotateAngleX += pitch * temp;
            this.head.field_78795_f += -pitch * temp;
            this.arm1.rotateAngleX += 0.5235988F * temp;
            this.arm1.rotateAngleZ += 0.1256637F * temp;
            this.arm2.rotateAngleX += 0.5235988F * temp;
            this.arm2.rotateAngleZ += -0.1256637F * temp;
            this.leg1.field_78808_h += 0.03926991F * temp;
            this.leg2.field_78808_h += -0.03926991F * temp;
        }
        if((data.eatTick > 0) || (data.prevEatTick > 0))
        {
            float eatAnim = data.prevEatTick + (data.eatTick - data.prevEatTick) * partialTick;
            f1 = eatAnim / 8.0F;
            f6 = 1.0F - f1;
            multiplyAngles(f6, new ModelRenderer[] {this.head, this.arm1, this.forearm1});
            facePitch *= f6;
            faceYaw *= f6;
            float eatMove = MathHelper.sin(f2 * 1.1F) * 0.2F;
            this.head.field_78795_f += (0.1963496F + eatMove) * f1 * f1 * f1;
            this.arm1.rotateAngleX -= 0.6283186F * f1;
            this.arm1.rotateAngleY -= 0.6283186F * f1;
            this.forearm1.field_78795_f -= 0.6283186F * f1;
        }
        if((data.drinkTick > 0) || (data.prevDrinkTick > 0))
        {
            float drinkAnim = data.prevDrinkTick + (data.drinkTick - data.prevDrinkTick) * partialTick;
            f1 = drinkAnim / 8.0F;
            f6 = 1.0F - f1;
            multiplyAngles(f6, new ModelRenderer[] {this.head, this.arm1, this.forearm1});
            facePitch *= f6;
            faceYaw *= f6;
            this.head.field_78795_f -= 0.1963496F * f1;
            this.arm1.rotateAngleX -= 0.7853982F * f1;
            this.arm1.rotateAngleY -= 0.5235988F * f1;
            this.forearm1.field_78795_f -= 0.6283186F * f1;
        }
        if(this.isRiding)
        {
            resetAngles(new ModelRenderer[] {this.chest, this.pelvis, this.head});
            resetAngles(new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            resetAngles(new ModelRenderer[] {this.leg1, this.leg2, this.leg1.getModel(), this.leg2.getModel()});
            resetAngles(new ModelRenderer[] {this.foot1, this.foot2, this.foot1.getModel(), this.foot2.getModel()});
            this.pelvis.rotationPointY = 12.0F;
            faceYaw = Math.min(1.57F, faceYaw);
            faceYaw = Math.max(-1.57F, faceYaw);
            if((player.ridingEntity instanceof EntityLiving))
            {
                this.pelvis.rotationPointZ += 4.0F;
                this.chest.rotateAngleX += 0.3141593F;
                this.head.field_78795_f -= 0.3141593F;
                this.arm1.rotateAngleX -= 0.5235988F;
                this.arm2.rotateAngleX -= 0.5235988F;
                this.forearm1.field_78795_f -= 0.448799F;
                this.forearm2.field_78795_f -= 0.448799F;
                this.leg1.field_78795_f -= 1.256637F;
                this.leg1.field_78796_g += 0.5235988F;
                this.leg2.field_78795_f -= 1.256637F;
                this.leg2.field_78796_g -= 0.5235988F;
                this.foot1.field_78795_f += 0.6283186F;
                this.foot2.field_78795_f += 0.6283186F;
            }
            else
            {
                this.pelvis.rotationPointZ += 2.0F;
                this.arm1.rotateAngleX -= 0.6283186F;
                this.arm2.rotateAngleX -= 0.6283186F;
                this.leg1.field_78795_f -= 1.256637F;
                this.leg1.field_78796_g += 0.3141593F;
                this.leg2.field_78795_f -= 1.256637F;
                this.leg2.field_78796_g -= 0.3141593F;
            }
        }
        if(this.field_78118_o > 0)
        {
            player.renderYawOffset = (player.rotationYaw - 40.0F);
            float aimTick = (Items.bow.getMaxItemUseDuration(null) - this.field_78118_o + partialTick) / 22.0F;
            aimTick = Math.min(1.0F, aimTick);
            resetAngles(new ModelRenderer[] {this.arm1, this.arm2, this.forearm1, this.forearm2});
            this.arm1.rotateAngleX += facePitch - 1.5F;
            this.arm1.rotateAngleY += faceYaw - 0.3F;
            this.arm2.rotateAngleX += Math.max(-0.79F, Math.min(0.5F, facePitch)) - 1.5F;
            this.arm2.rotateAngleY += faceYaw + 0.6F - aimTick * 0.8F;
            this.forearm2.field_78808_h += aimTick * 0.8F;
        }
        if(this.onGround > 1.0E-004F)
        {
            resetAngles(new ModelRenderer[] {this.arm1});

            this.arm1.rotateAngleX = 0.1F;
            this.arm1.rotateAngleZ = 0.1F;
            if(!data.holdingSword)
            {
                if(this.onGround < 0.2F)
                {
                    float tick = this.onGround / 0.2F;
                    float rot = MathHelper.sin(tick * 3.141593F / 2.0F);
                    this.chest.rotateAngleY += rot * 3.141593F / 12.0F;
                    this.arm1.rotateAngleX += -rot * 3.141593F / 2.5F;
                    this.arm1.rotateAngleZ += rot * 3.141593F / 10.0F;
                    this.arm2.rotateAngleZ += -rot * 3.141593F / 20.0F;
                }
                else if(this.onGround < 0.5F)
                {
                    float tick = (this.onGround - 0.2F) / 0.3F;
                    float rot = MathHelper.sin(tick * 3.141593F / 2.0F);
                    float neg = 1.0F - rot;
                    this.chest.rotateAngleY += -rot * 3.141593F / 12.0F + neg * 3.141593F / 12.0F;
                    this.arm1.rotateAngleX += rot * 3.141593F / 8.0F - neg * 3.141593F / 2.5F;
                    this.arm1.rotateAngleY += -rot * 3.141593F / 16.0F;
                    this.arm1.rotateAngleZ += rot * 3.141593F / 16.0F - neg * 3.141593F / 10.0F;
                    this.arm2.rotateAngleX += rot * 3.141593F / 14.0F;
                    this.arm2.rotateAngleZ += -0.1570796F;
                }
                else if(this.onGround < 0.6F)
                {
                    this.chest.rotateAngleY += -0.261799F;
                    this.arm1.rotateAngleX += 0.3926991F;
                    this.arm1.rotateAngleY += -0.1963496F;
                    this.arm1.rotateAngleZ += 0.1963496F;
                    this.arm2.rotateAngleX += 0.2243995F;
                    this.arm2.rotateAngleZ += -0.1570796F;
                }
                else if(this.onGround < 0.8F)
                {
                    float tick = (this.onGround - 0.6F) / 0.2F;
                    float rot = MathHelper.cos(tick * 3.141593F / 2.0F);
                    this.chest.rotateAngleY += -rot * 3.141593F / 12.0F;
                    this.arm1.rotateAngleX += rot * 3.141593F / 8.0F;
                    this.arm1.rotateAngleY += -rot * 3.141593F / 16.0F;
                    this.arm1.rotateAngleZ += rot * 3.141593F / 16.0F;
                    this.arm2.rotateAngleX += rot * 3.141593F / 14.0F;
                    this.arm2.rotateAngleZ += -rot * 3.141593F / 20.0F;
                }
            }
            else if(this.onGround < 0.2F)
            {
                float tick = this.onGround / 0.2F;
                float rot = MathHelper.sin(tick * 3.141593F / 2.0F);
                this.chest.rotateAngleY += rot * 3.141593F / 10.0F;
                this.arm1.rotateAngleX += rot * 3.141593F / 8.0F;
                this.arm1.rotateAngleZ += rot * 3.141593F / 6.0F;
                this.arm2.rotateAngleX += -rot * 3.141593F / 12.0F;
                this.arm2.rotateAngleZ += -rot * 3.141593F / 12.0F;
            }
            else if(this.onGround < 0.5F)
            {
                float tick = (this.onGround - 0.2F) / 0.3F;
                float rot = MathHelper.sin(tick * 3.141593F / 2.0F);
                float neg = 1.0F - rot;
                this.chest.rotateAngleY += neg * 3.141593F / 10.0F - rot * 3.141593F / 8.0F;
                this.arm1.rotateAngleX += neg * 3.141593F / 8.0F - rot * 3.141593F / 2.5F;
                this.arm1.rotateAngleZ += neg * 3.141593F / 6.0F + rot * 3.141593F / 2.75F;
                this.arm2.rotateAngleX += -neg * 3.141593F / 12.0F + rot * 3.141593F / 10.0F;
                this.arm2.rotateAngleZ += -0.261799F;
            }
            else if(this.onGround < 0.6F)
            {
                this.chest.rotateAngleY += -0.3926991F;
                this.arm1.rotateAngleX += -1.256637F;
                this.arm1.rotateAngleZ += 1.142397F;
                this.arm2.rotateAngleX += 0.3141593F;
                this.arm2.rotateAngleZ += -0.261799F;
            }
            else if(this.onGround < 0.9F)
            {
                float tick = (this.onGround - 0.6F) / 0.3F;
                float rot = MathHelper.cos(tick * 3.141593F / 2.0F);
                this.chest.rotateAngleY += -rot * 3.141593F / 8.0F;
                this.arm1.rotateAngleX += -rot * 3.141593F / 2.5F;
                this.arm1.rotateAngleZ += rot * 3.141593F / 2.75F;
                this.arm2.rotateAngleX += rot * 3.141593F / 10.0F;
                this.arm2.rotateAngleZ += -rot * 3.141593F / 12.0F;
            }
        }
        this.head.getModel().rotateAngleX = facePitch;
        this.head.getModel().rotateAngleY = faceYaw;
        float rightEye = faceYaw < 0.0F ? 0.6F : 3.0F;
        float leftEye = faceYaw > 0.0F ? 0.6F : 3.0F;
        for(int i = 0; i < this.rightEye.length; i++)
        {
            this.rightEye[i].rotationPointX -= faceYaw * rightEye / 4.712389F;
            this.leftEye[i].rotationPointX -= faceYaw * leftEye / 4.712389F;
        }
        this.head.field_78795_f += breatheAnim * 0.01F;
        if((this.chest instanceof ModelObjRenderer))
        {
            ((ModelObjRenderer)this.chest).setScale(1.0F + breatheAnim * 0.015F);
        }
        this.chest.rotateAngleX -= breatheAnim * 0.02F - 0.02F;
        this.brow1.rotationPointY -= breatheAnim * 0.06F;
        this.brow2.rotationPointY -= breatheAnim * 0.06F;
        this.arm1.rotateAngleZ += breatheAnim * 0.03F + 0.03F;
        this.arm2.rotateAngleZ -= breatheAnim * 0.03F + 0.03F;
        float mouthAnim = data.prevExhaustionTick + (data.exhaustionTick - data.prevExhaustionTick) * partialTick;
        f1 = mouthAnim / 10.0F;
        this.mouth.rotationPointY -= (breatheAnim * 0.25F + 0.25F) * f1;
    }

    public void postRender(float f, ModelRenderer model)
    {
        if(!this.boxList.contains(model))
        {
            throw new IllegalArgumentException("PostRender model is not in box list.");
        }
        this.pelvis.postRender(f);
        if(model == this.head)
        {
            this.chest.postRender(f);
        }
        else if(isOnHead(model))
        {
            this.chest.postRender(f);
            this.head.func_78794_c(f);
        }
        else if(model == this.forearm1)
        {
            this.chest.postRender(f);
            this.arm1.postRender(f);
        }
        else if(model == this.forearm2)
        {
            this.chest.postRender(f);
            this.arm2.postRender(f);
        }
        else if(model == this.foot1)
        {
            this.leg1.func_78794_c(f);
        }
        else if(model == this.foot2)
        {
            this.leg2.func_78794_c(f);
        }
        model.postRender(f);
    }

    private boolean isOnHead(ModelRenderer model)
    {
        if((model == this.headwear) || (model == this.brow1) || (model == this.brow2))
        {
            return true;
        }
        if((model == this.upperLip) || (model == this.lowerLip) || (model == this.insideMouth) || (model == this.mouth) || (model == this.upperTeeth) || (model == this.lowerTeeth))
        {
            return true;
        }
        for(int i = 0; i < this.rightEye.length; i++)
        {
            for(int j = 0; j < this.iris.length; j++)
            {
                if(model == this.iris[j][i])
                {
                    return true;
                }
            }
            if(model == this.rightEye[i])
            {
                return true;
            }
            if(model == this.leftEye[i])
            {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    private void initImprovedHatOld(float f)
    {
        ModelBoxUV box = null;
        for(int x = -4; x < 4; x++)
        {
            for(int y = -8; y < 0; y++)
            {
                for(int z = -4; z < 4; z++)
                {
                    boolean right = x == -4;
                    boolean left = x == 3;
                    boolean front = z == -4;
                    boolean back = z == 3;
                    boolean top = y == -8;
                    boolean bottom = y == -1;
                    if((right) || (left) || (front) || (back) || (top) || (bottom))
                    {
                        float scale = 1.14F;
                        box = ModelBoxUV.addBox(this.headwear, x * scale, (y + 0.5F) * scale, z * scale, 1, 1, 1, f + 0.07F);
                        if(right)
                        {
                            box.setAllUV(36 - z - 1, 16 + y);
                        }
                        if(left)
                        {
                            box.setAllUV(52 + z, 16 + y);
                        }
                        if(front)
                        {
                            box.setAllUV(44 + x, 16 + y);
                        }
                        if(back)
                        {
                            box.setAllUV(60 + x, 16 + y);
                        }
                        if(top)
                        {
                            box.setAllUV(44 + x, 3 - z);
                        }
                        if(bottom)
                        {
                            box.setAllUV(52 + x, 3 - z);
                        }
                        if((right) && (front))
                        {
                            box.setSideUV(0, 36 - z - 1, 16 + y);
                            box.setSideUV(4, 44 + x, 16 + y);
                        }
                        if((right) && (back))
                        {
                            box.setSideUV(0, 36 - z - 1, 16 + y);
                            box.setSideUV(5, 60 + x, 16 + y);
                        }
                        if((right) && (top))
                        {
                            box.setSideUV(0, 36 - z - 1, 16 + y);
                            box.setSideUV(2, 44 + x, 3 - z);
                        }
                        if((right) && (bottom))
                        {
                            box.setSideUV(0, 36 - z - 1, 16 + y);
                            box.setSideUV(3, 52 + x, 3 - z);
                        }
                        if((left) && (front))
                        {
                            box.setSideUV(1, 52 + z, 16 + y);
                            box.setSideUV(4, 44 + x, 16 + y);
                        }
                        if((left) && (back))
                        {
                            box.setSideUV(1, 52 + z, 16 + y);
                            box.setSideUV(5, 60 + x, 16 + y);
                        }
                        if((left) && (top))
                        {
                            box.setSideUV(1, 52 + z, 16 + y);
                            box.setSideUV(2, 44 + x, 3 - z);
                        }
                        if((left) && (bottom))
                        {
                            box.setSideUV(1, 52 + z, 16 + y);
                            box.setSideUV(3, 52 + x, 3 - z);
                        }
                        if((front) && (top))
                        {
                            box.setSideUV(4, 44 + x, 16 + y);
                            box.setSideUV(2, 44 + x, 3 - z);
                        }
                        if((front) && (bottom))
                        {
                            box.setSideUV(4, 44 + x, 16 + y);
                            box.setSideUV(3, 52 + x, 3 - z);
                        }
                        if((back) && (top))
                        {
                            box.setSideUV(5, 60 + x, 16 + y);
                            box.setSideUV(2, 44 + x, 3 - z);
                        }
                        if((back) && (bottom))
                        {
                            box.setSideUV(5, 60 + x, 16 + y);
                            box.setSideUV(3, 52 + x, 3 - z);
                        }
                        box.initQuads();
                    }
                }
            }
        }
    }
}
