package com.barion.dungeons_enhanced.world;

import com.legacy.structure_gel.api.block_entity.SpawnerAccessHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentTable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Map;
import java.util.Optional;

public final class SpawnDataBuilder {
    private final EntityType<?> entity;
    private EquipmentTable equipmentTable = null;
    private CompoundTag passengerTag = null;

    public SpawnDataBuilder(EntityType<?> entity) {
        this.entity = entity;
    }

    public SpawnDataBuilder equipment(ResourceKey<LootTable> lootTable) {
        return equipment(new EquipmentTable(lootTable, 0.085f));
    }

    public SpawnDataBuilder equipment(ResourceKey<LootTable> lootTable, float dropChance) {
        return equipment(new EquipmentTable(lootTable, dropChance));
    }

    public SpawnDataBuilder equipment(ResourceKey<LootTable> lootTable, Map<EquipmentSlot, Float> dropChances) {
        return equipment(new EquipmentTable(lootTable, dropChances));
    }

    public SpawnDataBuilder equipment(EquipmentTable equipmentTable) {
        this.equipmentTable = equipmentTable;
        return this;
    }

    public SpawnDataBuilder passenger(EntityType<?> passenger) {
        this.passengerTag = new CompoundTag();
        passengerTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(passenger).toString());
        return this;
    }

    public SpawnDataBuilder passenger(EntityType<?> passenger, Item handItem, HolderLookup.Provider registry) {
        return passenger(passenger, handItem.getDefaultInstance(), registry);
    }

    public SpawnDataBuilder passenger(EntityType<?> passenger, ItemStack handItem, HolderLookup.Provider registry) {
        this.passengerTag = new CompoundTag();
        passengerTag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(passenger).toString());
        var handItems = new ListTag();
        handItems.add(handItem.save(registry));
        handItems.add(new CompoundTag());
        passengerTag.put("HandItems", handItems);
        return this;
    }

    public SpawnDataBuilder passenger(CompoundTag passenger) {
        this.passengerTag = passenger;
        return this;
    }

    public SpawnData build() {
        var tag = new CompoundTag();
        if (passengerTag != null) {
            var passengers = new ListTag();
            passengers.add(passengerTag);
            tag.put("Passengers", passengers);
        }
        return SpawnerAccessHelper.createSpawnerEntity(entity, tag, Optional.empty(), Optional.ofNullable(equipmentTable));
    }
}
