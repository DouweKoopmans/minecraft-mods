/*  1:   */ package kes5219.utils.classtransformhelper;
/*  2:   */ 
/*  3:   */ import org.objectweb.asm.ClassWriter;
/*  4:   */ 
/*  5:   */ public class SuperClassWriter
/*  6:   */   extends ClassWriter
/*  7:   */ {
/*  8:   */   String superClassName;
/*  9:   */   
/* 10:   */   public SuperClassWriter(int flags, String superClassName)
/* 11:   */   {
/* 12:11 */     super(flags);
/* 13:   */     
/* 14:13 */     this.superClassName = superClassName;
/* 15:   */   }
/* 16:   */   
/* 17:   */   protected String getCommonSuperClass(String type1, String type2)
/* 18:   */   {
/* 19:   */     String ret;
/* 20:   */     String ret;
/* 21:19 */     if (this.superClassName == null) {
/* 22:20 */       ret = super.getCommonSuperClass(type1, type2);
/* 23:   */     } else {
/* 24:22 */       ret = this.superClassName;
/* 25:   */     }
/* 26:24 */     return ret;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.SuperClassWriter
 * JD-Core Version:    0.7.0.1
 */