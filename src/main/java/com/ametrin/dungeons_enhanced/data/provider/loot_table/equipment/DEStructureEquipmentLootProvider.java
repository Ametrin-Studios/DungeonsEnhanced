package com.ametrin.dungeons_enhanced.data.provider.loot_table.equipment;

import com.ametrin.dungeons_enhanced.registry.DELootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetEnchantmentsFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.util.function.BiConsumer;

import static com.ametrinstudios.ametrin.data.LootTableProviderHelper.*;

public record DEStructureEquipmentLootProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        deepCrypt(output);
        flyingDutchman(output);
        icePit(output);
    }

    private void deepCrypt(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        var black = new DyedItemColor(DyeColor.BLACK.getTextureDiffuseColor() & 0xFFFFFF);
        var red = new DyedItemColor(DyeColor.RED.getTextureDiffuseColor() & 0xFFFFFF);
        output.accept(DELootTables.DeepCrypt.EQUIPMENT_SKELETON, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.BOW).setWeight(3))
                        .add(item(Items.STONE_SWORD))
                        .add(item(Items.STONE_SPEAR))
                        .add(item(Items.COPPER_SWORD))
                        .add(item(Items.IRON_SWORD))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_HELMET).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, black)).setWeight(2))
                        .add(item(Items.LEATHER_HELMET).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, red)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75f))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_CHESTPLATE).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, black)))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_LEGGINGS).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, black)).setWeight(2))
                        .add(item(Items.LEATHER_LEGGINGS).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, red)))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_BOOTS).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, black)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75f))
                )
        );
    }

    private void flyingDutchman(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);

        output.accept(DELootTables.EQUIPMENT_HENDRICK_VAN_DER_DECKEN, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.BOW).apply(new SetEnchantmentsFunction.Builder()
                                .withEnchantment(enchantments.getOrThrow(Enchantments.FLAME), one())
                                .withEnchantment(enchantments.getOrThrow(Enchantments.PUNCH), one())
                                .withEnchantment(enchantments.getOrThrow(Enchantments.POWER), number(4))
                        ))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.IRON_HELMET))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_CHESTPLATE).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, new DyedItemColor(953344))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_LEGGINGS))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_BOOTS).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, new DyedItemColor(953344))))
                )
        );

        output.accept(DELootTables.EQUIPMENT_FLYING_DUTCHMAN_SKELETONS, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.BOW).setWeight(2))
                        .add(item(Items.STONE_SWORD))
                        .add(item(Items.STONE_SPEAR))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_HELMET).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, new DyedItemColor(65322))))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_CHESTPLATE).apply(SetComponentsFunction.setComponent(DataComponents.DYED_COLOR, new DyedItemColor(65322))))
                )
        );
    }

    private void icePit(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(DELootTables.IcePit.EQUIPMENT_ARMOR_STAND, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_HELMET).setWeight(2))
                        .add(item(Items.CHAINMAIL_HELMET).setWeight(2))
                        .add(item(Items.COPPER_HELMET))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_CHESTPLATE).setWeight(2))
                        .add(item(Items.CHAINMAIL_CHESTPLATE).setWeight(2))
                        .add(item(Items.COPPER_CHESTPLATE))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_LEGGINGS).setWeight(2))
                        .add(item(Items.CHAINMAIL_LEGGINGS).setWeight(2))
                        .add(item(Items.COPPER_LEGGINGS))
                        .add(empty(2))
                )
                .withPool(LootPool.lootPool().setRolls(one())
                        .add(item(Items.LEATHER_BOOTS).setWeight(2))
                        .add(item(Items.CHAINMAIL_BOOTS).setWeight(2))
                        .add(item(Items.COPPER_BOOTS))
                        .add(empty(2))
                )
        );
    }
}
