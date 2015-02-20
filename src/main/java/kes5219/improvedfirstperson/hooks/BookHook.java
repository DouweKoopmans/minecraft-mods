package kes5219.improvedfirstperson.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BookHook
{
  public static boolean cancelOpeningGUI()
  {
    return false;
  }
  
  public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {}
}
