package com.skycatdev.spectralspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpectralSpiritEntity extends PathAwareEntity implements Ownable {
    protected PlayerEntity owner;

    public SpectralSpiritEntity(EntityType<? extends SpectralSpiritEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return owner;
    }
}
