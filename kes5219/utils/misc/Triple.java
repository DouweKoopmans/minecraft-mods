/*  1:   */ package kes5219.utils.misc;
/*  2:   */ 
/*  3:   */ public class Triple<T1, T2, T3>
/*  4:   */ {
/*  5:   */   public final T1 first;
/*  6:   */   public final T2 second;
/*  7:   */   public final T3 third;
/*  8:   */   
/*  9:   */   public Triple(T1 value1, T2 value2, T3 value3)
/* 10:   */   {
/* 11:11 */     this.first = value1;
/* 12:12 */     this.second = value2;
/* 13:13 */     this.third = value3;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public Triple(T1 value1, T2 value2)
/* 17:   */   {
/* 18:18 */     this.first = value1;
/* 19:19 */     this.second = value2;
/* 20:20 */     this.third = null;
/* 21:   */   }
/* 22:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.misc.Triple
 * JD-Core Version:    0.7.0.1
 */