package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.provider.loot_table.DEEquipmentLootProvider;
import com.barion.dungeons_enhanced.data.provider.loot_table.chest.DECastleChestLootProvider;
import com.barion.dungeons_enhanced.data.provider.loot_table.chest.DEMonsterMazeChestLootProvider;
import com.barion.dungeons_enhanced.registry.DELootTables;
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
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.barion.dungeons_enhanced.data.provider.loot_table.DELootTableProviderHelper.*;

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
        public void generate(@Nonnull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            {
                output.accept(DELootTables.DEEP_CRYPT, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 13))
                                .add(item(Items.DIAMOND, 2))
                                .add(item(Items.BONE, 8, number(1, 3)))
                                .add(item(Items.BONE_MEAL, 3, number(1, 2)))
                                .add(item(Items.COBWEB, 4, number(1, 2)))
                                .add(item(Items.STRING, 6, number(1, 2)))
                                .add(item(Items.SPIDER_EYE, 3, number(1, 2)))
                                .add(item(Items.BOOK, 4, number(1, 3)))
                                .add(item(Items.WRITABLE_BOOK, 2))
                                .add(item(Items.CANDLE, 2, number(1, 2)))
                                .add(item(Items.WHITE_CANDLE, 2))
                                .add(item(Items.ROTTEN_FLESH, 3, number(1, 3)))
                                .add(item(Items.GLOW_BERRIES, 4, number(1, 3)))
                                .add(item(Items.IRON_CHAIN, 5, number(1, 3)))
                                .add(item(Items.SKULL_BANNER_PATTERN, 1))
                                .add(item(Items.EMERALD, 3, number(1, 4)))
                                .add(item(Items.GOLD_INGOT, 4, number(1, 5)))
                                .add(item(Items.IRON_INGOT, 2, number(1, 2)))
                                .add(item(Items.MAP, 2, number(1, 2)))
                                .add(item(Items.PAPER, 4, number(1, 3)))
                                .add(enchantedItem(Items.BOOK, 2, number(1, 2), registries))
                                .add(item(Items.GOLDEN_APPLE, 1, number(1, 2)))
                                .add(item(Items.WITHER_ROSE, 1))
                                .add(item(Items.CHAINMAIL_BOOTS, 2))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 2))
                                .add(item(Items.CHAINMAIL_HELMET, 2))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 2))
                                .add(item(Items.IRON_HELMET, 1))
                                .add(item(Items.IRON_CHESTPLATE, 1))
                                .add(item(Items.IRON_LEGGINGS, 1))
                                .add(item(Items.IRON_BOOTS, 1))
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
                                .add(item(Items.GOLDEN_APPLE, 1))
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
                                .add(item(Items.COD, 3))
                                .add(item(Items.SALMON, 3))
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
                                .add(potion(1, Potions.WATER_BREATHING, number(0, 1)))
                                .add(item(Items.PRISMARINE_CRYSTALS, 2, number(0, 3)))
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
                                .add(item(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, 1))
                                .add(item(Items.TRIDENT, 1, number(0, 1)))
                                .add(item(Items.DIAMOND, 1, number(0, 2)))
                                .add(item(Items.TURTLE_EGG, 1, number(0, 1)))
                                .add(item(Items.COPPER_INGOT, 4, number(0, 3)))
                                .add(potion(1, Potions.WATER_BREATHING, number(0, 1)))
                                .add(item(Items.PRISMARINE_CRYSTALS, 3, number(0, 3)))
                        ));
            } // Elders Temple

            {
                output.accept(DELootTables.FLYING_DUTCHMAN, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(7, 9))
                                .add(item(Items.SKULL_BANNER_PATTERN, 1))
                                .add(item(Items.NAUTILUS_SHELL, 2))
                                .add(item(Items.TURTLE_EGG, 1))
                                .add(item(Items.TURTLE_SCUTE, 1))
                                .add(item(Items.EXPERIENCE_BOTTLE, 1))
                                .add(item(Items.DIAMOND, 2))
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
                                .add(suspiciousStew(3))
                                .add(item(Items.SPYGLASS, 1))
                        ));
            } // Flying Dutchman
            {
                output.accept(DELootTables.LARGE_DUNGEON, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 14))
                                .add(item(Items.IRON_INGOT, 3))
                                .add(item(Items.IRON_NUGGET, 6, number(1, 3)))
                                .add(item(Items.GOLD_INGOT, 3))
                                .add(item(Items.GOLD_NUGGET, 6, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH, 13))
                                .add(item(Items.BONE, 10))
                                .add(item(Items.BROWN_MUSHROOM, 4))
                                .add(item(Items.RED_MUSHROOM, 4))
                                .add(item(Items.CARROT, 4))
                                .add(item(Items.POTATO, 4))
                                .add(item(Items.POISONOUS_POTATO, 6))
                                .add(item(Items.STRING, 7))
                                .add(suspiciousStew(3))
                                .add(item(Items.SPIDER_EYE, 3)))
                        .withPool(LootPool.lootPool().setRolls(number(0, 2))
                                .add(item(Items.DIAMOND, 2))
                                .add(item(Items.GOLDEN_APPLE, 2))
                                .add(enchantedItem(Items.IRON_HELMET, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_CHESTPLATE, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_LEGGINGS, 2, number(4, 12), registries))
                                .add(enchantedItem(Items.IRON_BOOTS, 2, number(4, 12), registries))
                                .add(item(Items.DIAMOND_AXE, 1))
                                .add(item(Items.DIAMOND_SWORD, 1))
                                .add(item(Items.DIAMOND_PICKAXE, 1))
                                .add(item(Items.DIAMOND_HELMET, 1))
                                .add(item(Items.DIAMOND_CHESTPLATE, 1))
                                .add(item(Items.DIAMOND_LEGGINGS, 1))
                                .add(item(Items.DIAMOND_BOOTS, 1))
                        ));
            } // Large Dungeon

            MinersHouseLoot(output);

            {
                output.accept(DELootTables.PIRATE_SHIP, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.COD, 3))
                                .add(item(Items.SALMON, 3))
                                .add(item(Items.STRING, 4))
                                .add(item(Items.FISHING_ROD, 1))
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
                                .add(item(Items.COOKED_MUTTON, 1))
                                .add(item(Items.COOKED_BEEF, 1))
                                .add(item(Items.COOKED_PORKCHOP, 1))
                                .add(item(Items.COOKED_CHICKEN, 1))
                                .add(item(Items.COOKED_RABBIT, 1))
                                .add(item(Items.COOKED_SALMON, 1))
                                .add(item(Items.COOKED_COD, 1))
                                .add(item(Items.BAKED_POTATO, 1))
                                .add(item(Items.MUTTON, 1))
                                .add(item(Items.BEEF, 1))
                                .add(item(Items.PORKCHOP, 1))
                                .add(item(Items.CHICKEN, 1))
                                .add(item(Items.RABBIT, 1))
                                .add(item(Items.SALMON, 1))
                                .add(item(Items.COD, 1))
                                .add(item(Items.POTATO, 1))
                                .add(item(Items.CARROT, 1))
                                .add(item(Items.WHEAT, 1, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 1, number(1, 3)))
                        ));

                output.accept(DELootTables.PillagerCamp.GENERAL, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(6, 16))
                                .add(item(Items.MAP, 1))
                                .add(item(Items.EMERALD, 1))
                                .add(item(Items.PAPER, 7))
                                .add(item(Items.GOLDEN_CARROT, 2))
                                .add(item(Items.GOLDEN_APPLE, 2))
                                .add(item(Items.COMPASS, 1))
                                .add(item(Items.CLOCK, 1))
                                .add(item(Items.IRON_AXE, 2))
                                .add(item(Items.CROSSBOW, 1))
                                .add(item(Items.ARROW, 5))
                                .add(item(Items.DIAMOND, 1, number(0, 1)))
                                .add(item(Items.IRON_INGOT, 2))
                        ));
            } // Pillager Camp
            {
                output.accept(location("ruined_building"), LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(2, 4))
                                .add(item(Items.IRON_NUGGET, 6, number(2, 5)))
                                .add(item(Items.IRON_INGOT, 2))
                                .add(item(Items.GOLD_NUGGET, 4, number(1, 5)))
                                .add(item(Items.CAKE, 1))
                                .add(item(Items.FIELD_MASONED_BANNER_PATTERN, 1))
                                .add(item(Items.BUNDLE, 1))
                                .add(item(Items.GOLDEN_APPLE, 1)))
                        .withPool(LootPool.lootPool().setRolls(number(6, 10))
                                .add(item(Items.STICK, 6, number(1, 3)))
                                .add(item(Items.PAPER, 2, number(1, 2)))
                                .add(item(Items.BOOK, 1))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(suspiciousStew(2))
                                .add(item(Items.WHEAT, 5, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 5)))
                                .add(item(Items.MELON_SEEDS, 5, number(1, 3)))
                                .add(item(Items.POTATO, 4, number(1, 2)))
                                .add(item(Items.POISONOUS_POTATO, 6, number(1, 3)))
                                .add(item(Items.CARROT, 4, number(1, 3)))
                                .add(item(Items.PUMPKIN_SEEDS, 5, number(1, 4)))
                                .add(item(Items.GOLDEN_CARROT, 1))
                        ));
            } // Ruined Building
            {
                output.accept(DELootTables.TreeHouse.ROOF, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(4, 8))
                                .add(item(Items.DIAMOND, 1))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 3)))
                                .add(item(Items.IRON_INGOT, 3))
                                .add(item(Items.SPYGLASS, 1))
                                .add(tag(ItemTags.BUNDLES, 1))
                                .add(item(Items.COCOA_BEANS, 6, number(1, 2)))
                                .add(item(Items.MELON_SEEDS, 6, number(1, 3)))
                        ));
            } // Tree House
            {
                output.accept(DELootTables.UndeadTower.TREASURE, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(10, 18))
                                .add(item(Items.GOLD_NUGGET, 5, number(1, 2)))
                                .add(item(Items.GOLD_INGOT, 3))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 2)))
                                .add(item(Items.IRON_INGOT, 2))
                                .add(item(Items.GOLDEN_CARROT, 2))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 3)))
                                .add(item(Items.WHEAT, 6, number(1, 3)))
                                .add(item(Items.STRING, 6, number(1, 3)))
                                .add(item(Items.BONE, 7, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH, 10, number(1, 3)))
                                .add(item(Items.IRON_AXE, 1))
                                .add(item(Items.IRON_SWORD, 1))
                                .add(item(Items.CROSSBOW, 1))
                                .add(item(Items.MAP, 1))
                                .add(item(Items.COBWEB, 4))
                                .add(item(Items.GOLDEN_APPLE, 2))
                                .add(item(Items.ARROW, 6, number(2, 3)))
                        )
                        .withPool(LootPool.lootPool().setRolls(number(1, 3))
                                .add(item(Items.LEATHER_HELMET, 3))
                                .add(item(Items.LEATHER_CHESTPLATE, 3))
                                .add(item(Items.LEATHER_LEGGINGS, 3))
                                .add(item(Items.LEATHER_BOOTS, 3))
                                .add(item(Items.CHAINMAIL_HELMET, 2))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 2))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 2))
                                .add(item(Items.CHAINMAIL_BOOTS, 2))
                                .add(item(Items.IRON_HELMET, 1))
                                .add(item(Items.IRON_CHESTPLATE, 1))
                                .add(item(Items.IRON_LEGGINGS, 1))
                                .add(enchantedItem(Items.BOOK, 1, number(4, 10), registries))
                                .add(item(Items.IRON_BOOTS, 1))
                        )
                );
            } // Tower of the Undead
            {
                output.accept(DELootTables.SUNKEN_SHRINE, LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(number(8, 16))
                                .add(item(Items.COD, 3))
                                .add(item(Items.SALMON, 3))
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
                                .add(item(Items.PRISMARINE_CRYSTALS, 2, number(0, 3))
                                )));
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
                                .add(item(Items.EXPERIENCE_BOTTLE, 2))
                                .add(item(Items.STRING, 8, number(1, 3)))
                                .add(item(Items.BREAD, 4, number(1, 3)))
                                .add(suspiciousStew(1))
                                .add(item(Items.MAP, 2))
                                .add(item(Items.LEATHER_HELMET, 1))
                                .add(item(Items.LEATHER_CHESTPLATE, 1))
                                .add(item(Items.LEATHER_LEGGINGS, 1))
                                .add(item(Items.LEATHER_BOOTS, 1))
                        )
                        .withPool(LootPool.lootPool().setRolls(number(1, 3))
                                .add(item(Items.DIAMOND, 1))
                                .add(item(Items.SHIELD, 1))
                                .add(item(Items.BOW, 1))
                                .add(item(Items.CROSSBOW, 1))
                                .add(item(Items.IRON_SWORD, 1))
                                .add(item(Items.IRON_AXE, 1))
                                .add(item(Items.IRON_HELMET, 1))
                                .add(item(Items.IRON_CHESTPLATE, 1))
                                .add(item(Items.IRON_LEGGINGS, 1))
                                .add(item(Items.IRON_BOOTS, 1))
                                .add(item(Items.CHAINMAIL_HELMET, 1))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 1))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 1))
                                .add(item(Items.CHAINMAIL_BOOTS, 1))
                                .add(item(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, 1))
                        ).withPool(LootPool.lootPool().setRolls(one())
                                .add(item(Items.SPYGLASS, 1))
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
                                .add(item(Items.BOOK, 1)))
                        .withPool(LootPool.lootPool().setRolls(number(2, 4))
                                .add(item(Items.RABBIT_FOOT, 1))
                                .add(item(Items.NAME_TAG, 1))
                                .add(item(Items.GOLDEN_APPLE, 1))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 13), registries))));
            } // Witch Tower
        }

        private static void MinersHouseLoot(@Nonnull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(DELootTables.MINERS_HOUSE, LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(number(3, 4))
                            .add(item(Items.ORANGE_TERRACOTTA, 1, number(2, 6)))
                            .add(item(Items.SPIDER_EYE, 1, number(1, 2)))
                            .add(item(Items.STRING, 1, number(2, 4)))
                            .add(item(Items.BONE, 1, number(1, 3)))
                            .add(item(Items.COBBLESTONE, 1, number(3, 5)))
                            .add(item(Items.STICK, 1, number(4, 7)))
                            .add(item(Items.DEAD_BUSH, 1))
                    ).withPool(LootPool.lootPool().setRolls(number(2, 3))
                            .add(item(Items.DIAMOND, 1))
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
    @Nonnull
    public List<SubProviderEntry> getTables() {
        return tables;
    }
}