package kes5219.utils.classtransformhelper;

import org.objectweb.asm.MethodVisitor;

public abstract interface ICustomProfilerSectionHook
{
  public abstract void onDesignatedSection(MethodVisitor paramMethodVisitor);
}


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.classtransformhelper.ICustomProfilerSectionHook
 * JD-Core Version:    0.7.0.1
 */