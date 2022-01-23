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
        public static void addStructuresToBiomes(final AddStructureToBiomeEvent event) {
            addStructureToBiomeEvent = event;
            addToBiomes(DEStructures.Castle);
            addToBiomes(DEStructures.DesertTemple);
            addToBiomes(DEStructures.DesertTomb);
            addToBiomes(DEStructures.DruidCircle);
            addToBiomes(DEStructures.DungeonVariant);
            //addToBiomes(DEStructures.FlyingDutchman);
            addToBiomes(DEStructures.HayStorage);
            addToBiomes(DEStructures.IcePit);
            addToBiomes(DEStructures.JungleMonument);
            addToBiomes(DEStructures.LargeDungeon);
            addToBiomes(DEStructures.MinersHouse);
            addToBiomes(DEStructures.MonsterMaze);
            addToBiomes(DEStructures.MushroomHouse);
            addToBiomes(DEStructures.PillagerCamp);
            addToBiomes(DEStructures.RuinedBuilding);
            addToBiomes(DEStructures.Stables);
            addToBiomes(DEStructures.TallWitchHut);
            addToBiomes(DEStructures.TreeHouse);
            addToBiomes(DEStructures.TowerOfTheUndead);
            addToBiomes(DEStructures.WatchTower);
            addToBiomes(DEStructures.WitchTower);

            addToBiomes(DEStructures.Debug);
        }
        private static <C extends FeatureConfiguration, S extends StructureFeature<C> & IConfigStructure, T extends StructureRegistrar<C,S>>void addToBiomes(T structure){
            addStructureToBiomeEvent.register(structure.getStructureFeature());
        }
    }
}