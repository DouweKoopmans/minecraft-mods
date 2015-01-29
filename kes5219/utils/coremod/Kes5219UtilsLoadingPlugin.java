package kes5219.utils.coremod;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({"kes5219.utils.coremod"})
@MCVersion("1.7.10")
public abstract class Kes5219UtilsLoadingPlugin implements IFMLLoadingPlugin {

    public String[] getLibraryRequestClass() 
    {
        return null;
    }

    public String[] getASMTransformerClass() 
    {
        return new String[] {"kes5219.utils.coremod.ClassTransformer"};
    }

    public String getModContainerClass() 
    {
        return null;
    }

    public String getSetupClass() 
    {
        return null;
    }

    public void injectData(Map<String, Object> data) 
    {
        
    }
}