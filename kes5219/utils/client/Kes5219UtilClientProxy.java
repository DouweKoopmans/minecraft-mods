package kes5219.utils.client;

import kes5219.utils.common.Kes5219UtilCommonProxy;
import kes5219.utils.misc.PartialTickRetriever;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Kes5219UtilClientProxy extends Kes5219UtilCommonProxy{
    
     
    public void preInit(FMLPreInitializationEvent event) {

    }
    
     
    public void init(FMLInitializationEvent event) {
    	FMLCommonHandler.instance().bus().register(new PartialTickRetriever());
    }
}