package api.player.forge;

import java.util.*;
import com.google.common.eventbus.*;
import cpw.mods.fml.common.*;

public class RenderPlayerAPIEnhancerContainer extends DummyModContainer
{
	public RenderPlayerAPIEnhancerContainer()
	{
		super(createMetadata());
	}

	public boolean registerBus(EventBus bus, LoadController controller)
	{
		return true;
	}

	private static ModMetadata createMetadata()
	{
		ModMetadata meta = new ModMetadata();

		meta.modId = "RenderPlayerAPIEnhancer";
		meta.name = "Render Player API Enhancer";
		meta.version = RenderPlayerAPIEnhancerPlugin.Version;
		meta.description = "Render Player API Enhancer for Minecraft Forge";
		meta.url = "http://www.minecraftforum.net/topic/1261354-";
		meta.authorList = Arrays.asList(new String[] { "Divisor" });

		return meta;
	}
}