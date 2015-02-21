package kes5219.utils.coremod;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer
  implements IClassTransformer
{
  public byte[] transform(String name, String transformedName, byte[] bytes)
  {
    return bytes;
  }
}
