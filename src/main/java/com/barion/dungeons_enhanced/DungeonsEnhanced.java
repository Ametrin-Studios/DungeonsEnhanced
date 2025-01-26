package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DEBiomeTagsProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.data.provider.DEStructureTagsProvider;
import com.barion.dungeons_enhanced.registry.*;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.RegistrySetBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";

    public DungeonsEnhanced(IEventBus modEventBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);

        modEventBus.addListener(DungeonsEnhanced::gatherData);
        DEStructures.ALL_STRUCTURE_REGISTRARS.clone();

        RegistrarHandler.registerHandlers(MOD_ID, modEventBus, DETemplatePools.HANDLER, DEProcessorLists.HANDLER, DEJigsawTypes.HANDLER, DELootTableAliases.HANDLER);
    }

    public static void gatherData(GatherDataEvent.Client event) {
        var output = event.getGenerator().getPackOutput();

        var registrarProvider = new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), RegistrarHandler.injectRegistries(new RegistrySetBuilder()), Set.of(DungeonsEnhanced.MOD_ID));
        event.addProvider(registrarProvider);

        event.createProvider(DEBiomeTagsProvider::new);
        event.createProvider(DELootTableProvider::new);
        event.createProvider(DEAdvancementProvider::new);
        event.createProvider(DEStructureTagsProvider::new);
    }
}