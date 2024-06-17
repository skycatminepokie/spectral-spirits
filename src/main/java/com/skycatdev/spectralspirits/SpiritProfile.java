package com.skycatdev.spectralspirits;

import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public record SpiritProfile(/*Set<AbilityType> abilities*/) {
    // public static final Codec<HashSet<AbilityType>> CODEC = Codec.list(AbilityTypes.REGISTRY.getCodec()).xmap(Sets::newHashSet, Lists::newArrayList); // TODO

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
