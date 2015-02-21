package com.domochevsky.stimpacks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemStimpack
  extends Item
{
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Regen;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Swift;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_FireRes;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Heal;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Night;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Strength;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Invis;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Water;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Miner;
  @SideOnly(Side.CLIENT)
  private IIcon StimIcon_Antidote;
  
  public ItemStimpack()
  {
    setMaxStackSize(8);
    setMaxDamage(30);
    setCreativeTab(CreativeTabs.tabBrewing);
    setUnlocalizedName("Potion Pack - Undefined");
    setHasSubtypes(true);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IconRegister)
  {
    this.StimIcon_Regen = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Regen");
    this.StimIcon_Swift = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Swift");
    this.StimIcon_FireRes = par1IconRegister.registerIcon("packchevsky:ItemStimpack_FireRes");
    this.StimIcon_Heal = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Heal");
    this.StimIcon_Night = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Night");
    this.StimIcon_Strength = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Strength");
    this.StimIcon_Invis = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Invis");
    this.StimIcon_Water = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Water");
    this.StimIcon_Miner = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Miner");
    this.StimIcon_Antidote = par1IconRegister.registerIcon("packchevsky:ItemStimpack_Antidote");
  }
  
  public IIcon getIconFromDamage(int dmg)
  {
    if ((dmg == 0) || (dmg == 1) || (dmg == 2)) {
      return this.StimIcon_Regen;
    }
    if ((dmg == 3) || (dmg == 4) || (dmg == 5)) {
      return this.StimIcon_Swift;
    }
    if ((dmg == 6) || (dmg == 7)) {
      return this.StimIcon_FireRes;
    }
    if ((dmg == 8) || (dmg == 9)) {
      return this.StimIcon_Heal;
    }
    if ((dmg == 10) || (dmg == 11)) {
      return this.StimIcon_Night;
    }
    if ((dmg == 12) || (dmg == 13) || (dmg == 14)) {
      return this.StimIcon_Strength;
    }
    if ((dmg == 15) || (dmg == 16)) {
      return this.StimIcon_Invis;
    }
    if ((dmg == 17) || (dmg == 18)) {
      return this.StimIcon_Water;
    }
    if ((dmg == 19) || (dmg == 20) || (dmg == 21) || (dmg == 22)) {
      return this.StimIcon_Miner;
    }
    if (dmg == 23) {
      return this.StimIcon_Antidote;
    }
    return this.StimIcon_Regen;
  }
  
  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
  {
    super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    if (getDamage(par1ItemStack) == 0)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Regeneration I (0:45)");
    }
    else if (getDamage(par1ItemStack) == 1)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Regeneration I (2:00)");
    }
    else if (getDamage(par1ItemStack) == 2)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Regeneration II (0:22)");
    }
    else if (getDamage(par1ItemStack) == 3)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Swiftness I (3:00) / +20% SPEED");
      par3List.add("Run faster.");
    }
    else if (getDamage(par1ItemStack) == 4)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Swiftness I (8:00) / +20% SPEED");
      par3List.add("Run faster.");
    }
    else if (getDamage(par1ItemStack) == 5)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Swiftness II (1:30) / +40% SPEED");
      par3List.add("Run faster.");
    }
    else if (getDamage(par1ItemStack) == 6)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Fire Resistance I (3:00)");
    }
    else if (getDamage(par1ItemStack) == 7)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Fire Resistance I (8:00)");
    }
    else if (getDamage(par1ItemStack) == 8)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Healing I (2 Hearts)");
    }
    else if (getDamage(par1ItemStack) == 9)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Healing II (4 Hearts)");
    }
    else if (getDamage(par1ItemStack) == 10)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Night Vision I (3:00)");
    }
    else if (getDamage(par1ItemStack) == 11)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Night Vision II (8:00)");
    }
    else if (getDamage(par1ItemStack) == 12)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Strength I (3:00) / +30% DMG");
    }
    else if (getDamage(par1ItemStack) == 13)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Strength I (8:00) / +30% DMG");
    }
    else if (getDamage(par1ItemStack) == 14)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Strength II (1:30) / +60% DMG");
    }
    else if (getDamage(par1ItemStack) == 15)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Invisibility I (3:00)");
    }
    else if (getDamage(par1ItemStack) == 16)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Invisibility II (8:00)");
    }
    else if (getDamage(par1ItemStack) == 17)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Water Breathing I (3:00)");
    }
    else if (getDamage(par1ItemStack) == 18)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Water Breathing II (8:00)");
    }
    else if (getDamage(par1ItemStack) == 19)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Haste I (3:00) / +20% SPEED");
      par3List.add("Dig, chop and mine faster.");
    }
    else if (getDamage(par1ItemStack) == 20)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Haste I (8:00) / +20% SPEED");
      par3List.add("Dig, chop and mine faster.");
    }
    else if (getDamage(par1ItemStack) == 21)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Haste II (1:30) / +40% SPEED");
      par3List.add("Dig, chop and mine faster.");
    }
    else if (getDamage(par1ItemStack) == 22)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Haste III (3:00) / +60% SPEED");
      par3List.add("Dig, chop and mine faster.");
    }
    else if (getDamage(par1ItemStack) == 23)
    {
      par3List.add("A tied together pack of potions.");
      par3List.add("Milk. It's a good antidote.");
    }
  }
  
  public String getItemStackDisplayName(ItemStack par1ItemStack)
  {
    if (par1ItemStack.getItemDamage() == 0) {
      return "Pack of Regeneration";
    }
    if (par1ItemStack.getItemDamage() == 1) {
      return "Pack of Extended Regeneration";
    }
    if (par1ItemStack.getItemDamage() == 2) {
      return "Pack of Empowered Regeneration";
    }
    if (par1ItemStack.getItemDamage() == 3) {
      return "Pack of Swiftness";
    }
    if (par1ItemStack.getItemDamage() == 4) {
      return "Pack of Extended Swiftness";
    }
    if (par1ItemStack.getItemDamage() == 5) {
      return "Pack of Empowered Swiftness";
    }
    if (par1ItemStack.getItemDamage() == 6) {
      return "Pack of Fire Resistance";
    }
    if (par1ItemStack.getItemDamage() == 7) {
      return "Pack of Extended Fire Resistance";
    }
    if (par1ItemStack.getItemDamage() == 8) {
      return "Pack of Healing";
    }
    if (par1ItemStack.getItemDamage() == 9) {
      return "Pack of Empowered Healing";
    }
    if (par1ItemStack.getItemDamage() == 10) {
      return "Pack of Night Vision";
    }
    if (par1ItemStack.getItemDamage() == 11) {
      return "Pack of Extended Night Vision";
    }
    if (par1ItemStack.getItemDamage() == 12) {
      return "Pack of Strength";
    }
    if (par1ItemStack.getItemDamage() == 13) {
      return "Pack of Extended Strength";
    }
    if (par1ItemStack.getItemDamage() == 14) {
      return "Pack of Empowered Strength";
    }
    if (par1ItemStack.getItemDamage() == 15) {
      return "Pack of Invisibility";
    }
    if (par1ItemStack.getItemDamage() == 16) {
      return "Pack of Extended Invisibility";
    }
    if (par1ItemStack.getItemDamage() == 17) {
      return "Pack of Water Breathing";
    }
    if (par1ItemStack.getItemDamage() == 18) {
      return "Pack of Extended Water Breathing";
    }
    if (par1ItemStack.getItemDamage() == 19) {
      return "Pack of Haste";
    }
    if (par1ItemStack.getItemDamage() == 20) {
      return "Pack of Extended Haste";
    }
    if (par1ItemStack.getItemDamage() == 21) {
      return "Pack of Empowered Haste";
    }
    if (par1ItemStack.getItemDamage() == 22) {
      return "Pack of High Power Haste";
    }
    if (par1ItemStack.getItemDamage() == 23) {
      return "Pack of Milk";
    }
    return "Undefined Potion Pack";
  }
  
  public void getSubItems(Item stack, CreativeTabs par2CreativeTabs, List par3List)
  {
    par3List.add(new ItemStack(stack, 1, 0));
    par3List.add(new ItemStack(stack, 1, 1));
    par3List.add(new ItemStack(stack, 1, 2));
    par3List.add(new ItemStack(stack, 1, 3));
    par3List.add(new ItemStack(stack, 1, 4));
    par3List.add(new ItemStack(stack, 1, 5));
    par3List.add(new ItemStack(stack, 1, 6));
    par3List.add(new ItemStack(stack, 1, 7));
    par3List.add(new ItemStack(stack, 1, 8));
    par3List.add(new ItemStack(stack, 1, 9));
    par3List.add(new ItemStack(stack, 1, 10));
    par3List.add(new ItemStack(stack, 1, 11));
    par3List.add(new ItemStack(stack, 1, 12));
    par3List.add(new ItemStack(stack, 1, 13));
    par3List.add(new ItemStack(stack, 1, 14));
    par3List.add(new ItemStack(stack, 1, 15));
    par3List.add(new ItemStack(stack, 1, 16));
    par3List.add(new ItemStack(stack, 1, 17));
    par3List.add(new ItemStack(stack, 1, 18));
    par3List.add(new ItemStack(stack, 1, 19));
    par3List.add(new ItemStack(stack, 1, 20));
    par3List.add(new ItemStack(stack, 1, 21));
    par3List.add(new ItemStack(stack, 1, 22));
    par3List.add(new ItemStack(stack, 1, 23));
  }
  
  public int getDisplayDamage(ItemStack stack)
  {
    return 0;
  }
  
  public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
    return par1ItemStack;
  }
  
  public EnumAction getItemUseAction(ItemStack par1ItemStack)
  {
    return EnumAction.drink;
  }
  
  public int getMaxItemUseDuration(ItemStack par1ItemStack)
  {
    return 32;
  }
  
  public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    if (!par2World.isRemote) {
      if (par1ItemStack.getItemDamage() == 0)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(10, 900, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 1)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(10, 2400, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 2)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(10, 450, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 3)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(1, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 4)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(1, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 5)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(1, 1800, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 6)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(12, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 7)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(12, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 8)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(6, 1, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 9)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(6, 1, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 10)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(16, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 11)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(16, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 12)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(5, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 13)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(5, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 14)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(5, 1800, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 15)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(14, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 16)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(14, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 17)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(13, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 18)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(13, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 19)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(3, 3600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 20)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(3, 9600, 0, false));
      }
      else if (par1ItemStack.getItemDamage() == 21)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(3, 1800, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 22)
      {
        par3EntityPlayer.addPotionEffect(new PotionEffect(3, 3600, 2, false));
        par3EntityPlayer.addPotionEffect(new PotionEffect(17, 3600, 1, false));
      }
      else if (par1ItemStack.getItemDamage() == 23)
      {
        ItemStack milk = new ItemStack(Items.milk_bucket);
        par3EntityPlayer.curePotionEffects(milk);
      }
    }
    if (!par3EntityPlayer.capabilities.isCreativeMode)
    {
      par1ItemStack.stackSize -= 1;
      if (par1ItemStack.getItemDamage() != 23)
      {
        boolean invsuccess = par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        if (!invsuccess) {
          if (!par2World.isRemote) {
            par2World.spawnEntityInWorld(new EntityItem(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, new ItemStack(Items.glass_bottle)));
          }
        }
      }
    }
    return par1ItemStack;
  }
}
