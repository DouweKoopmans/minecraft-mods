package thehippomaster.animatedplayer;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;

public class PlayerData
{
  public int exhaustionTick;
  public int prevExhaustionTick;
  public int exhaustionOffset;
  public int browTick;
  public int prevBrowTick;
  public int sprintTick;
  public int prevSprintTick;
  public int sneakTick;
  public int prevSneakTick;
  public int jumpTick;
  public int prevJumpTick;
  public int swimTick;
  public int prevSwimTick;
  public int blockTick;
  public int prevBlockTick;
  public int climbingTick;
  public int prevClimbingTick;
  public int eatTick;
  public int prevEatTick;
  public int drinkTick;
  public int prevDrinkTick;
  public int flyingTick;
  public int prevFlyingTick;
  public float climbingOffset;
  public float prevClimbingOffset;
  public float pitch;
  public float prevPitch;
  public float posTick;
  public float prevPosTick;
  public boolean moveBackwards;
  public boolean moveBackwards2;
  public boolean isAirborne;
  public boolean jumpRight;
  public boolean holdingSword;
  public TextureInfo textureInfo;
  private static HashMap<EntityPlayer, PlayerData> playerMap = new HashMap();
  
  private PlayerData()
  {
    this.exhaustionTick = (this.prevExhaustionTick = this.exhaustionOffset = 0);
    this.browTick = (this.prevBrowTick = 0);
    this.sprintTick = (this.prevSprintTick = 0);
    this.sneakTick = (this.prevSneakTick = 0);
    this.jumpTick = (this.prevJumpTick = 0);
    this.swimTick = (this.prevSwimTick = 0);
    this.blockTick = (this.prevBlockTick = 0);
    this.climbingTick = (this.prevClimbingTick = 0);
    this.eatTick = (this.prevEatTick = 0);
    this.drinkTick = (this.prevDrinkTick = 0);
    this.flyingTick = (this.prevFlyingTick = 0);
    this.climbingOffset = (this.prevClimbingOffset = 0.0F);
    this.pitch = (this.prevPitch = 0.0F);
    this.posTick = (this.prevPosTick = 0.0F);
    this.moveBackwards = (this.moveBackwards2 = 0);
    this.isAirborne = false;
    this.jumpRight = false;
    this.holdingSword = false;
    this.textureInfo = new TextureInfo();
  }
  
  public void initTextureInfo(AbstractClientPlayer player)
  {
    if (this.textureInfo.init) {
      return;
    }
    if (!AnimatedPlayer.animateFace)
    {
      this.textureInfo.init = true;
      return;
    }
    TextureManager engine = RenderManager.instance.renderEngine;
    boolean fail = false;
    int[] data = null;
    
    ThreadDownloadImageData imgData = player.getTextureSkin();
    BufferedImage img = null;
    boolean textureUploaded = imgData.isTextureUploaded();
    if (imgData != null) {
      img = (BufferedImage)ReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, imgData, AnimatedPlayer.fPlayerImgBuffer);
    }
    if ((imgData == null) || (img == null) || (!textureUploaded))
    {
      fail = true;
    }
    else
    {
      int w = img.getWidth();int h = img.getHeight();
      data = img.getRGB(0, 0, w, h, data, 0, w);
      if (data == null) {
        fail = true;
      }
    }
    if (fail)
    {
      this.textureInfo.setDefault();
      this.textureInfo.initAttempts = Math.max(0, this.textureInfo.initAttempts - 1);
      if (this.textureInfo.initAttempts == 0)
      {
        System.out.println("[AnimatedPlayer] Cannot find texture: " + player.getLocationSkin());
        System.out.println("[AnimatedPlayer] Using default texture.");
        this.textureInfo.defaultTexture = true;
        this.textureInfo.init = true;
      }
      return;
    }
    checkTextureForEyes(1, data);
    checkTextureForEyes(2, data);
    checkTextureForEyes(3, data);
    checkTextureForEyebrows(data);
    checkTextureForMouth(data);
    this.textureInfo.init = true;
  }
  
  private static boolean isSuitableColor(int i)
  {
    return (i != 0) && (i != -16777216) && (i != -1);
  }
  
  private void checkTextureForEyes(int position, int[] data)
  {
    boolean flag = false;
    for (int x = 0; x < 5; x++)
    {
      int i = data[(x + 27 + (position - 1) * 64)];
      if (isSuitableColor(i))
      {
        flag = true;
        break;
      }
    }
    if (position == 1) {
      this.textureInfo.animateEyes1 = flag;
    }
    if (position == 2) {
      this.textureInfo.animateEyes2 = flag;
    }
    if (position == 3) {
      this.textureInfo.animateEyes3 = flag;
    }
  }
  
  private void checkTextureForEyebrows(int[] data)
  {
    boolean flag = false;
    for (int x = 0; x < 3; x++)
    {
      int i = data[(x + 24)];
      if (isSuitableColor(i))
      {
        flag = true;
        break;
      }
    }
    this.textureInfo.animateEyebrows = flag;
  }
  
  private void checkTextureForMouth(int[] data)
  {
    boolean flag = false;
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 2; x++)
      {
        int i = data[(x + 24 + (y + 1) * 64)];
        if (isSuitableColor(i))
        {
          flag = true;
          break;
        }
      }
    }
    this.textureInfo.animateMouth = flag;
  }
  
  public boolean updateExhaustion(EntityPlayer player)
  {
    boolean flag = false;
    boolean jump = false;
    if ((player instanceof EntityPlayerSP))
    {
      EntityPlayerSP playerSP = (EntityPlayerSP)player;
      jump = playerSP.movementInput.jump;
    }
    if ((player.isSprinting()) || (jump) || (player.isInWater()) || (player.hurtTime > 0)) {
      flag = true;
    }
    if ((player.isPotionActive(Potion.moveSlowdown)) || (player.isPotionActive(Potion.hunger))) {
      flag = true;
    }
    if (flag) {
      this.exhaustionOffset = Math.min(160, this.exhaustionOffset + 3);
    } else {
      this.exhaustionOffset = Math.max(0, this.exhaustionOffset - 1);
    }
    if (player.isInWater()) {
      return false;
    }
    return this.exhaustionOffset > 0;
  }
  
  public boolean shouldArchBrows(EntityPlayer player, ItemStack stack)
  {
    if ((player.isSwingInProgress) || (player.isSneaking())) {
      return true;
    }
    if (stack != null)
    {
      if ((stack.getItem() instanceof ItemSword)) {
        return true;
      }
      if ((player.getItemInUseCount() > 0) && (stack.getItemUseAction() == EnumAction.bow)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isAirborne(EntityPlayer player)
  {
    return this.isAirborne = (!player.onGround) && (!player.isInWater()) && (!player.isInsideOfMaterial(Material.lava)) && (!player.isPlayerSleeping()) ? 1 : 0;
  }
  
  public boolean isBlocking(EntityPlayer player, ItemStack stack)
  {
    return (stack != null) && (player.getItemInUseCount() > 0) && (stack.getItemUseAction() == EnumAction.block);
  }
  
  public boolean isEating(EntityPlayer player, ItemStack stack)
  {
    return (stack != null) && (player.getItemInUseCount() > 0) && (stack.getItemUseAction() == EnumAction.eat);
  }
  
  public boolean isDrinking(EntityPlayer player, ItemStack stack)
  {
    return (stack != null) && (player.getItemInUseCount() > 0) && (stack.getItemUseAction() == EnumAction.drink);
  }
  
  public static PlayerData getPlayerData(EntityPlayer player)
  {
    PlayerData data = (PlayerData)playerMap.get(player);
    if (data == null)
    {
      data = new PlayerData();
      playerMap.put(player, data);
    }
    return data;
  }
  
  public static void recycleMap(World world)
  {
    Iterator<EntityPlayer> iterator = playerMap.keySet().iterator();
    ArrayList<EntityPlayer> removeList = new ArrayList();
    while (iterator.hasNext())
    {
      EntityPlayer player = (EntityPlayer)iterator.next();
      if (!world.playerEntities.contains(player)) {
        removeList.add(player);
      }
    }
    playerMap.keySet().removeAll(removeList);
  }
  
  public static class TextureInfo
  {
    private int initAttempts;
    private boolean init;
    public boolean defaultTexture;
    public boolean animateEyes1;
    public boolean animateEyes2;
    public boolean animateEyes3;
    public boolean animateEyebrows;
    public boolean animateMouth;
    
    public TextureInfo()
    {
      this.initAttempts = 10;
      this.init = false;
      this.defaultTexture = false;
      this.animateEyes1 = (this.animateEyes2 = this.animateEyes3 = 0);
      this.animateEyebrows = (this.animateMouth = 0);
    }
    
    public void setDefault()
    {
      this.animateEyes1 = (this.animateEyes3 = 0);
      this.animateEyes2 = true;
      this.animateEyebrows = true;
      this.animateMouth = true;
    }
  }
}
