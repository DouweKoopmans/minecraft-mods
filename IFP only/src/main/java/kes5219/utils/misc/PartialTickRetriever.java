package kes5219.utils.misc;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

//Retrieves partial tick which is crucial for rendering
public class PartialTickRetriever {

    private static float partialTick;
    
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if(Minecraft.getMinecraft().theWorld != null) { 
            partialTick = event.renderTickTime;
        }
    }
    
    public static float getPartialTick()
    {
        return partialTick;
    }

}