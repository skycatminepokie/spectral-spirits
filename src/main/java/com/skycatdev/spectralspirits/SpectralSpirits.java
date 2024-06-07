package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectralSpirits implements ModInitializer {
	public static final String MOD_ID = "spectral-spirits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EntityType<SpectralSpiritEntity> SPECTRAL_SPIRIT = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MOD_ID, "spectral_spirit"),
			EntityType.Builder.create(SpectralSpiritEntity::new, SpawnGroup.MISC).build());

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(SPECTRAL_SPIRIT, SpectralSpiritEntity.createMobAttributes());
	}
}