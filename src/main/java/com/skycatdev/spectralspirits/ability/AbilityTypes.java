package com.skycatdev.spectralspirits.ability;

import com.mojang.serialization.Lifecycle;
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
    public static final Registry<AbilityType> REGISTRY = new SimpleRegistry<>(RegistryKey.ofRegistry(REGISTRY_ID), Lifecycle.stable());
    public static final AbilityType FIRE_RESISTANCE = register(Identifier.of(MOD_ID, "fire_resistance"), true, new ItemStack(Items.MAGMA_CREAM));

    @Contract("_,_,_->new")
    public static AbilityType register(@NotNull Identifier id, boolean passive, ItemStack icon) {
        AbilityType abilityType = new AbilityType(id, passive, icon);
        Registry.register(REGISTRY, abilityType.getId(), abilityType);
        return abilityType;
    }

}
