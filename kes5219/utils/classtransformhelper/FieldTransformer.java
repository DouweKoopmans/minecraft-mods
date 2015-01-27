/*  1:   */ package kes5219.utils.classtransformhelper;
/*  2:   */ 
/*  3:   */ import org.objectweb.asm.ClassVisitor;
/*  4:   */ import org.objectweb.asm.FieldVisitor;
/*  5:   */ 
/*  6:   */ public class FieldTransformer
/*  7:   */   extends ClassVisitor
/*  8:   */ {
/*  9:   */   String fieldName;
/* 10:   */   int fieldAccess;
/* 11:   */   
/* 12:   */   FieldTransformer(ClassVisitor cv)
/* 13:   */   {
/* 14:14 */     super(262144, cv);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
/* 18:   */   {
/* 19:19 */     if (name.equals(this.fieldName)) {
/* 20:20 */       return this.cv.visitField(this.fieldAccess, name, desc, signature, value);
/* 21:   */     }
/* 22:22 */     return this.cv.visitField(access, name, desc, signature, value);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.FieldTransformer
 * JD-Core Version:    0.7.0.1
 */