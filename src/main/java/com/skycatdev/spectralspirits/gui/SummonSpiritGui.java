package com.skycatdev.spectralspirits.gui;

import com.skycatdev.spectralspirits.SpectralSpiritHolder;
import com.skycatdev.spectralspirits.SpectralSpirits;
import com.skycatdev.spectralspirits.SpiritEventHandler;
import com.skycatdev.spectralspirits.SpiritProfile;
import com.skycatdev.spectralspirits.entity.SpectralSpiritEntity;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;

public class SummonSpiritGui extends SimpleGui {
    protected SpectralSpiritEntity currentSpirit;
    /**
     * Constructs a new simple container gui for the supplied player.
     *
     * @param player                the player to send this gui to
     */
    public SummonSpiritGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X1, player, false);
        currentSpirit = ((SpectralSpiritHolder) player).spectral_spirits$getSpirit();
        // TODO: Handle generating these builders in SpiritProfile
        var testSpiritElement = GuiElementBuilder.from(new ItemStack(Items.BARRIER));
        var fireSpiritElement = GuiElementBuilder.from(new ItemStack(Items.FIRE_CHARGE));
        if (currentSpirit.getType().equals(SpectralSpirits.FIRE_SPIRIT)) {
            addSlot(fireSpiritElement.glow());
            addSlot(testSpiritElement);
        } else {
            addSlot(testSpiritElement.glow());
            addSlot(fireSpiritElement);
        }
    }

    protected void onSelectSpiritType(SpiritProfile profile) {
        SpiritEventHandler.summon(profile, player);
    }

}
