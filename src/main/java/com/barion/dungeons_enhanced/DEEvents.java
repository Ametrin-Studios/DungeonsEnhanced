package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.access_helpers.BiomeAccessHelper;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.structure.GelStructure;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class DEEvents {
    public static class Forge {
        private static BiomeLoadingEvent biomeLoadingEvent;

        @SubscribeEvent
        public static <C extends IFeatureConfig, S extends GelStructure<C>, T extends StructureRegistrar2<C,S>> void onBiomeLoad(final BiomeLoadingEvent event) {
            biomeLoadingEvent = event;
            for(StructureRegistrar2<?,?> structure : DEStructures.getAllStructureRegistrars()) {
                addToBiomes((T) structure);
            }
        }
        private static <C extends IFeatureConfig, S extends GelStructure<C>, T extends StructureRegistrar2<C,S>>void addToBiomes(T structure){
            BiomeAccessHelper.addStructureIfAllowed(biomeLoadingEvent, Objects.requireNonNull(structure.getStructureFeature()));
        }
    }
}