/*  1:   */ package kes5219.improvedfirstperson.classtransformer;
/*  2:   */ 
/*  3:   */ import abw;
/*  4:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*  5:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
/*  6:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
/*  7:   */ import java.util.Map;
/*  8:   */ 
/*  9:   */ @IFMLLoadingPlugin.TransformerExclusions({"kes5219.improvedfirstperson.classtransformer"})
/* 10:   */ @IFMLLoadingPlugin.MCVersion("1.6.4")
/* 11:   */ public class ImprovedFirstPersonLoadingPlugin
/* 12:   */   implements IFMLLoadingPlugin
/* 13:   */ {
/* 14:   */   public String[] getLibraryRequestClass()
/* 15:   */   {
/* 16:17 */     return null;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public String[] getASMTransformerClass()
/* 20:   */   {
/* 21:21 */     boolean isObfuscated = !abw.class.getSimpleName().equals("World");
/* 22:   */     
/* 23:23 */     ObfTable.ClassItemRenderer = isObfuscated ? "bfj" : "net.minecraft.client.renderer.ItemRenderer";
/* 24:24 */     ObfTable.MethodRenderItemInFirstPerson = isObfuscated ? "a" : "renderItemInFirstPerson";
/* 25:   */     
/* 26:26 */     ObfTable.ClassEntityRenderer = isObfuscated ? "bfe" : "net.minecraft.client.renderer.EntityRenderer";
/* 27:27 */     ObfTable.FieldRendererUpdateCount = isObfuscated ? "field_78529_t" : "rendererUpdateCount";
/* 28:28 */     ObfTable.MethodRenderWorld = isObfuscated ? "a" : "renderWorld";
/* 29:29 */     ObfTable.MethodRenderWorldDesc = "(FJ)V";
/* 30:30 */     ObfTable.MethodGetMouseOver = isObfuscated ? "a" : "getMouseOver";
/* 31:31 */     ObfTable.MethodGetMouseOverDesc = "(F)V";
/* 32:32 */     ObfTable.MethodSetupCameraTransform = isObfuscated ? "a" : "setupCameraTransform";
/* 33:33 */     ObfTable.MethodSetupCameraTransformDesc = "(FI)V";
/* 34:   */     
/* 35:35 */     ObfTable.ClassRenderFish = isObfuscated ? "bgq" : "net.minecraft.client.renderer.entity.RenderFish";
/* 36:36 */     ObfTable.MethodDoRenderFishHook = isObfuscated ? "a" : "doRenderFishHook";
/* 37:37 */     ObfTable.MethodDoRenderFishHookDesc = isObfuscated ? "(Lul;DDDFF)V" : "(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V";
/* 38:   */     
/* 39:39 */     ObfTable.ClassRenderGlobal = isObfuscated ? "bfl" : "net.minecraft.client.renderer.RenderGlobal";
/* 40:40 */     ObfTable.MethodRenderEntities = isObfuscated ? "a" : "renderEntities";
/* 41:41 */     ObfTable.MethodRenderEntitiesDesc = isObfuscated ? "(Latc;Lbft;F)V" : "(Lnet/minecraft/util/Vec3;Lnet/minecraft/client/renderer/culling/ICamera;F)V";
/* 42:   */     
/* 43:43 */     ObfTable.ClassItemEditableBook = isObfuscated ? "zo" : "net.minecraft.item.ItemEditableBook";
/* 44:44 */     ObfTable.ClassItemWritableBook = isObfuscated ? "zn" : "net.minecraft.item.ItemWritableBook";
/* 45:45 */     ObfTable.MethodOnItemRightClick = isObfuscated ? "a" : "onItemRightClick";
/* 46:46 */     ObfTable.MethodOnItemRightClickDesc = isObfuscated ? "(Lye;Labw;Luf;)Lye;" : "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";
/* 47:   */     
/* 48:48 */     ObfTable.ClassEntityLivingBase = isObfuscated ? "of" : "net.minecraft.entity.EntityLivingBase";
/* 49:49 */     ObfTable.ClassEntityPlayer = isObfuscated ? "uf" : "net.minecraft.entity.player.EntityPlayer";
/* 50:50 */     ObfTable.MethodOnUpdate = isObfuscated ? "l_" : "onUpdate";
/* 51:51 */     ObfTable.MethodOnUpdateDesc = "()V";
/* 52:52 */     ObfTable.FieldRenderYawOffset = isObfuscated ? "field_70761_aq" : "renderYawOffset";
/* 53:   */     
/* 54:54 */     ObfTable.ClassAnimatedPlayer = "thehippomaster.AnimatedPlayer.AnimatedPlayer";
/* 55:55 */     ObfTable.MethodOnClientTickAnimatedPlayerMod = "onClientTick";
/* 56:56 */     ObfTable.MethodOnClientTickAnimatedPlayerModDesc = "(Lnet/minecraft/world/World;)V";
/* 57:   */     
/* 58:58 */     ObfTable.ClassModelPlayerAnimatedPlayerMod = "thehippomaster.AnimatedPlayer.client.ModelPlayer";
/* 59:59 */     ObfTable.MethodAnimateAnimatedPlayerMod = "animate";
/* 60:60 */     ObfTable.MethodAnimateAnimatedPlayerModDesc = "(Lnet/minecraft/entity/player/EntityPlayer;Lthehippomaster/AnimatedPlayer/PlayerData;FFFFFF)V";
/* 61:   */     
/* 62:62 */     return new String[] { "kes5219.improvedfirstperson.classtransformer.ClassTransformer" };
/* 63:   */   }
/* 64:   */   
/* 65:   */   public String getModContainerClass()
/* 66:   */   {
/* 67:66 */     return null;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public String getSetupClass()
/* 71:   */   {
/* 72:70 */     return null;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public void injectData(Map<String, Object> data) {}
/* 76:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.classtransformer.ImprovedFirstPersonLoadingPlugin
 * JD-Core Version:    0.7.0.1
 */