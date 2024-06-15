package com.skycatdev.spectralspirits;

import com.mojang.serialization.Codec;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpiritProfile {
    public static final Codec<SpiritProfile> CODEC = Codec.unit(SpiritProfile::new); // TODO

    public SpiritProfile() {
    }

    public <T extends SpectralSpiritEntity> @Nullable T createEntity(EntityType<T> entityType, World world, PlayerEntity owner) {
        if (world instanceof ServerWorld serverWorld) {
            return entityType.create(serverWorld, (spirit) -> {
                spirit.setOwner(owner);
                spirit.setProfile(this);
                spirit.setOwner(owner);
                spirit.refreshPositionAndAngles(spirit.getPos(), spirit.getYaw(), spirit.getPitch());
                serverWorld.spawnEntity(spirit);
            }, owner.getBlockPos(), SpawnReason.MOB_SUMMONED, false, false);
        }
        return null;
    }
}
