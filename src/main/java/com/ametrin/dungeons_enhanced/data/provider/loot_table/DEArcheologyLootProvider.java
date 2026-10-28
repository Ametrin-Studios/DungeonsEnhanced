package com.ametrin.dungeons_enhanced.data.provider.loot_table;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.item;
import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.one;

public record DEArcheologyLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.JungleMonument.ARCHEOLOGY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.SNIFFER_EGG))
                        .add(item(Items.DIAMOND))
                        .add(item(Items.EMERALD))
                        .add(item(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE))
                )

        );
    }
}
