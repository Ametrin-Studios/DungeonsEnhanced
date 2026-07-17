package com.barion.dungeons_enhanced.data.provider.loot_table;

import com.barion.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.item.equipment.trim.TrimPatterns;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static com.barion.dungeons_enhanced.data.provider.loot_table.DELootTableProviderHelper.*;
import static net.minecraft.world.level.storage.loot.functions.SetComponentsFunction.setComponent;
import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;

public record DEEquipmentLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        var trimPatterns = registries.lookupOrThrow(Registries.TRIM_PATTERN);
        var trimMaterials = registries.lookupOrThrow(Registries.TRIM_MATERIAL);
        var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.STONE_SWORD, 1))
                        .add(item(Items.STONE_SHOVEL, 2))
                        .add(item(Items.COPPER_SHOVEL, 2))
                        .add(item(Items.COPPER_HOE, 2))
                        .add(empty(4))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET, 1))
                        .add(item(Items.LEATHER_HELMET, 1))
                        .add(item(Items.CHAINMAIL_HELMET, 1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE, 1))
                        .add(item(Items.LEATHER_CHESTPLATE, 1))
                        .add(item(Items.CHAINMAIL_CHESTPLATE, 1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_LEGGINGS, 1))
                        .add(item(Items.LEATHER_LEGGINGS, 1))
                        .add(item(Items.CHAINMAIL_LEGGINGS, 1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_BOOTS, 1))
                        .add(item(Items.LEATHER_BOOTS, 1))
                        .add(item(Items.CHAINMAIL_BOOTS, 1))
                        .add(empty(3))
                )
        );

        var ironWildTrim = new ArmorTrim(trimMaterials.getOrThrow(TrimMaterials.IRON), trimPatterns.getOrThrow(TrimPatterns.WILD));
        output.accept(DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.STONE_PICKAXE, 2))
                        .add(item(Items.STONE_SHOVEL, 2))
                        .add(item(Items.COPPER_PICKAXE, 1))
                        .add(item(Items.COPPER_SHOVEL, 1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET, 1))
                        .add(item(Items.LEATHER_HELMET, 1))
                        .add(item(Items.CHAINMAIL_HELMET, 1))
                        .add(empty(3))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE, 1))
                        .add(item(Items.LEATHER_CHESTPLATE, 1).apply(setComponent(DataComponents.TRIM, ironWildTrim).when(randomChance(0.4f))))
                        .add(item(Items.CHAINMAIL_CHESTPLATE, 1))
                        .add(empty(3))
                )
        );

        output.accept(DELootTables.MonsterMaze.EQUIPMENT_SKELETON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.BOW, 1))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_HELMET, 1))
                        .add(item(Items.CHAINMAIL_HELMET, 1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_CHESTPLATE, 1))
                        .add(item(Items.CHAINMAIL_CHESTPLATE, 1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_LEGGINGS, 1))
                        .add(item(Items.CHAINMAIL_LEGGINGS, 1))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.COPPER_BOOTS, 1))
                        .add(item(Items.CHAINMAIL_BOOTS, 1))
                        .add(empty(2))
                )
        );
    }
}
