package api.player.model;

import org.objectweb.asm.MethodVisitor;

public class ModelPlayerAPIEnhancerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ModelPlayerAPIEnhancerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(name.equals("<init>") && ((isObfuscated && owner.equals("bhm")) || owner.equals("net/minecraft/client/model/ModelBiped")))
			owner = owner.equals("bhm") ? "api/player/model/ModelPlayer" : "api/player/model/ModelPlayer";
		super.visitMethodInsn(opcode, owner, name, desc);
	}
}
