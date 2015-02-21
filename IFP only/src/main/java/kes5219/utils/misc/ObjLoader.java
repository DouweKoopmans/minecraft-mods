package kes5219.utils.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ObjLoader
{
    @SuppressWarnings("unused")
    public static int loadObjToDisplaylist(InputStream stream)
    {
        ArrayList<Triple<Float, Float, Float>> vertices = new ArrayList();

        ArrayList<Triple<Float, Float, Float>> texCoords = new ArrayList();

        ArrayList<Triple<Float, Float, Float>> normals = new ArrayList();

        ArrayList<Triple<Integer, Integer, Integer>> faceDef = new ArrayList();

        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(stream));

            for(;;)
            {
                String line = reader.readLine();
                String[] splitString = line.split("\\s+");
                if(line == null)
                {
                    break;
                }
                if(!line.startsWith("#"))
                {
                    if(line.startsWith("vt"))
                    {

                        texCoords.add(new Triple(new Float(splitString[1]), new Float(splitString[2])));
                    }
                    else if(line.startsWith("vn"))
                    {

                        normals.add(new Triple(new Float(splitString[1]), new Float(splitString[2]), new Float(splitString[3])));
                    }
                    else if(line.startsWith("v"))
                    {

                        vertices.add(new Triple(new Float(splitString[1]), new Float(splitString[2]), new Float(splitString[3])));
                    }
                    else if(line.startsWith("f"))
                    {
                        splitString = line.split("\\s+");

                        int count = 1;
                        while(count < 4)
                        {
                            String splitString1 = splitString[count];
                            String[] splitStrings = splitString1.split("/");
                            int vertexIndex = 0;
                            int texCoordIndex = 0;
                            int normalIndex = 0;

                            vertexIndex = Integer.parseInt(splitStrings[0]);
                            texCoordIndex = Integer.parseInt(splitStrings[1]);
                            normalIndex = Integer.parseInt(splitStrings[2]);

                            faceDef.add(new Triple(Integer.valueOf(vertexIndex), Integer.valueOf(texCoordIndex), Integer.valueOf(normalIndex)));

                            count++;
                        }
                        if(splitString.length == 5)
                        {
                            Triple<Integer, Integer, Integer> firstVertex = (Triple)faceDef.get(faceDef.size() - 3);
                            Triple<Integer, Integer, Integer> lastVertex = (Triple)faceDef.get(faceDef.size() - 1);
                            String splitString1 = splitString[4];
                            String[] splitStrings = splitString1.split("/");
                            int vertexIndex = 0;
                            int texCoordIndex = 0;
                            int normalIndex = 0;

                            vertexIndex = Integer.parseInt(splitStrings[0]);
                            texCoordIndex = Integer.parseInt(splitStrings[1]);
                            normalIndex = Integer.parseInt(splitStrings[2]);

                            Triple<Integer, Integer, Integer> fourthVertex = new Triple(Integer.valueOf(vertexIndex), Integer.valueOf(texCoordIndex), Integer.valueOf(normalIndex));

                            faceDef.add(firstVertex);
                            faceDef.add(lastVertex);
                            faceDef.add(fourthVertex);
                        }
                    }
                }
            }
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
        catch(IOException e)
        {
            return 0;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return 0;
        }
        catch(NumberFormatException e)
        {
            return 0;
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        int displayListIndex;
        displayListIndex = GL11.glGenLists(1);
        if(displayListIndex == 0)
        {
            return 0;
        }
        int arraySize = faceDef.size();
        ArrayList<Triple<Float, Float, Float>> vertexArrayList = new ArrayList();
        for(int i = 0; i < arraySize; i++)
        {
            int index = ((Integer)((Triple)faceDef.get(i)).first).intValue();
            vertexArrayList.add(vertices.get(index - 1));
        }
        float[] vertexArray = new float[vertexArrayList.size() * 3];
        for(int i = 0; i < vertexArrayList.size(); i++)
        {
            vertexArray[(i * 3)] = ((Float)((Triple)vertexArrayList.get(i)).first).floatValue();
            vertexArray[(i * 3 + 1)] = ((Float)((Triple)vertexArrayList.get(i)).second).floatValue();
            vertexArray[(i * 3 + 2)] = ((Float)((Triple)vertexArrayList.get(i)).third).floatValue();
        }
        FloatBuffer vertexPointer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexPointer.put(vertexArray);
        vertexPointer.flip();

        ArrayList<Triple<Float, Float, Float>> texCoordArrayList = new ArrayList();
        for(int i = 0; i < arraySize; i++)
        {
            int index = ((Integer)((Triple)faceDef.get(i)).second).intValue();
            texCoordArrayList.add(texCoords.get(index - 1));
        }
        float[] texCoordArray = new float[texCoordArrayList.size() * 2];
        for(int i = 0; i < texCoordArrayList.size(); i++)
        {
            texCoordArray[(i * 2)] = (((Float)((Triple)texCoordArrayList.get(i)).first).floatValue() / 16.0F);
            texCoordArray[(i * 2 + 1)] = (((Float)((Triple)texCoordArrayList.get(i)).second).floatValue() / 16.0F);
        }
        FloatBuffer texCoordPointer = BufferUtils.createFloatBuffer(texCoordArray.length);
        texCoordPointer.put(texCoordArray);
        texCoordPointer.flip();

        ArrayList<Triple<Float, Float, Float>> normalArrayList = new ArrayList();
        for(int i = 0; i < arraySize; i++)
        {
            int index = ((Integer)((Triple)faceDef.get(i)).third).intValue();
            normalArrayList.add(normals.get(index - 1));
        }
        float[] normalArray = new float[normalArrayList.size() * 3];
        for(int i = 0; i < normalArrayList.size(); i++)
        {
            normalArray[(i * 3)] = ((Float)((Triple)normalArrayList.get(i)).first).floatValue();
            normalArray[(i * 3 + 1)] = ((Float)((Triple)normalArrayList.get(i)).second).floatValue();
            normalArray[(i * 3 + 2)] = ((Float)((Triple)normalArrayList.get(i)).third).floatValue();
        }
        FloatBuffer normalPointer = BufferUtils.createFloatBuffer(normalArray.length);
        normalPointer.put(normalArray);
        normalPointer.flip();

        GL11.glEnableClientState(32884);
        GL11.glVertexPointer(3, 0, vertexPointer);

        GL11.glEnableClientState(32888);
        GL11.glTexCoordPointer(2, 0, texCoordPointer);

        GL11.glEnableClientState(32885);
        GL11.glNormalPointer(0, normalPointer);

        GL11.glNewList(displayListIndex, 4864);
        GL11.glDrawArrays(4, 0, arraySize);
        GL11.glEndList();

        GL11.glDisableClientState(32884);
        GL11.glDisableClientState(32888);
        GL11.glDisableClientState(32885);

        return displayListIndex;
    }
}
