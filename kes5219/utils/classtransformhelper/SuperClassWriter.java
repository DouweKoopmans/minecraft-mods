package kes5219.utils.classtransformhelper;

import org.objectweb.asm.ClassWriter;

public class SuperClassWriter extends ClassWriter
{
    String superClassName;

    public SuperClassWriter(int flags, String superClassName)
    {
        super(flags);

        this.superClassName = superClassName;
    }

    protected String getCommonSuperClass(String type1, String type2)
    {

        String ret;
        if(this.superClassName == null)
        {
            ret = super.getCommonSuperClass(type1, type2);
        }
        else
        {
            ret = this.superClassName;
        }
        return ret;
    }
}
