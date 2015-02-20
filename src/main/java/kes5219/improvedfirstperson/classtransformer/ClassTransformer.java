package kes5219.improvedfirstperson.classtransformer;

import java.io.PrintStream;
import kes5219.utils.classtransformhelper.ClassTransformHelper;
import kes5219.utils.classtransformhelper.CustomMethodTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class ClassTransformer
  implements IClassTransformer
{
  public byte[] transform(String name, String transformedName, byte[] bytes)
  {
    if (name.equals(ObfTable.ClassItemRenderer))
    {
      bytes = ClassTransformHelper.injectSimpleHook(bytes, true, ObfTable.MethodRenderItemInFirstPerson, "(F)V", "kes5219/improvedfirstperson/hooks/ItemRendererHook", "shouldRenderItemInFirstPerson");
      System.out.println("Improved First Person Mod: Successfully modified renderItemInFirstPerson in ItemRenderer");
    }
    else if (name.equals(ObfTable.ClassEntityRenderer))
    {
      bytes = ClassTransformHelper.changeFieldAcess(bytes, ObfTable.FieldRendererUpdateCount, 1);
      System.out.println("Improved First Person Mod: Successfully changed the field rendererUpdateCount to public in EntityRenderer");
      
      bytes = ClassTransformHelper.injectCustomHook(bytes, new AfterCameraTransformTransformer(), ObfTable.MethodSetupCameraTransform, ObfTable.MethodSetupCameraTransformDesc);
      System.out.println("Improved First Person Mod: Successfully injected hook into setupCameraTransform in EntityRenderer");
      
      bytes = ClassTransformHelper.injectCustomHook(bytes, new MethodGetMouseOverTransformer(), ObfTable.MethodGetMouseOver, ObfTable.MethodGetMouseOverDesc);
      System.out.println("Improved First Person Mod: Successfully injected hook into getMouseOver in EntityRenderer");
    }
    else if (name.equals(ObfTable.ClassRenderFish))
    {
      bytes = ClassTransformHelper.injectCustomHook(bytes, new MethodDoRenderFishHookTransformer(), ObfTable.MethodDoRenderFishHook, ObfTable.MethodDoRenderFishHookDesc);
      System.out.println("Improved First Person Mod: Successfully modified doRenderFishHook in RenderFish");
    }
    else if (name.equals(ObfTable.ClassRenderGlobal))
    {
      bytes = ClassTransformHelper.injectSimpleHookAtProfilerSection(bytes, ObfTable.MethodRenderEntities, ObfTable.MethodRenderEntitiesDesc, "kes5219/improvedfirstperson/hooks/RenderEntityHook", "onRenderEntities", "tileentities");
      System.out.println("Improved First Person Mod: Successfully modified renderEntities in RenderGlobal");
    }
    else if ((name.equals(ObfTable.ClassItemEditableBook)) || (name.equals(ObfTable.ClassItemWritableBook)))
    {
      bytes = ClassTransformHelper.injectCustomHook(bytes, new MethodOnItemRightClickTransformer(), ObfTable.MethodOnItemRightClick, ObfTable.MethodOnItemRightClickDesc);
      
      System.out.println("Improved First Person Mod: Successfully modified onItemRightClick in " + (name.equals(ObfTable.ClassItemEditableBook) ? "ItemEditableBook" : "ItemWritableBook"));
    }
    else if (name.equals(ObfTable.ClassEntityLivingBase))
    {
      bytes = ClassTransformHelper.injectCustomHook(bytes, new PlayerPreRotationTransformer(), ObfTable.MethodOnUpdate, ObfTable.MethodOnUpdateDesc, ObfTable.ClassEntityLivingBase.replace('.', '/'));
      

      bytes = ClassTransformHelper.injectCustomHook(bytes, new PlayerPostRotationTransformer(), ObfTable.MethodOnUpdate, ObfTable.MethodOnUpdateDesc, ObfTable.ClassEntityLivingBase.replace('.', '/'));
      
      System.out.println("Improved First Person Mod: Successfully modified onUpdate in EntityLivingBase");
    }
    else if (name.equals(ObfTable.ClassModelPlayerAnimatedPlayerMod))
    {
      bytes = ClassTransformHelper.injectCustomHook(bytes, new AnimatedPlayerAnimateTransformer(), ObfTable.MethodAnimateAnimatedPlayerMod, ObfTable.MethodAnimateAnimatedPlayerModDesc);
      
      System.out.println("Improved First Person Mod: Successfully injected code into animate in ModelPlayer from the Animated Player mod");
      
      bytes = ClassTransformHelper.changeFieldAcess(bytes, "partialTick", 1);
      System.out.println("Improved First Person Mod: Successfully changed the field partialTick to public in ModelPlayer from the Animated Player mod");
    }
    else if (name.equals(ObfTable.ClassAnimatedPlayer))
    {
      bytes = ClassTransformHelper.injectCustomHook(bytes, new AnimatedPlayerModContainerTransformer(), ObfTable.MethodOnClientTickAnimatedPlayerMod, ObfTable.MethodOnClientTickAnimatedPlayerModDesc);
      
      System.out.println("Improved First Person Mod: Successfully injected code into onClientTick in AnimatedPlayer");
    }
    return bytes;
  }
  
  private class MethodDoRenderFishHookTransformer
    extends CustomMethodTransformer
  {
    private MethodDoRenderFishHookTransformer() {}
    
    public void visitCode()
    {
      this.mv.visitCode();
      this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/RenderFishHook", "onMethodStart", "()V");
    }
    
    public void visitInsn(int opcode)
    {
      if (opcode == 177) {
        this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/RenderFishHook", "onMethodEnd", "()V");
      }
      this.mv.visitInsn(opcode);
    }
  }
  
  private class MethodGetMouseOverTransformer
    extends CustomMethodTransformer
  {
    private MethodGetMouseOverTransformer() {}
    
    public void visitCode()
    {
      this.mv.visitCode();
      this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/MouseSelectionOverride", "onMethodStart", "()V");
    }
    
    public void visitInsn(int opcode)
    {
      if (opcode == 177) {
        this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/MouseSelectionOverride", "onMethodEnd", "()V");
      }
      this.mv.visitInsn(opcode);
    }
  }
  
  private class AfterCameraTransformTransformer
    extends CustomMethodTransformer
  {
    private AfterCameraTransformTransformer() {}
    
    public void visitInsn(int opcode)
    {
      if (opcode == 177) {
        this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/AfterCameraTransformation", "afterCameraTransform", "()V");
      }
      this.mv.visitInsn(opcode);
    }
  }
  
  private class PlayerPreRotationTransformer
    extends CustomMethodTransformer
  {
    private PlayerPreRotationTransformer() {}
    
    public void visitLdcInsn(Object cst)
    {
      if (((cst instanceof String)) && (((String)cst).equals("headTurn"))) {
        try
        {
          this.mv.visitVarInsn(25, 0);
          this.mv.visitFieldInsn(180, ObfTable.ClassEntityLivingBase.replace('.', '/'), ObfTable.FieldRenderYawOffset, "F");
          this.mv.visitVarInsn(56, 15);
        }
        catch (Throwable e)
        {
          e.printStackTrace();
        }
      }
      this.mv.visitLdcInsn(cst);
    }
  }
  
  private class PlayerPostRotationTransformer
    extends CustomMethodTransformer
  {
    private PlayerPostRotationTransformer() {}
    
    public void visitLdcInsn(Object cst)
    {
      if (((cst instanceof String)) && (((String)cst).equals("rangeChecks"))) {
        try
        {
          this.mv.visitVarInsn(25, 0);
          this.mv.visitVarInsn(23, 15);
          this.mv.visitVarInsn(23, 6);
          this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/EntityLivingBaseHook", "handleRotation", "(L" + ObfTable.ClassEntityLivingBase.replace('.', '/') + ";FF)V");
        }
        catch (Throwable e)
        {
          e.printStackTrace();
        }
      }
      this.mv.visitLdcInsn(cst);
    }
  }
  
  private class MethodOnItemRightClickTransformer
    extends CustomMethodTransformer
  {
    private MethodOnItemRightClickTransformer() {}
    
    public void visitCode()
    {
      this.mv.visitCode();
      
      Label label = new Label();
      this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/BookHook", "cancelOpeningGUI", "()Z");
      

      this.mv.visitJumpInsn(153, label);
      this.mv.visitVarInsn(25, 1);
      this.mv.visitInsn(176);
      this.mv.visitLabel(label);
    }
  }
  
  private class AnimatedPlayerAnimateTransformer
    extends CustomMethodTransformer
  {
    private AnimatedPlayerAnimateTransformer() {}
    
    public void visitCode()
    {
      this.mv.visitCode();
      try
      {
        this.mv.visitVarInsn(25, 1);
        this.mv.visitVarInsn(25, 0);
        this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/AnimatedPlayerHooks", "beforeAnimatedPlayerRender", "(L" + ObfTable.ClassEntityPlayer.replace('.', '/') + ";L" + ObfTable.ClassModelPlayerAnimatedPlayerMod.replace('.', '/') + ";)V");
      }
      catch (Throwable e)
      {
        e.printStackTrace();
      }
    }
    
    public void visitFieldInsn(int opcode, String owner, String name, String desc)
    {
      boolean skipInsn = false;
      if ((opcode == 181) && (owner.equals(ObfTable.ClassEntityPlayer.replace('.', '/'))) && (name.equals(ObfTable.FieldRenderYawOffset)))
      {
        this.mv.visitInsn(88);
        skipInsn = true;
      }
      if (!skipInsn) {
        this.mv.visitFieldInsn(opcode, owner, name, desc);
      }
    }
  }
  
  private class AnimatedPlayerModContainerTransformer
    extends CustomMethodTransformer
  {
    private AnimatedPlayerModContainerTransformer() {}
    
    public void visitMethodInsn(int opcode, String owner, String name, String desc)
    {
      this.mv.visitMethodInsn(opcode, owner, name, desc);
    }
    
    public void visitFrame(int opcode, int nLocal, Object[] local, int nStack, Object[] stack)
    {
      this.mv.visitFrame(opcode, nLocal, local, nStack, stack);
    }
    
    public void visitFieldInsn(int opcode, String owner, String name, String desc)
    {
      boolean skipInsn = false;
      if ((opcode == 181) && (owner.equals("net/minecraft/entity/player/EntityPlayer")) && (name.equals(ObfTable.FieldRenderYawOffset)))
      {
        this.mv.visitInsn(88);
        skipInsn = true;
      }
      if (!skipInsn) {
        this.mv.visitFieldInsn(opcode, owner, name, desc);
      }
    }
  }
}
