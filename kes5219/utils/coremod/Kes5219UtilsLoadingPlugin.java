/*  1:   */ package kes5219.utils.coremod;
/*  2:   */ 
/*  3:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*  4:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
/*  5:   */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
/*  6:   */ import java.util.Map;
/*  7:   */ 
/*  8:   */ @IFMLLoadingPlugin.TransformerExclusions({"kes5219.utils.coremod"})
/*  9:   */ @IFMLLoadingPlugin.MCVersion("1.7.10")
/* 10:   */ public abstract class Kes5219UtilsLoadingPlugin
/* 11:   */   implements IFMLLoadingPlugin
/* 12:   */ {
/* 13:   */   public String[] getLibraryRequestClass()
/* 14:   */   {
/* 15:15 */     return null;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String[] getASMTransformerClass()
/* 19:   */   {
/* 20:20 */     return new String[] { "kes5219.utils.coremod.ClassTransformer" };
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getModContainerClass()
/* 24:   */   {
/* 25:25 */     return null;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getSetupClass()
/* 29:   */   {
/* 30:30 */     return null;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void injectData(Map<String, Object> data) {}
/* 34:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.coremod.Kes5219UtilsLoadingPlugin
 * JD-Core Version:    0.7.0.1
 */