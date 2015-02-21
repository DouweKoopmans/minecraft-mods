package kes5219.utils.classtransformhelper;

import org.objectweb.asm.MethodVisitor;

public abstract interface ICustomProfilerSectionHook
{
  public abstract void onDesignatedSection(MethodVisitor paramMethodVisitor);
}
