package com.ametrin.dungeons_enhanced;

import com.ametrin.dungeons_enhanced.data.provider.*;
import com.ametrin.dungeons_enhanced.registry.*;
import com.ametrin.structures.registry.ASRegistries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";

    public DungeonsEnhanced(IEventBus modEventBus, ModContainer container) {
        modEventBus.addListener(DungeonsEnhanced::gatherData);
        DEPieceSources.REGISTER.register(modEventBus);
        DEProcessorTypes.REGISTER.register(modEventBus);
    }

    public static void gatherData(GatherDataEvent event) {
        var builder = new RegistrySetBuilder()
                .add(Registries.PROCESSOR_LIST, DEProcessorLists::bootstrap)
                .add(Registries.TEMPLATE_POOL, DETemplatePools::bootstrap)
                .add(ASRegistries.FIXTURE_PRESET, DEFixturePresets::bootstrap)
                .add(ASRegistries.SPAWNER_PROFILE, DESpawnerProfiles::bootstrap);
        DEStructures.BOOTSTRAP.addTo(builder);

        event.createDatapackRegistryObjects(builder);

        event.createProvider(DEBiomeTagsProvider::new);
        event.createProvider(DELootTableProvider::new);
        event.createProvider(DEAdvancementProvider::new);
        event.createProvider(DEStructureTagsProvider::new);
        event.createProvider(DEDataMapProvider::new);
//        event.addProvider(new StructureTemplateUpdater(event.getInputs()));
    }

    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(DungeonsEnhanced.MOD_ID, path);
    }
}