package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.data.DETags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public interface DEVillagerTrades {
    VillagerTrades.ItemListing ELDERS_EXPLORER_MAP_TRADE = explorerMap(13, DETags.Structures.ON_ELDERS_TEMPLE_EXPLORER_MAPS, DEStructureIDs.ELDERS_TEMPLE);
    VillagerTrades.ItemListing CASTLE_EXPLORER_MAP_TRADE = explorerMap(13, DETags.Structures.ON_CASTLE_EXPLORER_MAPS, DEStructureIDs.CASTLE);
    VillagerTrades.ItemListing MONSTER_MAZE_EXPLORER_MAP_TRADE = explorerMap(15, DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS, DEStructureIDs.MONSTER_MAZE);
    VillagerTrades.ItemListing DESERT_EXPLORER_MAP_TRADE = explorerMap(14, DETags.Structures.ON_DESERT_TEMPLE_EXPLORER_MAPS, DEStructureIDs.DESERT_TEMPLE);

    static void addTrades(VillagerTradesEvent event) {
        if (event.getType() != VillagerProfession.CARTOGRAPHER) return;

        var trades = event.getTrades();
        trades.get(2).add(ELDERS_EXPLORER_MAP_TRADE);
        trades.get(3).add(CASTLE_EXPLORER_MAP_TRADE);
        trades.get(4).add(MONSTER_MAZE_EXPLORER_MAP_TRADE);
        trades.get(5).add(DESERT_EXPLORER_MAP_TRADE);
    }

    private static VillagerTrades.ItemListing explorerMap(int emeraldCost, TagKey<Structure> destination, String structureId) {
        return new VillagerTrades.TreasureMapForEmeralds(emeraldCost, destination, "filled_map.dungeons_enhanced." + structureId, MapDecorationTypes.TARGET_X, 12, 10);
    }
}
