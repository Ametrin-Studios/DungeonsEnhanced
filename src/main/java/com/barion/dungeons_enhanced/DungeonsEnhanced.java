package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.*;
import com.barion.dungeons_enhanced.registry.*;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";

    public DungeonsEnhanced(IEventBus modEventBus, ModContainer container) {
        modEventBus.addListener(DungeonsEnhanced::gatherData);
        RegistrarHandler.registerHandlers(MOD_ID, modEventBus, DEJigsawTypes.HANDLER, DELootTableAliases.HANDLER, DEDynamicSpawners.HANDLER);
    }

    public static void gatherData(GatherDataEvent.Server event) {
        event.createDatapackRegistryObjects(RegistrarHandler.injectRegistries(new RegistrySetBuilder()
                .add(Registries.VILLAGER_TRADE, DEVillagerTrades::bootstrap)
                .add(Registries.PROCESSOR_LIST, DEProcessorLists::bootstrap)
                .add(Registries.TEMPLATE_POOL, DETemplatePools::bootstrap)
        ));

        event.createProvider(DEBiomeTagsProvider::new);
        event.createProvider(DEVillagerTradesTagsProvider::new);
        event.createProvider(DELootTableProvider::new);
        // event.createProvider(StructureNbtUpdater::new);
        event.createProvider(DEAdvancementProvider::new);
        event.createProvider(DEStructureTagsProvider::new);

    }

    public static Identifier locate(String path) {
        return Identifier.fromNamespaceAndPath(DungeonsEnhanced.MOD_ID, path);
    }
}