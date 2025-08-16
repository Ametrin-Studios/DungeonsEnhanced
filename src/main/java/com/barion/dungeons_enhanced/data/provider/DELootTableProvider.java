package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public final class DELootTableProvider extends LootTableProvider {
    private static List<SubProviderEntry> tables;

    public DELootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), tables, registries);
        tables = List.of(
                new SubProviderEntry(DEStructureLootTables::new, LootContextParamSets.CHEST)
        );
    }

    public static final class DEStructureLootTables implements LootTableSubProvider {
        private final HolderLookup.Provider _registries;

        public DEStructureLootTables(HolderLookup.Provider registries) {
            _registries = registries;
        }

        @Override
        public void generate(@Nonnull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            {
                consumer.accept(DELootTables.Castle.ARMORY, LootTable.lootTable()
                        .withPool(pool(number(2, 3))
                                .add(item(Items.DIAMOND, 2))
                                .add(item(Items.IRON_AXE, 2))
                                .add(item(Items.IRON_SWORD, 3))
                                .add(item(Items.SHIELD, 3))
                                .add(item(Items.BOW, 3))
                                .add(item(Items.CROSSBOW, 3))
                                .add(item(Items.IRON_HELMET, 2))
                                .add(item(Items.IRON_CHESTPLATE, 2))
                                .add(item(Items.IRON_LEGGINGS, 2))
                                .add(item(Items.IRON_BOOTS, 2))
                                .add(item(Items.CHAINMAIL_HELMET, 3))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 3))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 3))
                                .add(item(Items.CHAINMAIL_BOOTS, 3))
                        ).withPool(pool(number(5, 11))
                                .add(item(Items.IRON_NUGGET, 7, number(1, 3)))
                                .add(item(Items.ARROW, 6, number(1, 3)))
                                .add(item(Items.STICK, 9, number(1, 2)))
                                .add(item(Items.STRING, 8, number(1, 2)))
                                .add(item(Items.COBBLESTONE, 7, number(1, 2)))
                                .add(item(Items.ROTTEN_FLESH, 6))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3))
                                .add(item(Items.IRON_INGOT, 3))
                                .add(item(Items.CHAIN, 5))));

                consumer.accept(DELootTables.Castle.BEDROOM, LootTable.lootTable()
                        .withPool(pool(number(8, 14))
                                .add(item(Items.BOOK, 2))
                                .add(item(Items.WRITABLE_BOOK, 2))
                                .add(item(Items.WHITE_WOOL, 3))
                                .add(item(Items.STRING, 3))
                                .add(item(Items.COBWEB, 3))
                                .add(item(Items.NAME_TAG, 3))
                                .add(item(Items.EXPERIENCE_BOTTLE, 2))
                                .add(item(Items.AMETHYST_SHARD, 1))
                                .add(item(Items.MAP, 2)))
                        .withPool(pool(number(0, 1))
                                .add(item(Items.MUSIC_DISC_STRAD, 1))
                                .add(item(Items.MUSIC_DISC_STAL, 1))
                                .add(item(Items.MUSIC_DISC_MELLOHI, 1))
                                .add(item(Items.MUSIC_DISC_MALL, 1))
                                .add(item(Items.MUSIC_DISC_FAR, 1))
                                .add(item(Items.MUSIC_DISC_CHIRP, 1))
                                .add(item(Items.MUSIC_DISC_CAT, 1))
                                .add(item(Items.MUSIC_DISC_BLOCKS, 1))
                                .add(item(Items.MUSIC_DISC_WARD, 1))
                                .add(item(Items.MUSIC_DISC_13, 1))
                                .add(item(Items.MUSIC_DISC_11, 1))
                                .add(item(Items.MUSIC_DISC_WAIT, 1))));

                consumer.accept(DELootTables.Castle.CELLAR, LootTable.lootTable()
                        .withPool(pool(number(8, 17))
                                .add(item(Items.BONE, 14))
                                .add(item(Items.ROTTEN_FLESH, 9))
                                .add(item(Items.REDSTONE, 3))
                                .add(item(Items.RED_WOOL, 1))
                                .add(item(Items.RED_CARPET, 2))
                                .add(item(Items.IRON_NUGGET, 10))
                                .add(item(Items.STONE_HOE, 3))
                                .add(item(Items.STONE_SWORD, 1))
                                .add(item(Items.SHEARS, 2))
                                .add(item(Items.IRON_NUGGET, 10))
                                .add(item(Items.FLINT_AND_STEEL, 2))
                                .add(item(Items.AMETHYST_SHARD, 1))
                                .add(item(Items.FLINT, 4))
                                .add(item(Items.TNT, 3))
                                .add(item(Items.COBWEB, 2))
                                .add(item(Items.SPIDER_EYE, 5))
                                .add(item(Items.LEAD, 1))
                                .add(item(Items.CHAIN, 6))
                                .add(item(Items.STRING, 7)))
                        .withPool(pool(number(1, 3))
                                .add(item(Items.DIAMOND, 1))
                                .add(item(Items.GOLD_INGOT, 3))
                                .add(item(Items.IRON_INGOT, 4))
                                .add(item(Items.LAPIS_LAZULI, 2))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3))));

                consumer.accept(DELootTables.Castle.COFFIN, LootTable.lootTable()
                        .withPool(pool(number(8, 17))
                                .add(item(Items.BONE, 12))
                                .add(item(Items.ROTTEN_FLESH, 9))
                                .add(item(Items.RED_MUSHROOM, 5))
                                .add(item(Items.BROWN_MUSHROOM, 5))
                                .add(item(Items.GOLDEN_SHOVEL, 1))
                                .add(item(Items.GOLDEN_PICKAXE, 2))
                                .add(item(Items.GOLDEN_SWORD, 2))
                                .add(item(Items.SPIDER_EYE, 10))
                                .add(item(Items.EMERALD, 2))
                                .add(item(Items.GOLD_NUGGET, 6, number(1, 3)))
                                .add(item(Items.GOLD_INGOT, 3))
                                .add(item(Items.DIAMOND, 1)))
                        .withPool(pool(one())
                                .add(item(Items.SKELETON_SKULL, 1))
                                .add(item(Items.ENCHANTED_GOLDEN_APPLE, 3))));

                consumer.accept(DELootTables.Castle.KITCHEN, LootTable.lootTable()
                        .withPool(pool(number(10, 19))
                                .add(item(Items.GOLDEN_CARROT, 3))
                                .add(item(Items.GOLDEN_APPLE, 1))
                                .add(item(Items.BREAD, 6))
                                .add(item(Items.WHEAT, 8))
                                .add(item(Items.BAKED_POTATO, 7))
                                .add(item(Items.COOKED_CHICKEN, 5))
                                .add(item(Items.COOKED_RABBIT, 5))
                                .add(item(Items.COOKED_BEEF, 5))
                                .add(item(Items.COOKED_MUTTON, 5))
                                .add(item(Items.COOKED_PORKCHOP, 5, number(1, 3)))
                                .add(item(Items.CARROT, 3))
                                .add(item(Items.MUSHROOM_STEW, 7))
                                .add(item(Items.CARROT, 7))
                                .add(item(Items.APPLE, 7))
                                .add(suspiciousStew(10))
                                .add(item(Items.ROTTEN_FLESH, 20))));

                consumer.accept(DELootTables.Castle.LIBRARY, LootTable.lootTable()
                        .withPool(pool(number(7, 15))
                                .add(item(Items.BOOK, 5))
                                .add(item(Items.PAPER, 8))
                                .add(item(Items.BOOKSHELF, 2))
                                .add(item(Items.GUNPOWDER, 5))
                                .add(item(Items.STRING, 5))
                                .add(item(Items.COBWEB, 2))
                                .add(item(Items.EXPERIENCE_BOTTLE, 2))
                                .add(item(Items.SAND, 5)))
                        .withPool(pool(number(1, 3))
                                .add(enchantedItem(Items.BOOK, 1, number(20, 25), _registries))));

                consumer.accept(DELootTables.Castle.PRISON, LootTable.lootTable()
                        .withPool(pool(number(10, 16))
                                .add(item(Items.CHAIN, 2))
                                .add(item(Items.PAPER, 2))
                                .add(item(Items.ROTTEN_FLESH, 3))
                                .add(item(Items.POISONOUS_POTATO, 2))
                                .add(item(Items.STRING, 2))
                                .add(item(Items.BONE, 3))
                                .add(item(Items.BOWL, 2))
                                .add(suspiciousStew(2))
                                .add(item(Items.CHAINMAIL_HELMET, 1))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 1))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 1))
                                .add(item(Items.CHAINMAIL_BOOTS, 1))));

                consumer.accept(DELootTables.Castle.QUARTERS, LootTable.lootTable()
                        .withPool(pool(number(7, 15))
                                .add(item(Items.WHITE_WOOL, 4))
                                .add(item(Items.WHITE_CARPET, 5))
                                .add(item(Items.PAPER, 4))
                                .add(item(Items.STRING, 5))
                                .add(item(Items.EMERALD, 3))
                                .add(item(Items.NAME_TAG, 1))
                                .add(item(Items.BOOK, 3))));

                consumer.accept(DELootTables.Castle.SPRING, LootTable.lootTable()
                        .withPool(pool(number(11, 22))
//                                .add(tag(DETags.Items.CASTLE_TREASURE, 1))
                                .add(item(Items.DIAMOND, 1))
                                .add(item(Items.IRON_INGOT, 8))
                                .add(item(Items.IRON_NUGGET, 6))
                                .add(item(Items.GOLD_INGOT, 7))
                                .add(item(Items.GOLD_NUGGET, 5))
                                .add(item(Items.LAPIS_LAZULI, 5))
                                .add(item(Items.AMETHYST_SHARD, 4))
                                .add(item(Items.REDSTONE, 6))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3))
                                .add(item(Items.EMERALD, 3))
                                .add(item(Items.ENDER_PEARL, 2))));

                consumer.accept(DELootTables.Castle.THRONE, LootTable.lootTable()
                                .withPool(pool(number(9, 13))
                                        .add(item(Items.GOLD_NUGGET, 10, number(1, 4)))
                                        .add(item(Items.GOLD_INGOT, 15))
                                        .add(item(Items.GOLD_BLOCK, 5))
                                        .add(item(Items.DIAMOND, 2))
                                        .add(item(Items.IRON_INGOT, 20))
                                        .add(item(Items.IRON_NUGGET, 15, number(2, 5))))
                                .withPool(pool(number(0, 2))
//                                        .add(tag(DETags.Items.CASTLE_TREASURE, 2))
                                        .add(enchantedItem(Items.DIAMOND_SWORD, 4, number(4, 12), _registries))
                                        .add(enchantedItem(Items.DIAMOND_AXE, 4, number(4, 12), _registries))
                                        .add(enchantedItem(Items.DIAMOND_HELMET, 4, number(4, 10), _registries))
                                        .add(enchantedItem(Items.DIAMOND_CHESTPLATE, 3, number(3, 10), _registries))
                                        .add(enchantedItem(Items.DIAMOND_LEGGINGS, 3, number(3, 10), _registries))
                                        .add(enchantedItem(Items.DIAMOND_BOOTS, 4, number(3, 10), _registries))
                                )
                );
            } // Castle
            {
                consumer.accept(location("deep_crypt"), LootTable.lootTable()
                        .withPool(pool(number(8, 13))
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
                                .add(item(Items.CHAIN, 5, number(1, 3)))
                                .add(item(Items.SKULL_BANNER_PATTERN, 1))
                                .add(item(Items.EMERALD, 3, number(1, 4)))
                                .add(item(Items.GOLD_INGOT, 4, number(1, 5)))
                                .add(item(Items.IRON_INGOT, 2, number(1, 2)))
                                .add(item(Items.MAP, 2, number(1, 2)))
                                .add(item(Items.PAPER, 4, number(1, 3)))
                                .add(enchantedItem(Items.BOOK, 2, number(1, 2), _registries))
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
                consumer.accept(location("desert_tomb"), LootTable.lootTable()
                        .withPool(pool(number(4, 6))
                                .add(item(Items.GOLD_NUGGET, 5, number(4, 7)))
                                .add(item(Items.GOLD_INGOT, 2, number(1, 3)))
                                .add(item(Items.GOLDEN_APPLE, 1))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(item(Items.STRING, 7, number(2, 4)))
                                .add(item(Items.GUNPOWDER, 7, number(2, 4)))
                                .add(item(Items.REDSTONE, 4, number(2, 3)))
                                .add(item(Items.SADDLE, 1))
                                .add(item(Items.SAND, 15, number(2, 5))))
                        .withPool(pool(number(0, 1))
                                .add(enchantedItem(Items.BOOK, 1, number(5, 18), _registries))
                                .add(enchantedItem(Items.GOLDEN_SWORD, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_PICKAXE, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_AXE, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_HELMET, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_CHESTPLATE, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_LEGGINGS, 1, number(0, 7), _registries))
                                .add(enchantedItem(Items.GOLDEN_BOOTS, 1, number(0, 7), _registries))
                        ));
            } // Desert Tomb
            {
                consumer.accept(location("elders_temple/main"), LootTable.lootTable()
                        .withPool(pool(number(8, 16))
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

                consumer.accept(location("elders_temple/elder_room"), LootTable.lootTable()
                        .withPool(pool(number(8, 16))
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
                consumer.accept(location("flying_dutchman"), LootTable.lootTable()
                        .withPool(pool(number(7, 9))
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
                                .add(enchantedItem(Items.BOOK, 1, number(6, 14), _registries))
                                .add(item(Items.KELP, 8, number(2, 5)))
                                .add(item(Items.GOLD_INGOT, 3, number(1, 2)))
                                .add(suspiciousStew(3))
                                .add(item(Items.SPYGLASS, 1))
                        ));
            } // Flying Dutchman
            {
                consumer.accept(location("large_dungeon"), LootTable.lootTable()
                        .withPool(pool(number(8, 14))
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
                        .withPool(pool(number(0, 2))
                                .add(item(Items.DIAMOND, 2))
                                .add(item(Items.GOLDEN_APPLE, 2))
                                .add(enchantedItem(Items.IRON_HELMET, 2, number(4, 12), _registries))
                                .add(enchantedItem(Items.IRON_CHESTPLATE, 2, number(4, 12), _registries))
                                .add(enchantedItem(Items.IRON_LEGGINGS, 2, number(4, 12), _registries))
                                .add(enchantedItem(Items.IRON_BOOTS, 2, number(4, 12), _registries))
                                .add(item(Items.DIAMOND_AXE, 1))
                                .add(item(Items.DIAMOND_SWORD, 1))
                                .add(item(Items.DIAMOND_PICKAXE, 1))
                                .add(item(Items.DIAMOND_HELMET, 1))
                                .add(item(Items.DIAMOND_CHESTPLATE, 1))
                                .add(item(Items.DIAMOND_LEGGINGS, 1))
                                .add(item(Items.DIAMOND_BOOTS, 1))
                        ));
            } // Large Dungeon

            MinersHouseLoot(consumer);

            {
                consumer.accept(location("monster_maze/church"), LootTable.lootTable()
                        .withPool(pool(number(5, 7))
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
                                .add(enchantedItem(Items.BOOK, 1, number(6, 14), _registries))
                                .add(item(Items.GOLD_INGOT, 4, number(2, 3)))));

                consumer.accept(location("monster_maze/brewery"), LootTable.lootTable()
                        .withPool(pool(number(10, 15))
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
                                .add(item(Items.TURTLE_SCUTE, 2)))
                        .withPool(pool(number(0, 3))
                                .add(item(Items.BLACK_CANDLE, 1, number(0, 1)))
                                .add(item(Items.CYAN_CANDLE, 1, number(0, 1)))
                                .add(item(Items.BLUE_CANDLE, 1, number(0, 1)))
                                .add(item(Items.BROWN_CANDLE, 1, number(0, 1)))
                                .add(item(Items.GRAY_CANDLE, 1, number(0, 1)))
                                .add(item(Items.GREEN_CANDLE, 1, number(0, 1)))
                                .add(item(Items.LIGHT_BLUE_CANDLE, 1, number(0, 1)))
                                .add(item(Items.LIGHT_GRAY_CANDLE, 1, number(0, 1)))
                                .add(item(Items.LIME_CANDLE, 1, number(0, 1)))
                                .add(item(Items.MAGENTA_CANDLE, 1, number(0, 1)))
                                .add(item(Items.ORANGE_CANDLE, 1, number(0, 1)))
                                .add(item(Items.PINK_CANDLE, 1, number(0, 1)))
                                .add(item(Items.PURPLE_CANDLE, 1, number(0, 1)))
                                .add(item(Items.RED_CANDLE, 1, number(0, 1)))
                                .add(item(Items.WHITE_CANDLE, 1, number(0, 1)))
                                .add(item(Items.YELLOW_CANDLE, 1, number(0, 1)))
                                .add(item(Items.CANDLE, 1, number(0, 1))))
                        .withPool(pool(number(0, 2))
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
                        ));
            } // Monster Maze
            {
                consumer.accept(location("pirate_ship"), LootTable.lootTable()
                        .withPool(pool(number(8, 16))
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
                        ));
            } // Pirate Ship
            {
                consumer.accept(DELootTables.PillagerCamp.KITCHEN, LootTable.lootTable()
                        .withPool(pool(number(12, 19))
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

                consumer.accept(DELootTables.PillagerCamp.GENERAL, LootTable.lootTable()
                        .withPool(pool(number(6, 16))
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
                consumer.accept(location("ruined_building"), LootTable.lootTable()
                        .withPool(pool(number(2, 4))
                                .add(item(Items.IRON_NUGGET, 6, number(2, 5)))
                                .add(item(Items.IRON_INGOT, 2))
                                .add(item(Items.GOLD_NUGGET, 4, number(1, 5)))
                                .add(item(Items.CAKE, 1))
                                .add(item(Items.FIELD_MASONED_BANNER_PATTERN, 1))
                                .add(item(Items.BUNDLE, 1))
                                .add(item(Items.GOLDEN_APPLE, 1)))
                        .withPool(pool(number(6, 10))
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
                consumer.accept(location("tree_house/roof"), LootTable.lootTable()
                        .withPool(pool(number(4, 8))
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
                consumer.accept(location("tower_of_the_undead/treasure"), LootTable.lootTable()
                        .withPool(pool(number(10, 18))
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
                                .add(item(Items.ARROW, 6, number(2, 3))))
                        .withPool(pool(number(1, 3))
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
                                .add(enchantedItem(Items.BOOK, 1, number(4, 10), _registries))
                                .add(item(Items.IRON_BOOTS, 1))
                        ));
            } // Tower of the Undead
            {
                consumer.accept(DELootTables.SUNKEN_SHRINE, LootTable.lootTable()
                        .withPool(pool(number(8, 16))
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
                consumer.accept(location("watch_tower"), LootTable.lootTable()
                        .withPool(pool(number(4, 10))
                                .add(item(Items.CHAIN, 5, number(1, 3)))
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
                                .add(item(Items.LEATHER_BOOTS, 1)))
                        .withPool(pool(number(1, 3))
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
                        ).withPool(pool(one())
                                .add(item(Items.SPYGLASS, 1))
                        ));
            } // Watch Tower
            {
                consumer.accept(location("witch_tower"), LootTable.lootTable()
                        .withPool(pool(number(7, 10))
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
                        .withPool(pool(number(2, 4))
                                .add(item(Items.RABBIT_FOOT, 1))
                                .add(item(Items.NAME_TAG, 1))
                                .add(item(Items.GOLDEN_APPLE, 1))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 13), _registries))));
            } // Witch Tower
        }

        private static void MinersHouseLoot(@Nonnull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(DELootTables.MINERS_HOUSE, LootTable.lootTable()
                    .withPool(pool(number(3, 4))
                            .add(item(Items.ORANGE_TERRACOTTA, 1, number(2, 6)))
                            .add(item(Items.SPIDER_EYE, 1, number(1, 2)))
                            .add(item(Items.STRING, 1, number(2, 4)))
                            .add(item(Items.BONE, 1, number(1, 3)))
                            .add(item(Items.COBBLESTONE, 1, number(3, 5)))
                            .add(item(Items.STICK, 1, number(4, 7)))
                            .add(item(Items.DEAD_BUSH, 1))
                    ).withPool(pool(number(2, 3))
                            .add(item(Items.DIAMOND, 1))
                            .add(item(Items.COAL, 3, number(3, 6)))
                            .add(item(Items.IRON_ORE, 2, number(1, 3)))
                            .add(item(Items.GOLD_ORE, 2, number(2, 4)))
                            .add(item(Items.GOLD_NUGGET, 1, number(4, 12)))
                    )
            );
        }

        private static LootPoolEntryContainer.Builder<?> item(ItemLike item, int weight) {
            return LootItem.lootTableItem(item).setWeight(weight);
        }

        private static LootPoolEntryContainer.Builder<?> item(ItemLike item, int weight, NumberProvider amount) {
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }

        private static LootPoolEntryContainer.Builder<?> tag(TagKey<Item> item, int weight) {
            return TagEntry.expandTag(item).setWeight(weight);
        }

        private static LootPoolEntryContainer.Builder<?> tag(TagKey<Item> item, int weight, NumberProvider amount) {
            return TagEntry.expandTag(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }

        private static LootPoolEntryContainer.Builder<?> enchantedItem(ItemLike item, int weight, NumberProvider enchant, NumberProvider amount, HolderLookup.Provider provider) {
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantWithLevelsFunction.enchantWithLevels(provider, enchant));
        }

        private static LootPoolEntryContainer.Builder<?> enchantedItem(ItemLike item, int weight, NumberProvider amount, HolderLookup.Provider provider) {
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantRandomlyFunction.randomApplicableEnchantment(provider));
        }

        private static LootPoolEntryContainer.Builder<?> suspiciousStew(int weight) {
            return LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(weight).apply(SetStewEffectFunction.stewEffect().withEffect(MobEffects.NIGHT_VISION, number(7, 10)).withEffect(MobEffects.JUMP, number(7, 10)).withEffect(MobEffects.WEAKNESS, number(6, 8)).withEffect(MobEffects.BLINDNESS, number(5, 7)).withEffect(MobEffects.POISON, number(10, 20)).withEffect(MobEffects.SATURATION, number(7, 10)));
        }

        private static LootPoolEntryContainer.Builder<?> potion(int weight, Holder<Potion> potion, NumberProvider amount) {
            return LootItem.lootTableItem(Items.POTION).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetPotionFunction.setPotion(potion));
        }

        private LootPoolEntryContainer.Builder<?> splashPotion(int weight, Holder<Potion> potion, NumberProvider amount) {
            return LootItem.lootTableItem(Items.SPLASH_POTION).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetPotionFunction.setPotion(potion));
        }

        private LootPoolEntryContainer.Builder<?> lingeringPotion(int weight, Holder<Potion> potion, NumberProvider amount) {
            return LootItem.lootTableItem(Items.LINGERING_POTION).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetPotionFunction.setPotion(potion));
        }

        private static NumberProvider one() {
            return number(1);
        }

        private static NumberProvider number(int amount) {
            return ConstantValue.exactly(amount);
        }

        private static NumberProvider number(int minAmount, int maxAmount) {
            return UniformGenerator.between(minAmount, maxAmount);
        }

        private static LootPool.Builder pool(NumberProvider rolls) {
            return LootPool.lootPool().setRolls(rolls);
        }

        @Deprecated(forRemoval = true)
        private static ResourceKey<LootTable> location(String name) {
            return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(DungeonsEnhanced.MOD_ID, "chests/" + name));
        }
    }

    @Override
    @Nonnull
    public List<SubProviderEntry> getTables() {
        return tables;
    }
}