package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.DiscardItem;
import net.minecraft.world.level.storage.loot.functions.FilteredFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.List;
import java.util.Optional;

public interface DEVillagerTrades {
    ResourceKey<VillagerTrade> ELDERS_EXPLORER_MAP_TRADE = resourceKey("cartographer/2/emerald_and_compass_elders_explorer_map");
    ResourceKey<VillagerTrade> CASTLE_EXPLORER_MAP_TRADE = resourceKey("cartographer/3/emerald_and_compass_castle_explorer_map");
    ResourceKey<VillagerTrade> MONSTER_MAZE_EXPLORER_MAP_TRADE = resourceKey("cartographer/4/emerald_and_compass_monster_maze_explorer_map");
    ResourceKey<VillagerTrade> DESERT_EXPLORER_MAP_TRADE = resourceKey("cartographer/5/emerald_and_compass_desert_explorer_map");

    static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);

        context.register(ELDERS_EXPLORER_MAP_TRADE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 13),
                Optional.of(new TradeCost(Items.COMPASS, 1)),
                new ItemStackTemplate(Items.MAP),
                12,
                10,
                0.2F,
                Optional.empty(),
                List.of(
                        DEExplorerMaps.ELDERS_TEMPLE,
                        removeNotFound(items)
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
                        DEExplorerMaps.CASTLE,
                        removeNotFound(items)
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
                        DEExplorerMaps.MONSTER_MAZE,
                        removeNotFound(items)
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
                        DEExplorerMaps.DESERT_TEMPLE,
                        removeNotFound(items)
                )
        ));

        return null;
    }

    static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, DungeonsEnhanced.locate(path));
    }

    static LootItemFunction removeNotFound(HolderGetter<Item> items) {
        return FilteredFunction.filtered(
                        new ItemPredicate.Builder()
                                .of(items, Items.FILLED_MAP)
                                .withComponents(DataComponentMatchers.Builder.components().any(DataComponents.MAP_ID).build())
                                .build()
                )
                .onFail(Optional.of(DiscardItem.discardItem().build()))
                .build();
    }
}
