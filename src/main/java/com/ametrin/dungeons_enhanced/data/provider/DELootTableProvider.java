package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.DEEquipmentLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.chest.DECastleChestLootProvider;
import com.ametrin.dungeons_enhanced.data.provider.loot_table.chest.DEMonsterMazeChestLootProvider;
import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetOminousBottleAmplifierFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;

public final class DELootTableProvider extends LootTableProvider {
    private static List<SubProviderEntry> tables;

    public DELootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), tables, registries);
        tables = List.of(
                new SubProviderEntry(DEStructureLootTables::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DECastleChestLootProvider::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DEMonsterMazeChestLootProvider::new, LootContextParamSets.CHEST),
                new SubProviderEntry(DEEquipmentLootProvider::new, LootContextParamSets.EQUIPMENT)
        );
    }

    public record DEStructureLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            {
                output.accept(DELootTables.DEEP_CRYPT, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 13))
                                .add(item(Items.DIAMOND).setWeight(2))
                                .add(item(Items.BONE, 8, number(1, 3)))
                                .add(item(Items.BONE_MEAL, 3, number(1, 2)))
                                .add(item(Items.COBWEB, 4, number(1, 2)))
                                .add(item(Items.STRING, 6, number(1, 2)))
                                .add(item(Items.SPIDER_EYE, 3, number(1, 2)))
                                .add(item(Items.BOOK, 4, number(1, 3)))
                                .add(item(Items.WRITABLE_BOOK).setWeight(2))
                                .add(item(Items.CANDLE, 2, number(1, 2)))
                                .add(item(Items.WHITE_CANDLE).setWeight(2))
                                .add(item(Items.ROTTEN_FLESH, 3, number(1, 3)))
                                .add(item(Items.GLOW_BERRIES, 4, number(1, 3)))
                                .add(item(Items.IRON_CHAIN, 5, number(1, 3)))
                                .add(item(Items.SKULL_BANNER_PATTERN))
                                .add(item(Items.EMERALD, 3, number(1, 4)))
                                .add(item(Items.GOLD_INGOT, 4, number(1, 5)))
                                .add(item(Items.IRON_INGOT, 2, number(1, 2)))
                                .add(item(Items.MAP, 2, number(1, 2)))
                                .add(item(Items.PAPER, 4, number(1, 3)))
                                .add(enchantedItem(Items.BOOK, 2, number(1, 2), registries))
                                .add(item(Items.GOLDEN_APPLE, 1, number(1, 2)))
                                .add(item(Items.WITHER_ROSE))
                                .add(item(Items.CHAINMAIL_BOOTS).setWeight(2))
                                .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(2))
                                .add(item(Items.CHAINMAIL_HELMET).setWeight(2))
                                .add(item(Items.CHAINMAIL_LEGGINGS).setWeight(2))
                                .add(item(Items.IRON_HELMET))
                                .add(item(Items.IRON_CHESTPLATE))
                                .add(item(Items.IRON_LEGGINGS))
                                .add(item(Items.IRON_BOOTS))
                                .add(item(Items.STONE_SWORD, 3, number(1, 2)))
                                .add(item(Items.DEEPSLATE, 5, number(1, 2)))
                                .add(item(Items.COBBLED_DEEPSLATE, 5, number(1, 2)))
                                .add(item(Items.CLOCK, 2, number(1, 2)))));
            } // Deep Crypt
            {
                output.accept(DELootTables.DESERT_TOMB, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(4, 6))
                                .add(item(Items.GOLD_NUGGET, 5, number(4, 7)))
                                .add(item(Items.GOLD_INGOT, 2, number(1, 3)))
                                .add(item(Items.GOLDEN_APPLE))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(item(Items.STRING, 7, number(2, 4)))
                                .add(item(Items.GUNPOWDER, 7, number(2, 4)))
                                .add(item(Items.REDSTONE, 4, number(2, 3)))
                                .add(item(Items.LEATHER, 4, number(1, 5)))
                                .add(item(Items.SAND, 15, number(2, 5))))
                        .withPool(LootPool.lootPool().setRolls(number(0, 1))
                                .add(enchantedItem(Items.BOOK, 1, number(5, 18), registries))
                                .add(enchantedItem(Items.GOLDEN_SWORD, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_PICKAXE, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_AXE, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_HELMET, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_CHESTPLATE, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_LEGGINGS, 1, number(0, 7), registries))
                                .add(enchantedItem(Items.GOLDEN_BOOTS, 1, number(0, 7), registries))
                        ));
            } // Desert Tomb
            {
                output.accept(DELootTables.EldersTemple.MAIN, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.COD).setWeight(3))
                                .add(item(Items.SALMON).setWeight(3))
                                .add(item(Items.PUFFERFISH))
                                .add(item(Items.BRAIN_CORAL, 1, number(0, 1)))
                                .add(item(Items.BUBBLE_CORAL, 1, number(0, 1)))
                                .add(item(Items.FIRE_CORAL, 1, number(0, 1)))
                                .add(item(Items.HORN_CORAL, 1, number(0, 1)))
                                .add(item(Items.TUBE_CORAL, 1, number(0, 1)))
                                .add(item(Items.NAUTILUS_SHELL, 2, number(0, 1)))
                                .add(item(Items.EMERALD, 3, number(0, 3)))
                                .add(item(Items.TURTLE_SCUTE, 1, number(0, 1)))
                                .add(item(Items.PRISMARINE_SHARD, 3, number(0, 3)))
                                .add(item(Items.GOLDEN_APPLE, 1, number(0, 2)))
                                .add(item(Items.GOLD_NUGGET, 2, number(0, 3)))
                                .add(item(Items.DIAMOND, 1, number(0, 1)))
                                .add(item(Items.TURTLE_EGG, 1, number(0, 1)))
                                .add(item(Items.INK_SAC, 1, number(0, 1)))
                                .add(item(Items.NAME_TAG, 1, number(0, 1)))
                                .add(item(Items.COPPER_INGOT, 4, number(0, 3)))
                                .add(potion(1, Potions.WATER_BREATHING, one()))
                                .add(item(Items.PRISMARINE_CRYSTALS, 2, number(0, 3)))
                                .add(item(Items.SEA_PICKLE, 2, number(0, 2)))
                        ));

                output.accept(DELootTables.EldersTemple.ELDER_ROOM, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.NAUTILUS_SHELL, 2, number(0, 1)))
                                .add(item(Items.EMERALD, 2, number(0, 3)))
                                .add(item(Items.TURTLE_SCUTE, 1, number(0, 1)))
                                .add(item(Items.PRISMARINE_SHARD, 3, number(0, 3)))
                                .add(item(Items.GOLDEN_APPLE, 2, number(0, 2)))
                                .add(item(Items.GOLD_NUGGET, 2, number(0, 3)))
                                .add(item(Items.GOLD_INGOT, 1, number(0, 2)))
                                .add(item(Items.HEART_OF_THE_SEA, 1, number(0, 1)))
                                .add(item(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE))
                                .add(item(Items.TRIDENT, 1, number(0, 1)))
                                .add(item(Items.DIAMOND, 1, number(0, 2)))
                                .add(item(Items.TURTLE_EGG, 1, number(0, 1)))
                                .add(item(Items.COPPER_INGOT, 4, number(0, 3)))
                                .add(potion(1, Potions.LONG_WATER_BREATHING, one()))
                                .add(item(Items.PRISMARINE_CRYSTALS, 3, number(0, 3)))
                                .add(item(Items.COPPER_NAUTILUS_ARMOR, 1, number(0, 1)))
                        ));
            } // Elders Temple

            {
                output.accept(DELootTables.FLYING_DUTCHMAN, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(7, 9))
                                .add(item(Items.SKULL_BANNER_PATTERN))
                                .add(item(Items.NAUTILUS_SHELL).setWeight(2))
                                .add(item(Items.TURTLE_EGG))
                                .add(item(Items.TURTLE_SCUTE))
                                .add(item(Items.EXPERIENCE_BOTTLE))
                                .add(item(Items.DIAMOND).setWeight(2))
                                .add(item(Items.ROTTEN_FLESH, 8, number(2, 5)))
                                .add(item(Items.BONE, 8, number(1, 4)))
                                .add(item(Items.FIRE_CHARGE, 4, number(1, 3)))
                                .add(item(Items.EMERALD, 4, number(1, 3)))
                                .add(item(Items.COOKED_COD, 5, number(1, 3)))
                                .add(item(Items.COOKED_SALMON, 5, number(1, 3)))
                                .add(item(Items.ARROW, 4, number(2, 4)))
                                .add(item(Items.STRING, 5, number(1, 4)))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 14), registries))
                                .add(item(Items.KELP, 8, number(2, 5)))
                                .add(item(Items.GOLD_INGOT, 3, number(1, 2)))
                                .add(suspiciousStew(3, one()))
                                .add(item(Items.SPYGLASS))
                        ));
            } // Flying Dutchman
            {
                output.accept(DELootTables.LARGE_DUNGEON, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 14))
                                .add(item(Items.IRON_INGOT).setWeight(3))
                                .add(item(Items.IRON_NUGGET, 6, number(1, 3)))
                                .add(item(Items.GOLD_INGOT).setWeight(3))
                                .add(item(Items.GOLD_NUGGET, 6, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH).setWeight(13))
                                .add(item(Items.BONE).setWeight(10))
                                .add(item(Items.BROWN_MUSHROOM).setWeight(4))
                                .add(item(Items.RED_MUSHROOM).setWeight(4))
                                .add(item(Items.CARROT).setWeight(4))
                                .add(item(Items.POTATO).setWeight(4))
                                .add(item(Items.POISONOUS_POTATO).setWeight(6))
                                .add(item(Items.STRING).setWeight(7))
                                .add(suspiciousStew(3, one()))
                                .add(item(Items.SPIDER_EYE).setWeight(3)))
                        .withPool(LootPool.lootPool().setRolls(number(0, 2))
                                .add(item(Items.DIAMOND).setWeight(2))
                                .add(item(Items.GOLDEN_APPLE).setWeight(2))
                                .add(enchantedItem(Items.IRON_HELMET, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_CHESTPLATE, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_LEGGINGS, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_BOOTS, 2, number(4, 12), registries))
                                .add(item(Items.DIAMOND_AXE))
                                .add(item(Items.DIAMOND_SWORD))
                                .add(item(Items.DIAMOND_PICKAXE))
                                .add(item(Items.DIAMOND_HELMET))
                                .add(item(Items.DIAMOND_CHESTPLATE))
                                .add(item(Items.DIAMOND_LEGGINGS))
                                .add(item(Items.DIAMOND_BOOTS))
                        ));
            } // Large Dungeon

            MinersHouseLoot(output);

            {
                output.accept(DELootTables.PIRATE_SHIP, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.COD).setWeight(3))
                                .add(item(Items.SALMON).setWeight(3))
                                .add(item(Items.STRING).setWeight(4))
                                .add(item(Items.FISHING_ROD))
                                .add(item(Items.NAUTILUS_SHELL, 1, number(0, 1)))
                                .add(item(Items.BOWL, 1, number(0, 1)))
                                .add(item(Items.LEATHER, 2, number(0, 2)))
                                .add(item(Items.EMERALD, 3, number(0, 3)))
                                .add(item(Items.BREAD, 2, number(0, 2)))
                                .add(item(Items.PAPER, 1, number(0, 2)))
                                .add(item(Items.SPYGLASS, 1, number(0, 1)))
                                .add(item(Items.MAP, 2, number(0, 1)))
                                .add(item(Items.IRON_AXE, 1, number(0, 1)))
                                .add(item(Items.ARROW, 3, number(0, 4)))
                                .add(item(Items.CROSSBOW, 1, number(0, 1)))
                                .add(item(Items.TURTLE_SCUTE, 1, number(0, 1)))
                                .add(item(Items.PRISMARINE_SHARD, 2, number(0, 3)))
                                .add(item(Items.IRON_SWORD, 1, number(0, 1)))
                                .add(item(Items.GOLDEN_APPLE, 1, number(0, 2)))
                                .add(item(Items.GOLD_NUGGET, 2, number(0, 3)))
                        )
                );
            } // Pirate Ship
            {
                output.accept(DELootTables.PillagerCamp.KITCHEN, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(12, 19))
                                .add(item(Items.COOKED_MUTTON))
                                .add(item(Items.COOKED_BEEF))
                                .add(item(Items.COOKED_PORKCHOP))
                                .add(item(Items.COOKED_CHICKEN))
                                .add(item(Items.COOKED_RABBIT))
                                .add(item(Items.COOKED_SALMON))
                                .add(item(Items.COOKED_COD))
                                .add(item(Items.BAKED_POTATO))
                                .add(item(Items.MUTTON))
                                .add(item(Items.BEEF))
                                .add(item(Items.PORKCHOP))
                                .add(item(Items.CHICKEN))
                                .add(item(Items.RABBIT))
                                .add(item(Items.SALMON))
                                .add(item(Items.COD))
                                .add(item(Items.POTATO))
                                .add(item(Items.CARROT))
                                .add(item(Items.WHEAT, 1, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 1, number(1, 3)))
                        ));

                output.accept(DELootTables.PillagerCamp.GENERAL, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(6, 16))
                                .add(item(Items.MAP))
                                .add(item(Items.EMERALD))
                                .add(item(Items.PAPER).setWeight(7))
                                .add(item(Items.GOLDEN_CARROT).setWeight(2))
                                .add(item(Items.GOLDEN_APPLE).setWeight(2))
                                .add(item(Items.COMPASS))
                                .add(item(Items.CLOCK))
                                .add(item(Items.IRON_AXE).setWeight(2))
                                .add(item(Items.CROSSBOW))
                                .add(item(Items.ARROW).setWeight(5))
                                .add(item(Items.DIAMOND, 1, number(0, 1)))
                                .add(item(Items.IRON_INGOT).setWeight(2))
                        ));
            } // Pillager Camp
            {
                output.accept(DELootTables.Ruined.DEFAULT, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(2, 4))
                                .add(item(Items.IRON_NUGGET, 6, number(2, 5)))
                                .add(item(Items.IRON_INGOT).setWeight(2))
                                .add(item(Items.GOLD_NUGGET, 4, number(1, 5)))
                                .add(item(Items.CAKE))
                                .add(item(Items.FIELD_MASONED_BANNER_PATTERN))
                                .add(item(Items.BUNDLE))
                                .add(item(Items.GOLDEN_APPLE)))
                        .withPool(LootPool.lootPool().setRolls(number(6, 10))
                                .add(item(Items.STICK, 6, number(1, 3)))
                                .add(item(Items.PAPER, 2, number(1, 2)))
                                .add(item(Items.BOOK))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(suspiciousStew(2, one()))
                                .add(item(Items.WHEAT, 5, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 5)))
                                .add(item(Items.MELON_SEEDS, 5, number(1, 3)))
                                .add(item(Items.POTATO, 4, number(1, 2)))
                                .add(item(Items.POISONOUS_POTATO, 6, number(1, 3)))
                                .add(item(Items.CARROT, 4, number(1, 3)))
                                .add(item(Items.PUMPKIN_SEEDS, 5, number(1, 4)))
                                .add(item(Items.GOLDEN_CARROT))
                        ));
            } // Ruined Building
            {
                output.accept(DELootTables.TreeHouse.ROOF, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(4, 8))
                                .add(item(Items.DIAMOND))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 3)))
                                .add(item(Items.IRON_INGOT).setWeight(3))
                                .add(item(Items.SPYGLASS))
                                .add(tag(ItemTags.BUNDLES))
                                .add(item(Items.COCOA_BEANS, 6, number(1, 2)))
                                .add(item(Items.MELON_SEEDS, 6, number(1, 3)))
                        ));
            } // Tree House
            {
                output.accept(DELootTables.TowerOfTheUndead.TREASURE, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(10, 18))
                                .add(item(Items.GOLD_NUGGET, 5, number(1, 2)))
                                .add(item(Items.GOLD_INGOT).setWeight(3))
                                .add(item(Items.COPPER_NUGGET, 5, number(1, 2)))
                                .add(item(Items.COPPER_INGOT).setWeight(3))
                                .add(item(Items.EXPERIENCE_BOTTLE).setWeight(3))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 2)))
                                .add(item(Items.IRON_INGOT).setWeight(2))
                                .add(item(Items.GOLDEN_CARROT).setWeight(2))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 3)))
                                .add(item(Items.WHEAT, 6, number(1, 3)))
                                .add(item(Items.STRING, 6, number(1, 3)))
                                .add(item(Items.BONE, 7, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH, 10, number(1, 3)))
                                .add(item(Items.IRON_AXE))
                                .add(item(Items.IRON_SWORD))
                                .add(item(Items.COPPER_SWORD))
                                .add(item(Items.COPPER_AXE))
                                .add(item(Items.CROSSBOW))
                                .add(item(Items.MAP))
                                .add(item(Items.COBWEB).setWeight(4))
                                .add(item(Items.GOLDEN_APPLE).setWeight(2))
                                .add(item(Items.ARROW, 6, number(2, 3)))
                        )
                        .withPool(LootPool.lootPool().setRolls(number(1, 3))
                                .add(item(Items.LEATHER_HELMET).setWeight(3))
                                .add(item(Items.LEATHER_CHESTPLATE).setWeight(3))
                                .add(item(Items.LEATHER_LEGGINGS).setWeight(3))
                                .add(item(Items.LEATHER_BOOTS).setWeight(3))
                                .add(item(Items.CHAINMAIL_HELMET).setWeight(2))
                                .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(2))
                                .add(item(Items.CHAINMAIL_LEGGINGS).setWeight(2))
                                .add(item(Items.CHAINMAIL_BOOTS).setWeight(2))
                                .add(item(Items.COPPER_HELMET))
                                .add(item(Items.COPPER_CHESTPLATE))
                                .add(item(Items.COPPER_LEGGINGS))
                                .add(item(Items.COPPER_BOOTS))
                                .add(item(Items.IRON_HELMET))
                                .add(item(Items.IRON_CHESTPLATE))
                                .add(item(Items.IRON_LEGGINGS))
                                .add(item(Items.IRON_BOOTS))
                                .add(enchantedItem(Items.BOOK, 1, number(4, 10), registries))
                        )
                );
            } // Tower of the Undead
            {
                output.accept(DELootTables.SUNKEN_SHRINE, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.COD).setWeight(3))
                                .add(item(Items.SALMON).setWeight(3))
                                .add(item(Items.PUFFERFISH))
                                .add(item(Items.BRAIN_CORAL, 1, number(0, 1)))
                                .add(item(Items.BUBBLE_CORAL, 1, number(0, 1)))
                                .add(item(Items.FIRE_CORAL, 1, number(0, 1)))
                                .add(item(Items.HORN_CORAL, 1, number(0, 1)))
                                .add(item(Items.TUBE_CORAL, 1, number(0, 1)))
                                .add(item(Items.NAUTILUS_SHELL, 2, number(0, 1)))
                                .add(item(Items.EMERALD, 3, number(0, 3)))
                                .add(item(Items.TURTLE_SCUTE, 1, number(0, 1)))
                                .add(item(Items.PRISMARINE_SHARD, 3, number(0, 3)))
                                .add(item(Items.GOLDEN_APPLE, 1, number(0, 2)))
                                .add(item(Items.GOLD_NUGGET, 2, number(0, 3)))
                                .add(item(Items.DIAMOND, 1, number(0, 1)))
                                .add(item(Items.TURTLE_EGG, 1, number(0, 1)))
                                .add(item(Items.INK_SAC, 1, number(0, 1)))
                                .add(item(Items.NAME_TAG, 1, number(0, 1)))
                                .add(item(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, 1, number(0, 1)))
                                .add(item(Items.COPPER_INGOT, 4, number(0, 3)))
                                .add(potion(1, Potions.WATER_BREATHING, number(0, 1)))
                                .add(item(Items.PRISMARINE_CRYSTALS, 2, number(0, 3)))
                                .add(item(Items.SEA_PICKLE, 2, number(0, 3)))
                        ));
            } // Sunken Shrine
            {
                output.accept(DELootTables.WATCH_TOWER, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(4, 10))
                                .add(item(Items.IRON_CHAIN, 5, number(1, 3)))
                                .add(item(Items.IRON_NUGGET, 6, number(3, 5)))
                                .add(item(Items.IRON_INGOT, 3, number(1, 2)))
                                .add(item(Items.STICK, 8, number(2, 5)))
                                .add(item(Items.ROTTEN_FLESH, 7, number(3, 6)))
                                .add(item(Items.COBBLESTONE, 6, number(2, 4)))
                                .add(item(Items.ARROW, 4, number(5, 9)))
                                .add(item(Items.EXPERIENCE_BOTTLE).setWeight(2))
                                .add(item(Items.STRING, 8, number(1, 3)))
                                .add(item(Items.BREAD, 4, number(1, 3)))
                                .add(suspiciousStew(1, one()))
                                .add(item(Items.MAP).setWeight(2))
                                .add(item(Items.LEATHER_HELMET))
                                .add(item(Items.LEATHER_CHESTPLATE))
                                .add(item(Items.LEATHER_LEGGINGS))
                                .add(item(Items.LEATHER_BOOTS))
                        )
                        .withPool(LootPool.lootPool().setRolls(number(1, 3))
                                .add(item(Items.DIAMOND))
                                .add(item(Items.SHIELD))
                                .add(item(Items.BOW))
                                .add(item(Items.CROSSBOW))
                                .add(item(Items.IRON_SWORD))
                                .add(item(Items.IRON_AXE))
                                .add(item(Items.IRON_HELMET))
                                .add(item(Items.IRON_CHESTPLATE))
                                .add(item(Items.IRON_LEGGINGS))
                                .add(item(Items.IRON_BOOTS))
                                .add(item(Items.CHAINMAIL_HELMET))
                                .add(item(Items.CHAINMAIL_CHESTPLATE))
                                .add(item(Items.CHAINMAIL_LEGGINGS))
                                .add(item(Items.CHAINMAIL_BOOTS))
                                .add(item(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE))
                        ).withPool(LootPool.lootPool().setRolls(one())
                                .add(item(Items.SPYGLASS))
                        )
                );
            } // Watch Tower
            {
                output.accept(DELootTables.WITCH_TOWER, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(7, 10))
                                .add(item(Items.SPIDER_EYE, 1, number(2, 3)))
                                .add(item(Items.GUNPOWDER, 1, number(1, 2)))
                                .add(item(Items.REDSTONE, 1, number(1, 2)))
                                .add(item(Items.RABBIT_HIDE, 1, number(1, 2)))
                                .add(item(Items.BEETROOT_SOUP, 1, number(1, 2)))
                                .add(item(Items.EXPERIENCE_BOTTLE, 1, number(0, 1)))
                                .add(item(Items.LEAD, 1, number(0, 1)))
                                .add(item(Items.CLOCK, 1, number(0, 1)))
                                .add(item(Items.SUGAR, 1, number(1, 2)))
                                .add(item(Items.PAPER, 1, number(1, 3)))
                                .add(item(Items.STRING, 2, number(2, 3)))
                                .add(item(Items.BOOK)))
                        .withPool(LootPool.lootPool().setRolls(number(2, 4))
                                .add(item(Items.RABBIT_FOOT))
                                .add(item(Items.NAME_TAG))
                                .add(item(Items.GOLDEN_APPLE))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 13), registries))));
            } // Witch Tower

            output.accept(DELootTables.DungeonVariant.BREWING_STAND, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(number(0, 3))
                            .add(potion(1, Potions.OOZING, one()))
                            .add(potion(1, Potions.WEAVING, one()))
                            .add(potion(1, Potions.WIND_CHARGED, one()))
                            .add(potion(1, Potions.HEALING, one()))
                            .add(potion(1, Potions.INFESTED, one()))
                            .add(potion(1, Potions.STRENGTH, one()))
                            .add(splashPotion(1, Potions.POISON, one()))
                            .add(splashPotion(1, Potions.WEAKNESS, one()))
                            .add(potion(5, Potions.WATER, one()))
                    )
                    .withPool(LootPool.lootPool().setRolls(number(0, 1))
                            .add(item(Items.BLAZE_POWDER, 1, number(1, 3)))
                    )
            );

            output.accept(DELootTables.DungeonVariant.COPPER_SHRINE, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(number(4, 8))
                            .add(item(Items.COPPER_INGOT, 2, number(3, 5)))
                            .add(item(Items.COPPER_BLOCK))
                            .add(item(Items.WEATHERED_COPPER))
                            .add(item(Items.HONEYCOMB).setWeight(2))
                            .add(item(Items.COPPER_CHAIN.waxedWeathered()))
                            .add(item(Items.REDSTONE, 2, number(1, 3)))
                            .add(item(Items.RED_CANDLE))
                            .add(item(Items.CANDLE))
                            .add(item(Items.ORANGE_CANDLE))
                            .add(item(Items.PUMPKIN_SEEDS))
                            .add(suspiciousStew(2, number(1, 2)))
                    )
                    .withPool(LootPool.lootPool().setRolls(number(0, 1))
                            .add(item(Items.OMINOUS_BOTTLE).apply(SetOminousBottleAmplifierFunction.setAmplifier(number(1, 3))))
                    )
            );

            output.accept(DELootTables.FUEL_COAL, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(one())
                            .add(item(Items.COAL, 1, between(0, 5)))
                            .add(item(Items.CHARCOAL, 1, between(0, 5)))
                    )
            );
        }

        private static void MinersHouseLoot(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(DELootTables.MINERS_HOUSE, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(number(3, 4))
                            .add(item(Items.ORANGE_TERRACOTTA, 1, number(2, 6)))
                            .add(item(Items.SPIDER_EYE, 1, number(1, 2)))
                            .add(item(Items.STRING, 1, number(2, 4)))
                            .add(item(Items.BONE, 1, number(1, 3)))
                            .add(item(Items.COBBLESTONE, 1, number(3, 5)))
                            .add(item(Items.STICK, 1, number(4, 7)))
                            .add(item(Items.DEAD_BUSH))
                    ).withPool(LootPool.lootPool().setRolls(number(2, 3))
                            .add(item(Items.DIAMOND))
                            .add(item(Items.COAL, 3, number(3, 6)))
                            .add(item(Items.IRON_ORE, 2, number(1, 3)))
                            .add(item(Items.GOLD_ORE, 2, number(2, 4)))
                            .add(item(Items.GOLD_NUGGET, 1, number(4, 12)))
                    )
            );
        }

        @Deprecated(forRemoval = true)
        private static ResourceKey<LootTable> location(String name) {
            return ResourceKey.create(Registries.LOOT_TABLE, DungeonsEnhanced.locate("chests/" + name));
        }
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return tables;
    }
}