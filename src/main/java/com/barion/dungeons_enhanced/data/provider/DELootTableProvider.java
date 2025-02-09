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
        tables = List.of(new SubProviderEntry(DEStructureLootTables::new, LootContextParamSets.CHEST));
    }

    public static final class DEStructureLootTables implements LootTableSubProvider {
        private final HolderLookup.Provider _registries;

        public DEStructureLootTables(HolderLookup.Provider registries) {
            _registries = registries;
        }

        @Override
        public void generate(@Nonnull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            {
                consumer.accept(location("castle/armory"), LootTable.lootTable()
                        .withPool(pool(number(2, 3))
                                .add(item(Items.DIAMOND, 2, one()))
                                .add(item(Items.IRON_AXE, 2, one()))
                                .add(item(Items.IRON_SWORD, 3, one()))
                                .add(item(Items.SHIELD, 3, one()))
                                .add(item(Items.BOW, 3, one()))
                                .add(item(Items.CROSSBOW, 3, one()))
                                .add(item(Items.IRON_HELMET, 2, one()))
                                .add(item(Items.IRON_CHESTPLATE, 2, one()))
                                .add(item(Items.IRON_LEGGINGS, 2, one()))
                                .add(item(Items.IRON_BOOTS, 2, one()))
                                .add(item(Items.CHAINMAIL_HELMET, 3, one()))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 3, one()))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 3, one()))
                                .add(item(Items.CHAINMAIL_BOOTS, 3, one()))
                        ).withPool(pool(number(5, 11))
                                .add(item(Items.IRON_NUGGET, 7, number(1, 3)))
                                .add(item(Items.ARROW, 6, number(1, 3)))
                                .add(item(Items.STICK, 9, number(1, 2)))
                                .add(item(Items.STRING, 8, number(1, 2)))
                                .add(item(Items.COBBLESTONE, 7, number(1, 2)))
                                .add(item(Items.ROTTEN_FLESH, 6, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3, one()))
                                .add(item(Items.IRON_INGOT, 3, one()))
                                .add(item(Items.CHAIN, 5, one()))));

                consumer.accept(location("castle/bedroom"), LootTable.lootTable()
                        .withPool(pool(number(8, 14))
                                .add(item(Items.BOOK, 2, one()))
                                .add(item(Items.WRITABLE_BOOK, 2, one()))
                                .add(item(Items.WHITE_WOOL, 3, one()))
                                .add(item(Items.STRING, 3, one()))
                                .add(item(Items.COBWEB, 3, one()))
                                .add(item(Items.NAME_TAG, 3, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 2, one()))
                                .add(item(Items.AMETHYST_SHARD, 1, one()))
                                .add(item(Items.MAP, 2, one())))
                        .withPool(pool(number(0, 1))
                                .add(item(Items.MUSIC_DISC_STRAD, 1, one()))
                                .add(item(Items.MUSIC_DISC_STAL, 1, one()))
                                .add(item(Items.MUSIC_DISC_MELLOHI, 1, one()))
                                .add(item(Items.MUSIC_DISC_MALL, 1, one()))
                                .add(item(Items.MUSIC_DISC_FAR, 1, one()))
                                .add(item(Items.MUSIC_DISC_CHIRP, 1, one()))
                                .add(item(Items.MUSIC_DISC_CAT, 1, one()))
                                .add(item(Items.MUSIC_DISC_BLOCKS, 1, one()))
                                .add(item(Items.MUSIC_DISC_WARD, 1, one()))
                                .add(item(Items.MUSIC_DISC_13, 1, one()))
                                .add(item(Items.MUSIC_DISC_11, 1, one()))
                                .add(item(Items.MUSIC_DISC_WAIT, 1, one()))));

                consumer.accept(location("castle/cellar"), LootTable.lootTable()
                        .withPool(pool(number(8, 17))
                                .add(item(Items.BONE, 14, one()))
                                .add(item(Items.ROTTEN_FLESH, 9, one()))
                                .add(item(Items.REDSTONE, 3, one()))
                                .add(item(Items.RED_WOOL, 1, one()))
                                .add(item(Items.RED_CARPET, 2, one()))
                                .add(item(Items.IRON_NUGGET, 10, one()))
                                .add(item(Items.STONE_HOE, 3, one()))
                                .add(item(Items.STONE_SWORD, 1, one()))
                                .add(item(Items.SHEARS, 2, one()))
                                .add(item(Items.IRON_NUGGET, 10, one()))
                                .add(item(Items.FLINT_AND_STEEL, 2, one()))
                                .add(item(Items.AMETHYST_SHARD, 1, one()))
                                .add(item(Items.FLINT, 4, one()))
                                .add(item(Items.TNT, 3, one()))
                                .add(item(Items.COBWEB, 2, one()))
                                .add(item(Items.SPIDER_EYE, 5, one()))
                                .add(item(Items.LEAD, 1, one()))
                                .add(item(Items.CHAIN, 6, one()))
                                .add(item(Items.STRING, 7, one())))
                        .withPool(pool(number(1, 3))
                                .add(item(Items.DIAMOND, 1, one()))
                                .add(item(Items.GOLD_INGOT, 3, one()))
                                .add(item(Items.IRON_INGOT, 4, one()))
                                .add(item(Items.LAPIS_LAZULI, 2, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3, one()))));

                consumer.accept(location("castle/coffin"), LootTable.lootTable()
                        .withPool(pool(number(8, 17))
                                .add(item(Items.BONE, 12, one()))
                                .add(item(Items.ROTTEN_FLESH, 9, one()))
                                .add(item(Items.RED_MUSHROOM, 5, one()))
                                .add(item(Items.BROWN_MUSHROOM, 5, one()))
                                .add(item(Items.GOLDEN_SHOVEL, 1, one()))
                                .add(item(Items.GOLDEN_PICKAXE, 2, one()))
                                .add(item(Items.GOLDEN_SWORD, 2, one()))
                                .add(item(Items.SPIDER_EYE, 10, one()))
                                .add(item(Items.EMERALD, 2, one()))
                                .add(item(Items.GOLD_NUGGET, 6, number(1, 3)))
                                .add(item(Items.GOLD_INGOT, 3, one()))
                                .add(item(Items.DIAMOND, 1, one())))
                        .withPool(pool(one())
                                .add(item(Items.SKELETON_SKULL, 1, one()))
                                .add(item(Items.ENCHANTED_GOLDEN_APPLE, 3, one()))));

                consumer.accept(location("castle/kitchen"), LootTable.lootTable()
                        .withPool(pool(number(10, 19))
                                .add(item(Items.GOLDEN_CARROT, 3, one()))
                                .add(item(Items.GOLDEN_APPLE, 1, one()))
                                .add(item(Items.BREAD, 6, one()))
                                .add(item(Items.WHEAT, 8, one()))
                                .add(item(Items.BAKED_POTATO, 7, one()))
                                .add(item(Items.COOKED_CHICKEN, 5, one()))
                                .add(item(Items.COOKED_RABBIT, 5, one()))
                                .add(item(Items.COOKED_BEEF, 5, one()))
                                .add(item(Items.COOKED_MUTTON, 5, one()))
                                .add(item(Items.COOKED_PORKCHOP, 5, number(1, 3)))
                                .add(item(Items.CARROT, 3, one()))
                                .add(item(Items.MUSHROOM_STEW, 7, one()))
                                .add(item(Items.CARROT, 7, one()))
                                .add(item(Items.APPLE, 7, one()))
                                .add(suspiciousStew(10, one()))
                                .add(item(Items.ROTTEN_FLESH, 20, one()))));

                consumer.accept(location("castle/library"), LootTable.lootTable()
                        .withPool(pool(number(7, 15))
                                .add(item(Items.BOOK, 5, one()))
                                .add(item(Items.PAPER, 8, one()))
                                .add(item(Items.BOOKSHELF, 2, one()))
                                .add(item(Items.GUNPOWDER, 5, one()))
                                .add(item(Items.STRING, 5, one()))
                                .add(item(Items.COBWEB, 2, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 2, one()))
                                .add(item(Items.SAND, 5, one())))
                        .withPool(pool(number(1, 3))
                                .add(enchantedItem(Items.BOOK, 1, number(20, 25), one(), _registries))));

                consumer.accept(location("castle/prison"), LootTable.lootTable()
                        .withPool(pool(number(10, 16))
                                .add(item(Items.CHAIN, 2, one()))
                                .add(item(Items.PAPER, 2, one()))
                                .add(item(Items.ROTTEN_FLESH, 3, one()))
                                .add(item(Items.POISONOUS_POTATO, 2, one()))
                                .add(item(Items.STRING, 2, one()))
                                .add(item(Items.BONE, 3, one()))
                                .add(item(Items.BOWL, 2, one()))
                                .add(suspiciousStew(2, one()))
                                .add(item(Items.CHAINMAIL_HELMET, 1, one()))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 1, one()))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 1, one()))
                                .add(item(Items.CHAINMAIL_BOOTS, 1, one()))));

                consumer.accept(location("castle/quarters"), LootTable.lootTable()
                        .withPool(pool(number(7, 15))
                                .add(item(Items.WHITE_WOOL, 4, one()))
                                .add(item(Items.WHITE_CARPET, 5, one()))
                                .add(item(Items.PAPER, 4, one()))
                                .add(item(Items.STRING, 5, one()))
                                .add(item(Items.EMERALD, 3, one()))
                                .add(item(Items.NAME_TAG, 1, one()))
                                .add(item(Items.BOOK, 3, one()))));

                consumer.accept(location("castle/spring"), LootTable.lootTable()
                        .withPool(pool(number(11, 22))
//                                .add(tag(DETags.Items.CASTLE_TREASURE, 1, one()))
                                .add(item(Items.DIAMOND, 1, one()))
                                .add(item(Items.IRON_INGOT, 8, one()))
                                .add(item(Items.IRON_NUGGET, 6, one()))
                                .add(item(Items.GOLD_INGOT, 7, one()))
                                .add(item(Items.GOLD_NUGGET, 5, one()))
                                .add(item(Items.LAPIS_LAZULI, 5, one()))
                                .add(item(Items.AMETHYST_SHARD, 4, one()))
                                .add(item(Items.REDSTONE, 6, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3, one()))
                                .add(item(Items.EMERALD, 3, one()))
                                .add(item(Items.ENDER_PEARL, 2, one()))));

                consumer.accept(location("castle/throne"), LootTable.lootTable()
                                .withPool(pool(number(9, 13))
                                        .add(item(Items.GOLD_NUGGET, 10, number(1, 4)))
                                        .add(item(Items.GOLD_INGOT, 15, one()))
                                        .add(item(Items.GOLD_BLOCK, 5, one()))
                                        .add(item(Items.DIAMOND, 2, one()))
                                        .add(item(Items.IRON_INGOT, 20, one()))
                                        .add(item(Items.IRON_NUGGET, 15, number(2, 5))))
                                .withPool(pool(number(0, 2))
//                                        .add(tag(DETags.Items.CASTLE_TREASURE, 2, one()))
                                        .add(enchantedItem(Items.DIAMOND_SWORD, 4, number(4, 12), one(), _registries))
                                        .add(enchantedItem(Items.DIAMOND_AXE, 4, number(4, 12), one(), _registries))
                                        .add(enchantedItem(Items.DIAMOND_HELMET, 4, number(4, 10), one(), _registries))
                                        .add(enchantedItem(Items.DIAMOND_CHESTPLATE, 3, number(3, 10), one(), _registries))
                                        .add(enchantedItem(Items.DIAMOND_LEGGINGS, 3, number(3, 10), one(), _registries))
                                        .add(enchantedItem(Items.DIAMOND_BOOTS, 4, number(3, 10), one(), _registries))
                                )
                );
            } // Castle
            {
                consumer.accept(location("deep_crypt"), LootTable.lootTable()
                        .withPool(pool(number(8, 13))
                                .add(item(Items.DIAMOND, 2, one()))
                                .add(item(Items.BONE, 8, number(1, 3)))
                                .add(item(Items.BONE_MEAL, 3, number(1, 2)))
                                .add(item(Items.COBWEB, 4, number(1, 2)))
                                .add(item(Items.STRING, 6, number(1, 2)))
                                .add(item(Items.SPIDER_EYE, 3, number(1, 2)))
                                .add(item(Items.BOOK, 4, number(1, 3)))
                                .add(item(Items.WRITABLE_BOOK, 2, one()))
                                .add(item(Items.CANDLE, 2, number(1, 2)))
                                .add(item(Items.WHITE_CANDLE, 2, one()))
                                .add(item(Items.ROTTEN_FLESH, 3, number(1, 3)))
                                .add(item(Items.GLOW_BERRIES, 4, number(1, 3)))
                                .add(item(Items.CHAIN, 5, number(1, 3)))
                                .add(item(Items.SKULL_BANNER_PATTERN, 1, one()))
                                .add(item(Items.EMERALD, 3, number(1, 4)))
                                .add(item(Items.GOLD_INGOT, 4, number(1, 5)))
                                .add(item(Items.IRON_INGOT, 2, number(1, 2)))
                                .add(item(Items.MAP, 2, number(1, 2)))
                                .add(item(Items.PAPER, 4, number(1, 3)))
                                .add(enchantedItem(Items.BOOK, 2, number(1, 2), _registries))
                                .add(item(Items.GOLDEN_APPLE, 1, number(1, 2)))
                                .add(item(Items.WITHER_ROSE, 1, one()))
                                .add(item(Items.CHAINMAIL_BOOTS, 2, one()))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 2, one()))
                                .add(item(Items.CHAINMAIL_HELMET, 2, one()))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 2, one()))
                                .add(item(Items.IRON_HELMET, 1, one()))
                                .add(item(Items.IRON_CHESTPLATE, 1, one()))
                                .add(item(Items.IRON_LEGGINGS, 1, one()))
                                .add(item(Items.IRON_BOOTS, 1, one()))
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
                                .add(item(Items.GOLDEN_APPLE, 1, one()))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(item(Items.STRING, 7, number(2, 4)))
                                .add(item(Items.GUNPOWDER, 7, number(2, 4)))
                                .add(item(Items.REDSTONE, 4, number(2, 3)))
                                .add(item(Items.SADDLE, 1, one()))
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
                                .add(item(Items.COD, 3, one()))
                                .add(item(Items.SALMON, 3, one()))
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
                                .add(item(Items.SKULL_BANNER_PATTERN, 1, one()))
                                .add(item(Items.NAUTILUS_SHELL, 2, one()))
                                .add(item(Items.TURTLE_EGG, 1, one()))
                                .add(item(Items.TURTLE_SCUTE, 1, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 1, one()))
                                .add(item(Items.DIAMOND, 2, one()))
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
                                .add(suspiciousStew(3, number(1, 2)))
                                .add(item(Items.SPYGLASS, 1, one()))
                        ));
            } // Flying Dutchman
            {
                consumer.accept(location("large_dungeon"), LootTable.lootTable()
                        .withPool(pool(number(8, 14))
                                .add(item(Items.IRON_INGOT, 3, one()))
                                .add(item(Items.IRON_NUGGET, 6, number(1, 3)))
                                .add(item(Items.GOLD_INGOT, 3, one()))
                                .add(item(Items.GOLD_NUGGET, 6, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH, 13, one()))
                                .add(item(Items.BONE, 10, one()))
                                .add(item(Items.BROWN_MUSHROOM, 4, one()))
                                .add(item(Items.RED_MUSHROOM, 4, one()))
                                .add(item(Items.CARROT, 4, one()))
                                .add(item(Items.POTATO, 4, one()))
                                .add(item(Items.POISONOUS_POTATO, 6, one()))
                                .add(item(Items.STRING, 7, one()))
                                .add(suspiciousStew(3, one()))
                                .add(item(Items.SPIDER_EYE, 3, one())))
                        .withPool(pool(number(0, 2))
                                .add(item(Items.DIAMOND, 2, one()))
                                .add(item(Items.GOLDEN_APPLE, 2, one()))
                                .add(enchantedItem(Items.IRON_HELMET, 2, number(4, 12), one(), _registries))
                                .add(enchantedItem(Items.IRON_CHESTPLATE, 2, number(4, 12), one(), _registries))
                                .add(enchantedItem(Items.IRON_LEGGINGS, 2, number(4, 12), one(), _registries))
                                .add(enchantedItem(Items.IRON_BOOTS, 2, number(4, 12), one(), _registries))
                                .add(item(Items.DIAMOND_AXE, 1, one()))
                                .add(item(Items.DIAMOND_SWORD, 1, one()))
                                .add(item(Items.DIAMOND_PICKAXE, 1, one()))
                                .add(item(Items.DIAMOND_HELMET, 1, one()))
                                .add(item(Items.DIAMOND_CHESTPLATE, 1, one()))
                                .add(item(Items.DIAMOND_LEGGINGS, 1, one()))
                                .add(item(Items.DIAMOND_BOOTS, 1, one()))
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
                                .add(item(Items.GOLD_BLOCK, 1, one()))
                                .add(item(Items.PUMPKIN, 2, one()))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 14), one(), _registries))
                                .add(item(Items.GOLD_INGOT, 4, number(2, 3)))));

                consumer.accept(location("monster_maze/treasure"), LootTable.lootTable()
                        .withPool(pool(number(4, 9))
                                .add(item(Items.BONE, 3, number(2, 4)))
                                .add(item(Items.ROTTEN_FLESH, 4, number(1, 2)))
                                .add(item(Items.STRING, 3, number(1, 2)))
                                .add(item(Items.COBWEB, 2, number(1, 2)))
                                .add(item(Items.SLIME_BALL, 2, number(1, 2)))
                                .add(item(Items.CLOCK, 1, one()))
                                .add(item(Items.COMPASS, 1, one()))
                                .add(item(Items.MAP, 2, number(1, 2)))
                                .add(item(Items.EMERALD, 2, number(1, 2)))
                                .add(item(Items.IRON_NUGGET, 2, number(2, 5)))
                                .add(item(Items.GOLD_NUGGET, 2, number(2, 5))))
                        .withPool(pool(number(4, 6))
                                .add(item(Items.GOLD_INGOT, 2, number(2, 4)))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3, one()))
                                .add(item(Items.DIAMOND, 2, one()))
                                .add(item(Items.GOLD_BLOCK, 2, number(1, 2)))
                                .add(enchantedItem(Items.BOOK, 2, number(10, 22), one(), _registries))
                                .add(enchantedItem(Items.BOW, 1, number(0, 12), one(), _registries))
                                .add(enchantedItem(Items.CROSSBOW, 1, number(0, 12), one(), _registries))
                                .add(enchantedItem(Items.DIAMOND_HELMET, 1, number(0, 12), one(), _registries))
                                .add(enchantedItem(Items.DIAMOND_CHESTPLATE, 1, number(0, 12), one(), _registries))
                                .add(enchantedItem(Items.DIAMOND_LEGGINGS, 1, number(0, 12), one(), _registries))
                                .add(enchantedItem(Items.DIAMOND_BOOTS, 1, number(0, 12), one(), _registries)))
                        .withPool(pool(number(0, 1))
                                .add(item(Items.MUSIC_DISC_11, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_13, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_BLOCKS, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_CAT, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_CHIRP, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_FAR, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_MALL, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_MELLOHI, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_STAL, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_STRAD, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_OTHERSIDE, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_WAIT, 1, number(0, 1)))
                                .add(item(Items.MUSIC_DISC_WARD, 1, number(0, 1)))
                        ));

                consumer.accept(location("monster_maze/prison"), LootTable.lootTable()
                        .withPool(pool(number(10, 16))
                                .add(item(Items.PAPER, 2, one()))
                                .add(item(Items.CHAIN, 2, one()))
                                .add(item(Items.ROTTEN_FLESH, 3, one()))
                                .add(item(Items.POTATO, 2, one()))
                                .add(item(Items.POISONOUS_POTATO, 2, one()))
                                .add(item(Items.STRING, 2, one()))
                                .add(item(Items.BONE, 3, one()))
                                .add(item(Items.MAP, 1, one()))
                                .add(item(Items.LEAD, 1, one()))
                                .add(item(Items.MUSHROOM_STEW, 1, one()))
                                .add(item(Items.CANDLE, 1, one()))
                                .add(suspiciousStew(2, one()))
                                .add(item(Items.BOWL, 2, one()))
                                .add(potion(1, Potions.WEAKNESS, number(0, 1)))
                                .add(potion(1, Potions.STRENGTH, number(0, 1))))
                        .withPool(pool(number(0, 1))
                                .add(enchantedItem(Items.STONE_PICKAXE, 4, number(5, 10), one(), _registries))
                                .add(enchantedItem(Items.GOLDEN_PICKAXE, 3, number(4, 9), one(), _registries))
                                .add(enchantedItem(Items.IRON_PICKAXE, 2, number(3, 8), one(), _registries))
                                .add(enchantedItem(Items.DIAMOND_PICKAXE, 1, number(2, 7), one(), _registries)))
                        .withPool(pool(number(0, 2))
                                .add(potion(1, Potions.HEALING, number(0, 1)))
                                .add(potion(1, Potions.INVISIBILITY, number(0, 1)))
                                .add(potion(1, Potions.LEAPING, number(0, 1)))
                                .add(potion(1, Potions.NIGHT_VISION, number(0, 1)))
                                .add(potion(1, Potions.REGENERATION, number(0, 1)))
                                .add(potion(1, Potions.SLOW_FALLING, number(0, 1)))
                                .add(potion(1, Potions.STRENGTH, number(0, 1)))
                                .add(potion(1, Potions.WATER_BREATHING, number(0, 1)))
                                .add(potion(1, Potions.FIRE_RESISTANCE, number(0, 1)))));

                consumer.accept(location("monster_maze/brewery"), LootTable.lootTable()
                        .withPool(pool(number(10, 15))
                                .add(item(Items.REDSTONE, 2, one()))
                                .add(item(Items.SUGAR, 2, one()))
                                .add(item(Items.GLOWSTONE_DUST, 2, one()))
                                .add(item(Items.SPIDER_EYE, 2, one()))
                                .add(item(Items.POISONOUS_POTATO, 1, one()))
                                .add(item(Items.RABBIT_FOOT, 1, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 1, one()))
                                .add(item(Items.BROWN_MUSHROOM, 2, one()))
                                .add(item(Items.AMETHYST_SHARD, 2, one()))
                                .add(item(Items.GLISTERING_MELON_SLICE, 1, one()))
                                .add(item(Items.PHANTOM_MEMBRANE, 1, one()))
                                .add(item(Items.GOLDEN_CARROT, 2, one()))
                                .add(item(Items.FERMENTED_SPIDER_EYE, 2, one()))
                                .add(item(Items.GUNPOWDER, 2, one()))
                                .add(item(Items.TURTLE_SCUTE, 2, one())))
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
                        ));
            } // Monster Maze
            {
                consumer.accept(location("pirate_ship"), LootTable.lootTable()
                        .withPool(pool(number(8, 16))
                                .add(item(Items.COD, 3, one()))
                                .add(item(Items.SALMON, 3, one()))
                                .add(item(Items.STRING, 4, one()))
                                .add(item(Items.FISHING_ROD, 1, one()))
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
                                .add(item(Items.COOKED_MUTTON, 1, one()))
                                .add(item(Items.COOKED_BEEF, 1, one()))
                                .add(item(Items.COOKED_PORKCHOP, 1, one()))
                                .add(item(Items.COOKED_CHICKEN, 1, one()))
                                .add(item(Items.COOKED_RABBIT, 1, one()))
                                .add(item(Items.COOKED_SALMON, 1, one()))
                                .add(item(Items.COOKED_COD, 1, one()))
                                .add(item(Items.BAKED_POTATO, 1, one()))
                                .add(item(Items.MUTTON, 1, one()))
                                .add(item(Items.BEEF, 1, one()))
                                .add(item(Items.PORKCHOP, 1, one()))
                                .add(item(Items.CHICKEN, 1, one()))
                                .add(item(Items.RABBIT, 1, one()))
                                .add(item(Items.SALMON, 1, one()))
                                .add(item(Items.COD, 1, one()))
                                .add(item(Items.POTATO, 1, one()))
                                .add(item(Items.CARROT, 1, one()))
                                .add(item(Items.WHEAT, 1, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 1, number(1, 3)))
                        ));

                consumer.accept(DELootTables.PillagerCamp.GENERAL, LootTable.lootTable()
                        .withPool(pool(number(6, 16))
                                .add(item(Items.MAP, 1, one()))
                                .add(item(Items.EMERALD, 1, one()))
                                .add(item(Items.PAPER, 7, one()))
                                .add(item(Items.GOLDEN_CARROT, 2, one()))
                                .add(item(Items.GOLDEN_APPLE, 2, one()))
                                .add(item(Items.COMPASS, 1, one()))
                                .add(item(Items.CLOCK, 1, one()))
                                .add(item(Items.IRON_AXE, 2, one()))
                                .add(item(Items.CROSSBOW, 1, one()))
                                .add(item(Items.ARROW, 5, one()))
                                .add(item(Items.DIAMOND, 1, number(0, 1)))
                                .add(item(Items.IRON_INGOT, 2, one()))
                        ));
            } // Pillager Camp
            {
                consumer.accept(location("ruined_building"), LootTable.lootTable()
                        .withPool(pool(number(2, 4))
                                .add(item(Items.IRON_NUGGET, 6, number(2, 5)))
                                .add(item(Items.IRON_INGOT, 2, one()))
                                .add(item(Items.GOLD_NUGGET, 4, number(1, 5)))
                                .add(item(Items.CAKE, 1, one()))
//                            .add(item(Items.FIELD_MASONED_BANNER_PATTERN, 1, one()))
                                .add(item(Items.GOLDEN_APPLE, 1, one())))
                        .withPool(pool(number(6, 10))
                                .add(item(Items.STICK, 6, number(1, 3)))
                                .add(item(Items.PAPER, 2, number(1, 2)))
                                .add(item(Items.BOOK, 1, one()))
                                .add(item(Items.ROTTEN_FLESH, 10, number(2, 5)))
                                .add(suspiciousStew(2, one()))
                                .add(item(Items.WHEAT, 5, number(1, 3)))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 5)))
                                .add(item(Items.MELON_SEEDS, 5, number(1, 3)))
                                .add(item(Items.POTATO, 4, number(1, 2)))
                                .add(item(Items.POISONOUS_POTATO, 6, number(1, 3)))
                                .add(item(Items.CARROT, 4, number(1, 3)))
                                .add(item(Items.PUMPKIN_SEEDS, 5, number(1, 4)))
                                .add(item(Items.GOLDEN_CARROT, 1, one()))
                        ));
            } // Ruined Building
            {
                consumer.accept(location("tree_house/roof"), LootTable.lootTable()
                        .withPool(pool(number(4, 8))
                                .add(item(Items.DIAMOND, 1, one()))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 3)))
                                .add(item(Items.IRON_INGOT, 3, one()))
                                .add(item(Items.SPYGLASS, 1, one()))
                                .add(item(Items.COCOA_BEANS, 6, number(1, 2)))
                                .add(item(Items.MELON_SEEDS, 6, number(1, 3)))
                        ));
            } // Tree House
            {
                consumer.accept(location("tower_of_the_undead/treasure"), LootTable.lootTable()
                        .withPool(pool(number(10, 18))
                                .add(item(Items.GOLD_NUGGET, 5, number(1, 2)))
                                .add(item(Items.GOLD_INGOT, 3, one()))
                                .add(item(Items.EXPERIENCE_BOTTLE, 3, one()))
                                .add(item(Items.IRON_NUGGET, 4, number(1, 2)))
                                .add(item(Items.IRON_INGOT, 2, one()))
                                .add(item(Items.GOLDEN_CARROT, 2, one()))
                                .add(item(Items.WHEAT_SEEDS, 8, number(1, 3)))
                                .add(item(Items.WHEAT, 6, number(1, 3)))
                                .add(item(Items.STRING, 6, number(1, 3)))
                                .add(item(Items.BONE, 7, number(1, 3)))
                                .add(item(Items.ROTTEN_FLESH, 10, number(1, 3)))
                                .add(item(Items.IRON_AXE, 1, one()))
                                .add(item(Items.IRON_SWORD, 1, one()))
                                .add(item(Items.CROSSBOW, 1, one()))
                                .add(item(Items.MAP, 1, one()))
                                .add(item(Items.COBWEB, 4, one()))
                                .add(item(Items.GOLDEN_APPLE, 2, one()))
                                .add(item(Items.ARROW, 6, number(2, 3))))
                        .withPool(pool(number(1, 3))
                                .add(item(Items.LEATHER_HELMET, 3, one()))
                                .add(item(Items.LEATHER_CHESTPLATE, 3, one()))
                                .add(item(Items.LEATHER_LEGGINGS, 3, one()))
                                .add(item(Items.LEATHER_BOOTS, 3, one()))
                                .add(item(Items.CHAINMAIL_HELMET, 2, one()))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 2, one()))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 2, one()))
                                .add(item(Items.CHAINMAIL_BOOTS, 2, one()))
                                .add(item(Items.IRON_HELMET, 1, one()))
                                .add(item(Items.IRON_CHESTPLATE, 1, one()))
                                .add(item(Items.IRON_LEGGINGS, 1, one()))
                                .add(enchantedItem(Items.BOOK, 1, number(4, 10), one(), _registries))
                                .add(item(Items.IRON_BOOTS, 1, one()))
                        ));
            } // Tower of the Undead
            {
                consumer.accept(DELootTables.SUNKEN_SHRINE, LootTable.lootTable()
                        .withPool(pool(number(8, 16))
                                .add(item(Items.COD, 3, one()))
                                .add(item(Items.SALMON, 3, one()))
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
                                .add(item(Items.EXPERIENCE_BOTTLE, 2, one()))
                                .add(item(Items.STRING, 8, number(1, 3)))
                                .add(item(Items.BREAD, 4, number(1, 3)))
                                .add(suspiciousStew(1, one()))
                                .add(item(Items.MAP, 2, one()))
                                .add(item(Items.LEATHER_HELMET, 1, one()))
                                .add(item(Items.LEATHER_CHESTPLATE, 1, one()))
                                .add(item(Items.LEATHER_LEGGINGS, 1, one()))
                                .add(item(Items.LEATHER_BOOTS, 1, one())))
                        .withPool(pool(number(1, 3))
                                .add(item(Items.DIAMOND, 1, one()))
                                .add(item(Items.SHIELD, 1, one()))
                                .add(item(Items.BOW, 1, one()))
                                .add(item(Items.CROSSBOW, 1, one()))
                                .add(item(Items.IRON_SWORD, 1, one()))
                                .add(item(Items.IRON_AXE, 1, one()))
                                .add(item(Items.IRON_HELMET, 1, one()))
                                .add(item(Items.IRON_CHESTPLATE, 1, one()))
                                .add(item(Items.IRON_LEGGINGS, 1, one()))
                                .add(item(Items.IRON_BOOTS, 1, one()))
                                .add(item(Items.CHAINMAIL_HELMET, 1, one()))
                                .add(item(Items.CHAINMAIL_CHESTPLATE, 1, one()))
                                .add(item(Items.CHAINMAIL_LEGGINGS, 1, one()))
                                .add(item(Items.CHAINMAIL_BOOTS, 1, one()))
                        ).withPool(pool(one())
                                .add(item(Items.SPYGLASS, 1, one()))
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
                                .add(item(Items.BOOK, 1, one())))
                        .withPool(pool(number(2, 4))
                                .add(item(Items.RABBIT_FOOT, 1, one()))
                                .add(item(Items.NAME_TAG, 1, one()))
                                .add(item(Items.GOLDEN_APPLE, 1, one()))
                                .add(enchantedItem(Items.BOOK, 1, number(6, 13), one(), _registries))));
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
                            .add(item(Items.DEAD_BUSH, 1, one()))
                    ).withPool(pool(number(2, 3))
                            .add(item(Items.DIAMOND, 1, one()))
                            .add(item(Items.COAL, 3, number(3, 6)))
                            .add(item(Items.IRON_ORE, 2, number(1, 3)))
                            .add(item(Items.GOLD_ORE, 2, number(2, 4)))
                            .add(item(Items.GOLD_NUGGET, 1, number(4, 12)))
                    )
            );
        }

        private static LootPoolEntryContainer.Builder<?> item(ItemLike item, int weight, NumberProvider amount) {
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
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

        private static LootPoolEntryContainer.Builder<?> suspiciousStew(int weight, NumberProvider amount) {
            return LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetStewEffectFunction.stewEffect().withEffect(MobEffects.NIGHT_VISION, number(7, 10)).withEffect(MobEffects.JUMP, number(7, 10)).withEffect(MobEffects.WEAKNESS, number(6, 8)).withEffect(MobEffects.BLINDNESS, number(5, 7)).withEffect(MobEffects.POISON, number(10, 20)).withEffect(MobEffects.SATURATION, number(7, 10)));
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