package api.player.forge;

import net.minecraft.launchwrapper.*;

import api.player.model.*;

public class RenderPlayerAPIEnhancerTransformer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		for(String canonicalClassNamePattern : RenderPlayerAPIEnhancerPlugin.canonicalClassNamePatterns)
			if(transformedName.matches(canonicalClassNamePattern))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class because it matches the configured canonical class name pattern '%s'", transformedName, canonicalClassNamePattern);
				return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
			}
		for(String canonicalClassName : RenderPlayerAPIEnhancerPlugin.canonicalClassNames)
			if(transformedName.equals(canonicalClassName))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class because it equals the configured canonical class name'%s'", transformedName, canonicalClassName);
				return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
			}
		return bytes;
	}
}