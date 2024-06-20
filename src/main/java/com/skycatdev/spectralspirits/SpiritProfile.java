package com.skycatdev.spectralspirits;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.skycatdev.spectralspirits.ability.Ability;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public record SpiritProfile(EntityType<? extends SpectralSpiritEntity> type, Set<Ability> abilities) {
    public static final Codec<SpiritProfile> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            SpectralSpirits.SPIRIT_TYPE_REGISTRY.getCodec().fieldOf("type").forGetter(SpiritProfile::type),
            Ability.CODEC.listOf().xmap(SpiritProfile::abilityListToSet, SpiritProfile::abilitySetToList).fieldOf("abilities").forGetter(SpiritProfile::abilities)
    ).apply(instance, SpiritProfile::new));

    private static Set<Ability> abilityListToSet(List<Ability> list) {
        return Set.copyOf(list);
    }

    private static List<Ability> abilitySetToList(Set<Ability> set) {
        return List.copyOf(set);
    }

    public @Nullable SpectralSpiritEntity spawnEntity(World world, PlayerEntity owner) {
        if (world instanceof ServerWorld serverWorld) {
            return type.create(serverWorld, (spirit) -> {
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
