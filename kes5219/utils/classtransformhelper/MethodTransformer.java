/*  1:   */ package kes5219.utils.classtransformhelper;
/*  2:   */ 
/*  3:   */ import org.objectweb.asm.ClassVisitor;
/*  4:   */ import org.objectweb.asm.Label;
/*  5:   */ import org.objectweb.asm.MethodVisitor;
/*  6:   */ 
/*  7:   */ public class MethodTransformer
/*  8:   */   extends ClassVisitor
/*  9:   */ {
/* 10:   */   String tgtMethodName;
/* 11:   */   String tgtMethodDesc;
/* 12:   */   String hookMethodOwner;
/* 13:   */   String hookMethodName;
/* 14:   */   boolean isConditionalReturn;
/* 15:   */   CustomMethodTransformer template;
/* 16:   */   
/* 17:   */   MethodTransformer(ClassVisitor cv)
/* 18:   */   {
/* 19:19 */     super(262144, cv);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/* 23:   */   {
/* 24:23 */     if ((name.equals(this.tgtMethodName)) && (desc.startsWith(this.tgtMethodDesc)))
/* 25:   */     {
/* 26:24 */       if (this.template != null)
/* 27:   */       {
/* 28:25 */         MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
/* 29:26 */         this.template.init(mv);
/* 30:27 */         return this.template;
/* 31:   */       }
/* 32:29 */       MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
/* 33:30 */       return new MethodAdaptor(mv);
/* 34:   */     }
/* 35:33 */     return this.cv.visitMethod(access, name, desc, signature, exceptions);
/* 36:   */   }
/* 37:   */   
/* 38:   */   private class MethodAdaptor
/* 39:   */     extends MethodVisitor
/* 40:   */   {
/* 41:   */     public MethodAdaptor(MethodVisitor mv)
/* 42:   */     {
/* 43:39 */       super(mv);
/* 44:   */     }
/* 45:   */     
/* 46:   */     public void visitCode()
/* 47:   */     {
/* 48:44 */       this.mv.visitCode();
/* 49:45 */       if (MethodTransformer.this.isConditionalReturn)
/* 50:   */       {
/* 51:47 */         Label label0 = new Label();
/* 52:48 */         this.mv.visitMethodInsn(184, MethodTransformer.this.hookMethodOwner, MethodTransformer.this.hookMethodName, "()Z");
/* 53:49 */         this.mv.visitJumpInsn(154, label0);
/* 54:50 */         this.mv.visitInsn(177);
/* 55:51 */         this.mv.visitLabel(label0);
/* 56:   */       }
/* 57:   */       else
/* 58:   */       {
/* 59:53 */         this.mv.visitMethodInsn(184, MethodTransformer.this.hookMethodOwner, MethodTransformer.this.hookMethodName, "()V");
/* 60:   */       }
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.MethodTransformer
 * JD-Core Version:    0.7.0.1
 */