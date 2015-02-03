package api.player.model;

import java.util.Set;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ModelPlayerAPIEnhancerMethodScanner extends MethodVisitor
{
	private final String name;
	private final String desc;
	private final Set<String> scanned;

	public ModelPlayerAPIEnhancerMethodScanner(MethodVisitor paramMethodVisitor, String name, String desc,  Set<String> scanned)
	{
		super(262144, paramMethodVisitor);
		this.name = name;
		this.desc = desc;
		this.scanned = scanned;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(opcode == Opcodes.INVOKESPECIAL && name.equals(this.name) && desc.equals(this.desc))
			scanned.add(name + "___" + desc);
		super.visitMethodInsn(opcode, owner, name, desc);
	}
}
