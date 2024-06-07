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
        setNoGravity(true);
        noClip = true;
    }

    @Override
    public boolean hasNoGravity() { // TODO later: Check if there's a better way to do this. Vexes just set it every tick.
        return true;
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return owner;
    }
}
