package com.barion.dungeons_enhanced;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(DEStructureLootTables::new, LootContextParamSets.CHEST));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTables.validate(validationContext, p_218436_2_, p_218436_3_));
    }

    public static class DEStructureLootTables extends ChestLoot {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
            super.accept(lootTable);
            lootTable.accept(location("flying_dutchman"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5, 6))
                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2))))
                            .add(LootItem.lootTableItem(Items.BONE).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                            .add(LootItem.lootTableItem(Items.ARROW).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                            .add(LootItem.lootTableItem(Items.KELP).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
                            .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
                            .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3))))
                            .add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2))))));

            lootTable.accept(location("monster_maze/church"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5, 6))
                            .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,2))))
                            .add(LootItem.lootTableItem(Items.BONE).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                            .add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3))))
                            .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(6,12))))
                            .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(2).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,1))))
                            .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3))))));

        }
        private static ResourceLocation location(String name){
            return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + name);
        }
    }
}