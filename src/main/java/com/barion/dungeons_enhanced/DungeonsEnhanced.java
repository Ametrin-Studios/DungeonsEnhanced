package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DEBiomeTagsProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.data.provider.DEStructureTagsProvider;
import com.barion.dungeons_enhanced.registry.*;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";

    public DungeonsEnhanced(IEventBus modEventBus, ModContainer container) {
//        container.registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);

        modEventBus.addListener(DungeonsEnhanced::gatherData);

        RegistrarHandler.registerHandlers(MOD_ID, modEventBus, DETemplatePools.HANDLER, DEProcessorLists.HANDLER, DEJigsawTypes.HANDLER, DELootTableAliases.HANDLER, DEDynamicSpawners.HANDLER);
    }

    public static void gatherData(GatherDataEvent.Server event) {
        var output = event.getGenerator().getPackOutput();

        var registrarProvider = new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), RegistrarHandler.injectRegistries(new RegistrySetBuilder()), Set.of(DungeonsEnhanced.MOD_ID));
        var lookup = registrarProvider.getRegistryProvider();

        event.addProvider(registrarProvider);

        event.createProvider(DEBiomeTagsProvider::new);
        event.createProvider(DELootTableProvider::new);
//        event.createProvider(StructureNbtUpdater::new);
        event.addProvider(new DEAdvancementProvider(output, lookup));
        event.addProvider(new DEStructureTagsProvider(output, lookup));
    }

    public static ResourceLocation locate(String path) {
        return ResourceLocation.fromNamespaceAndPath(DungeonsEnhanced.MOD_ID, path);
    }
}