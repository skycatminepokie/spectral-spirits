package com.skycatdev.spectralspirits.client.entity;

import com.skycatdev.spectralspirits.SpiritProfile;
import com.skycatdev.spectralspirits.ability.Ability;
import com.skycatdev.spectralspirits.ability.AbilityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public abstract class SpectralSpiritEntity extends MobEntity implements Ownable {
    protected PlayerEntity owner;
    protected Set<Ability> abilities = new HashSet<>();
    protected final EntityType<? extends SpectralSpiritEntity> spiritType;

    public SpectralSpiritEntity(EntityType<? extends SpectralSpiritEntity> entityType, World world) {
        super(entityType, world);
        noClip = true;
        owner = null;
        moveControl = new FlightMoveControl(this, 1, true);
        intersectionChecked = false;
        spiritType = entityType;
        setInvulnerable(true);
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return damageSource.isSourceCreativePlayer() || super.isInvulnerableTo(damageSource);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    /**
     * @return {@link Set#add(Object)}
     */
    public boolean addAbility(Ability ability) {
        return abilities.add(ability);
    }

    public void updateFromProfile(SpiritProfile profile) {
        abilities = profile.abilities();
    }

    public SpiritProfile toProfile() {
        return new SpiritProfile(spiritType, abilities); // TODO
    }

    public boolean hasActiveAbility(AbilityType<?> abilityType) { // TODO: Make this less expensive
        for (Ability ability : abilities) {
            if (ability.isActive() && ability.getType().equals(abilityType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // goalSelector.add(1, new WanderNearTargetGoal(this, 3, 32));
        goalSelector.add(0, new FollowOwnerGoal(this::getOwner));
    }

    @Override
    public void tick() {
        setNoGravity(true);
        super.tick();
    }

    @Nullable
    @Override
    public Entity getOwner() {
        return owner;
    }

    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
    }

    public class FollowOwnerGoal extends Goal {
        public Supplier<Entity> owner;

        public FollowOwnerGoal(Supplier<Entity> owner) {
            this.owner = owner;
        }

        @Override
        public boolean canStart() {
            return owner.get() != null && !getMoveControl().isMoving();
        }

        @Override
        public void tick() {
            Entity owner = this.owner.get();
            // From Leashable#method_61161
            double targetX = owner.getX();
            double targetY = owner.getEyeY();
            double targetZ = owner.getZ();
            double regYaw = owner.getBodyYaw() + 180;

            // Stay on the right-hand side
            targetX += Math.cos(Math.toRadians(regYaw));
            targetZ += Math.sin(Math.toRadians(regYaw));

            double d = (targetX - getX()) / 3f;
            double e = (targetY - getY()) / 15f;
            double g = (targetZ - getZ()) / 3f;
            double newX = Math.copySign(d * d * 0.4, d);
            double newY = Math.copySign(e * e * 0.4, e);
            double newZ = Math.copySign(g * g * 0.4, g);

            // if at correct y level, cancel y
            if (Math.abs(targetY - getY()) < 0.0002) {
                newY = -getVelocity().getY();
            }

            Vec3d addVelocity = getVelocity().add(newX, newY, newZ);
            setVelocity(addVelocity);
            lookAtEntity(owner, 10, 10); // TODO: Look ahead of owner
        }
    }

}
