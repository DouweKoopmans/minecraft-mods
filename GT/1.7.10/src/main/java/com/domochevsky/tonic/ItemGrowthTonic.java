package com.domochevsky.tonic;

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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemGrowthTonic
  extends Item
{
  @SideOnly(Side.CLIENT)
  private IIcon TonicIcon;
  
  public ItemGrowthTonic()
  {
    setMaxStackSize(16);
    setCreativeTab(CreativeTabs.tabBrewing);
    setUnlocalizedName("Growth Tonic");
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IconRegister)
  {
    this.TonicIcon = par1IconRegister.registerIcon("tonicchevsky:ItemGrowthTonic");
  }
  
  public IIcon getIconFromDamage(int par1)
  {
    return this.TonicIcon;
  }
  
  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
  {
    super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    par3List.add("Causes dirt to grow into");
    par3List.add("grass via FORMULA 6.");
    par3List.add("Patent pending. Do not ingest.");
  }
  
  public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float faceX, float faceY, float faceZ)
  {
    if (!par3World.isRemote)
    {
      if (par3World.getBlock(x, y, z) == Blocks.dirt) {
        par3World.setBlock(x, y, z, Blocks.grass);
      } else if (par3World.getBlock(x, y, z) == Blocks.grass) {
        if (par3World.isAirBlock(x, y + 1, z))
        {
          par3World.setBlock(x, y + 1, z, Blocks.tallgrass);
          par3World.setBlockMetadataWithNotify(x, y + 1, z, 1, 3);
        }
      }
      par3World.playSoundAtEntity(par2EntityPlayer, "random.splash", 1.0F, 3.0F);
      par1ItemStack.stackSize -= 1;
    }
    if (par3World.isRemote)
    {
      par3World.spawnParticle("splash", x + 0.5F, y + 1.0F, z + 0.5F, 0.0D, 0.0D, 0.0D);
      par3World.spawnParticle("witchMagic", x + 0.5F, y + 1.0F, z + 0.5F, 0.0D, 0.0D, 0.0D);
      par3World.spawnParticle("slime", x + 0.5F, y + 1.0F, z + 0.5F, 0.0D, 0.0D, 0.0D);
    }
    if (!par2EntityPlayer.capabilities.isCreativeMode)
    {
      boolean invsuccess = par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
      if (!invsuccess) {
        if (!par3World.isRemote) {
          par3World.spawnEntityInWorld(new EntityItem(par3World, par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ, new ItemStack(Items.glass_bottle)));
        }
      }
    }
    return true;
  }
  
  public String getItemStackDisplayName(ItemStack par1ItemStack)
  {
    return "Growth Tonic";
  }
}
