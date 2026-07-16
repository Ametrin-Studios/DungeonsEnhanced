package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.DiscardItem;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.FilteredFunction;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;

import java.util.List;
import java.util.Optional;

public interface DEVillagerTrades {
    ResourceKey<VillagerTrade> ELDER_EXPLORER_MAP_TRADE = resourceKey("cartographer/2/emerald_and_compass_elder_explorer_map");
    ResourceKey<VillagerTrade> CASTLE_EXPLORER_MAP_TRADE = resourceKey("cartographer/3/emerald_and_compass_castle_explorer_map");
    ResourceKey<VillagerTrade> MONSTER_MAZE_EXPLORER_MAP_TRADE = resourceKey("cartographer/4/emerald_and_compass_monster_maze_explorer_map");
    ResourceKey<VillagerTrade> DESERT_EXPLORER_MAP_TRADE = resourceKey("cartographer/5/emerald_and_compass_desert_explorer_map");

    static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);

        context.register(ELDER_EXPLORER_MAP_TRADE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 13),
                Optional.of(new TradeCost(Items.COMPASS, 1)),
                new ItemStackTemplate(Items.MAP),
                12,
                10,
                0.2F,
                Optional.empty(),
                List.of(
                        ExplorationMapFunction.makeExplorationMap()
                                .setDestination(DETags.Structures.ON_ELDER_EXPLORER_MAPS)
                                .setMapDecoration(MapDecorationTypes.TARGET_X)
                                .setSearchRadius(100)
                                .setSkipKnownStructures(false)
                                .build(),
                        SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced.elders_temple"), SetNameFunction.Target.ITEM_NAME).build(),
                        FilteredFunction.filtered(
                                        new ItemPredicate.Builder()
                                                .of(items, Items.FILLED_MAP)
                                                .withComponents(DataComponentMatchers.Builder.components().any(DataComponents.MAP_ID).build())
                                                .build()
                                )
                                .onFail(Optional.of(DiscardItem.discardItem().build()))
                                .build()
                )
        ));

        context.register(CASTLE_EXPLORER_MAP_TRADE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 13),
                Optional.of(new TradeCost(Items.COMPASS, 1)),
                new ItemStackTemplate(Items.MAP),
                12,
                10,
                0.2F,
                Optional.empty(),
                List.of(
                        ExplorationMapFunction.makeExplorationMap()
                                .setDestination(DETags.Structures.ON_CASTLE_EXPLORER_MAPS)
                                .setMapDecoration(MapDecorationTypes.TARGET_X)
                                .setSearchRadius(100)
                                .setSkipKnownStructures(false)
                                .build(),
                        SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced.castle"), SetNameFunction.Target.ITEM_NAME).build(),
                        FilteredFunction.filtered(
                                        new ItemPredicate.Builder()
                                                .of(items, Items.FILLED_MAP)
                                                .withComponents(DataComponentMatchers.Builder.components().any(DataComponents.MAP_ID).build())
                                                .build()
                                )
                                .onFail(Optional.of(DiscardItem.discardItem().build()))
                                .build()
                )
        ));

        context.register(MONSTER_MAZE_EXPLORER_MAP_TRADE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 15),
                Optional.of(new TradeCost(Items.COMPASS, 1)),
                new ItemStackTemplate(Items.MAP),
                12,
                10,
                0.2F,
                Optional.empty(),
                List.of(
                        ExplorationMapFunction.makeExplorationMap()
                                .setDestination(DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS)
                                .setMapDecoration(MapDecorationTypes.TARGET_X)
                                .setSearchRadius(100)
                                .setSkipKnownStructures(false)
                                .build(),
                        SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced.monster_maze"), SetNameFunction.Target.ITEM_NAME).build(),
                        FilteredFunction.filtered(
                                        new ItemPredicate.Builder()
                                                .of(items, Items.FILLED_MAP)
                                                .withComponents(DataComponentMatchers.Builder.components().any(DataComponents.MAP_ID).build())
                                                .build()
                                )
                                .onFail(Optional.of(DiscardItem.discardItem().build()))
                                .build()
                )
        ));

        context.register(DESERT_EXPLORER_MAP_TRADE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 14),
                Optional.of(new TradeCost(Items.COMPASS, 1)),
                new ItemStackTemplate(Items.MAP),
                12,
                10,
                0.2F,
                Optional.empty(),
                List.of(
                        ExplorationMapFunction.makeExplorationMap()
                                .setDestination(DETags.Structures.ON_DESERT_EXPLORER_MAPS)
                                .setMapDecoration(MapDecorationTypes.TARGET_X)
                                .setSearchRadius(100)
                                .setSkipKnownStructures(false)
                                .build(),
                        SetNameFunction.setName(Component.translatable("filled_map.dungeons_enhanced.desert_temple"), SetNameFunction.Target.ITEM_NAME).build(),
                        FilteredFunction.filtered(
                                        new ItemPredicate.Builder()
                                                .of(items, Items.FILLED_MAP)
                                                .withComponents(DataComponentMatchers.Builder.components().any(DataComponents.MAP_ID).build())
                                                .build()
                                )
                                .onFail(Optional.of(DiscardItem.discardItem().build()))
                                .build()
                )
        ));

        return null;
    }

    static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, DungeonsEnhanced.locate(path));
    }
}
