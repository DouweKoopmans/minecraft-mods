/*  1:   */ package kes5219.improvedfirstperson.client;
/*  2:   */ 
/*  3:   */ import ats;
/*  4:   */ import atv;
/*  5:   */ import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
/*  6:   */ import cpw.mods.fml.common.TickType;
/*  7:   */ import java.util.EnumSet;
/*  8:   */ import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
/*  9:   */ 
/* 10:   */ public class IFPKeyHandler
/* 11:   */   extends KeyBindingRegistry.KeyHandler
/* 12:   */ {
/* 13:19 */   static ats toggle = new ats("Toggle IFP View", 64);
/* 14:   */   
/* 15:   */   public IFPKeyHandler()
/* 16:   */   {
/* 17:23 */     super(new ats[] { toggle }, new boolean[] { false });
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getLabel()
/* 21:   */   {
/* 22:29 */     return "ModImprovedFirstPersonBindings";
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void keyDown(EnumSet<TickType> types, ats kb, boolean tickEnd, boolean isRepeat)
/* 26:   */   {
/* 27:36 */     if ((tickEnd) && (IFPClientProxy.getMC().n == null)) {
/* 28:38 */       if (kb.d == toggle.d) {
/* 29:40 */         ModImprovedFirstPerson.enableBodyRender = !ModImprovedFirstPerson.enableBodyRender;
/* 30:   */       }
/* 31:   */     }
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void keyUp(EnumSet<TickType> types, ats kb, boolean tickEnd) {}
/* 35:   */   
/* 36:   */   public EnumSet<TickType> ticks()
/* 37:   */   {
/* 38:50 */     return EnumSet.of(TickType.CLIENT);
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.client.IFPKeyHandler
 * JD-Core Version:    0.7.0.1
 */