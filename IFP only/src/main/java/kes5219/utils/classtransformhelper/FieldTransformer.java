package kes5219.utils.classtransformhelper;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class FieldTransformer
  extends ClassVisitor
{
  String fieldName;
  int fieldAccess;
  
  FieldTransformer(ClassVisitor cv)
  {
    super(262144, cv);
  }
  
  public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
  {
    if (name.equals(this.fieldName)) {
      return this.cv.visitField(this.fieldAccess, name, desc, signature, value);
    }
    return this.cv.visitField(access, name, desc, signature, value);
  }
}
