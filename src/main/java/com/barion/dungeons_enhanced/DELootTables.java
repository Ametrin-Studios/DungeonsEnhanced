package com.barion.dungeons_enhanced;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DELootTables extends LootTableProvider {
    public DELootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(DEStructureLootTables::new, LootParameterSets.CHEST));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationTracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationTracker, p_218436_2_, p_218436_3_));
    }

    public static class DEStructureLootTables extends ChestLootTables{
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
            super.accept(lootTable);
            lootTable.accept(location("flying_dutchman"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(RandomValueRange.between(5, 6))
                            .add(ItemLootEntry.lootTableItem(Items.DIAMOND).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1,2))))
                            .add(ItemLootEntry.lootTableItem(Items.BONE).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                            .add(ItemLootEntry.lootTableItem(Items.ARROW).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                            .add(ItemLootEntry.lootTableItem(Items.KELP).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1,3))))
                            .add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(1,3))))
                            .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(2,3))))
                            .add(ItemLootEntry.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(1,2))))));

            lootTable.accept(location("monster_maze/church"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(RandomValueRange.between(5, 6))
                            .add(ItemLootEntry.lootTableItem(Items.DIAMOND).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(1,2))))
                            .add(ItemLootEntry.lootTableItem(Items.BONE).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                            .add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(1,3))))
                            .add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(6,12))))
                            .add(ItemLootEntry.lootTableItem(Items.GOLD_BLOCK).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1,1))))
                            .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(2,3))))));

        }
        private static ResourceLocation location(String name){
            return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + name);
        }
    }
}