package com.ametrin.dungeons_enhanced.data.provider.loot_table.chest;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;

public record DEIcePitChestLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.IcePit.ARMORY, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(5, 8)).setBonusRolls(between(1, 2))
                        .add(item(Items.ARROW, between(6, 12)).setWeight(5))
                        .add(item(Items.IRON_INGOT, between(2, 4)).setWeight(2))
                        .add(item(Items.LEATHER, between(3, 7)).setWeight(6))
                        .add(item(Items.IRON_NUGGET, between(4, 9)).setWeight(5))
                        .add(item(Items.STRING, between(1, 6)).setWeight(6))
                        .add(item(Items.IRON_CHAIN).setWeight(6))
                        .add(item(Items.IRON_HORSE_ARMOR))
                        .add(item(Items.GOLDEN_APPLE))
                        .add(item(Items.TIPPED_ARROW, between(3, 7)).apply(SetPotionFunction.setPotion(Potions.SLOWNESS)).setWeight(4))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(empty(11))
                        .add(item(Items.BLADE_POTTERY_SHERD))
                )
                .withPool(LootPool.lootPool().setRolls(between(1, 3))
                        .apply(SetItemDamageFunction.setDamage(between(0.5f, 1)).when(LootItemRandomChanceCondition.randomChance(0.8f)))
                        .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, between(5, 12)).when(LootItemRandomChanceCondition.randomChance(0.5f)))
                        .add(item(Items.IRON_SWORD))
                        .add(item(Items.CHAINMAIL_HELMET))
                        .add(item(Items.CHAINMAIL_CHESTPLATE))
                        .add(item(Items.CHAINMAIL_LEGGINGS))
                        .add(item(Items.CHAINMAIL_BOOTS))
                        .add(item(Items.SHIELD))
                        .add(item(Items.BOW))
                        .add(item(Items.STONE_SWORD))
                )
        );

        output.accept(DELootTables.IcePit.BED, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(2, 3))
                        .add(enchantedItem(Items.BOOK, one(), registries, between(5, 15)))
                )
                .withPool(LootPool.lootPool().setRolls(between(3, 4)).setBonusRolls(between(1, 2))
                        .add(item(Items.BOOK, between(2, 3)).setWeight(2))
                        .add(item(Items.PAPER, between(3, 5)).setWeight(5))
                        .add(item(Items.SNOWBALL, between(4, 6)).setWeight(6))
                        .add(item(Items.SOUL_TORCH, between(2, 3)).setWeight(3))
                )
                .withPool(LootPool.lootPool().setRolls(between(3, 6))
                        .add(item(Items.WHITE_WOOL).setWeight(2))
                        .add(item(Items.BROWN_WOOL))
                        .add(item(Items.LIGHT_BLUE_WOOL))
                        .add(item(Items.LIGHT_GRAY_WOOL))
                        .add(item(Items.GRAY_WOOL))
                )
        );

        output.accept(DELootTables.IcePit.FOOD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(5, 6)).setBonusRolls(between(1, 3))
                        .add(item(Items.POTATO, between(2, 4)).setWeight(2))
                        .add(item(Items.BREAD, between(2, 3)))
                        .add(item(Items.BEETROOT, between(4, 6)).setWeight(4))
                        .add(item(Items.BEETROOT_SEEDS, between(3, 7)).setWeight(3))
                        .add(item(Items.PUMPKIN_PIE, between(1, 2)).setWeight(2))
                        .add(item(Items.PUMPKIN_SEEDS, between(2, 5)).setWeight(4))
                        .add(item(Items.STICK, between(1, 3)).setWeight(6))
                        .add(item(Items.ROTTEN_FLESH, between(5, 8)).setWeight(8))
                        .add(item(Items.COD, between(3, 4)).setWeight(5))
                        .add(item(Items.SALMON, between(3, 4)).setWeight(5))
                        .add(item(Items.BOWL).setWeight(2))
                )
        );

        output.accept(DELootTables.IcePit.GARDEN, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(7, 9)).setBonusRolls(one())
                        .add(item(Items.PUMPKIN_SEEDS, between(3, 5)).setWeight(7))
                        .add(item(Items.PUMPKIN, between(2, 3)).setWeight(2))
                        .add(item(Items.SPRUCE_SAPLING, between(2, 5)).setWeight(5))
                        .add(item(Items.FERN, between(3, 5)).setWeight(7))
                        .add(item(Items.BEETROOT_SEEDS, between(3, 4)).setWeight(5))
                        .add(item(Items.BEETROOT).setWeight(4))
                        .add(item(Items.BEETROOT_SOUP, between(1, 2)))
                        .add(item(Items.VINE).setWeight(3))
                        .add(item(Items.LARGE_FERN))
                        .add(item(Items.SPRUCE_LEAVES).setWeight(2))
                        .add(item(Items.STICK).setWeight(5))
                )
        );

        output.accept(DELootTables.IcePit.HALL, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(between(1, 2))
                        .add(item(Items.NAME_TAG))
                        .add(item(Items.LEATHER, between(1, 5)))
                        .add(item(Items.DIAMOND))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(empty(17))
                        .add(item(Items.MOURNER_POTTERY_SHERD))
                        .add(item(Items.PLENTY_POTTERY_SHERD))
                        .add(item(Items.ARMS_UP_POTTERY_SHERD))
                )
                .withPool(LootPool.lootPool().setRolls(between(2, 4))
                        .add(item(Items.PACKED_ICE, between(2, 6)))
                        .add(item(Items.SOUL_TORCH, between(2, 3)).setWeight(2))
                        .add(item(Items.BONE, between(3, 5)).setWeight(2))
                        .add(item(Items.STRING, between(3, 6)).setWeight(4))
                        .add(item(Items.ARROW, between(2, 3)).setWeight(3))
                        .add(item(Items.SNOWBALL, between(4, 5)).setWeight(5))
                        .add(item(Items.ICE, between(3, 4)).setWeight(4))
                        .add(item(Items.FLINT).setWeight(2))
                )
        );
    }
}
