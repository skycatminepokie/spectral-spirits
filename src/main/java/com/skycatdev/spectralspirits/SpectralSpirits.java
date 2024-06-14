package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.command.CommandHandler;
import com.skycatdev.spectralspirits.entity.FireSpiritEntity;
import com.skycatdev.spectralspirits.entity.TestSpectralSpiritEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectralSpirits implements ModInitializer {
	public static final String MOD_ID = "spectral-spirits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EntityType<TestSpectralSpiritEntity> TEST_SPECTRAL_SPIRIT = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MOD_ID, "test_spectral_spirit"),
			EntityType.Builder.create(TestSpectralSpiritEntity::new, SpawnGroup.MISC).dimensions(0.08f, 0.16f).disableSaving().disableSummon().build());
	public static final EntityType<FireSpiritEntity> FIRE_SPIRIT = Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(MOD_ID, "fire_spirit"),
			EntityType.Builder.create(FireSpiritEntity::new, SpawnGroup.MISC).dimensions(0.08f, 0.16f).disableSaving().disableSummon().build());

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(TEST_SPECTRAL_SPIRIT, TestSpectralSpiritEntity.createMobAttributes().add(EntityAttributes.GENERIC_FLYING_SPEED, 1));
		FabricDefaultAttributeRegistry.register(FIRE_SPIRIT, FireSpiritEntity.createMobAttributes().add(EntityAttributes.GENERIC_FLYING_SPEED, 1));
		CommandRegistrationCallback.EVENT.register(CommandHandler::register);
	}
}