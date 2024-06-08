package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.TestSpectralSpiritModel;
import com.skycatdev.spectralspirits.entity.TestSpectralSpiritRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.skycatdev.spectralspirits.SpectralSpirits.MOD_ID;

public class SpectralSpiritsClient implements ClientModInitializer {
	public static final EntityModelLayer SPECTRAL_SPIRIT_MODEL_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "spectral_spirit"), "main");
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(SpectralSpirits.TEST_SPECTRAL_SPIRIT, TestSpectralSpiritRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(SPECTRAL_SPIRIT_MODEL_LAYER, TestSpectralSpiritModel::getTexturedModelData);
	}
}