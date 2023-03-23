package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.DEPools;
import com.barion.dungeons_enhanced.world.DEProcessors;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DungeonsEnhanced.MOD_ID)
public class DungeonsEnhanced{
    public static final String MOD_ID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.register(this);
        modBus.register(DEStructures.class);

        DEStructures.init();
        RegistrarHandler.registerHandlers(MOD_ID, modBus, DEPools.HANDLER);
    }

    public static void register(RegisterEvent event){
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper ->{
            DEProcessors.Types.register();
        });
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();

        var registrarProvider = RegistrarHandler.createGenerator(output, MOD_ID);
        generator.addProvider(event.includeServer(), registrarProvider);

        generator.addProvider(event.includeServer(), new DELootTableProvider(output));
        generator.addProvider(event.includeServer(), new DEAdvancementProvider(output, existingFileHelper));
    }
}