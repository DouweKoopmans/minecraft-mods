package api.player.model;

import java.io.*;
import java.util.*;

import org.objectweb.asm.*;
import org.apache.logging.log4j.*;

public class ModelPlayerAPIEnhancerClassVisitor extends ClassVisitor
{
	public static byte[] transform(byte[] bytes, boolean isObfuscated)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ModelPlayerAPIEnhancerClassVisitor p = new ModelPlayerAPIEnhancerClassVisitor(cw, isObfuscated, bytes);

			cr.accept(p, 0);

			byte[] result = bytes;
			if (p.enhancableClassName != null)
				result = cw.toByteArray();

			in.close();
			return result;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}

	public ModelPlayerAPIEnhancerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated, byte[] bytes)
	{
		super(262144, classVisitor);
		this.isObfuscated = isObfuscated;
		this.bytes = bytes;
	}

	private final boolean isObfuscated;
	private String enhancableClassName;
	private final byte[] bytes;
	private Set<String> scanned;

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		if((isObfuscated && superName.equals("bhm")) || superName.equals("net/minecraft/client/model/ModelBiped"))
		{
			enhancableClassName = name.replace('/', '.');
			String newSuperName = superName.equals("bhm") ? "api/player/model/ModelPlayer" : "api/player/model/ModelPlayer";
			info("enhances class '%s' to extend class '%s' instead of class '%s'", enhancableClassName, newSuperName.replace('/', '.'), superName.replace('/', '.'));
			superName = newSuperName;
			scanned = ModelPlayerAPIEnhancerClassScanner.scan(bytes);
		}
		else
			info("leaves class '%s' untouched because it extends the unrelated class '%s'", name.replace('/', '.'), superName.replace('/', '.'));

		super.visit(version, access, name, signature, superName, interfaces);
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if(enhancableClassName != null && !name.equals("<cinit>") && !name.equals("<clinit>"))
		{
			if(name.equals("<init>"))
			{
				info("modifies constructor '%s%s'", enhancableClassName, desc);
				return new ModelPlayerAPIEnhancerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated);
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/util/Random;)Lbix;"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'getRandomModelBox' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'getRandomModelBox' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_85181_a") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'getRandomModelBox' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'getRandomModelBox' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("getRandomModelBox") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/lang/String;)Lbiy;"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'getTextureOffset' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'getTextureOffset' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78084_a") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'getTextureOffset' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'getTextureOffset' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("getTextureOffset") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Lsa;FFFFFF)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'render' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'render' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78088_a") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'render' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'render' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("render") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("c") && desc.equals("(F)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'renderCloak' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'renderCloak' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78111_c") && desc.equals("(F)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'renderCloak' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'renderCloak' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("renderCloak") && desc.equals("(F)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("b") && desc.equals("(F)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'renderEars' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'renderEars' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78110_b") && desc.equals("(F)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'renderEars' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'renderEars' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("renderEars") && desc.equals("(F)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Lsv;FFF)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setLivingAnimations' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setLivingAnimations' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78086_a") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setLivingAnimations' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setLivingAnimations' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("setLivingAnimations") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(FFFFFFLsa;)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setRotationAngles' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setRotationAngles' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78087_a") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setRotationAngles' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setRotationAngles' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("setRotationAngles") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/lang/String;II)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setTextureOffset' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setTextureOffset' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
				else if(name.equals("func_78085_a") && desc.equals("(Ljava/lang/String;II)V"))
				{
					if(!scanned.contains(name + "___" + desc))
					{
						info("removes method '%s.%s%s' because it actually is 'setTextureOffset' and doesn't call its super method", enhancableClassName, name, desc);
						return null;
					}
					else
					{
						info("leaves method '%s.%s%s' untouched even though it actually is 'setTextureOffset' because it calls its super method", enhancableClassName, name, desc);
						return super.visitMethod(access, name, desc, signature, exceptions);
					}
				}
			}
			else if(name.equals("setTextureOffset") && desc.equals("(Ljava/lang/String;II)V"))
			{
				if(!scanned.contains(name + "___" + desc))
				{
					info("removes method '%s.%s%s' because doesn't call its super method", enhancableClassName, name, desc);
					return null;
				}
				else
				{
					info("leaves method '%s.%s%s' untouched because it calls its super method", enhancableClassName, name, desc);
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
			}

			info("leaves unknown method '%s.%s%s' untouched", enhancableClassName, name, desc);
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	public static void info(String format, Object... args)
	{
		log(Level.INFO, "Render Player API enhancer " + format, args);
	}

	public static void log(Level level, String format, Object... args)
	{
		LogManager.getLogger("RenderPlayerAPIEnhancer").log(level, String.format(format, args));
	}
}
