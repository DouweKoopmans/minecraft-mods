/*  1:   */ package kes5219.utils.classtransformhelper;
/*  2:   */ 
/*  3:   */ import org.objectweb.asm.ClassVisitor;
/*  4:   */ import org.objectweb.asm.MethodVisitor;
/*  5:   */ 
/*  6:   */ public class MethodProfilerTransformer
/*  7:   */   extends ClassVisitor
/*  8:   */ {
/*  9:   */   String profilerSection;
/* 10:   */   String tgtMethodName;
/* 11:   */   String tgtMethodDesc;
/* 12:   */   String hookMethodOwner;
/* 13:   */   String hookMethodName;
/* 14:   */   ICustomProfilerSectionHook template;
/* 15:   */   
/* 16:   */   public MethodProfilerTransformer(ClassVisitor cv)
/* 17:   */   {
/* 18:17 */     super(262144, cv);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/* 22:   */   {
/* 23:22 */     if ((name.equals(this.tgtMethodName)) && (desc.startsWith(this.tgtMethodDesc)))
/* 24:   */     {
/* 25:23 */       MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
/* 26:24 */       return new MethodProfilerAdaptor(mv);
/* 27:   */     }
/* 28:26 */     return this.cv.visitMethod(access, name, desc, signature, exceptions);
/* 29:   */   }
/* 30:   */   
/* 31:   */   private class MethodProfilerAdaptor
/* 32:   */     extends MethodVisitor
/* 33:   */   {
/* 34:   */     public MethodProfilerAdaptor(MethodVisitor mv)
/* 35:   */     {
/* 36:32 */       super(mv);
/* 37:   */     }
/* 38:   */     
/* 39:   */     public void visitLdcInsn(Object cst)
/* 40:   */     {
/* 41:38 */       if (((cst instanceof String)) && (((String)cst).equals(MethodProfilerTransformer.this.profilerSection))) {
/* 42:39 */         if (MethodProfilerTransformer.this.template != null) {
/* 43:40 */           MethodProfilerTransformer.this.template.onDesignatedSection(this.mv);
/* 44:   */         } else {
/* 45:43 */           this.mv.visitMethodInsn(184, MethodProfilerTransformer.this.hookMethodOwner, MethodProfilerTransformer.this.hookMethodName, "()V");
/* 46:   */         }
/* 47:   */       }
/* 48:46 */       this.mv.visitLdcInsn(cst);
/* 49:   */     }
/* 50:   */   }
/* 51:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.MethodProfilerTransformer
 * JD-Core Version:    0.7.0.1
 */