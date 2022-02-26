package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.events.AddStructureToBiomeEvent;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.base.IConfigStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DEEvents {
    public static class Forge {
        private static AddStructureToBiomeEvent addStructureToBiomeEvent;

        @SubscribeEvent
        public static <C extends FeatureConfiguration, S extends StructureFeature<C> & IConfigStructure, T extends StructureRegistrar<C,S>> void addStructuresToBiomes(final AddStructureToBiomeEvent event) {
            addStructureToBiomeEvent = event;
            for(StructureRegistrar<?,?> structure : DEStructures.getAllStructureRegistrars()) {
                addToBiomes((T) structure);
            }
        }
        private static <C extends FeatureConfiguration, S extends StructureFeature<C> & IConfigStructure, T extends StructureRegistrar<C,S>>void addToBiomes(T structure){
            addStructureToBiomeEvent.register(structure.getStructureFeature());
        }
    }
}