package kes5219.improvedfirstperson.client;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.MapData;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

public class FirstPersonMapRenderer
  implements IItemRenderer
{
  public static ResourceLocation mapBackLoc = new ResourceLocation("textures/map/map_background.png");
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
      return true;
    }
    return false;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return false;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    Minecraft mc = Minecraft.getMinecraft();
    float f14 = 0.8F;
    GL11.glScalef(-f14, -f14, f14);
    GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(-2.4F, -2.0F, 0.0F);
    float f20 = 0.015625F;
    GL11.glScalef(f20, f20, f20);
    mc.renderEngine.bindTexture(mapBackLoc);
    Tessellator tessellator = Tessellator.instance;
    GL11.glNormal3f(0.0F, 0.0F, -1.0F);
    tessellator.startDrawingQuads();
    byte byte0 = 7;
    tessellator.addVertexWithUV(0 - byte0, 128 + byte0, 0.0D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(128 + byte0, 128 + byte0, 0.0D, 1.0D, 1.0D);
    tessellator.addVertexWithUV(128 + byte0, 0 - byte0, 0.0D, 1.0D, 0.0D);
    tessellator.addVertexWithUV(0 - byte0, 0 - byte0, 0.0D, 0.0D, 0.0D);
    tessellator.draw();
    if (item.getItem() == Items.filled_map)
    {
      MapData mapdata = Items.filled_map.getMapData(item, mc.theWorld);
      /*
      if (mapdata != null) {
        mc.entityRenderer.theMapItemRenderer.renderMap(mc.thePlayer, mc.renderEngine, mapdata);
      }
      */
    }
  }
}
