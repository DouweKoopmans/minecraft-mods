/*   1:    */ package kes5219.utils.classtransformhelper;
/*   2:    */ 
/*   3:    */ import org.objectweb.asm.ClassReader;
/*   4:    */ import org.objectweb.asm.ClassWriter;
/*   5:    */ 
/*   6:    */ public class ClassTransformHelper
/*   7:    */ {
/*   8:    */   public static byte[] injectSimpleHook(byte[] byteCode, boolean usesConditionalReturn, String tgtMethodName, String tgtMethodDesc, String hookMethodOwner, String hookMethodName)
/*   9:    */   {
/*  10: 23 */     ClassWriter classWriter = new ClassWriter(3);
/*  11: 24 */     MethodTransformer transformer = new MethodTransformer(classWriter);
/*  12:    */     
/*  13: 26 */     transformer.tgtMethodName = tgtMethodName;
/*  14: 27 */     transformer.tgtMethodDesc = tgtMethodDesc;
/*  15: 28 */     transformer.hookMethodOwner = hookMethodOwner;
/*  16: 29 */     transformer.hookMethodName = hookMethodName;
/*  17: 30 */     transformer.isConditionalReturn = usesConditionalReturn;
/*  18: 31 */     transformer.template = null;
/*  19:    */     
/*  20: 33 */     ClassReader classReader = new ClassReader(byteCode);
/*  21: 34 */     classReader.accept(transformer, 0);
/*  22: 35 */     return classWriter.toByteArray();
/*  23:    */   }
/*  24:    */   
/*  25:    */   public static byte[] injectCustomHook(byte[] byteCode, CustomMethodTransformer template, String tgtMethodName, String tgtMethodDesc)
/*  26:    */   {
/*  27: 43 */     return injectCustomHook(byteCode, template, tgtMethodName, tgtMethodDesc, null);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public static byte[] injectCustomHook(byte[] byteCode, CustomMethodTransformer template, String tgtMethodName, String tgtMethodDesc, String superClassName)
/*  31:    */   {
/*  32: 52 */     ClassWriter classWriter = new SuperClassWriter(3, superClassName);
/*  33: 53 */     MethodTransformer transformer = new MethodTransformer(classWriter);
/*  34:    */     
/*  35: 55 */     transformer.tgtMethodName = tgtMethodName;
/*  36: 56 */     transformer.tgtMethodDesc = tgtMethodDesc;
/*  37: 58 */     if (template == null) {
/*  38: 59 */       return byteCode;
/*  39:    */     }
/*  40: 61 */     transformer.template = template;
/*  41:    */     
/*  42:    */ 
/*  43: 64 */     ClassReader classReader = new ClassReader(byteCode);
/*  44: 65 */     classReader.accept(transformer, 0);
/*  45: 66 */     return classWriter.toByteArray();
/*  46:    */   }
/*  47:    */   
/*  48:    */   public static byte[] changeFieldAcess(byte[] byteCode, String fieldName, int fieldAccess)
/*  49:    */   {
/*  50: 73 */     ClassWriter classWriter = new ClassWriter(3);
/*  51: 74 */     FieldTransformer transformer = new FieldTransformer(classWriter);
/*  52:    */     
/*  53: 76 */     transformer.fieldName = fieldName;
/*  54: 77 */     transformer.fieldAccess = fieldAccess;
/*  55:    */     
/*  56: 79 */     ClassReader classReader = new ClassReader(byteCode);
/*  57: 80 */     classReader.accept(transformer, 0);
/*  58: 81 */     return classWriter.toByteArray();
/*  59:    */   }
/*  60:    */   
/*  61:    */   public static byte[] injectSimpleHookAtProfilerSection(byte[] byteCode, String tgtMethodName, String tgtMethodDesc, String hookMethodOwner, String hookMethodName, String profilerSection)
/*  62:    */   {
/*  63: 91 */     ClassWriter classWriter = new ClassWriter(0);
/*  64: 92 */     MethodProfilerTransformer transformer = new MethodProfilerTransformer(classWriter);
/*  65:    */     
/*  66: 94 */     transformer.tgtMethodName = tgtMethodName;
/*  67: 95 */     transformer.tgtMethodDesc = tgtMethodDesc;
/*  68: 96 */     transformer.hookMethodOwner = hookMethodOwner;
/*  69: 97 */     transformer.hookMethodName = hookMethodName;
/*  70: 98 */     transformer.profilerSection = profilerSection;
/*  71: 99 */     transformer.template = null;
/*  72:    */     
/*  73:101 */     ClassReader classReader = new ClassReader(byteCode);
/*  74:102 */     classReader.accept(transformer, 0);
/*  75:103 */     return classWriter.toByteArray();
/*  76:    */   }
/*  77:    */   
/*  78:    */   public static byte[] injectCustomeHookAtProfilerSection(byte[] byteCode, ICustomProfilerSectionHook template, String tgtMethodName, String tgtMethodDesc, String profilerSection)
/*  79:    */   {
/*  80:144 */     ClassWriter classWriter = new ClassWriter(3);
/*  81:145 */     MethodProfilerTransformer transformer = new MethodProfilerTransformer(classWriter);
/*  82:    */     
/*  83:147 */     transformer.tgtMethodName = tgtMethodName;
/*  84:148 */     transformer.tgtMethodDesc = tgtMethodDesc;
/*  85:149 */     transformer.profilerSection = profilerSection;
/*  86:150 */     if (template == null) {
/*  87:151 */       return byteCode;
/*  88:    */     }
/*  89:153 */     transformer.template = template;
/*  90:    */     
/*  91:    */ 
/*  92:156 */     ClassReader classReader = new ClassReader(byteCode);
/*  93:157 */     classReader.accept(transformer, 0);
/*  94:158 */     return classWriter.toByteArray();
/*  95:    */   }
/*  96:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.ClassTransformHelper
 * JD-Core Version:    0.7.0.1
 */