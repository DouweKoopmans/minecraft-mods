package api.player.model;

import java.io.*;
import java.util.*;

import org.objectweb.asm.*;

public class ModelPlayerAPIEnhancerClassScanner extends ClassVisitor
{
	public static Set<String> scan(byte[] bytes)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ModelPlayerAPIEnhancerClassScanner p = new ModelPlayerAPIEnhancerClassScanner(cw);

			cr.accept(p, 0);
			return p.scanned;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}

	public ModelPlayerAPIEnhancerClassScanner(ClassVisitor classVisitor)
	{
		super(262144, classVisitor);
		this.scanned = new HashSet<String>();
	}

	private final Set<String> scanned;

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
		if((access & Opcodes.ACC_STATIC) == 0 && !name.equals("<init>"))
			visitor = new ModelPlayerAPIEnhancerMethodScanner(visitor, name, desc, scanned);
		return visitor;
	}
}
