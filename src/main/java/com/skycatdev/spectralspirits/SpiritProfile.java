package com.skycatdev.spectralspirits;

import com.mojang.serialization.Codec;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class SpiritProfile {
    protected String name;
    public static final Codec<SpiritProfile> CODEC = Codec.STRING.xmap(SpiritProfile::new, SpiritProfile::getName);

    public SpiritProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <T extends SpectralSpiritEntity> void createEntity(EntityType<T> entityType, World world, PlayerEntity owner) {
        if (world instanceof ServerWorld serverWorld) {
            var entity = entityType.create(serverWorld, (e) -> e.setOwner(owner), owner.getBlockPos(), SpawnReason.MOB_SUMMONED, false, false);
            if (entity != null) {
                entity.setProfile(this);
                entity.refreshPositionAndAngles(entity.getPos(), entity.getYaw(), entity.getPitch());
                serverWorld.spawnEntity(entity);
            }
            // Spawn entity and passengers
        }

    }
}
