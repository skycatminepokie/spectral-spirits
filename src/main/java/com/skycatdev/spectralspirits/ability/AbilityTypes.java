package com.skycatdev.spectralspirits.ability;

import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static com.skycatdev.spectralspirits.SpectralSpirits.MOD_ID;

public class AbilityTypes {
    public static final Identifier REGISTRY_ID = Identifier.of(MOD_ID, "ability_type");
    public static final Registry<AbilityType<? extends Ability>> REGISTRY = new SimpleRegistry<>(RegistryKey.ofRegistry(REGISTRY_ID), Lifecycle.stable());
    public static final AbilityType<FireResistanceAbility> FIRE_RESISTANCE = register(Identifier.of(MOD_ID, "fire_resistance"), true, new ItemStack(Items.MAGMA_CREAM), FireResistanceAbility.CODEC);

    @Contract("_,_,_,_->new")
    public static <T extends Ability> AbilityType<T> register(@NotNull Identifier id, boolean passive, ItemStack icon, MapCodec<T> codec) {
        AbilityType<T> abilityType = new AbilityType<>(id, passive, icon, codec);
        Registry.register(REGISTRY, abilityType.getId(), abilityType);
        return abilityType;
    }

}
