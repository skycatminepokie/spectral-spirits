package com.skycatdev.spectralspirits;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.skycatdev.spectralspirits.ability.AbilityType;
import com.skycatdev.spectralspirits.ability.AbilityTypes;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record SpiritProfile(List<AbilityType> abilities) {
    public static final Codec<SpiritProfile> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AbilityTypes.REGISTRY.getCodec().listOf().fieldOf("abilities").forGetter(SpiritProfile::abilities) // TODO: Use set instead of list. DFU make me sad rn.
    ).apply(instance, SpiritProfile::new));

    public <T extends SpectralSpiritEntity> @Nullable T createEntity(EntityType<T> entityType, World world, PlayerEntity owner) {
        if (world instanceof ServerWorld serverWorld) {
            return entityType.create(serverWorld, (spirit) -> {
                spirit.setOwner(owner);
                spirit.updateFromProfile(this);
                spirit.setOwner(owner);
                spirit.refreshPositionAndAngles(spirit.getPos(), spirit.getYaw(), spirit.getPitch());
                serverWorld.spawnEntity(spirit);
            }, owner.getBlockPos(), SpawnReason.MOB_SUMMONED, false, false);
        }
        return null;
    }
}
