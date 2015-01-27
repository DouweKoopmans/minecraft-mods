/*  1:   */ package kes5219.utils.classtransformhelper;
/*  2:   */ 
/*  3:   */ import org.objectweb.asm.ClassVisitor;
/*  4:   */ import org.objectweb.asm.FieldVisitor;
/*  5:   */ 
/*  6:   */ public abstract class CustomFieldTransformer
/*  7:   */   extends ClassVisitor
/*  8:   */ {
/*  9:   */   public CustomFieldTransformer()
/* 10:   */   {
/* 11: 9 */     super(262144, null);
/* 12:   */   }
/* 13:   */   
/* 14:   */   final void init(ClassVisitor cv)
/* 15:   */   {
/* 16:13 */     this.cv = cv;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public abstract FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject);
/* 20:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.CustomFieldTransformer
 * JD-Core Version:    0.7.0.1
 */