/*   1:    */ package kes5219.utils.classtransformhelper;
/*   2:    */ 
/*   3:    */ import org.objectweb.asm.AnnotationVisitor;
/*   4:    */ import org.objectweb.asm.Attribute;
/*   5:    */ import org.objectweb.asm.Handle;
/*   6:    */ import org.objectweb.asm.Label;
/*   7:    */ import org.objectweb.asm.MethodVisitor;
/*   8:    */ 
/*   9:    */ public abstract class CustomMethodTransformer
/*  10:    */   extends MethodVisitor
/*  11:    */ {
/*  12:    */   protected CustomMethodTransformer()
/*  13:    */   {
/*  14: 13 */     super(262144, null);
/*  15:    */   }
/*  16:    */   
/*  17:    */   final void init(MethodVisitor mv)
/*  18:    */   {
/*  19: 17 */     this.mv = mv;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public AnnotationVisitor visitAnnotation(String desc, boolean visible)
/*  23:    */   {
/*  24: 22 */     return super.visitAnnotation(desc, visible);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public AnnotationVisitor visitAnnotationDefault()
/*  28:    */   {
/*  29: 27 */     return super.visitAnnotationDefault();
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void visitAttribute(Attribute attr)
/*  33:    */   {
/*  34: 32 */     super.visitAttribute(attr);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void visitCode()
/*  38:    */   {
/*  39: 37 */     super.visitCode();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void visitEnd()
/*  43:    */   {
/*  44: 42 */     super.visitEnd();
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void visitFieldInsn(int opcode, String owner, String name, String desc)
/*  48:    */   {
/*  49: 47 */     super.visitFieldInsn(opcode, owner, name, desc);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack)
/*  53:    */   {
/*  54: 52 */     super.visitFrame(type, nLocal, local, nStack, stack);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void visitIincInsn(int var, int increment)
/*  58:    */   {
/*  59: 57 */     super.visitIincInsn(var, increment);
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void visitInsn(int opcode)
/*  63:    */   {
/*  64: 62 */     super.visitInsn(opcode);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void visitIntInsn(int opcode, int operand)
/*  68:    */   {
/*  69: 67 */     super.visitIntInsn(opcode, operand);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs)
/*  73:    */   {
/*  74: 72 */     super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void visitJumpInsn(int opcode, Label label)
/*  78:    */   {
/*  79: 77 */     super.visitJumpInsn(opcode, label);
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void visitLabel(Label label)
/*  83:    */   {
/*  84: 82 */     super.visitLabel(label);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void visitLdcInsn(Object cst)
/*  88:    */   {
/*  89: 87 */     super.visitLdcInsn(cst);
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void visitLineNumber(int line, Label start)
/*  93:    */   {
/*  94: 92 */     super.visitLineNumber(line, start);
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index)
/*  98:    */   {
/*  99: 97 */     super.visitLocalVariable(name, desc, signature, start, end, index);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels)
/* 103:    */   {
/* 104:102 */     super.visitLookupSwitchInsn(dflt, keys, labels);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void visitMaxs(int maxStack, int maxLocals)
/* 108:    */   {
/* 109:107 */     super.visitMaxs(maxStack, maxLocals);
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void visitMethodInsn(int opcode, String owner, String name, String desc)
/* 113:    */   {
/* 114:112 */     super.visitMethodInsn(opcode, owner, name, desc);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void visitMultiANewArrayInsn(String desc, int dims)
/* 118:    */   {
/* 119:117 */     super.visitMultiANewArrayInsn(desc, dims);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible)
/* 123:    */   {
/* 124:122 */     return super.visitParameterAnnotation(parameter, desc, visible);
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels)
/* 128:    */   {
/* 129:127 */     super.visitTableSwitchInsn(min, max, dflt, labels);
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type)
/* 133:    */   {
/* 134:132 */     super.visitTryCatchBlock(start, end, handler, type);
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void visitTypeInsn(int opcode, String type)
/* 138:    */   {
/* 139:137 */     super.visitTypeInsn(opcode, type);
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void visitVarInsn(int opcode, int var)
/* 143:    */   {
/* 144:142 */     super.visitVarInsn(opcode, var);
/* 145:    */   }
/* 146:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.CustomMethodTransformer
 * JD-Core Version:    0.7.0.1
 */