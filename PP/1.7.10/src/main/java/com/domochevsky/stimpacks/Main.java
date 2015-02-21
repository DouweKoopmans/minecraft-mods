package com.domochevsky.stimpacks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid="packchevsky", name="Potion Packs", version="b5")
public class Main
{
  @Mod.Instance("packchevsky")
  public static Main instance;
  
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    Item stim = new ItemStimpack();
    GameRegistry.registerItem(stim, "stimchevsky_Potionpack");
    


    GameRegistry.addRecipe(new ItemStack(stim, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8193), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8257), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8225), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8194), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 4), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8258), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 5), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8226), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 6), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8195), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 6), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8227), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 7), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8259), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 8), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8197), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 8), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8261), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 9), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8229), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 10), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8198), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 10), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8230), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 11), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8262), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 12), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8201), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 13), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8265), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 14), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8233), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 15), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8206), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 15), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8238), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 16), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8270), Character.valueOf('y'), new ItemStack(Items.string) });
    
    GameRegistry.addRecipe(new ItemStack(stim, 8, 17), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8205), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 17), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8237), Character.valueOf('y'), new ItemStack(Items.string) });
    GameRegistry.addRecipe(new ItemStack(stim, 8, 18), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.potionitem, 1, 8269), Character.valueOf('y'), new ItemStack(Items.string) });
    


    GameRegistry.addShapelessRecipe(new ItemStack(stim, 1, 19), new Object[] { new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.nether_wart), new ItemStack(Items.paper) });
    



    GameRegistry.addShapelessRecipe(new ItemStack(stim, 1, 20), new Object[] { new ItemStack(Items.glowstone_dust), new ItemStack(stim, 1, 19) });
    


    GameRegistry.addShapelessRecipe(new ItemStack(stim, 1, 21), new Object[] { new ItemStack(Items.redstone), new ItemStack(stim, 1, 19) });
    





    GameRegistry.addShapelessRecipe(new ItemStack(stim, 1, 22), new Object[] { new ItemStack(Items.dye, 1, 4), new ItemStack(stim, 1, 19) });
    



    GameRegistry.addRecipe(new ItemStack(stim, 8, 23), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), new ItemStack(Items.milk_bucket), Character.valueOf('y'), new ItemStack(Items.string) });
  }
}
