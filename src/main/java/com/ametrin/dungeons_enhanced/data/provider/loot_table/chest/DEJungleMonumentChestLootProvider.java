package com.ametrin.dungeons_enhanced.data.provider.loot_table.chest;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction.randomEnchantment;
import static net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction.enchantWithLevels;
import static net.minecraft.world.level.storage.loot.functions.SetComponentsFunction.setComponent;
import static net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount;
import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;
import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;

public record DEJungleMonumentChestLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.JungleMonument.FLOOR, lootTable()
                .withPool(lootPool().setRolls(between(3, 5)).setBonusRolls(one())
                        .add(item(Items.STRING, between(3, 4)).setWeight(3))
                        .add(item(Items.BONE, between(2, 4)).setWeight(2))
                        .add(item(Items.ROTTEN_FLESH, between(3, 5)).setWeight(4))
                        .add(item(Items.MELON_SEEDS, between(2, 3)).setWeight(2))
                        .add(item(Items.GLISTERING_MELON_SLICE, between(1, 2)))
                        .add(item(Items.MELON_SLICE, between(2, 3)).setWeight(2))
                        .add(item(Items.MELON))
                        .add(item(Items.ARROW, between(3, 4)).setWeight(2))
                        .add(item(Items.BAMBOO, between(2, 4)).setWeight(2))
                        .add(item(Items.COCOA_BEANS, between(1, 2)))
                )
                .withPool(lootPool().setRolls(one())
                        .add(empty(16))
                        .add(item(Items.ARCHER_POTTERY_SHERD))
                        .add(item(Items.SHELTER_POTTERY_SHERD))
                        .add(item(Items.EXPLORER_POTTERY_SHERD))
                )
                .withPool(lootPool().setRolls(between(2, 3)).setBonusRolls(one())
                        .add(item(Items.IRON_INGOT, between(1, 2)))
                        .add(item(Items.IRON_NUGGET, between(4, 6)).setWeight(2))
                        .add(item(Items.GOLD_INGOT, between(2, 3)).setWeight(2))
                        .add(item(Items.GOLD_NUGGET, between(6, 9)).setWeight(3))
                        .add(item(Items.LEATHER, between(1, 5)).setWeight(2))
                        .add(item(Items.EMERALD, between(2, 8)).setWeight(3))
                        .add(item(Items.GOLDEN_HOE))
                        .add(item(Items.TRIPWIRE_HOOK))
                        .add(item(Items.SLIME_BALL))
                )
        );

        output.accept(DELootTables.JungleMonument.TREASURE, lootTable()
                .withPool(lootPool().setRolls(between(1, 2))
                        .add(lootTableItem(Items.BOOK).apply(enchantWithLevels(registries, between(14, 18)).when(randomChance(0.5f))).setWeight(5))
                        .add(lootTableItem(Items.GOLDEN_APPLE).setWeight(3))
                        .add(lootTableItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .add(lootTableItem(Items.GOLDEN_HORSE_ARMOR))
                        .add(lootTableItem(Items.DIAMOND).setWeight(2))
                        .add(lootTableItem(Items.RABBIT_FOOT))
                        .add(lootTableItem(Items.ENDER_PEARL).setWeight(2))
                        .add(empty(5))
                )
                .withPool(lootPool().setRolls(one())
                        .add(empty(9))
                        .add(item(Items.FLOWER_BANNER_PATTERN))
                        .add(item(Items.BORDURE_INDENTED_BANNER_PATTERN))
                )
                .withPool(lootPool().setRolls(between(4, 7))
                        .add(item(Items.GOLD_NUGGET, between(2, 6)).setWeight(7))
                        .add(item(Items.VINE, between(3, 4)).setWeight(3))
                        .add(item(Items.GLOW_BERRIES, between(2, 3)).setWeight(3))
                        .add(item(Items.GOLD_INGOT, between(1, 3)).setWeight(5))
                        .add(item(Items.IRON_INGOT, between(1, 2)).setWeight(3))
                        .add(item(Items.EMERALD, between(2, 4)))
                        .add(item(Items.REDSTONE, between(2, 5)).setWeight(4))
                        .add(item(Items.GLISTERING_MELON_SLICE, between(2, 3)).setWeight(3))
                        .add(item(Items.GOLDEN_CARROT, between(2, 3)).setWeight(2))
                        .add(item(Items.STRING, between(1, 4)).setWeight(5))
                )
                .withPool(lootPool().setRolls(between(1, 2)).setBonusRolls(one())
                        .apply(enchantWithLevels(registries, exactly(30)).when(randomChance(0.45f)))
                        .add(lootTableItem(Items.IRON_SWORD))
                        .add(lootTableItem(Items.GOLDEN_HELMET))
                        .add(lootTableItem(Items.IRON_CHESTPLATE))
                        .add(lootTableItem(Items.GOLDEN_LEGGINGS))
                        .add(lootTableItem(Items.IRON_BOOTS))
                        .add(lootTableItem(Items.GOLDEN_AXE))
                )
                .withPool(lootPool().setRolls(one())
                        .add(lootTableItem(Items.PITCHER_POD))
                        .add(lootTableItem(Items.TORCHFLOWER_SEEDS))
                        .add(lootTableItem(Items.BRUSH))
                        .when(randomChance(0.3f))
                )
        );

        output.accept(DELootTables.JungleMonument.AXOLOTL_TREASURE, lootTable()
                .withPool(lootPool().setRolls(one())
                        .add(lootTableItem(Items.GOLDEN_DANDELION).setWeight(8))
                        .add(lootTableItem(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(5))
                        .add(lootTableItem(Items.HEART_OF_THE_SEA).setWeight(3))
                )
                .withPool(lootPool().setRolls(between(2, 4))
                        .add(lootTableItem(Items.EMERALD).setWeight(20).apply(setCount(between(3, 8))))
                        .add(lootTableItem(Items.GOLD_INGOT).setWeight(15).apply(setCount(between(2, 6))))
                        .add(potion(Potions.WATER_BREATHING, one()).setWeight(10))
                        .add(lootTableItem(Items.GOLDEN_APPLE).setWeight(8))
                        .add(lootTableItem(Items.BOOK).setWeight(8).apply(randomEnchantment()
                                .withEnchantment(registries.getOrThrow(Enchantments.AQUA_AFFINITY))
                                .withEnchantment(registries.getOrThrow(Enchantments.RESPIRATION))
                                .withEnchantment(registries.getOrThrow(Enchantments.DEPTH_STRIDER))
                                .withEnchantment(registries.getOrThrow(Enchantments.PROJECTILE_PROTECTION))
                                .withEnchantment(registries.getOrThrow(Enchantments.LOYALTY))
                                .withEnchantment(registries.getOrThrow(Enchantments.RIPTIDE))))
                        .add(lootTableItem(Items.DIAMOND).setWeight(6)
                                .apply(setCount(between(1, 3))))
                        .add(lootTableItem(Items.EMERALD_BLOCK).setWeight(3))
                        .add(lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                )
                .withPool(lootPool().setRolls(between(2, 4))
                        .add(lootTableItem(Items.GLOW_BERRIES).setWeight(9).apply(setCount(between(3, 8))))
                        .add(lootTableItem(Items.COCOA_BEANS).setWeight(7).apply(setCount(between(2, 5))))
                        .add(lootTableItem(Items.PRISMARINE_SHARD).setWeight(5).apply(setCount(between(2, 6))))
                        .add(lootTableItem(Items.DRIED_KELP).setWeight(6).apply(setCount(between(1, 4))))
                        .add(lootTableItem(Items.TROPICAL_FISH_BUCKET).setWeight(2))
                        .add(lootTableItem(Items.SPORE_BLOSSOM).setWeight(1))
                )
                .withPool(lootPool().setRolls(one()).setBonusRolls(one())
                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.AXOLOTL_VARIANT, Axolotl.Variant.LUCY)).setWeight(22))
                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.AXOLOTL_VARIANT, Axolotl.Variant.WILD)).setWeight(22))
                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.AXOLOTL_VARIANT, Axolotl.Variant.GOLD)).setWeight(22))
                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.AXOLOTL_VARIANT, Axolotl.Variant.CYAN)).setWeight(22))
                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.AXOLOTL_VARIANT, Axolotl.Variant.BLUE)).setWeight(12))
//                        .add(lootTableItem(Items.AXOLOTL_BUCKET).apply(setComponent(DataComponents.BUCKET_ENTITY_DATA, CustomData.of(Util.make(new CompoundTag(), tag -> tag.putInt("Variant", Axolotl.Variant.BLUE.getId())))))
                )
        );

        output.accept(DELootTables.JungleMonument.POT_AXOLOTL, lootTable()
                .withPool(lootPool().setRolls(one())
                        .add(lootTableItem(Items.NAME_TAG).setWeight(1))
                        .add(lootTableItem(Items.EMERALD).setWeight(1))
                        .add(lootTableItem(Items.STRING).setWeight(1).apply(setCount(between(2, 4))))
                        .add(lootTableItem(Items.TRIPWIRE_HOOK).setWeight(1))
                        .add(lootTableItem(Items.ARROW).setWeight(1).apply(setCount(between(1, 3))))
                )
        );
    }
}
