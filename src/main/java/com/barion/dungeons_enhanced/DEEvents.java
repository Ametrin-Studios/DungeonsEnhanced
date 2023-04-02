package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.access_helpers.BiomeAccessHelper;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.structure.GelStructure;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DEEvents {
    public static class Forge {
        @SubscribeEvent
        public static <C extends IFeatureConfig, S extends GelStructure<C>, T extends StructureRegistrar2<C,S>> void onBiomeLoad(final BiomeLoadingEvent event) {
            for(StructureRegistrar2<?, ?> structure : DEStructures.getAllStructureRegistrars()) {
                BiomeAccessHelper.addStructureIfAllowed(event, (StructureFeature<C, S>) structure.getStructureFeature());
            }
        }
    }
}