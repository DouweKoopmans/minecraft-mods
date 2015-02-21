package kes5219.improvedfirstperson.hooks;

import net.minecraft.util.MathHelper;
import thehippomaster.animatedplayer.PlayerData;

public class AnimatedPlayerOtherHooks
{
    public static int getJumpTickIncrementDown(PlayerData data)
    {
        if(data.jumpTick > 4)
        {
            return 4;
        }
        switch(data.jumpTick)
        {
            case 4:
                return 2;
            case 2:
                return 1;
            case 1:
                return 0;
        }
        return MathHelper.clamp_int(data.jumpTick - 1, 0, 4);
    }
}
