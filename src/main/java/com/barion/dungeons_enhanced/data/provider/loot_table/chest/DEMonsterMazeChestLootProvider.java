package com.barion.dungeons_enhanced.data.provider.loot_table.chest;

import com.barion.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static com.barion.dungeons_enhanced.data.provider.loot_table.DELootTableProviderHelper.*;

public record DEMonsterMazeChestLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.MonsterMaze.BREWERY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(number(10, 15))
                        .add(item(Items.REDSTONE, 2))
                        .add(item(Items.SUGAR, 2))
                        .add(item(Items.GLOWSTONE_DUST, 2))
                        .add(item(Items.SPIDER_EYE, 2))
                        .add(item(Items.POISONOUS_POTATO, 1))
                        .add(item(Items.RABBIT_FOOT, 1))
                        .add(item(Items.EXPERIENCE_BOTTLE, 1))
                        .add(item(Items.BROWN_MUSHROOM, 2))
                        .add(item(Items.AMETHYST_SHARD, 2))
                        .add(item(Items.GLISTERING_MELON_SLICE, 1))
                        .add(item(Items.PHANTOM_MEMBRANE, 1))
                        .add(item(Items.GOLDEN_CARROT, 2))
                        .add(item(Items.FERMENTED_SPIDER_EYE, 2))
                        .add(item(Items.GUNPOWDER, 2))
                        .add(item(Items.TURTLE_SCUTE, 2))
                )
                .withPool(LootPool.lootPool().setRolls(number(0, 3))
                        .add(tag(ItemTags.CANDLES, 1, number(0, 1)))
                )
                .withPool(LootPool.lootPool().setRolls(number(0, 2))
                        .add(potion(1, Potions.HEALING, number(0, 1)))
                        .add(potion(1, Potions.INVISIBILITY, number(0, 1)))
                        .add(potion(1, Potions.LEAPING, number(0, 1)))
                        .add(potion(1, Potions.NIGHT_VISION, number(0, 1)))
                        .add(potion(1, Potions.REGENERATION, number(0, 1)))
                        .add(potion(1, Potions.SLOW_FALLING, number(0, 1)))
                        .add(potion(1, Potions.STRENGTH, number(0, 1)))
                        .add(potion(1, Potions.WATER_BREATHING, number(0, 1)))
                        .add(potion(1, Potions.FIRE_RESISTANCE, number(0, 1)))
                        .add(potion(1, Potions.OOZING, number(0, 1)))
                )
        );

        output.accept(DELootTables.MonsterMaze.CHURCH, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(number(5, 7))
                        .add(item(Items.DIAMOND, 1, number(1, 2)))
                        .add(item(Items.BONE, 4, number(1, 3)))
                        .add(item(Items.PUMPKIN_SEEDS, 6, number(2, 4)))
                        .add(item(Items.BOOK, 2, number(1, 3)))
                        .add(item(Items.ROTTEN_FLESH, 4, number(1, 3)))
                        .add(item(Items.EGG, 3, number(1, 3)))
                        .add(item(Items.SUGAR, 4, number(1, 3)))
                        .add(item(Items.SUGAR_CANE, 2, number(1, 2)))
                        .add(item(Items.GOLD_NUGGET, 4, number(4, 10)))
                        .add(item(Items.GOLD_BLOCK, 1))
                        .add(item(Items.PUMPKIN, 2))
                        .add(enchantedItem(Items.BOOK, 1, number(6, 14), registries))
                        .add(item(Items.GOLD_INGOT, 4, number(2, 3)))
                )
        );
    }
}
