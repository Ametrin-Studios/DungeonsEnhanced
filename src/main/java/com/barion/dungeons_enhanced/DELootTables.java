package com.barion.dungeons_enhanced;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
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

    @Override @ParametersAreNonnullByDefault
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTables.validate(validationContext, p_218436_2_, p_218436_3_));
    }

    public static class DEStructureLootTables extends ChestLoot {
        @Override @ParametersAreNonnullByDefault
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
            super.accept(lootTable);
            lootTable.accept(location("flying_dutchman"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5, 9))
                            .add(lootItem(Items.SKULL_BANNER_PATTERN, 1, 1))
                            .add(lootItem(Items.NAUTILUS_SHELL, 2, 1))
                            .add(lootItem(Items.TURTLE_EGG, 1, 1))
                            .add(lootItem(Items.DIAMOND, 2, 1))
                            .add(lootItem(Items.ROTTEN_FLESH, 8, 2, 5))
                            .add(lootItem(Items.BONE, 8, 2, 4))
                            .add(lootItem(Items.FIRE_CHARGE, 4, 1, 3))
                            .add(lootItem(Items.EMERALD, 4, 1, 3))
                            .add(lootItem(Items.COOKED_COD, 5, 1, 3))
                            .add(lootItem(Items.COOKED_SALMON, 5, 1, 3))
                            .add(lootItem(Items.ARROW, 4, 1, 3))
                            .add(lootItem(Items.STRING, 5, 1, 3))
                            .add(lootItem(Items.KELP, 8, 2, 5))
                            .add(lootItem(Items.GOLD_INGOT, 3, 1, 2))
                            .add(lootItem(Items.GOLD_BLOCK, 1, 1))
                            .add(lootItem(Items.SUSPICIOUS_STEW, 3, 1, 2))));

            lootTable.accept(location("monster_maze/church"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5, 7))
                            .add(lootItem(Items.DIAMOND, 1, 1, 2))
                            .add(lootItem(Items.BONE, 6, 1, 3))
                            .add(lootItem(Items.BOOK, 2, 1, 3))
                            .add(lootItem(Items.ROTTEN_FLESH, 4, 1, 3))
                            .add(lootItem(Items.GOLD_NUGGET, 6, 4, 10))
                            .add(lootItem(Items.GOLD_BLOCK, 1, 1))
                            .add(lootItem(Items.GOLD_INGOT, 4, 2, 3))));

        }

        private LootPoolEntryContainer.Builder<?> lootItem(Item item, int weight, int amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount)));
        }

        private LootPoolEntryContainer.Builder<?> lootItem(Item item, int weight, int minAmount, int maxAmount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(minAmount, maxAmount)));
        }

        private static ResourceLocation location(String name){
            return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + name);
        }
    }
}