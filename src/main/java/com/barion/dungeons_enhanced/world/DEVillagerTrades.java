package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

public interface DEVillagerTrades {
    VillagerTrades.ItemListing CASTLE_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(13, DETags.Structures.ON_CASTLE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.castle", MapDecoration.Type.TARGET_X, 12, 10);
    VillagerTrades.ItemListing DESERT_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(14, DETags.Structures.ON_DESERT_EXPLORER_MAPS, "filled_map.dungeons_enhanced.desert_temple", MapDecoration.Type.TARGET_X, 12, 10);
    VillagerTrades.ItemListing MONSTER_MAZE_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(14, DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.monster_maze", MapDecoration.Type.TARGET_X, 12, 10);
}
