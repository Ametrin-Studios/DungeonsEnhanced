package com.ametrin.dungeons_enhanced.data.provider.loot_table.equipment;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.functions.SetComponentsFunction.setComponent;
import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;
import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;

public record DEMonsterMazeEquipmentLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        var trimPatterns = registries.lookupOrThrow(Registries.TRIM_PATTERN);
        var trimMaterials = registries.lookupOrThrow(Registries.TRIM_MATERIAL);
        var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.STONE_SWORD).setWeight(1))
                        .add(item(Items.STONE_SHOVEL).setWeight(2))
                        .add(item(Items.COPPER_SHOVEL).setWeight(2))
                        .add(item(Items.COPPER_HOE).setWeight(2))
                        .add(empty(4))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET).setWeight(1))
                        .add(item(Items.LEATHER_HELMET).setWeight(1))
                        .add(item(Items.CHAINMAIL_HELMET).setWeight(1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE).setWeight(1))
                        .add(item(Items.LEATHER_CHESTPLATE).setWeight(1))
                        .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_LEGGINGS).setWeight(1))
                        .add(item(Items.LEATHER_LEGGINGS).setWeight(1))
                        .add(item(Items.CHAINMAIL_LEGGINGS).setWeight(1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_BOOTS).setWeight(1))
                        .add(item(Items.LEATHER_BOOTS).setWeight(1))
                        .add(item(Items.CHAINMAIL_BOOTS).setWeight(1))
                        .add(empty(3))
                )
        );

        var ironWildTrim = new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.IRON), trimPatterns.getOrThrow(TrimPatterns.WILD));
        output.accept(DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.STONE_PICKAXE).setWeight(2))
                        .add(item(Items.STONE_SHOVEL).setWeight(2))
                        .add(item(Items.COPPER_PICKAXE).setWeight(1))
                        .add(item(Items.COPPER_SHOVEL).setWeight(1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET).setWeight(1))
                        .add(item(Items.LEATHER_HELMET).setWeight(1))
                        .add(item(Items.CHAINMAIL_HELMET).setWeight(1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE).setWeight(1))
                        .add(item(Items.LEATHER_CHESTPLATE).setWeight(1).apply(setComponent(DataComponents.TRIM, ironWildTrim).when(randomChance(0.4f))))
                        .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(1))
                        .add(empty(3))
                )
        );

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_SKELETON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.BOW).setWeight(1))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET).setWeight(1))
                        .add(item(Items.CHAINMAIL_HELMET).setWeight(1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE).setWeight(1))
                        .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_LEGGINGS).setWeight(1))
                        .add(item(Items.CHAINMAIL_LEGGINGS).setWeight(1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_BOOTS).setWeight(1))
                        .add(item(Items.CHAINMAIL_BOOTS).setWeight(1))
                        .add(empty(2))
                )
        );

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE_KING, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.DIAMOND_SWORD).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), exactly(2))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.DIAMOND_HELMET).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), exactly(2))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.DIAMOND_CHESTPLATE).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), exactly(2)).withEnchantment(enchantments.getOrThrow(Enchantments.PROTECTION), exactly(3))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.DIAMOND_LEGGINGS).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), exactly(2)).withEnchantment(enchantments.getOrThrow(Enchantments.PROTECTION), exactly(3))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.DIAMOND_BOOTS).apply(new SetEnchantmentsFunction.Builder().withEnchantment(enchantments.getOrThrow(Enchantments.UNBREAKING), exactly(2))))
                )
        );

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE_KING_GUARD, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.STONE_AXE))
                        .add(item(Items.STONE_SPEAR))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.IRON_HELMET))
                        .add(item(Items.COPPER_HELMET))
                        .add(empty(1))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.IRON_CHESTPLATE))
                        .add(item(Items.COPPER_CHESTPLATE))
                        .add(empty(1))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.IRON_LEGGINGS))
                        .add(item(Items.COPPER_LEGGINGS))
                        .add(empty(1))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.IRON_BOOTS))
                        .add(item(Items.COPPER_BOOTS))
                        .add(empty(1))
                )
        );
    }
}
