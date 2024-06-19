package com.skycatdev.spectralspirits.ability;

import com.mojang.serialization.MapCodec;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AbilityType<T extends Ability> {
    protected @NotNull Identifier id;
    /**
     * {@code true} if this is active in the background, {@code false} if the player must activate it.
     */
    protected boolean ambient;
    protected ItemStack icon;
    protected MapCodec<T> codec;

    protected AbilityType(@NotNull Identifier id, boolean ambient, ItemStack icon, MapCodec<T> codec) {
        this.id = id;
        this.ambient = ambient;
        this.icon = icon;
        this.codec = codec;
    }

    public MapCodec<T> getCodec() {
        return codec;
    }

    @Contract(pure = true)
    public @NotNull Identifier getId() {
        return id;
    }

    @Contract(pure = true)
    public boolean isAmbient() {
        return ambient;
    }

    @Contract("->new")
    public @NotNull ItemStack getIcon() {
        return icon.copy();
    }
}
