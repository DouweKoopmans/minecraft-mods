/*  1:   */ package kes5219.utils.misc;
/*  2:   */ 
/*  3:   */ import atv;
/*  4:   */ import cpw.mods.fml.common.ITickHandler;
/*  5:   */ import cpw.mods.fml.common.TickType;
/*  6:   */ import java.util.EnumSet;
/*  7:   */ 
/*  8:   */ public class PartialTickRetriever
/*  9:   */   implements ITickHandler
/* 10:   */ {
/* 11:   */   private static float partialTick;
/* 12:   */   
/* 13:   */   public void tickStart(EnumSet<TickType> type, Object... tickData)
/* 14:   */   {
/* 15:16 */     if ((type.contains(TickType.RENDER)) && 
/* 16:17 */       (atv.w().f != null)) {
/* 17:18 */       partialTick = ((Float)tickData[0]).floatValue();
/* 18:   */     }
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static float getPartialTick()
/* 22:   */   {
/* 23:25 */     return partialTick;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void tickEnd(EnumSet<TickType> type, Object... tickData) {}
/* 27:   */   
/* 28:   */   public EnumSet<TickType> ticks()
/* 29:   */   {
/* 30:31 */     return EnumSet.of(TickType.RENDER);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getLabel()
/* 34:   */   {
/* 35:35 */     return null;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.misc.PartialTickRetriever
 * JD-Core Version:    0.7.0.1
 */