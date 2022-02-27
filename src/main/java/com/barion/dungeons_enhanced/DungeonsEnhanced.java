package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.biome_dictionary.BiomeDictionary;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DungeonsEnhanced.Mod_ID)
public class DungeonsEnhanced{
    public static final String Mod_ID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.register(this);
        modBus.register(DEStructures.class);
        modBus.register(DEEvents.class);
        forgeBus.register(DEEvents.Forge.class);

        modBus.addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event){
        BiomeDictionary.BEACH.biomes(Biomes.STONE_SHORE, Biomes.SNOWY_BEACH);
    }

    @Mod.EventBusSubscriber(modid = DungeonsEnhanced.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class DataGenerators{
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event){
            DataGenerator dataGen = event.getGenerator();
            ExistingFileHelper exFileHelper = event.getExistingFileHelper();

            dataGen.addProvider(new DELootTableProvider(dataGen));
            dataGen.addProvider(new DEAdvancementProvider(dataGen, exFileHelper));
        }
    }
}