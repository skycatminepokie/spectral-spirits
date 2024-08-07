package com.skycatdev.spectralspirits;

import com.mojang.serialization.Lifecycle;
import com.skycatdev.spectralspirits.client.entity.FireSpiritEntity;
import com.skycatdev.spectralspirits.client.entity.SpectralSpiritEntity;
import com.skycatdev.spectralspirits.client.entity.TestSpectralSpiritEntity;
import com.skycatdev.spectralspirits.command.CommandHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectralSpirits implements ModInitializer {
    // To register a new Spirit type: Register everything normally, except for the EntityType. That has to be registered with SpectralSpirits#registerSpiritEntityType.
    public static final String MOD_ID = "spectral-spirits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Registry<EntityType<? extends SpectralSpiritEntity>> SPIRIT_TYPE_REGISTRY = new SimpleRegistry<>(RegistryKey.ofRegistry(Identifier.of(MOD_ID, "spirit_type")), Lifecycle.stable());
    public static final EntityType<TestSpectralSpiritEntity> TEST_SPECTRAL_SPIRIT = registerSpiritEntityType(Identifier.of(MOD_ID, "test_spectral_spirit"),
            EntityType.Builder.create(TestSpectralSpiritEntity::new, SpawnGroup.MISC).dimensions(0.08f, 0.16f).disableSaving().disableSummon().build());
    public static final EntityType<FireSpiritEntity> FIRE_SPIRIT = registerSpiritEntityType(Identifier.of(MOD_ID, "fire_spirit"),
            EntityType.Builder.create(FireSpiritEntity::new, SpawnGroup.MISC).dimensions(0.64f, 0.52f).disableSaving().disableSummon().build());
    @SuppressWarnings("UnstableApiUsage") public static final AttachmentType<SpiritProfile> SPECTRAL_SPIRIT_ATTACHMENT = AttachmentRegistry.<SpiritProfile>builder().copyOnDeath().persistent(SpiritProfile.CODEC).buildAndRegister(Identifier.of(MOD_ID, "spirit_profile"));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(TEST_SPECTRAL_SPIRIT, TestSpectralSpiritEntity.createMobAttributes().add(EntityAttributes.GENERIC_FLYING_SPEED, 1));
        FabricDefaultAttributeRegistry.register(FIRE_SPIRIT, FireSpiritEntity.createMobAttributes().add(EntityAttributes.GENERIC_FLYING_SPEED, 1));
        CommandRegistrationCallback.EVENT.register(CommandHandler::register);
    }

    /**
     * Register a spirit type. Also registers the entity type, but not the default attributes.
     * @param id The {@link Identifier} used in both registries.
     * @param type The type to register
     * @return The registered type
     */
    public static <T extends SpectralSpiritEntity> EntityType<T> registerSpiritEntityType(Identifier id, EntityType<T> type) {
        Registry.register(Registries.ENTITY_TYPE, id, type);
        Registry.register(SPIRIT_TYPE_REGISTRY, id, type);
        return type;
    }
}