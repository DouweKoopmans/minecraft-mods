package kes5219.utils.classtransformhelper;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassTransformHelper
{
  public static byte[] injectSimpleHook(byte[] byteCode, boolean usesConditionalReturn, String tgtMethodName, String tgtMethodDesc, String hookMethodOwner, String hookMethodName)
  {
    ClassWriter classWriter = new ClassWriter(3);
    MethodTransformer transformer = new MethodTransformer(classWriter);
    
    transformer.tgtMethodName = tgtMethodName;
    transformer.tgtMethodDesc = tgtMethodDesc;
    transformer.hookMethodOwner = hookMethodOwner;
    transformer.hookMethodName = hookMethodName;
    transformer.isConditionalReturn = usesConditionalReturn;
    transformer.template = null;
    
    ClassReader classReader = new ClassReader(byteCode);
    classReader.accept(transformer, 0);
    return classWriter.toByteArray();
  }
  
  public static byte[] injectCustomHook(byte[] byteCode, CustomMethodTransformer template, String tgtMethodName, String tgtMethodDesc)
  {
    return injectCustomHook(byteCode, template, tgtMethodName, tgtMethodDesc, null);
  }
  
  public static byte[] injectCustomHook(byte[] byteCode, CustomMethodTransformer template, String tgtMethodName, String tgtMethodDesc, String superClassName)
  {
    ClassWriter classWriter = new SuperClassWriter(3, superClassName);
    MethodTransformer transformer = new MethodTransformer(classWriter);
    
    transformer.tgtMethodName = tgtMethodName;
    transformer.tgtMethodDesc = tgtMethodDesc;
    if (template == null) {
      return byteCode;
    }
    transformer.template = template;
    

    ClassReader classReader = new ClassReader(byteCode);
    classReader.accept(transformer, 0);
    return classWriter.toByteArray();
  }
  
  public static byte[] changeFieldAcess(byte[] byteCode, String fieldName, int fieldAccess)
  {
    ClassWriter classWriter = new ClassWriter(3);
    FieldTransformer transformer = new FieldTransformer(classWriter);
    
    transformer.fieldName = fieldName;
    transformer.fieldAccess = fieldAccess;
    
    ClassReader classReader = new ClassReader(byteCode);
    classReader.accept(transformer, 0);
    return classWriter.toByteArray();
  }
  
  public static byte[] injectSimpleHookAtProfilerSection(byte[] byteCode, String tgtMethodName, String tgtMethodDesc, String hookMethodOwner, String hookMethodName, String profilerSection)
  {
    ClassWriter classWriter = new ClassWriter(0);
    MethodProfilerTransformer transformer = new MethodProfilerTransformer(classWriter);
    
    transformer.tgtMethodName = tgtMethodName;
    transformer.tgtMethodDesc = tgtMethodDesc;
    transformer.hookMethodOwner = hookMethodOwner;
    transformer.hookMethodName = hookMethodName;
    transformer.profilerSection = profilerSection;
    transformer.template = null;
    
    ClassReader classReader = new ClassReader(byteCode);
    classReader.accept(transformer, 0);
    return classWriter.toByteArray();
  }
  
  public static byte[] injectCustomeHookAtProfilerSection(byte[] byteCode, ICustomProfilerSectionHook template, String tgtMethodName, String tgtMethodDesc, String profilerSection)
  {
    ClassWriter classWriter = new ClassWriter(3);
    MethodProfilerTransformer transformer = new MethodProfilerTransformer(classWriter);
    
    transformer.tgtMethodName = tgtMethodName;
    transformer.tgtMethodDesc = tgtMethodDesc;
    transformer.profilerSection = profilerSection;
    if (template == null) {
      return byteCode;
    }
    transformer.template = template;
    

    ClassReader classReader = new ClassReader(byteCode);
    classReader.accept(transformer, 0);
    return classWriter.toByteArray();
  }
}
