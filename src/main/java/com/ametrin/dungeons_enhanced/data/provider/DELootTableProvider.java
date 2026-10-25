package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.data.provider.loot_table.chest.DECastleChestLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.chest.DEMonsterMazeChestLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.chest.DEStructureChestLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.equipment.DECastleEquipmentLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.equipment.DEMonsterMazeEquipmentLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.equipment.DEStructureEquipmentLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class DELootTableProvider extends LootTableProvider {

    public DELootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        var tables = List.of(
                new SubProviderEntry(DEStructureChestLootProvider::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DECastleChestLootProvider::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DEMonsterMazeChestLootProvider::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DEStructureEquipmentLootProvider::new, LootContextParamSets.EQUIPMENT),
                new SubProviderEntry(DECastleEquipmentLootProvider::new, LootContextParamSets.EQUIPMENT),
                new SubProviderEntry(DEMonsterMazeEquipmentLootProvider::new, LootContextParamSets.EQUIPMENT)
        );
        super(output, Set.of(), tables, registries);
    }
}