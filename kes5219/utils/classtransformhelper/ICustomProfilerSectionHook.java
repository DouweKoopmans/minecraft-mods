package kes5219.utils.classtransformhelper;

import org.objectweb.asm.MethodVisitor;

public interface ICustomProfilerSectionHook {
    public abstract void onDesignatedSection(MethodVisitor mv);
}