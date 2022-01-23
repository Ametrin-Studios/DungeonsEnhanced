package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.biome_dictionary.BiomeDictionary;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
        BiomeDictionary.BEACH.biomes(Biomes.STONY_SHORE, Biomes.SNOWY_BEACH);
    }
}