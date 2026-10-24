package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.data.DETags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SequenceFunction;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;

import java.util.List;

public final class DEExplorerMaps {

    public static final LootItemFunction CASTLE = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_CASTLE_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.CASTLE), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction DEEP_CRYPT = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_DEEP_CRYPT_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(true)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.DEEP_CRYPT), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction DESERT_TEMPLE = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_DESERT_TEMPLE_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.DESERT_TEMPLE), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction ELDERS_TEMPLE = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_ELDERS_TEMPLE_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.ELDERS_TEMPLE), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction JUNGLE_MONUMENT = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_JUNGLE_MONUMENT_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.JUNGLE_TEMPLE)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.JUNGLE_MONUMENT), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction MONSTER_MAZE = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.MONSTER_MAZE), SetNameFunction.Target.ITEM_NAME).build()
    ));

    public static final LootItemFunction MUSHROOM_HOUSE = SequenceFunction.of(List.of(
            ExplorationMapFunction.makeExplorationMap()
                    .setDestination(DETags.Structures.ON_MUSHROOM_HOUSE_EXPLORER_MAPS)
                    .setMapDecoration(MapDecorationTypes.TARGET_X)
                    .setSearchRadius(50)
                    .setSkipKnownStructures(false)
                    .build(),
            SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced." + DEStructureIDs.MUSHROOM_HOUSE), SetNameFunction.Target.ITEM_NAME).build()
    ));

}
