/*   1:    */ package kes5219.utils.misc;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStream;
/*   6:    */ import java.io.InputStreamReader;
/*   7:    */ import java.nio.FloatBuffer;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import org.lwjgl.BufferUtils;
/*  10:    */ import org.lwjgl.opengl.GL11;
/*  11:    */ 
/*  12:    */ public class ObjLoader
/*  13:    */ {
/*  14:    */   public static int loadObjToDisplaylist(InputStream stream)
/*  15:    */   {
/*  16: 29 */     ArrayList<Triple<Float, Float, Float>> vertices = new ArrayList();
/*  17:    */     
/*  18: 31 */     ArrayList<Triple<Float, Float, Float>> texCoords = new ArrayList();
/*  19:    */     
/*  20: 33 */     ArrayList<Triple<Float, Float, Float>> normals = new ArrayList();
/*  21:    */     
/*  22: 35 */     ArrayList<Triple<Integer, Integer, Integer>> faceDef = new ArrayList();
/*  23:    */     
/*  24: 37 */     BufferedReader reader = null;
/*  25:    */     try
/*  26:    */     {
/*  27: 40 */       reader = new BufferedReader(new InputStreamReader(stream));
/*  28:    */       String[] splitString;
/*  29:    */       for (;;)
/*  30:    */       {
/*  31: 44 */         String line = reader.readLine();
/*  32: 45 */         if (line == null) {
/*  33:    */           break;
/*  34:    */         }
/*  35: 49 */         if (!line.startsWith("#")) {
/*  36: 53 */           if (line.startsWith("vt"))
/*  37:    */           {
/*  38: 56 */             String[] splitString = line.split("\\s+");
/*  39: 57 */             texCoords.add(new Triple(new Float(splitString[1]), new Float(splitString[2])));
/*  40:    */           }
/*  41: 61 */           else if (line.startsWith("vn"))
/*  42:    */           {
/*  43: 64 */             String[] splitString = line.split("\\s+");
/*  44: 65 */             normals.add(new Triple(new Float(splitString[1]), new Float(splitString[2]), new Float(splitString[3])));
/*  45:    */           }
/*  46: 70 */           else if (line.startsWith("v"))
/*  47:    */           {
/*  48: 73 */             String[] splitString = line.split("\\s+");
/*  49: 74 */             vertices.add(new Triple(new Float(splitString[1]), new Float(splitString[2]), new Float(splitString[3])));
/*  50:    */           }
/*  51: 79 */           else if (line.startsWith("f"))
/*  52:    */           {
/*  53: 82 */             splitString = line.split("\\s+");
/*  54:    */             
/*  55:    */ 
/*  56: 85 */             int count = 1;
/*  57: 86 */             while (count < 4)
/*  58:    */             {
/*  59: 88 */               String splitString1 = splitString[count];
/*  60: 89 */               String[] splitStrings = splitString1.split("/");
/*  61: 90 */               int vertexIndex = 0;
/*  62: 91 */               int texCoordIndex = 0;
/*  63: 92 */               int normalIndex = 0;
/*  64:    */               
/*  65: 94 */               vertexIndex = Integer.parseInt(splitStrings[0]);
/*  66: 95 */               texCoordIndex = Integer.parseInt(splitStrings[1]);
/*  67: 96 */               normalIndex = Integer.parseInt(splitStrings[2]);
/*  68:    */               
/*  69: 98 */               faceDef.add(new Triple(Integer.valueOf(vertexIndex), Integer.valueOf(texCoordIndex), Integer.valueOf(normalIndex)));
/*  70:    */               
/*  71:100 */               count++;
/*  72:    */             }
/*  73:102 */             if (splitString.length == 5)
/*  74:    */             {
/*  75:104 */               Triple<Integer, Integer, Integer> firstVertex = (Triple)faceDef.get(faceDef.size() - 3);
/*  76:105 */               Triple<Integer, Integer, Integer> lastVertex = (Triple)faceDef.get(faceDef.size() - 1);
/*  77:106 */               String splitString1 = splitString[4];
/*  78:107 */               String[] splitStrings = splitString1.split("/");
/*  79:108 */               int vertexIndex = 0;
/*  80:109 */               int texCoordIndex = 0;
/*  81:110 */               int normalIndex = 0;
/*  82:    */               
/*  83:112 */               vertexIndex = Integer.parseInt(splitStrings[0]);
/*  84:113 */               texCoordIndex = Integer.parseInt(splitStrings[1]);
/*  85:114 */               normalIndex = Integer.parseInt(splitStrings[2]);
/*  86:    */               
/*  87:116 */               Triple<Integer, Integer, Integer> fourthVertex = new Triple(Integer.valueOf(vertexIndex), Integer.valueOf(texCoordIndex), Integer.valueOf(normalIndex));
/*  88:    */               
/*  89:118 */               faceDef.add(firstVertex);
/*  90:119 */               faceDef.add(lastVertex);
/*  91:120 */               faceDef.add(fourthVertex);
/*  92:    */             }
/*  93:    */           }
/*  94:    */         }
/*  95:    */       }
/*  96:    */       try
/*  97:    */       {
/*  98:142 */         reader.close();
/*  99:    */       }
/* 100:    */       catch (IOException e)
/* 101:    */       {
/* 102:145 */         e.printStackTrace();
/* 103:    */       }
/* 104:149 */       displayListIndex = GL11.glGenLists(1);
/* 105:    */     }
/* 106:    */     catch (IOException e)
/* 107:    */     {
/* 108:129 */       return 0;
/* 109:    */     }
/* 110:    */     catch (ArrayIndexOutOfBoundsException e)
/* 111:    */     {
/* 112:133 */       return 0;
/* 113:    */     }
/* 114:    */     catch (NumberFormatException e)
/* 115:    */     {
/* 116:137 */       return 0;
/* 117:    */     }
/* 118:    */     finally
/* 119:    */     {
/* 120:    */       try
/* 121:    */       {
/* 122:142 */         reader.close();
/* 123:    */       }
/* 124:    */       catch (IOException e)
/* 125:    */       {
/* 126:145 */         e.printStackTrace();
/* 127:    */       }
/* 128:    */     }
/* 129:    */     int displayListIndex;
/* 130:150 */     if (displayListIndex == 0) {
/* 131:153 */       return 0;
/* 132:    */     }
/* 133:156 */     int arraySize = faceDef.size();
/* 134:157 */     ArrayList<Triple<Float, Float, Float>> vertexArrayList = new ArrayList();
/* 135:159 */     for (int i = 0; i < arraySize; i++)
/* 136:    */     {
/* 137:161 */       int index = ((Integer)((Triple)faceDef.get(i)).first).intValue();
/* 138:162 */       vertexArrayList.add(vertices.get(index - 1));
/* 139:    */     }
/* 140:164 */     float[] vertexArray = new float[vertexArrayList.size() * 3];
/* 141:165 */     for (int i = 0; i < vertexArrayList.size(); i++)
/* 142:    */     {
/* 143:167 */       vertexArray[(i * 3)] = ((Float)((Triple)vertexArrayList.get(i)).first).floatValue();
/* 144:168 */       vertexArray[(i * 3 + 1)] = ((Float)((Triple)vertexArrayList.get(i)).second).floatValue();
/* 145:169 */       vertexArray[(i * 3 + 2)] = ((Float)((Triple)vertexArrayList.get(i)).third).floatValue();
/* 146:    */     }
/* 147:171 */     FloatBuffer vertexPointer = BufferUtils.createFloatBuffer(vertexArray.length);
/* 148:172 */     vertexPointer.put(vertexArray);
/* 149:173 */     vertexPointer.flip();
/* 150:    */     
/* 151:175 */     ArrayList<Triple<Float, Float, Float>> texCoordArrayList = new ArrayList();
/* 152:177 */     for (int i = 0; i < arraySize; i++)
/* 153:    */     {
/* 154:179 */       int index = ((Integer)((Triple)faceDef.get(i)).second).intValue();
/* 155:180 */       texCoordArrayList.add(texCoords.get(index - 1));
/* 156:    */     }
/* 157:182 */     float[] texCoordArray = new float[texCoordArrayList.size() * 2];
/* 158:183 */     for (int i = 0; i < texCoordArrayList.size(); i++)
/* 159:    */     {
/* 160:185 */       texCoordArray[(i * 2)] = (((Float)((Triple)texCoordArrayList.get(i)).first).floatValue() / 16.0F);
/* 161:186 */       texCoordArray[(i * 2 + 1)] = (((Float)((Triple)texCoordArrayList.get(i)).second).floatValue() / 16.0F);
/* 162:    */     }
/* 163:188 */     FloatBuffer texCoordPointer = BufferUtils.createFloatBuffer(texCoordArray.length);
/* 164:189 */     texCoordPointer.put(texCoordArray);
/* 165:190 */     texCoordPointer.flip();
/* 166:    */     
/* 167:192 */     ArrayList<Triple<Float, Float, Float>> normalArrayList = new ArrayList();
/* 168:194 */     for (int i = 0; i < arraySize; i++)
/* 169:    */     {
/* 170:196 */       int index = ((Integer)((Triple)faceDef.get(i)).third).intValue();
/* 171:197 */       normalArrayList.add(normals.get(index - 1));
/* 172:    */     }
/* 173:199 */     float[] normalArray = new float[normalArrayList.size() * 3];
/* 174:200 */     for (int i = 0; i < normalArrayList.size(); i++)
/* 175:    */     {
/* 176:202 */       normalArray[(i * 3)] = ((Float)((Triple)normalArrayList.get(i)).first).floatValue();
/* 177:203 */       normalArray[(i * 3 + 1)] = ((Float)((Triple)normalArrayList.get(i)).second).floatValue();
/* 178:204 */       normalArray[(i * 3 + 2)] = ((Float)((Triple)normalArrayList.get(i)).third).floatValue();
/* 179:    */     }
/* 180:206 */     FloatBuffer normalPointer = BufferUtils.createFloatBuffer(normalArray.length);
/* 181:207 */     normalPointer.put(normalArray);
/* 182:208 */     normalPointer.flip();
/* 183:    */     
/* 184:210 */     GL11.glEnableClientState(32884);
/* 185:211 */     GL11.glVertexPointer(3, 0, vertexPointer);
/* 186:    */     
/* 187:213 */     GL11.glEnableClientState(32888);
/* 188:214 */     GL11.glTexCoordPointer(2, 0, texCoordPointer);
/* 189:    */     
/* 190:216 */     GL11.glEnableClientState(32885);
/* 191:217 */     GL11.glNormalPointer(0, normalPointer);
/* 192:    */     
/* 193:219 */     GL11.glNewList(displayListIndex, 4864);
/* 194:220 */     GL11.glDrawArrays(4, 0, arraySize);
/* 195:221 */     GL11.glEndList();
/* 196:    */     
/* 197:223 */     GL11.glDisableClientState(32884);
/* 198:224 */     GL11.glDisableClientState(32888);
/* 199:225 */     GL11.glDisableClientState(32885);
/* 200:    */     
/* 201:227 */     return displayListIndex;
/* 202:    */   }
/* 203:    */ }


/* Location:           C:\Users\Benoît\Desktop\ImprovedFirstPerson1.6.4r1.jar
 * Qualified Name:     kes5219.utils.misc.ObjLoader
 * JD-Core Version:    0.7.0.1
 */