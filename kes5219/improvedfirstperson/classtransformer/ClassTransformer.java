/*   1:    */ 
/*   2:    */ 
/*   3:    */ java.io.PrintStream
/*   4:    */ kes5219.utils.classtransformhelper.ClassTransformHelper
/*   5:    */ kes5219.utils.classtransformhelper.CustomMethodTransformer
/*   6:    */ net.minecraft.launchwrapper.IClassTransformer
/*   7:    */ org.objectweb.asm.Label
/*   8:    */ org.objectweb.asm.MethodVisitor
/*   9:    */ 
/*  10:    */ ClassTransformer
/*  11:    */   
/*  12:    */ 
/*  13:    */   []transform, , []
/*  14:    */   
/*  15: 72 */      (equalsClassItemRenderer
/*  16:    */     
/*  17: 74 */        = injectSimpleHook, , MethodRenderItemInFirstPerson, "(F)V", "kes5219/improvedfirstperson/hooks/ItemRendererHook", "shouldRenderItemInFirstPerson"
/*  18: 75 */       outprintln"Improved First Person Mod: Successfully modified renderItemInFirstPerson in ItemRenderer"
/*  19:    */     
/*  20: 77 */      (equalsClassEntityRenderer
/*  21:    */     
/*  22: 79 */        = changeFieldAcess, FieldRendererUpdateCount, 1
/*  23: 80 */       outprintln"Improved First Person Mod: Successfully changed the field rendererUpdateCount to public in EntityRenderer"
/*  24:    */       
/*  25: 82 */        = injectCustomHook, , MethodSetupCameraTransform, MethodSetupCameraTransformDesc
/*  26: 83 */       outprintln"Improved First Person Mod: Successfully injected hook into setupCameraTransform in EntityRenderer"
/*  27:    */       
/*  28: 85 */        = injectCustomHook, , MethodGetMouseOver, MethodGetMouseOverDesc
/*  29: 86 */       outprintln"Improved First Person Mod: Successfully injected hook into getMouseOver in EntityRenderer"
/*  30:    */     
/*  31: 88 */      (equalsClassRenderFish
/*  32:    */     
/*  33: 90 */        = injectCustomHook, , MethodDoRenderFishHook, MethodDoRenderFishHookDesc
/*  34: 91 */       outprintln"Improved First Person Mod: Successfully modified doRenderFishHook in RenderFish"
/*  35:    */     
/*  36: 93 */      (equalsClassRenderGlobal
/*  37:    */     
/*  38: 95 */        = injectSimpleHookAtProfilerSection, MethodRenderEntities, MethodRenderEntitiesDesc, "kes5219/improvedfirstperson/hooks/RenderEntityHook", "onRenderEntities", "tileentities"
/*  39: 96 */       outprintln"Improved First Person Mod: Successfully modified renderEntities in RenderGlobal"
/*  40:    */     
/*  41: 98 */      (equalsClassItemEditableBookequalsClassItemWritableBook
/*  42:    */     
/*  43:100 */        = injectCustomHook, , MethodOnItemRightClick, MethodOnItemRightClickDesc
/*  44:    */       
/*  45:102 */       outprintln"Improved First Person Mod: Successfully modified onItemRightClick in "equalsClassItemEditableBook ? "ItemEditableBook" : "ItemWritableBook"
/*  46:    */     
/*  47:110 */      (equalsClassEntityLivingBase
/*  48:    */     
/*  49:112 */        = injectCustomHook, , MethodOnUpdate, MethodOnUpdateDesc, ClassEntityLivingBasereplace'.', '/'
/*  50:    */       
/*  51:    */ 
/*  52:115 */        = injectCustomHook, , MethodOnUpdate, MethodOnUpdateDesc, ClassEntityLivingBasereplace'.', '/'
/*  53:    */       
/*  54:117 */       outprintln"Improved First Person Mod: Successfully modified onUpdate in EntityLivingBase"
/*  55:    */     
/*  56:119 */      (equalsClassModelPlayerAnimatedPlayerMod
/*  57:    */     
/*  58:121 */        = injectCustomHook, , MethodAnimateAnimatedPlayerMod, MethodAnimateAnimatedPlayerModDesc
/*  59:    */       
/*  60:123 */       outprintln"Improved First Person Mod: Successfully injected code into animate in ModelPlayer from the Animated Player mod"
/*  61:    */       
/*  62:125 */        = changeFieldAcess, "partialTick", 1
/*  63:126 */       outprintln"Improved First Person Mod: Successfully changed the field partialTick to public in ModelPlayer from the Animated Player mod"
/*  64:    */     
/*  65:128 */      (equalsClassAnimatedPlayer
/*  66:    */     
/*  67:130 */        = injectCustomHook, , MethodOnClientTickAnimatedPlayerMod, MethodOnClientTickAnimatedPlayerModDesc
/*  68:    */       
/*  69:132 */       outprintln"Improved First Person Mod: Successfully injected code into onClientTick in AnimatedPlayer"
/*  70:    */     
/*  71:135 */     
/*  72:    */   
/*  73:    */   
/*  74:    */   MethodDoRenderFishHookTransformer
/*  75:    */     
/*  76:    */   
/*  77:    */     MethodDoRenderFishHookTransformer {}
/*  78:    */     
/*  79:    */     visitCode
/*  80:    */     
/*  81:141 */       mv.visitCode();
/*  82:142 */       this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/RenderFishHook", "onMethodStart", "()V");
/*  83:    */     }
/*  84:    */     
/*  85:    */     public void visitInsn(int opcode)
/*  86:    */     {
/*  87:146 */       if (opcode == 177) {
/*  88:147 */         this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/RenderFishHook", "onMethodEnd", "()V");
/*  89:    */       }
/*  90:149 */       this.mv.visitInsn(opcode);
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   private class MethodGetMouseOverTransformer
/*  95:    */     extends CustomMethodTransformer
/*  96:    */   {
/*  97:    */     private MethodGetMouseOverTransformer() {}
/*  98:    */     
/*  99:    */     public void visitCode()
/* 100:    */     {
/* 101:156 */       this.mv.visitCode();
/* 102:157 */       this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/MouseSelectionOverride", "onMethodStart", "()V");
/* 103:    */     }
/* 104:    */     
/* 105:    */     public void visitInsn(int opcode)
/* 106:    */     {
/* 107:162 */       if (opcode == 177) {
/* 108:163 */         this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/MouseSelectionOverride", "onMethodEnd", "()V");
/* 109:    */       }
/* 110:165 */       this.mv.visitInsn(opcode);
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   private class AfterCameraTransformTransformer
/* 115:    */     extends CustomMethodTransformer
/* 116:    */   {
/* 117:    */     private AfterCameraTransformTransformer() {}
/* 118:    */     
/* 119:    */     public void visitInsn(int opcode)
/* 120:    */     {
/* 121:172 */       if (opcode == 177) {
/* 122:173 */         this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/AfterCameraTransformation", "afterCameraTransform", "()V");
/* 123:    */       }
/* 124:175 */       this.mv.visitInsn(opcode);
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   private class PlayerPreRotationTransformer
/* 129:    */     extends CustomMethodTransformer
/* 130:    */   {
/* 131:    */     private PlayerPreRotationTransformer() {}
/* 132:    */     
/* 133:    */     public void visitLdcInsn(Object cst)
/* 134:    */     {
/* 135:182 */       if (((cst instanceof String)) && (((String)cst).equals("headTurn"))) {
/* 136:    */         try
/* 137:    */         {
/* 138:185 */           this.mv.visitVarInsn(25, 0);
/* 139:186 */           this.mv.visitFieldInsn(180, ObfTable.ClassEntityLivingBase.replace('.', '/'), ObfTable.FieldRenderYawOffset, "F");
/* 140:187 */           this.mv.visitVarInsn(56, 15);
/* 141:    */         }
/* 142:    */         catch (Throwable e)
/* 143:    */         {
/* 144:191 */           e.printStackTrace();
/* 145:    */         }
/* 146:    */       }
/* 147:194 */       this.mv.visitLdcInsn(cst);
/* 148:    */     }
/* 149:    */   }
/* 150:    */   
/* 151:    */   private class PlayerPostRotationTransformer
/* 152:    */     extends CustomMethodTransformer
/* 153:    */   {
/* 154:    */     private PlayerPostRotationTransformer() {}
/* 155:    */     
/* 156:    */     public void visitLdcInsn(Object cst)
/* 157:    */     {
/* 158:201 */       if (((cst instanceof String)) && (((String)cst).equals("rangeChecks"))) {
/* 159:    */         try
/* 160:    */         {
/* 161:204 */           this.mv.visitVarInsn(25, 0);
/* 162:205 */           this.mv.visitVarInsn(23, 15);
/* 163:206 */           this.mv.visitVarInsn(23, 6);
/* 164:207 */           this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/EntityLivingBaseHook", "handleRotation", "(L" + ObfTable.ClassEntityLivingBase.replace('.', '/') + ";FF)V");
/* 165:    */         }
/* 166:    */         catch (Throwable e)
/* 167:    */         {
/* 168:211 */           e.printStackTrace();
/* 169:    */         }
/* 170:    */       }
/* 171:214 */       this.mv.visitLdcInsn(cst);
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   private class MethodOnItemRightClickTransformer
/* 176:    */     extends CustomMethodTransformer
/* 177:    */   {
/* 178:    */     private MethodOnItemRightClickTransformer() {}
/* 179:    */     
/* 180:    */     public void visitCode()
/* 181:    */     {
/* 182:221 */       this.mv.visitCode();
/* 183:    */       
/* 184:223 */       Label label = new Label();
/* 185:224 */       this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/BookHook", "cancelOpeningGUI", "()Z");
/* 186:    */       
/* 187:    */ 
/* 188:227 */       this.mv.visitJumpInsn(153, label);
/* 189:228 */       this.mv.visitVarInsn(25, 1);
/* 190:229 */       this.mv.visitInsn(176);
/* 191:230 */       this.mv.visitLabel(label);
/* 192:    */     }
/* 193:    */   }
/* 194:    */   
/* 195:    */   private class AnimatedPlayerAnimateTransformer
/* 196:    */     extends CustomMethodTransformer
/* 197:    */   {
/* 198:    */     private AnimatedPlayerAnimateTransformer() {}
/* 199:    */     
/* 200:    */     public void visitCode()
/* 201:    */     {
/* 202:237 */       this.mv.visitCode();
/* 203:    */       try
/* 204:    */       {
/* 205:241 */         this.mv.visitVarInsn(25, 1);
/* 206:242 */         this.mv.visitVarInsn(25, 0);
/* 207:243 */         this.mv.visitMethodInsn(184, "kes5219/improvedfirstperson/hooks/AnimatedPlayerHooks", "beforeAnimatedPlayerRender", "(L" + ObfTable.ClassEntityPlayer.replace('.', '/') + ";L" + ObfTable.ClassModelPlayerAnimatedPlayerMod.replace('.', '/') + ";)V");
/* 208:    */       }
/* 209:    */       catch (Throwable e)
/* 210:    */       {
/* 211:249 */         e.printStackTrace();
/* 212:    */       }
/* 213:    */     }
/* 214:    */     
/* 215:    */     public void visitFieldInsn(int opcode, String owner, String name, String desc)
/* 216:    */     {
/* 217:256 */       boolean skipInsn = false;
/* 218:258 */       if ((opcode == 181) && (owner.equals(ObfTable.ClassEntityPlayer.replace('.', '/'))) && (name.equals(ObfTable.FieldRenderYawOffset)))
/* 219:    */       {
/* 220:262 */         this.mv.visitInsn(88);
/* 221:263 */         skipInsn = true;
/* 222:    */       }
/* 223:266 */       if (!skipInsn) {
/* 224:268 */         this.mv.visitFieldInsn(opcode, owner, name, desc);
/* 225:    */       }
/* 226:    */     }
/* 227:    */   }
/* 228:    */   
/* 229:    */   private class AnimatedPlayerModContainerTransformer
/* 230:    */     extends CustomMethodTransformer
/* 231:    */   {
/* 232:    */     private AnimatedPlayerModContainerTransformer() {}
/* 233:    */     
/* 234:    */     public void visitMethodInsn(int opcode, String owner, String name, String desc)
/* 235:    */     {
/* 236:280 */       this.mv.visitMethodInsn(opcode, owner, name, desc);
/* 237:    */     }
/* 238:    */     
/* 239:    */     public void visitFrame(int opcode, int nLocal, Object[] local, int nStack, Object[] stack)
/* 240:    */     {
/* 241:293 */       this.mv.visitFrame(opcode, nLocal, local, nStack, stack);
/* 242:    */     }
/* 243:    */     
/* 244:    */     public void visitFieldInsn(int opcode, String owner, String name, String desc)
/* 245:    */     {
/* 246:308 */       boolean skipInsn = false;
/* 247:330 */       if ((opcode == 181) && (owner.equals("net/minecraft/entity/player/EntityPlayer")) && (name.equals(ObfTable.FieldRenderYawOffset)))
/* 248:    */       {
/* 249:334 */         this.mv.visitInsn(88);
/* 250:335 */         skipInsn = true;
/* 251:    */       }
/* 252:338 */       if (!skipInsn) {
/* 253:340 */         this.mv.visitFieldInsn(opcode, owner, name, desc);
/* 254:    */       }
/* 255:    */     }
/* 256:    */   }
/* 257:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.improvedfirstperson.classtransformer.ClassTransformer
 * JD-Core Version:    0.7.0.1
 */