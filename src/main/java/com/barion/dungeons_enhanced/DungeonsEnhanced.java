package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DEBiomeTagsProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.data.provider.DEStructureTagsProvider;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.barion.dungeons_enhanced.world.structure.processor.DEProcessors;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.RegistrySetBuilder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@Mod(DungeonsEnhanced.MOD_ID)
public class DungeonsEnhanced{
    public static final String MOD_ID = "dungeons_enhanced";

    public DungeonsEnhanced(IEventBus modEventBus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
//        Reflection.initialize(DEStructures.class, DEProcessors.class, DETemplatePools.class);

        modEventBus.addListener(DungeonsEnhanced::gatherData);

        RegistrarHandler.registerHandlers(MOD_ID, modEventBus, DETemplatePools.HANDLER, DEProcessors.HANDLER);
    }

    public static void gatherData(GatherDataEvent event){
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var runServer = event.includeServer();

        var registrarProvider = new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), RegistrarHandler.injectRegistries(new RegistrySetBuilder()), Set.of(DungeonsEnhanced.MOD_ID));
        generator.addProvider(runServer, registrarProvider);
        var lookup = registrarProvider.getRegistryProvider();

        generator.addProvider(runServer, new DEBiomeTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(runServer, new DELootTableProvider(output));
        generator.addProvider(runServer, new DEAdvancementProvider(output, existingFileHelper));
        generator.addProvider(runServer, new DEStructureTagsProvider(output, lookup, existingFileHelper));
    }
}