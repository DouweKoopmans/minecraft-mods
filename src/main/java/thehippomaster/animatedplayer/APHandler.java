package thehippomaster.animatedplayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;


public class APHandler
{
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event)
  {
    if (event.phase == TickEvent.Phase.END) {
      AnimatedPlayer.proxy.onClientTick();
    }
  }
}
