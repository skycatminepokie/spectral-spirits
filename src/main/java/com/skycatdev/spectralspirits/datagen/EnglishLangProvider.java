package com.skycatdev.spectralspirits.datagen;

import com.skycatdev.spectralspirits.SpectralSpirits;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends FabricLanguageProvider {
    protected EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder tb) {
        tb.add(SpectralSpirits.FIRE_SPIRIT, "Fire Spirit");
        tb.add(SpectralSpirits.TEST_SPECTRAL_SPIRIT, "Test Spirit");
    }
}
