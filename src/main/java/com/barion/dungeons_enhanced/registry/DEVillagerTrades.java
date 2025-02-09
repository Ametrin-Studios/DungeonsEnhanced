package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;


public interface DEVillagerTrades {
    VillagerTrades.ItemListing CASTLE_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(13, DETags.Structures.ON_CASTLE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.castle", MapDecorationTypes.TARGET_X, 12, 10);
    VillagerTrades.ItemListing DESERT_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(14, DETags.Structures.ON_DESERT_EXPLORER_MAPS, "filled_map.dungeons_enhanced.desert_temple", MapDecorationTypes.TARGET_X, 12, 10);
    VillagerTrades.ItemListing ELDER_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(13, DETags.Structures.ON_ELDER_EXPLORER_MAPS, "filled_map.dungeons_enhanced.elders_temple", MapDecorationTypes.TARGET_X, 12, 10);
    VillagerTrades.ItemListing MONSTER_MAZE_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(15, DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.monster_maze", MapDecorationTypes.TARGET_X, 12, 10);
}
