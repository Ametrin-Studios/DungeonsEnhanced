package com.barion.dungeons_enhanced;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DELootGenerator extends LootTableProvider {
    public DELootGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override @Nonnull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(DEStructureLootTables::new, LootContextParamSets.CHEST));
    }

    @Override @ParametersAreNonnullByDefault
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((x, y) -> LootTables.validate(validationContext, x, y));
    }

    public static class DEStructureLootTables extends ChestLoot {
        @Override @ParametersAreNonnullByDefault
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
            lootTable.accept(location("flying_dutchman"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(7, 9))
                            .add(lootItem(Items.SKULL_BANNER_PATTERN, 1, one()))
                            .add(lootItem(Items.NAUTILUS_SHELL, 2, one()))
                            .add(lootItem(Items.TURTLE_EGG, 1, one()))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 1, one()))
                            .add(lootItem(Items.DIAMOND, 2, one()))
                            .add(lootItem(Items.ROTTEN_FLESH, 8, lootNumber(2, 5)))
                            .add(lootItem(Items.BONE, 8, lootNumber(1, 4)))
                            .add(lootItem(Items.FIRE_CHARGE, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.EMERALD, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.COOKED_COD, 5, lootNumber(1, 3)))
                            .add(lootItem(Items.COOKED_SALMON, 5, lootNumber(1, 3)))
                            .add(lootItem(Items.ARROW, 4, lootNumber(2, 4)))
                            .add(lootItem(Items.STRING, 5, lootNumber(1, 4)))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(6, 14), one()))
                            .add(lootItem(Items.KELP, 8, lootNumber(2, 5)))
                            .add(lootItem(Items.GOLD_INGOT, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_BLOCK, 1, one()))
                            .add(suspiciousStew(3, lootNumber(1, 2)))
                            .add(lootItem(Items.SPYGLASS, 1, one()))));

            lootTable.accept(location("monster_maze/church"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(5, 7))
                            .add(lootItem(Items.DIAMOND, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.BONE, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.PUMPKIN_SEEDS, 6, lootNumber(2, 4)))
                            .add(lootItem(Items.BOOK, 2, lootNumber(1, 3)))
                            .add(lootItem(Items.ROTTEN_FLESH, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.EGG, 3, lootNumber(1, 3)))
                            .add(lootItem(Items.SUGAR, 4, lootNumber(1, 3)))
                            .add(lootItem(Items.SUGAR_CANE, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_NUGGET, 4, lootNumber(4, 10)))
                            .add(lootItem(Items.GOLD_BLOCK, 1, one()))
                            .add(lootItem(Items.PUMPKIN, 2, one()))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(6, 14), one()))
                            .add(lootItem(Items.GOLD_INGOT, 4, lootNumber(2, 3)))));

            lootTable.accept(location("monster_maze/treasure"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(4, 9))
                            .add(lootItem(Items.BONE, 3, lootNumber(2, 4)))
                            .add(lootItem(Items.ROTTEN_FLESH, 4, lootNumber(1, 2)))
                            .add(lootItem(Items.STRING, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.COBWEB, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.SLIME_BALL, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.CLOCK, 1, one()))
                            .add(lootItem(Items.COMPASS, 1, one()))
                            .add(lootItem(Items.MAP, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.EMERALD, 2, lootNumber(1, 2)))
                            .add(lootItem(Items.IRON_NUGGET, 2, lootNumber(2, 5)))
                            .add(lootItem(Items.GOLD_NUGGET, 2, lootNumber(2, 5))))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(4, 6))
                            .add(lootItem(Items.GOLD_INGOT, 2, lootNumber(2, 4)))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 3, one()))
                            .add(lootItem(Items.DIAMOND, 2, one()))
                            .add(lootItem(Items.GOLD_BLOCK, 2, lootNumber(1, 2)))
                            .add(enchantedLootItem(Items.BOOK, 2, lootNumber(10, 22), one()))
                            .add(enchantedLootItem(Items.BOW, 1, lootNumber(0, 12), one()))
                            .add(enchantedLootItem(Items.CROSSBOW, 1, lootNumber(0, 12), one()))
                            .add(enchantedLootItem(Items.DIAMOND_HELMET, 1, lootNumber(0, 12), one()))
                            .add(enchantedLootItem(Items.DIAMOND_CHESTPLATE, 1, lootNumber(0, 12), one()))
                            .add(enchantedLootItem(Items.DIAMOND_LEGGINGS, 1, lootNumber(0, 12), one()))
                            .add(enchantedLootItem(Items.DIAMOND_BOOTS, 1, lootNumber(0, 12), one())))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(0, 1))
                            .add(lootItem(Items.MUSIC_DISC_11, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_13, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_BLOCKS, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_CAT, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_CHIRP, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_FAR, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_MALL, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_MELLOHI, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_STAL, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_STRAD, 1, one()))
                            .add(lootItem(Items.MUSIC_DISC_WARD, 1, one()))));

            lootTable.accept(location("monster_maze/prison"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(10, 16))
                            .add(lootItem(Items.PAPER, 2, one()))
                            .add(lootItem(Items.CHAIN, 2, one()))
                            .add(lootItem(Items.ROTTEN_FLESH, 3, one()))
                            .add(lootItem(Items.POTATO, 2, one()))
                            .add(lootItem(Items.POISONOUS_POTATO, 2, one()))
                            .add(lootItem(Items.STRING, 2, one()))
                            .add(lootItem(Items.BONE, 3, one()))
                            .add(lootItem(Items.MAP, 1, one()))
                            .add(lootItem(Items.LEAD, 1, one()))
                            .add(lootItem(Items.MUSHROOM_STEW, 1, one()))
                            .add(lootItem(Items.CANDLE, 1, one()))
                            .add(suspiciousStew(2, one()))
                            .add(lootItem(Items.BOWL, 2, one())))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(0, 1))
                            .add(enchantedLootItem(Items.STONE_PICKAXE, 4, lootNumber(5, 10), one()))
                            .add(enchantedLootItem(Items.GOLDEN_PICKAXE, 3, lootNumber(5, 10), one()))
                            .add(enchantedLootItem(Items.IRON_PICKAXE, 2, lootNumber(5, 10), one()))
                            .add(enchantedLootItem(Items.DIAMOND_PICKAXE, 1, lootNumber(5, 10), one()))));

            lootTable.accept(location("monster_maze/brewery"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(10, 15))
                            .add(lootItem(Items.REDSTONE, 2, one()))
                            .add(lootItem(Items.SUGAR, 2, one()))
                            .add(lootItem(Items.GLOWSTONE_DUST, 2, one()))
                            .add(lootItem(Items.SPIDER_EYE, 2, one()))
                            .add(lootItem(Items.POISONOUS_POTATO, 1, one()))
                            .add(lootItem(Items.RABBIT_FOOT, 1, one()))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 1, one()))
                            .add(lootItem(Items.BROWN_MUSHROOM, 2, one()))
                            .add(lootItem(Items.AMETHYST_SHARD, 2, one()))
                            .add(lootItem(Items.GLISTERING_MELON_SLICE, 1, one()))
                            .add(lootItem(Items.PHANTOM_MEMBRANE, 1, one()))
                            .add(lootItem(Items.GOLDEN_CARROT, 2, one()))
                            .add(lootItem(Items.FERMENTED_SPIDER_EYE, 2, one()))
                            .add(lootItem(Items.GUNPOWDER, 2, one()))
                            .add(lootItem(Items.SCUTE, 2, one())))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(0, 3))
                            .add(lootItem(Items.BLACK_CANDLE, 1, one()))
                            .add(lootItem(Items.CYAN_CANDLE, 1, one()))
                            .add(lootItem(Items.BLUE_CANDLE, 1, one()))
                            .add(lootItem(Items.BROWN_CANDLE, 1, one()))
                            .add(lootItem(Items.GRAY_CANDLE, 1, one()))
                            .add(lootItem(Items.GREEN_CANDLE, 1, one()))
                            .add(lootItem(Items.LIGHT_BLUE_CANDLE, 1, one()))
                            .add(lootItem(Items.LIGHT_GRAY_CANDLE, 1, one()))
                            .add(lootItem(Items.LIME_CANDLE, 1, one()))
                            .add(lootItem(Items.MAGENTA_CANDLE, 1, one()))
                            .add(lootItem(Items.ORANGE_CANDLE, 1, one()))
                            .add(lootItem(Items.PINK_CANDLE, 1, one()))
                            .add(lootItem(Items.PURPLE_CANDLE, 1, one()))
                            .add(lootItem(Items.RED_CANDLE, 1, one()))
                            .add(lootItem(Items.WHITE_CANDLE, 1, one()))
                            .add(lootItem(Items.YELLOW_CANDLE, 1, one()))
                            .add(lootItem(Items.CANDLE, 1, one()))));

            lootTable.accept(location("tower_of_the_undead/treasure"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(10, 18))
                            .add(lootItem(Items.GOLD_NUGGET, 5, lootNumber(1, 2)))
                            .add(lootItem(Items.GOLD_INGOT, 3, one()))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 3, one()))
                            .add(lootItem(Items.IRON_NUGGET, 4, lootNumber(1, 2)))
                            .add(lootItem(Items.IRON_INGOT, 2, one()))
                            .add(lootItem(Items.GOLDEN_CARROT, 2, one()))
                            .add(lootItem(Items.WHEAT_SEEDS, 8, lootNumber(1, 3)))
                            .add(lootItem(Items.WHEAT, 6, lootNumber(1, 3)))
                            .add(lootItem(Items.STRING, 6, lootNumber(1, 3)))
                            .add(lootItem(Items.BONE, 7, lootNumber(1, 3)))
                            .add(lootItem(Items.ROTTEN_FLESH, 10, lootNumber(1, 3)))
                            .add(lootItem(Items.IRON_AXE, 1, one()))
                            .add(lootItem(Items.IRON_SWORD, 1, one()))
                            .add(lootItem(Items.CROSSBOW, 1, one()))
                            .add(lootItem(Items.MAP, 1, one()))
                            .add(lootItem(Items.COBWEB, 4, one()))
                            .add(lootItem(Items.GOLDEN_APPLE, 2, one()))
                            .add(lootItem(Items.ARROW, 6, lootNumber(2, 3))))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(1, 3))
                            .add(lootItem(Items.LEATHER_HELMET, 3, one()))
                            .add(lootItem(Items.LEATHER_CHESTPLATE, 3, one()))
                            .add(lootItem(Items.LEATHER_LEGGINGS, 3, one()))
                            .add(lootItem(Items.LEATHER_BOOTS, 3, one()))
                            .add(lootItem(Items.CHAINMAIL_HELMET, 2, one()))
                            .add(lootItem(Items.CHAINMAIL_CHESTPLATE, 2, one()))
                            .add(lootItem(Items.CHAINMAIL_LEGGINGS, 2, one()))
                            .add(lootItem(Items.CHAINMAIL_BOOTS, 2, one()))
                            .add(lootItem(Items.IRON_HELMET, 1, one()))
                            .add(lootItem(Items.IRON_CHESTPLATE, 1, one()))
                            .add(lootItem(Items.IRON_LEGGINGS, 1, one()))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(4, 10), one()))
                            .add(lootItem(Items.IRON_BOOTS, 1, one()))));

            lootTable.accept(location("watch_tower"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(4, 10))
                            .add(lootItem(Items.CHAIN, 5, lootNumber(1, 3)))
                            .add(lootItem(Items.IRON_NUGGET, 6, lootNumber(3, 5)))
                            .add(lootItem(Items.IRON_INGOT, 3, lootNumber(1, 2)))
                            .add(lootItem(Items.STICK, 8, lootNumber(2, 5)))
                            .add(lootItem(Items.ROTTEN_FLESH, 7, lootNumber(3, 6)))
                            .add(lootItem(Items.COBBLESTONE, 6, lootNumber(2, 4)))
                            .add(lootItem(Items.ARROW, 4, lootNumber(5, 9)))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 2, one()))
                            .add(lootItem(Items.STRING, 8, lootNumber(1, 3)))
                            .add(lootItem(Items.BREAD, 4, lootNumber(1, 3)))
                            .add(suspiciousStew(1, one()))
                            .add(lootItem(Items.MAP, 2, one()))
                            .add(lootItem(Items.LEATHER_HELMET, 1, one()))
                            .add(lootItem(Items.LEATHER_CHESTPLATE, 1, one()))
                            .add(lootItem(Items.LEATHER_LEGGINGS, 1, one()))
                            .add(lootItem(Items.LEATHER_BOOTS, 1, one())))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(1, 3))
                            .add(lootItem(Items.DIAMOND, 1, one()))
                            .add(lootItem(Items.SHIELD, 1, one()))
                            .add(lootItem(Items.BOW, 1, one()))
                            .add(lootItem(Items.CROSSBOW, 1, one()))
                            .add(lootItem(Items.IRON_SWORD, 1, one()))
                            .add(lootItem(Items.IRON_AXE, 1, one()))
                            .add(lootItem(Items.IRON_HELMET, 1, one()))
                            .add(lootItem(Items.IRON_CHESTPLATE, 1, one()))
                            .add(lootItem(Items.IRON_LEGGINGS, 1, one()))
                            .add(lootItem(Items.IRON_BOOTS, 1, one()))
                            .add(lootItem(Items.CHAINMAIL_HELMET, 1, one()))
                            .add(lootItem(Items.CHAINMAIL_CHESTPLATE, 1, one()))
                            .add(lootItem(Items.CHAINMAIL_LEGGINGS, 1, one()))
                            .add(lootItem(Items.CHAINMAIL_BOOTS, 1, one()))));

            lootTable.accept(location("witch_tower"), LootTable.lootTable()
                    .withPool(LootPool.lootPool().setRolls(lootNumber(7, 10))
                            .add(lootItem(Items.SPIDER_EYE, 1, lootNumber(2, 3)))
                            .add(lootItem(Items.GUNPOWDER, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.REDSTONE, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.RABBIT_HIDE, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.BEETROOT_SOUP, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.EXPERIENCE_BOTTLE, 1, lootNumber(0, 1)))
                            .add(lootItem(Items.LEAD, 1, lootNumber(0, 1)))
                            .add(lootItem(Items.CLOCK, 1, lootNumber(0, 1)))
                            .add(lootItem(Items.SUGAR, 1, lootNumber(1, 2)))
                            .add(lootItem(Items.PAPER, 1, lootNumber(1, 3)))
                            .add(lootItem(Items.STRING, 2, lootNumber(2, 3)))
                            .add(lootItem(Items.BOOK, 1, one())))
                    .withPool(LootPool.lootPool().setRolls(lootNumber(2, 4))
                            .add(lootItem(Items.RABBIT_FOOT, 1, one()))
                            .add(lootItem(Items.NAME_TAG, 1, one()))
                            .add(lootItem(Items.GOLDEN_APPLE, 1, one()))
                            .add(enchantedLootItem(Items.BOOK, 1, lootNumber(6, 13), one()))));
        }

        private LootPoolEntryContainer.Builder<?> lootItem(Item item, int weight, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount));
        }
        private LootPoolEntryContainer.Builder<?> enchantedLootItem(Item item, int weight, NumberProvider enchant, NumberProvider amount){
            return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(EnchantWithLevelsFunction.enchantWithLevels(enchant));
        }
        private LootPoolEntryContainer.Builder<?> suspiciousStew(int weight, NumberProvider amount){
            return LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(weight).apply(SetItemCountFunction.setCount(amount)).apply(SetStewEffectFunction.stewEffect().withEffect(MobEffects.NIGHT_VISION, UniformGenerator.between(7.0F, 10.0F)).withEffect(MobEffects.JUMP, UniformGenerator.between(7.0F, 10.0F)).withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6.0F, 8.0F)).withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5.0F, 7.0F)).withEffect(MobEffects.POISON, UniformGenerator.between(10.0F, 20.0F)).withEffect(MobEffects.SATURATION, UniformGenerator.between(7.0F, 10.0F)));
        }

        private NumberProvider one(){
            return ConstantValue.exactly(1);
        }
        private NumberProvider lootNumber(int amount){
            return ConstantValue.exactly(amount);
        }
        private NumberProvider lootNumber(int minAmount, int maxAmount){
            return UniformGenerator.between(minAmount, maxAmount);
        }

        private static ResourceLocation location(String name){
            return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + name);
        }
    }
}