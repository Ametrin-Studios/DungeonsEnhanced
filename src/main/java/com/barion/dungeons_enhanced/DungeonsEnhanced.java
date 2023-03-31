package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DEBiomeTagsProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.data.provider.DEStructureTagsProvider;
import com.barion.dungeons_enhanced.world.DEJigsawTypes;
import com.barion.dungeons_enhanced.world.DELootTables;
import com.barion.dungeons_enhanced.world.DEPools;
import com.barion.dungeons_enhanced.world.structure.processor.DEProcessors;
import com.google.common.reflect.Reflection;
import com.legacy.structure_gel.api.events.RegisterLootTableAliasEvent;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.barion.dungeons_enhanced.DEUtil.location;

@Mod(DungeonsEnhanced.MOD_ID)
public class DungeonsEnhanced{
    public static final String MOD_ID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        Reflection.initialize(DEStructures.class, DEProcessors.class, DEPools.class);

        modBus.addListener(DungeonsEnhanced::register);
        modBus.addListener(DungeonsEnhanced::gatherData);
        modBus.addListener(DEJigsawTypes::register);
        modBus.addListener(DungeonsEnhanced::registerLootTableAlias);

        RegistrarHandler.registerHandlers(MOD_ID, modBus, DEPools.HANDLER, DEProcessors.HANDLER);
    }

    public static void register(RegisterEvent event){
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper ->{
            DEProcessors.Types.register();
        });
    }

    public static void gatherData(GatherDataEvent event){
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

    public static void registerLootTableAlias(final RegisterLootTableAliasEvent event){
        event.register(location("sunken_shrine"), DELootTables.SUNKEN_SHRINE);
        event.register(location("miners_house"), DELootTables.MINERS_HOUSE);
    }
}