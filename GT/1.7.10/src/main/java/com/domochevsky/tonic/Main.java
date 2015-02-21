package com.domochevsky.tonic;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid="tonicchevsky", name="GrowthTonic", version="b3")
public class Main
{
  @Mod.Instance("tonicchevsky")
  public static Main instance;
  @SidedProxy(clientSide="com.domochevsky.tonic.ClientProxy", serverSide="com.domochevsky.tonic.CommonProxy")
  public static CommonProxy proxy;
  public static int TONIC_ID;
  
  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    Item tonic = new ItemGrowthTonic();
    GameRegistry.registerItem(tonic, "tonicchevsky_GrowthTonic");
    


    GameRegistry.addShapelessRecipe(new ItemStack(tonic, 1), new Object[] { new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.wheat_seeds), new ItemStack(Items.wheat_seeds), new ItemStack(Items.wheat_seeds) });
  }
}
