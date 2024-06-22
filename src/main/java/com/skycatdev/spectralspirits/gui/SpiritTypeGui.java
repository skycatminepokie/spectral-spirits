package com.skycatdev.spectralspirits.gui;

import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.SpiritEventHandler;
import com.skycatdev.spectralspirits.SpiritProfile;
import com.skycatdev.spectralspirits.ability.FireResistanceAbility;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;
import java.util.Set;

public class SpiritTypeGui extends SimpleGui { // WARN: For testing/alpha purposes only
    /**
     * Constructs a new simple container gui for the supplied player.
     *
     * @param player the player to send this gui to
     */
    public SpiritTypeGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X1, player, false);
        for (var key : SpectralSpirits.SPIRIT_TYPE_REGISTRY.getKeys()) {
            EntityType<? extends SpectralSpiritEntity> spiritType = Objects.requireNonNull(SpectralSpirits.SPIRIT_TYPE_REGISTRY.get(key));
            addSlot(GuiElementBuilder.from(new ItemStack(Items.EMERALD)).setName(spiritType.getName()).setCallback(() -> {
                SpiritEventHandler.summonNew(new SpiritProfile(spiritType, Set.of(new FireResistanceAbility(true, true))), player);
                close();
            }));
        }
    }
}
