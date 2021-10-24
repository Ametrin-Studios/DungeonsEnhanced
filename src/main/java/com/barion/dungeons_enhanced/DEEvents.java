package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.access_helpers.BiomeAccessHelper;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DEEvents {
    private static BiomeLoadingEvent event;
    @SubscribeEvent
    protected static void biomeLoad(final BiomeLoadingEvent event){
        DEEvents.event = event;
        addToBiomes(DEStructures.BattleTower);
        addToBiomes(DEStructures.Castle);
        addToBiomes(DEStructures.CastleB);
        addToBiomes(DEStructures.DesertTemple);
        addToBiomes(DEStructures.DesertTomb);
        addToBiomes(DEStructures.DruidCircle);
        addToBiomes(DEStructures.DungeonVariant);
        addToBiomes(DEStructures.FlyingDutchman);
        addToBiomes(DEStructures.HayStorage);
        addToBiomes(DEStructures.IcePit);
        addToBiomes(DEStructures.JungleMonument);
        addToBiomes(DEStructures.LargeDungeon);
        addToBiomes(DEStructures.MinersHouse);
        addToBiomes(DEStructures.MobTower);
        addToBiomes(DEStructures.MonsterMaze);
        addToBiomes(DEStructures.MushroomHouse);
        addToBiomes(DEStructures.PillagerCamp);
        addToBiomes(DEStructures.RuinedBarn);
        addToBiomes(DEStructures.RuinedHouse);
        addToBiomes(DEStructures.Stables);
        addToBiomes(DEStructures.TallWitchHut);
        addToBiomes(DEStructures.TreeHouse);
        addToBiomes(DEStructures.WatchTower);
        addToBiomes(DEStructures.WitchTower);
    }

    private static <T extends StructureRegistrar2>void addToBiomes(T structure){
        BiomeAccessHelper.addStructureIfAllowed(event, structure.getStructureFeature());
    }
}