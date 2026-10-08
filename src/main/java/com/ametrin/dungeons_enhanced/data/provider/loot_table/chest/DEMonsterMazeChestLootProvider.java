package com.ametrin.dungeons_enhanced.data.provider.loot_table.chest;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;


public record DEMonsterMazeChestLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.MonsterMaze.BREWERY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(10, 15))
                        .add(item(Items.REDSTONE).setWeight(2))
                        .add(item(Items.SUGAR).setWeight(2))
                        .add(item(Items.GLOWSTONE_DUST).setWeight(2))
                        .add(item(Items.SPIDER_EYE).setWeight(2))
                        .add(item(Items.POISONOUS_POTATO))
                        .add(item(Items.RABBIT_FOOT))
                        .add(item(Items.EXPERIENCE_BOTTLE))
                        .add(item(Items.BROWN_MUSHROOM).setWeight(2))
                        .add(item(Items.AMETHYST_SHARD).setWeight(2))
                        .add(item(Items.GLISTERING_MELON_SLICE))
                        .add(item(Items.PHANTOM_MEMBRANE))
                        .add(item(Items.GOLDEN_CARROT).setWeight(2))
                        .add(item(Items.FERMENTED_SPIDER_EYE).setWeight(2))
                        .add(item(Items.GUNPOWDER).setWeight(2))
                        .add(item(Items.TURTLE_SCUTE).setWeight(2))
                )
                .withPool(LootPool.lootPool().setRolls(between(0, 3))
                        .add(tag(ItemTags.CANDLES, 1, between(0, 1)))
                )
                .withPool(LootPool.lootPool().setRolls(between(0, 2))
                        .add(potion(Potions.HEALING, between(0, 1)))
                        .add(potion(Potions.INVISIBILITY, between(0, 1)))
                        .add(potion(Potions.LEAPING, between(0, 1)))
                        .add(potion(Potions.NIGHT_VISION, between(0, 1)))
                        .add(potion(Potions.REGENERATION, between(0, 1)))
                        .add(potion(Potions.SLOW_FALLING, between(0, 1)))
                        .add(potion(Potions.STRENGTH, between(0, 1)))
                        .add(potion(Potions.WATER_BREATHING, between(0, 1)))
                        .add(potion(Potions.FIRE_RESISTANCE, between(0, 1)))
                        .add(potion(Potions.OOZING, between(0, 1)))
                )
        );

        output.accept(DELootTables.MonsterMaze.CHURCH, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(5, 7))
                        .add(item(Items.DIAMOND, between(1, 2)).setWeight(1))
                        .add(item(Items.BONE, between(1, 3)).setWeight(4))
                        .add(item(Items.PUMPKIN_SEEDS, between(2, 4)).setWeight(6))
                        .add(item(Items.BOOK, between(1, 3)).setWeight(2))
                        .add(item(Items.ROTTEN_FLESH, between(1, 3)).setWeight(4))
                        .add(item(Items.EGG, between(1, 3)).setWeight(3))
                        .add(item(Items.SUGAR, between(1, 3)).setWeight(4))
                        .add(item(Items.SUGAR_CANE, between(1, 2)).setWeight(2))
                        .add(item(Items.GOLD_NUGGET, between(4, 10)).setWeight(4))
                        .add(item(Items.GOLD_BLOCK))
                        .add(item(Items.PUMPKIN).setWeight(2))
                        .add(enchantedItem(Items.BOOK, one(), registries, between(6, 14)))
                        .add(item(Items.GOLD_INGOT, between(2, 3)).setWeight(4))
                )
        );

        output.accept(DELootTables.MonsterMaze.PRISON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(10, 6))
                        .add(item(Items.PAPER).setWeight(2))
                        .add(item(Items.IRON_CHAIN).setWeight(2))
                        .add(item(Items.ROTTEN_FLESH).setWeight(3))
                        .add(item(Items.POTATO).setWeight(2))
                        .add(item(Items.POISONOUS_POTATO).setWeight(2))
                        .add(item(Items.STRING).setWeight(2))
                        .add(item(Items.BONE).setWeight(3))
                        .add(item(Items.MAP))
                        .add(item(Items.LEAD))
                        .add(item(Items.MUSHROOM_STEW))
                        .add(item(Items.CANDLE))
                        .add(suspiciousStew(one()).setWeight(2))
                        .add(item(Items.BOWL))
                        .add(potion(Potions.WEAKNESS, between(0, 1)))
                        .add(potion(Potions.STRENGTH, between(0, 1)))
                )
                .withPool(LootPool.lootPool().setRolls(between(0, 1))
                        .add(enchantedItem(Items.STONE_PICKAXE, one(), registries, between(5, 10)).setWeight(5))
                        .add(enchantedItem(Items.GOLDEN_PICKAXE, one(), registries, between(4, 9)).setWeight(6))
                        .add(enchantedItem(Items.COPPER_PICKAXE, one(), registries, between(3, 8)).setWeight(2))
                        .add(enchantedItem(Items.IRON_PICKAXE, one(), registries, between(3, 8)).setWeight(2))
                        .add(enchantedItem(Items.DIAMOND_PICKAXE, one(), registries, between(2, 7)).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(between(0, 2))
                        .add(potion(Potions.HEALING, one()))
                        .add(potion(Potions.INVISIBILITY, one()))
                        .add(potion(Potions.LEAPING, one()))
                        .add(potion(Potions.NIGHT_VISION, one()))
                        .add(potion(Potions.REGENERATION, one()))
                        .add(potion(Potions.SLOW_FALLING, one()))
                        .add(potion(Potions.STRENGTH, one()))
                        .add(potion(Potions.WATER_BREATHING, one()))
                        .add(potion(Potions.FIRE_RESISTANCE, one()))
                        .add(item(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE))
                        .add(empty(4))
                )
        );

        output.accept(DELootTables.MonsterMaze.TREASURE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(4, 9))
                        .add(item(Items.BONE, between(2, 4)).setWeight(3))
                        .add(item(Items.ROTTEN_FLESH, between(1, 2)).setWeight(4))
                        .add(item(Items.STRING, between(1, 2)).setWeight(3))
                        .add(item(Items.COBWEB, between(1, 2)).setWeight(2))
                        .add(item(Items.SLIME_BALL, between(1, 2)).setWeight(2))
                        .add(item(Items.CLOCK))
                        .add(item(Items.COMPASS))
                        .add(item(Items.MAP).setWeight(2))
                        .add(item(Items.EMERALD, between(1, 2)).setWeight(2))
                        .add(item(Items.IRON_NUGGET, between(2, 5)).setWeight(2))
                        .add(item(Items.GOLD_NUGGET, between(2, 5)).setWeight(2))
                )
                .withPool(LootPool.lootPool().setRolls(between(4, 6))
                        .add(item(Items.GOLD_INGOT, between(2, 4)).setWeight(2))
                        .add(item(Items.EXPERIENCE_BOTTLE).setWeight(3))
                        .add(item(Items.DIAMOND).setWeight(2))
                        .add(item(Items.GOLD_BLOCK).setWeight(2))
                        .add(item(Items.BOOK).setWeight(2).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(10, 22)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.BOW).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.CROSSBOW).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.DIAMOND_HELMET).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.DIAMOND_CHESTPLATE).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.DIAMOND_LEGGINGS).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.DIAMOND_BOOTS).apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(12, 16)).when(LootItemRandomChanceCondition.randomChance(0.7f))))
                        .add(item(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE))
                        .add(item(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE))
                )
                .withPool(LootPool.lootPool().setRolls(between(0, 1))
                        .add(item(Items.MUSIC_DISC_11))
                        .add(item(Items.MUSIC_DISC_BLOCKS))
                        .add(item(Items.MUSIC_DISC_CAT))
                        .add(item(Items.MUSIC_DISC_CHIRP))
                        .add(item(Items.MUSIC_DISC_FAR))
                        .add(item(Items.MUSIC_DISC_MALL))
                        .add(item(Items.MUSIC_DISC_MELLOHI))
                        .add(item(Items.MUSIC_DISC_STAL))
                        .add(item(Items.MUSIC_DISC_STRAD))
                        .add(item(Items.MUSIC_DISC_PRECIPICE))
                        .add(item(Items.MUSIC_DISC_WAIT))
                        .add(item(Items.MUSIC_DISC_WARD))
                        .add(item(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE))
                )
        );
    }
}
