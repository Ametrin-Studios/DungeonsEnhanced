package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.api.registry.ASRegistries;
import com.ametrin.structures.api.spawner.DynamicSpawnerType;
import com.ametrin.structures.api.spawner.SpawnDataBuilder;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentTable;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public final class DEDynamicSpawners {
    public static final DeferredRegister<DynamicSpawnerType> REGISTER = DeferredRegister.create(ASRegistries.DYNAMIC_SPAWNER_TYPE, DungeonsEnhanced.MOD_ID);

    public static final DeferredHolder<DynamicSpawnerType, DynamicSpawnerType> MONSTER_MAZE_DEFAULT = REGISTER.register("", ()-> (builder, registry) ->
    {
        var ops = registry.createSerializationContext(NbtOps.INSTANCE);
//        builder.spawnData(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE))
//                .spawnData(createSpawnDataWithEquipment(EntityType.SKELETON, DELootTables.MonsterMaze.EQUIPMENT_SKELETON))
        builder.add(builder(EntityType.SPIDER).passenger(EntityType.SKELETON, Items.BOW, ops).build(), 1)
//                .spawnData(EntityType.CAVE_SPIDER)
        ;
    });

    public static final DeferredHolder<DynamicSpawnerType, DynamicSpawnerType> MONSTER_MAZE_BREWERY = REGISTER.register("", ()-> (builder, registry) ->
    {
        builder.add(EntityType.ZOMBIE, 1)
                .add(EntityType.SKELETON, 1)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
        ;
    });

    public static final DeferredHolder<DynamicSpawnerType, DynamicSpawnerType> MONSTER_MAZE_CHURCH = REGISTER.register("", ()-> (builder, registry) ->
    {
        builder.add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE), 3)
                .add(EntityType.SKELETON, 2)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
        ;
    });

    public static final DeferredHolder<DynamicSpawnerType, DynamicSpawnerType> MONSTER_MAZE_PRISON = REGISTER.register("", ()-> (builder, registry) ->
    {
        builder.add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE), 3)
                .add(EntityType.SKELETON, 2)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
        ;
    });

    private static SpawnDataBuilder builder(EntityType<?> entityType) {
        return new SpawnDataBuilder(entityType);
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, ResourceKey<LootTable> equipmentTable) {
        return createSpawnDataWithEquipment(entityType, new EquipmentTable(equipmentTable, 0.085f));
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, EquipmentTable equipmentTable) {
        return builder(entityType).equipment(equipmentTable).build();
    }
}