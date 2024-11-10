package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DEBiomeTagsProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.data.provider.DEStructureTagsProvider;
import com.barion.dungeons_enhanced.registry.*;
import com.google.common.reflect.Reflection;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        Reflection.initialize(DEStructures.class, DEProcessors.class, DETemplatePools.class);

        modBus.addListener(DungeonsEnhanced::gatherData);
        modBus.addListener(DEJigsawTypes::register);

        RegistrarHandler.registerHandlers(MOD_ID, modBus, DETemplatePools.HANDLER, DEProcessorLists.HANDLER);
    }

    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var runServer = event.includeServer();

        var registrarProvider = RegistrarHandler.createGenerator(output, MOD_ID);
        generator.addProvider(event.includeServer(), registrarProvider);
        var lookup = registrarProvider.getLookupProvider();

        generator.addProvider(runServer, new DEBiomeTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(runServer, new DELootTableProvider(output));
        generator.addProvider(runServer, new DEAdvancementProvider(output, existingFileHelper));
        generator.addProvider(runServer, new DEStructureTagsProvider(output, lookup, existingFileHelper));
    }
}