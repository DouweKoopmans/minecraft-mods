package api.player.model;

public class ModelPlayer extends net.minecraft.client.model.ModelBiped
{
	public ModelPlayer()
	{
		this(0.0F);
	}

	public ModelPlayer(float paramFloat)
	{
		this(paramFloat, 0.0F, 64, 32);
	}

	public ModelPlayer(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
	{
		super(paramFloat1, paramFloat2, paramInt1, paramInt2);
	}
}