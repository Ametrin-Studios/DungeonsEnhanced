package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.access_helpers.BiomeAccessHelper;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.structure.GelStructure;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class DEEvents {
    private static BiomeLoadingEvent event;
    @SubscribeEvent
    protected static void biomeLoad(final BiomeLoadingEvent event){
        DEEvents.event = event;
        addToBiome(DEStructures.TowerOfTheUndead);
        addToBiome(DEStructures.Castle);
        addToBiome(DEStructures.CastleB);
        addToBiome(DEStructures.DesertTemple);
        addToBiome(DEStructures.DesertTomb);
        addToBiome(DEStructures.DruidCircle);
        addToBiome(DEStructures.DungeonVariant);
        //addToBiomes(DEStructures.FlyingDutchman);
        addToBiome(DEStructures.HayStorage);
        addToBiome(DEStructures.IcePit);
        addToBiome(DEStructures.JungleMonument);
        addToBiome(DEStructures.LargeDungeon);
        addToBiome(DEStructures.MinersHouse);
        addToBiome(DEStructures.MobTower);
        addToBiome(DEStructures.MonsterMaze);
        addToBiome(DEStructures.MushroomHouse);
        addToBiome(DEStructures.PillagerCamp);
        addToBiome(DEStructures.RuinedBarn);
        addToBiome(DEStructures.RuinedHouse);
        addToBiome(DEStructures.Stables);
        addToBiome(DEStructures.TallWitchHut);
        addToBiome(DEStructures.TreeHouse);
        addToBiome(DEStructures.WatchTower);
        addToBiome(DEStructures.WitchTower);
    }

    private static <C extends IFeatureConfig, S extends GelStructure<C>, T extends StructureRegistrar2<C, S>>void addToBiome(T structure){
        BiomeAccessHelper.addStructureIfAllowed(event, Objects.requireNonNull(structure.getStructureFeature()));
    }
}