package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.registry.DEVillagerTrades;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VillagerTradesTagsProvider;
import net.minecraft.tags.VillagerTradeTags;

import java.util.concurrent.CompletableFuture;

public final class DEVillagerTradesTagsProvider extends VillagerTradesTagsProvider {
    public DEVillagerTradesTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(VillagerTradeTags.CARTOGRAPHER_LEVEL_2)
                .add(DEVillagerTrades.ELDER_EXPLORER_MAP_TRADE)
        ;

        tag(VillagerTradeTags.CARTOGRAPHER_LEVEL_3)
                .add(DEVillagerTrades.CASTLE_EXPLORER_MAP_TRADE)
        ;

        tag(VillagerTradeTags.CARTOGRAPHER_LEVEL_4)
                .add(DEVillagerTrades.MONSTER_MAZE_EXPLORER_MAP_TRADE)
        ;

        tag(VillagerTradeTags.CARTOGRAPHER_LEVEL_5)
                .add(DEVillagerTrades.DESERT_EXPLORER_MAP_TRADE)
        ;
    }
}
