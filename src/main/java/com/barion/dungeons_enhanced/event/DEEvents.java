package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.provider.DEAdvancementProvider;
import com.barion.dungeons_enhanced.data.provider.DELootTableProvider;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.structure.prefabs.DECellarStructure;
import com.legacy.structure_gel.access_helpers.JigsawAccessHelper;
import com.legacy.structure_gel.biome_dictionary.BiomeDictionary;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DEEvents {

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event){
        BiomeDictionary.BEACH.biomes(Biomes.STONE_SHORE, Biomes.SNOWY_BEACH);
    }

    @SubscribeEvent
    public static void structureRegistry(final RegistryEvent.Register<Structure<?>> event){
        IForgeRegistry<Structure<?>> registry = event.getRegistry();
        for(StructureRegistrar2<?,?> structure : DEStructures.ALL_STRUCTURE_REGISTRARS){
            structure.handleForge(registry);
        }
        DECellarStructure.init();

        noiseAffecting(
                DEStructures.Castle,
                DEStructures.DruidCircle,
                DEStructures.HayStorage,
                DEStructures.MinersHouse,
                DEStructures.MushroomHouse,
                DEStructures.PillagerCamp,
                DEStructures.RUINED_BUILDING,
                DEStructures.TowerOfTheUndead,
                DEStructures.TreeHouse,
                DEStructures.WatchTower,
                DEStructures.WitchTower
        );
        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new DELootTableProvider(generator));
        generator.addProvider(new DEAdvancementProvider(generator, existingFileHelper));
    }

    private static void noiseAffecting(StructureRegistrar2<?, ?>... structureRegs){
        for (StructureRegistrar2<?, ?> structure: structureRegs) {
            JigsawAccessHelper.addIllagerStructures(structure.getStructure());
        }
    }
}