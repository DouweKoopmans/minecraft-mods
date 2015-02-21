package kes5219.utils.classtransformhelper;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public abstract class CustomFieldTransformer
  extends ClassVisitor
{
  public CustomFieldTransformer()
  {
    super(262144, null);
  }
  
  final void init(ClassVisitor cv)
  {
    this.cv = cv;
  }
  
  public abstract FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject);
}
