package api.player.model;

import org.objectweb.asm.*;

public final class ModelPlayerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;
	private final boolean createApi;
	private final int parameterOffset;

	public ModelPlayerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated, boolean createApi, int parameterOffset)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
		this.createApi = createApi;
		this.parameterOffset = parameterOffset;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(isObfuscated && name.equals("<init>") && owner.equals("net/minecraft/client/model/ModelBiped"))
			owner = "bhm";
		if(name.equals("<init>") && owner.equals("api/player/model/ModelPlayer"))
		{
			desc = desc.substring(0, desc.indexOf(")")) + "Ljava/lang/String;)V";
			mv.visitVarInsn(Opcodes.ALOAD, parameterOffset);
		}
		super.visitMethodInsn(opcode, owner, name, desc);
		if(createApi && name.equals("<init>") && owner.equals(isObfuscated ? "bhm" : "net/minecraft/client/model/ModelBiped"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.ILOAD, 3);
			mv.visitVarInsn(Opcodes.ILOAD, 4);
			mv.visitVarInsn(Opcodes.ALOAD, 5);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "create", "(Lapi/player/model/IModelPlayerAPI;FFIILjava/lang/String;)Lapi/player/model/ModelPlayerAPI;");
			mv.visitFieldInsn(Opcodes.PUTFIELD, "api/player/model/ModelPlayer", "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;");

			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.FLOAD, 1);
			mv.visitIntInsn(Opcodes.FLOAD, 2);
			mv.visitIntInsn(Opcodes.ILOAD, 3);
			mv.visitIntInsn(Opcodes.ILOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "beforeLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FFII)V");
		}
	}

	public void visitInsn(int opcode)
	{
		if(createApi && opcode == Opcodes.RETURN)
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.FLOAD, 1);
			mv.visitIntInsn(Opcodes.FLOAD, 2);
			mv.visitIntInsn(Opcodes.ILOAD, 3);
			mv.visitIntInsn(Opcodes.ILOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "afterLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FFII)V");
		}
		super.visitInsn(opcode);
	}
}
