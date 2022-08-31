package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
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

@Mod(DungeonsEnhanced.ModID)
public class DungeonsEnhanced{
    public static final String ModID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.register(this);
        modBus.register(DEStructures.class);

        DEStructures.init();
        RegistrarHandler.registerHandlers(ModID, modBus);
    }

    public static void register(RegisterEvent event){
        event.register(Registry.STRUCTURE_PROCESSOR.key(), helper ->{
            DEUtil.Processors.Types.register();
        });
    }

    @Mod.EventBusSubscriber(modid = DungeonsEnhanced.ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class DataGenerators{
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event){
            DataGenerator dataGen = event.getGenerator();
            ExistingFileHelper exFileHelper = event.getExistingFileHelper();

            dataGen.addProvider(true, new DELootTableProvider(dataGen));
            dataGen.addProvider(true, new DEAdvancementProvider(dataGen, exFileHelper));
        }
    }
}