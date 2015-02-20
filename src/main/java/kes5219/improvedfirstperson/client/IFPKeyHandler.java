
package kes5219.improvedfirstperson.client;

import kes5219.improvedfirstperson.common.ModImprovedFirstPerson;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class IFPKeyHandler {
    
    static KeyBinding toggle = new KeyBinding("Toggle IFP View", Keyboard.KEY_F6, "key.categories.improvedfirstperson");
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(IFPClientProxy.getMC().currentScreen == null && toggle.isPressed()) {
            ModImprovedFirstPerson.enableBodyRender = !ModImprovedFirstPerson.enableBodyRender;
        }
    }

}
